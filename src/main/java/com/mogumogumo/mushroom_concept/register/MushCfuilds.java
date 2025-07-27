package com.mogumogumo.mushroom_concept.register;

import com.mogumogumo.mushroom_concept.mushroom_concept;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.mantle.registration.object.FluidObject;

import static com.mogumogumo.mushroom_concept.mushroom_concept.MODID;

public class MushCfuilds {
    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MODID);
    public static final FluidObject<ForgeFlowingFluid> molten_black_steel = register("molten_black_steel", 1430);
    public static final FluidObject<ForgeFlowingFluid> molten_cursium = register("molten_cursium", 1430);
    public static final FluidObject<ForgeFlowingFluid> molten_gnitium = register("molten_ignitium", 1430);
    public static final FluidObject<ForgeFlowingFluid> blood = register("molten_blood", 20);
    public static final FluidObject<ForgeFlowingFluid> molten_chariot_slime = register("molten_chariot_slime", 20);
    public static final FluidObject<ForgeFlowingFluid> molten_gem_of_ratlantis = register("molten_gem_of_ratlantis", 1840);
    public static final FluidObject<ForgeFlowingFluid> molten_oratchalcum = register("molten_oratchalcum", 1840);
    public static final FluidObject<ForgeFlowingFluid> molten_coal = register("molten_coal", 20);
    public static final FluidObject<ForgeFlowingFluid> universal_blood = register("universal_blood", 20);
    public static final FluidObject<ForgeFlowingFluid> blood_steel = register("molten_blood_steel", 1200);

    public MushCfuilds() {
    }

    private static FluidType.Properties hot(String name) {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000).descriptionId(mushroom_concept.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.ENDER_DRAGON_HURT).sound(SoundActions.BUCKET_EMPTY, SoundEvents.ENDER_DRAGON_DEATH);
    }

    private static FluidType.Properties cool(String name) {
        return cool().descriptionId(mushroom_concept.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.ENDER_DRAGON_HURT).sound(SoundActions.BUCKET_EMPTY, SoundEvents.ENDER_DRAGON_HURT);
    }

    private static FluidType.Properties cool() {
        return FluidType.Properties.create().sound(SoundActions.BUCKET_FILL, SoundEvents.ENDER_DRAGON_HURT).sound(SoundActions.BUCKET_EMPTY, SoundEvents.ENDER_DRAGON_HURT);
    }

    private static FlowingFluidObject<ForgeFlowingFluid> register(String name, int temp) {
        return FLUIDS.register(name).type(hot(name).temperature(temp).lightLevel(12)).bucket().flowing();
    }

}
