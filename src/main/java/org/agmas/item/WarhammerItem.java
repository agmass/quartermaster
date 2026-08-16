package org.agmas.item;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
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
import org.agmas.entity.GreataxeProjectileEntity;
import org.agmas.init.*;
import org.agmas.init.tag.ModTags;
import org.jspecify.annotations.Nullable;

public class WarhammerItem extends Item {
    public WarhammerItem(Properties properties) {
        super(properties);
    }

    public static ItemAttributeModifiers createAttributes(ToolMaterial material) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                (material == ModItems.BAMBOO  ? 0.01 : 6.5F) + material.attackDamageBonus(),
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
                .build() ;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        if (entity instanceof Player player) {
            if (player.hasAttached(ModAttachments.WARHAMMER_CAST_TIME) && !player.isCreative()) {
                boolean siesmic = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(serverLevel, ModEnchants.SIESMIC),itemStack) > 0;
                player.getCooldowns().addCooldown(itemStack, siesmic ? 20 * 12 : 20 * 6);
            }
        }
        super.inventoryTick(itemStack, serverLevel, entity, equipmentSlot);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        if (!level.isClientSide()) {
            if(!player.getCooldowns().isOnCooldown(stack)) {
                boolean siesmic = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder((ServerLevel) level, ModEnchants.SIESMIC),stack) > 0;
                player.setAttached(ModAttachments.WARHAMMER_CAST_TIME, siesmic ? 80 : 40);
            }
        }
        return super.use(level, player, interactionHand);
    }

    public static Properties createSettings(ToolMaterial material) {
        float range = 1f;
        if (material == ToolMaterial.STONE || material ==  ToolMaterial.COPPER) range = 1.5f;
        if (material == ToolMaterial.IRON) range = 2f;
        if (material == ToolMaterial.DIAMOND) range = 2.5f;
        if (material == ToolMaterial.NETHERITE) range = 3.5f;
        return new Properties()
                .stacksTo(1)
                .tool(material, BlockTags.MINEABLE_WITH_AXE, 0, 0, 5.0F)
                .attributes(WarhammerItem.createAttributes(material))
                .enchantable(material.enchantmentValue())
                .component(ModComponents.WARHAMMER_RANGE, range)
                .component(DataComponents.WEAPON, new Weapon(1))
                .durability(material.durability());
    }


    @Override
    public boolean canBeEnchantedWith(ItemStack stack, Holder<Enchantment> enchantment, EnchantingContext context) {
        return super.canBeEnchantedWith(stack, enchantment, context) && !enchantment.is(ModTags.EXCLUSIVE_SET_SHIELD_DISABLE);
    }
}
