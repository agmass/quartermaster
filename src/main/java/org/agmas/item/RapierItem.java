package org.agmas.item;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralBlock;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModComponents;
import org.agmas.init.ModGameRule;
import org.agmas.init.ModSounds;
import org.agmas.init.tag.ModTags;
import org.agmas.item.util.CustomHitSounds;

import java.util.function.Consumer;

public class RapierItem extends Item implements CustomHitSounds {
    public RapierItem(Properties properties) {
        super(properties);
    }

    /**
     * @author Chemthunder
     */
    public static ItemAttributeModifiers createAttributes(ToolMaterial material) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                2.0F + material.attackDamageBonus(),
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                -2.4F,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(
                                QMIdentifier.of("attack_knockback").id,
                                0.5F,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(
                                QMIdentifier.of("attack_reach").id,
                                0.25F,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        if (itemStack.get(ModComponents.IS_CORAL)) {
            consumer.accept(Component.translatable("item.quartermaster.rapier.coral").withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand interactionHand) {
        ItemStack stack = user.getItemInHand(interactionHand);

        user.startUsingItem(interactionHand);
        if (!user.isCreative()) {
            user.getCooldowns().addCooldown(stack, 20*6);
        }
        return super.use(level, user, interactionHand);
    }


    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            if (player.isCreative()) return Integer.MAX_VALUE;
        }
        return 5;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.BLOCK;
    }


    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (useOnContext.getLevel().getBlockState(useOnContext.getClickedPos()).is(Blocks.WATER_CAULDRON)) {
            useOnContext.getItemInHand().set(ModComponents.IS_CORAL, false);
            return InteractionResult.SUCCESS;
        }
        if (useOnContext.getLevel().getBlockState(useOnContext.getClickedPos()).getBlock() instanceof CoralBlock) {
            useOnContext.getItemInHand().set(ModComponents.IS_CORAL, true);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(useOnContext);
    }

    public static Properties createSettings(ToolMaterial material) {
        return new Properties()
                .component(ModComponents.IS_CORAL, false)
                .stacksTo(1)
                .attributes(RapierItem.createAttributes(material))
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

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, Holder<Enchantment> enchantment, EnchantingContext context) {
        return enchantment.is(ModTags.RAPIER_ENCHANTABLE);
    }
}
