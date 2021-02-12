package com.github.charlyb01.sihywtcamc.mixin.bow.power;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InfinityEnchantment.class)
public class InfinityMixin extends Enchantment {
    protected InfinityMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return !(other instanceof MendingEnchantment)
                && !(other instanceof PowerEnchantment)
                && super.canAccept(other);
    }
}
