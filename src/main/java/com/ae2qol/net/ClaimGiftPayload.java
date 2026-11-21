package com.ae2qol.net;

import com.ae2qol.Ae2Qol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Client -> server: claim one of the two kits.
 */
public record ClaimGiftPayload(String gift) implements CustomPacketPayload {
    public static final Type<ClaimGiftPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Ae2Qol.MODID, "claim_gift"));

    public static final StreamCodec<FriendlyByteBuf, ClaimGiftPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ClaimGiftPayload::gift,
            ClaimGiftPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
