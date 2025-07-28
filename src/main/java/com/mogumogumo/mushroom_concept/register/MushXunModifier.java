package com.mogumogumo.mushroom_concept.register;

import com.mogumogumo.mushroom_concept.modifiers.AAAxunzhang.Ecological;
import com.mogumogumo.mushroom_concept.modifiers.AAAxunzhang.bloody_mary_c;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.mogumogumo.mushroom_concept.Mushroom_Concept.MODID;

public class MushXunModifier {
    public static ModifierDeferredRegister XunModifier = ModifierDeferredRegister.create(MODID);

    public static final StaticModifier<Ecological> pBodyStaticModifier = XunModifier.register("ecological", Ecological::new);
    public static final StaticModifier<bloody_mary_c> bloodyMaryCStaticModifier = XunModifier.register("bloody_mary_c", bloody_mary_c::new);
}
