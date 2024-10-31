package com.github.charlyb01.sihywtcamc.mixin.item.projectile;

import com.github.charlyb01.sihywtcamc.config.Constants;
import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SnowballItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowballItem.class)
public class SnowBallMixin {
    @Inject(method = "use", at = @At("HEAD"))
    private void addSnowBallCooldown(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (ModConfig.get().generalConfig.eggSnowball.cooldown) {
            user.getItemCooldownManager().set(Items.SNOWBALL, Constants.SHORT_COOLDOWN);
        }
    }
}
