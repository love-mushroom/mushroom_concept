package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class Vice extends BattleModifier {

    public boolean havenolevel() {
        return true;
    }

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity entity, int index, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (entity instanceof Player player) {
            if (ModifierLevel.EquipHasModifierlevel(player, this.getId())) {
                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();
                int a = 12;
                List<Mob> mobList = player.level().getEntitiesOfClass(Mob.class, new AABB(x + 4 * a, y + 4 * a, z + 4 * a, x - 4 * a, y - 4 * a, z - 4 * a));
                for (Mob mob : mobList) {
                    if (mob != null) {
                        mob.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2, true, true));
                        break;
                    }
                }
            }
        }
    }
}

