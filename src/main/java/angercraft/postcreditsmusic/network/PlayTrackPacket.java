package angercraft.postcreditsmusic.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayTrackPacket {

    public PlayTrackPacket() {}

    public static void encode(PlayTrackPacket msg, PacketBuffer buf) {}

    public static PlayTrackPacket decode(PacketBuffer buf) {
        return new PlayTrackPacket();
    }

    public static void handle(PlayTrackPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Work that needs to be threadsafe (most work)
            ServerPlayerEntity sender = ctx.get().getSender(); // the client that sent this packet
            // do stuff
        });
        ctx.get().setPacketHandled(true);
    }
}
