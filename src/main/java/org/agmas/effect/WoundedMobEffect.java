package org.agmas.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.agmas.Quartermaster;
import org.agmas.init.ModDamageTypes;
import org.agmas.init.ModEffects;

import java.awt.*;

public class WoundedMobEffect extends MobEffect {
    public WoundedMobEffect() {
        super(MobEffectCategory.HARMFUL, new Color(199, 15, 15).getRGB());
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return amplifier >= 2 && duration % 5 == 0;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (!level.isClientSide()) {
            DamageSource damageSource = new DamageSource(
                    level.registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .get(ModDamageTypes.WOUND.identifier()).orElseThrow());

            entity.hurtServer(level, damageSource, 0.2f);
        }
        return super.applyEffectTick(level, entity, amplifier);
    }
}