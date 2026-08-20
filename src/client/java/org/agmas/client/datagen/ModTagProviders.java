package org.agmas.client.datagen;

//? if >=26.1 {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
*///? } else {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
//? }
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.agmas.init.ModDamageTypes;
import org.agmas.init.ModEffects;
import org.agmas.init.ModEnchants;
import org.agmas.init.ModItems;
import org.agmas.init.tag.ModItemLists;
import org.agmas.init.tag.ModTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModTagProviders {

    //? if >=26.1 {
    /*public static final class ItemTags extends FabricTagsProvider.ItemTagsProvider {
    *///?} else {
    public static final class ItemTags extends FabricTagProvider.ItemTagProvider {
    //? }


        //? if >=26.1 {
        /*public ItemTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        *///?} else {
        public ItemTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        //? }
            super(output, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            addList(ModItemLists.cutlasses, net.minecraft.tags.ItemTags.SWORDS);
            addList(ModItemLists.cutlasses, net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItemLists.cutlasses, net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            addList(ModItemLists.cutlasses, net.minecraft.tags.ItemTags.SWEEPING_ENCHANTABLE);
            addList(ModItemLists.cutlasses, net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItemLists.cutlasses, ModTags.CUTLASSES);

            addList(ModItemLists.morningstar, net.minecraft.tags.ItemTags.AXES);
            addList(ModItemLists.morningstar, net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItemLists.morningstar, net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            addList(ModItemLists.morningstar, net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItemLists.morningstar, net.minecraft.tags.ItemTags.MINING_ENCHANTABLE);
            addList(ModItemLists.morningstar, ModTags.MORNINGSTARS);

            addList(ModItemLists.estoc, net.minecraft.tags.ItemTags.SWORDS);
            addList(ModItemLists.estoc, net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItemLists.estoc, net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            addList(ModItemLists.estoc, net.minecraft.tags.ItemTags.SWEEPING_ENCHANTABLE);
            addList(ModItemLists.estoc, net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItemLists.estoc, ModTags.ESTOCS);

            addList(ModItemLists.greataxes, net.minecraft.tags.ItemTags.AXES);
            addList(ModItemLists.greataxes, net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItemLists.greataxes, net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            addList(ModItemLists.greataxes, net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItemLists.greataxes, net.minecraft.tags.ItemTags.MINING_ENCHANTABLE);
            addList(ModItemLists.greataxes, ModTags.GREATAXES);

            addList(ModItemLists.warhammers, net.minecraft.tags.ItemTags.AXES);
            addList(ModItemLists.warhammers, net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItemLists.warhammers, net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            addList(ModItemLists.warhammers, net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItemLists.warhammers, net.minecraft.tags.ItemTags.MINING_ENCHANTABLE);
            addList(ModItemLists.warhammers, ModTags.WARHAMMERS);

            addList(ModItemLists.wooden_heavy, ModTags.WOODEN_HEAVY_WEAPONS);
            addList(ModItemLists.wooden_light, ModTags.WOODEN_LIGHT_WEAPONS);

            addList(ModItemLists.stone_heavy, ModTags.STONE_HEAVY_WEAPONS);
            addList(ModItemLists.stone_light, ModTags.STONE_LIGHT_WEAPONS);

            addList(ModItemLists.copper_heavy, ModTags.COPPER_HEAVY_WEAPONS);
            addList(ModItemLists.copper_light, ModTags.COPPER_LIGHT_WEAPONS);

            addList(ModItemLists.iron_heavy, ModTags.IRON_HEAVY_WEAPONS);
            addList(ModItemLists.iron_light, ModTags.IRON_LIGHT_WEAPONS);

            addList(ModItemLists.golden_heavy, ModTags.GOLDEN_HEAVY_WEAPONS);
            addList(ModItemLists.golden_light, ModTags.GOLDEN_LIGHT_WEAPONS);

            addList(ModItemLists.diamond_heavy, ModTags.DIAMOND_HEAVY_WEAPONS);
            addList(ModItemLists.diamond_light, ModTags.DIAMOND_LIGHT_WEAPONS);

            addList(ModItemLists.netherite_heavy, ModTags.NETHERITE_HEAVY_WEAPONS);
            addList(ModItemLists.netherite_light, ModTags.NETHERITE_LIGHT_WEAPONS);

            addList(ModItemLists.ranged, ModTags.RANGED);

            add(ModItems.RAPIER, net.minecraft.tags.ItemTags.SWORDS);
            add(ModItems.RAPIER, net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            add(ModItems.RAPIER, net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            add(ModItems.RAPIER, net.minecraft.tags.ItemTags.SWEEPING_ENCHANTABLE);
            add(ModItems.RAPIER, net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);

            add(ModItems.FLINTLOCK, net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);

            add(Items.BAMBOO, ModTags.BAMBOO_REPAIR_MATERIALS);

            addList(ModItemLists.bamboo_weapons, ModTags.NON_DAMAGING);

        }

        public void add(Item item,TagKey<Item> tag) {
            //? if >=26.2 {
            /*tag(tag)
                    .add(ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(item)));
            *///? } else {
            valueLookupBuilder(tag)
                    .add(item);
             //? }
        }

        public void addList(List<Item> list,TagKey<Item> tag) {
            //? if >=26.2 {
            /*TagAppender<Item> itemTagAppender = tag(tag);
            *///? } else {
            TagAppender<Item, Item> itemTagAppender = valueLookupBuilder(tag);
            //? }
            for (Item item : list) {
                //? if >=26.2 {
                /*itemTagAppender.add(ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(item)));
                *///? } else {
                itemTagAppender.add(item);
                //? }
            }
        }
    }


    //? if >=26.1 {
    /*public static final class EnchantTags extends FabricTagsProvider<Enchantment> {
    *///?} else {
    public static final class EnchantTags extends FabricTagProvider<Enchantment> {
    //?}

        //? if >=26.1 {
        /*public EnchantTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        *///? } else {
        public EnchantTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        //? }
            super(output, Registries.ENCHANTMENT, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {

            builder(EnchantmentTags.NON_TREASURE)
                    .addOptional(ModEnchants.HOMERUN)
                    .addOptional(ModEnchants.POGO)
                    .addOptional(ModEnchants.PULL)
                    .addOptional(ModEnchants.BUSTER)
                    .addOptional(ModEnchants.CHARGED)
                    .addOptional(ModEnchants.EARTHQUAKE)
                    .addOptional(ModEnchants.FLING)
                    .addOptional(ModEnchants.PUNCTURE)
                    .addOptional(ModEnchants.SPLINTER)
                    .addOptional(ModEnchants.TAKEDOWN)
                    .addOptional(ModEnchants.SHIELD_BASH)
                    .addOptional(ModEnchants.BRITTLE)
                    .addOptional(ModEnchants.IMPACT_PROTECTION)
                    .addOptional(ModEnchants.FROST_PROTECTION)
                    .addOptional(ModEnchants.STUN_PROTECTION)
                    .addOptional(ModEnchants.MAGIC_PROTECTION)
                    .addOptional(ModEnchants.GLACIAL)
                    .addOptional(ModEnchants.HEATWAVE)
                    .addOptional(ModEnchants.TIDAL_CHILL)
                    .addOptional(ModEnchants.HELLFORK)
                    .addOptional(ModEnchants.CHILL)
                    .addOptional(ModEnchants.SINGLEHANDED)
                    .addOptional(ModEnchants.EXPLOSIVE_KINECTIVITY)
                    .addOptional(ModEnchants.BLADEDANCE)
                    .addOptional(ModEnchants.SIESMIC);

            builder(ModTags.EXCLUSIVE_SET_SHIELD_DISABLE)
                    .addOptional(ModEnchants.TAKEDOWN)
                    .addOptional(ModEnchants.SPLINTER);

            builder(ModTags.EXCLUSIVE_SET_SPEARS)
                    .addOptional(ModEnchants.POGO)
                    .addOptional(ModEnchants.PUNCTURE);

            builder(ModTags.EXCLUSIVE_SET_GREATAXE)
                    .addOptional(ModEnchants.CHARGED)
                    .addOptional(ModEnchants.BUSTER);

            builder(ModTags.EXCLUSIVE_SET_KNOCKBACK)
                    .addOptional(ModEnchants.HOMERUN)
                    .addOptional(Enchantments.KNOCKBACK);

            builder(ModTags.EXCLUSIVE_SET_WARHAMMER)
                    .addOptional(ModEnchants.PULL)
                    .addOptional(ModEnchants.HOMERUN);

            builder(ModTags.EXCLUSIVE_SET_FALL_PROTECTION)
                    .addOptional(ModEnchants.IMPACT_PROTECTION)
                    .addOptional(Enchantments.FEATHER_FALLING);

            builder(ModTags.EXCLUSIVE_SET_SHIELD)
                    .addOptional(ModEnchants.SHIELD_BASH)
                    .addOptional(ModEnchants.BRITTLE);

            builder(ModTags.YOU_TWO_SHOULD_JUST_KISS_ALREADY)
                    .addOptional(Enchantments.BLAST_PROTECTION)
                    .addOptional(Enchantments.FIRE_PROTECTION)
                    .addOptional(Enchantments.PROJECTILE_PROTECTION);

            builder(ModTags.EXCLUSIVE_SET_ELEMENTAL)
                    .addOptional(ModEnchants.GLACIAL)
                    .addOptional(ModEnchants.HEATWAVE)
                    .addOptional(ModEnchants.TIDAL_CHILL)
                    .addOptional(ModEnchants.HELLFORK)
                    .addOptional(ModEnchants.CHILL)
                    .addOptional(Enchantments.FIRE_ASPECT)
                    .addOptional(Enchantments.FLAME);
        }
    }

    //? if >=26.1 {
    /*public static final class DamageTypesTags extends FabricTagsProvider<DamageType> {
     *///?} else {
    public static final class DamageTypesTags extends FabricTagProvider<DamageType> {
        //?}

        //? if >=26.1 {
        /*public DamageTypesTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
         *///? } else {
        public DamageTypesTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            //? }
            super(output, Registries.DAMAGE_TYPE, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {

            this.builder(DamageTypeTags.BYPASSES_ARMOR)
                    .addOptional(ModDamageTypes.WOUND);
            this.builder(DamageTypeTags.BYPASSES_COOLDOWN)
                    .addOptional(ModDamageTypes.WOUND);
            this.builder(DamageTypeTags.NO_KNOCKBACK)
                    .addOptional(ModDamageTypes.WOUND);

            builder(ModTags.IS_MAGIC)
                    .addOptional(DamageTypes.MAGIC)
                    .addOptional(DamageTypes.INDIRECT_MAGIC)
                    .addOptional(DamageTypes.FIREBALL)
                    .addOptional(DamageTypes.DRAGON_BREATH)
                    .addOptional(ModDamageTypes.RUDE_DAMAGE)
                    .addOptional(DamageTypes.SONIC_BOOM);

            builder(ModTags.IS_IMPACT)
                    .addOptional(DamageTypes.MACE_SMASH)
                    .addOptional(DamageTypes.FALL)
                    .addOptional(DamageTypes.THORNS)
                    .addOptional(DamageTypes.FALLING_ANVIL)
                    .addOptional(DamageTypes.FALLING_BLOCK)
                    .addOptional(DamageTypes.FALLING_STALACTITE)
                    .addOptional(DamageTypes.SPEAR)
                    .addOptional(DamageTypes.STALAGMITE)
                    .addOptional(ModDamageTypes.SHOCKWAVE);
        }
    }

    //? if >=26.1 {
    /*public static final class MobEffectTags extends FabricTagsProvider<MobEffect> {
     *///?} else {
    public static final class MobEffectTags extends FabricTagProvider<MobEffect> {
        //?}

        //? if >=26.1 {
        /*public MobEffectTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
         *///? } else {
        public MobEffectTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            //? }
            super(output, Registries.MOB_EFFECT, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            builder(ModTags.IS_STUN)
                    .addOptional(ModEffects.STUNNED.unwrap().left().get())
                    .addOptional(ModEffects.DISARMED.unwrap().left().get())
                    .addOptional(ModEffects.WOUNDED.unwrap().left().get())
                    .addOptional(MobEffects.MINING_FATIGUE.unwrap().left().get())
                    .addOptional(MobEffects.POISON.unwrap().left().get())
                    .addOptional(MobEffects.WITHER.unwrap().left().get());
        }
    }
}
