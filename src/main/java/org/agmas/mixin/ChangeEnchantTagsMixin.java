package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.agmas.Quartermaster;
import org.agmas.config.QMConfig;
import org.agmas.init.ModEnchants;
import org.agmas.init.tag.ModTags;
import org.agmas.porting.QMIdentifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Enchantment.class)
public class ChangeEnchantTagsMixin {
    @WrapMethod(method = "canEnchant")
    private boolean stopEnchantUpdate(ItemStack itemStack, Operation<Boolean> original) {
        Enchantment enchantment = ((Enchantment) (Object)this);
        if (Quartermaster.registryAccess != null) {
            Identifier id = Quartermaster.registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getKey(enchantment);
            if (id != null) {
                if (id.equals(QMIdentifier.of("frost_aspect").id) && FabricLoader.getInstance().isModLoaded("enchancement") && QMConfig.mergeFrost) {
                    return false;
                }
            }
        }
        return original.call(itemStack) || Quartermaster.isAllowedItem(itemStack,enchantment);
    }


    @WrapMethod(method = "isPrimaryItem")
    private boolean changePrimaryItem(ItemStack itemStack, Operation<Boolean> original) {
        Enchantment enchantment = ((Enchantment) (Object)this);
        if (Quartermaster.registryAccess != null) {
            Identifier id = Quartermaster.registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getKey(enchantment);
            if (id != null) {
                if (id.equals(QMIdentifier.of("frost_aspect").id) && FabricLoader.getInstance().isModLoaded("enchancement") && QMConfig.mergeFrost) {
                    return false;
                }
            }
        }
        return original.call(itemStack) || Quartermaster.isAllowedItem(itemStack,enchantment);
    }

}
