package com.github.charlyb01.sihywtcamc;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.init.AttributesInit;
import com.github.charlyb01.sihywtcamc.init.StacksInit;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.Registry;

public class Sihywtcamc implements ModInitializer {
    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));

        RegistryEntryAddedCallback.event(Registry.ITEM).register((rawId, id, object) -> AttributesInit.initItem(id, object));
        AttributesInit.init();
        StacksInit.init();
    }
}
