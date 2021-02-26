package com.github.charlyb01.sihywtcamc.init.attributes;

import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.MiningToolAccessor;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class AxeInit {
    private AxeInit() {
    }

    private static final float weakDamage = 4;
    private static final float ironDamage = weakDamage + 1;
    private static final float diamondDamage = ironDamage + 1;
    private static final float netheriteDamage = diamondDamage + 1;

    private static final float axeSpeed = -2;

    public static void init() {
        initAxe(Items.WOODEN_AXE, weakDamage);
        initAxe(Items.STONE_AXE, weakDamage);
        initAxe(Items.GOLDEN_AXE, weakDamage);
        initAxe(Items.IRON_AXE, ironDamage);
        initAxe(Items.DIAMOND_AXE, diamondDamage);
        initAxe(Items.NETHERITE_AXE, netheriteDamage);
    }

    private static void initAxe(Item item, float damage) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> axeBuilder = ImmutableMultimap.builder();

        axeBuilder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_DAMAGE_MODIFIER_ID(),
                "Tool modifier", damage, EntityAttributeModifier.Operation.ADDITION));
        axeBuilder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_SPEED_MODIFIER_ID(),
                "Tool modifier", axeSpeed, EntityAttributeModifier.Operation.ADDITION));

        ((MiningToolAccessor) item).setAttackDamage(damage);
        ((MiningToolAccessor) item).setAttributeModifiers(axeBuilder.build());
    }
}
