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
        System.out.println("Packet received");
        ctx.get().enqueueWork(() -> {
            // Work that needs to be threadsafe (most work)
            System.out.println("Package being handled on "+ctx.get().getDirection().getReceptionSide().toString());

            ClientHandler.playTrack();

            // do stuff
        });
        ctx.get().setPacketHandled(true);
        System.out.println("Packet handled");
    }


}
