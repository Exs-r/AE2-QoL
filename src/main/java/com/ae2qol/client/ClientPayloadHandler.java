package com.ae2qol.client;

import com.ae2qol.net.SyncStatePayload;

/**
 * Client-side mirror of the server state, updated by the sync packet.
 */
public final class ClientPayloadHandler {
    public static volatile boolean channels;
    public static volatile boolean flight;

    private ClientPayloadHandler() {
    }

    public static void handleSync(SyncStatePayload payload) {
        channels = payload.channels();
        flight = payload.flight();
    }
}
