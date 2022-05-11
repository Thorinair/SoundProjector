package net.thorinair.soundprojector.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.thorinair.soundprojector.SoundProjector;

public class SpSound {
    public static final String SOUND_PREFIX = "sound";
    public static final String EFFECT_PREFIX = "effect";
    public static final float DEFAULT_RANGE = 16F;

    public static PositionedSoundRecord playSound(double x, double y, double z, String name, float volume) {
        float range = DEFAULT_RANGE;

        if (volume > 1.0F) {
            range *= volume;
        }

        Entity person = FMLClientHandler.instance().getClient().getRenderViewEntity();

        if (person != null && volume > 0 && person.getDistanceSq(x, y, z) < range * range) {
            ResourceLocation location = new ResourceLocation(SoundProjector.MODID + ":" + SOUND_PREFIX + "-" + name);

            SoundEvent soundEvent = new SoundEvent(location);

            if (soundEvent != null) {
                PositionedSoundRecord sound = new PositionedSoundRecord(soundEvent, SoundCategory.AMBIENT, volume, 1.0F, (float) x, (float) y, (float) z);
                Minecraft.getMinecraft().getSoundHandler().playSound(sound);
                return sound;
            }
        }
        return null;
    }

    public static boolean isPlaying(PositionedSoundRecord sound) {
        return Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound);
    }

    public static void stopSound(PositionedSoundRecord sound) {
        Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
    }
}
