package com.github.charlyb01.sihywtcamc.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.Arrays;
import java.util.List;

@Config(name = "tools")
public class ToolsConfig implements ConfigData {
    @ConfigEntry.Gui.RequiresRestart
    @ConfigEntry.Gui.Tooltip
    public boolean newAttributesValues = true;
    @ConfigEntry.Gui.RequiresRestart
    public boolean reachAttribute = true;

    public boolean swordSweepingEdge = true;

    @ConfigEntry.Gui.Tooltip
    public boolean shieldReduceArc = true;
    public boolean shieldReduceProtection = true;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
    public int shieldDamageProtection = 5;
    public boolean shieldEnchantable = true;
    public boolean shieldThorns = true;

    public boolean shieldCooldown = true;
    public boolean axeCooldown = true;

    public boolean bowLessPower = true;
    @ConfigEntry.Gui.Tooltip()
    public boolean bowPowerExclusive = true;

    @ConfigEntry.Gui.Tooltip()
    public boolean crossbowMultishotInclusive = true;

    public boolean flintFireEntities = true;

    public static class ToolsModifier {
        public String identifier;
        public float attackDamage;
        public float attackSpeed;
        public float attackReach;

        ToolsModifier() {
            this("mymod:bad_sword", 2, 2, 2.5F);
        }
        ToolsModifier(final String identifier, final float attackDamage, final float attackSpeed, final float attackReach) {
            this.identifier = identifier;
            this.attackDamage = attackDamage;
            this.attackSpeed = attackSpeed;
            this.attackReach = attackReach;
        }
    }

    @ConfigEntry.Gui.Excluded
    public List<ToolsModifier> modifiedTools = Arrays.asList(
            new ToolsModifier("minecraft:wooden_axe", 5, 2, 2.5F),
            new ToolsModifier("minecraft:stone_axe", 5, 2, 2.5F),
            new ToolsModifier("minecraft:golden_axe", 5, 2, 2.5F),
            new ToolsModifier("minecraft:iron_axe", 6, 2, 2.5F),
            new ToolsModifier("minecraft:diamond_axe", 7, 2, 2.5F),
            new ToolsModifier("minecraft:netherite_axe", 8, 2, 2.5F),

            new ToolsModifier("minecraft:wooden_hoe", 2, 2, 3.5F),
            new ToolsModifier("minecraft:stone_hoe", 2, 2.5F, 3.5F),
            new ToolsModifier("minecraft:golden_hoe", 2, 3.5F, 3.5F),
            new ToolsModifier("minecraft:iron_hoe", 3, 3, 3.5F),
            new ToolsModifier("minecraft:diamond_hoe", 3, 3.5F, 3.5F),
            new ToolsModifier("minecraft:netherite_hoe", 4, 3.5F, 3.5F),

            new ToolsModifier("minecraft:wooden_pickaxe", 3, 2.5F, 2.5F),
            new ToolsModifier("minecraft:stone_pickaxe", 3, 2.5F, 2.5F),
            new ToolsModifier("minecraft:golden_pickaxe", 3, 2.5F, 2.5F),
            new ToolsModifier("minecraft:iron_pickaxe", 4, 2.5F, 2.5F),
            new ToolsModifier("minecraft:diamond_pickaxe", 5, 2.5F, 2.5F),
            new ToolsModifier("minecraft:netherite_pickaxe", 6, 2.5F, 2.5F),

            new ToolsModifier("minecraft:wooden_shovel", 2, 2, 2.5F),
            new ToolsModifier("minecraft:stone_shovel", 2, 2, 2.5F),
            new ToolsModifier("minecraft:golden_shovel", 2, 2, 2.5F),
            new ToolsModifier("minecraft:iron_shovel", 3, 2, 2.5F),
            new ToolsModifier("minecraft:diamond_shovel", 4, 2, 2.5F),
            new ToolsModifier("minecraft:netherite_shovel", 5, 2, 2.5F),

            new ToolsModifier("minecraft:wooden_sword", 4, 3, 3),
            new ToolsModifier("minecraft:stone_sword", 4, 3, 3),
            new ToolsModifier("minecraft:golden_sword", 4, 3, 3),
            new ToolsModifier("minecraft:iron_sword", 5, 3, 3),
            new ToolsModifier("minecraft:diamond_sword", 6, 3, 3),
            new ToolsModifier("minecraft:netherite_sword", 7, 3, 3),

            new ToolsModifier("minecraft:trident", 7, 2, 3.5F)
            );
}
