package com.github.charlyb01.sihywtcamc.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "sihywtcamc")
public class ModConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.TransitiveObject
    public GeneralConfig generalConfig = new GeneralConfig();

    @ConfigEntry.Category("tools")
    @ConfigEntry.Gui.TransitiveObject
    public ToolsConfig toolsConfig = new ToolsConfig();

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
