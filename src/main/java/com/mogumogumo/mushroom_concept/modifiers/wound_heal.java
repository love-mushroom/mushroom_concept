package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class wound_heal extends ArmorModifier {
    @Override
    public float TrueDamageamount(IToolStackView armor, int level, EquipmentContext context, EquipmentSlot slot, DamageSource source, float amount, boolean isDirectDamage, LivingEntity entity, LivingEntity enemy) {
        if (enemy != null && entity instanceof Player player) {
            if (amount > player.getMaxHealth() * 0.35f) {
                player.heal(player.getMaxHealth() * 0.1f);
                return amount;
            }
        }
        return amount;
    }
}
