package org.agmas.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;

public class ModComponents {
   public static final DataComponentType<Integer> COMBO_TO_DISABLE = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            QMIdentifier.of("combo_to_disable").id,
            DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build()
    );
    public static final DataComponentType<Integer> CHARGES =  Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            QMIdentifier.of("charges").id,
            DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build()
    );
 public static final DataComponentType<Integer> MAX_CHARGES =  Registry.register(
         BuiltInRegistries.DATA_COMPONENT_TYPE,
         QMIdentifier.of("max_charges").id,
         DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build()
 );
     public static final DataComponentType<Float> WARHAMMER_RANGE =  Registry.register(
             BuiltInRegistries.DATA_COMPONENT_TYPE,
             QMIdentifier.of("warhammer_range").id,
             DataComponentType.<Float>builder().persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT).build()
     );
    public static final DataComponentType<Float> FALL_DAMAGE_SHIELD_DISABLE_MULTIPLIER = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            QMIdentifier.of("fall_damage_shield_disable_multiplier").id,
            DataComponentType.<Float>builder().persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT).build()
    );
    public static final DataComponentType<Integer> BUSTER_COLOR = Registry.register(
             BuiltInRegistries.DATA_COMPONENT_TYPE,
             QMIdentifier.of("buster_color").id,
             DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build()
    );
    public static final DataComponentType<Integer> COMBAT_EFFECT_COLOR = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            QMIdentifier.of("combat_effect_color").id,
            DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build()
    );

    public static final DataComponentType<Boolean> IS_CORAL = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            QMIdentifier.of("is_coral").id,
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build()
    );

    public static void init() {}
}
