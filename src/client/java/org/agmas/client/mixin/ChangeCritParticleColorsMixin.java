package org.agmas.client.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.agmas.init.ModComponents;
import org.agmas.init.ModParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPacketListener.class)
public abstract class ChangeCritParticleColorsMixin {

    @WrapOperation(method = "handleAnimate",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/ParticleEngine;createTrackingEmitter(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/particles/ParticleOptions;)V"))
    public void changeCritColors(ParticleEngine instance, Entity entity, ParticleOptions particle, Operation<Void> original) {
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.getLastDamageSource() != null) {
                if (livingEntity.getLastDamageSource().getWeaponItem() != null) {
                    if (livingEntity.getLastDamageSource().getWeaponItem().has(ModComponents.COMBAT_EFFECT_COLOR)) {
                        original.call(instance, entity, ColorParticleOption.create(ModParticles.COLOR_CRIT, livingEntity.getLastDamageSource().getWeaponItem().get(ModComponents.COMBAT_EFFECT_COLOR).intValue()));
                        return;
                    }
                }
            }
        }
        original.call(instance,entity,particle);
    }


}
