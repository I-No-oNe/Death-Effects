package net.i_no_am.death.utils;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.i_no_am.death.Global;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.FireworkExplosion;

import java.util.Collections;

public class Utils implements Global {

    public static String getPlayerName(Component message) {
        String[] words = message.getString().split(" ");
        for (String word : words) {
            for (Player player : mc.level.players()) {
                if (word.equalsIgnoreCase(player.getName().getString()))
                    return player.getName().getString();
            }
        }
        return null;
    }

    public static void spawnFirework(String playerName) {
        for (Player player : mc.level.players()) {
            if (playerName != null && player.getName().getString().contains(playerName))
                spawnFirework(player);
        }
    }

    public static void spawnLightning(String playerName) {
        for (Player player : mc.level.players()) {
            if (playerName != null && player.getName().getString().contains(playerName))
                spawnLightning(player);
        }
    }

    public static void spawnFirework(Entity entity) {
        IntList colors = new IntArrayList();
        colors.add(config.rocket.rocketColor);
        IntList fadeColors = new IntArrayList();
        fadeColors.add(fadeColor(config.rocket.rocketColor));

        FireworkExplosion fireworkExplosion = new FireworkExplosion(
                getRocketShape(),
                colors,
                fadeColors,
                true,
                true);
        mc.level.createFireworks(
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                0,
                config.rocket.rocketVelocity,
                0,
                Collections.singletonList(fireworkExplosion)
        );
    }

    public static void spawnLightning(Entity entity) {
        LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, mc.level);
        lightning.setPos(entity.getX(), entity.getY(), entity.getZ());
        mc.level.addEntity(lightning);
    }

    public static boolean shouldApplyEffect(Entity entity) {
        return switch (config.modSettings.entitySelection) {
            case PLAYER -> entity instanceof Player;
            case MOB -> !(entity instanceof Player);
            case BOTH -> true;
        };
    }

    public static int fadeColor(int baseColor) {
        int red = (baseColor >> 16) & 0xFF;
        int green = (baseColor >> 8) & 0xFF;
        int blue = baseColor & 0xFF;

        red = Math.min(255, red + 30);
        green = Math.min(255, green + 30);
        blue = Math.min(255, blue + 30);

        return (red << 16) | (green << 8) | blue;
    }

    public static FireworkExplosion.Shape getRocketShape() {
        return switch (config.rocket.rocketShape) {
            case BURST -> FireworkExplosion.Shape.BURST;
            case CREEPER -> FireworkExplosion.Shape.CREEPER;
            case STAR -> FireworkExplosion.Shape.STAR;
            case SMALL_BALL -> FireworkExplosion.Shape.SMALL_BALL;
            case LARGE_BALL -> FireworkExplosion.Shape.LARGE_BALL;
        };
    }
}
