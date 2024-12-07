package net.i_no_am.death.mixin;

import net.i_no_am.death.Global;
import net.i_no_am.death.utils.Utils;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class MixinChatHud implements Global {

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V"))
    private void onMessage(Text message, CallbackInfo ci) {
        if (config.modSettings.enableViaMessage && message.getString().contains(config.modSettings.trigger)) {
            if (config.rocket.spawnRocketParticles)
                Utils.spawnFirework(Utils.getPlayerName(message));
            if (config.lightning.spawnLighting)
                Utils.spawnLightning(Utils.getPlayerName(message));
        }
    }
}
