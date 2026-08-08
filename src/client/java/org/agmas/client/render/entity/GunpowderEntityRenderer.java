package org.agmas.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;

//? if <26.1 {
import net.minecraft.client.renderer.state.CameraRenderState;
//? } else {
/*import net.minecraft.client.renderer.state.level.CameraRenderState;
 *///? }
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.agmas.QMIdentifier;
import org.agmas.client.render.state.GreataxeProjectileRenderState;
import org.agmas.entity.GreataxeProjectileEntity;
import org.agmas.entity.GunpowderEntity;

import java.awt.*;

public class GunpowderEntityRenderer extends EntityRenderer<GunpowderEntity, EntityRenderState> {
    public GunpowderEntityRenderer(EntityRendererProvider.Context context) {
        super(context);

    }

    @Override
    public void submit(EntityRenderState entityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.entitySolid(QMIdentifier.of("textures/entity/gunpowder.png").id), ((pose, vertexConsumer) -> {
            int fullbright = entityRenderState.lightCoords;

            pose.scale(0.4f,0.4f,0.4f);
            pose.rotate(Axis.XP.rotationDegrees(entityRenderState.ageInTicks*60));
            pose.rotate(Axis.YP.rotationDegrees(entityRenderState.ageInTicks*60));
            pose.rotate(Axis.ZP.rotationDegrees(entityRenderState.ageInTicks*60));

            buildCube(pose,vertexConsumer,fullbright);
        }));
        super.submit(entityRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    public static void buildCube(PoseStack.Pose pose, VertexConsumer vertexConsumer, int fullbright) {
        vertexConsumer.addVertex(pose, 0.5f, -0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 0).setUv2(fullbright, fullbright).setUv1(0, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, -0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 0).setUv2(fullbright, fullbright).setUv1(1, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, 0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 1).setUv2(fullbright, fullbright).setUv1(1, 1).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, 0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 1).setUv2(fullbright, fullbright).setUv1(0, 1).setNormal(1, 1, 1);

        vertexConsumer.addVertex(pose, -0.5f, -0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 0).setUv2(fullbright, fullbright).setUv1(0, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, -0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 0).setUv2(fullbright, fullbright).setUv1(1, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, 0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 1).setUv2(fullbright, fullbright).setUv1(1, 1).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, 0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 1).setUv2(fullbright, fullbright).setUv1(0, 1).setNormal(1, 1, 1);

        vertexConsumer.addVertex(pose, -0.5f, -0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 0).setUv2(fullbright, fullbright).setUv1(0, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, -0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 0).setUv2(fullbright, fullbright).setUv1(1, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, -0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 1).setUv2(fullbright, fullbright).setUv1(1, 1).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, -0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 1).setUv2(fullbright, fullbright).setUv1(0, 1).setNormal(1, 1, 1);

        vertexConsumer.addVertex(pose, 0.5f, 0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 0).setUv2(fullbright, fullbright).setUv1(0, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, 0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 0).setUv2(fullbright, fullbright).setUv1(1, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, 0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 1).setUv2(fullbright, fullbright).setUv1(1, 1).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, 0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 1).setUv2(fullbright, fullbright).setUv1(0, 1).setNormal(1, 1, 1);

        vertexConsumer.addVertex(pose, -0.5f, 0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 0).setUv2(fullbright, fullbright).setUv1(0, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, -0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 0).setUv2(fullbright, fullbright).setUv1(1, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, -0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 1).setUv2(fullbright, fullbright).setUv1(1, 1).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, -0.5f, 0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 1).setUv2(fullbright, fullbright).setUv1(0, 1).setNormal(1, 1, 1);

        vertexConsumer.addVertex(pose, 0.5f, -0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 0).setUv2(fullbright, fullbright).setUv1(0, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, 0.5f, -0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 0).setUv2(fullbright, fullbright).setUv1(1, 0).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, 0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(1, 1).setUv2(fullbright, fullbright).setUv1(1, 1).setNormal(1, 1, 1);
        vertexConsumer.addVertex(pose, 0.5f, -0.5f, 0.5f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(-1).setUv(0, 1).setUv2(fullbright, fullbright).setUv1(0, 1).setNormal(1, 1, 1);

    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

}
