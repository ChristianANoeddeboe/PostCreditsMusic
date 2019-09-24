package angercraft.postcreditsmusic;

import angercraft.postcreditsmusic.network.ModNetworkHandler;
import angercraft.postcreditsmusic.network.PlayTrackPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.network.NetworkDirection;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = PostCreditsMusic.MOD_ID)
public class RespawnEventHandler {

    private final static Field seenCreditsField = ObfuscationReflectionHelper.findField(ServerPlayerEntity.class, "field_192040_cp");

    @SubscribeEvent
    public static void play(PlayerEvent.PlayerRespawnEvent event) {
        System.out.println(event.isEndConquered());
        PlayerEntity playerEntity = event.getPlayer();
        if(playerEntity instanceof ServerPlayerEntity && event.isEndConquered()) {
            ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
            boolean seenCredits = false;
            try {
                seenCredits = seenCreditsField.getBoolean(player);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(seenCredits) {
                ModNetworkHandler.channel.sendTo(new PlayTrackPacket(), player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
            }
        }
    }
}
