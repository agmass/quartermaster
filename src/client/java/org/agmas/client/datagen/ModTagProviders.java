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
//? if >1.21.1
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
            addList(ModItems.CUTLASS.asList(), net.minecraft.tags.ItemTags.SWORDS);
            addList(ModItems.CUTLASS.asList(), net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItems.CUTLASS.asList(), net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            //? if >1.21.1
            addList(ModItems.CUTLASS.asList(), net.minecraft.tags.ItemTags.SWEEPING_ENCHANTABLE);
            addList(ModItems.CUTLASS.asList(), net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItems.CUTLASS.asList(), ModTags.CUTLASSES);

            addList(ModItems.RAPIER.asList(), net.minecraft.tags.ItemTags.SWORDS);
            addList(ModItems.RAPIER.asList(), net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItems.RAPIER.asList(), net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            //? if >1.21.1
            addList(ModItems.RAPIER.asList(), net.minecraft.tags.ItemTags.SWEEPING_ENCHANTABLE);
            addList(ModItems.RAPIER.asList(), net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItems.RAPIER.asList(), ModTags.RAPIERS);

            addList(ModItems.SICKLES.asList(), net.minecraft.tags.ItemTags.SWORDS);
            addList(ModItems.SICKLES.asList(), net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItems.SICKLES.asList(), net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            //? if >1.21.1
            addList(ModItems.SICKLES.asList(), net.minecraft.tags.ItemTags.SWEEPING_ENCHANTABLE);
            addList(ModItems.SICKLES.asList(), net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItems.SICKLES.asList(), ModTags.RAPIERS);

            addList(ModItems.MORNINGSTAR.asList(), net.minecraft.tags.ItemTags.AXES);
            addList(ModItems.MORNINGSTAR.asList(), net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItems.MORNINGSTAR.asList(), net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            addList(ModItems.MORNINGSTAR.asList(), net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItems.MORNINGSTAR.asList(), net.minecraft.tags.ItemTags.MINING_ENCHANTABLE);
            addList(ModItems.MORNINGSTAR.asList(), ModTags.MORNINGSTARS);

            addList(ModItems.ESTOC.asList(), net.minecraft.tags.ItemTags.SWORDS);
            addList(ModItems.ESTOC.asList(), net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItems.ESTOC.asList(), net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            //? if >1.21.1
            addList(ModItems.ESTOC.asList(), net.minecraft.tags.ItemTags.SWEEPING_ENCHANTABLE);
            addList(ModItems.ESTOC.asList(), net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItems.ESTOC.asList(), ModTags.ESTOCS);

            addList(ModItems.GREATAXE.asList(), net.minecraft.tags.ItemTags.AXES);
            addList(ModItems.GREATAXE.asList(), net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItems.GREATAXE.asList(), net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            addList(ModItems.GREATAXE.asList(), net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItems.GREATAXE.asList(), net.minecraft.tags.ItemTags.MINING_ENCHANTABLE);
            addList(ModItems.GREATAXE.asList(), ModTags.GREATAXES);

            addList(ModItems.WARHAMMER.asList(), net.minecraft.tags.ItemTags.AXES);
            addList(ModItems.WARHAMMER.asList(), net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE);
            addList(ModItems.WARHAMMER.asList(), net.minecraft.tags.ItemTags.SHARP_WEAPON_ENCHANTABLE);
            addList(ModItems.WARHAMMER.asList(), net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);
            addList(ModItems.WARHAMMER.asList(), net.minecraft.tags.ItemTags.MINING_ENCHANTABLE);
            addList(ModItems.WARHAMMER.asList(), ModTags.WARHAMMERS);

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

            add(ModItems.FLINTLOCK, net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE);

            add(Items.BAMBOO, ModTags.BAMBOO_REPAIR_MATERIALS);

            addList(ModItemLists.bamboo_weapons, ModTags.NON_DAMAGING);

            for (ModItems.QMWeapon weapon : ModItemLists.weapons) {
                if (weapon.relic() != null)
                    add(weapon.relic(), ModTags.ELEMENTAL_WEAPONS);
                if (weapon.bamboo() != null)
                    add(weapon.bamboo(), ModTags.ELEMENTAL_WEAPONS);
                if (weapon.wooden() != null) {
                    add(weapon.wooden(), ModTags.ELEMENTAL_WEAPONS);
                    add(weapon.stone(), ModTags.ELEMENTAL_WEAPONS);
                    //? >=1.21.10
                    add(weapon.copper(), ModTags.ELEMENTAL_WEAPONS);
                    add(weapon.iron(), ModTags.ELEMENTAL_WEAPONS);
                    add(weapon.diamond(), ModTags.ELEMENTAL_WEAPONS);
                    add(weapon.netherite(), ModTags.ELEMENTAL_WEAPONS);
                }

                if (weapon.weaponClass().equals(ModItems.WeaponClass.HEAVY) && !weapon.equals(ModItems.AXE)) {
                    if (weapon.relic() != null)
                        add(weapon.relic(), ModTags.STUN_ENCHANTABLE);
                    if (weapon.bamboo() != null)
                        add(weapon.bamboo(), ModTags.STUN_ENCHANTABLE);
                    if (weapon.wooden() != null) {
                        add(weapon.wooden(), ModTags.STUN_ENCHANTABLE);
                        add(weapon.stone(), ModTags.STUN_ENCHANTABLE);
                        //? >=1.21.10
                        add(weapon.copper(), ModTags.STUN_ENCHANTABLE);
                        add(weapon.iron(), ModTags.STUN_ENCHANTABLE);
                        add(weapon.diamond(), ModTags.STUN_ENCHANTABLE);
                        add(weapon.netherite(), ModTags.STUN_ENCHANTABLE);
                    }
                }
            }

            // Technically redundant, but increases support with modded weapons
            add(net.minecraft.tags.ItemTags.WEAPON_ENCHANTABLE, ModTags.ELEMENTAL_WEAPONS);

            add(Items.TRIDENT, ModTags.ELEMENTAL_WEAPONS);
            add(Items.TRIDENT, ModTags.ELEMENTAL_RANGED_WEAPONS);
            add(Items.CROSSBOW, ModTags.ELEMENTAL_RANGED_WEAPONS);
            add(net.minecraft.tags.ItemTags.BOW_ENCHANTABLE, ModTags.ELEMENTAL_RANGED_WEAPONS);

        }

        public void add(TagKey<Item> item,TagKey<Item> tag) {
            //? if >=26.2 {
            /*tag(tag)
                    .addOptionalTag(item);
            *///? } else if >1.21.1 {
            builder(tag)
                    .addOptionalTag(item);
            //? } else{
            /*tag(tag)
                    .addOptionalTag(item);
            *///? }
        }
        public void add(Item item,TagKey<Item> tag) {
            //? if >=26.2 {
            /*tag(tag)
                    .addOptional(ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(item)));
            *///? } else if >1.21.1 {
            builder(tag)
                    .addOptional(ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(item)));
            //? } else{
            /*tag(tag)
                    .addOptional(BuiltInRegistries.ITEM.getResourceKey(item).get());
            *///? }
        }

        public void addList(List<Item> list,TagKey<Item> tag) {
            //? if >=26.2 {
            /*TagAppender<Item> itemTagAppender = tag(tag);
            *///? } else  if >1.21.1 {
            TagAppender<ResourceKey<Item>, Item> itemTagAppender = builder(tag);
            //? }
            for (Item item : list) {
                //? if >1.21.1 {
                itemTagAppender.addOptional(ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(item)));
                //? } else {
                    /*tag(tag)
                            .addOptional(BuiltInRegistries.ITEM.getResourceKey(item).get());
                    *///? }
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

            //? if >1.21.1
            builder(EnchantmentTags.NON_TREASURE)
            //? if <=1.21.1
            //getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
                    .addOptional(ModEnchants.HOMERUN)
                    .addOptional(ModEnchants.POGO)
                    .addOptional(ModEnchants.PULL)
                    .addOptional(ModEnchants.BUSTER)
                    .addOptional(ModEnchants.CHARGED)
                    .addOptional(ModEnchants.FROST_ASPECT)
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
                    .addOptional(ModEnchants.CHILL)
                    .addOptional(ModEnchants.SINGLEHANDED)
                    .addOptional(ModEnchants.EXPLOSIVE_KINECTIVITY)
                    .addOptional(ModEnchants.BLADEDANCE)
                    .addOptional(ModEnchants.NORTH_WIND)
                    .addOptional(ModEnchants.EXCAVATE)
                    .addOptional(ModEnchants.SIESMIC);

            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_SHIELD_DISABLE)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_SHIELD_DISABLE)
                    .addOptional(ModEnchants.TAKEDOWN)
                    .addOptional(ModEnchants.SPLINTER);


            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_SPEARS)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_SPEARS)
                    .addOptional(ModEnchants.POGO)
                    .addOptional(ModEnchants.PUNCTURE);

            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_GREATAXE)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_GREATAXE)
                    .addOptional(ModEnchants.CHARGED)
                    .addOptional(ModEnchants.BUSTER);

            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_KNOCKBACK)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_KNOCKBACK)
                    .addOptional(ModEnchants.HOMERUN)
                    .addOptional(Enchantments.KNOCKBACK);

            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_WARHAMMER)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_WARHAMMER)
                    .addOptional(ModEnchants.PULL)
                    .addOptional(ModEnchants.HOMERUN);

            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_FALL_PROTECTION)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_FALL_PROTECTION)
                    .addOptional(ModEnchants.IMPACT_PROTECTION)
                    .addOptional(Enchantments.FEATHER_FALLING);

            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_SHIELD)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_SHIELD)
                    .addOptional(ModEnchants.SHIELD_BASH)
                    .addOptional(ModEnchants.BRITTLE);

            //? if >1.21.1
            builder(ModTags.YOU_TWO_SHOULD_JUST_KISS_ALREADY)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.YOU_TWO_SHOULD_JUST_KISS_ALREADY)
                    .addOptional(Enchantments.BLAST_PROTECTION)
                    .addOptional(Enchantments.FIRE_PROTECTION)
                    .addOptional(Enchantments.PROJECTILE_PROTECTION);

            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_ELEMENTAL)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_ELEMENTAL)
                    .addOptional(ModEnchants.FROST_ASPECT)
                    .addOptional(Enchantments.FIRE_ASPECT);

            //? if >1.21.1
            builder(ModTags.EXCLUSIVE_SET_ELEMENTAL_RANGED)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.EXCLUSIVE_SET_ELEMENTAL_RANGED)
                    .addOptional(ModEnchants.CHILL)
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

            //? if >1.21.1
            builder(DamageTypeTags.BYPASSES_ARMOR)
            //? if <=1.21.1
            //getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR)
                    .addOptional(ModDamageTypes.WOUND);


            //? if >1.21.1
            builder(DamageTypeTags.BYPASSES_COOLDOWN)
            //? if <=1.21.1
            //getOrCreateTagBuilder(DamageTypeTags.BYPASSES_COOLDOWN)
                    .addOptional(ModDamageTypes.WOUND);


            //? if >1.21.1
            builder(DamageTypeTags.NO_KNOCKBACK)
            //? if <=1.21.1
            //getOrCreateTagBuilder(DamageTypeTags.NO_KNOCKBACK)
                    .addOptional(ModDamageTypes.WOUND);


            //? if >1.21.1
            builder(ModTags.IS_MAGIC)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.IS_MAGIC)
                    .addOptional(DamageTypes.MAGIC)
                    .addOptional(DamageTypes.INDIRECT_MAGIC)
                    .addOptional(DamageTypes.FIREBALL)
                    .addOptional(DamageTypes.DRAGON_BREATH)
                    .addOptional(ModDamageTypes.RUDE_DAMAGE)
                    .addOptional(DamageTypes.SONIC_BOOM);


            //? if >1.21.1
            builder(ModTags.IS_IMPACT)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.IS_IMPACT)
                    //? if >1.21.4
                    .addOptional(DamageTypes.MACE_SMASH)
                    .addOptional(DamageTypes.FALL)
                    .addOptional(DamageTypes.THORNS)
                    .addOptional(DamageTypes.FALLING_ANVIL)
                    .addOptional(DamageTypes.FALLING_BLOCK)
                    .addOptional(DamageTypes.FALLING_STALACTITE)
                    //? if >=1.21.11
                    .addOptional(DamageTypes.SPEAR)
                    .addOptional(DamageTypes.STALAGMITE)
                    .addOptional(ModDamageTypes.SHOCKWAVE)
                    .addOptional(ModDamageTypes.WALL_IMPACT);
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

            //? if >1.21.1
            builder(ModTags.IS_STUN)
            //? if <=1.21.1
            //getOrCreateTagBuilder(ModTags.IS_STUN)
                    .addOptional(ModEffects.STUNNED.unwrap().left().get())
                    .addOptional(ModEffects.DISARMED.unwrap().left().get())
                    .addOptional(ModEffects.WOUNDED.unwrap().left().get())
                    //? if >1.21.1
                    .addOptional(MobEffects.MINING_FATIGUE.unwrap().left().get())
                    .addOptional(MobEffects.POISON.unwrap().left().get())
                    .addOptional(MobEffects.WITHER.unwrap().left().get());
        }
    }
}
