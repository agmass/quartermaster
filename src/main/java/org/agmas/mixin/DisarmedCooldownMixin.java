package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
//? } else {
/*import net.minecraft.resources.ResourceLocation;
*///? }
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import org.agmas.Quartermaster;
import org.agmas.init.ModComponents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ItemCooldowns.class)
public abstract class DisarmedCooldownMixin {
    //? if >=1.21.11 {
    @Shadow
    @Final
    private Map<Identifier, ?> cooldowns;
    //? } else {
    /*@Shadow
    @Final
    private Map<ResourceLocation, ?> cooldowns;
    *///? }

    @WrapMethod(method = "isOnCooldown")
    public boolean noCooldownOnCharges(ItemStack itemStack, Operation<Boolean> original) {
        if (itemStack.has(ModComponents.CHARGES)) {
            return itemStack.get(ModComponents.CHARGES).intValue() <= 0;
        }
        return original.call(itemStack);
    }

    @WrapMethod(method = "getCooldownPercent")
    public float alwaysDisarmed(ItemStack item, float a, Operation<Float> original) {
        if (cooldowns.containsKey(Quartermaster.ALL_DISARMED_ID.id)) {
            return 1;
        }
        return original.call(item,a);
    }


}
