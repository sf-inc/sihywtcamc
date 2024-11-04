package com.github.charlyb01.sihywtcamc;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.enchantment.EnchantmentModifyCallback;
import com.github.charlyb01.sihywtcamc.item.AttributeModifierCallback;
import com.github.charlyb01.sihywtcamc.item.MaxStackSizeCallback;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

public class Sihywtcamc implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));

        AttributeModifierCallback.init();
        MaxStackSizeCallback.init();
        EnchantmentModifyCallback.init();
    }
}
