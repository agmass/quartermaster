package org.agmas.item;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import org.agmas.Quartermaster;
import org.agmas.init.*;
import org.agmas.item.util.CustomHitSounds;

import java.util.List;

import static net.minecraft.world.item.ToolMaterial.*;

public class CutlassItem extends net.minecraft.world.item.Item implements CustomHitSounds {
    public CutlassItem(Properties properties) {
        super(properties);
    }

    /**
     * @author Chemthunder
     */
    public static ItemAttributeModifiers createAttributes(ToolMaterial material) {
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
                                -2.3F,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }


    public static Item.Properties createSettings(ToolMaterial material) {
        int comboToDisable = 7; // fallback to wood

        //? if >=1.21.10 {
        if (material == COPPER || material == STONE)  comboToDisable = 6;
        //? } else {
        /*if (material == STONE)  comboToDisable = 6;
        *///? }
        if (material == IRON || material == GOLD)  comboToDisable = 5;
        if (material == DIAMOND)  comboToDisable = 4;
        if (material == NETHERITE)  comboToDisable = 3;

        return new Item.Properties()
                .component(ModComponents.COMBO_TO_DISABLE, comboToDisable)
                .stacksTo(1)
                .sword(material,0f,0f)
                .attributes(CutlassItem.createAttributes(material))
                .enchantable(material.enchantmentValue())
                .durability(material.durability());
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

    public static void onHit(LivingEntity livingEntity, DamageSource damageSource, float baseAttackDamage, float attackDamage, boolean blocked) {
        if (blocked) return;
        if (baseAttackDamage < 3) return;

        livingEntity.removeAttached(ModAttachments.CUTLASS_COMBO);
        if (livingEntity.hasEffect(ModEffects.DISARMED)) return;

        if (damageSource.getDirectEntity() != null) {
            if (damageSource.getDirectEntity() instanceof LivingEntity attacker) {
                if (!(attacker.getMainHandItem().getItem() instanceof CutlassItem)) return;

                if (attacker.hasAttached(ModAttachments.CUTLASS_COMBO)) {
                    attacker.setAttached(ModAttachments.CUTLASS_COMBO, attacker.getAttached(ModAttachments.CUTLASS_COMBO) + 1);
                    if (attacker.getAttached(ModAttachments.CUTLASS_COMBO) >= attacker.getMainHandItem().get(ModComponents.COMBO_TO_DISABLE)) {
                        attacker.removeAttached(ModAttachments.CUTLASS_COMBO);
                        boolean fling = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(livingEntity.level(), ModEnchants.FLING),attacker.getMainHandItem()) > 0;
                        if (fling) {
                            if (livingEntity instanceof Player player) {
                                int freeSlot =player.getInventory().getFreeSlot();
                                if (freeSlot != -1) {
                                    ItemStack stack = player.getMainHandItem().copy();
                                    player.getItemInHand(InteractionHand.MAIN_HAND).shrink(100);
                                    player.getInventory().setItem(freeSlot,stack);
                                }
                            } else {
                                livingEntity.drop(livingEntity.getMainHandItem(),false,false);
                                livingEntity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }
                        disarm(livingEntity,(fling ? 2 : 1));
                    }
                } else {
                    attacker.setAttached(ModAttachments.CUTLASS_COMBO, 1);
                }
            }
        }
    }

    public static void disarm(LivingEntity livingEntity, int division) {
        livingEntity.addEffect(new MobEffectInstance(ModEffects.DISARMED, Quartermaster.DISARMED_TICKS / division, 0));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.SPEED, Quartermaster.DISARMED_TICKS / division, 0));
    }
}
