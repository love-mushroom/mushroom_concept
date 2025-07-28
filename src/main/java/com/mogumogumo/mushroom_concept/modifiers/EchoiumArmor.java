package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.utils.MushUtils;
import com.mogumogumo.mushroom_concept.utils.slotUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.UUID;
import java.util.function.BiConsumer;

public class EchoiumArmor extends ArmorModifier {

    public EchoiumArmor() {
        MinecraftForge.EVENT_BUS.addListener(this::onTargetChange);
    }

    private void onTargetChange(LivingChangeTargetEvent event) {
        if (event.getNewTarget() != null && event.getEntity() instanceof EnderMan) {
            LivingEntity holder = event.getNewTarget();
            int modifierLv = 0;
            for (EquipmentSlot slot : slotUtil.ARMOR) {
                ItemStack stack = holder.getItemBySlot(slot);
                if (stack.getItem() instanceof IModifiable) {
                    ToolStack tool = ToolStack.from(stack);
                    if (tool.getModifierLevel(this) > 0) {
                        modifierLv += tool.getModifierLevel(this);
                    }
                }
            }
            if (modifierLv >= 1 && event.getNewTarget() != event.getEntity().getLastHurtByMob()) {
                event.setCanceled(true);
            }
        }
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE);
    }

    @Override
    public void onInventoryTick(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        holder.removeEffect(MobEffects.BLINDNESS);
        holder.removeEffect(MobEffects.DARKNESS);
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (MushUtils.isInArmorSlots(slot)) {
            switch (slot) {
                case HEAD -> {
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("f85578f9-5954-40dd-a683-5143820babe8"), Attributes.MAX_HEALTH.getDescriptionId(), 5, AttributeModifier.Operation.ADDITION));
                }
                case CHEST -> {
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("8cf4ca8a-f3b3-4ac8-a1c3-72d886dad20e"), Attributes.MAX_HEALTH.getDescriptionId(), 6, AttributeModifier.Operation.ADDITION));
                }
                case LEGS -> {
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("4aa25a4b-0337-48fe-a21c-879fc3d26caa"), Attributes.MAX_HEALTH.getDescriptionId(), 5, AttributeModifier.Operation.ADDITION));
                }
                case FEET -> {
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("55407ada-f46f-4421-a9b9-eece8941a19f"), Attributes.MAX_HEALTH.getDescriptionId(), 4, AttributeModifier.Operation.ADDITION));
                }
            }
        }
        if (MushUtils.isShieldInHandSlots(tool, slot)) {
            switch (slot) {
                case MAINHAND, OFFHAND -> {
                    consumer.accept(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("154de1d5-370b-4f8a-b317-1e3914c350f9"), Attributes.ATTACK_SPEED.getDescriptionId(), 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("922ee974-e7ce-47a6-8500-4b267b6fdf88"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("50612a79-ec41-4ecf-bcd4-af7f158a0854"), Attributes.MAX_HEALTH.getDescriptionId(), 5, AttributeModifier.Operation.ADDITION));
                }
            }
        }
    }
}
