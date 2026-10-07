package org.agmas.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.agmas.init.*;
import org.agmas.porting.QMPToolMaterial;


public class MorningstarItem extends EnchantableQMItem {
    public MorningstarItem(QMPToolMaterial toolMaterial, Properties properties) {
        super(toolMaterial, properties);
    }

    public static int BASE_DISABLE_TICKS = 15;


    public static ItemAttributeModifiers createAttributes(QMPToolMaterial material) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                (material == ModItems.BAMBOO  ? 0.01 : 5.5F) + material.attackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                -3.2F,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }


    public static Properties createSettings(QMPToolMaterial material) {
        return new Properties()
                .stacksTo(1)
                .attributes(MorningstarItem.createAttributes(material))
                .component(ModComponents.FALL_DAMAGE_SHIELD_DISABLE_MULTIPLIER, material.attackDamageBonus())
                //? if >1.21.1
                .enchantable(material.enchantability())
                //? if <=1.21.1
                //.component(DataComponents.TOOL, material.toolMaterial.createToolProperties(BlockTags.MINEABLE_WITH_AXE))
                .durability(material.durability());
    }


}
