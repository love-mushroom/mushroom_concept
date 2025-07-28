package com.mogumogumo.mushroom_concept.tool.tinkertool;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolActions;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableCrossbowItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;


public class Maogun extends ModifiableCrossbowItem {
    public Maogun(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
    }

    public static void fireMaogun(IToolStackView tool, Player player, InteractionHand hand, CompoundTag heldAmmo) {
        Level level = player.level();
        if (!level.isClientSide) {
            int damage = 0;
            float velocity = ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.VELOCITY);
            float inaccuracy = ModifierUtil.getInaccuracy(tool, player);
            boolean creative = player.getAbilities().instabuild;
            ItemStack ammo = ItemStack.of(heldAmmo);
            float startAngle = getAngleStart(ammo.getCount());
            int primaryIndex = ammo.getCount() / 2;
            for (int arrowIndex = 0; arrowIndex < ammo.getCount(); arrowIndex++) {
                Projectile projectile;
                float speed;
                Arrow arrow = new Arrow(level, player) {
                    @Override
                    protected void onHitEntity(EntityHitResult pResult) {
                        super.onHitEntity(pResult);
                        if (getOwner() instanceof Player playerOwner) {
                            var stack = playerOwner.getOffhandItem();
                            if (stack.isEmpty()) return;
                            pResult.getEntity().invulnerableTime = 0;
                            ToolAttackUtil.attackEntity(stack, playerOwner, pResult.getEntity());
                        }
                    }
                };
                projectile = arrow;
                arrow.setCritArrow(true);
                arrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
                arrow.setShotFromCrossbow(true);
                speed = 3f;
                damage += 1;
                float baseArrowDamage = (float) (arrow.getBaseDamage() - 2 + tool.getStats().get(ToolStats.PROJECTILE_DAMAGE));
                arrow.setBaseDamage(ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.PROJECTILE_DAMAGE, baseArrowDamage));
                if (creative) {
                    arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }
                Vec3 upVector = player.getUpVector(1.0f);
                float angle = startAngle + (10 * arrowIndex);
                Vector3f targetVector = player.getViewVector(1.0f).toVector3f().rotate((new Quaternionf()).setAngleAxis(angle * Math.PI / 180F, upVector.x, upVector.y, upVector.z));
                projectile.shoot(targetVector.x(), targetVector.y(), targetVector.z(), velocity * speed, inaccuracy);
                ModifierNBT modifiers = tool.getModifiers();
                projectile.getCapability(EntityModifierCapability.CAPABILITY).ifPresent(cap -> cap.setModifiers(modifiers));
                ModDataNBT projectileData = PersistentDataCapability.getOrWarn(projectile);
                for (ModifierEntry entry : modifiers.getModifiers()) {
                    entry.getHook(ModifierHooks.PROJECTILE_LAUNCH).onProjectileLaunch(tool, entry, player, projectile, arrow, projectileData, arrowIndex == primaryIndex);
                }
                level.addFreshEntity(projectile);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, 1);
            }
            tool.getPersistentData().remove(KEY_CROSSBOW_AMMO);
            ToolDamageUtil.damageAnimated(tool, damage, player, hand);
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayer, player.getItemInHand(hand));
                serverPlayer.awardStat(Stats.ITEM_USED.get(tool.getItem()));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack bow = player.getItemInHand(hand);
        ToolStack tool = ToolStack.from(bow);
        if (tool.isBroken()) {
            return InteractionResultHolder.fail(bow);
        }
        boolean sinistral = hand == InteractionHand.MAIN_HAND && tool.getModifierLevel(TinkerModifiers.sinistral.getId()) > 0;
        ModDataNBT persistentData = tool.getPersistentData();
        CompoundTag heldAmmo = persistentData.getCompound(KEY_CROSSBOW_AMMO);
        if (heldAmmo.isEmpty()) {
            if (sinistral && !player.getOffhandItem().isEmpty() && player.isCrouching()) {
                return InteractionResultHolder.pass(bow);
            }
            if (BowAmmoModifierHook.hasAmmo(tool, bow, player, getSupportedHeldProjectiles())) {
                GeneralInteractionModifierHook.startDrawtime(tool, player, 1);
                player.startUsingItem(hand);
                if (!level.isClientSide) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_QUICK_CHARGE_1, SoundSource.PLAYERS, 0.75F, 1.0F);
                }
                return InteractionResultHolder.consume(bow);
            } else if (ModifierUtil.canPerformAction(tool, ToolActions.SHIELD_BLOCK)) {
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(bow);
            } else {
                return InteractionResultHolder.fail(bow);
            }
        }
        if (sinistral) {
            ItemStack offhand = player.getOffhandItem();
            if (!offhand.isEmpty() && !offhand.is(Items.FIREWORK_ROCKET)) {
                return InteractionResultHolder.pass(bow);
            }
            if (ModifierUtil.canPerformAction(tool, ToolActions.SHIELD_BLOCK)) {
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(bow);
            }
        }
        fireMaogun(tool, player, hand, heldAmmo);
        return InteractionResultHolder.consume(bow);
    }
}