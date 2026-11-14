package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.agmas.init.tag.ModTags;
import org.agmas.item.SicklesItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class SwingTheOtherArmSomeOfTheTimeMixin extends Entity {

	public SwingTheOtherArmSomeOfTheTimeMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@WrapMethod(method = "swing(Lnet/minecraft/world/InteractionHand;Z)V")
	private void parry(InteractionHand interactionHand, boolean bl, Operation<Void> original) {
		if (getWeaponItem() != null) {
			if (getWeaponItem().getItem() instanceof SicklesItem) {
				original.call(Math.random() > 0.5 ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND,bl);
				return;
			}
		}
		original.call(interactionHand,bl);
	}
}