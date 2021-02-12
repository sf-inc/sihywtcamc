package com.github.charlyb01.sihywtcamc.mixin.shield;

import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShieldItem.class)
public class ShieldMixin extends Item {
    public ShieldMixin(Settings settings) {
        super(settings);
    }

    @Override
    public int getEnchantability() {
        return 10;
    }
}
