package com.mogumogumo.mushroom_concept.client;

import com.mogumogumo.mushroom_concept.entities.TinkerShurikenEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;

/**
 * RenderTinkerShuriken 类是 Tinker Transplant 模组的客户端渲染器，用于渲染 TinkerShurikenEntity 实体。
 */
public class RenderTinkerShuriken extends EntityRenderer<TinkerShurikenEntity> {

    // 用于渲染物品的渲染器
    private final ItemRenderer itemRenderer;

    /**
     * 构造函数，初始化渲染器。
     *
     * @param context 渲染器提供程序的上下文。
     */
    public RenderTinkerShuriken(EntityRendererProvider.Context context) {
        // 调用父类的构造函数
        super(context);
        // 获取物品渲染器
        this.itemRenderer = context.getItemRenderer();
    }

    /**
     * 渲染 TinkerShurikenEntity 实体的方法。
     *
     * @param entity        要渲染的实体。
     * @param entityYaw     实体的偏航角。
     * @param partialTicks  部分渲染帧的时间。
     * @param matrixStackIn 用于变换的矩阵堆栈。
     * @param buffIn        用于渲染的缓冲区源。
     * @param packedLightIn 打包的光照信息。
     */
    @Override
    public void render(TinkerShurikenEntity entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource buffIn, int packedLightIn) {
        // 如果实体的 tick 计数大于等于 2 或者相机距离实体的平方大于 12.25，则进行渲染
        matrixStackIn.scale(2, 2, 2);
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) - 45.0F + entity.xRotO));
        if (entity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(entity) < 12.25D)) {
            // 将矩阵堆栈压入
            matrixStackIn.pushPose();
            // 平移矩阵堆栈
            matrixStackIn.translate(-0.03125, -0.09375, 0);
            // 渲染静态物品
            this.itemRenderer.renderStatic(
                    entity.getItem(),
                    ItemDisplayContext.GROUND,
                    packedLightIn,
                    OverlayTexture.NO_OVERLAY,
                    matrixStackIn,
                    buffIn,
                    entity.level(),
                    entity.getId());
            // 弹出矩阵堆栈
            matrixStackIn.popPose();
            // 调用父类的渲染方法
            super.render(entity, entityYaw, partialTicks, matrixStackIn, buffIn, packedLightIn);
        }
    }

    /**
     * 获取 TinkerShurikenEntity 实体的纹理位置。
     *
     * @param entity 要获取纹理的实体。
     * @return 纹理的资源位置。
     */
    @Override
    public ResourceLocation getTextureLocation(TinkerShurikenEntity entity) {
        // 返回物品栏的纹理图集
        return InventoryMenu.BLOCK_ATLAS;
    }
}
