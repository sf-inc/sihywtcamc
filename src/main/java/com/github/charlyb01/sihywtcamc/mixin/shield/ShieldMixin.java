package com.github.charlyb01.sihywtcamc.mixin.shield;

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
        return 10;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) user;
            if (playerEntity.getMainHandStack().getItem() instanceof AxeItem) {
                playerEntity.getItemCooldownManager().set(playerEntity.getMainHandStack().getItem(), 40);
            }
        }
    }
}
