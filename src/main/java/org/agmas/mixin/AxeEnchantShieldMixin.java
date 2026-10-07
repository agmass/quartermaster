package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.agmas.Quartermaster;
import org.agmas.init.ModEnchants;
import org.agmas.item.CutlassItem;
import org.agmas.item.EstocItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class AxeEnchantShieldMixin extends LivingEntity {


    @Shadow
    public abstract ItemCooldowns getCooldowns();

    protected AxeEnchantShieldMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }


    //? if <1.21.11 {
    /*@WrapOperation(method = "blockUsingShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;disableShield()V"))
    public void axeEnchants(Player instance, Operation<Void> original, @Local LivingEntity attacker) {
    *///? } else {
    @WrapOperation(method = "blockUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getSecondsToDisableBlocking()F"))
    public float axeEnchants(LivingEntity attacker, Operation<Float> original) {
    //? }
        //? if >=1.21.11
        LivingEntity instance = this;

        //? if >=1.21.11
        float amount = original.call(attacker);
        //? if <1.21.11
        //float amount = 100/20F;
        boolean cancel = false;
        boolean splinter = EnchantmentHelper.getEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.SPLINTER),attacker) > 0;

        boolean takedown = EnchantmentHelper.getEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.TAKEDOWN),attacker) > 0;
        if (takedown) {
            CutlassItem.disarm(instance,1);
        }

        if (splinter) {
            EstocItem.wound(instance);
            EstocItem.wound(instance);
        }

        //? if >=1.21.11
        ItemStack blockingItem = getItemBlockingWith();
        //? if <1.21.11
        //ItemStack blockingItem = getUseItem();



        if (blockingItem != null) {
            boolean brittle = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.BRITTLE),blockingItem) > 0;
            if (brittle) {
                float attackDamage = (float)(attacker.getAttribute(Attributes.ATTACK_DAMAGE).getValue())/3.5f;
                instance.setDeltaMovement(getViewVector(0f).multiply(-attackDamage,-attackDamage,-attackDamage));
                //? if >=1.21.11 {
                instance.needsSync = true;
                //? } else {
                /*instance.hasImpulse = true;
                 *///? }
                if (instance instanceof ServerPlayer player2) {
                    player2.connection.send(new ClientboundSetEntityMotionPacket(player2));
                }
            }

            boolean shieldBash = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.SHIELD_BASH),blockingItem) > 0;
            if (shieldBash) {
                amount *= 2f;
                cancel = true;
            }
            if (brittle) {
                amount = 3.5f;
                cancel = true;
            }
        }
        if (takedown) {
            amount = Quartermaster.DISARMED_TICKS / 20f;
            cancel = true;
        }
        if (splinter) {
            amount *= 0.33f;
            cancel = true;
        }



        //? if >=1.21.11 {
        return amount;
        //? } else {
        /*if (cancel) {
            getCooldowns().addCooldown(Items.SHIELD, (int)(amount*20));
            this.stopUsingItem();
            this.level().broadcastEntityEvent(this, (byte)30);
        } else {
            original.call(instance);
        }
        *///? }
    }


}
