package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class Runriotarmor extends ArmorModifier {

    @Override
    public Component getDisplayName(int level) {
        return super.getDisplayName();
    }
    @Override
    public float TrueDamageamount(IToolStackView armor, int level, EquipmentContext context, EquipmentSlot slot, DamageSource source, float amount, boolean isDirectDamage, LivingEntity entity, LivingEntity enemy) {
        if (enemy  != null&& entity instanceof Player player) {
            if (amount>player.getMaxHealth()*0.3f){
                player.heal(player.getMaxHealth()*0.05f+1);
                OverslimeModifier overslime = TinkerModifiers.overslime.get();
                ModifierEntry entry = armor.getModifier(TinkerModifiers.overslime.getId());
                float a = 1-(overslime.getShieldCapacity(armor, entry) * 0.0005f*player.getSpeed());
                return amount*a;
            }
        }
        return amount;
    }
}
