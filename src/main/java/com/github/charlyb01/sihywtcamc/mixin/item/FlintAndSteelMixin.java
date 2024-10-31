package com.github.charlyb01.sihywtcamc.mixin.item;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelMixin extends ItemMixin {
    @Override
    protected ActionResult updateUseOnEntity(ActionResult original, ItemStack stack, PlayerEntity player,
                                             LivingEntity entity, Hand hand) {
        if (ModConfig.get().toolsConfig.flintFireEntities) {
            if (!player.getWorld().isClient()) {
                entity.setOnFireFor(3);
                if (!player.isCreative() && entity instanceof MobEntity mob) {
                    mob.setTarget(player);
                }
                stack.damage(1, player, LivingEntity.getSlotForHand(hand));
            }

            player.getWorld().playSound(player, entity.getBlockPos(), SoundEvents.ITEM_FLINTANDSTEEL_USE,
                    entity.getSoundCategory(), 1.0F, player.getRandom().nextFloat() * 0.4F + 0.8F);
            return ActionResult.CONSUME;
        }

        return original;
    }
}
