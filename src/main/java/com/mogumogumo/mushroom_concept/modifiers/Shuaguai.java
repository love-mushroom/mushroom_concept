package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.ForgeSpawnEggItem;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class Shuaguai extends BattleModifier {
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.PROCESS_LOOT);
    }

    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        for (ItemStack stack : generatedLoot) {
            if (stack.is(Items.SPAWNER)) {
                generatedLoot.add(new ItemStack(Items.SPAWNER));
            }
        }
        if (!context.hasParam(LootContextParams.DAMAGE_SOURCE)) {
            return;
        }
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity != null) {
            Item eggItem = ForgeSpawnEggItem.fromEntityType(entity.getType());

            if (eggItem == null) return;

            ItemStack egg = new ItemStack(eggItem);

            generatedLoot.add(egg);
        }
    }

}
