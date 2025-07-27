package com.mogumogumo.mushroom_concept.Mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WitherBoss.class)
public abstract class WitherMixin extends Monster {
    @Shadow public abstract int getAlternativeTarget(int pHead);

    @Shadow public abstract boolean isPowered();

    protected WitherMixin(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @ModifyArg(method = "aiStep",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/wither/WitherBoss;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V"))
    private Vec3 setMove(Vec3 par1){
        Vec3 vec3 = this.getDeltaMovement().multiply(1.0F, 0.6, (double)1.0F);
        if (!this.level().isClientSide && this.getAlternativeTarget(0) > 0) {
            Entity entity = this.level().getEntity(this.getAlternativeTarget(0));
            if (entity != null) {
                double d0 = vec3.y;
                if (this.getY() < entity.getY() || !this.isPowered() && this.getY() < entity.getY() + (double)5.0F) {
                    d0 = Math.max(0.0F, d0);
                    d0 += 0.3 - d0 * (double)0.6F;
                }
                vec3 = new Vec3(vec3.x, d0, vec3.z);
                Vec3 vec31 = new Vec3(entity.getX() - this.getX(), 0.0F, entity.getZ() - this.getZ());
                if (vec31.horizontalDistanceSqr() > (double)9.0F) {
                    Vec3 vec32 = vec31.normalize();
                    vec3 = vec3.add(vec32.x * 0.3 - vec3.x * 0.6, 0.0F, vec32.z * 0.3 - vec3.z * 0.6);
                }
            }
        }
        if(this.getPersistentData().getBoolean("fly_false")){
            this.setNoGravity(false);
            return new Vec3(vec3.x,-1,vec3.z);
        }
        return vec3;
    }
}
