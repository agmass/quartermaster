package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.agmas.init.ModComponents;
import org.agmas.init.ModParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class SweepAttackColorMixin {

    @Shadow
    public abstract ItemStack getWeaponItem();

    @WrapOperation(method = "doSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"))
    public int changeCritColors(ServerLevel instance, ParticleOptions particle, double x, double y, double z, int count, double xDist, double yDist, double zDist, double speed, Operation<Integer> original, @Local(argsOnly = true) Entity entity) {
;
        if (getWeaponItem().has(ModComponents.COMBAT_EFFECT_COLOR)) {
            return original.call(instance, ColorParticleOption.create(ModParticles.COLOR_SWEEP, getWeaponItem().get(ModComponents.COMBAT_EFFECT_COLOR).intValue()), x, y, z, count, xDist, yDist, zDist, speed);
        }
        return original.call(instance, particle, x, y, z, count, xDist, yDist, zDist, speed);
    }


}
