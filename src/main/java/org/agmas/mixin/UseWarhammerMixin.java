package org.agmas.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.agmas.entity.GreataxeProjectileEntity;
import org.agmas.init.ModAttachments;
import org.agmas.init.ModComponents;
import org.agmas.init.ModEntities;
import org.agmas.init.ModSounds;
import org.agmas.init.tag.ModTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MeleeAttackGoal.class)
public abstract class UseWarhammerMixin {

    @Shadow
    @Final
    protected PathfinderMob mob;

    @WrapMethod(method = "checkAndPerformAttack")
    void useWarhammer(LivingEntity livingEntity, Operation<Void> original) {
        if ((livingEntity.isBlocking() || livingEntity.getRandom().nextInt(0,3) == 0) && mob.getMainHandItem().is(ModTags.GREATAXES) && mob.tickCount % 100 == 0) {
            GreataxeProjectileEntity buster = ModEntities.GREATAXE_PROJECTILE.create(mob.level(), EntitySpawnReason.TRIGGERED);
            buster.setOwner(mob);
            buster.setColor(mob.getMainHandItem().get(ModComponents.BUSTER_COLOR).intValue());
            buster.attackPower = (int) (mob.getAttribute(Attributes.ATTACK_DAMAGE).getValue()*0.7f);
            buster.setDeltaMovement(mob.getViewVector(0f).multiply(2,1.5f,2));
            buster.setPos(mob.getEyePosition().add(0,-0.1f,0));
            mob.level().addFreshEntity(buster);
            mob.level().playSound(null,mob.getX(),mob.getY(),mob.getZ(), ModSounds.GREATAXE_USE, mob.getSoundSource());
            return;
        }
        if ((livingEntity.isBlocking() || livingEntity.getRandom().nextInt(0,20) == 0) && mob.getMainHandItem().is(ModTags.WARHAMMERS) && !mob.hasAttached(ModAttachments.WARHAMMER_CAST_TIME)) {
            mob.setAttached(ModAttachments.WARHAMMER_CAST_TIME, 40);
            return;
        }
        original.call(livingEntity);
    }


}
