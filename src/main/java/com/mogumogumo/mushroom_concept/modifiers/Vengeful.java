package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Vengeful extends ArmorModifier {
    public float TrueDamageamount(IToolStackView armor, int level, EquipmentContext context, EquipmentSlot slot, DamageSource source, float amount, boolean isDirectDamage, LivingEntity entity, LivingEntity enemy) {
        if (enemy != null) {
            enemy.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * level, level - 1));
            enemy.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * level, level - 1));
            enemy.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * level, level - 1));
            enemy.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * level, level - 1));
        }
        return amount;
    }
}
