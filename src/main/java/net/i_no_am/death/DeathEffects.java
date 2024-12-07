package net.i_no_am.death;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.i_no_am.death.config.DeathEffectsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeathEffects implements ClientModInitializer {
    public static final String MOD_ID = "death-effects";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        AutoConfig.register(DeathEffectsConfig.class, GsonConfigSerializer::new);
        LOGGER.info("Loaded Death Effects Config!");
    }
}
