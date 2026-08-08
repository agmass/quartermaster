package org.agmas.item;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralBlock;
import org.agmas.QMIdentifier;
import org.agmas.entity.GunpowderEntity;
import org.agmas.init.ModComponents;
import org.agmas.init.ModEntities;
import org.agmas.init.ModItems;
import org.agmas.init.ModSounds;
import org.agmas.init.tag.ModTags;
import org.agmas.item.util.CustomHitSounds;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class FlintlockItem extends CrossbowItem {
    public FlintlockItem(Properties properties) {
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (i)->i.is(ModItems.AMMUNITION);
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 15;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        livingEntity.stopUsingItem();
        return super.finishUsingItem(itemStack, level, livingEntity);
    }

    @Override
    protected Projectile createProjectile(Level level, LivingEntity livingEntity, ItemStack itemStack, ItemStack itemStack2, boolean bl) {
        GunpowderEntity gunpowderEntity = ModEntities.GUNPOWDER.create(level, EntitySpawnReason.TRIGGERED);
        gunpowderEntity.setPos(livingEntity.getPosition(0f).x,livingEntity.getEyePosition(0f).y,livingEntity.getPosition(0f).z);
        return gunpowderEntity;
    }
}
