package com.github.charlyb01.sihywtcamc.mixin.bow.power;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FlameEnchantment;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlameEnchantment.class)
public class FlameMixin extends Enchantment {
    protected FlameMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return ModConfig.get().toolsConfig.bowPowerExclusive ?
                !(other instanceof PowerEnchantment)
                && super.canAccept(other) :
                super.canAccept(other);
    }
}
