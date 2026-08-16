package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.agmas.init.ModAttributes;
import org.agmas.init.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class BambooWeaponsDoNoDamageMixin extends Entity {


    public BambooWeaponsDoNoDamageMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapMethod(method = "hurtServer")
    boolean noHurt(ServerLevel serverLevel, DamageSource damageSource, float f, Operation<Boolean> original) {
        if (damageSource.getDirectEntity() != null) {
            if (damageSource.getDirectEntity().getWeaponItem() != null) {
                if (damageSource.getDirectEntity().getWeaponItem().is(ModTags.NON_DAMAGING)) {
                    original.call(serverLevel, damageSource, 0f);
                    return true;
                }
            }
        }
        if (damageSource.getWeaponItem() != null) {
            if (damageSource.getWeaponItem().is(ModTags.NON_DAMAGING)) {
                original.call(serverLevel, damageSource, 0f);
                return true;
            }
        }
        return original.call(serverLevel,damageSource,f);
    }


}
