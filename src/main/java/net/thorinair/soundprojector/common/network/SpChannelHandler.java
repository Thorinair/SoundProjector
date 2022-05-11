package net.thorinair.soundprojector.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.thorinair.soundprojector.SoundProjector;

public class SpChannelHandler {
    public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(SoundProjector.MODID);

    public static void init() {
        network.registerMessage(SpPacketClientSound.class, SpPacketClientSound.class, 7, Side.SERVER);
    }
}
