package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.Mushroom_Concept;
import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.BiConsumer;

public class Prideful extends ArmorModifier {
    public static final ResourceLocation Prideful = Mushroom_Concept.getResource("prideful");

    public float TrueDamageamount(IToolStackView armor, int level, EquipmentContext context, EquipmentSlot slot, DamageSource source, float amount, boolean isDirectDamage, LivingEntity entity, LivingEntity enemy) {
        ModDataNBT a = armor.getPersistentData();
        if (entity != null && a.getInt(Prideful) < 50) {
            a.putInt(Prideful, a.getInt(Prideful) + 5);
        }
        return amount;
    }

    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        ModDataNBT a = tool.getPersistentData();
        if (!world.isClientSide && holder.tickCount % 20 == 0 && a.getInt(Prideful) > 1) {
            a.putInt(Prideful, a.getInt(Prideful) - 1);
        }
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        ModDataNBT a = tool.getPersistentData();
        int b = a.getInt(Prideful) / 25 * modifier.getLevel();
        consumer.accept(Attributes.ARMOR, new AttributeModifier(UUID.fromString("9bf29a55-81e2-4ba5-957f-596fb2a4082b"), Attributes.ARMOR.getDescriptionId(), 0.1f * b, AttributeModifier.Operation.MULTIPLY_TOTAL));
        consumer.accept(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.fromString("9bf29a55-81e2-4ba5-957f-596fb2a4082b"), Attributes.ATTACK_DAMAGE.getDescriptionId(), 0.1f * b, AttributeModifier.Operation.MULTIPLY_TOTAL));
    }
}
