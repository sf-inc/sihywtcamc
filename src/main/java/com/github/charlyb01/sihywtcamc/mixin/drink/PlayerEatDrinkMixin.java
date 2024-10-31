package com.github.charlyb01.sihywtcamc.mixin.drink;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEatDrinkMixin extends LivingEntity {
    protected PlayerEatDrinkMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;dropShoulderEntities()V"))
    private void cancelEatDrink(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().generalConfig.eatDrinkCancellable && source.getAttacker() != null) {
            if (this.getActiveItem().get(DataComponentTypes.FOOD) != null
                    || this.getActiveItem().getItem() instanceof PotionItem
                    || this.getActiveItem().isOf(Items.MILK_BUCKET)) {
                this.clearActiveItem();
            }
        }
    }
}
