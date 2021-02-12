package com.github.charlyb01.sihywtcamc.mixin.bow.power;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PunchEnchantment.class)
public class PunchMixin extends Enchantment {
    protected PunchMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof PowerEnchantment)
                && super.canAccept(other);
    }
}
