package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.agmas.init.ModAttributes;
import org.agmas.init.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
public abstract class ReduceFrostEffectTimesMixin {
	@WrapMethod(method = "setTicksFrozen")
	private void reduceTime(int i, Operation<Void> original) {
		if (((Entity) (Object) this) instanceof LivingEntity instance) {
			original.call((int)(i * (float)Math.max(instance.getAttribute(ModAttributes.FROST_TIME).getValue(), 0)));
			return;
		}
		original.call(i);
	}
}