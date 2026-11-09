package org.agmas.item;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import org.agmas.init.*;
import org.agmas.item.util.CustomHitSounds;
import org.agmas.porting.QMPToolMaterial;

import java.util.function.Consumer;

import static org.agmas.porting.QMPToolMaterial.*;


public class SicklesItem extends EnchantableQMItem implements CustomHitSounds {
    public SicklesItem(QMPToolMaterial toolMaterial, Properties properties) {
        super(toolMaterial, properties);
    }

    public static ItemAttributeModifiers createAttributes(QMPToolMaterial material) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                (material == ModItems.BAMBOO  ? 0.01 : 3F) + material.attackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                -1F,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }


    public static Properties createSettings(QMPToolMaterial material) {

        return new Properties()
                .stacksTo(1)
                //? if >1.21.1
                .sword(material.toolMaterial,0f,0f)
                .attributes(SicklesItem.createAttributes(material))
                //? if >1.21.1
                .enchantable(material.enchantability())
                //? if <=1.21.1
                //.component(DataComponents.TOOL, material.toolMaterial.createToolProperties(BlockTags.SWORD_EFFICIENT))
                .durability(material.durability());
    }

    public static float calculateFalloff(LivingEntity source, LivingEntity victim) {
        if (source.getPosition(1f).multiply(1,0,1).distanceTo(victim.getPosition(0f).multiply(1,0,1)) <= 1) {
            return 1;
        }
        return easeOutQuart((float)(1.0-(source.distanceTo(victim)/source.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).getValue())));
    }

    public static float easeOutQuart(float x) {
        return (float) (1 - Math.pow(1 - x, 4));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
        if (itemStack.getEntityRepresentation() != null) {
            if (itemStack.getEntityRepresentation() instanceof LivingEntity le) {
                if (le.getMainHandItem().is(le.getOffhandItem().getItem())) {
                    consumer.accept(Component.translatable("quartermaster.sickle_requires_both_hands").withStyle(ChatFormatting.RED));
                }
            }
        }
    }

    @Override
    public SoundEvent getSweepHitSound() {
        return ModSounds.CUTLASS_SWEEP;
    }

    @Override
    public SoundEvent getCritHitSound() {
        return ModSounds.CUTLASS_CRIT;
    }

    @Override
    public boolean playOriginalHitSounds(SoundEvent soundEvent) {
        return true;
    }

}
