package com.mogumogumo.mushroom_concept.entities;

import com.mogumogumo.mushroom_concept.MushCutil;
import com.mogumogumo.mushroom_concept.library.ProjectileInterface.InterfaceCriticalProjectile;
import com.mogumogumo.mushroom_concept.library.ProjectileInterface.InterfaceDamageProjectile;
import com.mogumogumo.mushroom_concept.library.utils.ThrowableProjectileAttackUtil;
import com.mogumogumo.mushroom_concept.register.MushCmodifiers;
import com.mogumogumo.mushroom_concept.utils.ModifierLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;

/**
 * 代表一个在游戏世界中飞行并可以对实体造成伤害的手里剑实体。
 * 这个实体实现了 IEntityAdditionalSpawnData 接口，允许它在网络上发送和接收额外的数据。
 * 此外，它还实现了 InterfaceDamageProjectile 和 InterfaceCriticalProjectile 接口，
 * 这意味着它可以造成伤害并且可能具有暴击效果。
 */
public class TinkerShurikenEntity extends ThrowableItemProjectile implements IEntityAdditionalSpawnData, InterfaceDamageProjectile, InterfaceCriticalProjectile {

    /**
     * 是否在地面上。
     */
    protected boolean inGround;
    /**
     * 在地面上的时间。
     */
    protected int inGroundTime;
    /**
     * 手里剑的物品堆栈。
     */
    private ItemStack shurikenStack;
    /**
     * 手里剑的工具堆栈。
     */
    private ToolStack toolStack;
    /**
     * 手里剑的统计数据。
     */
    private StatsNBT stats;
    /**
     * 额外的伤害加成。
     */
    private float bonusDamage = 0;
    /**
     * 是否为暴击状态。
     */
    private boolean critical = false;
    /**
     * 已过去的刻数。
     */
    private int numTicks = 0;

    /**
     * 上一个块状态。
     */
    @Nullable
    private BlockState lastState;

    /**
     * 使用给定的生物在给定的世界中创建一个新的手里剑实体。
     *
     * @param world  手里剑将存在的世界。
     * @param player 投掷手里剑的生物。
     */
    public TinkerShurikenEntity(Level world, LivingEntity player) {
        super(MushCutil.tinkerShurikenEntity.get(), player, world);
    }

    /**
     * 使用给定的实体类型和世界创建一个新的手里剑实体。
     *
     * @param type    手里剑实体的类型。
     * @param worldIn 手里剑将存在的世界。
     */
    public TinkerShurikenEntity(EntityType<? extends TinkerShurikenEntity> type, Level worldIn) {
        super(type, worldIn);

        shurikenStack = null;
        toolStack = null;
        stats = StatsNBT.EMPTY;
    }

    /**
     * 设置手里剑的工具。
     *
     * @param tool 要设置的工具堆栈。
     */
    public void setTool(ItemStack tool) {
        if (tool != null) {
            shurikenStack = tool.copy();
            toolStack = ToolStack.from(tool);
            stats = toolStack.getStats();
        } else {
            shurikenStack = null;
            toolStack = null;
            stats = StatsNBT.EMPTY;
        }
    }

    /**
     * 判断手里剑是否应该落下。
     * 手里剑应该落下的条件是：它在地面上，并且它的周围没有碰撞。
     *
     * @return 如果手里剑应该落下，则返回 true；否则返回 false。
     */
    private boolean shouldFall() {
        // 检查手里剑是否在地面上，并且它的周围没有碰撞
        return this.inGround && this.level().noCollision((new AABB(this.position(), this.position())).inflate(0.06D));
    }


    /**
     * 开始手里剑的落下过程。
     * 当手里剑位于地面上且满足落下条件时，此方法会被调用。
     * 它会重置手里剑的在地面状态，随机调整其移动向量，并重置刻数计数器。
     */
    private void startFalling() {
        // 设置手里剑为不在地面上
        this.inGround = false;
        // 获取手里剑当前的移动向量
        Vec3 vec3 = this.getDeltaMovement();
        // 随机调整手里剑的移动向量，使其在x、y、z方向上有轻微的随机偏移
        this.setDeltaMovement(vec3.multiply((double) (this.random.nextFloat() * 0.2F), (double) (this.random.nextFloat() * 0.2F), (double) (this.random.nextFloat() * 0.2F)));
        // 重置已过去的刻数计数器
        this.numTicks = 0;
    }


    /**
     * 处理手里剑的消失逻辑。
     */
    protected void tickDespawn() {
        ++this.numTicks;
        if (this.numTicks >= 1200) {
            this.discard();
        }
    }

    /**
     * 返回手里剑是否在地面上。
     *
     * @return 如果手里剑在地面上，则返回 true；否则返回 false。
     */
    public boolean isInGround() {
        return inGround;
    }

