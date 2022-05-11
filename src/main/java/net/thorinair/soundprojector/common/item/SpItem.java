package net.thorinair.soundprojector.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.thorinair.soundprojector.SoundProjector;

public class SpItem extends Item {

    public SpItem(String name, CreativeTabs tab) {
        this.setRegistryName(SoundProjector.MODID, name);
        this.setUnlocalizedName(SoundProjector.MODID + "." + name);
        this.setCreativeTab(tab);
    }
}
