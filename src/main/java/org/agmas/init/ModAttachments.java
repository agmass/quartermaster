package org.agmas.init;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import org.agmas.QMIdentifier;
import org.agmas.Quartermaster;

public class ModAttachments {

    public static final AttachmentType<Integer> CUTLASS_COMBO = AttachmentRegistry.create(
            QMIdentifier.of("cutlass_combo").id,
            integerBuilder -> integerBuilder.initializer(() -> 0).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.targetOnly())
    );
    public static final AttachmentType<Integer> STORED_ESTOC_TICKS = AttachmentRegistry.create(
            QMIdentifier.of("stored_estoc_ticks").id,
            integerBuilder -> integerBuilder.initializer(() -> 1).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.targetOnly())
    );
    public static final AttachmentType<Integer> DISARMED_ANIMATION_TICKS = AttachmentRegistry.create(
            QMIdentifier.of("disarmed_animation_ticks").id,
            integerBuilder -> integerBuilder.initializer(() -> 0).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all())
    );
    public static final AttachmentType<Integer> INSPECT_ANIMATION_TICKS = AttachmentRegistry.create(
            QMIdentifier.of("inspect_animation_ticks").id,
            integerBuilder -> integerBuilder.initializer(() -> 0).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all())
    );
    public static final AttachmentType<Integer> WARHAMMER_CAST_TIME = AttachmentRegistry.create(
            QMIdentifier.of("warhammer_cast_time").id,
            integerBuilder -> integerBuilder.initializer(() -> 0).syncWith(ByteBufCodecs.INT, AttachmentSyncPredicate.all())
    );


    public static void init() {}
}
