package org.agmas.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.trim.TrimMaterials;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ModColors {
    public static HashMap<Ingredient, Integer> trimColors = new HashMap<>();
    public static HashMap<TagKey<Item>, Integer> tagTrimColors = new HashMap<>();

    public static int WOOD = new Color(148,115,64).getRGB();
    public static int STONE = new Color(134,134,134).getRGB();
    public static int COPPER = new Color(208,110,81).getRGB();
    public static int IRON = Color.LIGHT_GRAY.getRGB();
    public static int GOLD = new Color(241,216,72).getRGB();
    public static int DIAMOND = Color.CYAN.getRGB();
    public static int NETHERITE = Color.MAGENTA.getRGB();

    public static int FROST_PROTECTION = new Color(202, 255, 252).getRGB();

    public static int AMETHYST = new Color(173, 136, 232).getRGB();
    public static int LAPIS = new Color(28, 82, 165).getRGB();
    public static int RESIN = new Color(238, 119, 27).getRGB();

    public static void init() {

        // Welcome back jank jank jank sahur
        // We missed you

        trimColors.put(Ingredient.of(Items.NETHERITE_INGOT), NETHERITE);
        trimColors.put(Ingredient.of(Items.DIAMOND), DIAMOND);
        trimColors.put(Ingredient.of(Items.GOLD_INGOT), GOLD);
        trimColors.put(Ingredient.of(Items.IRON_INGOT), IRON);
        trimColors.put(Ingredient.of(Items.COPPER_INGOT), COPPER);
        trimColors.put(Ingredient.of(Items.LAPIS_LAZULI), LAPIS);
        trimColors.put(Ingredient.of(Items.RESIN_CLUMP), RESIN);
        trimColors.put(Ingredient.of(Items.AMETHYST_SHARD), AMETHYST);
        trimColors.put(Ingredient.of(Items.REDSTONE), Color.RED.getRGB());
        trimColors.put(Ingredient.of(Items.EMERALD), Color.GREEN.getRGB());
        trimColors.put(Ingredient.of(Items.QUARTZ), Color.WHITE.getRGB());
        tagTrimColors.put(ItemTags.STONE_CRAFTING_MATERIALS, STONE);
        tagTrimColors.put(ItemTags.PLANKS, WOOD);

        //? <26.2 {
        trimColors.put(Ingredient.of(Items.BLACK_DYE), DyeColor.BLACK.getFireworkColor());
        trimColors.put(Ingredient.of(Items.BLUE_DYE), DyeColor.BLUE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.BROWN_DYE), DyeColor.BROWN.getFireworkColor());
        trimColors.put(Ingredient.of(Items.CYAN_DYE), DyeColor.CYAN.getFireworkColor());
        trimColors.put(Ingredient.of(Items.GRAY_DYE), DyeColor.GRAY.getFireworkColor());
        trimColors.put(Ingredient.of(Items.GREEN_DYE), DyeColor.GREEN.getFireworkColor());
        trimColors.put(Ingredient.of(Items.LIGHT_BLUE_DYE), DyeColor.LIGHT_BLUE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.LIGHT_GRAY_DYE), DyeColor.LIGHT_GRAY.getFireworkColor());
        trimColors.put(Ingredient.of(Items.LIME_DYE), DyeColor.LIME.getFireworkColor());
        trimColors.put(Ingredient.of(Items.YELLOW_DYE), DyeColor.YELLOW.getFireworkColor());
        trimColors.put(Ingredient.of(Items.WHITE_DYE), DyeColor.WHITE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.RED_DYE), DyeColor.RED.getFireworkColor());
        trimColors.put(Ingredient.of(Items.PURPLE_DYE), DyeColor.PURPLE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.ORANGE_DYE), DyeColor.ORANGE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.MAGENTA_DYE), DyeColor.MAGENTA.getFireworkColor());
        trimColors.put(Ingredient.of(Items.PINK_DYE), DyeColor.PINK.getFireworkColor());
        //? } else {
        /*trimColors.put(Ingredient.of(Items.DYE.black()), DyeColor.BLACK.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.blue()), DyeColor.BLUE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.brown()), DyeColor.BROWN.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.cyan()), DyeColor.CYAN.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.gray()), DyeColor.GRAY.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.green()), DyeColor.GREEN.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.lightBlue()), DyeColor.LIGHT_BLUE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.lightGray()), DyeColor.LIGHT_GRAY.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.lime()), DyeColor.LIME.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.yellow()), DyeColor.YELLOW.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.white()), DyeColor.WHITE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.red()), DyeColor.RED.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.purple()), DyeColor.PURPLE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.orange()), DyeColor.ORANGE.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.magenta()), DyeColor.MAGENTA.getFireworkColor());
        trimColors.put(Ingredient.of(Items.DYE.pink()), DyeColor.PINK.getFireworkColor());
        *///? }
    }
}
