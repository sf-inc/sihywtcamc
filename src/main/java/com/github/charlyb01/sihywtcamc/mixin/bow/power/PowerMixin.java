package com.github.charlyb01.sihywtcamc.mixin.bow.power;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PowerEnchantment.class)
public class PowerMixin extends Enchantment {
    protected PowerMixin(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return ModConfig.get().toolsConfig.bowPowerExclusive ?
                !(other instanceof PunchEnchantment)
                && !(other instanceof FlameEnchantment)
                && !(other instanceof InfinityEnchantment) :
                super.canAccept(other);
    }
}
