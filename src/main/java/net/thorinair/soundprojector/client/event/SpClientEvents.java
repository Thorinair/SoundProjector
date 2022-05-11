package net.thorinair.soundprojector.client.event;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.thorinair.soundprojector.SoundProjector;

@Mod.EventBusSubscriber(modid = SoundProjector.MODID, value = Side.CLIENT)
public final class SpClientEvents
{
    private SpClientEvents() {}
}
