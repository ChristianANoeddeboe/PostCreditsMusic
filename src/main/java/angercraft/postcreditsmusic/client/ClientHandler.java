package angercraft.postcreditsmusic.client;

import angercraft.postcreditsmusic.PostCreditsMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundEngine;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.screen.WinGameScreen;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.Map;

public class ClientHandler {

    private static boolean musicPlaying = false;
    private static boolean creditsHasBeenShown = false;
    private static Field soundEngineField = ObfuscationReflectionHelper.findField(SoundHandler.class, "field_147694_f");
    private static Field playingSoundsStopTimeField = ObfuscationReflectionHelper.findField(SoundEngine.class, "field_148624_n");
    private static ISound sound = null;
    private static Map<ISound, Integer> playingSoundsStopTime = null;
    private static SoundEngine soundEngine = null;

    public static void playTrack() {
        if(creditsHasBeenShown) {
            MinecraftForge.EVENT_BUS.register(ClientHandler.class);
            Minecraft mc = Minecraft.getInstance();
            MusicTicker musicTicker = mc.getMusicTicker();

            musicTicker.stop();

            mc.player.playSound(PostCreditsMusic.sound, 0.5F, 1.0F);
            musicPlaying = true;
            creditsHasBeenShown = false;

            SoundHandler soundHandler = mc.getSoundHandler();

            try {
                soundEngine = (SoundEngine) soundEngineField.get(soundHandler);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                playingSoundsStopTime = (Map<ISound, Integer>) playingSoundsStopTimeField.get(soundEngine);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        if(playingSoundsStopTime != null && sound != null) {
            if(!playingSoundsStopTime.containsKey(sound)) {
                musicPlaying = false;
                System.out.println("Other sounds can play again");
                MinecraftForge.EVENT_BUS.unregister(ClientHandler.class);
            }
        }
    }

    @SubscribeEvent
    public static void playSound(PlaySoundEvent event) {
        if(musicPlaying && event.getSound().getCategory().equals(SoundCategory.MUSIC)) {
            event.setResultSound(null);
        }
        if(event.getSound().getSoundLocation().getNamespace().equals(PostCreditsMusic.sound.getName().getNamespace())) {
            sound = event.getSound();
        }
    }

    public static void setCreditsHasBeenShown(boolean creditsHasBeenShown) {
        ClientHandler.creditsHasBeenShown = creditsHasBeenShown;
    }
}
