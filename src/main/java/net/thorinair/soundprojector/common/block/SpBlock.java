package net.thorinair.soundprojector.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.thorinair.soundprojector.SoundProjector;

public class SpBlock extends Block {

    public SpBlock(String name, CreativeTabs tab, Material material) {
        super(material);
        this.setRegistryName(SoundProjector.MODID, name);
        this.setUnlocalizedName(SoundProjector.MODID + "." + name);
        this.setCreativeTab(tab);
    }
}
