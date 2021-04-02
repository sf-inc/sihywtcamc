package com.github.charlyb01.sihywtcamc.mixin.attributes;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.github.charlyb01.sihywtcamc.init.attributes.ReachAttribute;
import com.google.common.collect.Multimap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "updateTargetedEntity", at = @At("HEAD"), cancellable = true)
    private void changeTarget(float tickDelta, CallbackInfo ci) {
        if (ModConfig.get().toolsConfig.reachAttribute) {
            Entity entity = this.client.getCameraEntity();
            if (entity != null) {
                if (this.client.world != null) {
                    this.client.getProfiler().push("pick");
                    this.client.targetedEntity = null;
                    if (this.client.interactionManager == null) return;
                    double d = this.client.interactionManager.getReachDistance();
                    this.client.crosshairTarget = entity.raycast(d, tickDelta, false);
                    Vec3d vec3d = entity.getCameraPosVec(tickDelta);
                    boolean bl = false;
                    double e = d;
                    if (this.client.interactionManager.hasExtendedReach()) {
                        e = 6.0D;
                        d = e;
                    } else {
                        if (d > 3.0D) {
                            bl = true;
                        }
                    }

                    e *= e;
                    if (this.client.crosshairTarget != null) {
                        e = this.client.crosshairTarget.getPos().squaredDistanceTo(vec3d);
                    }

                    Vec3d vec3d2 = entity.getRotationVec(1.0F);
                    Vec3d vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
                    Box box = entity.getBoundingBox().stretch(vec3d2.multiply(d)).expand(1.0D, 1.0D, 1.0D);
                    EntityHitResult entityHitResult = ProjectileUtil.raycast(entity, vec3d, vec3d3, box, (entityx) -> !entityx.isSpectator() && entityx.collides(), e);
                    if (entityHitResult != null) {
                        Entity entity2 = entityHitResult.getEntity();
                        Vec3d vec3d4 = entityHitResult.getPos();
                        double g = vec3d.squaredDistanceTo(vec3d4);

                        double value = ReachAttribute.GENERIC_ATTACK_REACH.getDefaultValue();
                        Multimap<EntityAttribute, EntityAttributeModifier> multimap;
                        if (this.client.player != null) {
                            multimap = this.client.player.getMainHandStack().getAttributeModifiers(EquipmentSlot.MAINHAND);

                            if (!multimap.isEmpty()) {
                                for (Map.Entry<EntityAttribute, EntityAttributeModifier> entityAttributeEntityAttributeModifierEntry : multimap.entries()) {
                                    EntityAttributeModifier entityAttributeModifier = entityAttributeEntityAttributeModifierEntry.getValue();
                                    if (entityAttributeModifier.getId().equals(ReachAttribute.ATTACK_REACH_MODIFIER_ID)) {
                                        value += entityAttributeModifier.getValue();
                                    }
                                }
                            }
                        }

                        if (bl && g > value * value) {
                            this.client.crosshairTarget = BlockHitResult.createMissed(vec3d4, Direction.getFacing(vec3d2.x, vec3d2.y, vec3d2.z), new BlockPos(vec3d4));
                        } else if (g < e || this.client.crosshairTarget == null) {
                            this.client.crosshairTarget = entityHitResult;
                            if (entity2 instanceof LivingEntity || entity2 instanceof ItemFrameEntity) {
                                this.client.targetedEntity = entity2;
                            }
                        }
                    }

                    this.client.getProfiler().pop();
                }
            }
            ci.cancel();
        }
    }
}
