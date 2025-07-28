package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.entities.EnderArrow;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import javax.annotation.Nullable;

public class Endspeed extends BattleModifier {

    public int getPriority() {
        return 60;
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow1, ModDataNBT modDataNBT, boolean primary) {
        if (projectile instanceof AbstractArrow arrow && shooter instanceof Player player && primary) {
            EnderArrow seekerArrow = new EnderArrow(projectile.level(), shooter);
            ModifierNBT modifiers = tool.getModifiers();
            seekerArrow.setBaseDamage((float) (arrow.getBaseDamage()));
            seekerArrow.getCapability(EntityModifierCapability.CAPABILITY).ifPresent(cap -> cap.setModifiers(modifiers));
            seekerArrow.setPos(arrow.getX(), arrow.getY(), arrow.getZ());
            seekerArrow.setOwner(player);
            seekerArrow.setDeltaMovement(arrow.getDeltaMovement());
            player.level().addFreshEntity(seekerArrow);
        }
        if (arrow1 != null) {
            arrow1.discard();
        }
    }
}
