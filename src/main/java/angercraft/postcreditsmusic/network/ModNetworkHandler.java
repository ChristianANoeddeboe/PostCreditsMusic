package angercraft.postcreditsmusic.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModNetworkHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel channel;

    public static void registerClientServerMessages() {
        channel = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("corruption","main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals);

        int discriminator = 0;

        //Hvis den her er rød igen senere, så må det være intelliJ, fordi det er sådan her den skal være.
        channel.registerMessage(discriminator++, PlayTrackPacket.class, PlayTrackPacket::encode, PlayTrackPacket::decode, PlayTrackPacket::handle);
    }

    public static <MSG> void send(PacketDistributor.PacketTarget target, MSG message) {
        channel.send(target, message);
    }
}
