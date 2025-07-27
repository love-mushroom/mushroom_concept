package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.entities.EnderArrow;
import com.mogumogumo.mushroom_concept.extend.superclass.BaseModifier;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import com.mogumogumo.mushroom_concept.register.Mushitems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.client.ResourceColorManager;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.module.ModuleHookMap.Builder;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.item.CrystalshotItem;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class Endspeed extends BattleModifier  {

    public int getPriority() {
        return 60;
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow1, ModDataNBT modDataNBT, boolean primary) {
        if (projectile instanceof AbstractArrow arrow&&shooter instanceof Player player&&primary) {
            EnderArrow seekerArrow=new EnderArrow(projectile.level(),shooter);
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
