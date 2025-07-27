package com.mogumogumo.mushroom_concept.items.tools;

import com.mogumogumo.mushroom_concept.mushroom_concept;
import com.mogumogumo.mushroom_concept.entities.TinkerShurikenEntity;
import com.mogumogumo.mushroom_concept.library.InterfaceProjectileItem;
import com.mogumogumo.mushroom_concept.MushCutil;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import slimeknights.mantle.util.TranslationHelper;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.item.ModifiableSwordItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = mushroom_concept.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Tinker_Shuriken extends ModifiableSwordItem implements InterfaceProjectileItem {
    
    private StatsNBT stats;

    public static final ToolDefinition SHURIKEN = ToolDefinition
            .create(MushCutil.SHURIKEN);

    public final BiFunction<Level, Player, TinkerShurikenEntity> entity;

    public Tinker_Shuriken(BiFunction<Level, Player, TinkerShurikenEntity> entity) {
        super(new Item.Properties(), SHURIKEN);
        this.entity = entity;
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        // 如果 stats 为 null，则初始化为空
        if (stats == null) {
            stats = StatsNBT.EMPTY;
        }
        // 获取玩家手中的物品堆栈
        ItemStack stack = player.getItemInHand(hand);
        // 从物品堆栈中获取工具视图
        IToolStackView tool = ToolStack.from(stack);
        // 获取攻击速度
        double attackSpeed = (double) tool.getStats().get(ToolStats.ATTACK_SPEED);
        // 计算冷却时间
        double CoolDownTime = 20 - attackSpeed * 2 ;

        // 如果不在客户端且工具未损坏
        if (!world.isClientSide() &&!tool.isBroken()) {
            // 为工具添加冷却时间
            player.getCooldowns().addCooldown(stack.getItem(), (int) CoolDownTime);
            // 减少工具造成 1 点耐久
            ToolDamageUtil.damageAnimated(tool, 1, player);
            // 在玩家位置播放投掷手里剑的声音
            world.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.SHURIKEN_THROW.getSound(), SoundSource.NEUTRAL,
                    0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            // 创建手里剑实体
            TinkerShurikenEntity projectile = (TinkerShurikenEntity) createProjectile(stack, world, player);
            // 为手里剑实体提供信息
            supplyInfoToProjectile(projectile, stack, world, player);
            // 设置手里剑实体的物品（只有这个才能使手里剑实体显示和当前手里剑工具一样的材质）
            projectile.setItem(stack);
            // 发射手里剑
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            // 将手里剑实体添加到世界中
            world.addFreshEntity(projectile);
        }
        // 返回交互结果
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        TranslationHelper.addOptionalTooltip(stack, tooltip);
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @Override
    public Projectile createProjectile(ItemStack shuriken, Level world, LivingEntity shooter) {
        return new TinkerShurikenEntity(world, shooter);
    }

    @Override
    public void supplyInfoToProjectile(Projectile projectile, ItemStack shuriken, Level world, LivingEntity shooter) {
        TinkerShurikenEntity newProjectile = (TinkerShurikenEntity) projectile;
        newProjectile.setTool(shuriken);
    }
}