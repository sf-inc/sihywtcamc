package com.github.charlyb01.sihywtcamc.init.attributes;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.SwordAccessor;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class SwordInit {
    private SwordInit() {
    }

    private static final float weakDamage = 3;
    private static final float ironDamage = weakDamage + 1;
    private static final float diamondDamage = ironDamage + 1;
    private static final float netheriteDamage = diamondDamage + 1;

    private static final float swordSpeed = -1;

    private static final float swordReach = 0.5F;

    public static void init() {
        initSword(Items.WOODEN_SWORD, weakDamage);
        initSword(Items.STONE_SWORD, weakDamage);
        initSword(Items.GOLDEN_SWORD, weakDamage);
        initSword(Items.IRON_SWORD, ironDamage);
        initSword(Items.DIAMOND_SWORD, diamondDamage);
        initSword(Items.NETHERITE_SWORD, netheriteDamage);
    }

    private static void initSword(Item item, float damage) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> swordBuilder = ImmutableMultimap.builder();

        swordBuilder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_DAMAGE_MODIFIER_ID(),
                "Weapon modifier", damage, EntityAttributeModifier.Operation.ADDITION));
        swordBuilder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_SPEED_MODIFIER_ID(),
                "Weapon modifier", swordSpeed, EntityAttributeModifier.Operation.ADDITION));

        if (ModConfig.get().toolsConfig.reachAttribute) {
            swordBuilder.put(ReachAttribute.GENERIC_ATTACK_REACH, new EntityAttributeModifier(ReachAttribute.ATTACK_REACH_MODIFIER_ID,
                    "Weapon modifier", swordReach, EntityAttributeModifier.Operation.ADDITION));
        }

        ((SwordAccessor) item).setAttackDamage(damage);
        ((SwordAccessor) item).setAttributeModifiers(swordBuilder.build());
    }
}
