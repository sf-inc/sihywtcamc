package com.github.charlyb01.sihywtcamc.mixin.entity;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityBlockingMixin extends Entity {
    @Shadow public abstract boolean blockedByShield(DamageSource source);

    public LivingEntityBlockingMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyReturnValue(method = "blockedByShield", at = @At(value = "RETURN", ordinal = 0))
    private boolean reduceShieldBlockingArc(boolean original, DamageSource source) {
        if (!ModConfig.get().toolsConfig.shieldReduceArc) return original;

        Vec3d vec3d = source.getPosition();
        if (vec3d != null) {
            Vec3d vec3d2 = this.getRotationVector(0.0F, this.getHeadYaw());
            Vec3d vec3d3 = vec3d.relativize(this.getPos());
            vec3d3 = vec3d3.withAxis(Direction.Axis.Y, 0.0).normalize();
            return vec3d3.dotProduct(vec3d2) < -0.5D;
        } else {
            return false;
        }
    }

    @Definition(id = "amount", local = @Local(ordinal = 0, type = float.class))
    @Expression("amount > 0.0")
    @WrapOperation(method = "damage", at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 0))
    private boolean blockNullDamage(float left, float right, Operation<Boolean> original) {
        return original.call(left, right) || left == right;
    }

    @Definition(id = "g", local = @Local(ordinal = 2, type = float.class))
    @Definition(id = "amount", local = @Local(ordinal = 0, type = float.class))
    @Expression("g = @(amount)")
    @ModifyExpressionValue(method = "damage", at = @At("MIXINEXTRAS:EXPRESSION"))
    private float updateAbsorbedDamage(float original, DamageSource source, float amount) {
        if (ModConfig.get().toolsConfig.shieldReduceProtection && !source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            return Math.min(original, (float) ModConfig.get().toolsConfig.shieldDamageProtection);
        } else {
            return original;
        }
    }

    @Definition(id = "amount", local = @Local(ordinal = 0, type = float.class))
    @Expression("amount = @(0.0)")
    @ModifyExpressionValue(method = "damage", at = @At("MIXINEXTRAS:EXPRESSION"))
    private float updateDealtDamage(float original, DamageSource source, float amount) {
        if (ModConfig.get().toolsConfig.shieldReduceProtection && !source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            return Math.max(original, amount - (float) ModConfig.get().toolsConfig.shieldDamageProtection);
        } else {
            return original;
        }
    }

    @Definition(id = "bl", local = @Local(ordinal = 0, type = boolean.class))
    @Expression("bl = @(true)")
    @ModifyExpressionValue(method = "damage", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean updateBlockedByShield(boolean original, DamageSource source, float amount) {
        if (ModConfig.get().toolsConfig.shieldReduceProtection && !source.isIn(DamageTypeTags.IS_EXPLOSION)) {
            return amount == 0.f;
        } else {
            return original;
        }
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;sendEntityDamage(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;)V"))
    private void sendBlockingStatusEvenWhenDamaged(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (this.blockedByShield(source)) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.BLOCK_WITH_SHIELD);
        }
    }

    @ModifyExpressionValue(method = "isBlocking", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getMaxUseTime(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)I"))
    private int instantlyBlock(int original) {
        return ModConfig.get().toolsConfig.shieldInstantBlock
                ? original + 5
                : original;
    }
}
