package org.agmas.client.datagen;

//? if >=26.1 {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
*///? } else {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
 //? }
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.agmas.client.QuartermasterClient;
import org.agmas.init.*;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {



    //? if >=26.1 {
    /*public ModLanguageProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
    *///? } else {
    public ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
     //? }
        super(dataOutput, registryLookup);
    }   

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        // enchants, oh god

        translationBuilder.add("enchantment.quartermaster.buster", "Buster");
        translationBuilder.add("enchantment.quartermaster.buster.desc", "Attacks no longer go through shields, but ups the damage of the Ranged ability.");

        translationBuilder.add("enchantment.quartermaster.charged", "Charged");
        translationBuilder.add("enchantment.quartermaster.charged.desc", "Lowers damage of the Ranged ability, but adds extra charges depending on level.");

        translationBuilder.add("enchantment.quartermaster.fling", "Fling");
        translationBuilder.add("enchantment.quartermaster.fling.desc", "Halves disarmed time, but sends held items into the victim's inventory (or makes mobs drop their items)");

        translationBuilder.add("enchantment.quartermaster.homerun", "Homerun");
        translationBuilder.add("enchantment.quartermaster.homerun.desc", "Replaces knock-back with a knock-up when landing Critical Hits.");

        translationBuilder.add("enchantment.quartermaster.pogo", "Pogo");
        translationBuilder.add("enchantment.quartermaster.pogo.desc", "Launches the user backwards when used on a block.");

        translationBuilder.add("enchantment.quartermaster.pull", "Pull");
        translationBuilder.add("enchantment.quartermaster.pull.desc", "Flings victims toward the user.");

        translationBuilder.add("enchantment.quartermaster.puncture", "Puncture");
        translationBuilder.add("enchantment.quartermaster.puncture.desc", "Deals reverse knockback and applies 1 stack of wound per attack.");

        translationBuilder.add("enchantment.quartermaster.siesmic", "Siesmic");
        translationBuilder.add("enchantment.quartermaster.siesmic.desc", "Deals the full weapon damage on slam, but greatly increases wind-up time.");

        translationBuilder.add("enchantment.quartermaster.splinter", "Splinter");
        translationBuilder.add("enchantment.quartermaster.splinter.desc", "Deals 2 stacks of wound when disabling a shield, but greatly decreases shield disable time");

        translationBuilder.add("enchantment.quartermaster.takedown", "Takedown");
        translationBuilder.add("enchantment.quartermaster.takedown.desc", "Applies Disarmed rather than disabling shields.");

        translationBuilder.add("enchantment.quartermaster.brittle", "Brittle");
        translationBuilder.add("enchantment.quartermaster.brittle.desc", "Everything disables your shield, but knocks you back significantlly");

        translationBuilder.add("enchantment.quartermaster.shield_bash", "Shield Bash");
        translationBuilder.add("enchantment.quartermaster.shield_bash.desc", "Shield gets disabled for longer; but can bash entities infront of the user, dealing damage and giving Stun.");

        translationBuilder.add("enchantment.quartermaster.impact_protection", "Impact Protection");
        translationBuilder.add("enchantment.quartermaster.impact_protection.desc", "Reduces damage done by impact (Maces, Warhammers, Fall, Dripstone)");

        translationBuilder.add("enchantment.quartermaster.magic_protection", "Magic Protection");
        translationBuilder.add("enchantment.quartermaster.magic_protection.desc", "Reduces damage done by magic sources");

        translationBuilder.add("enchantment.quartermaster.frost_protection", "Frost Protection");
        translationBuilder.add("enchantment.quartermaster.frost_protection.desc", "Reduces frost time");

        translationBuilder.add("enchantment.quartermaster.stun_protection", "Stun Protection");
        translationBuilder.add("enchantment.quartermaster.stun_protection.desc", "Reduces stun time");

        translationBuilder.add("enchantment.quartermaster.bladedance", "Bladedance");
        translationBuilder.add("enchantment.quartermaster.bladedance.desc", "Does more damage to victims mid-air.");

        translationBuilder.add("enchantment.quartermaster.singlehanded", "Singlehanded");
        translationBuilder.add("enchantment.quartermaster.singlehanded.desc", "Deals more damage when not holding any off-hand items.");

        translationBuilder.add("enchantment.quartermaster.glacial", "Glacial");
        translationBuilder.add("enchantment.quartermaster.glacial.desc", "Deals frost damage to all entities hit.");

        translationBuilder.add("enchantment.quartermaster.heatwave", "Heatwave");
        translationBuilder.add("enchantment.quartermaster.heatwave.desc", "Sets all entities hit on fire.");

        translationBuilder.add("enchantment.quartermaster.tidal_chill", "Tidal Chill");
        translationBuilder.add("enchantment.quartermaster.tidal_chill.desc", "Freezes victims.");

        translationBuilder.add("enchantment.quartermaster.hellfork", "Hellfork");
        translationBuilder.add("enchantment.quartermaster.hellfork.desc", "Sets victims on fire.");

        translationBuilder.add("enchantment.quartermaster.chill", "Chill");
        translationBuilder.add("enchantment.quartermaster.chill.desc", "Freezes victims.");

        translationBuilder.add("enchantment.quartermaster.explosive_kinectivity", "Explosive Kinectivity");
        translationBuilder.add("enchantment.quartermaster.explosive_kinectivity.desc", "Kaboom.");

        translationBuilder.add("death.attack.bleed", "%1$s bled out");
        translationBuilder.add("death.attack.bleed.player", "%1$s bled out because of %2$s");
        translationBuilder.add("death.attack.shockwave", "%1$s's skull was shattered");
        translationBuilder.add("death.attack.shockwave.player", "%1$s's bones were broken by %2$s");
        translationBuilder.add("quartermaster.combat_effect_tooltip", "Trimmed with Combat Effects");

        translationBuilder.add("item.minecraft.splash_potion.effect.frost_protection", "Splash Potion of Frost Resistance");
        translationBuilder.add("item.minecraft.potion.effect.frost_protection", "Potion of Frost Resistance");
        translationBuilder.add("item.minecraft.lingering_potion.effect.frost_protection", "Lingering Potion of Frost Resistance");

        translationBuilder.add(ModAttributes.FROST_TIME, "Frost Time");
        translationBuilder.add(ModAttributes.STUN_TIME, "Stun Time");

        translationBuilder.add("item.quartermaster.rapier.coral", "Coral");
        translationBuilder.add(ModSounds.CUTLASS_CRIT, "Cutlass Critical Hit");
        translationBuilder.add(ModSounds.CUTLASS_SWEEP, "Cutlass Sweep");
        translationBuilder.add(ModSounds.STRONG_SHIELD_BREAK, "Strong Shield Break");
        translationBuilder.add(ModSounds.PARRY, "Parry");
        translationBuilder.add(ModSounds.GREATAXE_HIT, "Greataxe Ability Hit");
        translationBuilder.add(ModSounds.GREATAXE_USE, "Greataxe Ability Used");
        translationBuilder.add(ModSounds.FLINTLOCK_LOAD_0, "Flintlock Loading");
        translationBuilder.add(ModSounds.FLINTLOCK_LOAD_1, "Flintlock Loaded");

        translationBuilder.add(ModEffects.WOUNDED.value(), "Wounded");
        translationBuilder.add(ModEffects.FROST_PROTECTION.value(), "Frost Protection");
        translationBuilder.add(ModEffects.DISARMED.value(), "Disarmed");
        translationBuilder.add(ModEffects.STUNNED.value(), "Stunned");

        translationBuilder.add("key.quartermaster.inspect", "Inspect");
        translationBuilder.add("key.category.quartermaster.quartermaster", "Quartermaster");

        translationBuilder.add(ModItems.CUTLASS, "Cutlass");
        translationBuilder.add(ModItems.MORNINGSTAR, "Morningstar");
        translationBuilder.add(ModItems.ESTOC, "Estoc");
        translationBuilder.add(ModItems.RAPIER, "Rapier");
        translationBuilder.add(ModItems.GREATAXE, "Greataxe");
        translationBuilder.add(ModItems.WARHAMMER, "Warhammer");
        translationBuilder.add(ModItems.FLINTLOCK, "Flintlock");

        generateTiered(translationBuilder, "cutlass", "Cutlass");
        generateTiered(translationBuilder, "morningstar", "Morningstar");
        generateTiered(translationBuilder, "estoc", "Estoc");
        generateTiered(translationBuilder, "greataxe", "Greataxe");
        generateTiered(translationBuilder, "warhammer", "Warhammer");

        translationBuilder.add(ModItems.RUINED_HANDLE, "Ruined Handle");
        translationBuilder.add(ModItems.RELIC_HANDLE, "Relic Handle");
        translationBuilder.add(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE, "Combat Effect Smithing Template");
        translationBuilder.add(ModItems.PELLET, "Pellet");
        translationBuilder.add(ModItems.AMMUNITION, "Ammunition");

    }

    public void generateTiered(TranslationBuilder translationBuilder, String toolName, String fancyName) {
        translationBuilder.add("item.quartermaster.wooden_" + toolName, "Wooden " + fancyName);
        translationBuilder.add("item.quartermaster.stone_" + toolName, "Stone " + fancyName);
        translationBuilder.add("item.quartermaster.copper_" + toolName, "Copper " + fancyName);
        translationBuilder.add("item.quartermaster.iron_" + toolName, "Iron " + fancyName);
        translationBuilder.add("item.quartermaster.golden_" + toolName, "Golden " + fancyName);
        translationBuilder.add("item.quartermaster.diamond_" + toolName, "Diamond " + fancyName);
        translationBuilder.add("item.quartermaster.netherite_" + toolName, "Netherite " + fancyName);
    }

}
