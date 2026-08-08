package org.agmas.client.render.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
//? if <26.1 {
import net.minecraft.client.renderer.state.QuadParticleRenderState;
//? } else {
/*import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
 *///? }
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.util.EasingType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.joml.Quaternionf;
import org.jspecify.annotations.Nullable;

public class WarhammerLandingParticle extends SingleQuadParticle {

    float maxSize = 2;
    WarhammerLandingParticle(ClientLevel clientLevel, double d, double e, double f, double g, SpriteSet spriteSet) {
        super(clientLevel, d, e, f, 0.0, 0.0, 0.0, spriteSet.first());
        this.lifetime = 40;
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
        setAlpha(1.0f-((float) age /lifetime));
        if (this.age++ >= this.lifetime) {
            this.remove();
        }
    }

    @Override
    public void extract(QuadParticleRenderState quadParticleRenderState, Camera camera, float f) {
        Quaternionf quaternionf = new Quaternionf();

        float ageInTicks = (age+f)/lifetime;

        quadSize = Mth.lerp(EasingType.OUT_EXPO.apply(ageInTicks), 0, maxSize*1.5f);

        quaternionf.rotateX((float) Math.toRadians(-90));
        quaternionf.rotateZ((float) Mth.lerp(ageInTicks, 0, Math.toRadians(270)));

        this.extractRotatedQuad(quadParticleRenderState, camera, quaternionf, f);

        quaternionf = new Quaternionf();

        quaternionf.rotateZ((float) Math.toRadians(-90));
        quaternionf.rotateX((float) Mth.lerp(ageInTicks, 0, Math.toRadians(270)));

        this.extractRotatedQuad(quadParticleRenderState, camera, quaternionf, f);
        quaternionf.rotateX((float) Math.toRadians(180));
        this.extractRotatedQuad(quadParticleRenderState, camera, quaternionf, f);

    }

    @Override
    public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class InstantProvider implements ParticleProvider<SpellParticleOption> {
        private final SpriteSet sprite;

        public InstantProvider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }
        @Override
        public @Nullable Particle createParticle(SpellParticleOption particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource) {
            WarhammerLandingParticle spellParticle = new WarhammerLandingParticle(clientLevel, d, e, f, g, this.sprite);
            spellParticle.setColor(particleOptions.getRed(), particleOptions.getGreen(), particleOptions.getBlue());
            spellParticle.maxSize = particleOptions.getPower();
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
            return new WarhammerLandingParticle(clientLevel, d, e, f, g, this.sprites);
        }
    }
}
