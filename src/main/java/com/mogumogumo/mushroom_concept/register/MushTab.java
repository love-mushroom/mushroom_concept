package com.mogumogumo.mushroom_concept.register;

import com.mogumogumo.mushroom_concept.MushCutil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.mogumogumo.mushroom_concept.Mushroom_Concept.MODID;

public class MushTab {
    public static final DeferredRegister<CreativeModeTab> creative_mode_tab = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final String OrdinaryTinkerMaterials = "tab.mushroom_concept.materials";
    public static final String OrdinaryTinkerTools = "tab.mushroom_concept.tools";
    public static final Supplier<CreativeModeTab> materials = creative_mode_tab.register("materials", () -> CreativeModeTab.builder()
            //槽位位置
            .withTabsBefore(CreativeModeTabs.COMBAT)
            //物品栏名称
            .title(Component.translatable(OrdinaryTinkerMaterials))
            //图标
            .icon(Mushitems.blood_ball.get()::getDefaultInstance)
            .displayItems((itemDisplayParameters, output) -> {
                for (RegistryObject<Item> itemsDeferredRegister : Mushitems.commonItem) {
                    if (itemsDeferredRegister.isPresent()) {
                        output.accept(itemsDeferredRegister.get());
                    }
                }
                //这个物品栏当中包含的物品
            })
            .build()
    );
    //类型不一的几个
    public static final Supplier<CreativeModeTab> toolsAndParts = creative_mode_tab.register("tools_and_parts", () -> CreativeModeTab.builder()
            //槽位位置
            .withTabsBefore(CreativeModeTabs.COMBAT)
            //物品栏名称
            .title(Component.translatable(OrdinaryTinkerTools))
            //图标
            .icon(Mushitems.blood_ball.get()::getDefaultInstance)
            .displayItems(MushTab::addToolItems)
            .build()
    );

    private static void acceptTool(Consumer<ItemStack> output, Supplier<? extends IModifiable> tool) {
        ToolBuildHandler.addVariants(output, tool.get(), "");
    }

    private static void acceptPart(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
        item.get().addVariants(output, "");
    }

    private static void accept(CreativeModeTab.Output output, Function<CastItemObject, ItemLike> getter, CastItemObject cast) {
        output.accept(getter.apply(cast));
    }

    private static void acceptTools(Consumer<ItemStack> output, EnumObject<?, ? extends IModifiable> tools) {
        tools.forEach(tool -> ToolBuildHandler.addVariants(output, tool, ""));
    }

    private static void addCasts(CreativeModeTab.Output output, Function<CastItemObject, ItemLike> getter) {
        accept(output, getter, Mushitems.shield_thing_cast);
    }

    private static void addToolItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
        Objects.requireNonNull(tab);
        Consumer<ItemStack> output = tab::accept;
        acceptPart(output, Mushitems.shield_thing);
        acceptPart(output, Mushitems.entity_box);
        acceptTool(output, Mushitems.shroomitezhua);
        acceptTool(output, MushCutil.SHURIKEN);
        acceptTool(output, Mushitems.nine_ring_largesword);
        addCasts(tab, CastItemObject::get);
        addCasts(tab, CastItemObject::getSand);
        addCasts(tab, CastItemObject::getRedSand);
    }

    public static void register(IEventBus bus) {
        creative_mode_tab.register(bus);
    }
}
