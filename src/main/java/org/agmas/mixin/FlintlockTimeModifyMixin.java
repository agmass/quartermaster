package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.agmas.init.ModItems;
import org.agmas.init.ModSounds;
import org.agmas.item.FlintlockItem;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Optional;

@Mixin(value = CrossbowItem.class, priority = 800)
public abstract class FlintlockTimeModifyMixin {

    @WrapMethod(method = "getChargeDuration")
    private static int changeDuration(ItemStack itemStack, LivingEntity livingEntity, Operation<Integer> original) {
        if (itemStack.is(ModItems.FLINTLOCK))
            return 15;
        return original.call(itemStack,livingEntity);
    }

    @ModifyArg(method = "shootProjectile", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
    private SoundEvent changeShootSound(SoundEvent soundEvent) {
        if (((CrossbowItem)(Object)this) instanceof FlintlockItem)
            return ModSounds.FLINTLOCK_SHOOT;
        return soundEvent;
    }


    @WrapMethod(method = "getChargingSounds")
    private
    //? if >=26.2 {
    /*static
    *///? }
    CrossbowItem.ChargingSounds changeSounds(ItemStack itemStack, Operation<CrossbowItem.ChargingSounds> original) {
        if (itemStack.is(ModItems.FLINTLOCK))
            return new CrossbowItem.ChargingSounds(
                    Optional.of(Holder.direct(ModSounds.FLINTLOCK_LOAD_0)),
                    Optional.empty(),
                    Optional.of(Holder.direct(ModSounds.FLINTLOCK_LOAD_1))
            );
        return original.call(itemStack);
    }



}
