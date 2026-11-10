package org.agmas.client.datagen;

//? if >=26.1 {
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
//? } else {
/*import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
 *///? }

//? if >=1.21.4 {
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
//? } else {
/*import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.*;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
*///? }
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import org.agmas.porting.QMIdentifier;
import org.agmas.init.ModItems;

import java.util.Arrays;
import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {

    //? if >=26.1 {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }
    //? } else {
    /*public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    *///? }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        //createRapier(itemModelGenerator);

        itemModelGenerator.generateFlatItem(ModItems.RELIC_HANDLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RUINED_HANDLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COMBAT_EFFECT_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PELLET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.AMMUNITION, ModelTemplates.FLAT_ITEM);

        ModelTemplate greataxeTemplate = new ModelTemplate(Optional.of(QMIdentifier.of("item/greataxe_template").id), Optional.empty(), TextureSlot.LAYER0);
        ModelTemplate cutlassTemplate = new ModelTemplate(Optional.of(QMIdentifier.of("item/cutlass_template").id), Optional.empty(), TextureSlot.LAYER0);

        createWeapon32(itemModelGenerator, ModItems.WARHAMMER, greataxeTemplate);
        createWeapon32(itemModelGenerator, ModItems.MORNINGSTAR, greataxeTemplate);
        createWeapon32(itemModelGenerator, ModItems.RAPIER, greataxeTemplate);
        createWeapon32(itemModelGenerator, ModItems.GREATAXE, greataxeTemplate);
        createWeapon32(itemModelGenerator, ModItems.CUTLASS, cutlassTemplate);
        createWeapon32(itemModelGenerator, ModItems.ESTOC, cutlassTemplate);
        createWeapon(itemModelGenerator, ModItems.SICKLES, ModelTemplates.FLAT_HANDHELD_ITEM);

        // Bamboo

        itemModelGenerator.generateFlatItem(ModItems.BAMBOO_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BAMBOO_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BAMBOO_MACE, ModelTemplates.FLAT_HANDHELD_MACE_ITEM);

        create32XSpear(itemModelGenerator, ModItems.BAMBOO_SPEAR);


    }

    public void createWeapon(ItemModelGenerators itemModelGenerator, ModItems.QMWeapon weapon, ModelTemplate template) {
        itemModelGenerator.generateFlatItem(weapon.relic(), template);
        itemModelGenerator.generateFlatItem(weapon.bamboo(), template);
        itemModelGenerator.generateFlatItem(weapon.wooden(), template);
        itemModelGenerator.generateFlatItem(weapon.stone(), template);
        //? >=1.21.10
        itemModelGenerator.generateFlatItem(weapon.copper(), template);
        itemModelGenerator.generateFlatItem(weapon.iron(), template);
        itemModelGenerator.generateFlatItem(weapon.golden(), template);
        itemModelGenerator.generateFlatItem(weapon.diamond(), template);
        itemModelGenerator.generateFlatItem(weapon.netherite(), template);
    }
    public void createWeapon32(ItemModelGenerators itemModelGenerator, ModItems.QMWeapon weapon, ModelTemplate template) {
        create32X(itemModelGenerator, weapon.relic(), template);
        create32X(itemModelGenerator, weapon.bamboo(), template);
        create32X(itemModelGenerator, weapon.wooden(), template);
        create32X(itemModelGenerator, weapon.stone(), template);
        //? >=1.21.10
        create32X(itemModelGenerator, weapon.copper(), template);
        create32X(itemModelGenerator, weapon.iron(), template);
        create32X(itemModelGenerator, weapon.golden(), template);
        create32X(itemModelGenerator, weapon.diamond(), template);
        create32X(itemModelGenerator, weapon.netherite(), template);
    }

    /// Credit: Originally from AcornLib by AcoYT, ported to Mojmaps, added the ability to use templates
    public void create32X(ItemModelGenerators itemModelGenerator, Item item, ModelTemplate template) {
        Identifier world_id = itemModelGenerator.createFlatItemModel(item, template);
        Identifier gui_id = template.create(
                world_id.withPath(world_id.getPath()+"_16"),
                TextureMapping.layer0(TextureMapping.getItemTexture(item, "_16")),
                itemModelGenerator.modelOutput
        );

        itemModelGenerator.itemModelOutput.accept(item,
            ItemModelUtils.select(
                new DisplayContext(),
                ItemModelUtils.plainModel(world_id),
                ItemModelUtils.when(
                        Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED),
                        ItemModelUtils.plainModel(gui_id)
                )
            )
        );
    }
    public void create32XSpear(ItemModelGenerators itemModelGenerator, Item item) {
        Identifier world_id = itemModelGenerator.createFlatItemModel(item, ModelTemplates.SPEAR_IN_HAND);
        Identifier gui_id = ModelTemplates.FLAT_HANDHELD_ITEM.create(
                world_id.withPath(world_id.getPath()+"_16"),
                TextureMapping.layer0(TextureMapping.getItemTexture(item, "_16")),
                itemModelGenerator.modelOutput
        );

        itemModelGenerator.itemModelOutput.accept(item,
                ItemModelUtils.select(
                        new DisplayContext(),
                        ItemModelUtils.plainModel(world_id),
                        ItemModelUtils.when(
                                Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED),
                                ItemModelUtils.plainModel(gui_id)
                        )
                )
        );
    }

}
