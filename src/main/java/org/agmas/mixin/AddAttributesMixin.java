package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import org.agmas.init.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class AddAttributesMixin extends Entity {


    public AddAttributesMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapMethod(method = "createLivingAttributes")
    private static AttributeSupplier.Builder createAttribute(Operation<AttributeSupplier.Builder> original) {
        return original.call().add(ModAttributes.FROST_TIME, 1).add(ModAttributes.STUN_TIME, 1);
    }


}
