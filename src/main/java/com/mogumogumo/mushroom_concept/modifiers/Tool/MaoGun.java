package com.mogumogumo.mushroom_concept.modifiers.Tool;

import com.mogumogumo.mushroom_concept.extend.superclass.MoguModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;

public class MaoGun extends MoguModifier implements ProjectileLaunchModifierHook {

    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, new ModuleHook[]{ModifierHooks.PLANT_HARVEST, ModifierHooks.PROJECTILE_HIT, ModifierHooks.PROJECTILE_LAUNCH, ModifierHooks.BLOCK_HARVEST, ModifierHooks.MELEE_HIT});
    }

    public int getPriority() {
        return 45;
    }


    public void onProjectileHitBlock(@NotNull ModifierNBT modifiers, @NotNull ModDataNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, @NotNull BlockHitResult hit, @Nullable LivingEntity attacker) {
        if (attacker != null && !attacker.isShiftKeyDown()) {
            BlockPos target = hit.getBlockPos().relative(hit.getDirection());
            attacker.teleportTo(target.getX(), target.getY(), target.getZ());
        }
    }

    @Override
    public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
        if (target != null && attacker instanceof Player player) {
            AbstractArrow arrow = (AbstractArrow) projectile;
            IToolStackView offhand = ToolStack.from(player.getItemBySlot(EquipmentSlot.OFFHAND));
            int builder = offhand.getStats().getInt(ToolStats.ATTACK_DAMAGE);
            float a = builder * 0.2f;
            arrow.setBaseDamage(arrow.getBaseDamage() + a);
            return false;
        }
        return false;
    }

}
