package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

public class fire extends BattleModifier {
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Entity entity = context.getTarget();
        if (modifier.getLevel() > 0 && entity instanceof LivingEntity target) {
            AttributeInstance attribute = target.getAttributes().getInstance(Attributes.ARMOR);
            if (attribute != null && !(target instanceof Player)) {
                attribute.setBaseValue(attribute.getBaseValue() - 0.1 * target.getArmorValue());
            }
        }
    }

    public boolean modifierOnProjectileHitEntity(ModifierNBT modifiers, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @javax.annotation.Nullable LivingEntity attacker, @javax.annotation.Nullable LivingEntity target) {
        if (modifier.getLevel() > 0 && target != null) {
            AttributeInstance attribute = target.getAttributes().getInstance(Attributes.ARMOR);
            if (attribute != null && !(target instanceof Player)) {
                attribute.setBaseValue(attribute.getBaseValue() - 0.1 * target.getArmorValue());
            }
        }
        return false;
    }
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (attacker instanceof Player player) {
            if (livingTarget.getArmorValue()<1){
                player.heal(player.getMaxHealth()*0.07f);
                return damage;
            }
        }
        return damage;
    }
}
