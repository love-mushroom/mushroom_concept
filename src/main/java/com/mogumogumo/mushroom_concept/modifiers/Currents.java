package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class Currents extends BattleModifier {

    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROCESS_LOOT);
    }

    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        int level = modifier.getLevel();
        int ran = RANDOM.nextInt(99);
        if (ran == 0 ) {
            generatedLoot.add(new ItemStack(Items.HEART_OF_THE_SEA));
        } else if (ran == 10 ) {
            generatedLoot.add(new ItemStack(Items.PRISMARINE_SHARD));
        } else if (ran <= 20 ) {
            generatedLoot.add(new ItemStack(Items.NAUTILUS_SHELL));
        } else if (ran <= 40 ) {
            generatedLoot.add(new ItemStack(Items.SCUTE));
        } else if (ran <= 60) {
            generatedLoot.add(new ItemStack(Items.TURTLE_EGG));
        } else if (ran <= 90) {
            generatedLoot.add(new ItemStack(Items.SALMON));
        }

    }
}
