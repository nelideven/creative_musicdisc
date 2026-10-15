package com.github.nelideven.creative_musicdisc.mixin;

import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.network.DisconnectionDetails;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// This Mixin serves as a safeguard against the game crashing when a player tries to connect to a vanilla server without the mod installed.
// It intercepts the packet error and provides a user-friendly message instead of a hard crash (IllegalStateException: Missing element ResourceKey).
@Mixin(ClientCommonPacketListenerImpl.class)
public class ConnectionGuardMixin {

    @Inject(method = "onPacketError", at = @At("HEAD"), cancellable = true)
    private void catchRegistrySyncError(Packet<?> packet, Exception exception, CallbackInfo ci) {
        String message = exception.getMessage();
        
        // If the game fails because of missing custom elements on a vanilla server
        if (message != null && message.contains("creative_musicdisc")) {
            Component reason = Component.literal(
                "§c[Creative Music Discs] §eThis server does not have the mod installed!\n\n" +
                "§7Try contacting the server owner to add the mod, play on a server that supports it, or remove the mod from the mods folder."
            );

            ClientCommonPacketListenerImpl listener = (ClientCommonPacketListenerImpl) (Object) this;
            listener.onDisconnect(new DisconnectionDetails(reason));

            // Cancel the hard crash
            ci.cancel();
        }
    }
}