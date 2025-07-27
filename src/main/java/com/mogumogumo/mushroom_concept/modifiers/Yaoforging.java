package com.mogumogumo.mushroom_concept.modifiers;


import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.Random;

public class Yaoforging extends ArmorModifier {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    @Override
    public Component getDisplayName(int level) {
        return super.getDisplayName();
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.DRAW_SPEED.add(builder, 6);
        ToolStats.MINING_SPEED.add(builder, 0.25);
        ToolStats.ATTACK_SPEED.add(builder, 0.25);
        ToolStats.ATTACK_DAMAGE.add(builder, 4);
        ToolStats.ATTACK_DAMAGE.multiply(builder, 1.25);
        ToolStats.ARMOR.add(builder, 3);
        ToolStats.ARMOR.multiply(builder, 1.25);
        ToolStats.ARMOR_TOUGHNESS.add(builder, 1);
        ToolStats.ARMOR_TOUGHNESS.multiply(builder, 1.25);
    }

    public boolean havenolevel() {
        return true;
    }

    public void LivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() != null) {
            LivingEntity entity = event.getEntity();
            if (ModifierLevel.getTotalArmorModifierlevel(entity, this.getId()) > 0) {
                if (entity instanceof Player player ) {
                    if (event.getAmount()<1){
                        event.getEntity().invulnerableTime = 40;
                        event.setCanceled(true);
                        }
                    if (event.getAmount()>=1){
                        event.setAmount(event.getAmount()*0.96f - ModifierLevel.getTotalArmorModifierlevel(entity, this.getId()));
                    }
                }
            }
        }
    }
}
