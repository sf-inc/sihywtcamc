package com.github.charlyb01.sihywtcamc.item;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.config.ToolsConfig;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class AttributeModifierCallback {
    public static final Identifier BASE_ATTACK_RANGE_MODIFIER_ID = Identifier.ofVanilla("base_attack_range");

    private static final double DEFAULT_ATTACK_DAMAGE = 1.0; // GENERIC_ATTACK_DAMAGE base value is changed for players!
    private static final double DEFAULT_ATTACK_SPEED = EntityAttributes.GENERIC_ATTACK_SPEED.value().getDefaultValue();
    private static final double DEFAULT_ATTACK_RANGE = EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE.value().getDefaultValue();

    private AttributeModifierCallback() {
    }

    public static void init() {
        if (!ModConfig.get().toolsConfig.newAttributesValues) return;

        DefaultItemComponentEvents.MODIFY.register((context -> context.modify(
                item -> {
                    Optional<RegistryKey<Item>> optionalItem = Registries.ITEM.getEntry(item).getKey();
                    return optionalItem.filter(itemRegistryKey -> ModConfig.get().toolsConfig.modifiedTools.stream()
                                    .anyMatch(toolModifier -> toolModifier.identifiers.contains(itemRegistryKey.getValue().toString())))
                            .isPresent();
                },
                (builder, item) -> {
                    Optional<RegistryKey<Item>> optionalItem = Registries.ITEM.getEntry(item).getKey();
                    if (optionalItem.isEmpty()) return;

                    Optional<ToolsConfig.ToolsModifier> optionalToolsModifier = ModConfig.get().toolsConfig.modifiedTools.stream()
                            .filter(toolModifier -> toolModifier.identifiers.contains(optionalItem.get().getValue().toString()))
                            .findFirst();
                    if (optionalToolsModifier.isEmpty()) return;

                    builder.add(
                            DataComponentTypes.ATTRIBUTE_MODIFIERS,
                            createAttributeModifiers(
                                    optionalToolsModifier.get().attackDamage - DEFAULT_ATTACK_DAMAGE,
                                    optionalToolsModifier.get().attackSpeed - DEFAULT_ATTACK_SPEED,
                                    optionalToolsModifier.get().attackReach - DEFAULT_ATTACK_RANGE
                            ));
                })));
    }

    public static AttributeModifiersComponent createAttributeModifiers(double attackDamage, double attackSpeed,
                                                                       double attackRange) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                attackDamage,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_RANGE_MODIFIER_ID,
                                attackRange,
                                EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }
}
