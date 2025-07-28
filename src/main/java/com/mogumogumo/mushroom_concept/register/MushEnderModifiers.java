package com.mogumogumo.mushroom_concept.register;

import com.mogumogumo.mushroom_concept.modifiers.Ender.Sikula;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.mogumogumo.mushroom_concept.Mushroom_Concept.MODID;

public class MushEnderModifiers {

    public static ModifierDeferredRegister ENDER_MODIFIERS = ModifierDeferredRegister.create(MODID);
    public static final StaticModifier<Sikula> sikulaStaticModifier = ENDER_MODIFIERS.register("sikula", Sikula::new);

}
