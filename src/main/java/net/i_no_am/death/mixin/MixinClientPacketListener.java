package net.i_no_am.death.mixin;

import net.i_no_am.death.Global;
import net.i_no_am.death.utils.Utils;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class MixinClientPacketListener implements Global {

    @Inject(method = "handleEntityEvent", at = @At("HEAD"))
    private void onEntityEvent(ClientboundEntityEventPacket packet, CallbackInfo ci) {
        if (mc.player == null || mc.level == null || config == null
                || config.modSettings.enableViaMessage && !config.modSettings.trigger.isEmpty())
            return;

        if (packet.getEventId() == 3) {
            var entity = packet.getEntity(mc.level);

            if (entity == null || !Utils.shouldApplyEffect(entity)) return;

            if (config.rocket.spawnRocketParticles)
                Utils.spawnFirework(entity);
            if (config.lightning.spawnLighting)
                Utils.spawnLightning(entity);
        }
    }
}
