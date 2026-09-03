package org.agmas.init;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
//? if <26.1 {
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
//? } else {
/*import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
*///? }
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
//? if >1.21.1
//import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.agmas.porting.QMIdentifier;
import org.agmas.init.tag.ModItemLists;
import org.agmas.init.tag.ModTags;
import org.agmas.item.*;
import org.agmas.porting.QMPToolMaterial;

import java.util.function.Function;

public class ModItems {
    public static final QMPToolMaterial BAMBOO =
            //? if >1.21.1
            //new QMPToolMaterial(new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 9999999, 0.0F, 0.0F, 15, ModTags.BAMBOO_REPAIR_MATERIALS));
            //? if <=1.21.1 {
            new QMPToolMaterial(new Tier() {
                @Override
                public int getUses() {
                    return 99999999;
                }

                @Override
                public float getSpeed() {
                    return 0;
                }

                @Override
                public float getAttackDamageBonus() {
                    return 0;
                }

                @Override
                public TagKey<Block> getIncorrectBlocksForDrops() {
                    return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
                }

                @Override
                public int getEnchantmentValue() {
                    return 15;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(ModTags.BAMBOO_REPAIR_MATERIALS);
                }
            });
            //? }


    public static Item RELIC_HANDLE = register("relic_handle", Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    public static Item RUINED_HANDLE = register("ruined_handle", RapierHandleItem::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE));

