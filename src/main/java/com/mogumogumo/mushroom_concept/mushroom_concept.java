package com.mogumogumo.mushroom_concept;

import com.mogumogumo.mushroom_concept.register.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.utils.Util;

import java.util.Random;
@Mod(mushroom_concept.MODID)
public class mushroom_concept {
    public static boolean xiaoyue = ModList.get().isLoaded("tinkers_ingenuity");
    public static boolean Rat = ModList.get().isLoaded("rats");
    public static boolean citadel = ModList.get().isLoaded("citadel");
    public static boolean cataclysm = ModList.get().isLoaded("cataclysm");
    public static final String MODID = "mushroom_concept";
    public mushroom_concept() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MushCmodifiers.MODIFIERS.register(bus);
        MushCfuilds.FLUIDS.register(bus);
        Mushitems.register(bus);
        Mushitems.ITEMS.register(bus);
        MushCutil.ITEMS.register(bus);
        MushCutil.ENTITY_TYPES.register(bus);
        MushCutil.MODIFIERS.register(bus);
        MushTab.creative_mode_tab.register(bus);
        bus.addListener(this::commonSetup);
        if (xiaoyue) {
            MushXunModifier.XunModifier.register(bus);
        }
        if (Rat&&citadel) {
            MushRatModifiers.RatModifier.register(bus);
        }
        if (cataclysm) {
            MushEnderModifiers.ENDER_MODIFIERS.register(bus);
        }
    }
    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(MushCutil::MaterialRegister);
    }

    public static String makeTranslationKey(String base, String name) {
        return Util.makeTranslationKey(base, getResource(name));
    }
    public static MutableComponent makeTranslation(String base, String name) {
        return Component.translatable(makeTranslationKey(base, name));
    }
    public static ResourceLocation getResource(String id) {
        return new ResourceLocation("mushroom_concept", id);
    }

    public static String makeDescriptionId(String type, String name) {
        return type + ".mushroom_concept." + name;
    }
}
