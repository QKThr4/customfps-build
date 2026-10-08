package com.customfps.client;

import com.customfps.CustomFps;
import com.customfps.FpsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

@EventBusSubscriber(modid = CustomFps.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public final class ClientEvents {
    private static boolean applied = false;

    private ClientEvents() {}

    /** Reaplica o limite salvo assim que o jogo começa a rodar. */
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post e) {
        if (applied) return;
        applied = true;
        int saved = FpsConfig.FPS_LIMIT.get();
        if (saved > 0) {
            Minecraft.getInstance().getWindow().setFramerateLimit(saved);
        }
    }

    /** Botão no menu ESC e na tela de Opções (acessível também pelo menu principal). */
    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Post e) {
        Screen screen = e.getScreen();
        if (screen instanceof OptionsScreen || screen instanceof PauseScreen) {
            Button b = Button.builder(Component.translatable("gui.customfps.button"),
                            btn -> Minecraft.getInstance().setScreen(new FpsLimitScreen(screen)))
                    .bounds(screen.width - 116, 6, 110, 20)
                    .build();
            e.addListener(b);
        }
    }
}