    /**
     * 将额外的数据添加到手里剑的 NBT 标签中，以便在网络上发送。
     *
     * @param tag 要添加数据的 NBT 标签。
     */
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("Tool", shurikenStack.serializeNBT());
        tag.putFloat("BonusDamage", bonusDamage);
        tag.putBoolean("Critical", critical);
        tag.putInt("TickCount", numTicks);
    }

    /**
     * 从 NBT 标签中读取额外的数据，这些数据是在网络上接收到的。
     *
     * @param tag 包含额外数据的 NBT 标签。
     */
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setTool(ItemStack.of(tag.getCompound("Tool")));
        bonusDamage = tag.getFloat("BonusDamage");
        critical = tag.getBoolean("Critical");
        numTicks = tag.getInt("TickCount");
    }

    /**
     * 每刻更新手里剑的状态。
     */
    @Override
    public void tick() {
        // 调用父类的tick方法
        super.tick();

        // 获取当前手里剑的方块位置
        BlockPos blockPos = this.blockPosition();
        // 获取当前位置的方块状态
        BlockState blockState = this.level().getBlockState(blockPos);

        // 如果方块状态不是空气
        if (!blockState.isAir()) {
            // 获取方块的碰撞形状
            VoxelShape voxelShape = blockState.getCollisionShape(this.level(), blockPos);
            // 如果碰撞形状不为空
            if (!voxelShape.isEmpty()) {
                // 获取手里剑的位置向量
                Vec3 vec3 = this.position();

                // 遍历碰撞形状的AABB包围盒
                for (AABB aabb : voxelShape.toAabbs()) {
                    // 如果AABB包围盒包含手里剑的位置
                    if (aabb.move(blockPos).contains(vec3)) {
                        // 设置手里剑为在地面上
                        this.inGround = true;
                        // 跳出循环
                        break;
                    }
                }
            }
        }

        // 如果手里剑在地面上
        if (this.inGround) {
            // 如果手里剑不应该落下
            if (!this.shouldFall()) {
                // 设置手里剑的移动向量为零
                this.setDeltaMovement(Vec3.ZERO);
            }
            // 如果上一个方块状态不等于当前方块状态，并且手里剑应该落下
            else if (this.lastState != blockState && this.shouldFall()) {
                // 开始手里剑的落下过程
                this.startFalling();
            }

            // 如果不是客户端
            if (!this.level().isClientSide) {
                // 处理手里剑的消失逻辑
                this.tickDespawn();
            }
        }
        // 如果手里剑不在地面上
        else {
            // 增加已过去的刻数
            numTicks++;
            // 重置在地面上的时间
            this.inGroundTime = 0;
        }
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        // 调用父类的onHitEntity方法
        super.onHitEntity(result);
        // 获取被击中的实体
        Entity entity = result.getEntity();
        // 获取手里剑的所有者
        Entity player = this.getOwner();
        // 如果所有者存在且被击中的实体是一个生物
        if (player != null && entity instanceof LivingEntity) {
            // 设置生物的最后一个伤害来源为被击中的实体
            ((LivingEntity) player).setLastHurtMob(entity);
        }

        // 判断被击中的实体是否是末影人
        boolean flag = entity.getType() == EntityType.ENDERMAN;

        // 如果手里剑着火并且被击中的实体不是末影人
        if (this.isOnFire() && !flag) {
            // 使被击中的实体着火5秒
            entity.setSecondsOnFire(5);
        }

        // 获取手里剑的生物所有者
        LivingEntity livingOwner = getOwner() instanceof LivingEntity ? (LivingEntity) getOwner() : null;

        // 如果是客户端或者攻击实体成功
        if (this.level().isClientSide || ThrowableProjectileAttackUtil.attackEntity(shurikenStack.getItem(), this, toolStack, livingOwner, entity, false)) {
            // 如果被击中的实体是末影人
            if (flag) {
                // 不做任何处理，直接返回
                return;
            }
        }

        // 移除手里剑实体

        if (!(player != null && ModifierLevel.getMainhandModifierlevel((LivingEntity) player, MushCmodifiers.chuantouStaticModifier.getId()) > 0)) {
            this.discard();
        }

    }


    @Override
    protected void onHitBlock(BlockHitResult result) {
        // 调用父类的onHitBlock方法
        super.onHitBlock(result);
        // 记录上一个方块状态
        this.lastState = this.level().getBlockState(this.blockPosition());
        // 获取击中位置与手里剑位置的差异向量
        Vec3 positionDifference = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        // 设置手里剑的移动向量为差异向量
        this.setDeltaMovement(positionDifference);
        // 计算一个微小的位移向量
        Vec3 vec3 = positionDifference.normalize().scale(0.05F);
        // 设置手里剑的位置为当前位置减去微小位移向量
        this.setPosRaw(this.getX() - vec3.x, this.getY() - vec3.y, this.getZ() - vec3.z);
        // 设置手里剑为在地面上
        this.inGround = true;
        // 设置手里剑为非暴击状态
        this.setCritical(false);
    }

    @Override
    public boolean getCritical() {
        // 获取手里剑是否为暴击状态
        return this.critical;
    }

    @Override
    public void setCritical(boolean critical) {
        // 设置手里剑是否为暴击状态
        this.critical = critical;
    }

    @Override
    public float getDamage() {
        // 获取手里剑的总伤害值
        float result = stats.get(ToolStats.ATTACK_DAMAGE) + bonusDamage + 1;
        // 如果手里剑为暴击状态
        if (critical) {
            // 伤害值乘以1.5
            result *= 2F;
        }
        return result;
    }

    @Override
    public void setDamage(float damage) {
        // 设置手里剑的额外伤害值
        this.bonusDamage = damage * 0.5F;
    }

    @Override
    protected Item getDefaultItem() {
        // 获取手里剑的默认物品
        return MushCutil.SHURIKEN.get();
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        // 写入手里剑的生成数据
        buffer.writeItem(this.getItemRaw());
        buffer.writeItem(shurikenStack);
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        // 读取手里剑的生成数据
        this.setItem(additionalData.readItem());
        setTool(additionalData.readItem());
        bonusDamage = additionalData.readFloat();
        critical = additionalData.readBoolean();
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
