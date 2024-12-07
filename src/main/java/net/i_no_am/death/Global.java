package net.i_no_am.death;

import me.shedaniel.autoconfig.AutoConfig;
import net.i_no_am.death.config.DeathEffectsConfig;
import net.minecraft.client.MinecraftClient;


public interface Global {
    MinecraftClient mc = MinecraftClient.getInstance();
    DeathEffectsConfig config = mc.options != null ? AutoConfig.getConfigHolder(DeathEffectsConfig.class).getConfig() : null;
}
