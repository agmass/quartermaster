package org.agmas.init;

import com.mojang.datafixers.types.Func;
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
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.agmas.config.QMConfig;
import org.agmas.porting.QMIdentifier;
import org.agmas.init.tag.ModItemLists;
import org.agmas.init.tag.ModTags;
import org.agmas.item.*;
import org.agmas.porting.QMPToolMaterial;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ModItems {
    public static final QMPToolMaterial BAMBOO =
            //? if >1.21.1
            new QMPToolMaterial(new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 9999999, 0.0F, 0.0F, 15, ModTags.BAMBOO_REPAIR_MATERIALS));
            //? if <=1.21.1 {
            /*new QMPToolMaterial(new Tier() {
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
            *///? }


    public static Item RELIC_HANDLE = register("relic_handle", Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE));
    public static Item RUINED_HANDLE = register("ruined_handle", RapierHandleItem::new, new Item.Properties().stacksTo(1).rarity(Rarity.RARE));

    public static Item COMBAT_EFFECT_SMITHING_TEMPLATE = register("combat_effect_smithing_template", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));

    public static Item FLINTLOCK = register("flintlock", FlintlockItem::new, new Item.Properties().stacksTo(1)
            //? if >1.21.1
            .enchantable(9)
            .rarity(Rarity.UNCOMMON).durability(930));
    public static Item PELLET = register("pellet", Item::new, new Item.Properties());
    public static Item AMMUNITION = register("ammunition", Item::new, new Item.Properties());

    public static QMWeapon RAPIER;
    public static QMWeapon CUTLASS;
    public static QMWeapon MORNINGSTAR;
    public static QMWeapon ESTOC;
    public static QMWeapon GREATAXE;
    public static QMWeapon WARHAMMER;
    public static QMWeapon SICKLES;

    public static QMWeapon SWORD;

    public static QMWeapon AXE;

    public static QMWeapon SPEAR;

    // Bamboo Toolset
    public static Item BAMBOO_SWORD = register("bamboo_sword",
            //? if >1.21.1
            Item::new
            //? if <=1.21.1
            //(p) -> new SwordItem(QMPToolMaterial.WOOD.toolMaterial, p)
            , new Item.Properties()
            //? if >1.21.1
            .sword(BAMBOO.toolMaterial, 0.01F, -2.4F)
    );
    public static Item BAMBOO_AXE = register("bamboo_axe",
            //? if <26.3 {
            properties -> new AxeItem(BAMBOO.toolMaterial,
            //? if >1.21.1
            0.01F, -3.2F,
            properties),
            //? } else {
            /*Item::new,
            *///? }
             new Item.Properties()
            //? if >=26.3
                     //.axe(BAMBOO.toolMaterial, 0.01F, -3.2F)
            );
    public static Item BAMBOO_MACE = register("bamboo_mace",  MaceItem::new, (new Item.Properties()).rarity(Rarity.EPIC).durability(500).component(DataComponents.TOOL, MaceItem.createToolProperties())
            //? if >1.21.1
            .repairable(Items.BAMBOO)
            .attributes(createBambooMaceAttributes())
            //? if >1.21.1
            .enchantable(15)
            //? if >1.21.1
            .component(DataComponents.WEAPON, new Weapon(1))
            );
    public static Item RELIC_MACE = register("relic_mace",  MaceItem::new, (new Item.Properties()).rarity(Rarity.EPIC).durability(500).component(DataComponents.TOOL, MaceItem.createToolProperties())
            //? if >1.21.1
            .repairable(Items.BREEZE_ROD)
            .attributes(MaceItem.createAttributes())
            //? if >1.21.1
            .enchantable(15)
            //? if >1.21.1
            .component(DataComponents.WEAPON, new Weapon(1))
    );
    //? if >=1.21.11 {
    public static Item BAMBOO_SPEAR = register(
            "bamboo_spear", Item::new, new Item.Properties().spear(BAMBOO.toolMaterial, 0.65F, 0.01F, 0.75F, 5.0F, 14.0F, 10.0F, 5.1F, 15.0F, 0F)
    );
    //? }


    public static void initItems() {
        RAPIER = generateItems(WeaponClass.LIGHT, "rapier", RapierItem.class, RapierItem::createSettings);
        CUTLASS = generateItems(WeaponClass.LIGHT,"cutlass", CutlassItem.class, CutlassItem::createSettings);
        ESTOC = generateItems(WeaponClass.LIGHT,"estoc", EstocItem.class, EstocItem::createSettings);
        SICKLES = generateItems(WeaponClass.LIGHT,"sickle", SicklesItem.class, SicklesItem::createSettings);
        MORNINGSTAR = generateItems(WeaponClass.HEAVY,"morningstar", MorningstarItem.class, MorningstarItem::createSettings);
        GREATAXE = generateItems(WeaponClass.HEAVY,"greataxe", GreataxeItem.class, GreataxeItem::createSettings);
        WARHAMMER = generateItems(WeaponClass.HEAVY,"warhammer", WarhammerItem.class, WarhammerItem::createSettings);

        SPEAR = new QMWeapon(WeaponClass.HEAVY,
                null,
                ModItems.BAMBOO_SPEAR,
                Items.WOODEN_SPEAR,
                Items.STONE_SPEAR,
                Items.COPPER_SPEAR,
                Items.IRON_SPEAR,
                Items.GOLDEN_SPEAR,
                Items.DIAMOND_SPEAR,
                Items.NETHERITE_SPEAR);

        AXE = new QMWeapon(WeaponClass.HEAVY,
                null,
                ModItems.BAMBOO_AXE,
                Items.WOODEN_AXE,
                Items.STONE_AXE,
                Items.COPPER_AXE,
                Items.IRON_AXE,
                Items.GOLDEN_AXE,
                Items.DIAMOND_AXE,
                Items.NETHERITE_AXE);

        SWORD = new QMWeapon(WeaponClass.LIGHT,
                null,
                ModItems.BAMBOO_SWORD,
                Items.WOODEN_SWORD,
                Items.STONE_SWORD,
                Items.COPPER_SWORD,
                Items.IRON_SWORD,
                Items.GOLDEN_SWORD,
                Items.DIAMOND_SWORD,
                Items.NETHERITE_SWORD);

        ModItemLists.weapons.add(SWORD);
        ModItemLists.weapons.add(AXE);
        ModItemLists.weapons.add(SPEAR);

        ModItemLists.initLists();
    }

    public static <T extends Item> EnchantableQMItem generateConstructor(Item.Properties properties, Class<? extends EnchantableQMItem> itemClass, QMPToolMaterial toolMaterial) {
        try {
            return itemClass.getConstructor(QMPToolMaterial.class, Item.Properties.class).newInstance(toolMaterial,properties);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static QMWeapon generateItems(WeaponClass weaponClass, String name, Class<? extends EnchantableQMItem> c, Function<QMPToolMaterial, Item.Properties> propertiesFunction) {
        Item relic = null;
        if (QMConfig.enabledWeapons.get(name).relic()) {
            relic = register(name, (p)->generateConstructor(p,c,QMPToolMaterial.NETHERITE), propertiesFunction.apply(QMPToolMaterial.NETHERITE).rarity(Rarity.RARE).fireResistant());
        }
        Item bamboo = null;
        if (QMConfig.enabledWeapons.get(name).bamboo()) {
            bamboo = register("bamboo_"+name, (p)->generateConstructor(p,c,BAMBOO), propertiesFunction.apply(BAMBOO));
        }

        QMWeapon w;
        if (QMConfig.enabledWeapons.get(name).tiered()) {
            w = new QMWeapon(weaponClass, relic, bamboo,
                    register("wooden_"+name, (p)->generateConstructor(p,c,QMPToolMaterial.WOOD), propertiesFunction.apply(QMPToolMaterial.WOOD)),
                    register("stone_"+name, (p)->generateConstructor(p,c,QMPToolMaterial.STONE), propertiesFunction.apply(QMPToolMaterial.STONE)),
                    //? >=1.21.10
                    register("copper_"+name, (p)->generateConstructor(p,c,QMPToolMaterial.COPPER), propertiesFunction.apply(QMPToolMaterial.COPPER)),
                    register("iron_"+name, (p)->generateConstructor(p,c,QMPToolMaterial.IRON), propertiesFunction.apply(QMPToolMaterial.IRON)),
                    register("golden_"+name, (p)->generateConstructor(p,c,QMPToolMaterial.GOLD), propertiesFunction.apply(QMPToolMaterial.GOLD)),
                    register("diamond_"+name, (p)->generateConstructor(p,c,QMPToolMaterial.DIAMOND), propertiesFunction.apply(QMPToolMaterial.DIAMOND)),
                    register("netherite_"+name, (p)->generateConstructor(p,c,QMPToolMaterial.NETHERITE), propertiesFunction.apply(QMPToolMaterial.NETHERITE)));
        } else {
            w = new QMWeapon(weaponClass, relic, bamboo, null, null, null, null, null, null, null);
        }
        ModItemLists.weapons.add(w);
        return w;
    }

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
                .setId(itemKey)
                //? }
        );

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
    public static void init() {
        initItems();
        ServerLivingEntityEvents.AFTER_DAMAGE.register(CutlassItem::onHit);
        ServerLivingEntityEvents.AFTER_DAMAGE.register(EstocItem::onHit);
        ServerLivingEntityEvents.AFTER_DAMAGE.register(RapierItem::onHit);

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

                    /*// Heavies
                    creativeTab.insertAfter(Items.NETHERITE_AXE, GREATAXE.asList().toArray(new Item[0]));
                    creativeTab.insertAfter(Items.NETHERITE_AXE, WARHAMMER.asList().toArray(new Item[0]));
                    creativeTab.insertAfter(Items.NETHERITE_AXE, MORNINGSTAR.asList().toArray(new Item[0]));

                    // Swords
                    creativeTab.insertAfter(Items.NETHERITE_SWORD, ESTOC.asList().toArray(new Item[0]));
                    creativeTab.insertAfter(Items.NETHERITE_SWORD, RAPIER.asList().toArray(new Item[0]));
                    creativeTab.insertAfter(Items.NETHERITE_SWORD, SICKLES.asList().toArray(new Item[0]));
                    creativeTab.insertAfter(Items.NETHERITE_SWORD, CUTLASS.asList().toArray(new Item[0]));

                    *///? } else {
                    
                    creativeTab.addAfter(Items.NETHERITE_AXE, ModItems.BAMBOO_AXE);
                    creativeTab.addAfter(Items.NETHERITE_SWORD, ModItems.BAMBOO_SWORD);
                    //? if >=1.21.11
                    creativeTab.addAfter(Items.NETHERITE_SPEAR, ModItems.BAMBOO_SPEAR);

                    // Heavies
                    creativeTab.addAfter(Items.NETHERITE_AXE, GREATAXE.asList().toArray(new Item[0]));
                    creativeTab.addAfter(Items.NETHERITE_AXE, WARHAMMER.asList().toArray(new Item[0]));
                    creativeTab.addAfter(Items.NETHERITE_AXE, MORNINGSTAR.asList().toArray(new Item[0]));

                    // Swords
                    creativeTab.addAfter(Items.NETHERITE_SWORD, ESTOC.asList().toArray(new Item[0]));
                    creativeTab.addAfter(Items.NETHERITE_SWORD, SICKLES.asList().toArray(new Item[0]));
                    creativeTab.addAfter(Items.NETHERITE_SWORD, RAPIER.asList().toArray(new Item[0]));
                    creativeTab.addAfter(Items.NETHERITE_SWORD, CUTLASS.asList().toArray(new Item[0]));
                    //? }
                });
    }

    public enum WeaponClass {
        LIGHT,
        HEAVY,
        RANGED // will probabbly go unused for a while
    }

    public record QMWeapon(WeaponClass weaponClass, Item relic, Item bamboo, Item wooden, Item stone,
                         //? if >=1.21.10
                         Item copper,
                         Item iron, Item golden, Item diamond, Item netherite) {
        public List<Item> asList() {
            ArrayList<Item> items = new ArrayList<>();
            if (relic != null) items.add(relic);
            if (bamboo != null) items.add(bamboo);
            if (wooden != null) {
                items.add(wooden);
                items.add(stone);
                //? >=1.21.10
                items.add(copper);
                items.add(iron);
                items.add(golden);
                items.add(diamond);
                items.add(netherite);
            }
            return items;
        }

    }
}
