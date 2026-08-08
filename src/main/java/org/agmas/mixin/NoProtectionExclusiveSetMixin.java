package org.agmas.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import org.agmas.init.ModAttributes;
import org.agmas.init.tag.ModTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class NoProtectionExclusiveSetMixin {
    @Shadow
    @Final
    private HolderSet<Enchantment> exclusiveSet;

    @Inject(method = "areCompatible", at = @At("HEAD"), cancellable = true)
    private static void createAttribute(Holder<Enchantment> holder, Holder<Enchantment> holder2, CallbackInfoReturnable<Boolean> cir) {
        if (holder.is(ModTags.YOU_TWO_SHOULD_JUST_KISS_ALREADY) && holder2.is(ModTags.YOU_TWO_SHOULD_JUST_KISS_ALREADY)) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }


}
