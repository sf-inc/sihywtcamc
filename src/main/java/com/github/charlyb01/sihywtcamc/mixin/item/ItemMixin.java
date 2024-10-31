package com.github.charlyb01.sihywtcamc.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    protected void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks,
                                  CallbackInfo ci) {

    }

    @ModifyReturnValue(method = "getEnchantability", at = @At("RETURN"))
    protected int updateEnchantability(int original) {
        return original;
    }

    @ModifyReturnValue(method = "useOnEntity", at = @At("RETURN"))
    protected ActionResult updateUseOnEntity(ActionResult original, ItemStack stack, PlayerEntity user,
                                             LivingEntity entity, Hand hand) {
        return original;
    }
}
