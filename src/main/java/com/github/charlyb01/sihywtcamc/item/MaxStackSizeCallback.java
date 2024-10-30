package com.github.charlyb01.sihywtcamc.item;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;

public class MaxStackSizeCallback {
    private MaxStackSizeCallback() {
    }

    public static void init() {
        DefaultItemComponentEvents.MODIFY.register((context -> context.modify(
                Items.POTION,
                builder -> builder.add(
                        DataComponentTypes.MAX_STACK_SIZE,
                        ModConfig.get().generalConfig.stacks.potionStack)
                )));

        DefaultItemComponentEvents.MODIFY.register((context -> context.modify(
                Items.SPLASH_POTION,
                builder -> builder.add(
                        DataComponentTypes.MAX_STACK_SIZE,
                        ModConfig.get().generalConfig.stacks.splashPotionStack)
        )));

        DefaultItemComponentEvents.MODIFY.register((context -> context.modify(
                Items.LINGERING_POTION,
                builder -> builder.add(
                        DataComponentTypes.MAX_STACK_SIZE,
                        ModConfig.get().generalConfig.stacks.lingeringPotionStack)
        )));

        DefaultItemComponentEvents.MODIFY.register((context -> context.modify(
                Items.MILK_BUCKET,
                builder -> builder.add(
                        DataComponentTypes.MAX_STACK_SIZE,
                        ModConfig.get().generalConfig.stacks.milkBucketStack)
        )));

        DefaultItemComponentEvents.MODIFY.register((context -> context.modify(
                Items.SNOWBALL,
                builder -> builder.add(
                        DataComponentTypes.MAX_STACK_SIZE,
                        ModConfig.get().generalConfig.stacks.snowBallStack)
        )));
    }
}
