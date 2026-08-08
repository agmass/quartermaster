package org.agmas.item.util;

import net.minecraft.sounds.SoundEvent;

public interface CustomHitSounds {
    default SoundEvent getSprintHitSound() {return null;}
    default SoundEvent getSweepHitSound() {return null;}
    default SoundEvent getCritHitSound() {return null;}
    default SoundEvent getWeakSound() {return null;}
    boolean playOriginalHitSounds(SoundEvent soundEvent);
}
