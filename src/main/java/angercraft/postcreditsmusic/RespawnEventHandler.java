package angercraft.postcreditsmusic;

import angercraft.postcreditsmusic.network.ModNetworkHandler;
import angercraft.postcreditsmusic.network.PlayTrackPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = PostCreditsMusic.MOD_ID)
public class RespawnEventHandler {

    @SubscribeEvent
    public static void play(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEntity playerEntity = event.getPlayer();
        if(playerEntity instanceof ServerPlayerEntity && event.isEndConquered()) {
            ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
            ModNetworkHandler.channel.sendTo(new PlayTrackPacket(), player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }
}
