package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.agmas.init.ModAttributes;
import org.agmas.init.ModEffects;
import org.agmas.init.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class ReduceStunEffectTimesMixin {
	@WrapMethod(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z")
	private boolean reduceTime(MobEffectInstance mobEffectInstance, Entity entity, Operation<Boolean> original) {
		LivingEntity instance = ((LivingEntity) (Object)this);
		if (mobEffectInstance.getEffect().is(ModTags.IS_STUN)) {
			return original.call(mobEffectInstance.withScaledDuration((float) instance.getAttribute(ModAttributes.STUN_TIME).getValue()),entity);
		}
		return original.call(mobEffectInstance,entity);
	}
}