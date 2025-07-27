package com.mogumogumo.mushroom_concept.library.utils;

import com.mogumogumo.mushroom_concept.library.InterfaceProjectileItem;
import com.mogumogumo.mushroom_concept.library.ProjectileInterface.InterfaceCriticalProjectile;
import com.mogumogumo.mushroom_concept.library.ProjectileInterface.InterfaceDamageProjectile;
import com.mogumogumo.mushroom_concept.library.ProjectileInterface.InterfaceKnockbackProjectile;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Random;

public class ArrowAttackUtil {
    public static boolean dealDefaultDamage(AbstractArrow arrow, LivingEntity attacker, Entity target, float damage) {
        if (arrow.level() instanceof ServerLevel) {
            DamageSource damageSource = arrow.damageSources().arrow(arrow, attacker);
            return target.hurt(damageSource, damage);
        }
        return false;
    }

    public static boolean attackEntity(Item weapon, AbstractArrow arrow, IToolStackView tool, LivingEntity attackerLiving, Entity targetEntity, float velocity, float bowknockback, boolean isExtraAttack) {
        boolean wasCritical = false;
        Random random = new Random();

        if (arrow instanceof InterfaceCriticalProjectile) {
            wasCritical = ((InterfaceCriticalProjectile) arrow).getCritical();
        }

        LivingEntity targetLiving = targetEntity instanceof LivingEntity ? (LivingEntity) targetEntity : null;

        if (attackerLiving == null) {
            return false;
        }

        ToolAttackContext context = new ToolAttackContext(attackerLiving, attackerLiving instanceof Player ? (Player) attackerLiving : null, InteractionHand.MAIN_HAND,
                targetEntity, targetLiving, wasCritical, 1, isExtraAttack);

        float damage;
        float knockback;

        if (arrow instanceof InterfaceDamageProjectile) {
            damage = ((InterfaceDamageProjectile) arrow).getDamage();
        } else {
            damage = tool.getDamage();
        }

        float baseDamage = damage;

        for (ModifierEntry entry : tool.getModifierList()) {
            damage = entry.getHook(ModifierHooks.MELEE_DAMAGE).getMeleeDamage(tool, entry, context, baseDamage, damage);
        }

        damage = Mth.ceil(Mth.clamp((double) velocity * (0.8 * damage + arrow.getBaseDamage()), 0.0D, 2.147483647E9D));
        if (arrow.isCritArrow()) {
            long j = random.nextInt((int) (damage / 2 + 2));
            damage = (int) Math.min(j + (long) damage, 2147483647L);
        }

        if (arrow instanceof InterfaceKnockbackProjectile) {
            knockback = ((InterfaceKnockbackProjectile) arrow).getKnockback();
        } else {
            knockback = 0.4F;
        }

        float baseKnockback = knockback;

        for (ModifierEntry entry : tool.getModifierList()) {
            knockback = entry.getHook(ModifierHooks.MELEE_HIT).beforeMeleeHit(tool, entry, context, damage, baseKnockback, knockback);
        }

        knockback = knockback + bowknockback;

        float oldHealth = 0.0F;
        if (targetLiving != null && targetEntity != null) {
            oldHealth = targetLiving.getHealth();
        }

        boolean didHit;

        if (isExtraAttack || !(weapon instanceof InterfaceProjectileItem)) {
            didHit = dealDefaultDamage(arrow, attackerLiving, targetEntity, damage);
        } else {
            didHit = ((InterfaceProjectileItem) weapon).dealArrowDamageProjectile(arrow, tool, context, damage);
        }

        if (didHit) {
            // 计算实际造成的伤害
            float damageDealt = damage;
            if (targetEntity != null && targetLiving != null) {
                damageDealt = oldHealth - targetLiving.getHealth();
            }

            if (knockback > 0) {
                Vec3 vec3 = arrow.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double) knockback * 0.6D);
                if (vec3.lengthSqr() > 0.0D) {
                    targetEntity.push(vec3.x, 0.1D, vec3.z);
                }
            }

            for (ModifierEntry entry : tool.getModifierList()) {
                entry.getHook(ModifierHooks.MELEE_HIT).afterMeleeHit(tool, entry, context, damageDealt);
            }

            return true;

        } else {
            for (ModifierEntry entry : tool.getModifierList()) {
                entry.getHook(ModifierHooks.MELEE_HIT).failedMeleeHit(tool, entry, context, damage);
            }

            return !isExtraAttack;
        }
    }
}

