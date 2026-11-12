package org.agmas.client.datagen;

//? if >=26.1 {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
*///? } else {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
 //? }
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.agmas.init.ModItems;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

public class ModRecipeProvider extends FabricRecipeProvider {

    //? if <=1.21.1
    //public HolderGetter<Item> items;

    //? if >=26.1 {
    /*public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        *///? } else {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    //? }
        super(output, registriesFuture);
        //? if <1.21.1
        //items= registriesFuture.resultNow().lookupOrThrow(Registries.ITEM);

    }

    public String getName() {
        return "Quartermaster Recipes";
    }




    //? if <=1.21.1
    //RecipeOutput output;
    //? if >=26.3 {
    /*@Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, BootstrapContext<Recipe<?>> output1, BootstrapContext<Advancement> bootstrapContext1) {
        return new RecipeProvider(output1,bootstrapContext1) {
            *///? } else if >=1.21.4 {
    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        return new RecipeProvider(provider, output) {
    //? }
    @Override
    //? if >=1.21.4 {
    public void buildRecipes() {
     //? } else if >=1.20.4 {
    /*public void buildRecipes(RecipeOutput output) {
     *///? } else if >1.18 {
    /*public void buildRecipes(Consumer<FinishedRecipe> output) {
     *///? } else {
    /*public void generateRecipes(Consumer<FinishedRecipe> output) {
        *///? }

                //? if <=1.21.1
                //this.output = output;
                shaped(RecipeCategory.COMBAT, ModItems.RAPIER.relic())
                        .pattern("  N")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, ModItems.RAPIER.name())));

                shaped(RecipeCategory.COMBAT, ModItems.CUTLASS.relic())
                        .pattern("  N")
                        .pattern("NN ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, ModItems.CUTLASS.name())));


                shaped(RecipeCategory.COMBAT, ModItems.MORNINGSTAR.relic())
                        .pattern(" NN")
                        .pattern(" NN")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, ModItems.MORNINGSTAR.name())));


                shaped(RecipeCategory.COMBAT, ModItems.ESTOC.relic())
                        .pattern(" N ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, ModItems.ESTOC.name())));

                shaped(RecipeCategory.COMBAT, ModItems.GREATAXE.relic())
                        .pattern(" NN")
                        .pattern("NN ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, ModItems.GREATAXE.name())));

                shaped(RecipeCategory.COMBAT, ModItems.WARHAMMER.relic())
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_INGOT)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, ModItems.WARHAMMER.name())));

                shaped(RecipeCategory.COMBAT, ModItems.SICKLES.relic())
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern(" H ")
                        .define('N', Items.NETHERITE_INGOT)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, ModItems.SICKLES.name())));

                shaped(RecipeCategory.COMBAT, ModItems.PELLET, 9)
                        .pattern(" I ")
                        .pattern("IGI")
                        .pattern(" I ")
                        .define('G', Items.IRON_INGOT)
                        .define('I', Items.IRON_NUGGET)
                        .unlockedBy("has_ingredient", has(Items.IRON_NUGGET))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.AMMUNITION, 3)
                        .pattern("   ")
                        .pattern("AAA")
                        .pattern("GPP")
                        .define('G', Items.GUNPOWDER)
                        .define('P', Items.PAPER)
                        .define('A', ModItems.PELLET)
                        .unlockedBy("has_ingredient", has(ModItems.PELLET))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE, 2)
                        .pattern("DAD")
                        .pattern("DBD")
                        .pattern("DDD")
                        .define('D', Items.DIAMOND)
                        .define('B', Items.BLACKSTONE)
                        .define('A', ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE)
                        .unlockedBy("has_ingredient", has(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE))
                        .save(output);

                buildQMWeapon(ModItems.CUTLASS,
                        "  N",
                        "NN ",
                        "H  ",
                        false);

                buildQMWeapon(ModItems.ESTOC,
                        " N ",
                        " H ",
                        "H  ",
                        false);

                buildQMWeapon(ModItems.SICKLES,
                        "   ",
                        " N ",
                        " H ",
                        false);

                buildQMWeapon(ModItems.RAPIER,
                        "  N",
                        " N ",
                        "H  ",
                        false);


                buildQMWeapon(ModItems.WARHAMMER,
                        "   ",
                        " N ",
                        "H  ",
                        true);


                buildQMWeapon(ModItems.GREATAXE,
                        " NN",
                        "NH ",
                        "H  ",
                        false);

                buildQMWeapon(ModItems.MORNINGSTAR,
                        " NN",
                        " HN",
                        "H  ",
                        false);

                shaped(RecipeCategory.COMBAT, ModItems.BAMBOO_MACE)
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.BAMBOO_BLOCK)
                        .define('H', Items.BREEZE_ROD)
                        .unlockedBy("has_ingredient", has(Items.BREEZE_ROD))
                        .save(output);

                //? if >=1.21.11 {
                shaped(RecipeCategory.COMBAT, ModItems.BAMBOO_SPEAR)
                        .pattern("  N")
                        .pattern(" H ")
                        .pattern("H  ")
                        .define('N', Items.BAMBOO)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(Items.BAMBOO))
                        .save(output);
                //? }

                shaped(RecipeCategory.COMBAT, ModItems.BAMBOO_SWORD)
                        .pattern("N")
                        .pattern("N")
                        .pattern("H")
                        .define('N', Items.BAMBOO)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(Items.BAMBOO))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.BAMBOO_AXE)
                        .pattern("NN")
                        .pattern("NH")
                        .pattern(" H")
                        .define('N', Items.BAMBOO)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(Items.BAMBOO))
                        .save(output);
            }


            public void buildQMWeapon(ModItems.QMWeapon weapon, String pattern1, String pattern2, String pattern3, boolean expensive) {

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(weapon.iron()),
                                RecipeCategory.TOOLS,
                                //? if >=26.1 {
                                /*CookingBookCategory.MISC,
                                 *///?}
                                weapon.weaponClass().equals(ModItems.WeaponClass.HEAVY) ? Items.COPPER_INGOT : Items.COPPER_NUGGET,
                                0.1F,
                                200)
                        .unlockedBy("has_ingredient", has(weapon.iron()))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("golden_"))), weapon.name().getPath() + "_smelt_copper");

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(weapon.iron()),
                                RecipeCategory.TOOLS,
                                //? if >=26.1 {
                                /*CookingBookCategory.MISC,
                                 *///?}
                                weapon.weaponClass().equals(ModItems.WeaponClass.HEAVY) ? Items.IRON_INGOT : Items.IRON_NUGGET,
                                0.1F,
                                200)
                        .unlockedBy("has_ingredient", has(weapon.iron()))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("golden_"))), weapon.name().getPath() + "_smelt_iron");

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(weapon.golden()),
                                RecipeCategory.TOOLS,
                                //? if >=26.1 {
                                /*CookingBookCategory.MISC,
                                 *///?}
                                weapon.weaponClass().equals(ModItems.WeaponClass.HEAVY) ? Items.GOLD_INGOT : Items.GOLD_NUGGET,
                                0.1F,
                                200)
                        .unlockedBy("has_ingredient", has(weapon.golden()))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("golden_"))), weapon.name().getPath() + "_smelt");


                shaped(RecipeCategory.COMBAT, weapon.bamboo())
                        .pattern(pattern1)
                        .pattern(pattern2)
                        .pattern(pattern3)
                        .define('N', Items.BAMBOO)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(Items.BAMBOO))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("bamboo_"))));

                shaped(RecipeCategory.COMBAT, weapon.wooden())
                        .pattern(pattern1)
                        .pattern(pattern2)
                        .pattern(pattern3)
                        .define('N', expensive ? tag(ItemTags.LOGS) : tag(ItemTags.PLANKS))
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(Items.STICK))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("wooden_"))));

                shaped(RecipeCategory.COMBAT, weapon.stone())
                        .pattern(pattern1)
                        .pattern(pattern2)
                        .pattern(pattern3)
                        .define('N', expensive ? tag(ItemTags.STONE_BRICKS) : tag(ItemTags.STONE_TOOL_MATERIALS))
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(ItemTags.STONE_TOOL_MATERIALS))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("stone_"))));

                shaped(RecipeCategory.COMBAT, weapon.iron())
                        .pattern(pattern1)
                        .pattern(pattern2)
                        .pattern(pattern3)
                        .define('N', expensive ? Items.IRON_BLOCK : Items.IRON_INGOT)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(Items.IRON_INGOT))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("iron_"))));

                //? if >=1.21.10 {
                shaped(RecipeCategory.COMBAT, weapon.copper())
                        .pattern(pattern1)
                        .pattern(pattern2)
                        .pattern(pattern3)
                        .define('N', expensive ?
                                //? if >=26.2
                                //Items.COPPER_BLOCK.weathering().unaffected()
                                //? if <26.2
                                Items.COPPER_BLOCK
                                : Items.COPPER_INGOT)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(Items.COPPER_INGOT))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("copper_"))));
                //? }
                shaped(RecipeCategory.COMBAT, weapon.golden())
                        .pattern(pattern1)
                        .pattern(pattern2)
                        .pattern(pattern3)
                        .define('N', expensive ? Items.GOLD_BLOCK : Items.GOLD_INGOT)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(Items.GOLD_INGOT))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("golden_"))));

                SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(weapon.diamond()), this.tag(ItemTags.NETHERITE_TOOL_MATERIALS), RecipeCategory.COMBAT, weapon.netherite())
                        .unlocks("has_netherite_ingot", this.has(ItemTags.NETHERITE_TOOL_MATERIALS))
                        .save(withConditions(output, ResourceConditions.registryContains(Registries.ITEM, weapon.name().withPrefix("netherite_"))), RecipeProvider.getItemName(weapon.netherite()) + "_smithing");

            }

    //? if >=1.21.4 {
        };
    }
    //? }


    //? if <=1.21.1 {
    /*public Ingredient tag(final TagKey<Item> id) {
        return Ingredient.of(items.getOrThrow(id).key());
    }
    *///? }
}
