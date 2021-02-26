package com.github.charlyb01.sihywtcamc.mixin;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerKnockbackMixin extends LivingEntity {
    @Shadow protected abstract void takeShieldHit(LivingEntity attacker);

    protected PlayerKnockbackMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At(value = "RETURN", ordinal = 3), cancellable = true)
    private void takeAllKnockback(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().generalConfig.eggSnowball.knockbackPlayer) {
            cir.setReturnValue(super.damage(source, amount));
        }
    }
}
