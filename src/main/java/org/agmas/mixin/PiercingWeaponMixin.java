package org.agmas.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.component.PiercingWeapon;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.HitResult;
import org.agmas.init.ModEnchants;
import org.apache.commons.lang3.function.Predicates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiercingWeapon.class)
public abstract class PiercingWeaponMixin {

    @Inject(method = "attack", at = @At("TAIL"))
    public void woundSpear(LivingEntity livingEntity, EquipmentSlot equipmentSlot, CallbackInfo ci) {
        boolean pogo = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(livingEntity.level(), ModEnchants.POGO),livingEntity.getItemBySlot(equipmentSlot)) > 0;

        if (pogo &&!ProjectileUtil.getHitResultOnViewVector(livingEntity, Predicates.truePredicate(),4).getType().equals(HitResult.Type.MISS)) {
            livingEntity.setDeltaMovement(livingEntity.getViewVector(0f).multiply(-1.5f,-0.75f,-1.5f));
            //? if >=1.21.11 {
            livingEntity.needsSync = true;
            //? } else {
            /*livingEntity.hasImpulse = true;
             *///? }
            if (livingEntity instanceof ServerPlayer player2) {
                player2.connection.send(new ClientboundSetEntityMotionPacket(player2));
            }
        }
    }


}
