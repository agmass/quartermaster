package org.agmas.init;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.phys.Vec3;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;
import org.agmas.effect.DisarmedMobEffect;
import org.agmas.effect.QMMobEffect;
import org.agmas.effect.StunnedMobEffect;
import org.agmas.effect.WoundedMobEffect;

public class ModEffects {
    public static final Holder<MobEffect> STUNNED =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, QMIdentifier.of("stunned").id, new StunnedMobEffect());

    public static final Holder<MobEffect> DISARMED =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, QMIdentifier.of("disarmed").id, new DisarmedMobEffect());

    public static final Holder<MobEffect> WOUNDED =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, QMIdentifier.of("wounded").id, new WoundedMobEffect());

    public static final Holder<MobEffect> FROST_PROTECTION =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, QMIdentifier.of("frost_protection").id, new QMMobEffect(MobEffectCategory.BENEFICIAL, ModColors.FROST_PROTECTION).addAttributeModifier(ModAttributes.FROST_TIME, QMIdentifier.of("frost_protection").id, -1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static void init() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(((livingEntity, damageSource, baseDamageTaken) -> {
            if (damageSource.getDirectEntity() != null) {
                if (damageSource.getDirectEntity() instanceof LivingEntity attacker) {
                    if (attacker.hasEffect(ModEffects.DISARMED)) {
                        livingEntity.push(attacker.getViewVector(0.5f).scale(Math.clamp(baseDamageTaken*0.1,0.2,4)));
                        //? if >=1.21.11 {
                        livingEntity.needsSync = true;
                        //? } else {
                        /*livingEntity.hasImpulse = true;
                        *///? }
                        if (livingEntity instanceof ServerPlayer player) {
                            player.connection.send(new ClientboundSetEntityMotionPacket(player));
                        }
                        return false;
                    }
                }
            }
            return true;
        }));
    }
}
