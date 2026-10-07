package org.agmas.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
//? if >=26.3
//import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.agmas.Quartermaster;
import org.agmas.init.ModEffects;
import org.agmas.init.ModEnchants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ShieldBashMixin extends Entity {


    @Shadow
    public abstract ItemStack getUseItem();

    @Unique
    public int shieldBashTicks = 0;


    //? if >=1.21.11 {
    @Shadow
    public abstract ItemStack getItemBlockingWith();
    //? } else {

    /*@Unique
    public ItemStack getItemBlockingWith() {
        if (getUseItem().is(Items.SHIELD)) {
            return getUseItem();
        }
        return null;
    }
    *///? }

    public ShieldBashMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tickShieldBashTicks(CallbackInfo ci) {
        if (shieldBashTicks > 0) {
            shieldBashTicks--;
        }
    }
    //? if <26.3 {
    @Inject(method = "swing(Lnet/minecraft/world/InteractionHand;Z)V", at = @At("HEAD"))
    public void alwaysDisarmed(InteractionHand interactionHand, boolean bl, CallbackInfo ci) {
    //? } else {
    /*@Inject(method = "swing", at = @At("HEAD"))
    public void alwaysDisarmed(InteractionHand hand, SwingAnimation animation, boolean sendToSwingingEntity, CallbackInfoReturnable<Boolean> cir) {
    *///? }
        if (getItemBlockingWith() != null && !level().isClientSide()) {
            boolean shieldBash = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.SHIELD_BASH),getItemBlockingWith()) > 0;
            if (shieldBash && shieldBashTicks <= 0) {
                addDeltaMovement(getViewVector(0f).multiply(1,0,1));

                //? if >=1.21.11 {
                needsSync = true;
                //? } else {
                /*hasImpulse = true;
                 *///? }
                if (((LivingEntity)(Object)this) instanceof ServerPlayer player2) {
                    player2.connection.send(new ClientboundSetEntityMotionPacket(player2));
                }

                for (Entity entity : level().getEntities(this, getBoundingBox().expandTowards(getDeltaMovement().multiply(3, 3, 3)))) {
                    entity.hurt(damageSources().mobAttack((LivingEntity)(Object)this),6f);
                    if (entity instanceof LivingEntity livingEntity) {
                        livingEntity.addEffect(new MobEffectInstance(ModEffects.STUNNED, Quartermaster.STUNNED_TICKS, 0));
                    }
                }

                shieldBashTicks = 50;
            }
        }
    }


}
