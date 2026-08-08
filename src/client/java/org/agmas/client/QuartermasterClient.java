package org.agmas.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
//? if <26.1 {
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
//? } else {
/*import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
 *///? }
//? if >=26.1 {
/*import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
*///? } else {
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
//? }
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.particle.CritParticle;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import org.agmas.QMIdentifier;
import org.agmas.client.item.CoralRapierBooleanProperty;
import org.agmas.client.render.entity.GreataxeProjectileEntityRenderer;
import org.agmas.client.render.entity.GunpowderEntityRenderer;
import org.agmas.client.render.hud.DisarmedHudElement;
import org.agmas.client.render.hud.ParryHudElement;
import org.agmas.client.render.hud.StunnedHudElement;
import org.agmas.client.render.hud.WoundHudElement;
import org.agmas.client.render.particle.ColorAttackSweepParticle;
import org.agmas.client.render.particle.ColorCritParticle;
import org.agmas.client.render.particle.WarhammerLandingParticle;
import org.agmas.init.ModEntities;
import org.agmas.init.ModParticles;
//? if <26.3 {
import org.agmas.network.ServerboundInspectPacket;
import org.lwjgl.glfw.GLFW;
//? }


public class QuartermasterClient implements ClientModInitializer {

	public static RenderStateDataKey<Float> warhammerCastTimeTicks = RenderStateDataKey.create(()->"warhammerCastTimeTicks");
	public static RenderStateDataKey<Float> disarmedTimeTicks = RenderStateDataKey.create(()->"disarmedTimeTicks");
	public static RenderStateDataKey<Float> inspectTicks = RenderStateDataKey.create(()->"inspectTicks");

	public static KeyMapping inspectAnimation;
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ConditionalItemModelProperties.ID_MAPPER.put(CoralRapierBooleanProperty.ID, CoralRapierBooleanProperty.CODEC);

		EntityRenderers.register(ModEntities.GUNPOWDER, GunpowderEntityRenderer::new);

		EntityRenderers.register(ModEntities.GREATAXE_PROJECTILE, GreataxeProjectileEntityRenderer::new);

		//? if <26.1 {
		ParticleFactoryRegistry.getInstance().register(ModParticles.COLOR_CRIT, ColorCritParticle.InstantProvider::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.COLOR_SWEEP, ColorAttackSweepParticle.InstantProvider::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.LANDING, WarhammerLandingParticle.InstantProvider::new);
		//? } else {
		/*ParticleProviderRegistry.getInstance().register(ModParticles.COLOR_CRIT, ColorCritParticle.InstantProvider::new);
		ParticleProviderRegistry.getInstance().register(ModParticles.COLOR_SWEEP, ColorAttackSweepParticle.InstantProvider::new);
		ParticleProviderRegistry.getInstance().register(ModParticles.LANDING, WarhammerLandingParticle.InstantProvider::new);
		*///? }


		//? if >=1.21.9 {
		KeyMapping.Category CATEGORY = KeyMapping.Category.register(
				QMIdentifier.of("quartermaster").id
		);
		//? } else {
		/*String CATEGORY = KeyMapping.CATEGORY_MOVEMENT;
		 *///? }
		inspectAnimation = registerKeyMapping(
				new KeyMapping(
						"key.quartermaster.inspect",
						//? if <26.3 {
						InputConstants.Type.KEYSYM,
						GLFW.GLFW_KEY_I,
						//? } else {
						/*InputConstants.Type.KEYBOARD,
						InputConstants.KEY_I,
						*///? }
						CATEGORY
				));

		HudElementRegistry.attachElementAfter(
				VanillaHudElements.CROSSHAIR,
				QMIdentifier.of("parry_hud").id,
				new ParryHudElement()
		);
		HudElementRegistry.attachElementAfter(
				VanillaHudElements.CROSSHAIR,
				QMIdentifier.of("disarmed_hud").id,
				new DisarmedHudElement()
		);
		HudElementRegistry.attachElementAfter(
				VanillaHudElements.CROSSHAIR,
				QMIdentifier.of("stunned_hud").id,
				new StunnedHudElement()
		);
		HudElementRegistry.attachElementAfter(
				VanillaHudElements.CROSSHAIR,
				QMIdentifier.of("wound_indicator_hud").id,
				new WoundHudElement()
		);

		ClientTickEvents.START_CLIENT_TICK.register((m)->{
			if (m.player != null) {
				if (m.player.getItemBlockingWith() != null) {
					if (m.player.getItemBlockingWith().is(Items.SHIELD)) {
						while (m.options.keyAttack.consumeClick()) {
							m.player.swing(InteractionHand.MAIN_HAND);
						}
					}
				}
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register((m)->{
			if (m.player != null) {
				while (inspectAnimation.consumeClick()) {
					ClientPlayNetworking.send(new ServerboundInspectPacket(true));
				}
			}
		});
	}

	public static KeyMapping registerKeyMapping(KeyMapping keyMapping) {
		//? if >=26.1 {
		/*return KeyMappingHelper.registerKeyMapping(keyMapping);
		*///? } else {
		return KeyBindingHelper.registerKeyBinding(keyMapping);
		 //? }
	}
}