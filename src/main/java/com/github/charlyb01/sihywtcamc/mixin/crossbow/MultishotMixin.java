package com.github.charlyb01.sihywtcamc.mixin.crossbow;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MultishotEnchantment;
import net.minecraft.enchantment.PiercingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MultishotEnchantment.class)
public class MultishotMixin extends Enchantment {
    protected MultishotMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return ModConfig.get().toolsConfig.crossbowMultishotInclusive ?
                super.canAccept(other) :
                !(other instanceof PiercingEnchantment)
                && super.canAccept(other) ;
    }
}
