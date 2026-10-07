package org.agmas.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
//? if <=1.21.1
//import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
//? if >1.21.1 {
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.item.component.TooltipDisplay;
//? }
import net.minecraft.world.level.Level;
import org.agmas.Quartermaster;
import org.agmas.entity.GunpowderEntity;
import org.agmas.init.ModEntities;
import org.agmas.init.ModItems;

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
        return getChargeDuration(itemStack,livingEntity);
    }

    @Override
    //? if >1.21.1
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
    //? if <=1.21.1
    //public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        ChargedProjectiles chargedProjectiles = itemStack.get(DataComponents.CHARGED_PROJECTILES);
        if (chargedProjectiles != null && !chargedProjectiles.isEmpty()) {
            Quartermaster.timeSinceShooting = 15;
        }
        return super.use(level, player, interactionHand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        livingEntity.stopUsingItem();
        return super.finishUsingItem(itemStack, level, livingEntity);
    }

    @Override
    protected Projectile createProjectile(Level level, LivingEntity livingEntity, ItemStack itemStack, ItemStack itemStack2, boolean bl) {
        GunpowderEntity gunpowderEntity = ModEntities.GUNPOWDER.create(level
                //? if >1.21.1
                , EntitySpawnReason.TRIGGERED
        );
        gunpowderEntity.setPos(livingEntity.getPosition(0f).x,livingEntity.getEyePosition(0f).y-0.25f,livingEntity.getPosition(0f).z);
        gunpowderEntity.setOwner(livingEntity);
        return gunpowderEntity;
    }
}
