package com.mogumogumo.mushroom_concept.library;

import com.mogumogumo.mushroom_concept.library.utils.ArrowAttackUtil;
import com.mogumogumo.mushroom_concept.library.utils.ThrowableProjectileAttackUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * 定义一个可投掷 projectile 物品的接口。
 * 该接口提供了创建 projectile、为 projectile 提供额外信息以及处理 projectile 造成伤害的方法。
 */
public interface InterfaceProjectileItem {

    /**
     * 创建一个 projectile 实体。
     *
     * @param tool 手里剑物品堆栈。
     * @param world 世界对象。
     * @param shooter 射击者实体。
     * @return 新创建的 projectile 实体。
     */
    Projectile createProjectile(ItemStack tool, Level world, LivingEntity shooter);

    /**
     * 为 projectile 实体提供额外的信息。
     *
     * @param projectile 要提供信息的 projectile 实体。
     * @param tool 手里剑物品堆栈。
     * @param world 世界对象。
     * @param shooter 射击者实体。
     */
    void supplyInfoToProjectile(Projectile projectile, ItemStack tool, Level world, LivingEntity shooter);

    /**
     * 处理 projectile 造成的伤害。
     *
     * @param projectile 造成伤害的 projectile 实体。
     * @param stack 与 projectile 相关的工具堆栈视图。
     * @param context 工具攻击上下文。
     * @param damage 要造成的伤害量。
     * @return 如果伤害处理成功，则返回 true；否则返回 false。
     */
    default boolean dealDamageProjectile(Projectile projectile, IToolStackView stack, ToolAttackContext context, float damage) {
        // 调用工具类中的方法来处理默认的伤害
        return ThrowableProjectileAttackUtil.dealDeafaultDamage(projectile, context.getAttacker(), context.getTarget(), damage);
    }

    default boolean dealArrowDamageProjectile(AbstractArrow arrow, IToolStackView stack, ToolAttackContext context, float damage) {
        // 调用工具类中的方法来处理默认的伤害
        return ArrowAttackUtil.dealDefaultDamage(arrow, context.getAttacker(), context.getTarget(), damage);
    }
}
