package com.mogumogumo.mushroom_concept;

import com.mogumogumo.mushroom_concept.client.RenderEnderarrow;
import com.mogumogumo.mushroom_concept.client.RenderTinkerShuriken;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.ClientEventBase;
import slimeknights.tconstruct.gadgets.client.FancyItemFrameRenderer;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = mushroom_concept.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkerTransplantUtilsClientEvent extends ClientEventBase {

    @SubscribeEvent
    static void registerModels(ModelEvent.RegisterAdditional event) {
        FancyItemFrameRenderer.LOCATIONS_MODEL.values().forEach(event::register);
        FancyItemFrameRenderer.LOCATIONS_MODEL_MAP.values().forEach(event::register);
    }

    @SubscribeEvent
    static void registerLayers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MushCutil.tinkerShurikenEntity.get(), RenderTinkerShuriken::new);
        event.registerEntityRenderer(MushCutil.ender_arrow.get(), RenderEnderarrow::new);
    }
}