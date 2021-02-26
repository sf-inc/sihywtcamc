package com.github.charlyb01.sihywtcamc.mixin.drink;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MilkBucketItem.class)
public class MilkMixin extends Item {
    public MilkMixin(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return ModConfig.get().generalConfig.drinkFaster ? 20 : 32;
    }
}
