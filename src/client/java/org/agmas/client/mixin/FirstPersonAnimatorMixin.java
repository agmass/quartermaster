

package org.agmas.client.mixin;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.agmas.client.render.entity.InspectAnimator;
import org.agmas.init.ModAttachments;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = ItemInHandRenderer.class)
public abstract class FirstPersonAnimatorMixin {

	@Shadow
	@Final
	private Minecraft minecraft;

	@WrapOperation(method = "renderArmWithItem", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
	public boolean undoCrossbow(ItemStack instance, Item item, Operation<Boolean> original) {
		float inspectTime = minecraft.player.getAttachedOrElse(ModAttachments.INSPECT_ANIMATION_TICKS, 0)-minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
		if (inspectTime > 0) {
			return false;
		}
		return original.call(instance,item);
	}
	@ModifyArg(method = "renderArmWithItem", at= @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V"))
	public PoseStack a(PoseStack par1) {
		float inspectTime = minecraft.player.getAttachedOrElse(ModAttachments.INSPECT_ANIMATION_TICKS, 0)-minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
		if (inspectTime > 0) {
			return InspectAnimator.poseHeldItem(par1, inspectTime, minecraft.player.tickCount);
		}
		return par1;
	}
}