package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import com.mogumogumo.mushroom_concept.utils.MushUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.UUID;
import java.util.function.BiConsumer;

public class EtheriteArmor extends ArmorModifier {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK, ModifierHooks.ATTRIBUTES, ModifierHooks.REMOVE);
    }

    public void MobEffectEvent(MobEffectEvent.Applicable event) {
        if (event.getEntity() != null && event.getEntity() instanceof LivingEntity) {
            if (ModifierLevel.EquipHasModifierlevel(event.getEntity(), this.getId())) {
                if (!event.getEffectInstance().getEffect().isBeneficial()) {
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }

    public void LivingAttackEvent(LivingAttackEvent event) {
        if (ModifierLevel.getTotalArmorModifierlevel(event.getEntity(), this.getId()) > 0) {
            if (event.getEntity() instanceof Player player) {
                if (event.getSource().getEntity() == null) {
                    event.getEntity().invulnerableTime = 80;
                    event.setCanceled(true);
                }
            }
        }
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        if (MushUtils.isInArmorSlots(slot)) {
            switch (slot) {
                case HEAD -> {
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("f85578f9-5954-40dd-a683-5143820babe8"), Attributes.MAX_HEALTH.getDescriptionId(), 14, AttributeModifier.Operation.ADDITION));
                }
                case CHEST -> {
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("8cf4ca8a-f3b3-4ac8-a1c3-72d886dad20e"), Attributes.MAX_HEALTH.getDescriptionId(), 18, AttributeModifier.Operation.ADDITION));
                }
                case LEGS -> {
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("4aa25a4b-0337-48fe-a21c-879fc3d26caa"), Attributes.MAX_HEALTH.getDescriptionId(), 16, AttributeModifier.Operation.ADDITION));
                }
                case FEET -> {
                    consumer.accept(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("55407ada-f46f-4421-a9b9-eece8941a19f"), Attributes.MAX_HEALTH.getDescriptionId(), 12, AttributeModifier.Operation.ADDITION));
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
