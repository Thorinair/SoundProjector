package net.thorinair.soundprojector.common.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thorinair.soundprojector.SoundProjector;
import net.thorinair.soundprojector.client.gui.GuiSoundProjector;
import net.thorinair.soundprojector.common.container.ContainerSoundProjector;
import net.thorinair.soundprojector.common.tileentity.TileEntitySoundProjector;

public class SpGui implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity != null) {
            if (ID == SoundProjector.GUI_ENUM.SOUND_PROJECTOR.ordinal())
            {
                return new ContainerSoundProjector((TileEntitySoundProjector)tileEntity);
            }
        }

        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity != null) {
            if (ID == SoundProjector.GUI_ENUM.SOUND_PROJECTOR.ordinal()) {
                return new GuiSoundProjector((TileEntitySoundProjector)tileEntity);
            }
        }
        return null;
    }
}