package net.thorinair.soundprojector.common.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.thorinair.soundprojector.common.item.SpItem;
import net.thorinair.soundprojector.common.item.SpItemBlock;

import java.util.ArrayList;
import java.util.List;

public final class SpItems {
    private static List<SpItem> items = new ArrayList<>();
    private static List<SpItemBlock> itemBlocks = new ArrayList<>();

    //public static final Item
//
    //hexorium_crystal_red = add(new ItemHexoriumCrystal(EHexColor.RED)),
    //hexorium_crystal_green = add(new ItemHexoriumCrystal(EHexColor.GREEN)),
    //hexorium_crystal_blue = add(new ItemHexoriumCrystal(EHexColor.BLUE)),
    //hexorium_crystal_white = add(new ItemHexoriumCrystal(EHexColor.WHITE)),
    //hexorium_crystal_black = add(new ItemHexoriumCrystal(EHexColor.BLACK)),
//
    //hexorium_reinforcer = add(new ItemHexoriumReinforcer()),
//
    //hexorium_glass_package = add(new HexItem("hexorium_glass_package", HexCreativeTabs.components));

    private SpItems() {}

    public static void register(RegistryEvent.Register<Item> event) {
        IForgeRegistry registry = event.getRegistry();
        for (SpItem item : items)
            registry.register(item);
        for (SpItemBlock item : itemBlocks)
            registry.register(item);
    }

    //@SideOnly(Side.CLIENT)
    //public static void registerModels()
    //{
    //    for (SpItem item : items) {
    //        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    //        // Add a custom model override if the item provides one
    //        //Optional<Function<IBakedModel, IBakedModel>> override = item.addModelOverride();
    //        //if (override.isPresent())
    //        //	HexClientEvents.addModelOverride(item.getRegistryName(), override.get());
    //    }
    //    for (SpItemBlock item : itemBlocks)
    //    {
    //        //Optional<HexStateMapper> mapper = ((SoundProjectorBlock) item.getBlock()).addStateMapper();
    //        // Register a state mapper if the the item's block has one
    //        //ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(mapper.isPresent() ? mapper.get().getPathForItem(item) : item.getRegistryName(), "inventory"));
    //    }
    //}

    //@SideOnly(Side.CLIENT)
    //public static void registerColors(ColorHandlerEvent.Item event)
    //{
    //    ItemColors registry = event.getItemColors();
    //    for (HexItemBlock item : itemBlocks)
    //    {
    //        // Register a color handler if the item's block has one
    //        Optional<IItemColor> color = ((HexBlock) item.getBlock()).addItemColor();
    //        if(color.isPresent())
    //            registry.registerItemColorHandler(color.get(), item);
    //    }
    //}

    /**
     * Add the given item for automatic registry
     */
    public static SpItem add(SpItem item) {
        items.add(item);
        return item;
    }

    /**
     * Add the given item block for automatic registry
     */
    public static SpItemBlock add(SpItemBlock item) {
        itemBlocks.add(item);
        return item;
    }
}
