package net.thorinair.soundprojector.common.item;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thorinair.soundprojector.SoundProjector;

import java.util.Optional;
import java.util.function.Function;

public class SpItem extends Item {

    public SpItem(String name, CreativeTabs tab) {
        this.setRegistryName(SoundProjector.MODID, name);
        this.setUnlocalizedName(SoundProjector.MODID + "." + name);
        this.setCreativeTab(tab);
    }

    ///**
    // * The item's model override function which wraps or replaces its existing model. Mostly used for fullbright model overrides. Can be left empty to not insert a model
    // */
    //@SideOnly(Side.CLIENT)
    //public Optional<Function<IBakedModel, IBakedModel>> addModelOverride()
    //{
    //    return Optional.empty();
    //}
}
