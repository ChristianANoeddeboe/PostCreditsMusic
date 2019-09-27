package angercraft.postcreditsmusic.client;

import angercraft.postcreditsmusic.PostCreditsMusic;
import net.minecraft.client.gui.screen.WinGameScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PostCreditsMusic.MOD_ID, value = Dist.CLIENT)
public class GuiListener {

    @SubscribeEvent
    public static void onGuiShown(GuiOpenEvent event) {
        if(event.getGui() instanceof WinGameScreen) {
            ClientHandler.setCreditsHasBeenShown(true);
        }
    }
}
