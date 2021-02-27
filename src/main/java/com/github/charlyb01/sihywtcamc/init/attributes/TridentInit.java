package com.github.charlyb01.sihywtcamc.init.attributes;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.mixin.ItemAccessor;
import com.github.charlyb01.sihywtcamc.mixin.attributes.TridentAccessor;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Items;

public class TridentInit {
    private TridentInit() {
    }

    private static final float tridentDamage = 6;
    private static final float tridentSpeed = -2;
    private static final float tridentReach = 1;

    public static void init() {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> tridentBuilder = ImmutableMultimap.builder();

        tridentBuilder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(((ItemAccessor) Items.TRIDENT).getATTACK_DAMAGE_MODIFIER_ID(),
                "Weapon modifier", tridentDamage, EntityAttributeModifier.Operation.ADDITION));
        tridentBuilder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(((ItemAccessor) Items.TRIDENT).getATTACK_SPEED_MODIFIER_ID(),
                "Weapon modifier", tridentSpeed, EntityAttributeModifier.Operation.ADDITION));

        if (ModConfig.get().toolsConfig.reachAttribute) {
            tridentBuilder.put(ReachAttribute.GENERIC_ATTACK_REACH, new EntityAttributeModifier(ReachAttribute.ATTACK_REACH_MODIFIER_ID,
                    "Weapon modifier", tridentReach, EntityAttributeModifier.Operation.ADDITION));
        }

        ((TridentAccessor) Items.TRIDENT).setAttributeModifiers(tridentBuilder.build());
    }
}
