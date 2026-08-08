package org.agmas.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SpellParticleOption;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.agmas.init.ModEntities;
import org.agmas.init.ModSounds;

import java.awt.*;
import java.util.List;

public class GunpowderEntity extends Projectile {

    float attackPower = 8;
    public GunpowderEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        this.hitTargetOrDeflectSelf(hitResult);

        setDeltaMovement(getDeltaMovement().scale(0.9f));
        setPos(getPosition(0f).add(getDeltaMovement()).add(0,-0.2f,0));
        super.tick();
    }


    @Override
    public boolean canBeHitByProjectile() {
        return true;
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        if (!level().isClientSide()) {
            discard();
        }
        super.onHitBlock(blockHitResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (!level().isClientSide()) {
            entityHitResult.getEntity().hurt(entityHitResult.getEntity().level().damageSources().explosion(this,(LivingEntity) getOwner()), attackPower);

            discard();
        }
        super.onHitEntity(entityHitResult);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

}
