

package org.agmas.client.mixin;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.zombie.AbstractZombieModel;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.UndeadRenderState;
import net.minecraft.world.entity.HumanoidArm;
import org.agmas.client.QuartermasterClient;
import org.agmas.client.render.entity.DisarmedAnimator;
import org.agmas.client.render.entity.WarhammerAnimator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AnimationUtils.class)
public abstract class WarhammerAnimationZombieMixin {

	@Inject(method = "animateZombieArms",at = @At("TAIL"), cancellable = true)
	private static void posArms(ModelPart leftArm, ModelPart rightArm, boolean bl, UndeadRenderState undeadRenderState, CallbackInfo ci) {
		float castTime = undeadRenderState.getDataOrDefault(QuartermasterClient.warhammerCastTimeTicks,0f).floatValue();
		if (castTime != 0) {
			WarhammerAnimator.poseRightArm(rightArm,castTime);
            WarhammerAnimator.poseLeftArm(leftArm,castTime);
			ci.cancel();
		}

		float disarmedTime = undeadRenderState.getDataOrDefault(QuartermasterClient.disarmedTimeTicks,0f).floatValue();
		if (disarmedTime != 0) {
			DisarmedAnimator.poseMainArm(rightArm, disarmedTime,undeadRenderState.ageInTicks);
			ci.cancel();
		}
	}
}