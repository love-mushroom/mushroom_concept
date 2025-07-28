package com.mogumogumo.mushroom_concept.modifiers;


import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class Yaoforging extends Modifier implements DamageBlockModifierHook, ToolStatsModifierHook, ModifyDamageModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS, ModifierHooks.DAMAGE_BLOCK, ModifierHooks.MODIFY_HURT);
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.DRAW_SPEED.add(builder, 6);
        ToolStats.MINING_SPEED.add(builder, 0.25);
        ToolStats.ATTACK_SPEED.add(builder, 0.25);
        ToolStats.ATTACK_DAMAGE.add(builder, 4);
        ToolStats.ATTACK_DAMAGE.multiply(builder, 1.25);
        ToolStats.ARMOR.add(builder, 3);
        ToolStats.ARMOR.multiply(builder, 1.25);
        ToolStats.ARMOR_TOUGHNESS.add(builder, 1);
        ToolStats.ARMOR_TOUGHNESS.multiply(builder, 1.25);
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        return amount < modifier.getLevel();
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        var modifierLevel = modifier.getLevel();
        if (amount > modifierLevel) {
            return (amount - modifierLevel) * (1 - 0.06f * modifierLevel);
        }
        return amount;
    }
}
