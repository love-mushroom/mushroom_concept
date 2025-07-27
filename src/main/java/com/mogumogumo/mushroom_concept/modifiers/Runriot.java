package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.mushroom_concept;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

import java.util.List;

public class Runriot extends BattleModifier {
    private static final Component Resistance = mushroom_concept.makeTranslation("modifier", "run_riot.resistance");

    @Override
    public Component getDisplayName(int level) {
        return super.getDisplayName();
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,  ModifierHooks.TOOLTIP);
    }
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        OverslimeModifier overslime = TinkerModifiers.overslime.get();
        ModifierEntry entry = tool.getModifier(TinkerModifiers.overslime.getId());
        float a = overslime.getShieldCapacity(tool, entry) * 0.01f * attacker.getSpeed()+1;
        return damage * a;
    }
    @Override
    public void arrowhurt(ModifierNBT modifiers, int level, Projectile projectile, EntityHitResult hit, AbstractArrow arrow, LivingEntity attacker, LivingEntity target) {
        if (target != null) {
            IToolStackView hand = ToolStack.from(attacker.getItemBySlot(EquipmentSlot.MAINHAND));
            OverslimeModifier overslime = TinkerModifiers.overslime.get();
            ModifierEntry entry = hand.getModifier(TinkerModifiers.overslime.getId());
            float a = overslime.getShieldCapacity(hand, entry) * 0.01f * attacker.getSpeed()+1;
            arrow.setBaseDamage(arrow.getBaseDamage() * a);
        }
    }
    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        Level world;
        if(player!=null){
            OverslimeModifier overslime = TinkerModifiers.overslime.get();
            ModifierEntry entry = tool.getModifier(TinkerModifiers.overslime.getId());
            float boost = player.getSpeed() * 0.01f * overslime.getShieldCapacity(tool, entry);
            TooltipModifierHook.addFlatBoost(this,Resistance , boost, tooltip);;
        }
    }
}
