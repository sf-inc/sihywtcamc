package com.github.charlyb01.sihywtcamc.mixin.bow;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BowItem.class)
public class BowMixin {
    @ModifyVariable(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setDamage(D)V", shift = At.Shift.AFTER),
            method = "onStoppedUsing")
    private PersistentProjectileEntity changePowerDamageB(PersistentProjectileEntity projectileEntity, ItemStack stack, World world,
                                                          LivingEntity user, int remainingUseTicks) {
        if (ModConfig.get().toolsConfig.bowLessPower) {
            int level = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
            if (level > 0) {
                projectileEntity.setDamage(projectileEntity.getDamage() - (double) level * 0.5D - 0.5D);
                projectileEntity.setDamage(projectileEntity.getDamage() + (double) level * 0.4D);
            }
        }
        return projectileEntity;
    }
}
