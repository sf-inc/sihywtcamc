package com.github.charlyb01.sihywtcamc.init;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.config.ToolsConfig;
import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.MiningToolAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.SwordAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.TridentAccessor;
import com.google.common.collect.ImmutableMultimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class AttributesInit {
    private AttributesInit() {
    }

    public static void initItem(final Identifier id, final Item item) {
        Optional<ToolsConfig.ToolsModifier> optional = ModConfig.get().toolsConfig.modifiedTools.stream()
                .filter(toolModifier -> toolModifier.identifier.equals(id.toString())).findAny();
        ToolsConfig.ToolsModifier toolsModifier = optional.orElse(null);
        if (toolsModifier == null) return;

        initModifiers(item, toolsModifier);
    }

    public static void init() {
        if (ModConfig.get().toolsConfig.newAttributesValues) {
            for (ToolsConfig.ToolsModifier toolsModifier: ModConfig.get().toolsConfig.modifiedTools) {
                Item item = Registry.ITEM.get(new Identifier(toolsModifier.identifier));
                initModifiers(item, toolsModifier);
            }
        }
    }

    private static void initModifiers(final Item item, final ToolsConfig.ToolsModifier toolsModifier) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> itemBuilder = ImmutableMultimap.builder();
        String modifier = item instanceof MiningToolItem ? "Tool modifier" : "Weapon modifier";
        float damage = toolsModifier.attackDamage - 1.0F;   // GENERIC_ATTACK_DAMAGE base value is changed for players!
        float speed = toolsModifier.attackSpeed  - (float) EntityAttributes.GENERIC_ATTACK_SPEED.getDefaultValue();
        float reach = toolsModifier.attackReach  - 3.0F;    // base value is not accessible through the API as it's additive!

        itemBuilder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_DAMAGE_MODIFIER_ID(),
                modifier, damage, EntityAttributeModifier.Operation.ADDITION));
        itemBuilder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_SPEED_MODIFIER_ID(),
                modifier, speed, EntityAttributeModifier.Operation.ADDITION));

        if (ModConfig.get().toolsConfig.reachAttribute && reach != 0) {
            itemBuilder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(
                    modifier, reach, EntityAttributeModifier.Operation.ADDITION));
        }

        if (item instanceof MiningToolItem) {
            ((MiningToolAccessor) item).setAttackDamage(damage);
            ((MiningToolAccessor) item).setAttributeModifiers(itemBuilder.build());
        } else if (item instanceof SwordItem) {
            ((SwordAccessor) item).setAttackDamage(damage);
            ((SwordAccessor) item).setAttributeModifiers(itemBuilder.build());
        } else if (item instanceof TridentItem) {
            ((TridentAccessor) item).setAttributeModifiers(itemBuilder.build());
        }
    }
}
