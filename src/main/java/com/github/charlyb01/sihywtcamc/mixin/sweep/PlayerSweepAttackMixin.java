package com.github.charlyb01.sihywtcamc.mixin.sweep;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(PlayerEntity.class)
public abstract class PlayerSweepAttackMixin extends LivingEntity {
    protected PlayerSweepAttackMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @WrapOperation(method = "attack", constant = @Constant(classValue = SwordItem.class))
    private boolean sweepingIfEnchant(Object object, Operation<Boolean> original) {
        return original.call(object)
                && (!ModConfig.get().toolsConfig.swordSweepingEdge
                || this.getAttributeValue(EntityAttributes.PLAYER_SWEEPING_DAMAGE_RATIO) > 0.0);
    }
}
