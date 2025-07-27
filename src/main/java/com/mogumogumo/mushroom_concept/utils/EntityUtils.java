package com.mogumogumo.mushroom_concept.utils;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class EntityUtils {

    public static int getHarmfulEffectCount(LivingEntity entity) {
        return (int)entity.getActiveEffects().stream().filter((e) -> e.getEffect().getCategory() == MobEffectCategory.HARMFUL).count();
    }
}
