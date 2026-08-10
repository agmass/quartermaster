package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.agmas.entity.GreataxeProjectileEntity;
import org.agmas.init.ModEnchants;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentHelper.class)
public abstract class ModifyDamagEnchantsMixin {

    @WrapMethod(method = "modifyDamage")
    private static float changeDamage(ServerLevel serverLevel, ItemStack itemStack, Entity entity, DamageSource damageSource, float f, Operation<Float> original) {
        float o = original.call(serverLevel,itemStack,entity,damageSource,f);
        if (damageSource.getDirectEntity() instanceof LivingEntity livingEntity) {
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder((ServerLevel) entity.level(), ModEnchants.SINGLEHANDED), itemStack) > 0 && livingEntity.getOffhandItem().isEmpty()) {
                return o + 2;
            }
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder((ServerLevel) entity.level(), ModEnchants.BLADEDANCE), itemStack) > 0 && !entity.onGround()) {
            return o + 2;
        }
        return o;
    }


}
