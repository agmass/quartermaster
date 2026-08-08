package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.agmas.init.ModAttachments;
import org.agmas.init.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class WarhammerForceUseMixin extends Entity {

	public WarhammerForceUseMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Shadow
	public abstract ItemStack getMainHandItem();

	@WrapMethod(method = "isUsingItem")
	public boolean alwaysUsing(Operation<Boolean> original) {
		if (getMainHandItem().is(ModTags.WARHAMMERS) && hasAttached(ModAttachments.WARHAMMER_CAST_TIME)) {
			return true;
		}
		return original.call();
	}
}