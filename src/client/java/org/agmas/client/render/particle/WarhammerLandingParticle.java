package org.agmas.client.render.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
//? if <=1.21.1 {

//? } else if <26.1 {
import net.minecraft.client.renderer.state.QuadParticleRenderState;
//? } else {
/*import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
 *///? }
import net.minecraft.core.particles.SimpleParticleType;
//? if >1.21.1 {
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.util.EasingType;
//? }
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.agmas.porting.QMSpellParticleOption;
import org.joml.Quaternionf;

public class WarhammerLandingParticle extends SingleQuadParticle {

    float maxSize = 2;
    WarhammerLandingParticle(ClientLevel clientLevel, double d, double e, double f, double g, SpriteSet spriteSet) {
        super(clientLevel, d, e, f, 0.0, 0.0, 0.0
                //? if >1.21.1
                //, spriteSet.first()
        );
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
    //? if >1.21.1
    //public void extract(QuadParticleRenderState quadParticleRenderState, Camera camera, float f) {
    //? if <=1.21.1
    public void render(VertexConsumer vertexConsumer, Camera camera, float f) {
        Quaternionf quaternionf = new Quaternionf();

        float ageInTicks = (age+f)/lifetime;

        //? if >1.21.1
        //quadSize = Mth.lerp(EasingType.OUT_EXPO.apply(ageInTicks), 0, maxSize*1.5f);
        //? if <=1.21.1
        quadSize = Mth.lerp(ageInTicks, 0, maxSize*1.5f);

        quaternionf.rotateX((float) Math.toRadians(-90));
        quaternionf.rotateZ((float) Mth.lerp(ageInTicks, 0, Math.toRadians(270)));

        //? if >1.21.1
        //this.extractRotatedQuad(quadParticleRenderState, camera, quaternionf, f);
        //? if <=1.21.1
        renderRotatedQuad(vertexConsumer,camera,quaternionf,f);

        quaternionf = new Quaternionf();

        quaternionf.rotateZ((float) Math.toRadians(-90));
        quaternionf.rotateX((float) Mth.lerp(ageInTicks, 0, Math.toRadians(270)));

        //? if >1.21.1
        //this.extractRotatedQuad(quadParticleRenderState, camera, quaternionf, f);
        //? if <=1.21.1
        renderRotatedQuad(vertexConsumer,camera,quaternionf,f);
        quaternionf.rotateX((float) Math.toRadians(180));
        //? if >1.21.1
        //this.extractRotatedQuad(quadParticleRenderState, camera, quaternionf, f);
        //? if <=1.21.1
        renderRotatedQuad(vertexConsumer,camera,quaternionf,f);

    }

    //? if <=1.21.1 {
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected float getU0() {
        return 0;
    }

    @Override
    protected float getU1() {
        return 1;
    }

    @Override
    protected float getV0() {
        return 0;
    }

    @Override
    protected float getV1() {
        return 1;
    }
    //? }


    //? if >1.21.1 {
    /*@Override
    public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }*/
    //? }

    @Environment(EnvType.CLIENT)
    public static class InstantProvider implements ParticleProvider<QMSpellParticleOption> {
        private final SpriteSet sprite;

        public InstantProvider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }
        @Override
        public Particle createParticle(QMSpellParticleOption particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i
                                                 //? if >1.21.1
                                                 //, RandomSource randomSource
        ) {
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
                SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i
                //? if >1.21.1
                //, RandomSource randomSource
        ) {
            return new WarhammerLandingParticle(clientLevel, d, e, f, g, this.sprites);
        }
    }
}
