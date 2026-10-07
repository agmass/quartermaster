package org.agmas.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
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
    public boolean applyEffectTick(
            //? if >=1.21.11
            ServerLevel level,
            LivingEntity entity, int amplifier) {
        //? if <1.21.11
        //Level level = entity.level();
        if (!level.isClientSide()) {
            DamageSource damageSource = new DamageSource(
                    level.registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .get(ModDamageTypes.WOUND
                                    //? if >=1.21.11
                                    .identifier()
                            ).orElseThrow());

            //? if >=1.21.11
            entity.hurtServer(level, damageSource, 0.2f);
            //? if <1.21.11
            //entity.hurt(damageSource, 0.2f);
        }
        return super.applyEffectTick(
                //? if >=1.21.11
                level,
                entity, amplifier);
    }
}