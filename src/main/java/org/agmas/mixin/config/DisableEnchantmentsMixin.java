package org.agmas.mixin.config;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.agmas.config.QMConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.stream.Stream;

@Mixin(EnchantmentHelper.class)
public class DisableEnchantmentsMixin {
    @WrapOperation(method = "updateEnchantments", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;get(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;"))
    private static Object stopEnchantUpdate(ItemStack instance, DataComponentType dataComponentType, Operation<Object> original) {
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable((ItemEnchantments) original.call(instance, dataComponentType));
        mutable.removeIf((enchantmentHolder -> QMConfig.disabledEnchants.contains(enchantmentHolder.unwrapKey().get().identifier().toString())));
        return mutable.toImmutable();
    }

    @WrapMethod(method = "getAvailableEnchantmentResults")
    private static List<EnchantmentInstance> sdadas(int i, ItemStack itemStack, Stream<Holder<Enchantment>> stream, Operation<List<EnchantmentInstance>> original) {
        return original.call(i,itemStack,stream.filter(enchantmentHolder -> !QMConfig.disabledEnchants.contains(enchantmentHolder.unwrapKey().get().identifier().toString())));
    }
}
