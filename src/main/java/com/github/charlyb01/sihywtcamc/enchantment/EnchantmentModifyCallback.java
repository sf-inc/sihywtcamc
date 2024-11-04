package com.github.charlyb01.sihywtcamc.enchantment;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.tag.EntityTypeTags;

public class EnchantmentModifyCallback {
    public static void init() {
        EnchantmentEvents.MODIFY.register((key, builder, source) -> {
            if (!source.isBuiltin()) return;

            if (ModConfig.get().toolsConfig.bowLessPower && key == Enchantments.POWER) {
                builder.addEffect(
                        EnchantmentEffectComponentTypes.DAMAGE,
                        new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.1F)),
                        EntityPropertiesLootCondition.builder(
                                LootContext.EntityTarget.DIRECT_ATTACKER,
                                EntityPredicate.Builder.create().type(EntityTypeTags.ARROWS).build())
                );
            }
        });
    }
}
