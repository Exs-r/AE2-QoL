package com.ae2qol.state;

import appeng.api.networking.pathing.ChannelMode;
import appeng.core.AEConfig;
import com.ae2qol.Ae2Qol;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * World-level state of the mod: the global channel toggle and a per-player flight toggle.
 */
public final class QolState extends SavedData {
    private static final String DATA_NAME = "ae2qol_state";

    private boolean channelsUnlimited;
    private final Map<UUID, Boolean> flightEnabled = new HashMap<>();

    public static QolState get(MinecraftServer server) {
        return server.overworld()
                .getDataStorage()
                .computeIfAbsent(new SavedData.Factory<>(QolState::new, QolState::load, null), DATA_NAME);
    }

    public boolean isChannelsUnlimited() {
        return channelsUnlimited;
    }

    public void setChannelsUnlimited(boolean value) {
        if (channelsUnlimited == value) {
            return;
        }
        channelsUnlimited = value;
        setDirty();
        applyChannels();
        Ae2Qol.LOG.info("AE2 channel mode set to {}", value ? "INFINITE" : "DEFAULT");
    }

    /**
     * Applies the saved channel mode to AE2's config. Safe to call on server start.
     */
    public void applyChannels() {
        AEConfig.instance().setChannelModel(channelsUnlimited ? ChannelMode.INFINITE : ChannelMode.DEFAULT);
    }

    public boolean isFlightEnabled(Player player) {
        return flightEnabled.getOrDefault(player.getUUID(), Boolean.FALSE);
    }

    public void setFlight(Player player, boolean value) {
        if (value) {
            flightEnabled.put(player.getUUID(), Boolean.TRUE);
        } else {
            flightEnabled.remove(player.getUUID());
        }
        setDirty();
        applyFlight(player);
    }

    /**
     * Keeps the player's ability flags in sync with the toggle. Creative and spectator
     * players are left untouched so their own flight is never removed.
     */
    public void applyFlight(Player player) {
        if (player.isCreative() || player.isSpectator()) {
            return;
        }

        boolean want = isFlightEnabled(player);
        var abilities = player.getAbilities();
        if (abilities.mayfly != want) {
            abilities.mayfly = want;
            if (!want) {
                abilities.flying = false;
            }
            player.onUpdateAbilities();
        }
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putBoolean("channels_unlimited", channelsUnlimited);

        CompoundTag flight = new CompoundTag();
        flightEnabled.forEach((uuid, enabled) -> {
            if (enabled) {
                flight.putBoolean(uuid.toString(), true);
            }
        });
        tag.put("flight", flight);
        return tag;
    }

    public static QolState load(CompoundTag tag, HolderLookup.Provider registries) {
        QolState state = new QolState();
        state.channelsUnlimited = tag.getBoolean("channels_unlimited");

        CompoundTag flight = tag.getCompound("flight");
        for (String key : flight.getAllKeys()) {
            if (!flight.getBoolean(key)) {
                continue;
            }
            try {
                state.flightEnabled.put(UUID.fromString(key), Boolean.TRUE);
            } catch (IllegalArgumentException ignored) {
                // Ignore malformed uuids instead of breaking the whole save.
            }
        }
        return state;
    }
}
