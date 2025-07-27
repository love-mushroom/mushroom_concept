package com.mogumogumo.mushroom_concept.Mixin;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlyingMoveControl.class)
public abstract class FlyingMoveControlMixin extends MoveControl {
    public FlyingMoveControlMixin(Mob pMob) {
        super(pMob);
    }

    @Inject(method = "tick",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;setNoGravity(Z)V",ordinal = 0), cancellable = true)
    private void set(CallbackInfo ci){
        if(this.mob.getPersistentData().getBoolean("fly_false")){
            this.mob.setNoGravity(false);
            this.mob.setYya(0.0F);
            this.mob.setZza(0.0F);
            ci.cancel();
        }
    }
}
