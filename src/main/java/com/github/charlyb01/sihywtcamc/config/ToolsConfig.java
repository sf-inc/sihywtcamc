package com.github.charlyb01.sihywtcamc.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "tools")
public class ToolsConfig implements ConfigData {
    public boolean swordSweepingEdge = true;

    @ConfigEntry.Gui.Tooltip
    public boolean shieldReduceArc = true;
    public boolean shieldEnchantable = true;
    public boolean shieldThorns = true;

    public boolean shieldCooldown = true;
    public boolean axeCooldown = true;

    public boolean bowLessPower = true;
    @ConfigEntry.Gui.Tooltip()
    public boolean bowPowerExclusive = true;

    @ConfigEntry.Gui.Tooltip()
    public boolean crossbowMultishotInclusive = true;
}
