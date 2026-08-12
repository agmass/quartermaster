package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.agmas.duck.PlayerAcessor;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModGameRule;
import org.agmas.init.tag.ModTags;
import org.agmas.item.EstocItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class LivingEntityEstocMixin extends Entity {
    public LivingEntityEstocMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "swing(Lnet/minecraft/world/InteractionHand;)V", at = @At("TAIL"))
    public void attackStrengthReset(CallbackInfo ci) {
        setAttached(ModAttachments.STORED_ESTOC_TICKS, 0);
    }
}
