package com.github.charlyb01.sihywtcamc.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "general")
public class GeneralConfig implements ConfigData {
    public boolean eatDrinkCancellable = true;
    public boolean drinkFaster = true;

    @ConfigEntry.Gui.CollapsibleObject
    public Stacks stacks = new Stacks();

    public static class Stacks {
        @ConfigEntry.Gui.RequiresRestart
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int potionStack = 16;
        @ConfigEntry.Gui.RequiresRestart
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int milkBucketStack = 16;
        @ConfigEntry.Gui.RequiresRestart
        @ConfigEntry.BoundedDiscrete(min = 1, max = 64)
        public int snowBallStack = 64;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public EggSnowball eggSnowball = new EggSnowball();

    public static class EggSnowball {
        public boolean cooldown = true;
        public boolean knockbackPlayer = true;
        public boolean shieldStopKnockack = true;
    }
}
