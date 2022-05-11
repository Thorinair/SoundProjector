package net.thorinair.soundprojector.common.network;

public class SpNetworkHelper {
    public static void updateSoundProjector(int x, int y, int z, String soundName, int soundRadius, int soundOffset, boolean soundLoop) {
        SpChannelHandler.network.sendToServer(new SpPacketClientSound(x, y, z, soundName, soundRadius, soundOffset, soundLoop));
    }
}
