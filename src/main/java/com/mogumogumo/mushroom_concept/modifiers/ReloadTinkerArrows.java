package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.library.item.tools.ModifiableArrowItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.function.Predicate;

public class ReloadTinkerArrows extends NoLevelsModifier implements BowAmmoModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.BOW_AMMO);
    }

    @Override
    public int getPriority() {
        return 59;
    }

    @Override
    public ItemStack findAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack standardAmmo, Predicate<ItemStack> ammoPredicate) {
        Player player = (Player) shooter;

        if (player.getOffhandItem().getItem() instanceof ModifiableArrowItem && !ToolStack.from(player.getOffhandItem()).isBroken()
                && tool.getModifierLevel(TinkerModifiers.multishot.getId()) > 0 ) {
            ItemStack stack = player.getOffhandItem().copy();
            stack.setCount(64);
            return stack;
        }

        else if (player.getMainHandItem().getItem() instanceof ModifiableArrowItem && !ToolStack.from(player.getMainHandItem()).isBroken()
                && tool.getModifierLevel(TinkerModifiers.multishot.getId()) > 0 ) {
            ItemStack stack = player.getMainHandItem().copy();
            stack.setCount(64);
            return stack;
        }

        else if (player.getOffhandItem().getItem() instanceof ModifiableArrowItem && !ToolStack.from(player.getOffhandItem()).isBroken()) {
            return player.getOffhandItem();
        }

        else if (player.getMainHandItem().getItem() instanceof ModifiableArrowItem && !ToolStack.from(player.getMainHandItem()).isBroken()) {
            return player.getMainHandItem();
        }

        else {
            Inventory inventory = player.getInventory();
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack itemstack = inventory.getItem(i);
                if (itemstack.getItem() instanceof ModifiableArrowItem && !ToolStack.from(itemstack).isBroken()) {
                    if (tool.getModifierLevel(TinkerModifiers.multishot.getId()) > 0) {
                        ItemStack stack = itemstack.copy();
                        stack.setCount(64);
                        return stack;
                    }

                    else {
                        return itemstack;
                    }
                }
            }
        }

        return ItemStack.EMPTY;
    }


    @Override
    public void shrinkAmmo(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, ItemStack ammo, int needed) {
        Player player = (Player) shooter;
        Inventory inventory = player.getInventory();
        if (ammo.getItem() instanceof ModifiableArrowItem) {
            if (ammo.getCount() > 1) {
                ItemStack stack = ammo.copy();
                stack.setCount(1);

                if (stack.getItem() == player.getOffhandItem().getItem() && !ToolStack.from(player.getOffhandItem()).isBroken()) {
                    IToolStackView tools = ToolStack.from(player.getOffhandItem());
                    ToolDamageUtil.damageAnimated(tools, needed, shooter);
                }

                else if (stack.getItem() == player.getMainHandItem().getItem() && !ToolStack.from(player.getMainHandItem()).isBroken()) {
                    IToolStackView tools = ToolStack.from(player.getMainHandItem());
                    ToolDamageUtil.damageAnimated(tools, needed, shooter);
                }

                else {
                    for (int i = 0; i < inventory.getContainerSize(); i++) {
                        ItemStack itemstack = inventory.getItem(i);
                        if (stack.getItem() == itemstack.getItem() && !ToolStack.from(itemstack).isBroken()) {
                            IToolStackView tools = ToolStack.from(itemstack);
                            ToolDamageUtil.damageAnimated(tools, needed, shooter);
                            break;
                        }
                    }
                }
            }

            else {
                IToolStackView tools = ToolStack.from(ammo);
                ToolDamageUtil.damageAnimated(tools, needed, shooter);
            }
        }
    }
}
