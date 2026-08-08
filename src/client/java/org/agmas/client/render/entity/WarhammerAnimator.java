package org.agmas.client.render.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.EasingType;
import net.minecraft.util.Mth;

public class WarhammerAnimator {
    public static void poseRightArm(ModelPart arm, float castTime) {
        float oxRot = arm.xRot;
        float oyRot = arm.yRot;
        if (castTime > 20) {
            float delta = 1.0f-((castTime/20)-1);
            float l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0, -2.5f);
            float l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0, -0.6f);
            arm.xRot = l;
            arm.yRot = l2;
        }  else if (castTime > 10) {
            float delta = 1.0f-((castTime/10)-1);
            float l = Mth.lerp(EasingType.OUT_BOUNCE.apply(delta), -2.5f, 0.2f);
            float l2 = Mth.lerp(EasingType.OUT_BOUNCE.apply(delta), -0.6f, -0.3f);
            arm.xRot = l;
            arm.yRot = l2;
        }  else {
            float delta = 1.0f-(castTime/10);
            float l = Mth.lerp(EasingType.OUT_SINE.apply(delta), 0.2f, oxRot);
            float l2 = Mth.lerp(EasingType.OUT_SINE.apply(delta), -0.3f, oyRot);
            arm.xRot = l;
            arm.yRot = l2;
        }
    }
    public static void poseLeftArm(ModelPart arm, float castTime) {
        float oxRot = arm.xRot;
        float oyRot = arm.yRot;
        if (castTime > 20) {
            float delta = 1.0f-((castTime/20)-1);
            float l = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0, -2.5f);
            float l2 = Mth.lerp(EasingType.OUT_QUAD.apply(delta), 0, 0.6f);
            arm.xRot = l;
            arm.yRot = l2;
        } else if (castTime > 10) {
            float delta = 1.0f-((castTime/10)-1);
            float l = Mth.lerp(EasingType.OUT_BOUNCE.apply(delta), -2.5f, 0.2f);
            float l2 = Mth.lerp(EasingType.OUT_BOUNCE.apply(delta), 0.6f, 0.3f);
            arm.xRot = l;
            arm.yRot = l2;
        } else {
            float delta = 1.0f-(castTime/10);
            float l = Mth.lerp(EasingType.OUT_SINE.apply(delta), 0.2f, oxRot);
            float l2 = Mth.lerp(EasingType.OUT_SINE.apply(delta), 0.3f, oyRot);
            arm.xRot = l;
            arm.yRot = l2;
        }
    }
}
