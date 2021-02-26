package com.github.charlyb01.sihywtcamc.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "general")
public class GeneralConfig implements ConfigData {
    public boolean eatDrinkCancellable = true;
    public boolean drinkFaster = true;

    @ConfigEntry.Gui.CollapsibleObject
    public Stacks stacks = new Stacks();

    public static class Stacks {
        public boolean potionStackable = true;
        public boolean milkBucketStackable = true;
        public boolean snowBallVeryStackable = true;
    }

    @ConfigEntry.Gui.CollapsibleObject
    public EggSnowball eggSnowball = new EggSnowball();

    public static class EggSnowball {
        public boolean cooldown = true;
        public boolean knockbackPlayer = true;
        public boolean shieldStopKnockack = true;
    }
}
