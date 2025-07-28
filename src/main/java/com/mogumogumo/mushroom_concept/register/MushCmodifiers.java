package com.mogumogumo.mushroom_concept.register;

import com.mogumogumo.mushroom_concept.modifiers.*;
import com.mogumogumo.mushroom_concept.modifiers.Tool.Guohun;
import com.mogumogumo.mushroom_concept.modifiers.Tool.MaoGun;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.mogumogumo.mushroom_concept.Mushroom_Concept.MODID;

public class MushCmodifiers {
    public static ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MODID);

    public static final StaticModifier<Guohun> guohunStaticModifier = MODIFIERS.register("guo_hun", Guohun::new);


    public static final StaticModifier<amethyst_armor> amethystArmorStaticModifier = MODIFIERS.register("amethyst_armor", amethyst_armor::new);
    public static final StaticModifier<Ratcoppertion> RATCOPPERTION_STATIC_MODIFIER = MODIFIERS.register("ratcoppertion", Ratcoppertion::new);
    public static final StaticModifier<chuantou> chuantouStaticModifier = MODIFIERS.register("chuantou", chuantou::new);
    public static final StaticModifier<Ajourneytothecentreoftheearth> Ajourneytothecentreoftheearth = MODIFIERS.register("a_journey_to_the_centre_of_the_earth", Ajourneytothecentreoftheearth::new);
    public static final StaticModifier<b_sharw> bSharwStaticModifier = MODIFIERS.register("b_sharw", b_sharw::new);
    public static final StaticModifier<stone> stoneStaticModifier = MODIFIERS.register("stone", stone::new);
    public static final StaticModifier<Kirakira> KIRAKIRA_STATIC_MODIFIER = MODIFIERS.register("kirakira", Kirakira::new);
    public static final StaticModifier<fire> fireStaticModifier = MODIFIERS.register("fire", fire::new);
    public static final StaticModifier<Outplane> OUT_PLANE_STATIC_MODIFIER = MODIFIERS.register("out_plane", Outplane::new);
    public static final StaticModifier<Prideful> PRIDEFUL_STATIC_MODIFIER = MODIFIERS.register("prideful", Prideful::new);
    public static final StaticModifier<Vengeful> VENGEFUL_STATIC_MODIFIER = MODIFIERS.register("vengeful", Vengeful::new);
    public static final StaticModifier<Grandeur> GRANDEUR_STATIC_MODIFIER = MODIFIERS.register("grandeur", Grandeur::new);
    public static final StaticModifier<Yaoforging> Yaoforging = MODIFIERS.register("yao_forging", Yaoforging::new);
    public static final StaticModifier<dragon_angry> dragon_angryStaticModifier = MODIFIERS.register("dragon_angry", dragon_angry::new);
    public static final StaticModifier<dread> dreadStaticModifier = MODIFIERS.register("dread", dread::new);
    public static final StaticModifier<mundane> mundaneStaticModifier = MODIFIERS.register("mundane", mundane::new);
    public static final StaticModifier<bone> boneStaticModifier = MODIFIERS.register("bone", bone::new);
    public static final StaticModifier<spectral> spectralStaticModifier = MODIFIERS.register("spectral", spectral::new);
    public static final StaticModifier<happy_thing> happy_thing = MODIFIERS.register("happy_thing", happy_thing::new);
    public static final StaticModifier<dread_armor> dreadArmorStaticModifier = MODIFIERS.register("dread_armor", dread_armor::new);
    public static final StaticModifier<eat> eatStaticModifier = MODIFIERS.register("eat", eat::new);
    public static final StaticModifier<kira> KIRA_STATIC_MODIFIER = MODIFIERS.register("kira", kira::new);
    public static final StaticModifier<sky_always> SKY_ALWAYS_STATIC_MODIFIER = MODIFIERS.register("sky_always", sky_always::new);
    public static final StaticModifier<knight_credo> KNIGHT_CREDO_STATIC_MODIFIER = MODIFIERS.register("knight_credo", knight_credo::new);
    public static final StaticModifier<knight_credo_armor> KNIGHT_CREDO_ARMOR_STATIC_MODIFIER = MODIFIERS.register("knight_credo_armor", knight_credo_armor::new);
    public static final StaticModifier<MaoGun> MAO_GUN_STATIC_MODIFIER = MODIFIERS.register("mao_gun", MaoGun::new);
    public static final StaticModifier<Darkwar> Darkwar = MODIFIERS.register("dark_war", Darkwar::new);
    public static final StaticModifier<Blackshield> Blackshield = MODIFIERS.register("black_shield", Blackshield::new);
    public static final StaticModifier<Dirtydeedsdonedirtcheap> Dirtydeedsdonedirtcheap = MODIFIERS.register("dirty_deeds_done_dirt_cheap", Dirtydeedsdonedirtcheap::new);
    public static final StaticModifier<Brokensky> BROKENSKY_STATIC_MODIFIER = MODIFIERS.register("broken_sky", Brokensky::new);
    public static final StaticModifier<bloody_mary> bloodyMaryStaticModifier = MODIFIERS.register("bloody_mary", bloody_mary::new);
    public static final StaticModifier<wound_heal> wound_heal = MODIFIERS.register("wound_heal", wound_heal::new);
    public static final StaticModifier<Currents> CURRENTS_STATIC_MODIFIER = MODIFIERS.register("currents", Currents::new);
    public static final StaticModifier<myrmex> myrmexStaticModifier = MODIFIERS.register("myrmex", myrmex::new);
    public static final StaticModifier<Etherite> etheriteStaticModifier = MODIFIERS.register("etherite", Etherite::new);
    public static final StaticModifier<EchoiumArmor> getEchoiumArmorStaticModifier = MODIFIERS.register("etherite_armor", EchoiumArmor::new);
    public static final StaticModifier<EtheriteAll> etheriteAllStaticModifier = MODIFIERS.register("etherite_all", EtheriteAll::new);
    public static final StaticModifier<Echoium> echoiumStaticModifier = MODIFIERS.register("echoium", Echoium::new);
    public static final StaticModifier<EchoiumArmor> echoiumArmorStaticModifier = MODIFIERS.register("echoium_armor", EchoiumArmor::new);
    public static final StaticModifier<flying_knockback> flyingKnockbackStaticModifier = MODIFIERS.register("flying_knockback", flying_knockback::new);
    public static final StaticModifier<flying_knockback_armor> flyingKnockbackArmorStaticModifier = MODIFIERS.register("flying_knockback_armor", flying_knockback_armor::new);
    public static final StaticModifier<soul_forge> soulForgeStaticModifier = MODIFIERS.register("soul_forge", soul_forge::new);
    public static final StaticModifier<soul_forge_armor> soulForgeArmorStaticModifier = MODIFIERS.register("soul_forge_armor", soul_forge_armor::new);
    public static final StaticModifier<fire_armor> fireArmorStaticModifier = MODIFIERS.register("fire_armor", fire_armor::new);
    public static final StaticModifier<Shuaguai> shuaguaiStaticModifier = MODIFIERS.register("shuaguai", Shuaguai::new);
    public static final StaticModifier<Vice> viceStaticModifier = MODIFIERS.register("vice", Vice::new);
    public static final StaticModifier<Runriot> runriotStaticModifier = MODIFIERS.register("run_riot", Runriot::new);
    public static final StaticModifier<Runriotarmor> runriotarmorStaticModifier = MODIFIERS.register("run_riot_armor", Runriotarmor::new);
    public static final StaticModifier<Waveagain> waveagainStaticModifier = MODIFIERS.register("wave_again", Waveagain::new);
    public static final StaticModifier<Waveagainarmor> waveagainarmorStaticModifier = MODIFIERS.register("wave_again_armor", Waveagainarmor::new);
    public static final StaticModifier<Endspeed> endspeedStaticModifier = MODIFIERS.register("endspeed", Endspeed::new);
    public static final StaticModifier<ZeroHand> zeroHandStaticModifier = MODIFIERS.register("zero_hand", ZeroHand::new);
    public static final StaticModifier<Principleofsufficientreason> principleofsufficientreasonStaticModifier = MODIFIERS.register("principle_of_sufficient_reason", Principleofsufficientreason::new);
    public static final StaticModifier<ancient_tinker> ancientTinkerStaticModifier = MODIFIERS.register("ancient_tinker", ancient_tinker::new);
}
