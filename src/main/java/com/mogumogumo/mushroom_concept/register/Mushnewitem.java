package com.mogumogumo.mushroom_concept.register;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Mushnewitem extends Item {
    public Mushnewitem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> list, @NotNull TooltipFlag flag) {

        list.add(Component.translatable("mushroom_concept.item.tooltip.qimeila").withStyle(ChatFormatting.LIGHT_PURPLE));

        super.appendHoverText(stack, level, list, flag);
    }
}