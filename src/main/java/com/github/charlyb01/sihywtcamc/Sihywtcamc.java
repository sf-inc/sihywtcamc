package com.github.charlyb01.sihywtcamc;

import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;

public class Sihywtcamc implements ModInitializer {
    @Override
    public void onInitialize() {
        ((ItemAccessor) Items.POTION).setMaxCount(16);
        ((ItemAccessor) Items.MILK_BUCKET).setMaxCount(16);
        ((ItemAccessor) Items.SNOWBALL).setMaxCount(64);
    }
}
