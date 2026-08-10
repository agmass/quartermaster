package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.agmas.init.ModItems;
import org.agmas.init.ModSounds;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(value = CrossbowItem.class, priority = 800)
public abstract class FlintlockTimeModifyMixin {

    @WrapMethod(method = "getChargeDuration")
    private static int changeDuration(ItemStack itemStack, LivingEntity livingEntity, Operation<Integer> original) {
        if (itemStack.is(ModItems.FLINTLOCK))
            return 15;
        return original.call(itemStack,livingEntity);
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
