package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.agmas.init.ModComponents;
import org.agmas.init.ModSounds;
import org.agmas.item.MorningstarItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MorningstarShieldDisableMixin extends LivingEntity {

    @Shadow
    public abstract ItemCooldowns getCooldowns();

    protected MorningstarShieldDisableMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    //? if <1.21.11 {
    /*@WrapOperation(method = "blockUsingShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;disableShield()V"))
    public void morningstarMixin(Player instance, Operation<Void> original, @Local LivingEntity livingEntity) {
        if (livingEntity.getMainHandItem().has(ModComponents.FALL_DAMAGE_SHIELD_DISABLE_MULTIPLIER)) {
            float multiplier = livingEntity.getMainHandItem().get(ModComponents.FALL_DAMAGE_SHIELD_DISABLE_MULTIPLIER);
            float amount = (float) ((MorningstarItem.BASE_DISABLE_TICKS + (livingEntity.fallDistance*(multiplier*5))) / 20f);
            if (livingEntity.fallDistance > 5) {
                level().playSound(null,getX(),getY(),getZ(),ModSounds.STRONG_SHIELD_BREAK,getSoundSource());
            }

            livingEntity.resetFallDistance();
            livingEntity.setDeltaMovement(0,0.5f,0);
            getCooldowns().addCooldown(Items.SHIELD, (int)(amount*20));
            stopUsingItem();
            level().broadcastEntityEvent(this, (byte)30);;
        }
    }
    *///? } else {
    @WrapOperation(method = "blockUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getSecondsToDisableBlocking()F"))
    public float morningstarMixin(LivingEntity attacker, Operation<Float> original) {
        if (attacker.getMainHandItem().has(ModComponents.FALL_DAMAGE_SHIELD_DISABLE_MULTIPLIER)) {
            float multiplier = attacker.getMainHandItem().get(ModComponents.FALL_DAMAGE_SHIELD_DISABLE_MULTIPLIER);
            float amount = (float) ((MorningstarItem.BASE_DISABLE_TICKS + (attacker.fallDistance*(multiplier*5))) / 20f);
            if (attacker.fallDistance > 5) {
                level().playSound(null,getX(),getY(),getZ(),ModSounds.STRONG_SHIELD_BREAK,getSoundSource());
                ((ServerLevel)level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.defaultBlockState()),false,false,getX(),getY()+0.5f,getZ(),40,1,2,1,1);
            }

            attacker.resetFallDistance();
            attacker.setDeltaMovement(0,0.5f,0);
            return amount;
        }
        return original.call(attacker);
    }
    //? }


}
