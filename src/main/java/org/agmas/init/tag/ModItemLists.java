package org.agmas.init.tag;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.agmas.init.ModItems;

import java.util.List;

public class ModItemLists {
    public static List<Item> cutlasses = List.of(
            ModItems.CUTLASS,
            ModItems.WOODEN_CUTLASS,
            ModItems.STONE_CUTLASS,
            //? if >=1.21.10 {
            ModItems.COPPER_CUTLASS,
            //? }
            ModItems.IRON_CUTLASS,
            ModItems.GOLDEN_CUTLASS,
            ModItems.DIAMOND_CUTLASS,
            ModItems.NETHERITE_CUTLASS
    );

    public static List<Item> morningstar = List.of(
            ModItems.MORNINGSTAR,
            ModItems.WOODEN_MORNINGSTAR,
            ModItems.STONE_MORNINGSTAR,
            //? if >=1.21.10 {
            ModItems.COPPER_MORNINGSTAR,
            //? }
            ModItems.IRON_MORNINGSTAR,
            ModItems.GOLDEN_MORNINGSTAR,
            ModItems.DIAMOND_MORNINGSTAR,
            ModItems.NETHERITE_MORNINGSTAR
    );

    public static List<Item> estoc = List.of(
            ModItems.ESTOC,
            ModItems.WOODEN_ESTOC,
            ModItems.STONE_ESTOC,
            //? if >=1.21.10 {
            ModItems.COPPER_ESTOC,
            //? }
            ModItems.IRON_ESTOC,
            ModItems.GOLDEN_ESTOC,
            ModItems.DIAMOND_ESTOC,
            ModItems.NETHERITE_ESTOC
    );

    public static List<Item> greataxes = List.of(
            ModItems.GREATAXE,
            ModItems.WOODEN_GREATAXE,
            ModItems.STONE_GREATAXE,
            //? if >=1.21.10 {
            ModItems.COPPER_GREATAXE,
            //? }
            ModItems.IRON_GREATAXE,
            ModItems.GOLDEN_GREATAXE,
            ModItems.DIAMOND_GREATAXE,
            ModItems.NETHERITE_GREATAXE
    );

    public static List<Item> warhammers = List.of(
            ModItems.WARHAMMER,
            ModItems.WOODEN_WARHAMMER,
            ModItems.STONE_WARHAMMER,
            //? if >=1.21.10 {
            ModItems.COPPER_WARHAMMER,
            //? }
            ModItems.IRON_WARHAMMER,
            ModItems.GOLDEN_WARHAMMER,
            ModItems.DIAMOND_WARHAMMER,
            ModItems.NETHERITE_WARHAMMER
    );

    // Heavies

    public static List<Item> wooden_heavy = List.of(
            ModItems.WOODEN_MORNINGSTAR,
            ModItems.WOODEN_WARHAMMER,
            ModItems.WOODEN_GREATAXE,
            Items.WOODEN_AXE,
            Items.WOODEN_SPEAR
    );

    public static List<Item> stone_heavy = List.of(
            ModItems.STONE_MORNINGSTAR,
            ModItems.STONE_WARHAMMER,
            ModItems.STONE_GREATAXE,
            Items.STONE_AXE,
            Items.STONE_SPEAR
    );

    public static List<Item> copper_heavy = List.of(
            ModItems.COPPER_MORNINGSTAR,
            ModItems.COPPER_WARHAMMER,
            ModItems.COPPER_GREATAXE,
            Items.COPPER_AXE,
            Items.COPPER_SPEAR
    );

    public static List<Item> iron_heavy = List.of(
            ModItems.IRON_MORNINGSTAR,
            ModItems.IRON_WARHAMMER,
            ModItems.IRON_GREATAXE,
            Items.IRON_AXE,
            Items.IRON_SPEAR
    );

    public static List<Item> golden_heavy = List.of(
            ModItems.GOLDEN_MORNINGSTAR,
            ModItems.GOLDEN_WARHAMMER,
            ModItems.GOLDEN_GREATAXE,
            Items.GOLDEN_AXE,
            Items.GOLDEN_SPEAR
    );

    public static List<Item> diamond_heavy = List.of(
            ModItems.DIAMOND_MORNINGSTAR,
            ModItems.DIAMOND_WARHAMMER,
            ModItems.DIAMOND_GREATAXE,
            Items.DIAMOND_AXE,
            Items.DIAMOND_SPEAR
    );

    public static List<Item> netherite_heavy = List.of(
            ModItems.NETHERITE_MORNINGSTAR,
            ModItems.NETHERITE_WARHAMMER,
            ModItems.NETHERITE_GREATAXE,
            Items.NETHERITE_AXE,
            Items.NETHERITE_SPEAR
    );

    // Lights

    public static List<Item> wooden_light = List.of(
            ModItems.WOODEN_CUTLASS,
            ModItems.WOODEN_ESTOC,
            Items.WOODEN_SWORD
    );

    public static List<Item> stone_light = List.of(
            ModItems.STONE_CUTLASS,
            ModItems.STONE_ESTOC,
            Items.STONE_SWORD
    );

    public static List<Item> copper_light = List.of(
            ModItems.COPPER_CUTLASS,
            ModItems.COPPER_ESTOC,
            Items.COPPER_SWORD
    );

    public static List<Item> iron_light = List.of(
            ModItems.IRON_CUTLASS,
            ModItems.IRON_ESTOC,
            Items.IRON_SWORD
    );

    public static List<Item> golden_light = List.of(
            ModItems.GOLDEN_CUTLASS,
            ModItems.GOLDEN_ESTOC,
            Items.GOLDEN_SWORD
    );

    public static List<Item> diamond_light = List.of(
            ModItems.DIAMOND_CUTLASS,
            ModItems.DIAMOND_ESTOC,
            Items.DIAMOND_SWORD
    );

    public static List<Item> netherite_light = List.of(
            ModItems.NETHERITE_CUTLASS,
            ModItems.NETHERITE_ESTOC,
            Items.NETHERITE_SWORD
    );



    public static List<Item> ranged = List.of(
            ModItems.FLINTLOCK,
            Items.BOW,
            Items.CROSSBOW
    );

}
