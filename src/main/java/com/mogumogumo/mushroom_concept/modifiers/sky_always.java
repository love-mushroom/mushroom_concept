package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class sky_always extends BattleModifier {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        float a = builder.getStat(OverslimeModifier.OVERSLIME_STAT) / 50;
        ToolStats.DRAW_SPEED.add(builder, 1 + a * modifier.getLevel());
        ToolStats.MINING_SPEED.add(builder, 1 + a * modifier.getLevel());
        ToolStats.ATTACK_SPEED.add(builder, 1 + a * modifier.getLevel());
        ToolStats.ATTACK_DAMAGE.add(builder, 1 + a * modifier.getLevel());
        ToolStats.VELOCITY.add(builder, 1 + a * modifier.getLevel());
        ToolStats.ACCURACY.add(builder, 1 + a * modifier.getLevel());
        ToolStats.DURABILITY.add(builder, 1 + a * modifier.getLevel());
        ToolStats.PROJECTILE_DAMAGE.add(builder, 1 + a * modifier.getLevel());
        ToolStats.ARMOR.add(builder, 1 + a * modifier.getLevel());
        ToolStats.ARMOR_TOUGHNESS.add(builder, 1 + a * modifier.getLevel());
    }
}
