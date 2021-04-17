package com.github.charlyb01.sihywtcamc;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.init.AttributesInit;
import com.github.charlyb01.sihywtcamc.init.StacksInit;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
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
