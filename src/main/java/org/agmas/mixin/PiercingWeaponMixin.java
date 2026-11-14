package org.agmas.mixin;

import com.google.common.base.Predicates;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Either;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.component.AttackRange;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.agmas.init.ModEffects;
import org.agmas.init.ModEnchants;
//? if >=1.21.11
import net.minecraft.world.item.component.PiercingWeapon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

//? if >=1.21.11 {
@Mixin(PiercingWeapon.class)
public abstract class PiercingWeaponMixin {

    @Shadow
    public static boolean canHitEntity(Entity jabber, Entity target) {
        throw new UnsupportedOperationException("Implemented via mixin");
    }

    @Inject(method = "attack", at = @At("TAIL"))
    public void woundSpear(LivingEntity livingEntity, EquipmentSlot equipmentSlot, CallbackInfo ci) {
        boolean pogo = ModEnchants.getItemEnchant(livingEntity.getItemBySlot(equipmentSlot), livingEntity.level(), ModEnchants.BRITTLE) > 0;;

        //? if <26.1
        AttackRange range = livingEntity.entityAttackRange();
        //? if >=26.1
        //AttackRange range = livingEntity.getAttackRangeWith(livingEntity.getItemBySlot(equipmentSlot));
        if (pogo &&!ProjectileUtil.getHitResultOnViewVector(livingEntity, Predicates.alwaysTrue(), range
                //? if <26.1
                .maxRange()
                //? if >=26.1
                //.maxReach()
        ).getType().equals(HitResult.Type.MISS)) {
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

        boolean earthquake = ModEnchants.getItemEnchant(livingEntity.getItemBySlot(equipmentSlot), livingEntity.level(), ModEnchants.EARTH_ASPECT) > 0;

        if (earthquake) {
            Either<BlockHitResult, Collection<EntityHitResult>> hitEntitiesAlong = ProjectileUtil.getHitEntitiesAlong(
                    livingEntity,range, entity -> canHitEntity(livingEntity, entity), ClipContext.Block.COLLIDER
            );
            if (hitEntitiesAlong.right().isPresent()) {
                for (EntityHitResult entityHitResult : hitEntitiesAlong.right().get()) {
                    if (entityHitResult.getEntity() instanceof LivingEntity livingEntity1) {
                        livingEntity1.setDeltaMovement(livingEntity.getViewVector(0f));
                        if (livingEntity1 instanceof ServerPlayer player) {
                            player.connection.send(new ClientboundSetEntityMotionPacket(player));
                        }
                        livingEntity1.addEffect(new MobEffectInstance(ModEffects.PUSHED, 8));
                    }
                }
            }
        }
    }


}
//? } else {
/*@Mixin(Player.class)
public abstract class PiercingWeaponMixin {}
*///? }
