package org.agmas.init;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;

public class ModEnchants {

    public static final ResourceKey<Enchantment> HOMERUN = of("homerun");
    public static final ResourceKey<Enchantment> SIESMIC = of("siesmic");
    public static final ResourceKey<Enchantment> CHARGED = of("charged");
    public static final ResourceKey<Enchantment> BUSTER = of("buster");
    public static final ResourceKey<Enchantment> FLING = of("fling");
    public static final ResourceKey<Enchantment> PULL = of("pull");
    public static final ResourceKey<Enchantment> SPLINTER = of("splinter");
    public static final ResourceKey<Enchantment> TAKEDOWN = of("takedown");
    public static final ResourceKey<Enchantment> POGO = of("pogo");
    public static final ResourceKey<Enchantment> PUNCTURE = of("puncture");
    public static final ResourceKey<Enchantment> BRITTLE = of("brittle");
    public static final ResourceKey<Enchantment> SHIELD_BASH = of("shield_bash");

    public static final ResourceKey<Enchantment> IMPACT_PROTECTION = of("impact_protection");
    public static final ResourceKey<Enchantment> STUN_PROTECTION = of("stun_protection");
    public static final ResourceKey<Enchantment> MAGIC_PROTECTION = of("magic_protection");
    public static final ResourceKey<Enchantment> FROST_PROTECTION = of("frost_protection");

    public static final ResourceKey<Enchantment> BLADEDANCE = of("bladedance");
    public static final ResourceKey<Enchantment> EXPLOSIVE_KINECTIVITY = of("explosive_kinectivity");
    public static final ResourceKey<Enchantment> GLACIAL = of("glacial");
    public static final ResourceKey<Enchantment> HEATWAVE = of("heatwave");
    public static final ResourceKey<Enchantment> EARTHQUAKE = of("earthquake");
    public static final ResourceKey<Enchantment> HELLFORK = of("hellfork");
    public static final ResourceKey<Enchantment> SINGLEHANDED = of("singlehanded");
    public static final ResourceKey<Enchantment> TIDAL_CHILL = of("tidal_chill");
    public static final ResourceKey<Enchantment> CHILL = of("chill");


    public static final ResourceKey<Enchantment> WHATSAPP = of("whatsapp");

    private static ResourceKey<Enchantment> of(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, QMIdentifier.of(name).id);
    }

    public static void init() {
        ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, damageSource,a) -> {
            if (EnchantmentHelper.getEnchantmentLevel(enchantHolder((ServerLevel) entity.level(),ModEnchants.EXPLOSIVE_KINECTIVITY), entity) > 0) {
                entity.level().explode(entity,entity.getPosition(0f).x,entity.getPosition(0f).y,entity.getPosition(0f).z,6, Level.ExplosionInteraction.BLOCK);
            }
            return true;
        }));
        ServerLivingEntityEvents.AFTER_DAMAGE.register(((entity, source, baseDamageTaken, damageTaken, blocked) -> {

            if (source.getWeaponItem() != null) {
                if (!blocked) {
                    if (EnchantmentHelper.getItemEnchantmentLevel(enchantHolder((ServerLevel) entity.level(), ModEnchants.HELLFORK), source.getWeaponItem()) > 0) {
                        entity.setRemainingFireTicks(90);
                    }
                    if (EnchantmentHelper.getItemEnchantmentLevel(enchantHolder((ServerLevel) entity.level(), ModEnchants.TIDAL_CHILL), source.getWeaponItem()) > 0) {
                        entity.setTicksFrozen(300);
                    }
                    if (EnchantmentHelper.getItemEnchantmentLevel(enchantHolder((ServerLevel) entity.level(), ModEnchants.CHILL), source.getWeaponItem()) > 0) {
                        entity.setTicksFrozen(300);
                    }
                }
            }
            if (source.getDirectEntity() instanceof LivingEntity livingEntity) {
                if (EnchantmentHelper.getEnchantmentLevel(enchantHolder(entity.level(), ModEnchants.WHATSAPP), livingEntity) > 0) {
                    entity.level().playSound(null,entity.getPosition(0f).x,entity.getPosition(0f).y,entity.getPosition(0f).z,ModSounds.WHATSAPP, entity.getSoundSource());
                }
            }

            if (source.getDirectEntity() instanceof Player player) {
                if (canCriticalAttack(player)) {
                    if (EnchantmentHelper.getEnchantmentLevel(enchantHolder((ServerLevel) player.level(),ModEnchants.HOMERUN), player) > 0 && entity.onGround()) {
                        entity.setDeltaMovement(0, 0.5f, 0);
                        //? if >=1.21.11 {
                        entity.needsSync = true;
                        //? } else {
                        /*entity.hasImpulse = true;
                         *///? }
                        if (entity instanceof ServerPlayer player2) {
                            player2.connection.send(new ClientboundSetEntityMotionPacket(player2));
                        }
                    }
                }
            }
        }));
    }
    public static Holder<Enchantment> enchantHolder(Level level, ResourceKey<Enchantment> e) {
        return level.registryAccess().lookup(Registries.ENCHANTMENT).get().wrapAsHolder(level.registryAccess().lookup(Registries.ENCHANTMENT).get().getValue(e));
    }
    public static boolean canCriticalAttack(Player entity) {
        return entity.fallDistance > 0.0
                && !entity.onGround()
                && !entity.onClimbable()
                && !entity.isInWater()
                && !entity.isMobilityRestricted()
                && !entity.isPassenger()
                && !entity.isSprinting();
    }

}
