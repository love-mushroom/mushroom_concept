package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.Mushroom_Concept;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ZeroHand extends Modifier implements InventoryTickModifierHook {
    private static final ResourceLocation hasChange = Mushroom_Concept.getResource("zero_hand_change");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (holder instanceof Player player) {
            var data = tool.getPersistentData();
            boolean hasChanged = data.getBoolean(hasChange);
            if (isSelected && !hasChanged) {
                data.putBoolean(hasChange, true);
                player.getCooldowns().removeCooldown(tool.getItem());
            }
            if (!isSelected && hasChanged) {
                data.putBoolean(hasChange, false);
            }
        }
    }
}
