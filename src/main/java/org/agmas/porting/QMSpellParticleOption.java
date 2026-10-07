package org.agmas.porting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
//? if >=1.21.11
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
//? if <=1.21.1
 //import net.minecraft.util.FastColor;

public class QMSpellParticleOption implements ParticleOptions {
    private final ParticleType<QMSpellParticleOption> type;
    private final int color;
    private final float power;

    public static MapCodec<QMSpellParticleOption> codec(ParticleType<QMSpellParticleOption> particleType) {
        return RecordCodecBuilder.mapCodec((instance) -> instance.group(
                //? if >1.21.1
                ExtraCodecs.RGB_COLOR_CODEC
                //? if <=1.21.1
                //ExtraCodecs.ARGB_COLOR_CODEC
                .optionalFieldOf("color", -1).forGetter((spellParticleOption) -> spellParticleOption.color), Codec.FLOAT.optionalFieldOf("power", 1.0F).forGetter((spellParticleOption) -> spellParticleOption.power)).apply(instance, (integer, float_) -> new QMSpellParticleOption(particleType, integer, float_)));
    }

    public static StreamCodec<? super ByteBuf, QMSpellParticleOption> streamCodec(ParticleType<QMSpellParticleOption> particleType) {
        return StreamCodec.composite(ByteBufCodecs.INT, (spellParticleOption) -> spellParticleOption.color, ByteBufCodecs.FLOAT, (spellParticleOption) -> spellParticleOption.power, (integer, float_) -> new QMSpellParticleOption(particleType, integer, float_));
    }

    private QMSpellParticleOption(ParticleType<QMSpellParticleOption> particleType, int i, float f) {
        this.type = particleType;
        this.color = i;
        this.power = f;
    }

    public ParticleType<QMSpellParticleOption> getType() {
        return this.type;
    }

    public float getRed() {
        //? if >1.21.1
        return (float)ARGB.red(this.color) / 255.0F;
        //? if <=1.21.1
        //return (float) FastColor.ARGB32.red(this.color) / 255.0F;
    }

    public float getGreen() {
        //? if >1.21.1
        return (float) ARGB.green(this.color) / 255.0F;
        //? if <=1.21.1
        //return (float) FastColor.ARGB32.green(this.color) / 255.0F;
    }

    public float getBlue() {
        //? if >1.21.1
        return (float)ARGB.blue(this.color) / 255.0F;
        //? if <=1.21.1
        //return (float) FastColor.ARGB32.blue(this.color) / 255.0F;
    }

    public float getPower() {
        return this.power;
    }

    public static QMSpellParticleOption create(ParticleType<QMSpellParticleOption> particleType, int i, float f) {
        return new QMSpellParticleOption(particleType, i, f);
    }

    public static QMSpellParticleOption create(ParticleType<QMSpellParticleOption> particleType, float f, float g, float h, float i) {
        //? if >1.21.1
        return create(particleType, ARGB.colorFromFloat(1.0F, f, g, h), i);
        //? if <=1.21.1
        //return create(particleType, FastColor.ARGB32.colorFromFloat(1.0F, f, g, h), i);
    }
}
