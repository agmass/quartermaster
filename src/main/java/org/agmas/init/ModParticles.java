package org.agmas.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.core.registries.BuiltInRegistries;
import org.agmas.QMIdentifier;

public class ModParticles {

    public static final ParticleType<ColorParticleOption> COLOR_SWEEP = FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec);
    public static final ParticleType<ColorParticleOption> COLOR_CRIT = FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec);
    public static final ParticleType<SpellParticleOption> LANDING = FabricParticleTypes.complex(SpellParticleOption::codec, SpellParticleOption::streamCodec);

    public static void init() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, QMIdentifier.of("color_crit").id, COLOR_CRIT);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, QMIdentifier.of("color_sweep").id, COLOR_SWEEP);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, QMIdentifier.of("landing").id, LANDING);
    }
}
