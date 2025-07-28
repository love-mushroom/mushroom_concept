package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class ancient_tinker extends BattleModifier {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        float a = 0.2f;
        ToolStats.ARMOR.multiply(builder, 1.4);
        ToolStats.ATTACK_DAMAGE.multiply(builder, 1.4);
    }
}
