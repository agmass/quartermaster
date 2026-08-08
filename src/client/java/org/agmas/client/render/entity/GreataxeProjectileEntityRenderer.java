package org.agmas.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
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

import java.awt.*;

public class GreataxeProjectileEntityRenderer extends EntityRenderer<GreataxeProjectileEntity, GreataxeProjectileRenderState> {
    public GreataxeProjectileEntityRenderer(EntityRendererProvider.Context context) {
        super(context);

    }

    @Override
    public void submit(GreataxeProjectileRenderState entityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        if (entityRenderState.ageInTicks < 8) {
            submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.entityCutout(QMIdentifier.ofVanilla("textures/particle/sweep_" + (int) Math.floor(entityRenderState.ageInTicks) + ".png").id), ((pose, vertexConsumer) -> {

                pose.rotate(cameraRenderState.orientation);
                int fullbright = 15728880;
                int magenta = entityRenderState.color;

                Color mutableColor = new Color(entityRenderState.color);
                int outline = new Color(mutableColor.getRed(), mutableColor.getGreen(), mutableColor.getBlue(), 150).darker().getRGB();

                PoseStack.Pose outlineStack = pose.copy();
                outlineStack.scale(2.5f * entityRenderState.scale, 2.5f * entityRenderState.scale, 2.5f * entityRenderState.scale);
                vertexConsumer.addVertex(outlineStack, -0.75f, -0.5f, -0.1f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(outline).setUv(0, 0).setUv2(fullbright, fullbright).setUv1(0, 0).setNormal(1, 1, 1);
                vertexConsumer.addVertex(outlineStack, 0.75f, -0.5f, -0.1f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(outline).setUv(1, 0).setUv2(fullbright, fullbright).setUv1(1, 0).setNormal(1, 1, 1);
                vertexConsumer.addVertex(outlineStack, 0.75f, 0.5f, -0.1f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(outline).setUv(1, 1).setUv2(fullbright, fullbright).setUv1(1, 1).setNormal(1, 1, 1);
                vertexConsumer.addVertex(outlineStack, -0.75f, 0.5f, -0.1f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(outline).setUv(0, 1).setUv2(fullbright, fullbright).setUv1(0, 1).setNormal(1, 1, 1);

                pose.scale(2 * entityRenderState.scale, 2 * entityRenderState.scale, 2 * entityRenderState.scale);
                vertexConsumer.addVertex(pose, -0.75f, -0.5f, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(magenta).setUv(0, 0).setUv2(fullbright, fullbright).setUv1(0, 0).setNormal(1, 1, 1);
                vertexConsumer.addVertex(pose, 0.75f, -0.5f, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(magenta).setUv(1, 0).setUv2(fullbright, fullbright).setUv1(1, 0).setNormal(1, 1, 1);
                vertexConsumer.addVertex(pose, 0.75f, 0.5f, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(magenta).setUv(1, 1).setUv2(fullbright, fullbright).setUv1(1, 1).setNormal(1, 1, 1);
                vertexConsumer.addVertex(pose, -0.75f, 0.5f, 0f).setOverlay(OverlayTexture.NO_OVERLAY).setColor(magenta).setUv(0, 1).setUv2(fullbright, fullbright).setUv1(0, 1).setNormal(1, 1, 1);

            }));
            super.submit(entityRenderState, poseStack, submitNodeCollector, cameraRenderState);
        }
    }

    @Override
    public GreataxeProjectileRenderState createRenderState() {
        return new GreataxeProjectileRenderState();
    }

    @Override
    public void extractRenderState(GreataxeProjectileEntity entity, GreataxeProjectileRenderState entityRenderState, float f) {
        super.extractRenderState(entity, entityRenderState, f);
        entityRenderState.color = entity.getColor();
        entityRenderState.scale = entity.getScale();
    }
}
