package angercraft.postcreditsmusic.network;

import angercraft.postcreditsmusic.client.ClientHandler;
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
        ctx.get().enqueueWork(ClientHandler::playTrack);
        ctx.get().setPacketHandled(true);
    }


}
