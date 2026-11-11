package org.agmas.client.datagen;

//? if >=26.1 {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
*///? } else {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
 //? }
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
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
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.CUTLASS.relic())
                        .pattern("  N")
                        .pattern("NN ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(output);


                shaped(RecipeCategory.COMBAT, ModItems.MORNINGSTAR.relic())
                        .pattern(" NN")
                        .pattern(" NN")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(output);


                shaped(RecipeCategory.COMBAT, ModItems.ESTOC.relic())
                        .pattern(" N ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.GREATAXE.relic())
                        .pattern(" NN")
                        .pattern("NN ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.WARHAMMER.relic())
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', Items.NETHERITE_INGOT)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.SICKLES.relic())
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern(" H ")
                        .define('N', Items.NETHERITE_INGOT)
                        .define('H', ModItems.RELIC_HANDLE)
                        .unlockedBy("has_ingredient", has(ModItems.RUINED_HANDLE))
                        .save(output);

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

                generateSmelt("cutlass", ModItems.CUTLASS);
                generateSmelt("estoc", ModItems.ESTOC);
                generateSmelt("rapier", ModItems.RAPIER);
                generateSmelt("sickles", ModItems.SICKLES);
                generateSmelt("greataxe", ModItems.GREATAXE);
                generateSmelt("warhammer", ModItems.WARHAMMER);
                generateSmelt("morningstar", ModItems.MORNINGSTAR);

                buildCutlass(ModItems.CUTLASS.wooden(), tag(ItemTags.PLANKS), Items.STICK);
                buildCutlass(ModItems.CUTLASS.stone(), tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                //? if >=1.21.10
                buildCutlass(ModItems.CUTLASS.copper(), Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildCutlass(ModItems.CUTLASS.iron(), Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildCutlass(ModItems.CUTLASS.golden(), Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildCutlass(ModItems.CUTLASS.diamond(), Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(
                        //? if <=1.21.1
                        //output,
                        ModItems.CUTLASS.diamond(), RecipeCategory.COMBAT, ModItems.CUTLASS.netherite());

                buildRapier(ModItems.RAPIER.wooden(), tag(ItemTags.PLANKS), Items.STICK);
                buildRapier(ModItems.RAPIER.stone(), tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                //? if >=1.21.10
                buildRapier(ModItems.RAPIER.copper(), Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildRapier(ModItems.RAPIER.iron(), Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildRapier(ModItems.RAPIER.golden(), Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildRapier(ModItems.RAPIER.diamond(), Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(
                        //? if <=1.21.1
                        //output,
                        ModItems.RAPIER.diamond(), RecipeCategory.COMBAT, ModItems.RAPIER.netherite());

                buildSickles(ModItems.SICKLES.wooden(), tag(ItemTags.PLANKS), Items.STICK);
                buildSickles(ModItems.SICKLES.stone(), tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                //? if >=1.21.10
                buildSickles(ModItems.SICKLES.copper(), Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildSickles(ModItems.SICKLES.iron(), Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildSickles(ModItems.SICKLES.golden(), Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildSickles(ModItems.SICKLES.diamond(), Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(
                        //? if <=1.21.1
                        //output,
                        ModItems.SICKLES.diamond(), RecipeCategory.COMBAT, ModItems.SICKLES.netherite());

                buildMorningstar(ModItems.MORNINGSTAR.wooden(), tag(ItemTags.PLANKS), Items.STICK);
                buildMorningstar(ModItems.MORNINGSTAR.stone(), tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                //? if >=1.21.10
                buildMorningstar(ModItems.MORNINGSTAR.copper(), Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildMorningstar(ModItems.MORNINGSTAR.iron(), Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildMorningstar(ModItems.MORNINGSTAR.golden(), Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildMorningstar(ModItems.MORNINGSTAR.diamond(), Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(
                        //? if <=1.21.1
                        //output,
                        ModItems.MORNINGSTAR.diamond(), RecipeCategory.COMBAT, ModItems.MORNINGSTAR.netherite());

                buildEstoc(ModItems.ESTOC.wooden(), tag(ItemTags.PLANKS), Items.STICK);
                buildEstoc(ModItems.ESTOC.stone(), tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                //? if >=1.21.10
                buildEstoc(ModItems.ESTOC.copper(), Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildEstoc(ModItems.ESTOC.iron(), Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildEstoc(ModItems.ESTOC.golden(), Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildEstoc(ModItems.ESTOC.diamond(), Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(
                        //? if <=1.21.1
                        //output,
                        ModItems.ESTOC.diamond(), RecipeCategory.COMBAT, ModItems.ESTOC.netherite());

                buildGreataxe(ModItems.GREATAXE.wooden(), tag(ItemTags.PLANKS), Items.STICK);
                buildGreataxe(ModItems.GREATAXE.stone(), tag(ItemTags.STONE_TOOL_MATERIALS), Items.COBBLESTONE);
                //? if >=1.21.10
                buildGreataxe(ModItems.GREATAXE.copper(), Ingredient.of(Items.COPPER_INGOT), Items.COPPER_INGOT);
                buildGreataxe(ModItems.GREATAXE.iron(), Ingredient.of(Items.IRON_INGOT), Items.IRON_INGOT);
                buildGreataxe(ModItems.GREATAXE.golden(), Ingredient.of(Items.GOLD_INGOT), Items.GOLD_INGOT);
                buildGreataxe(ModItems.GREATAXE.diamond(), Ingredient.of(Items.DIAMOND), Items.DIAMOND);
                netheriteSmithing(
                        //? if <=1.21.1
                        //output,
                        ModItems.GREATAXE.diamond(), RecipeCategory.COMBAT, ModItems.GREATAXE.netherite());

                buildWarhammers(ModItems.WARHAMMER.wooden(), tag(ItemTags.LOGS), Items.STICK);
                buildWarhammers(ModItems.WARHAMMER.stone(), tag(ItemTags.STONE_BRICKS), Items.COBBLESTONE);
                //? if >=26.2 {
                /*buildWarhammers(ModItems.WARHAMMER.copper(), Ingredient.of(Items.COPPER_BLOCK.weathering().unaffected()), Items.COPPER_INGOT);
                 *///? } else if >=1.21.10 {
                buildWarhammers(ModItems.WARHAMMER.copper(), Ingredient.of(Items.COPPER_BLOCK), Items.COPPER_INGOT);
                //? }
                buildWarhammers(ModItems.WARHAMMER.iron(), Ingredient.of(Items.IRON_BLOCK), Items.IRON_INGOT);
                buildWarhammers(ModItems.WARHAMMER.golden(), Ingredient.of(Items.GOLD_BLOCK), Items.GOLD_INGOT);
                buildWarhammers(ModItems.WARHAMMER.diamond(), Ingredient.of(Items.DIAMOND_BLOCK), Items.DIAMOND);
                netheriteSmithing(
                        //? if <=1.21.1
                        //output,
                        ModItems.WARHAMMER.diamond(), RecipeCategory.COMBAT, ModItems.WARHAMMER.netherite());

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


                buildEstoc(ModItems.ESTOC.bamboo(), Ingredient.of(Items.BAMBOO), Items.BAMBOO);
                buildCutlass(ModItems.CUTLASS.bamboo(), Ingredient.of(Items.BAMBOO), Items.BAMBOO);
                buildMorningstar(ModItems.MORNINGSTAR.bamboo(), Ingredient.of(Items.BAMBOO), Items.BAMBOO);
                buildGreataxe(ModItems.GREATAXE.bamboo(), Ingredient.of(Items.BAMBOO), Items.BAMBOO);
                buildWarhammers(ModItems.WARHAMMER.bamboo(), Ingredient.of(Items.BAMBOO), Items.BAMBOO);
            }

            public void generateSmelt(String name, ModItems.QMWeapon weapon) {
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
                        .save(output, name + "_smelt_iron");

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
                        .save(output, name + "_smelt");
            }

            public void buildRapier(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern("  N")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(output);
            }

            public void buildSickles(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern(" H ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(output);
            }

            public void buildWarhammers(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern("   ")
                        .pattern(" N ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(output);
            }

            public void buildGreataxe(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern(" NN")
                        .pattern("NH ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(output);
            }
            public void buildEstoc(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern(" N ")
                        .pattern(" H ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(output);
            }
            public void buildMorningstar(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern(" NN")
                        .pattern(" HN")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(output);
            }

            public void buildCutlass(Item cutlass, Ingredient material, Item unlock) {
                shaped(RecipeCategory.COMBAT, cutlass)
                        .pattern("  N")
                        .pattern("NN ")
                        .pattern("H  ")
                        .define('N', material)
                        .define('H', Items.STICK)
                        .unlockedBy("has_ingredient", has(unlock))
                        .save(output);
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
