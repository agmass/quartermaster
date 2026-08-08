package org.agmas;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import org.agmas.init.ModEnchants;
import org.agmas.init.*;
import org.agmas.init.tag.ModTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quartermaster implements ModInitializer {
	public static final String MOD_ID = "quartermaster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final QMIdentifier ALL_DISARMED_ID = QMIdentifier.of("all");

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

		LootTableEvents.MODIFY.register((key, builder, lootTableSource, provider)->{
			if (lootTableSource.isBuiltin() &&
					(key.equals(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_RARE)
							|| key.equals(BuiltInLootTables.BURIED_TREASURE))) {
				builder.modifyPools((m)->{
					m.add(LootItem.lootTableItem(ModItems.RUINED_HANDLE));
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
		});

	}

}
