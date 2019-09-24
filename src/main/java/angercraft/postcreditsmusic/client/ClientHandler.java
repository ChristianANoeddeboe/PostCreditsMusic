package angercraft.postcreditsmusic.client;

import angercraft.postcreditsmusic.PostCreditsMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = PostCreditsMusic.MOD_ID, value = Dist.CLIENT)
public class ClientHandler {

    //private static final Field timeUntilNextMusic = ObfuscationReflectionHelper.findField(MusicTicker.class, "field_147676_d");
    private static MusicTicker musicTicker = null;
    private static Minecraft mc = null;
    private static boolean musicPlaying = false;

    public static void playTrack() {
        mc = Minecraft.getInstance();
        musicTicker = mc.getMusicTicker();

        musicTicker.stop();

        /*musicTicker = mc.getMusicTicker();
        try {
            timeUntilNextMusic.setInt(musicTicker, 1000000);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/

        mc.player.playSound(PostCreditsMusic.sound, 0.6F, 1.0F);
        musicPlaying = true;
    }

    //@SubscribeEvent
    /*public static void onTick(TickEvent.ClientTickEvent event){
        if(musicPlaying) {
            mc = Minecraft.getInstance();
            musicTicker = mc.getMusicTicker();
            try {
                timeUntilNextMusic.setInt(musicTicker, 1000000);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }*/

    @SubscribeEvent
    public static void playSound(PlaySoundEvent event) {
        if(musicPlaying && event.getSound().getCategory().equals(SoundCategory.MUSIC)) {
            event.setResultSound(null);
        }
    }
}
