package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.Mushroom_Concept;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class soul_forge extends BattleModifier {
    public static final ResourceLocation heroic_spirit = Mushroom_Concept.getResource("heroic_spirit");

    public soul_forge() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::onEntityDeath);
    }

    @Override
    public Component getDisplayName(int level) {
        return super.getDisplayName();
    }

    private void onEntityDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player && event.getEntity() != null) {
            ModDataNBT a = ToolStack.from(player.getItemBySlot(EquipmentSlot.MAINHAND)).getPersistentData();
            if (a.getInt(heroic_spirit) < 100) {
                a.putInt(heroic_spirit, a.getInt(heroic_spirit) + 1);
            }
        }
    }

    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (attacker instanceof Player player) {
            return damage + tool.getPersistentData().getInt(heroic_spirit);
        }
        return damage;
    }
}
