package net.thorinair.soundprojector;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.thorinair.soundprojector.common.init.SpBlocks;
import net.thorinair.soundprojector.common.init.SpItems;
import net.thorinair.soundprojector.common.proxy.IProxy;
import org.apache.logging.log4j.Logger;

@Mod(modid = SoundProjector.MODID, name = SoundProjector.NAME, version = SoundProjector.VERSION)
public class SoundProjector
{
    public static final String MODID = "soundprojector";
    public static final String NAME = "SoundProjector";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "net.thorinair.soundprojector.client.proxy.ClientProxy", serverSide = "net.thorinair.soundprojector.server.proxy.ServerProxy")
    protected static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(SoundProjector.MODID + ":preInit");
        proxy.preInitialization();
        SpBlocks.init();
        SpItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println(SoundProjector.MODID + ":init");
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event) {
        System.out.println(SoundProjector.MODID + ":postInit");
    }
}
