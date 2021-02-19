package com.github.charlyb01.sihywtcamc.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerAxeMixin extends LivingEntity {
    @Shadow public abstract ItemCooldownManager getItemCooldownManager();

    protected PlayerAxeMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void axeDisablesShield(Entity target, CallbackInfo ci) {
        if (this.getMainHandStack().getItem() instanceof AxeItem) {
            this.getItemCooldownManager().set(Items.SHIELD, 40);
        }
    }
}
