package com.github.charlyb01.sihywtcamc.mixin.shield;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShieldItem.class)
public class ShieldMixin extends Item {
    public ShieldMixin(Settings settings) {
        super(settings);
    }

    @Override
    public int getEnchantability() {
        return ModConfig.get().toolsConfig.shieldEnchantable ? 10 : 0;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        if (ModConfig.get().toolsConfig.axeCooldown
                && user instanceof PlayerEntity playerEntity) {
            if (playerEntity.getMainHandStack().getItem() instanceof AxeItem) {
                playerEntity.getItemCooldownManager().set(playerEntity.getMainHandStack().getItem(), 40);
            }
        }
    }
}
