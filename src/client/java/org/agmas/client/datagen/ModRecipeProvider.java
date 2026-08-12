package org.agmas.client.datagen;

//? if >=26.1 {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
*///? } else {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
 //? }
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.agmas.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    //? if >=26.1 {
    /*public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        *///? } else {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    //? }
        super(output, registriesFuture);
    }

    public String getName() {
        return "Quartermaster Recipes";
    }


    //? if >=26.3 {
    /*@Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> bootstrapContext1) {
        return new RecipeProvider(recipeOutput,bootstrapContext1) {
    *///? } else {
    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
    //? }
            @Override
            public void buildRecipes() {

                shaped(RecipeCategory.COMBAT, ModItems.RAPIER)
                        .pattern("  N")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(recipeOutput);

                shaped(RecipeCategory.COMBAT, ModItems.CUTLASS)
                        .pattern("  N")
                        .pattern("NN ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(recipeOutput);


                shaped(RecipeCategory.COMBAT, ModItems.MORNINGSTAR)
                        .pattern(" NN")
                        .pattern(" NN")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(recipeOutput);


                shaped(RecipeCategory.COMBAT, ModItems.ESTOC)
                        .pattern(" N ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(recipeOutput);

                shaped(RecipeCategory.COMBAT, ModItems.GREATAXE)
                        .pattern(" NN")
                        .pattern("NN ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(recipeOutput);

                shaped(RecipeCategory.COMBAT, ModItems.WARHAMMER)
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_INGOT)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(recipeOutput);

                shaped(RecipeCategory.COMBAT, ModItems.PELLET, 9)
                        .pattern(" I ")
                        .pattern("IGI")
                        .pattern(" I ")
                        .define('G', Items.IRON_INGOT)
                        .define('I', Items.IRON_NUGGET)
                        .unlockedBy("has_ingredient", has(Items.IRON_NUGGET))
                        .save(recipeOutput);

                shaped(RecipeCategory.COMBAT, ModItems.AMMUNITION, 3)
                        .pattern("   ")
                        .pattern("AAA")
                        .pattern("GPP")
                        .define('G', Items.GUNPOWDER)
                        .define('P', Items.PAPER)
                        .define('A', ModItems.PELLET)
                        .unlockedBy("has_ingredient", has(ModItems.PELLET))
                        .save(recipeOutput);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ModItems.GOLDEN_CUTLASS),
                                RecipeCategory.TOOLS,
                                //? if >=26.1 {
                                /*CookingBookCategory.MISC,
                                *///?}
                                Items.GOLD_NUGGET,
                                0.1F,
                                200)
                        .unlockedBy("has_ingredient", has(ModItems.GOLDEN_CUTLASS))
                        .save(this.output);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ModItems.GOLDEN_ESTOC),
                                RecipeCategory.TOOLS,
                                //? if >=26.1 {
                                /*CookingBookCategory.MISC,
                                 *///?}
                                Items.GOLD_NUGGET,
                                0.1F,
                                200)
                        .unlockedBy("has_ingredient", has(ModItems.GOLDEN_ESTOC))
                        .save(this.output);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ModItems.GOLDEN_GREATAXE),
                                RecipeCategory.TOOLS,
                                //? if >=26.1 {
                                /*CookingBookCategory.MISC,
                                 *///?}
                                Items.GOLD_INGOT,
                                0.1F,
                                200)
                        .unlockedBy("has_ingredient", has(ModItems.GOLDEN_GREATAXE))
                        .save(this.output);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ModItems.GOLDEN_MORNINGSTAR),
                                RecipeCategory.TOOLS,
                                //? if >=26.1 {
                                /*CookingBookCategory.MISC,
                                 *///?}
                                Items.GOLD_INGOT,
                                0.1F,
                                200)
                        .unlockedBy("has_ingredient", has(ModItems.GOLDEN_MORNINGSTAR))
                        .save(this.output);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ModItems.GOLDEN_WARHAMMER),
                                RecipeCategory.TOOLS,
                                //? if >=26.1 {
                                /*CookingBookCategory.MISC,
                                 *///?}
                                Items.GOLD_INGOT,
                                0.1F,
                                200)
                        .unlockedBy("has_ingredient", has(ModItems.GOLDEN_WARHAMMER))
                        .save(this.output);

                buildCutlass(ModItems.WOODEN_CUTLASS, tag(ItemTags.PLANKS), Items.STICK);
                buildCutlass(ModItems.STONE_CUTLASS, tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                buildCutlass(ModItems.COPPER_CUTLASS, Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildCutlass(ModItems.IRON_CUTLASS, Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildCutlass(ModItems.GOLDEN_CUTLASS, Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildCutlass(ModItems.DIAMOND_CUTLASS, Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(ModItems.DIAMOND_CUTLASS, RecipeCategory.COMBAT, ModItems.NETHERITE_CUTLASS);

                buildMorningstar(ModItems.WOODEN_MORNINGSTAR, tag(ItemTags.PLANKS), Items.STICK);
                buildMorningstar(ModItems.STONE_MORNINGSTAR, tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                buildMorningstar(ModItems.COPPER_MORNINGSTAR, Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildMorningstar(ModItems.IRON_MORNINGSTAR, Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildMorningstar(ModItems.GOLDEN_MORNINGSTAR, Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildMorningstar(ModItems.DIAMOND_MORNINGSTAR, Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(ModItems.DIAMOND_MORNINGSTAR, RecipeCategory.COMBAT, ModItems.NETHERITE_MORNINGSTAR);

                buildEstoc(ModItems.WOODEN_ESTOC, tag(ItemTags.PLANKS), Items.STICK);
                buildEstoc(ModItems.STONE_ESTOC, tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                buildEstoc(ModItems.COPPER_ESTOC, Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildEstoc(ModItems.IRON_ESTOC, Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildEstoc(ModItems.GOLDEN_ESTOC, Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildEstoc(ModItems.DIAMOND_ESTOC, Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(ModItems.DIAMOND_ESTOC, RecipeCategory.COMBAT, ModItems.NETHERITE_ESTOC);

                buildGreataxe(ModItems.WOODEN_GREATAXE, tag(ItemTags.PLANKS), Items.STICK);
                buildGreataxe(ModItems.STONE_GREATAXE, tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                buildGreataxe(ModItems.COPPER_GREATAXE, Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildGreataxe(ModItems.IRON_GREATAXE, Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildGreataxe(ModItems.GOLDEN_GREATAXE, Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildGreataxe(ModItems.DIAMOND_GREATAXE, Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(ModItems.DIAMOND_GREATAXE, RecipeCategory.COMBAT, ModItems.NETHERITE_GREATAXE);

                buildWarhammers(ModItems.WOODEN_WARHAMMER, tag(ItemTags.LOGS), Items.STICK);
                buildWarhammers(ModItems.STONE_WARHAMMER, tag(ItemTags.STONE_BRICKS), Items.COBBLESTONE);
                //? if >=26.2 {
                /*buildWarhammers(ModItems.COPPER_WARHAMMER, Ingredient.of(Items.COPPER_BLOCK.weathering().unaffected()), Items.COPPER_INGOT);
                 *///? } else {
                buildWarhammers(ModItems.COPPER_WARHAMMER, Ingredient.of(Items.COPPER_BLOCK), Items.COPPER_INGOT);
                //? }
                buildWarhammers(ModItems.IRON_WARHAMMER, Ingredient.of(Items.IRON_BLOCK), Items.IRON_INGOT);
                buildWarhammers(ModItems.GOLDEN_WARHAMMER, Ingredient.of(Items.GOLD_BLOCK), Items.GOLD_INGOT);
                buildWarhammers(ModItems.DIAMOND_WARHAMMER, Ingredient.of(Items.DIAMOND_BLOCK), Items.DIAMOND);
                netheriteSmithing(ModItems.DIAMOND_WARHAMMER, RecipeCategory.COMBAT, ModItems.NETHERITE_WARHAMMER);

            }

            public void buildWarhammers(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(recipeOutput);
            }

            public void buildGreataxe(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern(" NN")
                        .pattern("NH ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(recipeOutput);
            }
            public void buildEstoc(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern(" N ")
                        .pattern(" H ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(recipeOutput);
            }
            public void buildMorningstar(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern(" NN")
                        .pattern(" HN")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(recipeOutput);
            }

            public void buildCutlass(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern("  N")
                        .pattern("NN ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(recipeOutput);
            }
        };
    }
}
