package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.agmas.Quartermaster;
import org.agmas.init.*;
import org.agmas.init.tag.ModTags;
import org.agmas.item.RapierItem;
import org.agmas.item.SicklesItem;
import org.agmas.porting.QMSpellParticleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(LivingEntity.class)
public abstract class PushedWallDetectorMixin extends Entity {

	@Shadow
	public abstract boolean hurtServer(ServerLevel level, DamageSource source, float damage);

	@Shadow
	public abstract LivingEntity getLastAttacker();

	@Shadow
	public abstract boolean hasEffect(Holder<MobEffect> effect);

	@Shadow
	public abstract boolean removeEffect(Holder<MobEffect> effect);

	public PushedWallDetectorMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Unique
	Vec3 lastDeltaMovement = Vec3.ZERO;

	@Inject(at = @At("HEAD"), method = "tick")
	private void init(CallbackInfo info) {
		if (hasEffect(ModEffects.PUSHED)) {
			if (level() instanceof ServerLevel serverLevel) {
				float damage = 0;
				if (Math.abs(getKnownMovement().x) < 0.1) {
					if (Math.abs(lastDeltaMovement.x) > 0.4) {
						damage += (float) (Math.abs(lastDeltaMovement.x) * 20);
					}
				}
				if (Math.abs(getKnownMovement().z) < 0.1) {
					if (Math.abs(lastDeltaMovement.z) > 0.4) {
						damage += (float) (Math.abs(lastDeltaMovement.z) * 20);
					}
				}
				if (damage > 0) {
					//? if <26.3
					invulnerableTime = 0;
					//? if >=26.3
					//setInvulnerableTime(0);
					hurtServer(serverLevel, damageSources().source(ModDamageTypes.WALL_IMPACT, getLastAttacker()), damage);
					removeEffect(ModEffects.PUSHED);
					setDeltaMovement(lastDeltaMovement.multiply(-2,-2,-2).add(0,-0.4,0));
				}
			}
		}
		lastDeltaMovement = getKnownMovement();
	}
}