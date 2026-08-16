package org.agmas.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;

public class ModSounds {
    public static final SoundEvent CUTLASS_CRIT = registerSound("cutlass_crit");
    public static final SoundEvent CUTLASS_SWEEP = registerSound("cutlass_sweep");

    public static final SoundEvent STRONG_SHIELD_BREAK = registerSound("strong_shield_break");

    public static final SoundEvent PARRY = registerSound("parry");

    public static final SoundEvent GREATAXE_HIT = registerSound("greataxe_hit");
    public static final SoundEvent GREATAXE_USE = registerSound("greataxe_use");

    public static final SoundEvent WHATSAPP = registerSound("whatsappdanger");

    public static final SoundEvent FLINTLOCK_LOAD_0 = registerSound("flintlock_load_0");
    public static final SoundEvent FLINTLOCK_LOAD_1 = registerSound("flintlock_load_1");
    public static final SoundEvent FLINTLOCK_SHOOT = registerSound("flintlock_shoot");

    private static SoundEvent registerSound(String id) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, QMIdentifier.of(id).id, SoundEvent.createVariableRangeEvent(QMIdentifier.of(id).id));
    }

    public static void init() {}
}
