package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nonnull;

public class spectral extends ArmorModifier {

    public boolean havenolevel() {
        return true;
    }

    public void LivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() != null) {
            LivingEntity entity = event.getEntity();
            int b = RANDOM.nextInt(8);
            if (entity instanceof Player player && ModifierLevel.getTotalArmorModifierlevel(entity, this.getId()) > 0) {
                if (b == 0) {
                    event.getEntity().invulnerableTime = 80;
                    event.setCanceled(true);
                }
            }
        }
    }

    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!world.isClientSide && holder.tickCount % 20 == 0) {
            if (holder instanceof Player player) {
                if (ModifierLevel.getTotalArmorModifierlevel(player, this.getId()) > 0) {
                    player.heal(0.5f);
                }
            }
        }
    }
}
