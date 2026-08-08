package org.agmas.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
//? if >=26.1 {
/*import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
*///? } else {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
 //? }
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModels;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;
import org.agmas.client.item.CoralRapierBooleanProperty;
import org.agmas.init.ModItems;

import java.util.Arrays;
import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {

    //? if >=26.1 {
    /*public ModModelProvider(FabricPackOutput output) {
        super(output);
    }
    *///? } else {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    //? }

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

        ModelTemplate rapierTemplate = new ModelTemplate(Optional.of(QMIdentifier.of("item/rapier_template").id), Optional.empty(), TextureSlot.LAYER0);
        createRapier(itemModelGenerator, ModItems.RAPIER, rapierTemplate);

        ModelTemplate greataxeTemplate = new ModelTemplate(Optional.of(QMIdentifier.of("item/greataxe_template").id), Optional.empty(), TextureSlot.LAYER0);
        ModelTemplate cutlassTemplate = new ModelTemplate(Optional.of(QMIdentifier.of("item/cutlass_template").id), Optional.empty(), TextureSlot.LAYER0);
        create32X(itemModelGenerator, ModItems.CUTLASS, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.WOODEN_CUTLASS, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.STONE_CUTLASS, cutlassTemplate);
        //? if >=1.21.10 {
        create32X(itemModelGenerator, ModItems.COPPER_CUTLASS, cutlassTemplate);
        //? }
        create32X(itemModelGenerator, ModItems.IRON_CUTLASS, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.GOLDEN_CUTLASS, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.DIAMOND_CUTLASS, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.NETHERITE_CUTLASS, cutlassTemplate);

        create32X(itemModelGenerator, ModItems.MORNINGSTAR, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.WOODEN_MORNINGSTAR, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.STONE_MORNINGSTAR, greataxeTemplate);
        //? if >=1.21.10 {
        create32X(itemModelGenerator, ModItems.COPPER_MORNINGSTAR, cutlassTemplate);
        //? }
        create32X(itemModelGenerator, ModItems.IRON_MORNINGSTAR, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.GOLDEN_MORNINGSTAR, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.DIAMOND_MORNINGSTAR, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.NETHERITE_MORNINGSTAR, greataxeTemplate);

        create32X(itemModelGenerator, ModItems.ESTOC, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.WOODEN_ESTOC, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.STONE_ESTOC, cutlassTemplate);
        //? if >=1.21.10 {
        create32X(itemModelGenerator, ModItems.COPPER_ESTOC, cutlassTemplate);
        //? }
        create32X(itemModelGenerator, ModItems.IRON_ESTOC, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.GOLDEN_ESTOC, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.DIAMOND_ESTOC, cutlassTemplate);
        create32X(itemModelGenerator, ModItems.NETHERITE_ESTOC, cutlassTemplate);

        create32X(itemModelGenerator, ModItems.GREATAXE, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.WOODEN_GREATAXE, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.STONE_GREATAXE, greataxeTemplate);
        //? if >=1.21.10 {
        create32X(itemModelGenerator, ModItems.COPPER_GREATAXE, greataxeTemplate);
        //? }
        create32X(itemModelGenerator, ModItems.IRON_GREATAXE, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.GOLDEN_GREATAXE, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.DIAMOND_GREATAXE, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.NETHERITE_GREATAXE, greataxeTemplate);

        create32X(itemModelGenerator, ModItems.WARHAMMER, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.WOODEN_WARHAMMER, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.STONE_WARHAMMER, greataxeTemplate);
        //? if >=1.21.10 {
        create32X(itemModelGenerator, ModItems.COPPER_WARHAMMER, greataxeTemplate);
        //? }
        create32X(itemModelGenerator, ModItems.IRON_WARHAMMER, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.GOLDEN_WARHAMMER, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.DIAMOND_WARHAMMER, greataxeTemplate);
        create32X(itemModelGenerator, ModItems.NETHERITE_WARHAMMER, greataxeTemplate);


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

    public void createRapier(ItemModelGenerators itemModelGenerator, Item item, ModelTemplate template) {
        Identifier world_id = itemModelGenerator.createFlatItemModel(item, template);
        Identifier gui_id = template.create(
                world_id.withPath(world_id.getPath()+"_16"),
                TextureMapping.layer0(TextureMapping.getItemTexture(item, "_16")),
                itemModelGenerator.modelOutput
        );

        Identifier coral_world_id = template.create(
                world_id.withPath(world_id.getPath()+"coral"),
                TextureMapping.layer0(TextureMapping.getItemTexture(item, "_coral")),
                itemModelGenerator.modelOutput
        );
        Identifier coral_gui_id = template.create(
                world_id.withPath(world_id.getPath()+"coral_16"),
                TextureMapping.layer0(TextureMapping.getItemTexture(item, "_coral_16")),
                itemModelGenerator.modelOutput
        );

        itemModelGenerator.itemModelOutput.accept(item,
                ItemModelUtils.conditional(
                        new CoralRapierBooleanProperty(),
                        ItemModelUtils.select(
                            new DisplayContext(),
                            ItemModelUtils.plainModel(coral_world_id),
                            ItemModelUtils.when(
                                    Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED),
                                    ItemModelUtils.plainModel(coral_gui_id)
                            )
                        ),
                        ItemModelUtils.select(
                                new DisplayContext(),
                                ItemModelUtils.plainModel(world_id),
                                ItemModelUtils.when(
                                        Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED),
                                        ItemModelUtils.plainModel(gui_id)
                                )
                        )
                    )
        );
    }
}
