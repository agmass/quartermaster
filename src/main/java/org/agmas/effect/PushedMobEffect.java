package org.agmas.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.awt.*;

public class PushedMobEffect extends MobEffect {
    public PushedMobEffect() {
        super(MobEffectCategory.HARMFUL, new Color(112, 255, 87).getRGB());
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}