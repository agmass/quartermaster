package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
//? if >=26.3
//import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.agmas.init.ModAttributes;
import org.agmas.init.ModEnchants;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(Block.class)
public abstract class NoBlockDropsMixin {


    //? if <26.3 {
    @WrapMethod(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;")
    private static List<ItemStack> createAttribute(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack itemStack, Operation<List<ItemStack>> original) {
    //? } else {
    /*@WrapMethod(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemInstance;)Ljava/util/List;")
    private static List<ItemStack> createAttribute(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, BlockEntity blockEntity, Entity entity, ItemInstance itemStack, Operation<List<ItemStack>> original) {
    *///? }
        if (entity != null) {
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder((ServerLevel) entity.level(), ModEnchants.EXCAVATE), itemStack) > 0) {
                return List.of();
            }
        }
        return original.call(blockState,serverLevel,blockPos,blockEntity,entity,itemStack);
    }


}
