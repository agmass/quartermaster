package org.agmas.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.agmas.duck.PlayerAcessor;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModEffects;
import org.agmas.init.ModItems;
import org.agmas.porting.QMPToolMaterial;

import java.util.List;

public class EstocItem extends EnchantableQMItem {
    public EstocItem(QMPToolMaterial toolMaterial, Item.Properties properties) {
        super(toolMaterial, properties);
    }
    public static ItemAttributeModifiers createAttributes(QMPToolMaterial material) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                (material == ModItems.BAMBOO  ? 0.01 : 2.4F) + material.attackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                -2.4F,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    public static void wound(LivingEntity livingEntity) {
        if (!livingEntity.hasEffect(ModEffects.WOUNDED)) {
            livingEntity.addEffect(new MobEffectInstance(ModEffects.WOUNDED, 20*10,0));
        } else {
            livingEntity.addEffect(new MobEffectInstance(ModEffects.WOUNDED, 20*5,livingEntity.getEffect(ModEffects.WOUNDED).getAmplifier()+1));
        }
    }

    @Override
    //? if >1.21.1
    public void hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity attacker) {
    //? if <=1.21.1
    //public boolean hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity attacker) {
        if (!livingEntity.isBlocking()) {
            if (attacker instanceof Player player) {
                if (((PlayerAcessor) attacker).quartermaster$getEstocWoundChanceTicks() > 0) {
                    ((PlayerAcessor) attacker).quartermaster$setEstocWoundChanceTicks(0);
                    wound(livingEntity);
                }
            } else {
                wound(livingEntity);
            }
        }
        //? if <=1.21.1
        //return
        super.hurtEnemy(itemStack, livingEntity, attacker);
    }

    public static Properties createSettings(QMPToolMaterial material) {
        return new Properties()
                .stacksTo(1)
                //? if >1.21.1
                .sword(material.toolMaterial,0f,0f)
                .attributes(EstocItem.createAttributes(material))
                //? if >1.21.1
                .enchantable(material.enchantability())
                //? if <=1.21.1
                //.component(DataComponents.TOOL, material.toolMaterial.createToolProperties(BlockTags.SWORD_EFFICIENT))
                .durability(material.durability());
    }

    public static void playerTick(Player player) {
        if (!player.hasAttached(ModAttachments.STORED_ESTOC_TICKS)) player.setAttached(ModAttachments.STORED_ESTOC_TICKS, 0);

        if (player.getAttached(ModAttachments.STORED_ESTOC_TICKS) < 200) {
            player.setAttached(ModAttachments.STORED_ESTOC_TICKS, player.getAttached(ModAttachments.STORED_ESTOC_TICKS)+1);
        }
    }


}
