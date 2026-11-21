package com.ae2qol.net;

import com.ae2qol.Ae2Qol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Client -> server: toggle one of the two switches.
 */
public record SetTogglePayload(String key, boolean value) implements CustomPacketPayload {
    public static final Type<SetTogglePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Ae2Qol.MODID, "set_toggle"));

    public static final StreamCodec<FriendlyByteBuf, SetTogglePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, SetTogglePayload::key,
            ByteBufCodecs.BOOL, SetTogglePayload::value,
            SetTogglePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
