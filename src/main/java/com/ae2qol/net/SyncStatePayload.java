package com.ae2qol.net;

import com.ae2qol.Ae2Qol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Server -> client: the authoritative switch states.
 */
public record SyncStatePayload(boolean channels, boolean flight) implements CustomPacketPayload {
    public static final Type<SyncStatePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Ae2Qol.MODID, "sync_state"));

    public static final StreamCodec<FriendlyByteBuf, SyncStatePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, SyncStatePayload::channels,
            ByteBufCodecs.BOOL, SyncStatePayload::flight,
            SyncStatePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
