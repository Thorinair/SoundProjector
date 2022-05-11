package net.thorinair.soundprojector.common.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.thorinair.soundprojector.client.event.SpClientEvents;
import net.thorinair.soundprojector.client.model.SpStateMapper;
import net.thorinair.soundprojector.common.block.BlockSoundProjector;
import net.thorinair.soundprojector.common.block.SpBlock;
import net.thorinair.soundprojector.common.item.SpItemBlock;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class SpBlocks {
    private static List<SpBlock> blocks = new ArrayList<>();

    public static Block sound_projector;

    public static void init() {
        sound_projector = add(new BlockSoundProjector());
    }

    private SpBlocks() {}

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry registry = event.getRegistry();
        for(SpBlock block : blocks) {
			registry.registerAll(block);
			//System.out.println("Information!");
		}
    }

    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        IForgeRegistry registry = event.getRegistry();
        for(SpBlock block : blocks) {
            registry.registerAll(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }

    public static void registerRenders(ModelRegistryEvent event) {
        for(SpBlock block : blocks) {
            registerRender(Item.getItemFromBlock(block));
        }
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    //@SideOnly(Side.CLIENT)
    //public static void registerModels() {
    //	for(SpBlock block : blocks)
    //	{
    //		// Register a state mapper and add a custom model override to all of its models if the block provides these
    //		Optional<SpStateMapper> mapper = block.addStateMapper();
    //		if(mapper.isPresent())
    //		{
    //			ModelLoader.setCustomStateMapper(block, mapper.get());
    //			for(ResourceLocation path : mapper.get().paths)
    //			{
    //				Optional<Function<IBakedModel, IBakedModel>> override = block.addModelOverride(path);
    //				if(override.isPresent()) SpClientEvents.addModelOverride(path, override.get());
    //			}
    //		}
    //		else
    //		{
    //			Optional<Function<IBakedModel, IBakedModel>> override = block.addModelOverride(block.getRegistryName());
    //			if(override.isPresent()) SpClientEvents.addModelOverride(block.getRegistryName(), override.get());
    //		}
    //	}
    //}

    //@SideOnly(Side.CLIENT)
    //public static void registerColors(ColorHandlerEvent.Block event) {
    //	BlockColors registry = event.getBlockColors();
    //	for (HexBlock block : blocks)
    //	{
    //		// Register a color handler if the block provides one
    //		Optional<IBlockColor> color = block.addBlockColor();
    //		if(color.isPresent())
    //			registry.registerBlockColorHandler(color.get(), block);
    //	}
    //}

    /**
     * Add the given block for automatic registry
     */
    public static SpBlock add(SpBlock block) {
        blocks.add(block);
        //SpItems.add(new SpItemBlock(block));
        return block;
    }
}
