package com.github.charlyb01.sihywtcamc.mixin.sweep;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class PlayerSweepAttackMixin {
    @ModifyVariable(method = "attack", ordinal = 3, at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/entity/LivingEntity;)I"))
    private boolean sweepingIfEnchant(boolean bl4) {
        return ModConfig.get().toolsConfig.swordSweepingEdge ?
                bl4 && (EnchantmentHelper.getSweepingMultiplier((LivingEntity) (Object) this) > 0) :
                bl4;
    }
}
