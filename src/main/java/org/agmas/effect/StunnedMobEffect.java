package org.agmas.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import java.awt.*;

public class StunnedMobEffect extends MobEffect {
    public StunnedMobEffect() {
        super(MobEffectCategory.HARMFUL, new Color(255, 239, 87).getRGB());
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
       return super.applyEffectTick(level, entity, amplifier);
    }
}