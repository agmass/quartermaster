package org.agmas.item;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.agmas.duck.PlayerAcessor;
import org.agmas.entity.GreataxeProjectileEntity;
import org.agmas.init.*;
import org.agmas.init.tag.ModTags;
import org.jspecify.annotations.Nullable;

import java.awt.*;

public class GreataxeItem extends Item {
    public GreataxeItem(Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers createAttributes(ToolMaterial material) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                6.5F + material.attackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                -3.3F,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    public static float modifyBlockDamage(LivingEntity livingEntity, ServerLevel serverLevel, DamageSource damageSource, float original) {
        if (damageSource.getWeaponItem() != null) {
            boolean busterEnchant = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(serverLevel, ModEnchants.BUSTER),damageSource.getWeaponItem()) > 0;
            if (damageSource.getWeaponItem().is(ModTags.GREATAXES) && !busterEnchant) {
                serverLevel.broadcastDamageEvent(livingEntity, damageSource); // make the player RED WITH RAGE!!!!!!!!!!!!!!!!!!
                return original * 0.75f;
            }
        }
        return original;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        int charges = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(serverLevel, ModEnchants.CHARGED),itemStack);
        if (charges > 0) {
            itemStack.set(ModComponents.MAX_CHARGES, charges+1);
            if (!itemStack.has(ModComponents.CHARGES)) itemStack.set(ModComponents.CHARGES, charges+1);
            if (entity instanceof Player player) {
                if (player.getCooldowns().getCooldownPercent(itemStack,1f) <= 0.0f && itemStack.get(ModComponents.CHARGES).intValue() < (charges+1)) {
                    itemStack.set(ModComponents.CHARGES, itemStack.get(ModComponents.CHARGES).intValue()+1);
                    if (itemStack.get(ModComponents.CHARGES) < charges+1)
                        player.getCooldowns().addCooldown(itemStack,20*8);
                }
            }
        } else {
            itemStack.remove(ModComponents.MAX_CHARGES);
            itemStack.remove(ModComponents.CHARGES);
        }
        super.inventoryTick(itemStack, serverLevel, entity, equipmentSlot);
    }

    boolean canBeUsed(Player player, ItemStack stack) {
        if ((stack.has(ModComponents.CHARGES) && stack.get(ModComponents.CHARGES).intValue() > 0)) {
            return true;
        }
        return !player.getCooldowns().isOnCooldown(stack);
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        if (itemStack.has(ModComponents.MAX_CHARGES)) {
            if (itemStack.get(ModComponents.MAX_CHARGES).intValue() != itemStack.get(ModComponents.CHARGES).intValue()) {
                return true;
            }
        }
        return super.isBarVisible(itemStack);
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        if (itemStack.has(ModComponents.MAX_CHARGES)) {
            if (itemStack.get(ModComponents.MAX_CHARGES).intValue() != itemStack.get(ModComponents.CHARGES).intValue()) {
                return Color.MAGENTA.getRGB();
            }
        }
        return super.getBarColor(itemStack);
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        if (itemStack.has(ModComponents.MAX_CHARGES)) {
            if (itemStack.get(ModComponents.MAX_CHARGES).intValue() != itemStack.get(ModComponents.CHARGES).intValue()) {
                return (int)(((float)itemStack.get(ModComponents.CHARGES).intValue()/itemStack.get(ModComponents.MAX_CHARGES).intValue())*13);
            }
        }
        return super.getBarWidth(itemStack);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (!level.isClientSide()) {
            if (canBeUsed(player,stack)) {
                GreataxeProjectileEntity buster = ModEntities.GREATAXE_PROJECTILE.create(level, EntitySpawnReason.TRIGGERED);
                buster.setOwner(player);
                buster.setColor(stack.get(ModComponents.BUSTER_COLOR).intValue());
                buster.attackPower = (float) (player.getAttribute(Attributes.ATTACK_DAMAGE).getValue());

                if (stack.has(ModComponents.CHARGES)) {
                    stack.set(ModComponents.CHARGES, stack.get(ModComponents.CHARGES).intValue()-1);
                    buster.attackPower *= 0.7f;
                }

                boolean busterEnchant = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level, ModEnchants.BUSTER),stack) > 0;
                if (busterEnchant) {
                    buster.attackPower *= 1.5f;
                    buster.setDeltaMovement(player.getViewVector(0f).multiply(1.25f,1.5f,1.25f).add(player.getDeltaMovement()));
                } else {
                    buster.setDeltaMovement(player.getViewVector(0f).multiply(2,1.5f,2).add(player.getDeltaMovement()));
                }
                buster.setPos(player.getEyePosition().add(0,-0.1f,0));
                level.addFreshEntity(buster);
                stack.hurtAndBreak(2,player,interactionHand);
                level.playSound(null,player.getX(),player.getY(),player.getZ(), ModSounds.GREATAXE_USE, player.getSoundSource());
                if (!player.isCreative()) player.getCooldowns().addCooldown(stack,20*8);
            }
        }
        if (canBeUsed(player,stack)) {
            player.swing(interactionHand,true);
            player.resetAttackStrengthTicker();
        }
        return super.use(level, player, interactionHand);
    }

    public static Properties createSettings(ToolMaterial material) {
        return new Properties()
                .stacksTo(1)
                .tool(material, BlockTags.MINEABLE_WITH_AXE, 0, 0, 5.0F)
                .attributes(GreataxeItem.createAttributes(material))
                .component(ModComponents.BUSTER_COLOR,
                        material == ToolMaterial.WOOD ? ModColors.WOOD :
                                material == ToolMaterial.DIAMOND ? ModColors.DIAMOND :
                                        material == ToolMaterial.IRON ? ModColors.IRON :
                                                material == ToolMaterial.GOLD ? ModColors.GOLD :
                                                        material == ToolMaterial.COPPER ? ModColors.COPPER :
                                                                material == ToolMaterial.STONE ? ModColors.STONE :
                                                                        ModColors.NETHERITE
                        )
                .enchantable(material.enchantmentValue())
                .component(DataComponents.WEAPON, new Weapon(1))
                .durability(material.durability());
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, Holder<Enchantment> enchantment, EnchantingContext context) {
        return super.canBeEnchantedWith(stack, enchantment, context) && !enchantment.is(ModTags.EXCLUSIVE_SET_SHIELD_DISABLE);
    }
}
