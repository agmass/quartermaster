package org.agmas.init;

//? if >=26.1 {
/*import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
*///? } else {
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
//? }
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import org.agmas.QMIdentifier;

public class ModPotions {
    public static final Holder<Potion> FROST_PROTECTION =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    QMIdentifier.of("frost_protection").id,
                    new Potion("frost_protection",
                            new MobEffectInstance(
                                    ModEffects.FROST_PROTECTION,
                                    3600,
                                    0
                            )
                    )
            );

    public static final Holder<Potion> LONG_FROST_PROTECTION =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    QMIdentifier.of("long_frost_protection").id,
                    new Potion("frost_protection",
                            new MobEffectInstance(
                                    ModEffects.FROST_PROTECTION,
                                    9600,
                                    0
                            )
                    )
            );
    public static void init() {
        //? if >=26.1 {
        /*FabricPotionBrewingBuilder.BUILD.register(builder -> {
        *///? } else {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
        //? }
            builder.addMix(
                    Potions.AWKWARD,
                    Items.SNOWBALL,
                    FROST_PROTECTION
            );
        });

        //? if >=26.1 {
        /*FabricPotionBrewingBuilder.BUILD.register(builder -> {
         *///? } else {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            //? }
            builder.addMix(
                    FROST_PROTECTION,
                    Items.REDSTONE,
                    LONG_FROST_PROTECTION
            );
        });
    }
}
