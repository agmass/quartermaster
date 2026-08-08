package org.agmas.client.render.entity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.EasingType;
import net.minecraft.util.Mth;

public class DisarmedAnimator {
    public static void poseMainArm(ModelPart arm, float disarmTime, float ticks) {
        float oldyRot = arm.yRot;
        float oldxRot = arm.xRot;
        float oldzRot = arm.zRot;

        arm.yRot = 0.5f;
        arm.xRot = 0.05f;
        arm.zRot = 0.3f;
        arm.yRot += (float) Math.sin(ticks*0.5f)*0.2f;

        if (disarmTime < 20) {
            float delta = 1.0f-(disarmTime/20);
            arm.yRot = Mth.lerp(delta,arm.yRot,oldyRot);
            arm.xRot = Mth.lerp(delta,arm.xRot,oldxRot);
            arm.zRot = Mth.lerp(delta,arm.zRot,oldzRot);
        }
    }
}
