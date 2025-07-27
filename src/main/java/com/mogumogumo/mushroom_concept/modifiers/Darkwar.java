package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.utils.slotUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class Darkwar extends ArmorModifier {
    public Darkwar() {
        MinecraftForge.EVENT_BUS.addListener(this::onTargetChange);
    }

    private void onTargetChange(LivingChangeTargetEvent event) {
        if (event.getNewTarget() != null && event.getEntity() instanceof WitherSkeleton) {
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
    public float TrueDamageamount(IToolStackView armor, int level, EquipmentContext context, EquipmentSlot slot, DamageSource source, float amount, boolean isDirectDamage, LivingEntity entity, LivingEntity enemy) {
        return Math.min(amount, entity.getMaxHealth() / 3);
    }
}

