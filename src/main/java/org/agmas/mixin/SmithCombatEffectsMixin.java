package org.agmas.mixin;

import com.google.common.base.Predicates;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import org.agmas.init.ModColors;
import org.agmas.init.ModComponents;
import org.agmas.init.ModItems;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.Predicate;

@Mixin(SmithingMenu.class)
public abstract class SmithCombatEffectsMixin extends ItemCombinerMenu {
    public SmithCombatEffectsMixin(@Nullable MenuType<?> menuType, int i, Inventory inventory, ContainerLevelAccess containerLevelAccess, ItemCombinerMenuSlotDefinition itemCombinerMenuSlotDefinition) {
        super(menuType, i, inventory, containerLevelAccess, itemCombinerMenuSlotDefinition);
    }

    @WrapOperation(method = "createInputSlotDefinitions", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ItemCombinerMenuSlotDefinition$Builder;withSlot(IIILjava/util/function/Predicate;)Lnet/minecraft/world/inventory/ItemCombinerMenuSlotDefinition$Builder;"))
    private static ItemCombinerMenuSlotDefinition.Builder allowAnything(ItemCombinerMenuSlotDefinition.Builder instance, int slotIndex, int xPlacement, int yPlacement, Predicate<ItemStack> mayPlace, Operation<ItemCombinerMenuSlotDefinition.Builder> original) {
        return original.call(instance, slotIndex, xPlacement, yPlacement, Predicates.alwaysTrue());
    }
    @WrapMethod(method = "hasRecipeError")
    public boolean noError(Operation<Boolean> original) {
        if (getSlot(0).getItem().is(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE)) {
            return false;
        }
        return original.call();
    }
    @WrapMethod(method = "canMoveIntoInputSlots")
    public boolean canMoveAnythingIntoMenu(ItemStack itemStack, Operation<Boolean> original) {
        if (itemStack.is(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE) && !this.getSlot(0).hasItem()) {
            return true;
        }
        if (getSlot(0).hasItem()) {
            if (getSlot(0).getItem().is(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE)) {
                return true;
            }
        }
        return original.call(itemStack);
    }
    @Inject(method = "createResult", at = @At("TAIL"), cancellable = true)
    public void smithWeapons(CallbackInfo ci, @Local SmithingRecipeInput smithingRecipeInput) {
        if (smithingRecipeInput.template().is(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE)) {
            for (Map.Entry<Ingredient, Integer> entry : ModColors.trimColors.entrySet()) {
                if (entry.getKey().test(smithingRecipeInput.addition())) {
                    ItemStack stack = smithingRecipeInput.base().copyWithCount(1);
                    stack.set(ModComponents.COMBAT_EFFECT_COLOR, entry.getValue());
                    stack.set(ModComponents.BUSTER_COLOR, entry.getValue());
                    resultSlots.setItem(0, stack);
                    ci.cancel();
                    return;
                }
            }
            for (Map.Entry<TagKey<Item>, Integer> entry : ModColors.tagTrimColors.entrySet()) {
                //? if <26.1 {
                if (BuiltInRegistries.ITEM.get(entry.getKey()).get().contains(smithingRecipeInput.addition().getItemHolder())) {
                //? } else {
                /*if (BuiltInRegistries.ITEM.get(entry.getKey()).get().contains(smithingRecipeInput.addition().getItem().builtInRegistryHolder())) {
                *///? }
                    ItemStack stack = smithingRecipeInput.base().copyWithCount(1);
                    stack.set(ModComponents.COMBAT_EFFECT_COLOR, entry.getValue());
                    stack.set(ModComponents.BUSTER_COLOR, entry.getValue());
                    resultSlots.setItem(0, stack);
                    ci.cancel();
                    return;
                }
            }
        }
    }


}
