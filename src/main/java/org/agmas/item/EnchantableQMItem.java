package org.agmas.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Blocks;
import org.agmas.porting.QMPToolMaterial;

import java.util.List;

public class EnchantableQMItem extends Item {
    QMPToolMaterial toolMaterial;

    public EnchantableQMItem(QMPToolMaterial toolMaterial, Item.Properties properties) {
        super(properties);
        this.toolMaterial = toolMaterial;
    }



    //? if <=1.21.1 {

    /*@Override
    public boolean isEnchantable(ItemStack itemStack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return toolMaterial.enchantability();
    }
    private static Tool createSwordToolProperties() {
        return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2);
    }

    *///? }
}
