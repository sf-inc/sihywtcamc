package com.github.charlyb01.sihywtcamc.mixin.item.potion;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LingeringPotionItem.class)
public class LingeringPotionMixin extends ThrowablePotionItem {
    public LingeringPotionMixin(Item.Settings settings) {
        super(settings);
    }

    @Inject(method = "use", at = @At("HEAD"))
    private void setLingeringCooldown(World world, PlayerEntity user, Hand hand,
                                   CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        user.getItemCooldownManager().set(this, ModConfig.get().generalConfig.lingeringPotionCooldown);
    }
}
