package com.mogumogumo.mushroom_concept.tool.tinkertool;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

public class shroomite_zhua extends ModifiableItem {

    public shroomite_zhua(Item.Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        return 15f;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState blockState, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return true;
    }
}
