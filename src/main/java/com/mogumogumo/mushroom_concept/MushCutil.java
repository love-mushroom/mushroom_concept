package com.mogumogumo.mushroom_concept;

import com.mogumogumo.mushroom_concept.entities.EnderArrow;
import com.mogumogumo.mushroom_concept.entities.TinkerShurikenEntity;
import com.mogumogumo.mushroom_concept.items.tools.Tinker_Shuriken;
import com.mogumogumo.mushroom_concept.stats.TinkerTransplantStatlessMaterialStats;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.TinkerModule;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.tools.TinkerToolParts;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.mogumogumo.mushroom_concept.mushroom_concept.MODID;

@SuppressWarnings("unused")
public class MushCutil extends TinkerModule {
    public MushCutil(){}

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(MODID);
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static MaterialId createMaterial(String name) {
        return new MaterialId(new ResourceLocation(MODID, name));
    }

    public static final Item.Properties PARTS_PROPS = new Item.Properties().stacksTo(64);

    public static final ItemObject<Tinker_Shuriken> SHURIKEN = ITEMS.register("shuriken", () -> new Tinker_Shuriken(TinkerShurikenEntity::new));

    public static final RegistryObject<EntityType<TinkerShurikenEntity>> tinkerShurikenEntity = ENTITY_TYPES.register("tinker_shuriken", () ->
            EntityType.Builder.<TinkerShurikenEntity> of (TinkerShurikenEntity::new, MobCategory.MISC)
                    .sized(1F, 1F)
                    .setTrackingRange(4)
                    .setUpdateInterval(10)
                    .setCustomClientFactory((spawnEntity, world) -> new TinkerShurikenEntity(MushCutil.tinkerShurikenEntity.get(), world))
                    .setShouldReceiveVelocityUpdates(true)
                    .build(getResource("tinker_shuriken").toString())
    );

    public static final RegistryObject<EntityType<EnderArrow>> ender_arrow = ENTITY_TYPES.register(
            "ender_arrow",
            () -> EntityType.Builder.<EnderArrow>of(EnderArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(getResource("ender_arrow").toString())
    );

    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static void MaterialRegister() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(TinkerTransplantStatlessMaterialStats.ARROW_FLETCHING.getType(), new MaterialStatsId(MushCutil.getResource("arrow_fletching")));
    }


}