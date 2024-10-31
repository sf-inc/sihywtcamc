package com.github.charlyb01.sihywtcamc.mixin.drink;

import com.github.charlyb01.sihywtcamc.config.Constants;
import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.Item;
import net.minecraft.item.MilkBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MilkBucketItem.class)
public class MilkMixin extends Item {
    public MilkMixin(Settings settings) {
        super(settings);
    }

    @ModifyReturnValue(method = "getMaxUseTime", at = @At("RETURN"))
    private int shortedDrinkTime(int original) {
        return ModConfig.get().generalConfig.drinkFaster
                ? Constants.DRINK_DURATION
                : original;
    }
}
