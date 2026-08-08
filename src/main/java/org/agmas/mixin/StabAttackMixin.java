package org.agmas.mixin;

import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.component.PiercingWeapon;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.agmas.init.ModEnchants;
import org.agmas.item.EstocItem;
import org.apache.commons.lang3.function.Predicates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class StabAttackMixin extends LivingEntity {

    protected StabAttackMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "stabAttack", at = @At("TAIL"))
    public void woundSpear(EquipmentSlot equipmentSlot, Entity entity, float f, boolean bl, boolean bl2, boolean bl3, CallbackInfoReturnable<Boolean> cir) {
        boolean puncture = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.PUNCTURE),getItemBySlot(equipmentSlot)) > 0;
        if (puncture) {
            if (entity instanceof LivingEntity livingEntity)
                EstocItem.wound(livingEntity);
            entity.setDeltaMovement(getViewVector(0f).multiply(-1f,-1f,-1f));
            //? if >=1.21.11 {
            entity.needsSync = true;
            //? } else {
            /*entity.hasImpulse = true;
             *///? }
            if (entity instanceof ServerPlayer player2) {
                player2.connection.send(new ClientboundSetEntityMotionPacket(player2));
            }
        }
    }


}