    public static Item COMBAT_EFFECT_SMITHING_TEMPLATE = register("combat_effect_smithing_template", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));

    public static Item FLINTLOCK = register("flintlock", FlintlockItem::new, new Item.Properties().stacksTo(1)
            //? if >1.21.1
            //.enchantable(9)
            .rarity(Rarity.UNCOMMON).durability(930));
    public static Item PELLET = register("pellet", Item::new, new Item.Properties());
    public static Item AMMUNITION = register("ammunition", Item::new, new Item.Properties());

    public static Item RAPIER = register("rapier", RapierItem::new, RapierItem.createSettings(QMPToolMaterial.NETHERITE).rarity(Rarity.RARE).fireResistant());
    // Cutlasses
    public static Item CUTLASS = register("cutlass", (p)->new CutlassItem(QMPToolMaterial.NETHERITE, p), CutlassItem.createSettings(QMPToolMaterial.NETHERITE).rarity(Rarity.RARE).fireResistant());

    public static Item WOODEN_CUTLASS = register("wooden_cutlass", (p)->new CutlassItem(QMPToolMaterial.WOOD, p), CutlassItem.createSettings(QMPToolMaterial.WOOD));
    public static Item STONE_CUTLASS = register("stone_cutlass", (p)->new CutlassItem(QMPToolMaterial.STONE, p), CutlassItem.createSettings(QMPToolMaterial.STONE));
    //? if >=1.21.10 {
    /*public static Item COPPER_CUTLASS = register("copper_cutlass", (p)->new CutlassItem(QMPToolMaterial.COPPER, p), CutlassItem.createSettings(QMPToolMaterial.COPPER));
    *///? }
    public static Item IRON_CUTLASS = register("iron_cutlass", (p)->new CutlassItem(QMPToolMaterial.IRON, p), CutlassItem.createSettings(QMPToolMaterial.IRON));
    public static Item GOLDEN_CUTLASS = register("golden_cutlass", (p)->new CutlassItem(QMPToolMaterial.GOLD, p), CutlassItem.createSettings(QMPToolMaterial.GOLD));
    public static Item DIAMOND_CUTLASS = register("diamond_cutlass", (p)->new CutlassItem(QMPToolMaterial.DIAMOND, p), CutlassItem.createSettings(QMPToolMaterial.DIAMOND));
    public static Item NETHERITE_CUTLASS = register("netherite_cutlass", (p)->new CutlassItem(QMPToolMaterial.NETHERITE, p), CutlassItem.createSettings(QMPToolMaterial.NETHERITE).fireResistant());

    // Morningstars
    public static Item MORNINGSTAR = register("morningstar", (p)->new MorningstarItem(QMPToolMaterial.NETHERITE, p), MorningstarItem.createSettings(QMPToolMaterial.NETHERITE).rarity(Rarity.RARE).fireResistant());

    public static Item WOODEN_MORNINGSTAR = register("wooden_morningstar", (p)->new MorningstarItem(QMPToolMaterial.WOOD, p), MorningstarItem.createSettings(QMPToolMaterial.WOOD));
    public static Item STONE_MORNINGSTAR = register("stone_morningstar", (p)->new MorningstarItem(QMPToolMaterial.STONE, p), MorningstarItem.createSettings(QMPToolMaterial.STONE));
    //? if >=1.21.10 {
    /*public static Item COPPER_MORNINGSTAR = register("copper_morningstar", (p)->new MorningstarItem(QMPToolMaterial.COPPER, p), MorningstarItem.createSettings(QMPToolMaterial.COPPER));
    *///? }
    public static Item IRON_MORNINGSTAR = register("iron_morningstar", (p)->new MorningstarItem(QMPToolMaterial.IRON, p), MorningstarItem.createSettings(QMPToolMaterial.IRON));
    public static Item GOLDEN_MORNINGSTAR = register("golden_morningstar", (p)->new MorningstarItem(QMPToolMaterial.GOLD, p), MorningstarItem.createSettings(QMPToolMaterial.GOLD));
    public static Item DIAMOND_MORNINGSTAR = register("diamond_morningstar", (p)->new MorningstarItem(QMPToolMaterial.DIAMOND, p), MorningstarItem.createSettings(QMPToolMaterial.DIAMOND));
    public static Item NETHERITE_MORNINGSTAR = register("netherite_morningstar", (p)->new MorningstarItem(QMPToolMaterial.NETHERITE, p), MorningstarItem.createSettings(QMPToolMaterial.NETHERITE).fireResistant());

    // Estoc
    public static Item ESTOC = register("estoc", (p)->new EstocItem(QMPToolMaterial.NETHERITE, p), EstocItem.createSettings(QMPToolMaterial.NETHERITE).rarity(Rarity.RARE).fireResistant());

    public static Item WOODEN_ESTOC = register("wooden_estoc", (p)->new EstocItem(QMPToolMaterial.WOOD, p), EstocItem.createSettings(QMPToolMaterial.WOOD));
    public static Item STONE_ESTOC = register("stone_estoc", (p)->new EstocItem(QMPToolMaterial.STONE, p), EstocItem.createSettings(QMPToolMaterial.STONE));
    //? if >=1.21.10 {
    /*public static Item COPPER_ESTOC = register("copper_estoc", (p)->new EstocItem(QMPToolMaterial.COPPER, p), EstocItem.createSettings(QMPToolMaterial.COPPER));
    *///? }
    public static Item IRON_ESTOC = register("iron_estoc", (p)->new EstocItem(QMPToolMaterial.IRON, p), EstocItem.createSettings(QMPToolMaterial.IRON));
    public static Item GOLDEN_ESTOC = register("golden_estoc", (p)->new EstocItem(QMPToolMaterial.GOLD, p), EstocItem.createSettings(QMPToolMaterial.GOLD));
    public static Item DIAMOND_ESTOC = register("diamond_estoc", (p)->new EstocItem(QMPToolMaterial.DIAMOND, p), EstocItem.createSettings(QMPToolMaterial.DIAMOND));
    public static Item NETHERITE_ESTOC = register("netherite_estoc", (p)->new EstocItem(QMPToolMaterial.NETHERITE, p), EstocItem.createSettings(QMPToolMaterial.NETHERITE).fireResistant());

    // Greataxe
    public static Item GREATAXE = register("greataxe", (p)->new GreataxeItem(QMPToolMaterial.NETHERITE, p), GreataxeItem.createSettings(QMPToolMaterial.NETHERITE).rarity(Rarity.RARE).fireResistant());

    public static Item WOODEN_GREATAXE = register("wooden_greataxe", (p)->new GreataxeItem(QMPToolMaterial.WOOD, p), GreataxeItem.createSettings(QMPToolMaterial.WOOD));
    public static Item STONE_GREATAXE = register("stone_greataxe", (p)->new GreataxeItem(QMPToolMaterial.STONE, p), GreataxeItem.createSettings(QMPToolMaterial.STONE));
    //? if >=1.21.10 {
    /*public static Item COPPER_GREATAXE = register("copper_greataxe", (p)->new GreataxeItem(QMPToolMaterial.COPPER, p), GreataxeItem.createSettings(QMPToolMaterial.COPPER));
    *///? }
    public static Item IRON_GREATAXE = register("iron_greataxe", (p)->new GreataxeItem(QMPToolMaterial.IRON, p), GreataxeItem.createSettings(QMPToolMaterial.IRON));
    public static Item GOLDEN_GREATAXE = register("golden_greataxe", (p)->new GreataxeItem(QMPToolMaterial.GOLD, p), GreataxeItem.createSettings(QMPToolMaterial.GOLD));
    public static Item DIAMOND_GREATAXE = register("diamond_greataxe", (p)->new GreataxeItem(QMPToolMaterial.DIAMOND, p), GreataxeItem.createSettings(QMPToolMaterial.DIAMOND));
    public static Item NETHERITE_GREATAXE = register("netherite_greataxe", (p)->new GreataxeItem(QMPToolMaterial.NETHERITE, p), GreataxeItem.createSettings(QMPToolMaterial.NETHERITE).fireResistant());

    // Warhammer
    public static Item WARHAMMER = register("warhammer", (p)->new WarhammerItem(QMPToolMaterial.NETHERITE, p), WarhammerItem.createSettings(QMPToolMaterial.NETHERITE).rarity(Rarity.RARE).fireResistant());

    public static Item WOODEN_WARHAMMER = register("wooden_warhammer", (p)->new WarhammerItem(QMPToolMaterial.WOOD, p), WarhammerItem.createSettings(QMPToolMaterial.WOOD));
    public static Item STONE_WARHAMMER = register("stone_warhammer", (p)->new WarhammerItem(QMPToolMaterial.STONE, p), WarhammerItem.createSettings(QMPToolMaterial.STONE));
    //? if >=1.21.10 {
    /*public static Item COPPER_WARHAMMER = register("copper_warhammer", (p)->new WarhammerItem(QMPToolMaterial.COPPER, p), WarhammerItem.createSettings(QMPToolMaterial.COPPER));
    *///? }
    public static Item IRON_WARHAMMER = register("iron_warhammer", (p)->new WarhammerItem(QMPToolMaterial.IRON, p), WarhammerItem.createSettings(QMPToolMaterial.IRON));
    public static Item GOLDEN_WARHAMMER = register("golden_warhammer", (p)->new WarhammerItem(QMPToolMaterial.GOLD, p), WarhammerItem.createSettings(QMPToolMaterial.GOLD));
    public static Item DIAMOND_WARHAMMER = register("diamond_warhammer", (p)->new WarhammerItem(QMPToolMaterial.DIAMOND, p), WarhammerItem.createSettings(QMPToolMaterial.DIAMOND));
    public static Item NETHERITE_WARHAMMER = register("netherite_warhammer", (p)->new WarhammerItem(QMPToolMaterial.NETHERITE, p), WarhammerItem.createSettings(QMPToolMaterial.NETHERITE).fireResistant());

    // Bamboo Toolset
    public static Item BAMBOO_WARHAMMER = register("bamboo_warhammer", (p)->new WarhammerItem(BAMBOO, p), WarhammerItem.createSettings(BAMBOO));
    public static Item BAMBOO_GREATAXE = register("bamboo_greataxe",  (p)->new GreataxeItem(BAMBOO, p), GreataxeItem.createSettings(BAMBOO));
    public static Item BAMBOO_ESTOC = register("bamboo_estoc",  (p)->new EstocItem(BAMBOO, p), EstocItem.createSettings(BAMBOO));
    public static Item BAMBOO_MORNINGSTAR = register("bamboo_morningstar", (p)->new MorningstarItem(BAMBOO, p), MorningstarItem.createSettings(BAMBOO));
    public static Item BAMBOO_CUTLASS = register("bamboo_cutlass", (p)->new CutlassItem(BAMBOO, p), CutlassItem.createSettings(BAMBOO));
    public static Item BAMBOO_SWORD = register("bamboo_sword",
            //? if >1.21.1
            //Item::new
            //? if <=1.21.1
            (p) -> new SwordItem(QMPToolMaterial.WOOD.toolMaterial, p)
            , new Item.Properties()
            //? if >1.21.1
            //.sword(BAMBOO, 0.01F, -2.4F)
    );
    public static Item BAMBOO_AXE = register("bamboo_axe", properties -> new AxeItem(BAMBOO.toolMaterial,
            //? if >1.21.1
            //0.01F, -3.2F,
            properties), new Item.Properties());
    public static Item BAMBOO_MACE = register("bamboo_mace",  (p)->new MaceItem(
            //? if >1.21.1
            //BAMBOO.toolMaterial,
            p), (new Item.Properties()).rarity(Rarity.EPIC).durability(500).component(DataComponents.TOOL, MaceItem.createToolProperties())
            //? if >1.21.1
            //.repairable(Items.BAMBOO)
            .attributes(createBambooMaceAttributes())
            //? if >1.21.1
            //.enchantable(15)
            //? if >1.21.1
            //.component(DataComponents.WEAPON, new Weapon(1))
            );
    //? if >=1.21.11 {
    /*public static Item BAMBOO_SPEAR = register(
            "bamboo_spear", Item::new, new Item.Properties().spear(BAMBOO, 0.65F, 0.01F, 0.75F, 5.0F, 14.0F, 10.0F, 5.1F, 15.0F, 0F)
    );*/
    //? }


    public static ItemAttributeModifiers createBambooMaceAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -3.4F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }


    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, QMIdentifier.of(name).id);

        // Create the item instance.
        T item = itemFactory.apply(settings
                //? if >=1.21.4 {
                /*.setId(itemKey)
                *///? }
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
                    creativeTab.accept(ModItems.PELLET);
                    creativeTab.accept(ModItems.AMMUNITION);
                });

        //? if >=26.1 {
        /*CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT)
        *///? } else {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
         //? }
                .register((creativeTab) -> {
                    creativeTab.accept(ModItems.FLINTLOCK);
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
                    creativeTab.addAfter(Items.NETHERITE_AXE, ModItems.BAMBOO_AXE);
                    creativeTab.addAfter(Items.NETHERITE_SWORD, ModItems.BAMBOO_SWORD);
                    //? if >=1.21.11
                    //creativeTab.addAfter(Items.NETHERITE_SPEAR, ModItems.BAMBOO_SPEAR);

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
