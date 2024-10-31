package com.github.charlyb01.sihywtcamc.mixin;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public class PlayerKnockbackMixin {
    @Definition(id = "amount", local = @Local(ordinal = 0, type = float.class))
    @Expression("amount == 0.0")
    @WrapOperation(method = "damage", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean alwaysCallSuper(float left, float right, Operation<Boolean> original) {
        return !ModConfig.get().generalConfig.eggSnowball.knockbackPlayer
                && original.call(left, right);
    }
}
