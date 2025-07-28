package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.Mushroom_Concept;
import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import javax.annotation.Nullable;

public class soul_forge_armor extends ArmorModifier {
    private static final ResourceLocation DEATH = Mushroom_Concept.getResource("death");

    {
        MinecraftForge.EVENT_BUS.addListener(this::LivingDeathEvent);
    }

    @Override
    public @Nullable Component onRemoved(IToolStackView iToolStackView, Modifier modifier) {
        iToolStackView.getPersistentData().remove(DEATH);
        return null;
    }

    private void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (ModifierLevel.getMainhandModifierlevel(event.getEntity(), this.getId()) > 0) {
                ModDataNBT tooldata = ToolStack.from(player.getItemBySlot(EquipmentSlot.MAINHAND)).getPersistentData();
                if (tooldata.getInt(DEATH) < 4) {
                    tooldata.putFloat(DEATH, tooldata.getInt(DEATH) + 1);
                }
            }
        }
    }

    public float TrueDamageamount(IToolStackView armor, int level, EquipmentContext context, EquipmentSlot slot, DamageSource source, float amount, boolean isDirectDamage, LivingEntity entity, LivingEntity enemy) {
        return amount - armor.getPersistentData().getInt(DEATH);
    }
}
