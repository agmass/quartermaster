package org.agmas.client.render.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

public class ColorAttackSweepParticle extends SingleQuadParticle {
    private final SpriteSet sprites;

    ColorAttackSweepParticle(ClientLevel clientLevel, double d, double e, double f, double g, SpriteSet spriteSet) {
        super(clientLevel, d, e, f, 0.0, 0.0, 0.0, spriteSet.first());
        this.sprites = spriteSet;
        this.lifetime = 4;
        this.quadSize = 1.0F - (float)g * 0.5F;
        this.setSpriteFromAge(spriteSet);
    }

    //? if <26.1 {
    @Override
    public int getLightColor(float f) {
        return 15728880;
    }
    //? } else {
    /*@Override
    protected int getLightCoords(float a) {
        return 15728880;
    }
    *///? }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
        }
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class InstantProvider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprite;

        public InstantProvider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }
        @Override
        public @Nullable Particle createParticle(ColorParticleOption particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource) {
            ColorAttackSweepParticle spellParticle = new ColorAttackSweepParticle(clientLevel, d, e, f, g, this.sprite);
            spellParticle.setColor(particleOptions.getRed(), particleOptions.getGreen(), particleOptions.getBlue());
            return spellParticle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(
                SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource
        ) {
            return new ColorAttackSweepParticle(clientLevel, d, e, f, g, this.sprites);
        }
    }
}
