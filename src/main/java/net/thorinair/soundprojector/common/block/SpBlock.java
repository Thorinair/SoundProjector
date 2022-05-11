package net.thorinair.soundprojector.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thorinair.soundprojector.SoundProjector;
import net.thorinair.soundprojector.client.model.SpStateMapper;

import java.util.Optional;
import java.util.function.Function;


public class SpBlock extends Block {

    public SpBlock(String name, CreativeTabs tab, Material material) {
        super(material);
        this.setRegistryName(SoundProjector.MODID, name);
        this.setUnlocalizedName(SoundProjector.MODID + "." + name);
        this.setCreativeTab(tab);
    }

    //@SideOnly(Side.CLIENT)
    //public Optional<SpStateMapper> addStateMapper() {
    //    return Optional.empty();
    //}
//
    //@SideOnly(Side.CLIENT)
    //public Optional<Function<IBakedModel, IBakedModel>> addModelOverride(ResourceLocation path) {
    //    return Optional.empty();
    //}
}
