package com.mogumogumo.mushroom_concept.modifiers.Rat;

import com.github.alexthe666.rats.server.entity.rat.TamedRat;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import com.mogumogumo.mushroom_concept.register.MushRatModifiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ValidateModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BodyBecomeGod extends BattleModifier implements ModifyDamageModifierHook , ValidateModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MODIFY_DAMAGE,ModifierHooks.VALIDATE);
    }

    @Override
    public float modifyDamageTaken(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, EquipmentContext context, @NotNull EquipmentSlot slotType, @NotNull DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity entity = context.getEntity();
        if (entity instanceof TamedRat tamedRat) {
            float a = (float) (0.05f);
            return amount * a;
        }
        return amount;
    }

    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!world.isClientSide && holder.tickCount % 20 == 0) {
            if (holder instanceof TamedRat tamedRat) {
                tamedRat.heal(tamedRat.getMaxHealth() * 0.05f);
            }
        }
    }

    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (attacker instanceof TamedRat tamedRat && context != null) {
            return damage * 4F;
        }
        return damage;
    }

    @Override
    public @Nullable Component validate(IToolStackView iToolStackView, ModifierEntry modifierEntry) {
        if (iToolStackView.getModifierLevel(MushRatModifiers.ratArmorStaticModifier.getId()) >= 5) {
            return null;
        }
        return requirementsError(modifierEntry);
    }
    public @Nullable Component requirementsError(ModifierEntry entry) {
        return Component.translatable("recipes.modifier.mushroom_concept.rat_god");
    }
}
