package com.ae2qol.client;

import appeng.client.gui.widgets.AE2Button;
import com.ae2qol.net.ClaimGiftPayload;
import com.ae2qol.net.RequestStatePayload;
import com.ae2qol.net.SetTogglePayload;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * The mod screen, drawn in AE2's panel colours and using AE2's own button widget.
 */
public class QolScreen extends Screen {
    private static final int PANEL_WIDTH = 320;
    private static final int PANEL_HEIGHT = 200;

    private static final int TEXT_COLOR = 0xFF413F54;
    private static final int MUTED_COLOR = 0xFF878FA5;

    private int left;
    private int top;

    private AE2Button channelsButton;
    private AE2Button flightButton;

    public QolScreen() {
        super(Component.translatable("gui.ae2qol.title"));
    }

    @Override
    protected void init() {
        this.left = (this.width - PANEL_WIDTH) / 2;
        this.top = (this.height - PANEL_HEIGHT) / 2;

        this.channelsButton = new AE2Button(this.left + 200, this.top + 44, 106, 20,
                toggleLabel(ClientPayloadHandler.channels),
                button -> sendToggle("channels", !ClientPayloadHandler.channels));

        this.flightButton = new AE2Button(this.left + 200, this.top + 84, 106, 20,
                toggleLabel(ClientPayloadHandler.flight),
                button -> sendToggle("flight", !ClientPayloadHandler.flight));

        this.addRenderableWidget(this.channelsButton);
        this.addRenderableWidget(this.flightButton);

        this.addRenderableWidget(new AE2Button(this.left + 236, this.top + 124, 70, 20,
                Component.translatable("gui.ae2qol.claim"),
                button -> PacketDistributor.sendToServer(new ClaimGiftPayload("starter"))));

        this.addRenderableWidget(new AE2Button(this.left + 236, this.top + 164, 70, 20,
                Component.translatable("gui.ae2qol.claim"),
                button -> PacketDistributor.sendToServer(new ClaimGiftPayload("infinity"))));

        PacketDistributor.sendToServer(new RequestStatePayload());
    }

    private static void sendToggle(String key, boolean value) {
        PacketDistributor.sendToServer(new SetTogglePayload(key, value));
    }

    private static Component toggleLabel(boolean enabled) {
        return Component.translatable(enabled ? "gui.ae2qol.on" : "gui.ae2qol.off")
                .withStyle(enabled ? ChatFormatting.DARK_GREEN : ChatFormatting.DARK_GRAY);
    }

    @Override
    public void renderBackground(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.fill(0, 0, this.width, this.height, 0x88000000);
        drawPanel(graphics);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        // Keep the button captions in sync with the latest state from the server.
        this.channelsButton.setMessage(toggleLabel(ClientPayloadHandler.channels));
        this.flightButton.setMessage(toggleLabel(ClientPayloadHandler.flight));

        super.render(graphics, mouseX, mouseY, partialTick);

        graphics.drawString(this.font, this.title, this.left + 14, this.top + 12, TEXT_COLOR, false);
        graphics.drawString(this.font, Component.translatable("gui.ae2qol.subtitle"),
                this.left + 14, this.top + 26, MUTED_COLOR, false);

        graphics.drawString(this.font, Component.translatable("gui.ae2qol.channels"),
                this.left + 16, this.top + 50, TEXT_COLOR, false);
        graphics.drawString(this.font, Component.translatable("gui.ae2qol.flight"),
                this.left + 16, this.top + 90, TEXT_COLOR, false);
        graphics.drawString(this.font, Component.translatable("gui.ae2qol.starter"),
                this.left + 16, this.top + 130, TEXT_COLOR, false);
        graphics.drawString(this.font, Component.translatable("gui.ae2qol.infinity"),
                this.left + 16, this.top + 170, TEXT_COLOR, false);

        if (isHovering(16, 50, 180, 20, mouseX, mouseY)) {
            graphics.renderTooltip(this.font, Component.translatable("gui.ae2qol.channels.tooltip"),
                    mouseX, mouseY);
        } else if (isHovering(16, 90, 180, 20, mouseX, mouseY)) {
            graphics.renderTooltip(this.font, Component.translatable("gui.ae2qol.flight.tooltip"),
                    mouseX, mouseY);
        } else if (isHovering(16, 130, 180, 20, mouseX, mouseY)) {
            graphics.renderTooltip(this.font, Component.translatable("gui.ae2qol.starter.tooltip"),
                    mouseX, mouseY);
        } else if (isHovering(16, 170, 180, 20, mouseX, mouseY)) {
            graphics.renderTooltip(this.font, Component.translatable("gui.ae2qol.infinity.tooltip"),
                    mouseX, mouseY);
        }
    }

    private boolean isHovering(int relativeX, int relativeY, int width, int height, int mouseX, int mouseY) {
        int x = this.left + relativeX;
        int y = this.top + relativeY;
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private void drawPanel(GuiGraphics graphics) {
        // Pure colour drawing avoids texture sampling seams between the nine slices.
        // Colours match AE2's own panel texture: #413F54 outline, #F2F2F2 inner line,
        // #CBCCD4 fill.
        int x = this.left;
        int y = this.top;
        int w = PANEL_WIDTH;
        int h = PANEL_HEIGHT;

        // Outer frame: a second 1px ring floating a few pixels outside the panel.
        int gap = 3;
        int ox = x - gap;
        int oy = y - gap;
        int ow = w + gap * 2;
        int oh = h + gap * 2;
        graphics.fill(ox, oy, ox + ow, oy + 1, 0xFF413F54);
        graphics.fill(ox, oy + oh - 1, ox + ow, oy + oh, 0xFF413F54);
        graphics.fill(ox, oy, ox + 1, oy + oh, 0xFF413F54);
        graphics.fill(ox + ow - 1, oy, ox + ow, oy + oh, 0xFF413F54);

        // Panel body.
        graphics.fill(x, y, x + w, y + h, 0xFF413F54);
        graphics.fill(x + 1, y + 1, x + w - 1, y + h - 1, 0xFFF2F2F2);
        graphics.fill(x + 2, y + 2, x + w - 2, y + h - 2, 0xFFCBCCD4);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
