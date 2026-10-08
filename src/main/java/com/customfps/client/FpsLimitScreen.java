package com.customfps.client;

import com.customfps.FpsConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

/** Tela onde o jogador digita o limite de FPS (qualquer número >= 1). */
public class FpsLimitScreen extends Screen {
    private final Screen parent;
    private EditBox box;
    private boolean showError = false;

    public FpsLimitScreen(Screen parent) {
        super(Component.translatable("gui.customfps.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int cy = this.height / 2;

        box = new EditBox(this.font, cx - 60, cy - 10, 120, 20, this.title);
        box.setMaxLength(10);
        box.setFilter(s -> s.matches("\\d*"));
        box.setValue(String.valueOf(this.minecraft.getWindow().getFramerateLimit()));
        this.addRenderableWidget(box);
        this.setInitialFocus(box);

        this.addRenderableWidget(Button.builder(Component.translatable("gui.customfps.apply"),
                b -> applyAndClose()).bounds(cx - 102, cy + 20, 100, 20).build());
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL,
                b -> onClose()).bounds(cx + 2, cy + 20, 100, 20).build());
    }

    private void applyAndClose() {
        long v;
        try {
            v = Long.parseLong(box.getValue().trim());
        } catch (NumberFormatException e) {
            v = 0L;
        }
        if (v < 1L) {
            showError = true;
            return;
        }
        int fps = (int) Math.min(v, (long) Integer.MAX_VALUE);
        this.minecraft.getWindow().setFramerateLimit(fps);
        FpsConfig.FPS_LIMIT.set(fps);
        FpsConfig.FPS_LIMIT.save();
        onClose();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 257 || keyCode == 335) { // Enter / Enter do teclado numérico
            applyAndClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);
        int cx = this.width / 2;
        int cy = this.height / 2;
        g.drawCenteredString(this.font, this.title, cx, cy - 50, 0xFFFFFF);
        g.drawCenteredString(this.font, Component.translatable("gui.customfps.hint"), cx, cy - 30, 0xAAAAAA);
        if (showError) {
            g.drawCenteredString(this.font, Component.translatable("gui.customfps.invalid"), cx, cy + 48, 0xFF5555);
        }
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }
}
