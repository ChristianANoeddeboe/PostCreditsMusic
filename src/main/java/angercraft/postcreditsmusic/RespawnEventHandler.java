package angercraft.postcreditsmusic;

import angercraft.postcreditsmusic.client.ClientHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = PostCreditsMusic.MOD_ID)
public class RespawnEventHandler {

    private static Field seenCreditsField = null;

    @SubscribeEvent
    public static void play(PlayerEvent.PlayerRespawnEvent event) {
        System.out.println(event.isEndConquered());
        PlayerEntity playerEntity = event.getPlayer();
        if(playerEntity instanceof ServerPlayerEntity && event.isEndConquered()) {
            ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
            boolean seenCredits = false;
            if(seenCreditsField == null) {
                seenCreditsField = ObfuscationReflectionHelper.findField(ServerPlayerEntity.class, "field_192040_cp");
            }
            try {
                seenCredits = seenCreditsField.getBoolean(player);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(seenCredits) {
                ClientHandler.playTrack();
            }
        }
        /*if(ClientHandler.played == false) {
            ClientHandler.shouldPlayMusic = true;
        }*/
        //event.isEndConquered()
    }
}
