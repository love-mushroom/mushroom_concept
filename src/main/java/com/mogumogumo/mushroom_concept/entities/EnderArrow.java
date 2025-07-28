package com.mogumogumo.mushroom_concept.entities;

import com.mogumogumo.mushroom_concept.MushCutil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

public class EnderArrow extends AbstractArrow {
    public EnderArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public EnderArrow(Level pLevel, LivingEntity pShooter) {
        this(MushCutil.ender_arrow.get(), pLevel);
        this.setPos(pShooter.getX(), pShooter.getEyeY() - 0.1, pShooter.getZ());
        this.setYRot(pShooter.getYRot());
        this.setXRot(pShooter.getXRot());
        this.shootFromRotation(pShooter, pShooter.getXRot(), pShooter.getYRot(), 0, 3, 1);
        setOwner(pShooter);
        Vec3 move = this.getDeltaMovement();
        this.setDeltaMovement(move.scale(5F));
        this.pickup = Pickup.CREATIVE_ONLY;
    }

    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(Items.ENDER_PEARL);
    }

    @Override
    public void tick() {
        super.tick();
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean flag = this.isNoPhysics();
        this.setNoGravity(true);
        this.setNoPhysics(true);
        Vec3 vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;
        for (int i = 0; i < 4; ++i) {
            this.level().addParticle(ParticleTypes.CRIT, this.getX() + d5 * (double) i / 4.0D, this.getY() + d6 * (double) i / 4.0D, this.getZ() + d1 * (double) i / 4.0D, -d5, -d6 + 0.2D, -d1);
        }
        if (this.tickCount > 400) {
            this.discard();
        }
        if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !flag) {
            switch (ForgeEventFactory.onProjectileImpactResult(this, hitresult)) {
                case SKIP_ENTITY:
                    if (hitresult.getType() != HitResult.Type.ENTITY) {
                        this.onHit(hitresult);
                        this.hasImpulse = true;
                        break;
                    }
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        if (this.level().isClientSide()) return;
        Entity target = pResult.getEntity();
        float damage = (float) Mth.clamp(this.getDeltaMovement().length() * this.getBaseDamage(), 0.0D, Float.MAX_VALUE);
        target.hurt(this.damageSources().arrow(this, this.getOwner()), damage);
    }
}
