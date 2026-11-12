package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.agmas.init.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class StunMixin {
	@WrapMethod(method = "setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V")
	private void parry(Vec3 vec3, Operation<Void> original) {
		if (((Entity)(Object)this) instanceof LivingEntity livingEntity) {
			if (livingEntity.hasEffect(ModEffects.STUNNED)) {
				if (vec3.y > 0) {
					original.call(vec3.multiply(0.9f,0f,0.9f));
                } else {
					original.call(vec3.multiply(0.9f,1f,0.9f));
                }
                return;
            }
		}
		original.call(vec3);
	}
	@WrapMethod(method = "getBlockSpeedFactor")
	private float a(Operation<Float> original) {
		return 1;
	}

	@WrapOperation(method = "moveRelative", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getInputVector(Lnet/minecraft/world/phys/Vec3;FF)Lnet/minecraft/world/phys/Vec3;"))
	private Vec3 changeInput(Vec3 input, float speed, float yRot, Operation<Vec3> original) {
		if (((Entity)(Object)this) instanceof LivingEntity livingEntity) {
			if (!(livingEntity instanceof Player)) {
				return Vec3.ZERO;
			}
			if (livingEntity.hasEffect(ModEffects.PUSHED)) {
				return original.call(input.multiply(0.25f,0.25f,0.25f),speed,yRot);
			}
		}
		return original.call(input,speed,yRot);
	}
}