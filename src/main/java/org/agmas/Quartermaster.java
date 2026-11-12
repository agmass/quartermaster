package org.agmas;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.HolderSet;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
//? if <26.3 {
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
//? } else {
/*import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
*///? }
import org.agmas.config.QMConfig;
import org.agmas.init.ModEnchants;
import org.agmas.init.*;
import org.agmas.init.tag.ModTags;
import org.agmas.porting.QMIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quartermaster implements ModInitializer {
	public static final String MOD_ID = "quartermaster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final QMIdentifier ALL_DISARMED_ID = QMIdentifier.of("all");

	public static int timeSinceShooting = 0;
	public static int STUNNED_TICKS = 20*5;
	public static int DISARMED_TICKS = 50;
	public static RegistryAccess registryAccess;
	@Override
	public void onInitialize() {
		QMConfig.parse();
		ModItems.init();
		ModComponents.init();
		ModColors.init();
		ModGameRule.init();
		ModSounds.init();
		ModAttachments.init();
		ModPackets.init();
		ModEnchants.init();
		ModEntities.init();
		ModTags.init();
		ModPotions.init();
		ModParticles.init();
		ModEffects.init();
		ModDamageTypes.init();
		ModAttributes.init();
		ModTrades.init();
		QMConfig.write();

		ServerTickEvents.END_SERVER_TICK.register(((server) -> {
			registryAccess = server.registries().compositeAccess();
		}));

		LootTableEvents.MODIFY.register((key, builder, lootTableSource, provider)->{
			if (lootTableSource.isBuiltin() &&
					(key.equals(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_RARE)
							|| key.equals(BuiltInLootTables.BURIED_TREASURE))) {
				builder.modifyPools((m)->{
					m.add(LootItem.lootTableItem(ModItems.RUINED_HANDLE));
				});
			}
			if (lootTableSource.isBuiltin() && key.equals(BuiltInLootTables.BURIED_TREASURE)) {
				builder.modifyPools((m)->{
					m.add(LootItem.lootTableItem(ModItems.FLINTLOCK).setWeight(4));
					m.add(LootItem.lootTableItem(ModItems.AMMUNITION).setWeight(7));
				});
			}
			if (lootTableSource.isBuiltin() &&
					(key.equals(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_COMMON)
							|| key.equals(BuiltInLootTables.BURIED_TREASURE)
							|| key.equals(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE)
							|| key.equals(BuiltInLootTables.SHIPWRECK_TREASURE)
							|| key.equals(BuiltInLootTables.ANCIENT_CITY)
							|| key.equals(BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY)
							|| key.equals(BuiltInLootTables.DESERT_PYRAMID))) {
				builder.modifyPools((m)->{
					m.add(LootItem.lootTableItem(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE));
				});
			}

			// Guarantee Shield Books in Bastions

			if (lootTableSource.isBuiltin() &&
					key.equals(BuiltInLootTables.BASTION_BRIDGE) || key.equals(BuiltInLootTables.BASTION_OTHER) || key.equals(BuiltInLootTables.BASTION_HOGLIN_STABLE)) {
				builder.withPool(LootPool.lootPool()
						.setRolls(
								//? if <26.3
								ConstantValue.exactly(1)
								//? if >=26.3
								//ContextIntProviders.exactly(1)
						)
						.add(
								LootItem.lootTableItem(Items.BOOK)
										.setWeight(1)
										.apply(new EnchantRandomlyFunction.Builder().withOneOf(
												HolderSet.direct(
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.BRITTLE),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.SHIELD_BASH)
												)
										))
						));
			}

			// Guaranteed Books for Enchancements

			if (lootTableSource.isBuiltin() &&
					key.equals(BuiltInLootTables.END_CITY_TREASURE)) {
				builder.withPool(LootPool.lootPool()
						.setRolls(
								//? if <26.3
								ConstantValue.exactly(1)
								//? if >=26.3
								//ContextIntProviders.exactly(1)
						)
						.add(
								LootItem.lootTableItem(Items.BOOK)
										.setWeight(1)
										.apply(new EnchantRandomlyFunction.Builder().withOneOf(
												HolderSet.direct(
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.EXPLOSIVE_KINECTIVITY)
												)
										))
						));
			}
			if (FabricLoader.getInstance().isModLoaded("enchancement")) {
				if (lootTableSource.isBuiltin() &&
						key.equals(BuiltInLootTables.PILLAGER_OUTPOST)) {
					builder.withPool(LootPool.lootPool()
							.setRolls(
									//? if <26.3
									ConstantValue.exactly(1)
									//? if >=26.3
									//ContextIntProviders.exactly(1)
							)
							.add(
									LootItem.lootTableItem(Items.BOOK)
											.setWeight(1)
											.apply(new EnchantRandomlyFunction.Builder().withOneOf(
													HolderSet.direct(
															provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.FLING)
													)
											))
							));
				}
				if (lootTableSource.isBuiltin() &&
						key.equals(BuiltInLootTables.WOODLAND_MANSION)) {
					builder.withPool(LootPool.lootPool()
							.setRolls(
									//? if <26.3
									ConstantValue.exactly(1)
									//? if >=26.3
									//ContextIntProviders.exactly(1)
							)
							.add(
									LootItem.lootTableItem(Items.BOOK)
											.setWeight(1)
											.apply(new EnchantRandomlyFunction.Builder().withOneOf(
													HolderSet.direct(
															provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.PULL),
															provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.SIESMIC)
													)
											))
							));
				}
				if (lootTableSource.isBuiltin() &&
						(key.equals(BuiltInLootTables.IGLOO_CHEST) || key.equals(BuiltInLootTables.VILLAGE_TAIGA_HOUSE) || key.equals(BuiltInLootTables.VILLAGE_SNOWY_HOUSE) || key.equals(BuiltInLootTables.VILLAGE_PLAINS_HOUSE))) {
					builder.withPool(LootPool.lootPool()
							.setRolls(
									//? if <26.3
									UniformGenerator.between(0, 1)
									//? if >=26.3
									//ContextIntProviders.between(0,1)
							)
							.add(
									LootItem.lootTableItem(Items.BOOK)
											.setWeight(1)
											.apply(new EnchantRandomlyFunction.Builder().withOneOf(
													HolderSet.direct(
															provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.FROST_PROTECTION)
													)
											))
							));
				}

				if (lootTableSource.isBuiltin() &&
						(key.equals(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE) || key.equals(BuiltInLootTables.VILLAGE_DESERT_HOUSE) || key.equals(BuiltInLootTables.VILLAGE_PLAINS_HOUSE))) {
					builder.withPool(LootPool.lootPool()
							.setRolls(
									//? if <26.3
									UniformGenerator.between(0, 1)
									//? if >=26.3
									//ContextIntProviders.between(0,1)
							)
							.add(
									LootItem.lootTableItem(Items.BOOK)
											.setWeight(1)
											.apply(new EnchantRandomlyFunction.Builder().withOneOf(
													HolderSet.direct(
															provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.FROST_PROTECTION)
													)
											))
							));
				}
				if (lootTableSource.isBuiltin() &&
						key.equals(BuiltInLootTables.ABANDONED_MINESHAFT)) {
					builder.withPool(LootPool.lootPool()
							.setRolls(
									//? if <26.3
									UniformGenerator.between(0, 1)
									//? if >=26.3
									//ContextIntProviders.between(0,1)
							)
							.add(
									LootItem.lootTableItem(Items.BOOK)
											.setWeight(1)
											.apply(new EnchantRandomlyFunction.Builder().withOneOf(
													HolderSet.direct(
															provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.BUSTER),
															provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.CHARGED)
													)
											))
							));
				}
			}
			if (lootTableSource.isBuiltin() &&
					key.equals(BuiltInLootTables.TRIAL_CHAMBERS_REWARD) || key.equals(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS)) {
				builder.withPool(LootPool.lootPool()
						.setRolls(
								//? if <26.3
								UniformGenerator.between(0,1)
								//? if >=26.3
								//ContextIntProviders.between(0,1)
						)
						.add(
								LootItem.lootTableItem(Items.BOOK)
										.setWeight(1)
										.apply(new EnchantRandomlyFunction.Builder().withOneOf(
												HolderSet.direct(
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.BRITTLE),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.SHIELD_BASH),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.FROST_ASPECT),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.BUSTER),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.CHARGED),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.FLING),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.SIESMIC),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.PUNCTURE),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.PULL)
												)
										))
						));
			}
		});

	}

	public static boolean isAllowedItem(ItemStack itemStack, Enchantment enchantment) {
		if (Quartermaster.registryAccess != null) {
			Identifier id = Quartermaster.registryAccess.lookupOrThrow(Registries.ENCHANTMENT).getKey(enchantment);
			if (id != null) {
				if (id.equals(Identifier.withDefaultNamespace("fire_aspect"))) {
					return itemStack.is(ModTags.ELEMENTAL_WEAPONS);
				}
				if (id.equals(Identifier.withDefaultNamespace("flame"))) {
					return itemStack.is(ModTags.ELEMENTAL_RANGED_WEAPONS);
				}
				if (id.equals(QMIdentifier.of("enchancement", "frostbite").id)) { // Looks redundant; but needed for enchancement compat!
					return itemStack.is(ModTags.ELEMENTAL_WEAPONS);
				}
			}
		}
		return false;
	}

	public static void shieldBash(LivingEntity player) {
		Level level = player.level();
		if (player.getItemBlockingWith() != null && !level.isClientSide()) {
			boolean shieldBash = ModEnchants.getItemEnchant(player.getItemBlockingWith(), player.level(), ModEnchants.SHIELD_BASH) > 0;
			if (shieldBash) {
				player.addDeltaMovement(player.getViewVector(0f).multiply(1,0,1));
				player.level().playSound(null,player.getX(),player.getY(),player.getZ(),ModSounds.STRONG_SHIELD_BREAK, SoundSource.PLAYERS);
				//? if >=1.21.11 {
				player.needsSync = true;
				//? } else {
				/*hasImpulse = true;
				 *///? }
				if (player instanceof ServerPlayer player2) {
					player2.connection.send(new ClientboundSetEntityMotionPacket(player2));
					player2.getCooldowns().addCooldown(player.getItemBlockingWith(), 50);
				}

				for (Entity entity : level.getEntities(player, player.getBoundingBox().expandTowards(player.getDeltaMovement().multiply(3, 3, 3)))) {
					entity.hurt(player.damageSources().mobAttack(player),6f);
					if (entity instanceof LivingEntity livingEntity) {
						livingEntity.addEffect(new MobEffectInstance(ModEffects.STUNNED, Quartermaster.STUNNED_TICKS, 0));
					}
				}
				player.stopUsingItem();

			}
		}
	}



}
