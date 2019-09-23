package angercraft.postcreditsmusic.client;

import angercraft.postcreditsmusic.PostCreditsMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.WinGameScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientHandler {

     static Minecraft mc = Minecraft.getInstance();
     static SoundEvent sound = new SoundEvent(new ResourceLocation(PostCreditsMusic.MOD_ID, "track"));

    public static void playTrack() {
        SoundHandler soundHandler = mc.getSoundHandler();
        soundHandler.pause();
        mc.player.playSound(sound, 1.0F, 1.0F);
    }
}
