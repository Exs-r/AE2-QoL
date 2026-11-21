package com.ae2qol.net;

import com.ae2qol.Ae2Qol;
import com.ae2qol.client.ClientPayloadHandler;
import com.ae2qol.gift.GiftFactory;
import com.ae2qol.state.QolState;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class QolNetwork {
    private static final long CLAIM_COOLDOWN_MS = 1000L;
    private static final Map<UUID, Long> LAST_CLAIM = new ConcurrentHashMap<>();

    private QolNetwork() {
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(SetTogglePayload.TYPE, SetTogglePayload.STREAM_CODEC, QolNetwork::handleSetToggle);
        registrar.playToServer(ClaimGiftPayload.TYPE, ClaimGiftPayload.STREAM_CODEC, QolNetwork::handleClaim);
        registrar.playToServer(RequestStatePayload.TYPE, RequestStatePayload.STREAM_CODEC, QolNetwork::handleRequest);
        registrar.playToClient(SyncStatePayload.TYPE, SyncStatePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> ClientPayloadHandler.handleSync(payload)));
    }

    public static void sendState(ServerPlayer player, QolState state) {
        PacketDistributor.sendToPlayer(player,
                new SyncStatePayload(state.isChannelsUnlimited(), state.isFlightEnabled(player)));
    }

    private static void handleRequest(RequestStatePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                sendState(player, QolState.get(player.server));
            }
        });
    }

    private static void handleSetToggle(SetTogglePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }
            QolState state = QolState.get(player.server);
            switch (payload.key()) {
                case "channels" -> state.setChannelsUnlimited(payload.value());
                case "flight" -> state.setFlight(player, payload.value());
                default -> {
                    return;
                }
            }
            sendState(player, state);
        });
    }

    private static void handleClaim(ClaimGiftPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            long now = System.currentTimeMillis();
            Long last = LAST_CLAIM.get(player.getUUID());
            if (last != null && now - last < CLAIM_COOLDOWN_MS) {
                player.displayClientMessage(Component.translatable("msg.ae2qol.claim.cooldown"), true);
                return;
            }

            ItemStack gift = switch (payload.gift()) {
                case "starter" -> GiftFactory.buildStarter();
                case "infinity" -> GiftFactory.buildInfinity();
                default -> ItemStack.EMPTY;
            };
            if (gift.isEmpty()) {
                Ae2Qol.LOG.warn("Could not build gift '{}' for {}", payload.gift(), player.getGameProfile().getName());
                player.displayClientMessage(Component.translatable("msg.ae2qol.claim.failed"), true);
                return;
            }

            LAST_CLAIM.put(player.getUUID(), now);
            if (!player.getInventory().add(gift)) {
                player.drop(gift, false);
                player.displayClientMessage(Component.translatable("msg.ae2qol.claimed.dropped"), false);
            }
            player.displayClientMessage(Component.translatable("msg.ae2qol.claimed." + payload.gift()), false);
        });
    }
}
