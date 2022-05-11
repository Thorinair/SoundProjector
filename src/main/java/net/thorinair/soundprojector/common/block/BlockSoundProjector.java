package net.thorinair.soundprojector.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thorinair.soundprojector.SoundProjector;
import net.thorinair.soundprojector.common.init.SpCreativeTabs;
import net.thorinair.soundprojector.common.tileentity.TileEntitySoundProjector;

import javax.annotation.Nullable;

public class BlockSoundProjector extends SpBlockContainer
{
    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static final PropertyBool POWERED = PropertyBool.create("powered");

    public BlockSoundProjector() {
        super("sound_projector", SpCreativeTabs.soundprojector, Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false));
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySoundProjector();
    }

    /**
     * Called when the block is right clicked by a player.
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileentity = world.getTileEntity(pos);

        if (tileentity instanceof TileEntitySoundProjector) {
            player.openGui(SoundProjector.instance, SoundProjector.GUI_ENUM.SOUND_PROJECTOR.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos position, Block block, BlockPos fromPos) {
        if(!world.isRemote && (state.getValue(POWERED) != world.isBlockIndirectlyGettingPowered(position) > 0)) {
            world.setBlockState(position, state.cycleProperty(POWERED), 2);
            TileEntitySoundProjector tileSoundProjector = (TileEntitySoundProjector) world.getTileEntity(position);
            tileSoundProjector.setPowered(world.isBlockIndirectlyGettingPowered(position) > 0);
            tileSoundProjector.sendUpdates();
        }
    }

    /**
     * Called when the block is broken.
     */
    @Override
    public void breakBlock(World world, BlockPos position, IBlockState state) {
        TileEntitySoundProjector tileSoundProjector = (TileEntitySoundProjector) world.getTileEntity(position);

        if (tileSoundProjector != null) {
            world.removeTileEntity(position);
        }
        super.breakBlock(world, position, state);
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     */
    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(POWERED, (meta & 8) != 0);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(FACING)).getIndex() | (state.getValue(POWERED) ? 8 : 0);
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, POWERED});
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos position, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(position, placer)).withProperty(POWERED, world.isBlockPowered(position));
    }
}
