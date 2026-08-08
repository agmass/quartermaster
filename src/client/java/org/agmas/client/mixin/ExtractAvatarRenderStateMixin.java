

package org.agmas.client.mixin;


import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.agmas.client.QuartermasterClient;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModEnchants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AvatarRenderer.class)
public abstract class ExtractAvatarRenderStateMixin {

	@Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V", at = @At("TAIL"))
	public void quartermasterAnimations(Avatar entity, AvatarRenderState state, float partialTicks, CallbackInfo ci) {
		if (entity.hasAttached(ModAttachments.WARHAMMER_CAST_TIME)) {

			boolean siesmic = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(entity.level(), ModEnchants.SIESMIC),entity.getMainHandItem()) > 0;
			float ticks = entity.getAttached(ModAttachments.WARHAMMER_CAST_TIME)-partialTicks;
			if (siesmic && ticks > 20) {
				ticks = Mth.lerp((ticks-20)/60, 20f,40f);
			}
			state.setData(QuartermasterClient.warhammerCastTimeTicks,ticks);
		} else {
			state.setData(QuartermasterClient.warhammerCastTimeTicks, 0f);
		}

		if (entity.hasAttached(ModAttachments.DISARMED_ANIMATION_TICKS)) {
			state.setData(QuartermasterClient.disarmedTimeTicks, entity.getAttached(ModAttachments.DISARMED_ANIMATION_TICKS)-partialTicks);
		} else {
			state.setData(QuartermasterClient.disarmedTimeTicks, 0f);
		}

		if (entity.hasAttached(ModAttachments.INSPECT_ANIMATION_TICKS)) {
			state.setData(QuartermasterClient.inspectTicks, entity.getAttached(ModAttachments.INSPECT_ANIMATION_TICKS)-partialTicks);
		} else {
			state.setData(QuartermasterClient.inspectTicks, 0f);
		}
	}
}