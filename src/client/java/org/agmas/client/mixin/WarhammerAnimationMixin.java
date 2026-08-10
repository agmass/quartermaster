

package org.agmas.client.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.EasingType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.agmas.client.QuartermasterClient;
import org.agmas.client.render.entity.DisarmedAnimator;
import org.agmas.client.render.entity.FlintlockAnimator;
import org.agmas.client.render.entity.InspectAnimator;
import org.agmas.client.render.entity.WarhammerAnimator;
import org.agmas.init.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HumanoidModel.class)
public abstract class WarhammerAnimationMixin {
	@Shadow
	@Final
	public ModelPart head;

	@Shadow
	@Final
	public ModelPart rightArm;

	@Shadow
	@Final
	public ModelPart leftArm;

	@Inject(method = "poseRightArm",at = @At("TAIL"), cancellable = true)
	public void rightArm(HumanoidRenderState state, CallbackInfo ci) {

		float disarmedTime = state.getDataOrDefault(QuartermasterClient.disarmedTimeTicks,0f).floatValue();
		if (disarmedTime != 0 && state.mainArm.equals(HumanoidArm.RIGHT)) {
			DisarmedAnimator.poseMainArm(rightArm, disarmedTime,state.ageInTicks);
			ci.cancel();
		}
		float inspectTime = state.getDataOrDefault(QuartermasterClient.inspectTicks,0f).floatValue();
		if (inspectTime != 0 && state.mainArm.equals(HumanoidArm.RIGHT)) {
			InspectAnimator.poseMainArm(rightArm, inspectTime,state.ageInTicks,false);
			ci.cancel();
		}


		float castTime = state.getDataOrDefault(QuartermasterClient.warhammerCastTimeTicks,0f).floatValue();
		if (castTime != 0) {
			WarhammerAnimator.poseRightArm(rightArm,castTime);
			ci.cancel();
		} else if (!state.isUsingItem) {
			FlintlockAnimator.poseMainArm(rightArm,state.rightHandItemStack);
		}

	}
	@Inject(method = "poseLeftArm",at = @At("HEAD"), cancellable = true)
	public void leftArm(HumanoidRenderState state, CallbackInfo ci) {

		float inspectTime = state.getDataOrDefault(QuartermasterClient.inspectTicks,0f).floatValue();
		if (inspectTime != 0 && state.mainArm.equals(HumanoidArm.LEFT)) {
			InspectAnimator.poseMainArm(leftArm, inspectTime,state.ageInTicks,true);
			ci.cancel();
		}

		float disarmedTime = state.getDataOrDefault(QuartermasterClient.disarmedTimeTicks,0f).floatValue();
		if (disarmedTime != 0 && state.mainArm.equals(HumanoidArm.LEFT) ) {
			DisarmedAnimator.poseMainArm(leftArm, disarmedTime,state.ageInTicks);
			ci.cancel();
		}

		float castTime = state.getDataOrDefault(QuartermasterClient.warhammerCastTimeTicks,0f).floatValue();
		if (castTime != 0) {
			WarhammerAnimator.poseLeftArm(leftArm,castTime);
			ci.cancel();
		} else if (!state.isUsingItem) {
			FlintlockAnimator.poseMainArm(leftArm,state.leftHandItemStack);
		}

	}
}