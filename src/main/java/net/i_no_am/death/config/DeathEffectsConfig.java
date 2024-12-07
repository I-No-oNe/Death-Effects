package net.i_no_am.death.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler.EnumDisplayOption;

import static net.i_no_am.death.DeathEffects.MOD_ID;

@Config(name = MOD_ID)
public class DeathEffectsConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public ModSettings modSettings = new ModSettings();
    @ConfigEntry.Gui.CollapsibleObject
    public RocketColor rocket = new RocketColor();
    @ConfigEntry.Gui.CollapsibleObject
    public LightningBolt lightning = new LightningBolt();

    public static class RocketColor {
        public boolean spawnRocketParticles = false;

        @ConfigEntry.Gui.EnumHandler(option = EnumDisplayOption.BUTTON)
        public RocketShapes rocketShape = RocketShapes.BURST;

        @BoundedDiscrete(min = 0, max = 5)
        public int rocketVelocity = 1;
        @ConfigEntry.ColorPicker
        public int rocketColor = 0xFFFFFF;

    }

    public static class LightningBolt {
        public boolean spawnLighting = true;
    }

    public static class ModSettings {
        @ConfigEntry.Gui.EnumHandler(option = EnumDisplayOption.BUTTON)
        public Entities entitySelection = Entities.BOTH;
        public boolean enableViaMessage = false;
        public String trigger = "";
    }

    public enum Entities {
        PLAYER,
        MOB,
        BOTH
    }

    public enum RocketShapes {
        SMALL_BALL,
        LARGE_BALL,
        STAR,
        CREEPER,
        BURST
    }
}
