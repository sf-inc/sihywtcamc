package com.github.charlyb01.sihywtcamc.init;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import net.minecraft.item.Items;

public class StacksInit {
    private StacksInit() {
    }

    public static void init() {
        if (ModConfig.get().generalConfig.stacks.potionStackable) {
            ((ItemAccessor) Items.POTION).setMaxCount(16);
        }
        if (ModConfig.get().generalConfig.stacks.milkBucketStackable) {
            ((ItemAccessor) Items.MILK_BUCKET).setMaxCount(16);
        }
        if (ModConfig.get().generalConfig.stacks.snowBallVeryStackable) {
            ((ItemAccessor) Items.SNOWBALL).setMaxCount(64);
        }
    }
}
