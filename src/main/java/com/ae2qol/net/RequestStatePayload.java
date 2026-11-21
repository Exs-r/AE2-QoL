package com.ae2qol.net;

import com.ae2qol.Ae2Qol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Client -> server: ask for the current switch states (sent when the screen opens).
 */
public record RequestStatePayload() implements CustomPacketPayload {
    public static final Type<RequestStatePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Ae2Qol.MODID, "request_state"));

    public static final StreamCodec<FriendlyByteBuf, RequestStatePayload> STREAM_CODEC =
            StreamCodec.unit(new RequestStatePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
