package org.agmas.init;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.agmas.config.QMConfig;
import org.agmas.porting.QMIdentifier;

public class ModEnchants {

    public static ResourceKey<Enchantment> FROST_ASPECT = of("frost_aspect");
    public static final ResourceKey<Enchantment> EARTH_ASPECT = of("earth_aspect");

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

    public static final ResourceKey<Enchantment> EXCAVATE = of("excavate");
    public static final ResourceKey<Enchantment> BLADEDANCE = of("bladedance");
    public static final ResourceKey<Enchantment> EXPLOSIVE_KINECTIVITY = of("explosive_kinectivity");
    public static final ResourceKey<Enchantment> SINGLEHANDED = of("singlehanded");
    public static final ResourceKey<Enchantment> CHILL = of("chill");
    public static final ResourceKey<Enchantment> NORTH_WIND = of("north_wind");
    public static final ResourceKey<Enchantment> DEADLOCK = of("deadlock");


    public static final ResourceKey<Enchantment> WHATSAPP = of("whatsapp");

    private static ResourceKey<Enchantment> of(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, QMIdentifier.of(name).id);
    }
    private static ResourceKey<Enchantment> ofEnchancement(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, QMIdentifier.of("enchancement", name).id);
    }

    public static void init() {
        if (QMConfig.mergeFrost && FabricLoader.getInstance().isModLoaded("enchancement")) {
            FROST_ASPECT = ofEnchancement("frostbite");
        }
        ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, damageSource,a) -> {
            if (ModEnchants.getEnchant(entity, ModEnchants.EXPLOSIVE_KINECTIVITY) > 0) {
                entity.level().explode(entity,entity.getPosition(0f).x,entity.getPosition(0f).y,entity.getPosition(0f).z,6, Level.ExplosionInteraction.BLOCK);
            }
            return true;
        }));
        ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, source, damageTaken) -> {
            if (source.getWeaponItem() != null) {
                int flame = EnchantmentHelper.getItemEnchantmentLevel(enchantHolder((ServerLevel) entity.level(), Enchantments.FLAME), source.getWeaponItem());
                if (flame > 0) {
                    // Technically redundant, but this fixes mobs not giving cooked food on death
                    flame += 1;
                    entity.igniteForSeconds(flame * 4.0f);
                }
            }
            return true;
        }));
        ServerLivingEntityEvents.AFTER_DAMAGE.register(((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            if (source.getWeaponItem() != null) {
                if (!blocked) {
                    int flame = EnchantmentHelper.getItemEnchantmentLevel(enchantHolder((ServerLevel) entity.level(), Enchantments.FLAME), source.getWeaponItem());
                    if (flame > 0) {
                        // This does the exact same thing as flame, just did it this way to add more compatibility (specifically for tridents)
                        flame += 1;
                        entity.igniteForSeconds(flame*4.0f);
                    }
                    if (EnchantmentHelper.getItemEnchantmentLevel(enchantHolder((ServerLevel) entity.level(), ModEnchants.FROST_ASPECT), source.getWeaponItem()) > 0) {
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



    public static int getItemEnchant(ItemStack itemStack, Level level, ResourceKey<Enchantment> e) {
        if (!enabled(level,e)) return 0;
        return EnchantmentHelper.getItemEnchantmentLevel(enchantHolder(level, e), itemStack);
    }

    public static int getEnchant(LivingEntity livingEntity, ResourceKey<Enchantment> e) {
        if (!enabled(livingEntity.level(),e)) return 0;
        return EnchantmentHelper.getEnchantmentLevel(enchantHolder(livingEntity.level(), e), livingEntity);
    }

    public static boolean enabled(Level level, ResourceKey<Enchantment> e) {
        return level.registryAccess().lookup(Registries.ENCHANTMENT).get().containsKey(e);
    }
    private static Holder<Enchantment> enchantHolder(Level level, ResourceKey<Enchantment> e) {
        //? if >=1.21.11
        return level.registryAccess().lookup(Registries.ENCHANTMENT).get().wrapAsHolder(level.registryAccess().lookup(Registries.ENCHANTMENT).get().getValue(e));
        //? if <1.21.11
        //return level.registryAccess().lookup(Registries.ENCHANTMENT).get().getOrThrow(e);
    }
    public static boolean canCriticalAttack(Player entity) {
        return entity.fallDistance > 0.0
                && !entity.onGround()
                && !entity.onClimbable()
                && !entity.isInWater()
                //? if >=1.21.11
                && !entity.isMobilityRestricted()
                && !entity.isPassenger()
                && !entity.isSprinting();
    }

}
