package com.mogumogumo.mushroom_concept.library.ProjectileInterface;

/**
 * 定义一个接口，用于表示一个可以造成击退效果的投掷物。
 * 实现此接口的投掷物可以设置和获取击退值。
 */
public interface InterfaceKnockbackProjectile {

    /**
     * 设置投掷物的击退值。
     *
     * @param knockback 要设置的击退值。
     */
    void setKnockback(float knockback);

    /**
     * 获取投掷物的击退值。
     *
     * @return 投掷物的击退值。
     */
    float getKnockback();
}
