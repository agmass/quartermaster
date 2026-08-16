package org.agmas.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.agmas.Quartermaster;
import org.agmas.init.*;
import org.agmas.item.util.CustomHitSounds;

import static net.minecraft.world.item.ToolMaterial.*;

public class MorningstarItem extends Item {
    public MorningstarItem(Properties properties) {
        super(properties);
    }

    public static int BASE_DISABLE_TICKS = 15;

    public static ItemAttributeModifiers createAttributes(ToolMaterial material) {
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


    public static Properties createSettings(ToolMaterial material) {
        return new Properties()
                .stacksTo(1)
                .attributes(MorningstarItem.createAttributes(material))
                .component(ModComponents.FALL_DAMAGE_SHIELD_DISABLE_MULTIPLIER, material.attackDamageBonus())
                .enchantable(material.enchantmentValue())
                .durability(material.durability());
    }


}
