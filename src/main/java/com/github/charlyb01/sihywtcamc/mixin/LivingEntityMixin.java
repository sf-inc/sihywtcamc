package com.github.charlyb01.sihywtcamc.mixin;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", ordinal = 1))
    private void reduceInvulnerability(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().generalConfig.appropriateInvulnerability && source.getAttacker() instanceof PlayerEntity entity) {
            this.timeUntilRegen = Math.min(20,
                    MathHelper.ceil(60 / entity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED)));
        }
    }
}
