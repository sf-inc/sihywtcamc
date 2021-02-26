package com.github.charlyb01.sihywtcamc.mixin.projectile;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EggItem.class)
public class EggMixin {
    @Inject(method = "use", at = @At("HEAD"))
    private void addEggCooldown(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (ModConfig.get().generalConfig.eggSnowball.cooldown) {
            user.getItemCooldownManager().set(Items.EGG, 5);
        }
    }
}
