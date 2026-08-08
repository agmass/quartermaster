package org.agmas.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.agmas.Quartermaster;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModEffects;

import java.awt.*;

public class DisarmedMobEffect extends MobEffect {
    public DisarmedMobEffect() {
        super(MobEffectCategory.HARMFUL, new Color(255, 210, 132).getRGB());
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity instanceof Player p) {
            p.getCooldowns().addCooldown(Quartermaster.ALL_DISARMED_ID.id, 4);
        }
        entity.setAttached(ModAttachments.DISARMED_ANIMATION_TICKS, entity.getEffect(ModEffects.DISARMED).getDuration()+20);
        return super.applyEffectTick(level, entity, amplifier);
    }
}