package org.agmas.init;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import org.agmas.QMIdentifier;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> WOUND = ResourceKey.create(Registries.DAMAGE_TYPE, QMIdentifier.of("wound").id);
    public static final ResourceKey<DamageType> SHOCKWAVE = ResourceKey.create(Registries.DAMAGE_TYPE, QMIdentifier.of("shockwave").id);

    public static void init() {
    }
}
