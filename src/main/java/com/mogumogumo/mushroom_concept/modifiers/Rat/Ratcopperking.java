package com.mogumogumo.mushroom_concept.modifiers.Rat;

import com.github.L_Ender.cataclysm.entity.projectile.Water_Spear_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.alexthe666.rats.registry.RatsEntityRegistry;
import com.github.alexthe666.rats.server.entity.rat.TamedRat;
import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Ratcopperking extends BattleModifier {
    public float staticdamage(IToolStackView tool, int level, ToolAttackContext context, LivingEntity attacker, LivingEntity livingTarget, float baseDamage, float damage) {
        if (attacker instanceof Player player) {
            return damage + 6 ;
        }
        return damage;
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Level world = context.getAttacker().getCommandSenderWorld();
        if (context.getLivingTarget() != null) {
            LivingEntity entity = context.getLivingTarget();
            spawnAnimal(world, entity, context.getAttacker());
        }
    }
    public void spawnAnimal(Level world, Entity entity, Entity summoner) {
        TamedRat tamedRat = RatsEntityRegistry.TAMED_RAT.get().create(world);
        if (tamedRat != null) {
            world.addFreshEntity(tamedRat);
        }
        if (tamedRat != null) {
            tamedRat.moveTo(entity.getX(), entity.getY(), entity.getZ());
        }

        if (tamedRat != null) {
            tamedRat.spawnAnim();
        }
        summoner.gameEvent(GameEvent.ENTITY_PLACE, summoner);
    }
    public boolean havenolevel() {
        return true;
    }
}
