package org.agmas.init.tag;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.agmas.init.ModItems;

import java.util.ArrayList;
import java.util.List;

public class ModItemLists {
    // Heavies

    public static List<Item> wooden_heavy = new ArrayList<>();

    public static List<Item> stone_heavy = new ArrayList<>();

    //? if >=1.21.11 {
    public static List<Item> copper_heavy = new ArrayList<>();
    //? }

    public static List<Item> iron_heavy = new ArrayList<>();

    public static List<Item> golden_heavy = new ArrayList<>();

    public static List<Item> diamond_heavy = new ArrayList<>();

    public static List<Item> netherite_heavy = new ArrayList<>();

    // Lights

    public static List<Item> wooden_light = new ArrayList<>();

    public static List<Item> stone_light = new ArrayList<>();


    //? if >=1.21.11 {
    public static List<Item> copper_light = new ArrayList<>();
    //? }

    public static List<Item> iron_light = new ArrayList<>();

    public static List<Item> golden_light = new ArrayList<>();

    public static List<Item> diamond_light = new ArrayList<>();

    public static List<Item> netherite_light = new ArrayList<>();

    public static List<Item> bamboo_weapons = new ArrayList<>();

    public static List<ModItems.QMWeapon> weapons = new ArrayList<>();

    public static void initLists() {
        for (ModItems.QMWeapon weapon : weapons) {
            if (weapon.stone() != null) {
                if (weapon.weaponClass().equals(ModItems.WeaponClass.LIGHT)) {
                    wooden_light.add(weapon.wooden());
                    stone_light.add(weapon.stone());
                    //? >=1.21.10
                    copper_light.add(weapon.copper());
                    iron_light.add(weapon.iron());
                    diamond_light.add(weapon.diamond());
                    netherite_light.add(weapon.netherite());
                }
                if (weapon.weaponClass().equals(ModItems.WeaponClass.HEAVY)) {
                    wooden_heavy.add(weapon.wooden());
                    stone_heavy.add(weapon.stone());
                    //? >=1.21.10
                    copper_heavy.add(weapon.copper());
                    iron_heavy.add(weapon.iron());
                    diamond_heavy.add(weapon.diamond());
                    netherite_heavy.add(weapon.netherite());
                }
            }
            if (weapon.bamboo() != null) {
                bamboo_weapons.add(weapon.bamboo());
            }
            if (weapon.relic() != null) {
                bamboo_weapons.add(weapon.relic());
            }
        }
    }

    public static List<Item> ranged = List.of(
            ModItems.FLINTLOCK,
            Items.BOW,
            Items.CROSSBOW
    );

}
