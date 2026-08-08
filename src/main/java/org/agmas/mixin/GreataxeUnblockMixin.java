package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.agmas.init.ModComponents;
import org.agmas.init.ModEnchants;
import org.agmas.init.ModSounds;
import org.agmas.init.tag.ModTags;
import org.agmas.item.GreataxeItem;
import org.agmas.item.MorningstarItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LivingEntity.class)
public abstract class GreataxeUnblockMixin extends Entity {


    public GreataxeUnblockMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @WrapMethod(method = "applyItemBlocking")
    public float greataxeBlock(ServerLevel serverLevel, DamageSource damageSource, float f, Operation<Float> original) {
        return GreataxeItem.modifyBlockDamage((LivingEntity)(Object)this, serverLevel,damageSource,original.call(serverLevel,damageSource,f));
    }


}
