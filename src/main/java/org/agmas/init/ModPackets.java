package org.agmas.init;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.agmas.network.ServerboundInspectPacket;

public class ModPackets {
    public static void init() {
        //? if >=26.1 {
        /*PayloadTypeRegistry.serverboundPlay()
        *///? } else {
        PayloadTypeRegistry.playC2S()
        //? }
                .register(ServerboundInspectPacket.TYPE, ServerboundInspectPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ServerboundInspectPacket.TYPE, ((payload, context) -> {
            if (!context.player().hasAttached(ModAttachments.INSPECT_ANIMATION_TICKS)) {
                context.player().setAttached(ModAttachments.INSPECT_ANIMATION_TICKS, 90);
            } else {
                context.player().removeAttached(ModAttachments.INSPECT_ANIMATION_TICKS);
            }
        }));
    }
}
