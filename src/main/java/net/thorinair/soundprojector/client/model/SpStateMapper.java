package net.thorinair.soundprojector.client.model;

import com.google.common.collect.Maps;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thorinair.soundprojector.SoundProjector;

import java.util.Map;

@SideOnly(Side.CLIENT)
public class SpStateMapper extends StateMapperBase
{
    /**
     * All the possible model paths that can be used
     */
    public final ResourceLocation[] paths;
    /**
     * All the properties which should be removed
     */
    public final IProperty[] properties;

    public SpStateMapper(String model, IProperty... properties)
    {
        this(new ResourceLocation[] { new ResourceLocation(SoundProjector.MODID, model) }, properties);
    }

    protected SpStateMapper(ResourceLocation[] paths, IProperty... properties)
    {
        this.paths = paths;
        this.properties = properties;
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state)
    {
        Map<IProperty<?>, Comparable<? >> map = Maps.newLinkedHashMap(state.getProperties());
        // Remove all the supplied properties
        for(IProperty property : this.properties) map.remove(property);
        // Rename the variant's model path
        return new ModelResourceLocation(this.getPathForState(state), this.getPropertyString(map));
    }

    /**
     * Returns the model path which should be used for the given state
     */
    public ResourceLocation getPathForState(IBlockState state)
    {
        return this.paths[0];
    }

    /**
     * Returns the model path which should be used for the given item block
     */
    public ResourceLocation getPathForItem(ItemBlock item)
    {
        return this.paths[0];
    }
}
