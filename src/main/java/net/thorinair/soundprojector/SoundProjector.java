package net.thorinair.soundprojector;

import net.minecraft.client.audio.Sound;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thorinair.soundprojector.common.init.SpBlocks;
import net.thorinair.soundprojector.common.init.SpItems;
import net.thorinair.soundprojector.common.init.SpTileEntities;
import net.thorinair.soundprojector.common.network.SpChannelHandler;
import net.thorinair.soundprojector.common.proxy.IProxy;
import net.thorinair.soundprojector.common.util.SpGui;
import org.apache.logging.log4j.Logger;

@Mod(modid = SoundProjector.MODID, name = SoundProjector.NAME, version = SoundProjector.VERSION)
public class SoundProjector
{
    public static final String MODID = "soundprojector";
    public static final String NAME = "SoundProjector";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "net.thorinair.soundprojector.client.proxy.ClientProxy", serverSide = "net.thorinair.soundprojector.server.proxy.ServerProxy")
    protected static IProxy proxy;

    @Mod.Instance(MODID)
    public static SoundProjector instance;

    public enum GUI_ENUM {
        SOUND_PROJECTOR
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(SoundProjector.MODID + ":preInit");
        proxy.preInitialization();
        SpChannelHandler.init();
        SpBlocks.init();
        SpItems.init();
        SpTileEntities.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(SoundProjector.MODID + ":init");
        NetworkRegistry.INSTANCE.registerGuiHandler(SoundProjector.instance, new SpGui());
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event) {
        System.out.println(SoundProjector.MODID + ":postInit");
    }
}
