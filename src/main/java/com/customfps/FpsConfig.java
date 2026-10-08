package com.customfps;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class FpsConfig {
    public static final ModConfigSpec SPEC;
    /** 0 = ainda não configurado (usa o valor normal do Minecraft). */
    public static final ModConfigSpec.IntValue FPS_LIMIT;

    static {
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        FPS_LIMIT = b.comment("Limite de FPS personalizado (minimo 1). 0 = nao configurado. "
                        + "No Minecraft, 260 ou mais significa ilimitado.")
                .defineInRange("fpsLimit", 0, 0, Integer.MAX_VALUE);
        SPEC = b.build();
    }

    private FpsConfig() {}
}
