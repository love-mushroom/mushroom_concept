package com.mogumogumo.mushroom_concept.modifiers;

import com.mogumogumo.mushroom_concept.extend.superclass.BattleModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nonnull;
import java.util.UUID;
import java.util.function.BiConsumer;

public class Blackshield extends BattleModifier implements MeleeHitModifierHook {


    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        Level world = context.getAttacker().getCommandSenderWorld();
        if (context != null) {
            LivingEntity entity = context.getLivingTarget();
            spawnAnimal(world, entity, context.getAttacker());
        }
    }

    public void spawnAnimal(Level world, Entity entity, Entity summoner) {
        WitherSkeleton witherSkeleton = EntityType.WITHER_SKELETON.create(world);
        world.addFreshEntity(witherSkeleton);
        witherSkeleton.moveTo(entity.getX(), entity.getY(), entity.getZ());

        witherSkeleton.spawnAnim();
        summoner.gameEvent(GameEvent.ENTITY_PLACE, summoner);
    }

    public boolean havenolevel() {
        return true;
    }

    @Override
    public void onInventoryTick(@Nonnull IToolStackView tool, ModifierEntry modifier, @Nonnull Level world, @Nonnull LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!world.isClientSide) {
            if (holder instanceof Player player) {
                if (player.tickCount % 10 == 0 && player.getAbsorptionAmount() < player.getMaxHealth() * 3) {
                    player.setAbsorptionAmount(player.getAbsorptionAmount() + 2);
                }
            }
        }
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        consumer.accept(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("9bf29a55-81e2-4ba5-957f-596fb2a4082b"), Attributes.MOVEMENT_SPEED.getDescriptionId(), 0.1f, AttributeModifier.Operation.ADDITION));
    }
}
