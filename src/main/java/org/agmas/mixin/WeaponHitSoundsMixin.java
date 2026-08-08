package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.agmas.item.util.CustomHitSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class WeaponHitSoundsMixin extends LivingEntity {
    protected WeaponHitSoundsMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }
    @WrapMethod(method = "playServerSideSound")
    public void changeSoundWithWeapon(SoundEvent soundEvent, Operation<Void> original) {
        if (getMainHandItem().getItem() instanceof CustomHitSounds) {
            CustomHitSounds customHitSounds = (CustomHitSounds) (getMainHandItem().getItem());
            SoundEvent newHitSound = null;
            if (soundEvent.equals(SoundEvents.PLAYER_ATTACK_KNOCKBACK) && customHitSounds.getSprintHitSound() != null) {
                newHitSound= customHitSounds.getSprintHitSound();
            }
            if (soundEvent.equals(SoundEvents.PLAYER_ATTACK_STRONG) && customHitSounds.getSprintHitSound() != null) {
                newHitSound= customHitSounds.getSprintHitSound();
            }
            if (soundEvent.equals(SoundEvents.PLAYER_ATTACK_SWEEP) && customHitSounds.getSweepHitSound() != null) {
                newHitSound= customHitSounds.getSweepHitSound();
            }
            if (soundEvent.equals(SoundEvents.PLAYER_ATTACK_WEAK) && customHitSounds.getWeakSound() != null) {
                newHitSound= customHitSounds.getWeakSound();
            }
            if (soundEvent.equals(SoundEvents.PLAYER_ATTACK_CRIT) && customHitSounds.getCritHitSound() != null) {
                newHitSound= customHitSounds.getCritHitSound();
            }
            if (newHitSound != null) {
                original.call(newHitSound);
                if (!customHitSounds.playOriginalHitSounds(soundEvent)) return;
            }
        }
        original.call(soundEvent);
    }


}
