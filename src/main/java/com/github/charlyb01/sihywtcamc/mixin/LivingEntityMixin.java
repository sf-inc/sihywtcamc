package com.github.charlyb01.sihywtcamc.mixin;

import com.github.charlyb01.sihywtcamc.config.ModConfig;
import com.google.common.collect.Multimap;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", ordinal = 1))
    private void reduceInvulnerability(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.get().generalConfig.appropriateInvulnerability && source.getAttacker() instanceof LivingEntity entity) {
            ItemStack itemStack = entity.getMainHandStack();
            Item item = itemStack.getItem();
            Multimap<EntityAttribute, EntityAttributeModifier> multimap = itemStack.getAttributeModifiers(EquipmentSlot.MAINHAND);
            if (!multimap.isEmpty()) {
                for (Map.Entry<EntityAttribute, EntityAttributeModifier> entry : multimap.entries()) {
                    EntityAttributeModifier entityAttributeModifier = entry.getValue();
                    if (entityAttributeModifier.getId() == ((ItemAccessor) item).getATTACK_SPEED_MODIFIER_ID()) {
                        this.timeUntilRegen = (int) (50.0D / (entityAttributeModifier.getValue()
                                + EntityAttributes.GENERIC_ATTACK_SPEED.getDefaultValue()));
                        this.timeUntilRegen = Math.min(this.timeUntilRegen, 20);
                    }
                }
            }
        }
    }
}
