package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import com.mogumogumo.mushroom_concept.utils.EntityUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;

import javax.annotation.Nullable;
import java.util.Objects;

public class Principleofsufficientreason extends BattleModifier {

    public boolean havenolevel() {
        return true;
    }
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (attacker instanceof Player player && context != null) {
            int a = RANDOM.nextInt(14);
            int b = RANDOM.nextInt(14);
            if (a == 1){livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON,90 * level, level));}
            if (a == 2){livingTarget.addEffect(new MobEffectInstance(MobEffects.WITHER,90 * level, level));}
            if (a == 3){livingTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,90 * level, level));}
            if (a == 4){livingTarget.addEffect(new MobEffectInstance(MobEffects.LEVITATION,90 * level, level));}
            if (a == 5){livingTarget.addEffect(new MobEffectInstance(MobEffects.HUNGER,90 * level, level));}
            if (a == 6){livingTarget.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,90 * level, level));}
            if (a == 7){livingTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,90 * level, level));}
            if (a == 8){livingTarget.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,90 * level, level));}
            if (a == 9){livingTarget.addEffect(new MobEffectInstance(MobEffects.GLOWING,90 * level, level));}
            if (a == 10){livingTarget.addEffect(new MobEffectInstance(MobEffects.DARKNESS,90 * level, level));}
            if (a == 11){livingTarget.addEffect(new MobEffectInstance(MobEffects.UNLUCK,90 * level, level));}
            if (a == 0){livingTarget.addEffect(new MobEffectInstance(MobEffects.HARM,1, level));}
            if (a == 12){livingTarget.addEffect(new MobEffectInstance(TinkerModifiers.bleeding.get(), 90, 1));}
            if (a == 13){livingTarget.addEffect(new MobEffectInstance(TinkerModifiers.enderferenceEffect.get(), 90, 1));}
            if (b == 1){livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON,90 * level, level));}
            if (b == 2){livingTarget.addEffect(new MobEffectInstance(MobEffects.WITHER,90 * level, level));}
            if (b == 3){livingTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,90 * level, level));}
            if (b == 4){livingTarget.addEffect(new MobEffectInstance(MobEffects.LEVITATION,90 * level, level));}
            if (b == 5){livingTarget.addEffect(new MobEffectInstance(MobEffects.HUNGER,90 * level, level));}
            if (b == 6){livingTarget.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,90 * level, level));}
            if (b == 7){livingTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,90 * level, level));}
            if (b == 8){livingTarget.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,90 * level, level));}
            if (b == 9){livingTarget.addEffect(new MobEffectInstance(MobEffects.GLOWING,90 * level, level));}
            if (b == 10){livingTarget.addEffect(new MobEffectInstance(MobEffects.DARKNESS,90 * level, level));}
            if (b == 11){livingTarget.addEffect(new MobEffectInstance(MobEffects.UNLUCK,90 * level, level));}
            if (b == 0){livingTarget.addEffect(new MobEffectInstance(MobEffects.HARM,1, level));}
            if (b == 12){livingTarget.addEffect(new MobEffectInstance(TinkerModifiers.bleeding.get(), 90, 1));}
            if (b == 13){livingTarget.addEffect(new MobEffectInstance(TinkerModifiers.enderferenceEffect.get(), 90, 1));}
        }
        float a = 1+(EntityUtils.getHarmfulEffectCount(livingTarget)*0.2f);
        return damage * a;
    }
    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target)  {
        if (target != null) {
            AbstractArrow arrow = (AbstractArrow) projectile;
            int level = modifier.getLevel();
            int a = RANDOM.nextInt(14);
            int b = RANDOM.nextInt(14);
            if (a == 1){target.addEffect(new MobEffectInstance(MobEffects.POISON,90 * level, level));}
            if (a == 2){target.addEffect(new MobEffectInstance(MobEffects.WITHER,90 * level, level));}
            if (a == 3){target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,90 * level, level));}
            if (a == 4){target.addEffect(new MobEffectInstance(MobEffects.LEVITATION,90 * level, level));}
            if (a == 5){target.addEffect(new MobEffectInstance(MobEffects.HUNGER,90 * level, level));}
            if (a == 6){target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,90 * level, level));}
            if (a == 7){target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,90 * level, level));}
            if (a == 8){target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,90 * level, level));}
            if (a == 9){target.addEffect(new MobEffectInstance(MobEffects.GLOWING,90 * level, level));}
            if (a == 10){target.addEffect(new MobEffectInstance(MobEffects.DARKNESS,90 * level, level));}
            if (a == 11){target.addEffect(new MobEffectInstance(MobEffects.UNLUCK,90 * level, level));}
            if (a == 0){target.addEffect(new MobEffectInstance(MobEffects.HARM,1, level));}
            if (a == 12){target.addEffect(new MobEffectInstance(TinkerModifiers.bleeding.get(), 90, 1));}
            if (a == 13){target.addEffect(new MobEffectInstance(TinkerModifiers.enderferenceEffect.get(), 90, 1));}
            if (b == 1){target.addEffect(new MobEffectInstance(MobEffects.POISON,90 * level, level));}
            if (b == 2){target.addEffect(new MobEffectInstance(MobEffects.WITHER,90 * level, level));}
            if (b == 3){target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS,90 * level, level));}
            if (b == 4){target.addEffect(new MobEffectInstance(MobEffects.LEVITATION,90 * level, level));}
            if (b == 5){target.addEffect(new MobEffectInstance(MobEffects.HUNGER,90 * level, level));}
            if (b == 6){target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,90 * level, level));}
            if (b == 7){target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,90 * level, level));}
            if (b == 8){target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,90 * level, level));}
            if (b == 9){target.addEffect(new MobEffectInstance(MobEffects.GLOWING,90 * level, level));}
            if (b == 10){target.addEffect(new MobEffectInstance(MobEffects.DARKNESS,90 * level, level));}
            if (b == 11){target.addEffect(new MobEffectInstance(MobEffects.UNLUCK,90 * level, level));}
            if (b == 0){target.addEffect(new MobEffectInstance(MobEffects.HARM,1, level));}
            if (b == 12){target.addEffect(new MobEffectInstance(TinkerModifiers.bleeding.get(), 90, 1));}
            if (b == 13){target.addEffect(new MobEffectInstance(TinkerModifiers.enderferenceEffect.get(), 90, 1));}
            float d =1+(EntityUtils.getHarmfulEffectCount(target)*0.2f);
            arrow.setBaseDamage(arrow.getBaseDamage()*d);
            return false;
        }
        return false;
    }
}
