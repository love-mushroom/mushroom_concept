package com.mogumogumo.mushroom_concept.library.ProjectileInterface;

/**
 * 定义一个接口，用于表示一个可以造成伤害的投掷物。
 * 实现此接口的投掷物可以设置和获取伤害值。
 */
public interface InterfaceDamageProjectile {

    /**
     * 设置投掷物的伤害值。
     *
     * @param damage 要设置的伤害值。
     */
    void setDamage(float damage);

    /**
     * 获取投掷物的伤害值。
     *
     * @return 投掷物的伤害值。
     */
    float getDamage();
}
