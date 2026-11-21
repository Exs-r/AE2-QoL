package com.ae2qol.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

public final class ClientSetup {
    public static final KeyMapping OPEN_KEY = new KeyMapping(
            "key.ae2qol.open",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_I,
            "key.categories.ae2qol");

    private ClientSetup() {
    }

    public static void init(IEventBus modBus) {
        modBus.addListener(ClientSetup::registerKeys);
        NeoForge.EVENT_BUS.addListener(ClientSetup::onClientTick);
    }

    private static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(OPEN_KEY);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        while (OPEN_KEY.consumeClick()) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.screen == null) {
                minecraft.setScreen(new QolScreen());
            }
        }
    }
}
