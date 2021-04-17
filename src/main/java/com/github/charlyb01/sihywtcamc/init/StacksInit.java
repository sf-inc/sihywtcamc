package com.github.charlyb01.sihywtcamc.init;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import net.minecraft.item.Items;

public class StacksInit {
    private StacksInit() {
    }

    public static void init() {
        ((ItemAccessor) Items.POTION).setMaxCount(ModConfig.get().generalConfig.stacks.potionStack);
        ((ItemAccessor) Items.MILK_BUCKET).setMaxCount(ModConfig.get().generalConfig.stacks.milkBucketStack);
        ((ItemAccessor) Items.SNOWBALL).setMaxCount(ModConfig.get().generalConfig.stacks.snowBallStack);
    }
}
