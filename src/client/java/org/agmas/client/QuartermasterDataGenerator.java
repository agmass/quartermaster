package org.agmas.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.agmas.client.datagen.ModLanguageProvider;
import org.agmas.client.datagen.ModModelProvider;
import org.agmas.client.datagen.ModRecipeProvider;
import org.agmas.client.datagen.ModTagProviders;

public class QuartermasterDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModLanguageProvider::new);
		pack.addProvider(ModTagProviders.ItemTags::new);
		pack.addProvider(ModTagProviders.EnchantTags::new);
		pack.addProvider(ModTagProviders.DamageTypesTags::new);
		pack.addProvider(ModTagProviders.MobEffectTags::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
