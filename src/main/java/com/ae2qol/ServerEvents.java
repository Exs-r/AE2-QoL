package com.ae2qol;

import com.ae2qol.net.QolNetwork;
import com.ae2qol.state.QolState;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public final class ServerEvents {
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        QolState.get(event.getServer()).applyChannels();
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            QolState.get(player.server).applyFlight(player);
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            QolState state = QolState.get(player.server);
            state.applyFlight(player);
            QolNetwork.sendState(player, state);
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            QolState state = QolState.get(player.server);
            state.applyFlight(player);
            QolNetwork.sendState(player, state);
        }
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            QolState state = QolState.get(player.server);
            state.applyFlight(player);
            QolNetwork.sendState(player, state);
        }
    }
}
