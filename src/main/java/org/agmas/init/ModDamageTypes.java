package org.agmas.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import org.agmas.porting.QMIdentifier;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> WOUND = ResourceKey.create(Registries.DAMAGE_TYPE, QMIdentifier.of("wound").id);
    public static final ResourceKey<DamageType> SHOCKWAVE = ResourceKey.create(Registries.DAMAGE_TYPE, QMIdentifier.of("shockwave").id);
    public static final ResourceKey<DamageType> RUDE_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, QMIdentifier.of("rude_damage").id);

    public static void init() {
    }
}
