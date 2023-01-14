package com.github.charlyb01.sihywtcamc.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

@Mixin(Item.class)
public interface ItemAccessor {
    @Accessor
    static UUID getATTACK_DAMAGE_MODIFIER_ID() {
        throw new AssertionError();
    }

    @Accessor
    static UUID getATTACK_SPEED_MODIFIER_ID() {
        throw new AssertionError();
    }

    @Accessor
    @Mutable
    void setMaxCount(int newCount);
}
