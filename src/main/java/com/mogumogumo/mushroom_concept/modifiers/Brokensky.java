package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

import java.util.Collection;

public class Brokensky extends ArmorModifier {
    public boolean havenolevel() {
        return true;
    }

    public void MobEffectEvent(MobEffectEvent.Applicable event) {
        if (event.getEntity() != null && event.getEntity() instanceof LivingEntity) {
            if (ModifierLevel.EquipHasModifierlevel(event.getEntity(), this.getId())) {
                if (!event.getEffectInstance().getEffect().isBeneficial()) {
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }

    public float TrueDamageamount(IToolStackView armor, int level, EquipmentContext context, EquipmentSlot slot, DamageSource source, float amount, boolean isDirectDamage, LivingEntity entity, LivingEntity enemy) {
        entity.getCapability(TinkerDataCapability.CAPABILITY).ifPresent((holder) -> {
            Collection<MobEffectInstance> listEffect;
            listEffect = entity.getActiveEffects();
            if (!listEffect.isEmpty() && !(listEffect instanceof NoMilkEffect)) {
                for (int i = 0; i < listEffect.size(); i++) {
                    MobEffectInstance effect = listEffect.stream().toList().get(i);
                    if (effect != null) {
                        MobEffect ei = effect.getEffect();
                        if (ei.getCategory() == MobEffectCategory.BENEFICIAL) {
                            entity.removeEffect(ei);
                            entity.getCommandSenderWorld().addParticle(
                                    ParticleTypes.HAPPY_VILLAGER,
                                    entity.getX() + RANDOM.nextDouble() - 0.5,
                                    entity.getY() + 1,
                                    entity.getZ() + RANDOM.nextDouble() - 0.5,
                                    0, 0, 0);
                        }
                    }
                }
            }

        });
        return amount;
    }
}
