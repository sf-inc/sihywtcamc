package com.github.charlyb01.sihywtcamc.init.attributes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class ReachAttribute {
    private ReachAttribute() {
    }

    public static final UUID ATTACK_REACH_MODIFIER_ID = UUID.fromString("26cb07a3-209d-4110-8e10-1010243614c8");

    public static final EntityAttribute GENERIC_ATTACK_REACH = Registry.register(Registry.ATTRIBUTE, "generic.attack_reach",
            new ClampedEntityAttribute("attribute.name.generic.attack_reach", 2.5D, 0.0D, 6.0D));

    public static double reachDistance(final Entity attacking, final Entity attacked) {
        EntityDimensions attackingDimensions = attacking.getDimensions(attacking.getPose());
        EntityDimensions attackedDimensions = attacked.getDimensions(attacked.getPose());

        Vec3d attackedPos = new Vec3d(Math.abs(attacked.getX() - attacking.getX()),
                Math.abs(attacked.getEyeY() - attacking.getEyeY()),
                Math.abs(attacked.getZ() - attacking.getZ()));

        double offset = attackingDimensions.width / 2.0D + attackedDimensions.width / 2.0D;
        Vec3d normalized = attackedPos.normalize();
        Vec3d boxes = new Vec3d(-offset, 0.0D, -offset);

        normalized = normalized.multiply(boxes);
        attackedPos = attackedPos.add(normalized);

        return Math.sqrt(attackedPos.x*attackedPos.x + attackedPos.y*attackedPos.y + attackedPos.z*attackedPos.z);
    }
}
