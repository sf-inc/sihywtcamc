package com.github.charlyb01.sihywtcamc.mixin.crossbow;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PiercingEnchantment.class)
public class PiercingMixin extends Enchantment {
    protected PiercingMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return ModConfig.get().toolsConfig.crossbowMultishotInclusive ?
                super.canAccept(other) :
                !(other instanceof MultishotEnchantment)
                && super.canAccept(other) ;
    }
}
