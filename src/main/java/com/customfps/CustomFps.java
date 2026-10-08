package com.customfps;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(CustomFps.MODID)
public class CustomFps {
    public static final String MODID = "customfps";

    public CustomFps(ModContainer container) {
        // O valor escolhido fica salvo em config/customfps-client.toml
        container.registerConfig(ModConfig.Type.CLIENT, FpsConfig.SPEC);
    }
}
