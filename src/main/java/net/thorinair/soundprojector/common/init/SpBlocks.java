package net.thorinair.soundprojector.common.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.thorinair.soundprojector.common.block.BlockSoundProjector;

import java.util.ArrayList;
import java.util.List;

public final class SpBlocks {
    private static final List<Block> blocks = new ArrayList<>();

    public static Block sound_projector;

    public static void init() {
        sound_projector = add(new BlockSoundProjector());
    }

    private SpBlocks() {}

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry registry = event.getRegistry();
        for(Block block : blocks) {
			registry.registerAll(block);
		}
    }

    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        IForgeRegistry registry = event.getRegistry();
        for(Block block : blocks) {
            registry.registerAll(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }

    public static void registerRenders(ModelRegistryEvent event) {
        for(Block block : blocks) {
            registerRender(Item.getItemFromBlock(block));
        }
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    /**
     * Add the given block for automatic registry
     */
    public static Block add(Block block) {
        blocks.add(block);
        //SpItems.add(new SpItemBlock(block));
        return block;
    }
}
