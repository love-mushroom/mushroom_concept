package com.mogumogumo.mushroom_concept.library.utils;

import com.mogumogumo.mushroom_concept.library.InterfaceProjectileItem;
import com.mogumogumo.mushroom_concept.library.ProjectileInterface.InterfaceCriticalProjectile;
import com.mogumogumo.mushroom_concept.library.ProjectileInterface.InterfaceDamageProjectile;
import com.mogumogumo.mushroom_concept.library.ProjectileInterface.InterfaceKnockbackProjectile;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ThrowableProjectileAttackUtil {

    /**
     * 处理默认的伤害逻辑。
     *
     * @param projectile 投掷物实体。
     * @param attacker   攻击者实体。
     * @param target     目标实体。
     * @param damage     伤害值。
     * @return 如果目标受到伤害，则返回 true；否则返回 false。
     */
    public static boolean dealDeafaultDamage(Projectile projectile, LivingEntity attacker, Entity target, float damage) {
        if (projectile.level() instanceof ServerLevel) {
            DamageSource damageSource = projectile.damageSources().thrown(projectile, attacker);
            return target.hurt(damageSource, damage);
        }
        return false;
    }

    /**
     * 攻击实体的方法。
     *
     * @param weapon         武器物品。
     * @param projectile     投掷物实体。
     * @param tool           工具堆栈视图。
     * @param attackerLiving 攻击者实体。
     * @param targetEntity   目标实体。
     * @param isExtraAttack  是否是额外攻击。
     * @return 如果攻击成功，则返回 true；否则返回 false。
     */
    public static boolean attackEntity(Item weapon, Projectile projectile, IToolStackView tool, LivingEntity attackerLiving, Entity targetEntity, boolean isExtraAttack) {
        boolean wasCritical = false;

        // 检查投掷物是否是暴击投掷物，并获取暴击状态
        if (projectile instanceof InterfaceCriticalProjectile) {
            wasCritical = ((InterfaceCriticalProjectile) projectile).getCritical();
        }

        // 将目标实体转换为 LivingEntity 类型，如果不是则设置为 null
        LivingEntity targetLiving = targetEntity instanceof LivingEntity ? (LivingEntity) targetEntity : null;

        // 如果攻击者为空，则返回 false
        if (attackerLiving == null) {
            return false;
        }

        // 创建一个 ToolAttackContext 对象，包含攻击的上下文信息
        ToolAttackContext context = new ToolAttackContext(attackerLiving, attackerLiving instanceof Player ? (Player) attackerLiving : null, InteractionHand.MAIN_HAND,
                targetEntity, targetLiving, wasCritical, 1, isExtraAttack);

        float damage;
        float knockback;

        // 检查投掷物是否是伤害投掷物，并获取伤害值
        if (projectile instanceof InterfaceDamageProjectile) {
            damage = ((InterfaceDamageProjectile) projectile).getDamage();
        } else {
            damage = tool.getDamage();
        }

        // 获取基础伤害值
        float baseDamage = damage;

        // 遍历工具的修饰符列表，应用每个修饰符对伤害的影响
        for (ModifierEntry entry : tool.getModifierList()) {
            damage = entry.getHook(ModifierHooks.MELEE_DAMAGE).getMeleeDamage(tool, entry, context, baseDamage, damage);
        }

        // 检查投掷物是否是击退投掷物，并获取击退值
        if (projectile instanceof InterfaceKnockbackProjectile) {
            knockback = ((InterfaceKnockbackProjectile) projectile).getKnockback();
        } else {
            knockback = 0.4F;
        }

        // 获取基础击退值
        float baseKnockback = knockback;

        // 遍历工具的修饰符列表，应用每个修饰符对击退的影响
        for (ModifierEntry entry : tool.getModifierList()) {
            knockback = entry.getHook(ModifierHooks.MELEE_HIT).beforeMeleeHit(tool, entry, context, damage, baseKnockback, knockback);
        }

        // 初始化目标实体的旧生命值
        float oldHealth = 0.0F;
        if (targetLiving != null && targetEntity != null) {
            oldHealth = targetLiving.getHealth();
        }

        boolean didHit;
        // 如果是额外攻击或者武器不是可投掷 projectile 物品，则使用默认的伤害逻辑
        if (isExtraAttack || !(weapon instanceof InterfaceProjectileItem)) {
            didHit = dealDeafaultDamage(projectile, attackerLiving, targetEntity, damage);
        } else {
            // 否则，使用 InterfaceThrowableProjectileItem 接口的 dealDamageProjectile 方法处理伤害
            didHit = ((InterfaceProjectileItem) weapon).dealDamageProjectile(projectile, tool, context, damage);
        }

        // 如果攻击命中
        if (didHit) {
            // 计算实际造成的伤害
            float damageDealt = damage;
            if (targetEntity != null && targetLiving != null) {
                damageDealt = oldHealth - targetLiving.getHealth();
            }

            // 如果有击退效果，应用击退
            if (knockback > 0) {
                Vec3 vec3 = projectile.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double) knockback * 0.6D);
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
