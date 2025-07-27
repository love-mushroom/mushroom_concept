package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Objects;

public class Dirtydeedsdonedirtcheap extends BattleModifier {
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (attacker instanceof Player player) {
            int b = RANDOM.nextInt(4*level);
            int a = RANDOM.nextInt(4*level);
            int c = RANDOM.nextInt(4*level);
            livingTarget.teleportTo(livingTarget.getX()+a-b,livingTarget.getY()+b-c,livingTarget.getZ()+c-a);
            return damage * (1+ (float) (a + b + c) /10);
        }
        return damage;
    }
}
