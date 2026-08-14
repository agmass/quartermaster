package org.agmas;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.agmas.init.ModEnchants;
import org.agmas.init.*;
import org.agmas.init.tag.ModItemLists;
import org.agmas.init.tag.ModTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quartermaster implements ModInitializer {
	public static final String MOD_ID = "quartermaster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final QMIdentifier ALL_DISARMED_ID = QMIdentifier.of("all");

	public static int timeSinceShooting = 0;
	public static int STUNNED_TICKS = 20*5;
	public static int DISARMED_TICKS = 20*2;
	@Override
	public void onInitialize() {
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
						.setRolls(ConstantValue.exactly(1))
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
						.setRolls(ConstantValue.exactly(1))
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
			if (lootTableSource.isBuiltin() &&
					key.equals(BuiltInLootTables.PILLAGER_OUTPOST)) {
				builder.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
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
						.setRolls(ConstantValue.exactly(1))
						.add(
								LootItem.lootTableItem(Items.BOOK)
										.setWeight(1)
										.apply(new EnchantRandomlyFunction.Builder().withOneOf(
												HolderSet.direct(
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.PULL),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.SIESMIC),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.HEATWAVE),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.EARTHQUAKE),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.GLACIAL)
												)
										))
						));
			}
			if (lootTableSource.isBuiltin() &&
					key.equals(BuiltInLootTables.ABANDONED_MINESHAFT)) {
				builder.withPool(LootPool.lootPool()
						.setRolls(UniformGenerator.between(0,1))
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
			if (lootTableSource.isBuiltin() &&
					key.equals(BuiltInLootTables.TRIAL_CHAMBERS_REWARD) || key.equals(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS)) {
				builder.withPool(LootPool.lootPool()
						.setRolls(UniformGenerator.between(0,2))
						.add(
								LootItem.lootTableItem(Items.BOOK)
										.setWeight(1)
										.apply(new EnchantRandomlyFunction.Builder().withOneOf(
												HolderSet.direct(
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.BRITTLE),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.SHIELD_BASH),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.EARTHQUAKE),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.BUSTER),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.CHARGED),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.FLING),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.GLACIAL),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.SIESMIC),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.PUNCTURE),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.PULL),
														provider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ModEnchants.HEATWAVE)
												)
										))
						));
			}
		});

	}

}
