package net.i_no_am.death.utils;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.i_no_am.death.Global;
import net.minecraft.component.type.FireworkExplosionComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.Collections;

public class Utils implements Global {

    public static String getPlayerName(Text message) {
        String[] words = message.getString().split(" ");
        for (String word : words) {
            for (PlayerEntity player : mc.world.getPlayers()) {
                if (word.equalsIgnoreCase(player.getName().getString()))
                    return player.getName().getString();
            }
        }
        return null;
    }

    public static void spawnFirework(String playerName) {
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (playerName != null && player.getName().getString().contains(playerName))
                spawnFirework(player);
        }
    }

    public static void spawnLightning(String playerName) {
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (playerName != null && player.getName().getString().contains(playerName))
                spawnLightning(player);
        }
    }

    public static void spawnFirework(Entity entity) {
        IntList colors = new IntArrayList();
        colors.add(config.rocket.rocketColor);
        IntList fadeColors = new IntArrayList();
        fadeColors.add(fadeColor(config.rocket.rocketColor));

        FireworkExplosionComponent fireworkExplosion = new FireworkExplosionComponent(
                getRocketShape(),
                colors,
                fadeColors,
                true,
                true);
        mc.world.addFireworkParticle(
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
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, mc.world);
        lightning.refreshPositionAfterTeleport(entity.getX(), entity.getY(), entity.getZ());
        mc.world.addEntity(lightning);
    }

    public static boolean shouldApplyEffect(Entity entity) {
        return switch (config.modSettings.entitySelection) {
            case PLAYER -> entity instanceof PlayerEntity;
            case MOB -> !(entity instanceof PlayerEntity);
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

    public static FireworkExplosionComponent.Type getRocketShape() {
        return switch (config.rocket.rocketShape) {
            case BURST -> FireworkExplosionComponent.Type.BURST;
            case CREEPER -> FireworkExplosionComponent.Type.CREEPER;
            case STAR -> FireworkExplosionComponent.Type.STAR;
            case SMALL_BALL -> FireworkExplosionComponent.Type.SMALL_BALL;
            case LARGE_BALL -> FireworkExplosionComponent.Type.LARGE_BALL;
        };
    }
}
