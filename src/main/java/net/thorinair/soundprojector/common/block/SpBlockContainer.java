package net.thorinair.soundprojector.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.world.World;
import net.thorinair.soundprojector.SoundProjector;
import net.thorinair.soundprojector.common.tileentity.TileEntitySoundProjector;

import javax.annotation.Nullable;


public class SpBlockContainer extends BlockContainer {

    public SpBlockContainer(String name, CreativeTabs tab, Material material) {
        super(material);
        this.setRegistryName(SoundProjector.MODID, name);
        this.setUnlocalizedName(SoundProjector.MODID + "." + name);
        this.setCreativeTab(tab);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        TileEntitySoundProjector tileSoundProjector = new TileEntitySoundProjector();
        return null;
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
