package com.mogumogumo.mushroom_concept.register;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class MushFood {
    public static final FoodProperties blood_ball = new FoodProperties.Builder().alwaysEat().nutrition(2).saturationMod(2F)
            .effect(() ->new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 1.0F)
            .effect(() ->new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 1.0F)
            .build();
}
