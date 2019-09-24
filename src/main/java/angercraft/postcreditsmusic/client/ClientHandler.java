package angercraft.postcreditsmusic.client;

import angercraft.postcreditsmusic.PostCreditsMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.gui.screen.WinGameScreen;
import net.minecraft.client.gui.screen.WorkingScreen;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PostCreditsMusic.MOD_ID, value = Dist.CLIENT)
public class ClientHandler {

    private static MusicTicker musicTicker = null;
    private static Minecraft mc = null;
    private static boolean musicPlaying = false;
    private static boolean creditsHasBeenShown = false;

    public static void playTrack() {
        if(creditsHasBeenShown) {
            mc = Minecraft.getInstance();
            musicTicker = mc.getMusicTicker();

            musicTicker.stop();

            mc.player.playSound(PostCreditsMusic.sound, 0.5F, 1.0F);
            musicPlaying = true;
            creditsHasBeenShown = false;
        }
    }

    @SubscribeEvent
    public static void onGuiShown(GuiOpenEvent event) {
        if(event.getGui() instanceof WinGameScreen) {
            creditsHasBeenShown = true;
        }
        if(musicPlaying && event.getGui() instanceof MainMenuScreen) {
            musicPlaying = false;
        }
    }

    @SubscribeEvent
    public static void playSound(PlaySoundEvent event) {
        if(musicPlaying && event.getSound().getCategory().equals(SoundCategory.MUSIC)) {
            event.setResultSound(null);
        }
    }
}
