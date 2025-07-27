package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.ToolDataNBT;

public class Grandeur extends BattleModifier {

    @Override
    public void addVolatileData(IToolContext context, ModifierEntry modifier, ToolDataNBT toolDataNBT) {
        int level = modifier.getLevel();
        toolDataNBT.addSlots(SlotType.UPGRADE, level);
    }
}
