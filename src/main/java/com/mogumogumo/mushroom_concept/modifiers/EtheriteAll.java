package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.MushModifier;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;

public class EtheriteAll extends MushModifier {

    @Override
    protected void registerHooks(ModuleHookMap.Builder builder) {
        super.registerHooks(builder);
        builder.addHook(this, ModifierHooks.TOOL_DAMAGE, ModifierHooks.INVENTORY_TICK);
    }

    @Override
    public void modifierOnInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (world.getGameTime() % 10 == 0 && holder instanceof Player player) {
            ToolDamageUtil.repair(tool, 1);
            if (ModifierLevel.getTotalArmorModifierlevel(player, this.getId()) > 0) {
                player.heal(01f);
            }
        }
    }

    @Override
    public int onDamageTool(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i, @Nullable LivingEntity livingEntity) {
        return 0;
    }

    public void modifierOnEquip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        if (modifier.getLevel() > 0 && context.getEntity() instanceof Player player) {
            player.getAbilities().flying = true;
            player.getAbilities().mayfly = true;
        }
    }

    public void modifierOnUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        if (modifier.getLevel() > 0 && context.getEntity() instanceof Player player && !player.isCreative()) {
            player.getAbilities().flying = false;
            player.getAbilities().mayfly = false;
        }
    }
}
