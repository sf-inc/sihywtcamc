package com.github.charlyb01.sihywtcamc.mixin.shield;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityBlockingMixin extends Entity {
    @Unique
    private float sihywtcamc_damageAmount;

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

    @ModifyVariable(method = "damage", ordinal = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private boolean cancelNoDamageKnockback(boolean bl, DamageSource source, float amount) {
        if (Math.max(0.0F, this.sihywtcamc_damageAmount - ModConfig.get().toolsConfig.shieldDamageProtection) > 0.0F
                && ModConfig.get().toolsConfig.shieldReduceProtection) {
            bl = false;
        }
        return bl;
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void saveDamageAmount(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        this.sihywtcamc_damageAmount = amount;
    }

    @ModifyVariable(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z", ordinal = 1), argsOnly = true)
    private float reduceDamageIfBlocked(float amount2, DamageSource source, float amount) {
        return ModConfig.get().toolsConfig.shieldReduceProtection && !source.isIn(DamageTypeTags.IS_EXPLOSION) ?
                Math.max(0.0F, this.sihywtcamc_damageAmount - ModConfig.get().toolsConfig.shieldDamageProtection) : amount2;
    }

    @ModifyExpressionValue(method = "isBlocking", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getMaxUseTime(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/LivingEntity;)I"))
    private int instantlyBlock(int original) {
        return ModConfig.get().toolsConfig.shieldInstantBlock
                ? original + 5
                : original;
    }
}
