package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.agmas.duck.PlayerAcessor;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModGameRule;
import org.agmas.init.tag.ModItemLists;
import org.agmas.init.tag.ModTags;
import org.agmas.item.EstocItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class GiveMoreWeaponsMixin extends Entity {

    public GiveMoreWeaponsMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapMethod(method = "setItemSlot")
    public void changeWeapons(EquipmentSlot equipmentSlot, ItemStack itemStack, Operation<Void> original) {
        if (tickCount > 0 || !(((LivingEntity)(Object)this) instanceof Mob) || !equipmentSlot.equals(EquipmentSlot.MAINHAND)) {
            original.call(equipmentSlot,itemStack);
            return;
        }
        List<Item> replaceItems = null;

        // Thank god this only runs once per Mob with a weapon or else I would be compared to yanderedev

        if (itemStack.is(ModTags.WOODEN_HEAVY_WEAPONS)) {
            replaceItems = ModItemLists.wooden_heavy;
        } else if (itemStack.is(ModTags.WOODEN_LIGHT_WEAPONS)) {
            replaceItems = ModItemLists.wooden_light;
        } else if (itemStack.is(ModTags.STONE_HEAVY_WEAPONS)) {
            replaceItems = ModItemLists.stone_heavy;
        } else if (itemStack.is(ModTags.STONE_LIGHT_WEAPONS)) {
            replaceItems = ModItemLists.stone_light;
        } else if (itemStack.is(ModTags.COPPER_HEAVY_WEAPONS)) {
            replaceItems = ModItemLists.copper_heavy;
        } else if (itemStack.is(ModTags.COPPER_LIGHT_WEAPONS)) {
            replaceItems = ModItemLists.copper_light;
        } else if (itemStack.is(ModTags.IRON_HEAVY_WEAPONS)) {
            replaceItems = ModItemLists.iron_heavy;
        } else if (itemStack.is(ModTags.IRON_LIGHT_WEAPONS)) {
            replaceItems = ModItemLists.iron_light;
        } else if (itemStack.is(ModTags.GOLDEN_HEAVY_WEAPONS)) {
            replaceItems = ModItemLists.golden_heavy;
        } else if (itemStack.is(ModTags.GOLDEN_LIGHT_WEAPONS)) {
            replaceItems = ModItemLists.golden_light;
        } else if (itemStack.is(ModTags.DIAMOND_HEAVY_WEAPONS)) {
            replaceItems = ModItemLists.diamond_heavy;
        } else if (itemStack.is(ModTags.DIAMOND_LIGHT_WEAPONS)) {
            replaceItems = ModItemLists.diamond_light;
        }

        if (replaceItems != null) {
            original.call(EquipmentSlot.MAINHAND,replaceItems.get(random.nextInt(0,replaceItems.size())).getDefaultInstance());
        } else {
            original.call(equipmentSlot,itemStack);
        }
    }
}
