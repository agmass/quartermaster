package org.agmas.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.agmas.Quartermaster;
import org.agmas.init.*;
import org.agmas.item.util.CustomHitSounds;
import org.agmas.porting.QMPToolMaterial;


public class MorningstarItem extends EnchantableQMItem implements CustomHitSounds {
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

    @Override
    public float getAttackDamageBonus(Entity entity, float f, DamageSource damageSource) {
        if (damageSource.getDirectEntity() instanceof LivingEntity livingEntity) {
            if (entity instanceof LivingEntity victim) {
                if (livingEntity.fallDistance > 2) {
                    victim.addEffect(new MobEffectInstance(ModEffects.STUNNED, Quartermaster.STUNNED_TICKS), livingEntity);
                }
            }
        }
        return super.getAttackDamageBonus(entity, f, damageSource);
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

    @Override
    public SoundEvent getCritHitSound() {
        return ModSounds.HEAVY_WEAPON_CRIT;
    }

    @Override
    public boolean playOriginalHitSounds(SoundEvent soundEvent) {
        return true;
    }
}
