package org.agmas.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.EasingType;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import org.agmas.Quartermaster;
import org.agmas.client.QuartermasterClient;
import org.agmas.client.render.animations.FlintlockAnimationState;
import org.agmas.client.render.animations.FlintlockFirstPersonAnim;
import org.agmas.client.render.animations.HandsModel;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModItems;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;
import java.util.Vector;

public class FlintlockAnimator {


    public static void poseMainArm(ModelPart arm, ItemStack stack) {

        if (stack.is(ModItems.FLINTLOCK)) {
            ChargedProjectiles chargedProjectiles = stack.get(DataComponents.CHARGED_PROJECTILES);
            if (chargedProjectiles != null && !chargedProjectiles.isEmpty()) {
                arm.xRot = -1.5f;
            }  else {
                arm.xRot = 0.3f;
            }
        }
    }

    public static AnimationState state = new AnimationState();

    public static void animateHeld(PlayerModel model, Identifier identifier, SubmitNodeCollector submitNodeCollector, PoseStack stack, float delta, int lightCoords) {


        if (!Minecraft.getInstance().player.isUsingItem()) {
            state.start(0);
        } else {
            state.startIfStopped(0);
        }

        QuartermasterClient.handsRoot.getChild("main").getChild("leftarm").resetPose();
        QuartermasterClient.handsRoot.getChild("main").getChild("rightarm").resetPose();
        QuartermasterClient.handsRoot.getChild("main").getChild("rightarm").getChild("gun").resetPose();
        int i = Minecraft.getInstance().player.getUseItem().getUseDuration(Minecraft.getInstance().player);

        boolean useLH = false;
        float inspectTime = Minecraft.getInstance().player.getAttachedOrElse(ModAttachments.INSPECT_ANIMATION_TICKS, 0)-Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(false);

        if (Minecraft.getInstance().player.isUsingItem()) {
            FlintlockFirstPersonAnim.RELOAD.bake(QuartermasterClient.handsRoot).apply(state, ((Minecraft.getInstance().player.getTicksUsingItem() + delta) / i) * 40);
            useLH = true;
        } else if (Quartermaster.timeSinceShooting > 0) {
            FlintlockFirstPersonAnim.SHOOT.bake(QuartermasterClient.handsRoot).apply(state, 15-(Quartermaster.timeSinceShooting - delta));
        } else if (inspectTime > 0) {
            FlintlockFirstPersonAnim.INSPECT.bake(QuartermasterClient.handsRoot).apply(state, 90-(inspectTime));
        } else {
            FlintlockFirstPersonAnim.RELOAD.bake(QuartermasterClient.handsRoot).apply(state, 40);
        }

        stack.pushPose();
        stack.translate(-0.3,-0.3,-0.7);
        stack.rotateAround(Axis.ZP.rotationDegrees(180), 0, 0 ,0);

        if (useLH) {
            submitNodeCollector.submitModelPart(QuartermasterClient.handsRoot.getChild("main").getChild("leftarm"), stack, model.renderType(identifier), lightCoords, OverlayTexture.NO_OVERLAY, null);
       }
        submitNodeCollector.submitModelPart(QuartermasterClient.handsRoot.getChild("main").getChild("rightarm"),stack,model.renderType(identifier), lightCoords,OverlayTexture.NO_OVERLAY,null);
        stack.popPose();

        stack.pushPose();
        stack.translate(-0.3,-0.3,-0.7);
        stack.rotateAround(Axis.ZP.rotationDegrees(180), 0, 0 ,0);
        QuartermasterClient.handsRoot.getChild("main").getChild("rightarm").translateAndRotate(stack);
        QuartermasterClient.handsRoot.getChild("main").getChild("rightarm").getChild("gun").translateAndRotate(stack);
        QuartermasterClient.handsRoot.getChild("main").getChild("rightarm").getChild("gun").getChild("ITEM").translateAndRotate(stack);

        stack.mulPose(Axis.ZP.rotationDegrees(180));
        stack.translate(0,0.2,-0.2);
        ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
        Minecraft.getInstance().getItemModelResolver()
                .updateForTopItem(
                        itemStackRenderState, ModItems.FLINTLOCK.getDefaultInstance(), ItemDisplayContext.NONE, Minecraft.getInstance().level, Minecraft.getInstance().player, Minecraft.getInstance().player.getId() + ItemDisplayContext.NONE.ordinal()
                );

        itemStackRenderState.submit(stack,submitNodeCollector,lightCoords, OverlayTexture.NO_OVERLAY,0);

        stack.popPose();
    }

}
