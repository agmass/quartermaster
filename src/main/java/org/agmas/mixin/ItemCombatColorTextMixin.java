package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.agmas.init.ModComponents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Consumer;

@Mixin(Item.class)
public abstract class ItemCombatColorTextMixin {
    @WrapMethod(method = "getName(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/chat/Component;")
    public Component changeNameColor(ItemStack itemStack, Operation<Component> original) {
        if (itemStack.has(ModComponents.COMBAT_EFFECT_COLOR)) {
            return original.call(itemStack).copy().withColor(itemStack.get(ModComponents.COMBAT_EFFECT_COLOR).intValue());
        }
        return original.call(itemStack);
    }
    @WrapMethod(method = "appendHoverText")
    public void changeSoundWithWeapon(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag, Operation<Void> original) {
        if (itemStack.has(ModComponents.COMBAT_EFFECT_COLOR)) {
            consumer.accept(Component.translatable("quartermaster.combat_effect_tooltip").withColor(itemStack.get(ModComponents.COMBAT_EFFECT_COLOR).intValue()));
        }
    }


}
