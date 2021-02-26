package com.github.charlyb01.sihywtcamc.init.attributes;

import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.MiningToolAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.SwordAccessor;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class PickaxeInit {
    private PickaxeInit() {
    }

    private static final float weakDamage = 2;
    private static final float ironDamage = weakDamage + 1;
    private static final float diamondDamage = ironDamage + 1;
    private static final float netheriteDamage = diamondDamage + 1;

    private static final float pickaxeSpeed = -1.5F;

    public static void init() {
        initPickaxe(Items.WOODEN_PICKAXE, weakDamage);
        initPickaxe(Items.STONE_PICKAXE, weakDamage);
        initPickaxe(Items.GOLDEN_PICKAXE, weakDamage);
        initPickaxe(Items.IRON_PICKAXE, ironDamage);
        initPickaxe(Items.DIAMOND_PICKAXE, diamondDamage);
        initPickaxe(Items.NETHERITE_PICKAXE, netheriteDamage);
    }

    private static void initPickaxe(Item item, float damage) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> pickaxeBuilder = ImmutableMultimap.builder();

        pickaxeBuilder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_DAMAGE_MODIFIER_ID(),
                "Tool modifier", damage, EntityAttributeModifier.Operation.ADDITION));
        pickaxeBuilder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(((ItemAccessor) item).getATTACK_SPEED_MODIFIER_ID(),
                "Tool modifier", pickaxeSpeed, EntityAttributeModifier.Operation.ADDITION));

        ((MiningToolAccessor) item).setAttackDamage(damage);
        ((MiningToolAccessor) item).setAttributeModifiers(pickaxeBuilder.build());
    }
}
