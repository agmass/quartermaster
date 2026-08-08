package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.server.level.ServerLevel;
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

@Mixin(Player.class)
public abstract class ChangeAttackStrengthTickerToEstocMixin extends LivingEntity implements PlayerAcessor {
    protected ChangeAttackStrengthTickerToEstocMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    public int estocWoundChanceTicks = 0;
    @Unique
    public int lastEstocTicks = 0;

    @Unique
    @Override
    public void quartermaster$setEstocWoundChanceTicks(int estocWoundChanceTicks) {
        this.estocWoundChanceTicks = estocWoundChanceTicks;
    }

    @Unique
    @Override
    public int quartermaster$getEstocWoundChanceTicks() {
        return estocWoundChanceTicks;
    }

    @Inject(method = "getAttackStrengthScale", at = @At("HEAD"))
    public void changeToEstocTicker(float f, CallbackInfoReturnable<Float> cir) {
        if (getMainHandItem().getItem() instanceof EstocItem)
            attackStrengthTicker = getAttachedOrElse(ModAttachments.STORED_ESTOC_TICKS, 0);
    }
    @Inject(method = "tick", at = @At("TAIL"))
    public void toEstocTicker(CallbackInfo ci) {
        if (estocWoundChanceTicks > 0)
            estocWoundChanceTicks--;

        if (getMainHandItem().getItem() instanceof EstocItem)
            attackStrengthTicker = getAttachedOrElse(ModAttachments.STORED_ESTOC_TICKS, 0);
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;resetAttackStrengthTicker()V"))
    public void dontResetOnSwap(Player instance, Operation<Void> original) {
        int estocTicks = instance.getAttachedOrElse(ModAttachments.STORED_ESTOC_TICKS,0);
        original.call(instance);
        if (instance.getMainHandItem().is(ModTags.ESTOCS)) {
            if (!instance.level().isClientSide()) {
                if (lastEstocTicks < 10 && ((ServerLevel)instance.level()).getGameRules().get(ModGameRule.WOUND_WHEN_ESTOC_UNCHARGED_BOOLEAN_GAMERULE).booleanValue())
                {
                    EstocItem.wound(instance);
                }
            }
            estocWoundChanceTicks = 20;
        }
        instance.setAttached(ModAttachments.STORED_ESTOC_TICKS, estocTicks);
        lastEstocTicks = estocTicks;
    }

    @Inject(method = "resetAttackStrengthTicker", at = @At("TAIL"))
    public void attackStrengthReset(CallbackInfo ci) {
        setAttached(ModAttachments.STORED_ESTOC_TICKS, 0);
    }

    @Inject(method = "resetOnlyAttackStrengthTicker", at = @At("TAIL"))
    public void attackStrengthResetAgain(CallbackInfo ci) {
        setAttached(ModAttachments.STORED_ESTOC_TICKS, 0);
    }


}
