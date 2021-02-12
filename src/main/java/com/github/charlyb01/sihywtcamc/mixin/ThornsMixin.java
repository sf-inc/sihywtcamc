package com.github.charlyb01.sihywtcamc.mixin;

import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThornsEnchantment.class)
public class ThornsMixin {
    @Inject(at = @At("HEAD"), method = "isAcceptableItem", cancellable = true)
    private void shieldAcceptable(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof ShieldItem) {
            cir.setReturnValue(true);
        }
    }
}
