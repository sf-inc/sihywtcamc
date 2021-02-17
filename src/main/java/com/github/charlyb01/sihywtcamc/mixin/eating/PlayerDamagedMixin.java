package com.github.charlyb01.sihywtcamc.mixin.eating;

import com.github.charlyb01.sihywtcamc.imixin.IPlayerDamagedMixin;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerDamagedMixin implements IPlayerDamagedMixin {
    @Shadow public abstract boolean isInvulnerableTo(DamageSource damageSource);

    @Unique
    private boolean sihywtcamc_isDamaged = false;

    @Override
    public boolean getDamaged() {
        boolean isDamaged = sihywtcamc_isDamaged;
        sihywtcamc_isDamaged = false;
        return isDamaged;
    }

    @Inject(at = @At("HEAD"), method = "applyDamage")
    private void setDamaged(DamageSource source, float amount, CallbackInfo ci) {
        if (!this.isInvulnerableTo(source)) {
            sihywtcamc_isDamaged = true;
        }
    }
}
