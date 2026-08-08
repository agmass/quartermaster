package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.agmas.init.ModEffects;
import org.spongepowered.asm.mixin.Mixin;

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
}