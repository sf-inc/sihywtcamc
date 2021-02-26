package com.github.charlyb01.sihywtcamc.init.attributes;

import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.MiningToolAccessor;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class ShovelInit {
    private ShovelInit() {
    }

    private static final float weakDamage = 1;
    private static final float ironDamage = weakDamage + 1;
    private static final float diamondDamage = ironDamage + 1;
    private static final float netheriteDamage = diamondDamage + 1;

    private static final float shovelSpeed = -2;

    public static void init() {
        initShovel(Items.WOODEN_SHOVEL, weakDamage);
        initShovel(Items.STONE_SHOVEL, weakDamage);
        initShovel(Items.GOLDEN_SHOVEL, weakDamage);
        initShovel(Items.IRON_SHOVEL, ironDamage);
        initShovel(Items.DIAMOND_SHOVEL, diamondDamage);
        initShovel(Items.NETHERITE_SHOVEL, netheriteDamage);
    }

    private static void initShovel(Item item, float damage) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> shovelBuilder = ImmutableMultimap.builder();

        shovelBuilder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_DAMAGE_MODIFIER_ID(),
                "Tool modifier", damage, EntityAttributeModifier.Operation.ADDITION));
        shovelBuilder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_SPEED_MODIFIER_ID(),
                "Tool modifier", shovelSpeed, EntityAttributeModifier.Operation.ADDITION));

        ((MiningToolAccessor) item).setAttackDamage(damage);
        ((MiningToolAccessor) item).setAttributeModifiers(shovelBuilder.build());
    }


}
