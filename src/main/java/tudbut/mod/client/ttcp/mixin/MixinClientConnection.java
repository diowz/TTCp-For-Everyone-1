package tudbut.mod.client.ttcp.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tudbut.mod.client.ttcp.events.EventHandler;

@Mixin(value={NetworkManager.class})
public class MixinClientConnection {
    @Inject(method={"sendPacket(Lnet/minecraft/network/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void sendPacketPre(Packet<?> packet, CallbackInfo callbackInfo) {
        if (EventHandler.onPacket(packet)) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"channelRead0"}, at={@At(value="HEAD")}, cancellable=true)
    private void channelReadPre(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
        if (EventHandler.onPacket(packet)) {
            callbackInfo.cancel();
        }
    }
}
