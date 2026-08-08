

package org.agmas.client.mixin;


import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.Mob;
import org.agmas.client.QuartermasterClient;
import org.agmas.init.ModAttachments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HumanoidMobRenderer.class)
public abstract class QMExtractRenderStateMixin {

	@Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Mob;Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;F)V", at = @At("TAIL"))
	public void quartermasterAnimations(Mob entity, HumanoidRenderState state, float partialTicks, CallbackInfo ci) {
		if (entity.hasAttached(ModAttachments.WARHAMMER_CAST_TIME)) {
			state.setData(QuartermasterClient.warhammerCastTimeTicks, entity.getAttached(ModAttachments.WARHAMMER_CAST_TIME)-partialTicks);
		} else {
			state.setData(QuartermasterClient.warhammerCastTimeTicks, 0f);
		}

		if (entity.hasAttached(ModAttachments.DISARMED_ANIMATION_TICKS)) {
			state.setData(QuartermasterClient.disarmedTimeTicks, entity.getAttached(ModAttachments.DISARMED_ANIMATION_TICKS)-partialTicks);
		} else {
			state.setData(QuartermasterClient.disarmedTimeTicks, 0f);
		}
	}
}