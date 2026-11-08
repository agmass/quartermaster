package org.agmas.init.tag;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.agmas.init.ModItems;

import java.util.List;

public class ModItemLists {
    public static List<Item> cutlasses;

    public static List<Item> morningstar;

    public static List<Item> estoc;

    public static List<Item> greataxes;

    public static List<Item> warhammers;

    // Heavies

    public static List<Item> wooden_heavy;

    public static List<Item> stone_heavy;

    //? if >=1.21.11 {
    public static List<Item> copper_heavy;
    //? }

    public static List<Item> iron_heavy;

    public static List<Item> golden_heavy;

    public static List<Item> diamond_heavy;

    public static List<Item> netherite_heavy;

    // Lights

    public static List<Item> wooden_light;

    public static List<Item> stone_light;


    //? if >=1.21.11 {
    public static List<Item> copper_light;
    //? }

    public static List<Item> iron_light;

    public static List<Item> golden_light;

    public static List<Item> diamond_light;

    public static List<Item> netherite_light;

    public static List<Item> bamboo_weapons;


    public static List<Item> ranged = List.of(
            ModItems.FLINTLOCK,
            Items.BOW,
            Items.CROSSBOW
    );

}
