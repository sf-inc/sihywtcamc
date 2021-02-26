package com.github.charlyb01.sihywtcamc.init.attributes;

import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.MiningToolAccessor;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class HoeInit {
    private HoeInit() {
    }

    private static final float weakDamage = 1;
    private static final float mediumDamage = weakDamage + 1;
    private static final float strongDamage = mediumDamage + 1;

    private static final float woodenSpeed = -2;
    private static final float stoneSpeed = woodenSpeed + 0.5F;
    private static final float ironSpeed = stoneSpeed + 0.5F;
    private static final float highSpeed = ironSpeed + 0.5F;

    public static void init() {
        initHoe(Items.WOODEN_HOE, weakDamage, woodenSpeed);
        initHoe(Items.STONE_HOE, weakDamage, stoneSpeed);
        initHoe(Items.GOLDEN_HOE, weakDamage, highSpeed);
        initHoe(Items.IRON_HOE, mediumDamage, ironSpeed);
        initHoe(Items.DIAMOND_HOE, mediumDamage, highSpeed);
        initHoe(Items.NETHERITE_HOE, strongDamage, highSpeed);
    }

    private static void initHoe(Item item, float damage, float speed) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> hoeBuilder = ImmutableMultimap.builder();

        hoeBuilder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_DAMAGE_MODIFIER_ID(),
                "Tool modifier", damage, EntityAttributeModifier.Operation.ADDITION));
        hoeBuilder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_SPEED_MODIFIER_ID(),
                "Tool modifier", speed, EntityAttributeModifier.Operation.ADDITION));

        ((MiningToolAccessor) item).setAttackDamage(damage);
        ((MiningToolAccessor) item).setAttributeModifiers(hoeBuilder.build());
    }
}
