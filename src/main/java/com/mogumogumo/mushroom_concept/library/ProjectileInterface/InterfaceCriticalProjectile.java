package com.mogumogumo.mushroom_concept.library.ProjectileInterface;

/**
 * 定义一个接口，用于表示一个可以具有暴击效果的投掷物。
 * 实现此接口的投掷物可以设置和获取暴击状态。
 */
public interface InterfaceCriticalProjectile {

    /**
     * 获取投掷物是否为暴击状态。
     *
     * @return 如果投掷物为暴击状态，则返回 true；否则返回 false。
     */
    boolean getCritical();

    /**
     * 设置投掷物是否为暴击状态。
     *
     * @param critical 如果为 true，则设置投掷物为暴击状态；否则为非暴击状态。
     */
    void setCritical(boolean critical);
}
