package com.mogumogumo.mushroom_concept.extend.superclass;

import com.mogumogumo.mushroom_concept.extend.interfaces.AboutArrow;
import com.mogumogumo.mushroom_concept.extend.interfaces.AboutAttack;
import com.mogumogumo.mushroom_concept.extend.interfaces.AboutBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.*;

import java.util.List;
import java.util.function.BiConsumer;


public class BattleModifier extends Modifier implements AboutAttack, AboutBuilder, AboutArrow, EquipmentChangeModifierHook {
    public BattleModifier() {
        MinecraftForge.EVENT_BUS.addListener(this::LivingHurtEvent);
        MinecraftForge.EVENT_BUS.addListener(this::LivingAttackEvent);
        MinecraftForge.EVENT_BUS.addListener(this::LivingDamageEvent);
    }

    protected void registerHooks(ModuleHookMap.@NotNull Builder builder) {
        this.initattackinterface(builder);
        this.initbuilderinterface(builder);
        this.initarrowinterface(builder);
        builder.addHook(this, ModifierHooks.EQUIPMENT_CHANGE);
        super.registerHooks(builder);
    }

    public void LivingDamageEvent(LivingDamageEvent event) {
    }

    public void LivingHurtEvent(LivingHurtEvent event) {
    }

    public void LivingAttackEvent(LivingAttackEvent event) {
    }

    public boolean havenolevel() {
        return false;
    }

    public @NotNull Component getDisplayName(int level) {
        return this.havenolevel() ? super.getDisplayName() : super.getDisplayName(level);
    }

    public float modifierDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        return amount;
    }

    public boolean hidden() {
        return false;
    }

    public void addAttributes(ToolAttackContext context, IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
    }


    public void modifierOnEquip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
    }

    public void modifierOnUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
    }

    public boolean modifierOnProjectileHitEntity(ModifierNBT modifiers, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        return false;
    }

    public boolean shouldDisplay(boolean advanced) {
        return !this.hidden() || advanced;
    }

    public void processLoot(IToolStackView iToolStackView, ModifierEntry modifierEntry, List<ItemStack> list, LootContext lootContext) {
    }

    public @Nullable Component onRemoved(IToolStackView iToolStackView, Modifier modifier) {
        return null;
    }

    /**
     * @param iToolContext
     * @param modifierEntry
     * @param toolDataNBT
     */
    @Override
    public void addVolatileData(IToolContext iToolContext, ModifierEntry modifierEntry, ToolDataNBT toolDataNBT) {

    }


    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifiers, LivingEntity livingEntity, Projectile projectile, @Nullable AbstractArrow abstractArrow, boolean primary) {
        this.modifierOnProjectileLaunch(tool, modifiers, livingEntity, projectile, abstractArrow);
    }

    private void modifierOnProjectileLaunch(IToolStackView tool, ModifierEntry modifiers, LivingEntity livingEntity, Projectile projectile, @Nullable AbstractArrow abstractArrow) {
    }

    /**
     * @param iToolStackView
     * @param modifierEntry
     * @param livingEntity
     * @param projectile
     * @param abstractArrow
     * @param modDataNBT
     * @param b
     */
    @Override
    public void onProjectileLaunch(IToolStackView iToolStackView, ModifierEntry modifierEntry, LivingEntity livingEntity, Projectile projectile, @Nullable AbstractArrow abstractArrow, ModDataNBT modDataNBT, boolean b) {

    }
}
