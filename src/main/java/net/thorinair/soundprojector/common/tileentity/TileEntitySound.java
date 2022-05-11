package net.thorinair.soundprojector.common.tileentity;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.tileentity.TileEntity;
import net.thorinair.soundprojector.common.util.SpSound;

public class TileEntitySound extends TileEntity {

    /**** Variables ****/

    // Prepare variables.
    private PositionedSoundRecord sound;

    /**** Common TileEntity Methods ****/

    public TileEntitySound() {
    }

    public void stopSound() {
        if (sound != null) {
            SpSound.stopSound(sound);
            sound = null;
        }
    }

    public void playSound(double x, double y, double z, String soundName, float range, boolean skipCheck) {
        if (sound == null || skipCheck) {
            sound = SpSound.playSound(x, y, z, soundName, range);
        }
    }

    public boolean isPlaying() {
        if (sound == null) {
            return false;
        }
        return SpSound.isPlaying(sound);
    }
}
