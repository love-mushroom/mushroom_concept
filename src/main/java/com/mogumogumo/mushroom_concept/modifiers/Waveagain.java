package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.interfaces.AboutArrow;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class Waveagain extends BattleModifier implements AboutArrow {
    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage){
        if (context.getLivingTarget()!=null) {
            if (context.getLivingTarget().getHealth()<=damage) {
                OverslimeModifier overslime = TinkerModifiers.overslime.get();
                ModifierEntry entry = tool.getModifier(TinkerModifiers.overslime.getId());
                float maxshield = tool.getStats().get(OverslimeModifier.OVERSLIME_STAT) * 0.1f;
                overslime.addOverslime(tool, entry, (int) maxshield);
                context.getAttacker().heal(context.getAttacker().getMaxHealth() * 0.1f);
                return baseDamage;
            }
        }
        return baseDamage ;
    }

    @Override
    public void arrowhurt(ModifierNBT modifiers, int level, Projectile projectile, EntityHitResult hit, AbstractArrow arrow, LivingEntity attacker, LivingEntity target) {
        if (target != null) {
            if (target.getHealth()<arrow.getBaseDamage()){
                IToolStackView hand = ToolStack.from(attacker.getItemBySlot(EquipmentSlot.MAINHAND));
                OverslimeModifier overslime = TinkerModifiers.overslime.get();
                ModifierEntry entry = hand.getModifier(TinkerModifiers.overslime.getId());
                float a = overslime.getShieldCapacity(hand, entry) * 0.1f;
                overslime.addOverslime(hand, entry, (int) a);
                attacker.heal(attacker.getMaxHealth()*0.1f);
            }
        }
    }
    @Override
    public @NotNull Component getDisplayName(int level) {
        return super.getDisplayName();
    }
}
