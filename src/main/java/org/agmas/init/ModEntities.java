package org.agmas.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.agmas.QMIdentifier;
import org.agmas.entity.GreataxeProjectileEntity;
import org.agmas.entity.GunpowderEntity;

public class ModEntities {
    public static final EntityType<GreataxeProjectileEntity> GREATAXE_PROJECTILE = register(
            "greataxe_projectile",
            EntityType.Builder.<GreataxeProjectileEntity>of(GreataxeProjectileEntity::new, MobCategory.MISC)
                    .sized(2.5f, 0.5f)
    );
    public static final EntityType<GunpowderEntity> GUNPOWDER = register(
            "gunpowder",
            EntityType.Builder.<GunpowderEntity>of(GunpowderEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, QMIdentifier.of(name).id);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void init() {
    }

}
