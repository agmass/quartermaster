package org.agmas.init;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
//? if <26.1 {
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
//? } else {
/*import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
*///? }
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;
import org.agmas.init.tag.ModItemLists;
import org.agmas.item.*;

import java.util.function.Function;

public class ModItems {

    public static Item RELIC_HANDLE = register("relic_handle", Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    public static Item RUINED_HANDLE = register("ruined_handle", RapierHandleItem::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE));

    public static Item COMBAT_EFFECT_SMITHING_TEMPLATE = register("combat_effect_smithing_template", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));

    public static Item FLINTLOCK = register("flintlock", FlintlockItem::new, new Item.Properties().stacksTo(1).enchantable(9).rarity(Rarity.UNCOMMON).durability(930));
    public static Item PELLET = register("pellet", Item::new, new Item.Properties());
    public static Item AMMUNITION = register("ammunition", Item::new, new Item.Properties());

    public static Item RAPIER = register("rapier", RapierItem::new, RapierItem.createSettings(ToolMaterial.NETHERITE).rarity(Rarity.RARE));
    // Cutlasses
    public static Item CUTLASS = register("cutlass", CutlassItem::new, CutlassItem.createSettings(ToolMaterial.NETHERITE).rarity(Rarity.RARE));

    public static Item WOODEN_CUTLASS = register("wooden_cutlass", CutlassItem::new, CutlassItem.createSettings(ToolMaterial.WOOD));
    public static Item STONE_CUTLASS = register("stone_cutlass", CutlassItem::new, CutlassItem.createSettings(ToolMaterial.STONE));
    //? if >=1.21.10 {
    public static Item COPPER_CUTLASS = register("copper_cutlass", CutlassItem::new, CutlassItem.createSettings(ToolMaterial.COPPER));
    //? }
    public static Item IRON_CUTLASS = register("iron_cutlass", CutlassItem::new, CutlassItem.createSettings(ToolMaterial.IRON));
    public static Item GOLDEN_CUTLASS = register("golden_cutlass", CutlassItem::new, CutlassItem.createSettings(ToolMaterial.GOLD));
    public static Item DIAMOND_CUTLASS = register("diamond_cutlass", CutlassItem::new, CutlassItem.createSettings(ToolMaterial.DIAMOND));
    public static Item NETHERITE_CUTLASS = register("netherite_cutlass", CutlassItem::new, CutlassItem.createSettings(ToolMaterial.NETHERITE));

    // Morningstars
    public static Item MORNINGSTAR = register("morningstar", MorningstarItem::new, MorningstarItem.createSettings(ToolMaterial.NETHERITE).rarity(Rarity.RARE));

    public static Item WOODEN_MORNINGSTAR = register("wooden_morningstar", MorningstarItem::new, MorningstarItem.createSettings(ToolMaterial.WOOD));
    public static Item STONE_MORNINGSTAR = register("stone_morningstar", MorningstarItem::new, MorningstarItem.createSettings(ToolMaterial.STONE));
    //? if >=1.21.10 {
    public static Item COPPER_MORNINGSTAR = register("copper_morningstar", MorningstarItem::new, MorningstarItem.createSettings(ToolMaterial.COPPER));
    //? }
    public static Item IRON_MORNINGSTAR = register("iron_morningstar", MorningstarItem::new, MorningstarItem.createSettings(ToolMaterial.IRON));
    public static Item GOLDEN_MORNINGSTAR = register("golden_morningstar", MorningstarItem::new, MorningstarItem.createSettings(ToolMaterial.GOLD));
    public static Item DIAMOND_MORNINGSTAR = register("diamond_morningstar", MorningstarItem::new, MorningstarItem.createSettings(ToolMaterial.DIAMOND));
    public static Item NETHERITE_MORNINGSTAR = register("netherite_morningstar", MorningstarItem::new, MorningstarItem.createSettings(ToolMaterial.NETHERITE));

    // Estoc
    public static Item ESTOC = register("estoc", EstocItem::new, EstocItem.createSettings(ToolMaterial.NETHERITE).rarity(Rarity.RARE));

    public static Item WOODEN_ESTOC = register("wooden_estoc", EstocItem::new, EstocItem.createSettings(ToolMaterial.WOOD));
    public static Item STONE_ESTOC = register("stone_estoc", EstocItem::new, EstocItem.createSettings(ToolMaterial.STONE));
    //? if >=1.21.10 {
    public static Item COPPER_ESTOC = register("copper_estoc", EstocItem::new, EstocItem.createSettings(ToolMaterial.COPPER));
    //? }
    public static Item IRON_ESTOC = register("iron_estoc", EstocItem::new, EstocItem.createSettings(ToolMaterial.IRON));
    public static Item GOLDEN_ESTOC = register("golden_estoc", EstocItem::new, EstocItem.createSettings(ToolMaterial.GOLD));
    public static Item DIAMOND_ESTOC = register("diamond_estoc", EstocItem::new, EstocItem.createSettings(ToolMaterial.DIAMOND));
    public static Item NETHERITE_ESTOC = register("netherite_estoc", EstocItem::new, EstocItem.createSettings(ToolMaterial.NETHERITE));

    // Greataxe
    public static Item GREATAXE = register("greataxe", GreataxeItem::new, GreataxeItem.createSettings(ToolMaterial.NETHERITE).rarity(Rarity.RARE));

    public static Item WOODEN_GREATAXE = register("wooden_greataxe", GreataxeItem::new, GreataxeItem.createSettings(ToolMaterial.WOOD));
    public static Item STONE_GREATAXE = register("stone_greataxe", GreataxeItem::new, GreataxeItem.createSettings(ToolMaterial.STONE));
    //? if >=1.21.10 {
    public static Item COPPER_GREATAXE = register("copper_greataxe", GreataxeItem::new, GreataxeItem.createSettings(ToolMaterial.COPPER));
    //? }
    public static Item IRON_GREATAXE = register("iron_greataxe", GreataxeItem::new, GreataxeItem.createSettings(ToolMaterial.IRON));
    public static Item GOLDEN_GREATAXE = register("golden_greataxe", GreataxeItem::new, GreataxeItem.createSettings(ToolMaterial.GOLD));
    public static Item DIAMOND_GREATAXE = register("diamond_greataxe", GreataxeItem::new, GreataxeItem.createSettings(ToolMaterial.DIAMOND));
    public static Item NETHERITE_GREATAXE = register("netherite_greataxe", GreataxeItem::new, GreataxeItem.createSettings(ToolMaterial.NETHERITE));

    // Warhammer
    public static Item WARHAMMER = register("warhammer", WarhammerItem::new, WarhammerItem.createSettings(ToolMaterial.NETHERITE).rarity(Rarity.RARE));

    public static Item WOODEN_WARHAMMER = register("wooden_warhammer", WarhammerItem::new, WarhammerItem.createSettings(ToolMaterial.WOOD));
    public static Item STONE_WARHAMMER = register("stone_warhammer", WarhammerItem::new, WarhammerItem.createSettings(ToolMaterial.STONE));
    //? if >=1.21.10 {
    public static Item COPPER_WARHAMMER = register("copper_warhammer", WarhammerItem::new, WarhammerItem.createSettings(ToolMaterial.COPPER));
    //? }
    public static Item IRON_WARHAMMER = register("iron_warhammer", WarhammerItem::new, WarhammerItem.createSettings(ToolMaterial.IRON));
    public static Item GOLDEN_WARHAMMER = register("golden_warhammer", WarhammerItem::new, WarhammerItem.createSettings(ToolMaterial.GOLD));
    public static Item DIAMOND_WARHAMMER = register("diamond_warhammer", WarhammerItem::new, WarhammerItem.createSettings(ToolMaterial.DIAMOND));
    public static Item NETHERITE_WARHAMMER = register("netherite_warhammer", WarhammerItem::new, WarhammerItem.createSettings(ToolMaterial.NETHERITE));


    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, QMIdentifier.of(name).id);

        // Create the item instance.
        T item = itemFactory.apply(settings
                //? if >=1.21.4 {
                .setId(itemKey)
                //? }
        );

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
    public static void init() {
        ServerLivingEntityEvents.AFTER_DAMAGE.register(CutlassItem::onHit);

        //? if >=26.1 {
        /*CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
        *///? } else {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
         //? }
                .register((creativeTab) -> {
                    creativeTab.accept(ModItems.RELIC_HANDLE);
                    creativeTab.accept(ModItems.RUINED_HANDLE);
                    creativeTab.accept(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE);
                });

        //? if >=26.1 {
        /*CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
        *///? } else {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
         //? }
                .register((creativeTab) -> {
                    //? if >=26.1 {
                    
                    /*ItemStack coralRapier = ModItems.RAPIER.getDefaultInstance();
                    coralRapier.set(ModComponents.IS_CORAL, true);
                    creativeTab.insertAfter(Items.NETHERITE_SWORD, ModItems.RAPIER);
                    creativeTab.insertAfter(Items.NETHERITE_SWORD, coralRapier);

                    // Heavies
                    creativeTab.insertAfter(Items.NETHERITE_AXE, ModItemLists.greataxes.toArray(new Item[0]));
                    creativeTab.insertAfter(Items.NETHERITE_AXE, ModItemLists.warhammers.toArray(new Item[0]));
                    creativeTab.insertAfter(Items.NETHERITE_AXE, ModItemLists.morningstar.toArray(new Item[0]));

                    // Swords
                    creativeTab.insertAfter(Items.NETHERITE_SWORD, ModItemLists.estoc.toArray(new Item[0]));
                    creativeTab.insertAfter(Items.NETHERITE_SWORD, ModItemLists.cutlasses.toArray(new Item[0]));

                    *///? } else {
                    ItemStack coralRapier = ModItems.RAPIER.getDefaultInstance();
                    coralRapier.set(ModComponents.IS_CORAL, true);
                    creativeTab.addAfter(Items.NETHERITE_SWORD, ModItems.RAPIER);
                    creativeTab.addAfter(Items.NETHERITE_SWORD, coralRapier);

                    // Heavies
                    creativeTab.addAfter(Items.NETHERITE_AXE, ModItemLists.greataxes.toArray(new Item[0]));
                    creativeTab.addAfter(Items.NETHERITE_AXE, ModItemLists.warhammers.toArray(new Item[0]));
                    creativeTab.addAfter(Items.NETHERITE_AXE, ModItemLists.morningstar.toArray(new Item[0]));

                    // Swords
                    creativeTab.addAfter(Items.NETHERITE_SWORD, ModItemLists.estoc.toArray(new Item[0]));
                    creativeTab.addAfter(Items.NETHERITE_SWORD, ModItemLists.cutlasses.toArray(new Item[0]));
                    //? }
                });
    }
}
