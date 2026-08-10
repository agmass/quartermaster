

package org.agmas.client.mixin;


import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.agmas.client.QuartermasterClient;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModEnchants;
import org.agmas.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HumanoidModel.class)
public abstract class FlintlockReloadAnimationMixin {

	@WrapOperation(method = "poseRightArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/AnimationUtils;animateCrossbowCharge(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;FFZ)V"))
	public void quartermasterAnimations2(ModelPart modelPart, ModelPart modelPart2, float maxCharge, float useTicks, boolean bl, Operation<Void> original, @Local(argsOnly = true) HumanoidRenderState humanoidRenderState) {
		if (humanoidRenderState.rightHandItemStack.is(ModItems.FLINTLOCK)) {
			float reversedTicks = Math.abs(useTicks-maxCharge) % 5.0f;
			original.call(modelPart,modelPart2,5f,reversedTicks,bl);
			return;
		}
		original.call(modelPart,modelPart2,maxCharge,useTicks,bl);
	}
	@WrapOperation(method = "poseLeftArm", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/AnimationUtils;animateCrossbowCharge(Lnet/minecraft/client/model/geom/ModelPart;Lnet/minecraft/client/model/geom/ModelPart;FFZ)V"))
	public void quartermasterAnimations(ModelPart modelPart, ModelPart modelPart2, float maxCharge, float useTicks, boolean bl, Operation<Void> original, @Local(argsOnly = true) HumanoidRenderState humanoidRenderState) {
		if (humanoidRenderState.leftHandItemStack.is(ModItems.FLINTLOCK)) {
			float reversedTicks = Math.abs(useTicks-maxCharge) % 5.0f;
			original.call(modelPart,modelPart2,5f,reversedTicks,bl);
			return;
		}
		original.call(modelPart,modelPart2,maxCharge,useTicks,bl);
	}
}