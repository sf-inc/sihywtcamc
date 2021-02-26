package com.github.charlyb01.sihywtcamc.mixin.bow;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class ProjectileMixin {
    @Shadow public abstract double getDamage();

    @Shadow public abstract void setDamage(double damage);

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setDamage(D)V", shift = At.Shift.AFTER),
            method = "applyEnchantmentEffects")
    private void changePowerDamageP(LivingEntity entity, float damageModifier, CallbackInfo ci) {
        if (ModConfig.get().toolsConfig.bowLessPower) {
            int level = EnchantmentHelper.getEquipmentLevel(Enchantments.POWER, entity);
            if (level > 0) {
                this.setDamage(this.getDamage() - (double) level * 0.5D - 0.5D);
                this.setDamage(this.getDamage() + (double) level * 0.4D);
            }
        }
    }
}
