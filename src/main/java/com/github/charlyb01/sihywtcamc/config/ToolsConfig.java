package com.github.charlyb01.sihywtcamc.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.Arrays;
import java.util.List;

@Config(name = "tools")
public class ToolsConfig implements ConfigData {
    @ConfigEntry.Gui.RequiresRestart
    public boolean newAttributesValues = true;

    public boolean swordSweepingEdge = true;

    @ConfigEntry.Gui.Tooltip
    public boolean shieldReduceArc = true;
    public boolean shieldReduceProtection = true;
    @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
    public int shieldDamageProtection = 5;
    public boolean shieldInstantBlock = true;
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
        public List<String> identifiers;
        public double attackDamage;
        public double attackSpeed;
        public double attackReach;

        ToolsModifier() {
            this(List.of("example_mod:example_sword"), 2, 2, 2.5);
        }
        ToolsModifier(List<String> identifiers, double attackDamage, double attackSpeed, double attackReach) {
            this.identifiers = identifiers;
            this.attackDamage = attackDamage;
            this.attackSpeed = attackSpeed;
            this.attackReach = attackReach;
        }
    }

    public List<ToolsModifier> modifiedTools = Arrays.asList(

            new ToolsModifier(List.of(
                    "minecraft:wooden_axe",
                    "minecraft:stone_axe",
                    "minecraft:golden_axe",
                    "minecraft:netherite_shovel") , 5, 2, 2.5),
            new ToolsModifier(List.of("minecraft:iron_axe"), 6, 2, 2.5),
            new ToolsModifier(List.of("minecraft:diamond_axe"), 7, 2, 2.5),
            new ToolsModifier(List.of("minecraft:netherite_axe"), 8, 2, 2.5),

            new ToolsModifier(List.of(
                    "minecraft:wooden_pickaxe",
                    "minecraft:stone_pickaxe",
                    "minecraft:golden_pickaxe"), 3, 2.5, 2.5),
            new ToolsModifier(List.of("minecraft:iron_pickaxe"), 4, 2.5, 2.5),
            new ToolsModifier(List.of("minecraft:diamond_pickaxe"), 5, 2.5, 2.5),
            new ToolsModifier(List.of("minecraft:netherite_pickaxe"), 6, 2.5, 2.5),

            new ToolsModifier(List.of(
                    "minecraft:wooden_shovel",
                    "minecraft:stone_shovel",
                    "minecraft:golden_shovel"), 2, 2, 2.5),
            new ToolsModifier(List.of("minecraft:iron_shovel"), 3, 2, 2.5),
            new ToolsModifier(List.of("minecraft:diamond_shovel"), 4, 2, 2.5),

            new ToolsModifier(List.of(
                    "minecraft:wooden_sword",
                    "minecraft:stone_sword",
                    "minecraft:golden_sword"), 4, 3, 3),
            new ToolsModifier(List.of("minecraft:iron_sword"), 5, 3, 3),
            new ToolsModifier(List.of("minecraft:diamond_sword"), 6, 3, 3),
            new ToolsModifier(List.of("minecraft:netherite_sword"), 7, 3, 3),

            new ToolsModifier(List.of("minecraft:wooden_hoe"), 2, 2, 3.5),
            new ToolsModifier(List.of("minecraft:stone_hoe"), 2, 2.5, 3.5),
            new ToolsModifier(List.of("minecraft:golden_hoe"), 2, 3.5, 3.5),
            new ToolsModifier(List.of("minecraft:iron_hoe"), 3, 3, 3.5),
            new ToolsModifier(List.of("minecraft:diamond_hoe"), 3, 3.5, 3.5),
            new ToolsModifier(List.of("minecraft:netherite_hoe"), 4, 3.5, 3.5),

            new ToolsModifier(List.of("minecraft:trident"), 7, 2, 3.5)
    );
}
