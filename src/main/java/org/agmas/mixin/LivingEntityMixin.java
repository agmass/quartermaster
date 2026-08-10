package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.agmas.init.*;
import org.agmas.init.tag.ModTags;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract ItemStack getUseItem();

	@Shadow
	public abstract ItemStack getMainHandItem();

	@Shadow
	public abstract boolean hasEffect(Holder<MobEffect> effect);

	@Shadow
	public abstract @Nullable AttributeInstance getAttribute(Holder<Attribute> attribute);

	@Shadow
	public abstract boolean addEffect(MobEffectInstance newEffect);

	@Shadow
	protected int attackStrengthTicker;

	public LivingEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Inject(at = @At("HEAD"), method = "tick")
	private void init(CallbackInfo info) {
		if (hasAttached(ModAttachments.INSPECT_ANIMATION_TICKS)) {
			int ticks = getAttached(ModAttachments.INSPECT_ANIMATION_TICKS)-1;
			setAttached(ModAttachments.INSPECT_ANIMATION_TICKS,ticks);
			if (ticks <= 0) {
				setAttached(ModAttachments.INSPECT_ANIMATION_TICKS,80);
			}
		}

		if (!level().isClientSide()) {
			if (hasAttached(ModAttachments.DISARMED_ANIMATION_TICKS) && !hasEffect(ModEffects.DISARMED)) {
				int ticks = getAttached(ModAttachments.DISARMED_ANIMATION_TICKS)-1;
				setAttached(ModAttachments.DISARMED_ANIMATION_TICKS,ticks);
				if (ticks <= 0) {
					removeAttached(ModAttachments.DISARMED_ANIMATION_TICKS);
				}
			}

			if (hasAttached(ModAttachments.WARHAMMER_CAST_TIME)) {
				if (getAttached(ModAttachments.WARHAMMER_CAST_TIME) <= 20) {
					resetFallDistance();
				}
				if (getAttached(ModAttachments.WARHAMMER_CAST_TIME) >= 20) {
					if (!getMainHandItem().is(ModTags.WARHAMMERS)) {
						removeAttached(ModAttachments.WARHAMMER_CAST_TIME);
					}
				}
				if (((LivingEntity)(Object)this) instanceof Player) {
					if (attackStrengthTicker <= 1) {
						removeAttached(ModAttachments.WARHAMMER_CAST_TIME);
					}
				}
			}

			if (hasAttached(ModAttachments.WARHAMMER_CAST_TIME)) {
				if (onGround() || getAttached(ModAttachments.WARHAMMER_CAST_TIME) != 20) {
					int ticks = getAttached(ModAttachments.WARHAMMER_CAST_TIME) - 1;
					setAttached(ModAttachments.WARHAMMER_CAST_TIME, ticks);

					if (getAttached(ModAttachments.WARHAMMER_CAST_TIME) == 19) {
						ServerLevel level = (ServerLevel) level();
						level().playSound(null, getX(),getY(),getZ(), SoundEvents.ANVIL_FALL,getSoundSource());
						level().playSound(null, getX(),getY(),getZ(),ModSounds.STRONG_SHIELD_BREAK,getSoundSource());
						int color = Color.WHITE.getRGB();
						float power = 0.3f;
						if (getMainHandItem().has(ModComponents.COMBAT_EFFECT_COLOR)) {
							color = getMainHandItem().get(ModComponents.COMBAT_EFFECT_COLOR).intValue();
						}
						if (getMainHandItem().has(ModComponents.WARHAMMER_RANGE)) {
							power = getMainHandItem().get(ModComponents.WARHAMMER_RANGE).floatValue();
						}
						addEffect(new MobEffectInstance(MobEffects.SLOWNESS,20,0));
						level.sendParticles(SpellParticleOption.create(ModParticles.LANDING, color,power),getX(),getY()+0.02f,getZ(),0,0,0,0,0);
						boolean siesmic = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level, ModEnchants.SIESMIC),getMainHandItem()) > 0;
						boolean pull = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level, ModEnchants.PULL),getMainHandItem()) > 0;
						boolean homerun = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level, ModEnchants.HOMERUN),getMainHandItem()) > 0;
						boolean glacial = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level, ModEnchants.HEATWAVE),getMainHandItem()) > 0;
						boolean heatwave = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level, ModEnchants.GLACIAL),getMainHandItem()) > 0;

						for (Entity entity : level.getEntities(((LivingEntity) (Object) this), getBoundingBox().inflate(power * 1.5f))) {
							if (entity instanceof LivingEntity livingEntity) {
								DamageSource source = level.damageSources().source(ModDamageTypes.SHOCKWAVE, this);
								livingEntity.hurtServer(level,source, siesmic ? (float) getAttribute(Attributes.ATTACK_DAMAGE).getValue()*2f : 4f);
								if (!siesmic && livingEntity.isBlocking()) {
									livingEntity.getItemBlockingWith().get(DataComponents.BLOCKS_ATTACKS).disable(level,livingEntity,power,livingEntity.getItemBlockingWith());
								}
								if (heatwave) {
									livingEntity.setRemainingFireTicks(120);
								}
								if (glacial) {
									livingEntity.setTicksFrozen(480);
								}
								if (pull) {
									livingEntity.setDeltaMovement(livingEntity.getPosition(0f).subtract(getPosition(0f)).normalize().multiply(-2,-2,-2));
									//? if >=1.21.11 {
									livingEntity.needsSync = true;
									//? } else {
									/*livingEntity.hasImpulse = true;
									 *///? }
								}

								if (homerun) {
									livingEntity.setDeltaMovement(new Vec3(0,0.5f,0));
									//? if >=1.21.11 {
									livingEntity.needsSync = true;
									//? } else {
									/*livingEntity.hasImpulse = true;
									 *///? }
								}
							}
						}
					}

					if (ticks <= 0) {
						removeAttached(ModAttachments.WARHAMMER_CAST_TIME);
					}
				}
			}
		}
	}

	/**
	 * @author Chemthunder
	 */
	@WrapMethod(method = "hurtServer")
	private boolean parry(ServerLevel level, DamageSource source, float amount, Operation<Boolean> original) {
		if (source.getEntity() instanceof LivingEntity target) {
			if (getUseItem().is(ModItems.RAPIER)) {
				target.hurtServer(level,source,amount*2);
				level.playSound(null,position().x,position().y,position().z, ModSounds.PARRY, SoundSource.PLAYERS);
				return original.call(level,source,amount/4);
			}
		}
		return original.call(level, source, amount);
	}
}