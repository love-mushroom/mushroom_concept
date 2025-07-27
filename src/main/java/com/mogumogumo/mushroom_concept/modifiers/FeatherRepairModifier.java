package com.mogumogumo.mushroom_concept.modifiers;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class FeatherRepairModifier extends Modifier implements InventoryTickModifierHook {

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int level, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (tool.isBroken() && !world.isClientSide) {
            Player player = (Player) holder;
            Inventory inventory = player.getInventory();
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack itemStack = inventory.getItem(i);
                if (itemStack.getItem() == Items.FEATHER) {
                    if (itemStack.getCount() >= stack.getMaxDamage()) {
                        stack.setDamageValue(0);
                        itemStack.shrink(stack.getMaxDamage() / level);
                    } else {
                        stack.setDamageValue(stack.getDamageValue() - itemStack.getCount());
                        itemStack.shrink(itemStack.getCount() / level);
                    }
                }
            }
        }
    }
}
