package com.github.charlyb01.sihywtcamc.mixin.item;

import com.github.charlyb01.sihywtcamc.config.Constants;
import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ShieldItem.class)
public class ShieldMixin extends ItemMixin {
    @Unique
    private static final int ENCHANTABILITY = 10;

    @Override
    protected void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        if (ModConfig.get().toolsConfig.axeCooldown
                && user instanceof PlayerEntity playerEntity) {
            if (playerEntity.getMainHandStack().getItem() instanceof AxeItem) {
                // TODO: This does not prevent from attacking with an axe!
                playerEntity.getItemCooldownManager().set(playerEntity.getMainHandStack().getItem(), Constants.LONG_COOLDOWN);
            }
        }
    }

    @Override
    protected int updateEnchantability(int original) {
        return ModConfig.get().toolsConfig.shieldEnchantable
                ? ENCHANTABILITY
                : original;
    }
}
