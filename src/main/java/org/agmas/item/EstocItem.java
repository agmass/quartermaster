package org.agmas.item;

import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Blocks;
import org.agmas.duck.PlayerAcessor;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModComponents;
import org.agmas.init.ModEffects;
import org.agmas.mixin.ChangeAttackStrengthTickerToEstocMixin;

import java.util.List;

public class EstocItem extends Item {
    public EstocItem(Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers createAttributes(ToolMaterial material) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                2.4F + material.attackDamageBonus(),
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
    public void hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            if (((PlayerAcessor)attacker).quartermaster$getEstocWoundChanceTicks() > 0) {
                ((PlayerAcessor)attacker).quartermaster$setEstocWoundChanceTicks(0);
                wound(livingEntity);
            }
        } else {
            wound(livingEntity);
        }
        super.hurtEnemy(itemStack, livingEntity, attacker);
    }

    public static Properties createSettings(ToolMaterial material) {
        return new Properties()
                .stacksTo(1)
                .sword(material,0f,0f)
                .attributes(EstocItem.createAttributes(material))
                .enchantable(material.enchantmentValue())
                .durability(material.durability());
    }

    public static void playerTick(Player player) {
        if (!player.hasAttached(ModAttachments.STORED_ESTOC_TICKS)) player.setAttached(ModAttachments.STORED_ESTOC_TICKS, 0);

        if (player.getAttached(ModAttachments.STORED_ESTOC_TICKS) < 200) {
            player.setAttached(ModAttachments.STORED_ESTOC_TICKS, player.getAttached(ModAttachments.STORED_ESTOC_TICKS)+1);
        }
    }


}
