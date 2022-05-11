package net.thorinair.soundprojector.common.network;

public class SpNetworkHelper {
    public static void updateSoundProjector(int x, int y, int z, String soundName, int soundRange, int soundDistance, boolean soundLoop) {
        SpChannelHandler.network.sendToServer(new SpPacketClientSound(x, y, z, soundName, soundRange, soundDistance, soundLoop));
    }
}
