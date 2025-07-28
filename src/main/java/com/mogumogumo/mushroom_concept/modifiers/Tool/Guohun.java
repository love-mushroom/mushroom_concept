package com.mogumogumo.mushroom_concept.modifiers.Tool;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Guohun extends BattleModifier {
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (livingTarget.getArmorValue() <= 12) {
            return damage * 1.5F;
        }
        return damage;
    }
}
