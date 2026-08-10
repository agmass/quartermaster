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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.agmas.init.*;

import java.awt.*;
import java.util.List;

public class GreataxeProjectileEntity extends Projectile {

    public float attackPower = 4;
    public boolean canPierceShields = true;
    private static final EntityDataAccessor<Integer> COLOR_DATA = SynchedEntityData.defineId(GreataxeProjectileEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> SCALE_DATA = SynchedEntityData.defineId(GreataxeProjectileEntity.class, EntityDataSerializers.FLOAT);

    public GreataxeProjectileEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        if (tickCount >= 8) {
            discard();
        }
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        this.hitTargetOrDeflectSelf(hitResult);

        if (!level().isClientSide()) {
            List<Entity> entities = level().getEntities(this,getBoundingBox().inflate(6),(e)->{return e instanceof GreataxeProjectileEntity;});
            if (!entities.isEmpty()) {
                GreataxeProjectileEntity other = (GreataxeProjectileEntity) entities.getFirst();
                if (other.getOwner() != getOwner() && tickCount != 0 && other.tickCount != 0) {
                    GreataxeProjectileEntity buster = ModEntities.GREATAXE_PROJECTILE.create(level(), EntitySpawnReason.TRIGGERED);
                    buster.setOwner(getOwner());
                    Color thisColor = new Color(getColor());
                    Color otherColor = new Color(other.getColor());
                    int r = Math.min(thisColor.getRed() + otherColor.getRed(), 255);
                    int g = Math.min(thisColor.getGreen() + otherColor.getGreen(), 255);
                    int b = Math.min(thisColor.getBlue() + otherColor.getBlue(), 255);
                    buster.setColor(new Color(r, g, b, 255).getRGB());
                    buster.attackPower = (attackPower + other.attackPower) * 1.5f;
                    buster.setDeltaMovement(other.getDeltaMovement());
                    buster.setPos(getPosition(0f));

                    buster.setScale(getScale() + other.getScale());

                    level().addFreshEntity(buster);
                    level().playSound(null, getX(), getY(), getZ(), ModSounds.GREATAXE_USE, getSoundSource());
                    level().playSound(null, getX(), getY(), getZ(), SoundEvents.TRIAL_SPAWNER_OMINOUS_ACTIVATE, getSoundSource());

                    discard();
                    other.discard();
                }
            }
        }

        setPos(getPosition(0f).add(getDeltaMovement()));
        super.tick();
    }

    @Override
    public boolean canBeHitByProjectile() {
        return true;
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        if (!level().isClientSide()) {
            impact();
            discard();
        }
        super.onHitBlock(blockHitResult);
    }

    public void impact() {
        Entity shooter = getOwner();
        Level level = level();
        if (shooter == null) {
            level.playSound(null, getX(), getY(), getZ(), ModSounds.GREATAXE_HIT, getSoundSource());
        } else {
            level.playSound(shooter, getX(), getY(), getZ(), ModSounds.GREATAXE_HIT, getSoundSource());
            if (shooter instanceof ServerPlayer player) {
                player.connection.send(new ClientboundSoundPacket(BuiltInRegistries.SOUND_EVENT.get(ModSounds.GREATAXE_HIT.location()).get(),player.getSoundSource(),player.getX(),player.getY(),player.getZ(),1f,1f,0));
            }
        }
        for (Entity entity : level.getEntities(this, getBoundingBox().inflate(0.5, 3, 0.5))) {
            if (getOwner().equals(entity)) {
                entity.push(0, 0.1f + (0.05f * attackPower), 0);
            } else {
                entity.setDeltaMovement(0, 0.1f + (0.05f * attackPower), 0);
            }
            if (entity instanceof ServerPlayer player) {
                player.connection.send(new ClientboundSetEntityMotionPacket(player));
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (!level().isClientSide()) {

            impact();
            boolean isHit = true;
            if (!canPierceShields) {
                if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                    if (livingEntity.isBlocking()) isHit = false;
                }
            }
            if (isHit) {
                DamageSource source = level().damageSources().source(ModDamageTypes.RUDE_DAMAGE, this);
                entityHitResult.getEntity().hurt(source, attackPower);
                Entity hit = entityHitResult.getEntity();
                ((ServerLevel) level()).sendParticles(SpellParticleOption.create(ParticleTypes.INSTANT_EFFECT, getColor(), 4f), hit.getX(), hit.getEyeY(), hit.getZ(), 20, 0.5f, 0.5f, 0.5f, 0.5f);
            }
            discard();
        }
        super.onHitEntity(entityHitResult);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(COLOR_DATA, Color.MAGENTA.getRGB());
        builder.define(SCALE_DATA, 1f);
    }

    public void setColor(int color) {
        entityData.set(COLOR_DATA, color);
    }

    public int getColor() {
        return entityData.get(COLOR_DATA);
    }

    public void setScale(float scale) {
        entityData.set(SCALE_DATA, scale);
    }

    public float getScale() {
        return entityData.get(SCALE_DATA);
    }
}
