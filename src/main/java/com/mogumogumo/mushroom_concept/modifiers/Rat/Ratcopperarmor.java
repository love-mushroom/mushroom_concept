package com.mogumogumo.mushroom_concept.modifiers.Rat;

import com.github.alexthe666.rats.server.entity.rat.TamedRat;
import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Ratcopperarmor extends ArmorModifier  {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MODIFY_DAMAGE);
    }
    @Override
    public float modifyDamageTaken(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, EquipmentContext context, @NotNull EquipmentSlot slotType, @NotNull DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity entity = context.getEntity();
        if (entity instanceof Player player) {
            float a= (float) (1-(0.08*modifier.getLevel()));
            return amount * a ;
        }
        if (entity instanceof TamedRat tamedRat) {
            if (modifier.getLevel()<5) {
                float a = (float) (1 - (0.08 * modifier.getLevel()));
                return amount * a - 2 * modifier.getLevel();
            }
        }
        return amount;
    }
}
