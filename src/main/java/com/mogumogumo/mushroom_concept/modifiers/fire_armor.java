package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.ArmorModifier;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class fire_armor extends ArmorModifier {
    public void LivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() != null) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player && ModifierLevel.getTotalArmorModifierlevel(entity, this.getId()) > 0) {
                if (player.getHealth() <= player.getMaxHealth() / 2) {
                    player.heal(player.getMaxHealth() * 0.07f);
                }
            }
        }
    }
}
