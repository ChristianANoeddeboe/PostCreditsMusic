package angercraft.postcreditsmusic.client;

import angercraft.postcreditsmusic.PostCreditsMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PostCreditsMusic.MOD_ID, value = Dist.CLIENT)
public class ClientHandler {

    private static MusicTicker musicTicker = null;
    private static Minecraft mc = null;
    private static boolean musicPlaying = false;

    public static void playTrack() {
        mc = Minecraft.getInstance();
        musicTicker = mc.getMusicTicker();

        musicTicker.stop();

        mc.player.playSound(PostCreditsMusic.sound, 0.6F, 1.0F);
        musicPlaying = true;
    }

    @SubscribeEvent
    public static void playSound(PlaySoundEvent event) {
        if(musicPlaying && event.getSound().getCategory().equals(SoundCategory.MUSIC)) {
            event.setResultSound(null);
        }
    }
}
