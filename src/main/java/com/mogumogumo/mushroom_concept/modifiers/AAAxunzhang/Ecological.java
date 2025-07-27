package com.mogumogumo.mushroom_concept.modifiers.AAAxunzhang;

import com.xiaoyue.tinkers_ingenuity.content.generic.SimpleModifier;
import com.xiaoyue.tinkers_ingenuity.content.shared.holder.CurioStackView;
import com.xiaoyue.tinkers_ingenuity.content.shared.hooks.specail.TinkersCurioModifierHook;
import com.xiaoyue.tinkers_ingenuity.register.TIHooks;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.module.ModuleHookMap;

public class Ecological extends SimpleModifier implements TinkersCurioModifierHook {
    protected void addHooks(ModuleHookMap.Builder builder) {
        builder.addHook(this, TIHooks.TINKERS_CURIO);
    }
    public void onCurioTick(CurioStackView curio, int level, LivingEntity entity) {
        if (entity.tickCount %20 == 0){
            int a =RANDOM.nextInt(3);
            if (a==1) {
                entity.heal(0.5f );
            }
        }
    }
}
