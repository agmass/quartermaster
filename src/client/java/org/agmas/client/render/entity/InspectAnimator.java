package org.agmas.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.EasingType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Items;

public class InspectAnimator {
    public static void poseMainArm(ModelPart arm, float castTime, float ticks,boolean left) {
        if (castTime > 80) {
            float delta = 1.0f-((castTime-80)/10);
            float l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0, -0.4f);
            float l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0, -0.3f);
            float l3 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0, -0.8f);
            arm.xRot = l;
            arm.yRot = l2;
            arm.zRot = l3;
        } else if (castTime > 45) {
            arm.xRot = -0.4f;
            arm.yRot = -0.3f;
            arm.zRot = -0.8f;
        } else if (castTime > 40) {
            float delta = 1.0f-((castTime-40)/5);
            float l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -0.4f,-1.0f);
            float l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -0.3f,-0.7f);
            float l3 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -0.8f,1.3f);
            arm.xRot = l;
            arm.yRot = l2;
            arm.zRot = l3;
        } else if (castTime > 5) {
            arm.xRot = -1.0f;
            arm.yRot = -0.7f;
            arm.zRot = 1.3f;
        } else {
            float delta = 1.0f-(castTime/5);
            float l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -1.0f, -0.4f);
            float l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -0.7f, -0.3f);
            float l3 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 1.3f, -0.8f);
            arm.xRot = l;
            arm.yRot = l2;
            arm.zRot = l3;
        }
    }


    public static PoseStack poseHeldItem(PoseStack stack, float castTime, float ticks) {

        // Oh my god this looks so messy whatever I hate animation

        int k = Minecraft.getInstance().options.mainHand().get().equals(HumanoidArm.LEFT) ? -1 : 1;
        if (castTime > 80) {
            float delta = 1.0f-((castTime-80)/10);
            float l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0f,-90f);
            float l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0f,10f);
            float l3 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0f,40f);
            stack.mulPose(Axis.YP.rotationDegrees(k * l));
            stack.mulPose(Axis.ZP.rotationDegrees(k * l2));
            stack.mulPose(Axis.XP.rotationDegrees(l3));
            l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0f,0.1f);
            l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0f,0.3f);
            stack.translate(l, l2, l);
        } else if (castTime > 45) {
            stack.mulPose(Axis.YP.rotationDegrees(k * -90));
            stack.mulPose(Axis.ZP.rotationDegrees(k * 10));
            stack.mulPose(Axis.XP.rotationDegrees(40));
            stack.translate(0.1, 0.3, 0.1);
        } else if (castTime > 40) {
            float delta = 1.0f-((castTime-40)/5);
            float l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -90f,90f);
            float l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 10f,-10f);
            stack.mulPose(Axis.YP.rotationDegrees(k * l));
            stack.mulPose(Axis.ZP.rotationDegrees(k * l2));
            stack.mulPose(Axis.XP.rotationDegrees(40));
            float l3;
            l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0.1f,k * -0.25f);
            l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0.3f,-0.4f);
            l3 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0.1f,-0.7f);
            stack.translate(l,l2,l3);
        } else if (castTime > 5) {
            stack.mulPose(Axis.YP.rotationDegrees(k * 90));
            stack.mulPose(Axis.ZP.rotationDegrees(k * -10));
            stack.mulPose(Axis.XP.rotationDegrees(40));
            stack.translate(k * -0.25,-0.4,-0.7);
        } else {

            float delta = 1.0f-((castTime)/5);
            float l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 90f,-90f);
            float l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -10f,10f);
            stack.mulPose(Axis.YP.rotationDegrees(k * l));
            stack.mulPose(Axis.ZP.rotationDegrees(k * l2));
            stack.mulPose(Axis.XP.rotationDegrees(40));
            float l3;
            l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), k * -0.25f,0.1f);
            l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -0.4f,0.3f);
            l3 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), -0.7f,0.1f);
            stack.translate(l,l2,l3);
        }


        return stack;
    }
}
