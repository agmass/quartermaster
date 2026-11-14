package org.agmas.item;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
//? if <=1.21.1
//import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
//? if >1.21.1
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralBlock;
import org.agmas.init.*;
import org.agmas.porting.QMIdentifier;
import org.agmas.init.tag.ModTags;
import org.agmas.item.util.CustomHitSounds;
import org.agmas.porting.QMPToolMaterial;

import java.util.List;
import java.util.function.Consumer;

public class RapierItem extends EnchantableQMItem implements CustomHitSounds {

    public RapierItem(QMPToolMaterial toolMaterial, Properties properties) {
        super(toolMaterial, properties);
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

    public static float MAX_DAMAGE = 7.0f;

    public float getParryMultiplier() {
        if (getAttackDamage() / MAX_DAMAGE >= 1) { // netherite tier is stronger
            return (float) Mth.lerp(getAttackDamage() / MAX_DAMAGE, 0f, 2.5f);
        }
        return (float) Mth.lerp(getAttackDamage() / MAX_DAMAGE, 0f, 1.5f);
    }

    @Override
    //? if >1.21.1
    public InteractionResult use(Level level, Player user, InteractionHand interactionHand) {
        //? if <=1.21.1
        //public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand interactionHand) {
        ItemStack stack = user.getItemInHand(interactionHand);

        user.startUsingItem(interactionHand);
        if (!user.isCreative()) {
            user.getCooldowns().addCooldown(stack
                    //? if <=1.21.1
                    //.getItem()
                    , 20 * 6);
            for (Item item : ModItems.RAPIER.asList()) {
                user.getCooldowns().addCooldown(item.getDefaultInstance()
                        //? if <=1.21.1
                        //.getItem()
                        , 20 * 6);
            }
        }
        return super.use(level, user, interactionHand);
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            if (player.isCreative()) return Integer.MAX_VALUE;
        }
        return Math.max((int) Mth.lerp(getAttackDamage() / MAX_DAMAGE, 120, 5), 5);
    }

    public double getAttackDamage() {
        return components().get(DataComponents.ATTRIBUTE_MODIFIERS).compute(Attributes.ATTACK_DAMAGE, 1f, EquipmentSlot.MAINHAND);
    }

    @Override
    //? if >1.21.1 {
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.BLOCK;
    }
    //? } else if <=1.21.1 {
    /*public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BLOCK;
    }
     *///? }

    public static Properties createSettings(QMPToolMaterial material) {
        return new Properties()
                .stacksTo(1)
                //? if >1.21.1
                .sword(material.toolMaterial, 0f, 0f)
                .attributes(RapierItem.createAttributes(material))
                //? if >1.21.1
                .enchantable(material.enchantability())
                //? if <=1.21.1
                //.component(DataComponents.TOOL, material.toolMaterial.createToolProperties(BlockTags.SWORD_EFFICIENT))
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
    public SoundEvent getSprintHitSound() {
        return ModSounds.CUTLASS_SPRINT_HIT;
    }

    @Override
    public boolean playOriginalHitSounds(SoundEvent soundEvent) {
        return true;
    }

    public static void onHit(LivingEntity livingEntity, DamageSource damageSource, float baseAttackDamage, float attackDamage, boolean blocked) {
        if (livingEntity instanceof Mob m && damageSource.getEntity() instanceof LivingEntity victim) {
            if ((m.getTarget() == null || m.getTarget().equals(victim)) && m.getMainHandItem().getItem() instanceof RapierItem r) {
                if (m.getRandom().nextInt(0,4) < 1) {
                    r.parry(m,victim,damageSource,attackDamage);
                }
            }
        }
    }
    public float parry(LivingEntity parrier, LivingEntity parried, DamageSource source, float amount) {
        parrier.level().playSound(null,parrier.position().x,parrier.position().y,parrier.position().z, ModSounds.PARRY, SoundSource.PLAYERS);

        if (parrier.getUseItem().is(ModTags.NON_DAMAGING)) {
            return amount;
        } else {
            if (ModEnchants.getItemEnchant(parrier.getUseItem(), parrier.level(), ModEnchants.DEADLOCK) > 0) {
                double stunAmount = amount * getParryMultiplier();
                stunAmount *= 1.5;
                parried.addEffect(new MobEffectInstance(ModEffects.DISARMED, (int) Math.round(stunAmount / 2), 0));
                parried.addEffect(new MobEffectInstance(ModEffects.STUNNED, (int) Math.round(stunAmount), 0));
                return amount;
            }

            //? if >=1.21.11
            parried.hurtServer((ServerLevel) parried.level(), source, amount * getParryMultiplier());
            //? if <1.21.11
            //target.hurt(source,amount*2);
            return amount / (getParryMultiplier() * 2);
        }
    }
}
