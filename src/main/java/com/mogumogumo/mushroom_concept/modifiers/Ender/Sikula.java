package com.mogumogumo.mushroom_concept.modifiers.Ender;

import com.github.L_Ender.cataclysm.entity.projectile.Water_Spear_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Sikula extends BattleModifier {

    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (attacker instanceof Player player && context != null) {
            Water_Spear_Entity entity = new Water_Spear_Entity(ModEntities.WATER_SPEAR.get(), player.level());
            entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 0.5F);
            player.level().addFreshEntity(entity);
            return damage;
        }
        return damage;
    }

}
