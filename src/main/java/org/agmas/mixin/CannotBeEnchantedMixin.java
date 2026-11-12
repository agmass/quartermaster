package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.agmas.Quartermaster;
import org.agmas.config.QMConfig;
import org.agmas.init.ModEnchants;
import org.agmas.init.tag.ModTags;
import org.agmas.porting.QMIdentifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FabricItemStack.class)
public interface CannotBeEnchantedMixin {
    @WrapMethod(method = "canBeEnchantedWith")
    private boolean stopEnchantUpdate(Holder<Enchantment> enchantment, EnchantingContext context, Operation<Boolean> original) {
        if (enchantment.is(QMIdentifier.of("frost_aspect").id) && FabricLoader.getInstance().isModLoaded("enchancement") && QMConfig.mergeFrost) {
            return false;
        }
        return original.call(enchantment, context);
    }

}
