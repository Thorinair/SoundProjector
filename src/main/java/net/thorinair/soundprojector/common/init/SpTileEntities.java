package net.thorinair.soundprojector.common.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.thorinair.soundprojector.SoundProjector;
import net.thorinair.soundprojector.common.tileentity.TileEntitySound;
import net.thorinair.soundprojector.common.tileentity.TileEntitySoundProjector;

public class SpTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntitySoundProjector.class, new ResourceLocation(SoundProjector.MODID,"tileEntitySoundProjector"));;
        GameRegistry.registerTileEntity(TileEntitySound.class, new ResourceLocation(SoundProjector.MODID,"tileEntitySound"));;
    }
}
