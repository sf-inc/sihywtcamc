package com.github.charlyb01.sihywtcamc.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "tools")
public class ToolsConfig implements ConfigData {
    public boolean swordSweepingEdge = true;

    public boolean shieldEnchantable = true;
    public boolean shieldThorns = true;

    public boolean shieldCooldown = true;
    public boolean axeCooldown = true;

    public boolean bowLessPower = true;
    public boolean bowPowerExclusive = true;

    public boolean crossbowMultishotInclusive = true;
}
