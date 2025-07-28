package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.slotless.OverslimeModifier;

public class kira extends BattleModifier {
    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        // update 1 times a second, but skip when active (messes with pulling bow back)
        if (!world.isClientSide && holder.tickCount % 20 == 0 && holder.getUseItem() != stack) {
            OverslimeModifier overslime = TinkerModifiers.overslime.get();
            ModifierEntry entry = tool.getModifier(TinkerModifiers.overslime.getId());
            float b = tool.getStats().getInt(OverslimeModifier.OVERSLIME_STAT) * 0.03f;
            overslime.addOverslime(tool, entry, (int) b);
        }
    }
}
