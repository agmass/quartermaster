package org.agmas.item;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
//? if >1.21.1
//import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralBlock;
import org.agmas.porting.QMIdentifier;
import org.agmas.init.ModComponents;
import org.agmas.init.ModSounds;
import org.agmas.init.tag.ModTags;
import org.agmas.item.util.CustomHitSounds;
import org.agmas.porting.QMPToolMaterial;

import java.util.List;
import java.util.function.Consumer;

public class RapierItem extends Item implements CustomHitSounds {
    public RapierItem(Properties properties) {
        super(properties);
    }

    /**
     * @author Chemthunder
     */
    public static ItemAttributeModifiers createAttributes(QMPToolMaterial material) {
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
    //? if >1.21.1
    //public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
    //? if <=1.21.1
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> consumer, TooltipFlag tooltipFlag) {
        if (itemStack.get(ModComponents.IS_CORAL)) {

            //? if >1.21.1
            //consumer.accept
            //? if <=1.21.1
            consumer.add
                    (Component.translatable("item.quartermaster.rapier.coral").withStyle(ChatFormatting.GRAY));
        }
        //? if >1.21.1
        //super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
        //? if <=1.21.1
        super.appendHoverText(itemStack, tooltipContext, consumer, tooltipFlag);
    }


    @Override
    //? if >1.21.1
    //public InteractionResult use(Level level, Player user, InteractionHand interactionHand) {
    //? if <=1.21.1
    public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand interactionHand) {
        ItemStack stack = user.getItemInHand(interactionHand);

        user.startUsingItem(interactionHand);
        if (!user.isCreative()) {
            user.getCooldowns().addCooldown(stack
                    //? if <=1.21.1
                            .getItem()
                    , 20*6);
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
    //? if >1.21.1
    //public ItemUseAnimation getUseAnimation(ItemStack itemStack) { return ItemUseAnimation.BLOCK; }
    //? if <=1.21.1
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BLOCK;
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

    public static Properties createSettings(QMPToolMaterial material) {
        return new Properties()
                .component(ModComponents.IS_CORAL, false)
                .stacksTo(1)
                .attributes(RapierItem.createAttributes(material))
                //? if >1.21.1
                //.enchantable(material.enchantability())
                //? if <=1.21.1
                .component(DataComponents.TOOL, material.toolMaterial.createToolProperties(BlockTags.SWORD_EFFICIENT))
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
