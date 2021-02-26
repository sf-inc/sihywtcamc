package com.github.charlyb01.sihywtcamc.mixin.eating;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.imixin.IPlayerDamagedMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract ItemStack getActiveItem();

    @Shadow public abstract void clearActiveItem();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;usageTick(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)V"), method = "tickActiveItemStack")
    private void cancelUse(CallbackInfo ci) {
        if (ModConfig.get().generalConfig.eatDrinkCancellable
                && this.getType().equals(EntityType.PLAYER)
                && ((IPlayerDamagedMixin) this).getDamaged()) {
            if (this.getActiveItem().isFood() || this.getActiveItem().getItem() instanceof PotionItem) {
                this.clearActiveItem();
            }
        }
    }
}
