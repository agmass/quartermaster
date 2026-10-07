package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.agmas.init.ModEnchants;
import org.agmas.init.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.function.Function;

@Mixin(ThrownTrident.class)
public abstract class TridentHitMixin {

    @Inject(method = "hitBlockEnchantmentEffects", at = @At("TAIL"))
    void noHurt(ServerLevel serverLevel, BlockHitResult blockHitResult, ItemStack itemStack, CallbackInfo ci) {
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(serverLevel, ModEnchants.NORTH_WIND), itemStack) > 0) {
            Vec3 vec3 = blockHitResult.getLocation();
            ((Entity) (Object) this).level().explode((Entity) (Object) this, null,
                    new SimpleExplosionDamageCalculator(true, false, Optional.of(Float.valueOf(1.22f)), BuiltInRegistries.BLOCK.get(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                    vec3.x(), vec3.y(), vec3.z(), 2f, false, Level.ExplosionInteraction.TRIGGER, ParticleTypes.GUST_EMITTER_SMALL, ParticleTypes.GUST_EMITTER_LARGE, WeightedList.of(),
                    SoundEvents.WIND_CHARGE_BURST);
        }
    }


}
