package com.mogumogumo.mushroom_concept.register;

import com.mogumogumo.mushroom_concept.modifiers.Rat.BodyBecomeGod;
import com.mogumogumo.mushroom_concept.modifiers.Rat.RatArmor;
import com.mogumogumo.mushroom_concept.modifiers.Rat.Ratcopperarmor;
import com.mogumogumo.mushroom_concept.modifiers.Rat.Ratcopperking;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.mogumogumo.mushroom_concept.Mushroom_Concept.MODID;

public class MushRatModifiers {
    public static ModifierDeferredRegister RatModifier = ModifierDeferredRegister.create(MODID);
    public static final StaticModifier<RatArmor> ratArmorStaticModifier = RatModifier.register("rat_armor", RatArmor::new);
    public static final StaticModifier<Ratcopperarmor> ratcopperarmorStaticModifier = RatModifier.register("ratcopperarmor", Ratcopperarmor::new);
    public static final StaticModifier<Ratcopperking> ratcopperkingStaticModifier = RatModifier.register("ratcopperking", Ratcopperking::new);
    public static final StaticModifier<BodyBecomeGod> bodyBecomeGodStaticModifier = RatModifier.register("body_become_god", BodyBecomeGod::new);

}
