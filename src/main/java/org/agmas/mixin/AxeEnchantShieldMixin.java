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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.agmas.Quartermaster;
import org.agmas.init.ModEnchants;
import org.agmas.item.CutlassItem;
import org.agmas.item.EstocItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class AxeEnchantShieldMixin extends LivingEntity {


    protected AxeEnchantShieldMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }


    @WrapOperation(method = "blockUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getSecondsToDisableBlocking()F"))
    public float axeEnchants(LivingEntity attacker, Operation<Float> original) {
        LivingEntity instance = this;
        boolean splinter = EnchantmentHelper.getEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.SPLINTER),attacker) > 0;

        boolean takedown = EnchantmentHelper.getEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.TAKEDOWN),attacker) > 0;
        if (takedown) {
            CutlassItem.disarm(instance,1);
        }

        if (splinter) {
            EstocItem.wound(instance);
            EstocItem.wound(instance);
        }

        if (getItemBlockingWith() != null) {
            boolean brittle = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.BRITTLE),getItemBlockingWith()) > 0;
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

            boolean shieldBash = EnchantmentHelper.getItemEnchantmentLevel(ModEnchants.enchantHolder(level(), ModEnchants.SHIELD_BASH),getItemBlockingWith()) > 0;
            if (shieldBash)
                return original.call(attacker)*2f;
            if (brittle) {
                return 3.5f;
            }
        }
        if (takedown)
            return Quartermaster.DISARMED_TICKS/20f;
        if (splinter)
            return original.call(attacker)*0.33f;
        return original.call(attacker);
    }


}
