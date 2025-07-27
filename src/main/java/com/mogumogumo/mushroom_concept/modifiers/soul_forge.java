package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import com.mogumogumo.mushroom_concept.mushroom_concept;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.Objects;

public class soul_forge extends BattleModifier {
    public soul_forge() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST,this::onEntityDeath);
    }
    @Override
    public Component getDisplayName(int level) {
        return super.getDisplayName();
    }
    public static final ResourceLocation heroic_spirit = mushroom_concept.getResource("heroic_spirit");
    private void onEntityDeath(LivingDeathEvent event){
        if (event.getSource().getEntity() instanceof Player player&&event.getEntity()!=null) {
            ModDataNBT a = ToolStack.from(player.getItemBySlot(EquipmentSlot.MAINHAND)).getPersistentData();
            if (a.getInt(heroic_spirit)<100){
                a.putInt(heroic_spirit, a.getInt(heroic_spirit) + 1);
            }
        }
    }
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (attacker instanceof Player player) {
            return damage + tool.getPersistentData().getInt(heroic_spirit) ;
        }
        return damage;
    }
}
