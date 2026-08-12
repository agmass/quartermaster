

package org.agmas.client.mixin;


import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.agmas.client.render.entity.FlintlockAnimator;
import org.agmas.client.render.entity.InspectAnimator;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModItems;
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

	@Shadow
	@Final
	private EntityRenderDispatcher entityRenderDispatcher;

	//? <=1.21.11 {
	@WrapOperation(method = "renderArmWithItem", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
	public boolean undoCrossbow(ItemStack instance, Item item, Operation<Boolean> original) {
	//?} else {
	
	/*@WrapOperation(method = "submitArmWithItem", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
	public boolean undoCrossbow(ItemStack instance, Object item, Operation<Boolean> original) {
	*///? }
		float inspectTime = minecraft.player.getAttachedOrElse(ModAttachments.INSPECT_ANIMATION_TICKS, 0)-minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
		if (inspectTime > 0) {
			return false;
		}
		return original.call(instance,item);
	}

	//? <=1.21.11 {
	@ModifyArg(method = "renderArmWithItem"
	//?} else {
	
	/*@ModifyArg(method = "submitArmWithItem"
	*///? }
	, at= @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V"))
	public PoseStack a(PoseStack par1) {
		float inspectTime = minecraft.player.getAttachedOrElse(ModAttachments.INSPECT_ANIMATION_TICKS, 0)-minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
		if (inspectTime > 0) {
			return InspectAnimator.poseHeldItem(par1, inspectTime, minecraft.player.tickCount);
		}
		return par1;
	}

	//? <=1.21.11 {
	@WrapMethod(method = "renderArmWithItem")
	//?} else {
	
	/*@WrapMethod(method = "submitArmWithItem")
	*///? }
	public void renderFlintlockFirstPerson(AbstractClientPlayer abstractClientPlayer, float f, float g, InteractionHand interactionHand, float h, ItemStack itemStack, float i, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int j, Operation<Void> original) {
		if (minecraft.player.isHolding(ModItems.FLINTLOCK)) {
			if (minecraft.player.isUsingItem() || (minecraft.player.getMainHandItem().is(ModItems.FLINTLOCK) && interactionHand.equals(InteractionHand.MAIN_HAND))) {
				AvatarRenderer<AbstractClientPlayer> avatarRenderer = entityRenderDispatcher.getPlayerRenderer(abstractClientPlayer);
				FlintlockAnimator.animateHeld(avatarRenderer.getModel(), abstractClientPlayer.getSkin().body().texturePath(), submitNodeCollector, poseStack, f,j);
				return;
			}
		}
		original.call(abstractClientPlayer, f, g, interactionHand, h, itemStack, i, poseStack, submitNodeCollector, j);
	}
}