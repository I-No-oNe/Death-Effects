package net.i_no_am.death.mixin;

import net.i_no_am.death.Global;
import net.i_no_am.death.utils.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection implements Global {
    @Inject(method = "handlePacket", at = @At("HEAD"))
    private static void onHandlePacket(Packet<?> packet, PacketListener listener, CallbackInfo ci) {
        if (mc == null || mc.player == null || mc.world == null || config == null || config.modSettings.enableViaMessage && !config.modSettings.trigger.isEmpty()) return;

        if (packet instanceof EntityStatusS2CPacket p && p.getStatus() == 3) {
            Entity entity = p.getEntity(mc.world);

            if (entity == null || !Utils.shouldApplyEffect(entity)) return;

            if (config.rocket.spawnRocketParticles)
                Utils.spawnFirework(entity);
            if (config.lightning.spawnLighting)
                Utils.spawnLightning(entity);
        }
    }
}