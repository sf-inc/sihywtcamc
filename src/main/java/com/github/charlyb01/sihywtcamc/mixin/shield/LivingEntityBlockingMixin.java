package com.github.charlyb01.sihywtcamc.mixin.shield;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityBlockingMixin extends Entity {
    @Shadow protected abstract boolean blockedByShield(DamageSource source);

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
                Vec3d vec3d3 = vec3d.reverseSubtract(this.getPos()).normalize();
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
                && source.isProjectile()
                && ModConfig.get().generalConfig.eggSnowball.shieldStopKnockack
                && this.blockedByShield(source)) {
            bl = true;
        }
        return bl;
    }
}