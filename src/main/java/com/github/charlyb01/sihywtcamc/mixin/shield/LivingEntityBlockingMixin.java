package com.github.charlyb01.sihywtcamc.mixin.shield;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityBlockingMixin extends Entity {
    @Unique
    private float sihywtcamc_damageAmount;

    @Shadow public abstract boolean blockedByShield(DamageSource source);

    public LivingEntityBlockingMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "blockedByShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;getPosition()Lnet/minecraft/util/math/Vec3d;"),
            cancellable = true)
    private void reduceShieldBlockingArc(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().toolsConfig.shieldReduceArc) {
            Vec3d vec3d = source.getPosition();
            if (vec3d != null) {
                Vec3d vec3d2 = this.getRotationVec(1.0F);
                Vec3d vec3d3 = vec3d.relativize(this.getPos()).normalize();
                vec3d3 = new Vec3d(vec3d3.x, 0.0D, vec3d3.z);
                cir.setReturnValue(vec3d3.dotProduct(vec3d2) < -0.5D);
            } else {
                cir.setReturnValue(false);
            }
        }
    }

    @ModifyVariable(method = "damage", ordinal = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private boolean cancelNoDamageKnockback(boolean bl, DamageSource source, float amount) {
        if (amount == 0.0F
                && source.isIn(DamageTypeTags.IS_PROJECTILE)
                && ModConfig.get().generalConfig.eggSnowball.shieldStopKnockack
                && this.blockedByShield(source)) {
            bl = true;
        } else if (Math.max(0.0F, sihywtcamc_damageAmount - ModConfig.get().toolsConfig.shieldDamageProtection) > 0.0F
                && ModConfig.get().toolsConfig.shieldReduceProtection) {
            bl = false;
        }
        return bl;
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void saveDamageAmount(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        sihywtcamc_damageAmount = amount;
    }

    @ModifyVariable(method = "damage", ordinal = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"), argsOnly = true)
    private float reduceDamageIfBlocked(float amount2, DamageSource source, float amount) {
        return ModConfig.get().toolsConfig.shieldReduceProtection && !source.isIn(DamageTypeTags.IS_EXPLOSION) ?
                Math.max(0.0F, sihywtcamc_damageAmount - ModConfig.get().toolsConfig.shieldDamageProtection) : amount2;
    }

    @Inject(method = "isBlocking", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getMaxUseTime(Lnet/minecraft/item/ItemStack;)I"), cancellable = true)
    private void instantlyBlock(CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().toolsConfig.shieldInstantBlock) {
            cir.setReturnValue(true);
        }
    }
}
