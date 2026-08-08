

package org.agmas.client.mixin;

import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.agmas.client.QuartermasterClient;
import org.agmas.client.render.entity.WarhammerAnimator;
import org.agmas.init.ModAttachments;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Options.class)
public abstract class WarhammerForceThirdPersonMixin {
	@Shadow
	private CameraType cameraType;

	@Inject(method = "getCameraType",at = @At("HEAD"), cancellable = true)
	public void leftArm(CallbackInfoReturnable<CameraType> cir) {
		if (Minecraft.getInstance().player != null && cameraType == CameraType.FIRST_PERSON) {
			if (Minecraft.getInstance().player.hasAttached(ModAttachments.WARHAMMER_CAST_TIME)) {
				if (Minecraft.getInstance().player.getAttached(ModAttachments.WARHAMMER_CAST_TIME) > 19) {
					cir.setReturnValue(CameraType.THIRD_PERSON_BACK);
					cir.cancel();
				}
			}
		}
	}
}