package org.agmas.mixin.config;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.enchantment.Enchantment;
import org.agmas.Quartermaster;
import org.agmas.config.QMConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.stream.Stream;

@Mixin(CreativeModeTabs.class)
public class DisableEnchantmentsInCreativeTabMixin {
    @WrapOperation(method = "generateEnchantmentBookTypesAllLevels", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/HolderLookup;listElements()Ljava/util/stream/Stream;"))
    private static Stream<Holder.Reference<Enchantment>> noBooks(HolderLookup instance, Operation<Stream<Holder.Reference<Enchantment>>> original) {
        if (!Quartermaster.loadedConfig)
            QMConfig.parse();
        return original.call(instance).filter((enchantmentReference)-> !QMConfig.disabledEnchants.contains(enchantmentReference.key().identifier().toString()));
    }

    @WrapOperation(method = "generateEnchantmentBookTypesOnlyMaxLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/HolderLookup;listElements()Ljava/util/stream/Stream;"))
    private static Stream<Holder.Reference<Enchantment>> noBooks2(HolderLookup instance, Operation<Stream<Holder.Reference<Enchantment>>> original) {
        if (!Quartermaster.loadedConfig)
            QMConfig.parse();
        return original.call(instance).filter((enchantmentReference)-> {
                    Log.info(LogCategory.GENERAL, QMConfig.disabledEnchants.contains(enchantmentReference.key().identifier().toString())+"");
                return !QMConfig.disabledEnchants.contains(enchantmentReference.key().identifier().toString());
            }
        );
    }
}
