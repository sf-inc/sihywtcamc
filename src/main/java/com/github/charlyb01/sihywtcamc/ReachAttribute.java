package com.github.charlyb01.sihywtcamc;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class ReachAttribute {
    private ReachAttribute() {
    }

    public static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("26cb07a3-209d-4110-8e10-1010243614c8");

    public static final EntityAttribute GENERIC_ATTACK_REACH = Registry.register(Registry.ATTRIBUTE, "generic.attack_reach",
            new ClampedEntityAttribute("attribute.name.generic.attack_reach", 2.5D, 0.0D, 6.0D));
}
