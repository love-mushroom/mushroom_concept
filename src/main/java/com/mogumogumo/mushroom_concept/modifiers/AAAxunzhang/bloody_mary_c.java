package com.mogumogumo.mushroom_concept.modifiers.AAAxunzhang;

import com.xiaoyue.tinkers_ingenuity.content.generic.SimpleModifier;
import com.xiaoyue.tinkers_ingenuity.content.shared.holder.CurioStackView;
import com.xiaoyue.tinkers_ingenuity.content.shared.hooks.specail.TinkersCurioModifierHook;
import com.xiaoyue.tinkers_ingenuity.register.TIHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.module.ModuleHookMap;

public class bloody_mary_c extends SimpleModifier implements TinkersCurioModifierHook {

    public void onDamageTargetPre(CurioStackView curio, int level, LivingEntity attacker, LivingEntity target, LivingHurtEvent event) {
        float a = (target.getMaxHealth() - target.getHealth()) / (target.getMaxHealth());
        event.setAmount(event.getAmount() * (1.0F + a));
    }

    protected void addHooks(ModuleHookMap.Builder builder) {
        builder.addHook(this, TIHooks.TINKERS_CURIO);
    }
}
