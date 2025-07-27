package com.mogumogumo.mushroom_concept.register;

import com.mogumogumo.mushroom_concept.items.enderarrow;
import com.mogumogumo.mushroom_concept.mushroom_concept;
import com.mogumogumo.mushroom_concept.tool.MushCrToolDefinitions;
import com.mogumogumo.mushroom_concept.tool.tinkertool.Maogun;
import com.mogumogumo.mushroom_concept.tool.tinkertool.Nineringlargesword;
import com.mogumogumo.mushroom_concept.tool.tinkertool.shroomite_zhua;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableCrossbowItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.ToolDefinitions;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.mogumogumo.mushroom_concept.mushroom_concept.MODID;

public class Mushitems {
    public static final ItemDeferredRegisterExtension OTHER_ITEM = new ItemDeferredRegisterExtension(MODID);
    public static final DeferredRegister<Item> ITEM=DeferredRegister.create(Registries.ITEM,MODID);
    protected static final ItemDeferredRegisterExtension ModifiableItem = new ItemDeferredRegisterExtension(mushroom_concept.MODID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "mushroom_concept");
    private static final Item.Properties TOOL = (new Item.Properties()).stacksTo(1);
    private static final Item.Properties PART = (new Item.Properties()).stacksTo(64);
    private static final Item.Properties CASTS = (new Item.Properties()).stacksTo(64);

    public static List<RegistryObject<Item>> commonItem =new ArrayList<>(List.of());
    public static List<RegistryObject<Item>> toolsAndParts =new ArrayList<>(List.of());

    public static RegistryObject<Item> registerCommonMaterials(DeferredRegister<Item> register,String name, Supplier<? extends Item> sup){
        RegistryObject<Item> object = register.register(name,sup);
        commonItem.add(object);
        return object;
    }

    public static final Supplier<Item> blood_ball=registerCommonMaterials(ITEM,"blood_ball",()->new Item(new Item.Properties().food(MushFood.blood_ball)));
    public static final Supplier<Item> blood_steel=registerCommonMaterials(ITEM,"blood_steel",()->new Item(new Item.Properties()));
    public static final Supplier<Item> gaden=registerCommonMaterials(ITEM,"gaden",()->new Item(new Item.Properties()));
    public static final Supplier<Item> kira_knight_slime=registerCommonMaterials(ITEM,"kira_knight_slime",()->new Item(new Item.Properties()));
    public static final Supplier<Item> chariot_slime=registerCommonMaterials(ITEM,"chariot_slime",()->new Item(new Item.Properties()));
    public static final Supplier<Item> rat_reinforcement=registerCommonMaterials(ITEM,"rat_reinforcement",()->new Item(new Item.Properties()));
    public static final Supplier<Item> chimera_beta=registerCommonMaterials(ITEM,"chimera_beta",()->new Mushnewitem(new Item.Properties()));


    public static final RegistryObject<Item> end_arrow = ITEMS.register("end_arrow", ()->new enderarrow(new Item.Properties()));

    public static final RegistryObject<Item> black_potato = ITEMS.register("black_potato", () -> new Item(new Item.Properties()));

    public static final ItemObject<ToolPartItem> shield_thing = OTHER_ITEM.register("shield_thing", () -> new ToolPartItem(PART, PlatingMaterialStats.SHIELD.getId()));
    public static final ItemObject<ToolPartItem> entity_box = OTHER_ITEM.register("entity_box", () -> new ToolPartItem(PART, StatlessMaterialStats.BINDING.getIdentifier()));

    public static final CastItemObject shield_thing_cast = OTHER_ITEM.registerCast(shield_thing, CASTS);

    public static final RegistryObject<ModifiableItem> shroomitezhua = ITEM.register("shroomitezhua", () -> new shroomite_zhua(TOOL, MushCrToolDefinitions.Shroom));
    public static final RegistryObject<ModifiableCrossbowItem> mao_gun = ITEM.register("mao_gun", () -> new Maogun(TOOL, MushCrToolDefinitions.MAOGANG));
    public static final RegistryObject<ModifiableItem> nine_ring_largesword = ITEM.register("nine_ring_largesword", () -> new Nineringlargesword(TOOL, MushCrToolDefinitions.NINERING));
    public Mushitems(){
    }

    public static void register(IEventBus bus){
        ITEM.register(bus);
        OTHER_ITEM.register(bus);
        ModifiableItem.register(bus);
    }
}
