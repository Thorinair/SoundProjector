package net.thorinair.soundprojector.common.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.thorinair.soundprojector.SoundProjector;

public abstract class SpCreativeTab extends CreativeTabs {
    public SpCreativeTab(String name) {
        super(SoundProjector.MODID + "." + name);
    }
}
