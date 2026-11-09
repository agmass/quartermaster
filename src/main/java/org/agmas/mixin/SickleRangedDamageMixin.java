package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.agmas.init.*;
import org.agmas.item.RapierItem;
import org.agmas.item.SicklesItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class SickleRangedDamageMixin extends Entity {
	public SickleRangedDamageMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurtOrSimulate(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
	private boolean changeDamage(Entity instance, DamageSource source, float damage, Operation<Boolean> original) {
		if (source.getWeaponItem() != null && source.getEntity() != null) {
			if (source.getWeaponItem().getItem() instanceof SicklesItem) {
				if (source.getEntity() instanceof LivingEntity le && instance instanceof LivingEntity victim) {
					Vec3 oldVelocity = victim.getDeltaMovement();
					if (!le.getMainHandItem().is(le.getOffhandItem().getItem())) {
						boolean b = original.call(instance,source,0f);
						victim.setDeltaMovement(oldVelocity);
						return b;
					}
					float f = SicklesItem.calculateFalloff(le, victim);
					boolean b = original.call(instance,source,damage*f);
					victim.setDeltaMovement(oldVelocity.lerp(victim.getDeltaMovement(), f));
					//? if >=26.3
					//victim.setInvulnerableTime(0);
					//? if <26.3
					victim.invulnerableTime = 0;
					return b;
				}
			}
		}
		return original.call(instance,source,damage);
	}
}