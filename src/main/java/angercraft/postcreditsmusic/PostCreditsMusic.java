package angercraft.postcreditsmusic;

import angercraft.postcreditsmusic.network.ModNetworkHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PostCreditsMusic.MOD_ID)
public class PostCreditsMusic
{

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "postcreditsmusic";

    public static SoundEvent sound;

    public PostCreditsMusic() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModNetworkHandler.registerClientServerMessages();
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerMusic(final RegistryEvent.Register<SoundEvent> event) {
            ResourceLocation resource = new ResourceLocation(PostCreditsMusic.MOD_ID, "track");
            sound = new SoundEvent(resource);
            sound.setRegistryName(MOD_ID, "track");
            event.getRegistry().register(sound);
        }
    }
}
