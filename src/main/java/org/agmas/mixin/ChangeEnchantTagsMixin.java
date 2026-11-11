package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.agmas.Quartermaster;
import org.agmas.config.QMConfig;
import org.agmas.init.ModEnchants;
import org.agmas.init.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Enchantment.class)
public class ChangeEnchantTagsMixin {
    @WrapMethod(method = "canEnchant")
    private boolean stopEnchantUpdate(ItemStack itemStack, Operation<Boolean> original) {
        if (Quartermaster.registryAccess != null) {
            Enchantment enchantment = ((Enchantment) (Object)this);
            Identifier id = Quartermaster.registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getKey(enchantment);
            if (id != null) {
                if (id.equals(Enchantments.FIRE_ASPECT.identifier())) {
                    return original.call(itemStack) || itemStack.is(ModTags.ELEMENTAL_WEAPONS);
                }
                if (id.equals(Enchantments.FLAME.identifier())) {
                    return original.call(itemStack) || itemStack.is(ModTags.ELEMENTAL_RANGED_WEAPONS);
                }
                if (id.equals(ModEnchants.FROST_ASPECT.identifier())) { // Looks redundant; but needed for enchancement compat!
                    return original.call(itemStack) || itemStack.is(ModTags.ELEMENTAL_WEAPONS);
                }
            }
        }
        return original.call(itemStack);
    }

}
