package org.agmas.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.agmas.QMIdentifier;

public record ServerboundInspectPacket(boolean isInspecting) implements CustomPacketPayload {
    public static final Type<ServerboundInspectPacket> TYPE = new Type<>(QMIdentifier.of("inspect").id);
    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundInspectPacket> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, ServerboundInspectPacket::isInspecting, ServerboundInspectPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
