package com.github.charlyb01.sihywtcamc.mixin.item.potion;

import com.github.charlyb01.sihywtcamc.config.Constants;
import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PotionItem.class)
public class PotionMixin {
    @ModifyReturnValue(method = "getMaxUseTime", at = @At("RETURN"))
    private int shortedDrinkTime(int original) {
        return ModConfig.get().generalConfig.drinkFaster
                ? Constants.DRINK_DURATION
                : original;
    }
}
