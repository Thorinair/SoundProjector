package net.thorinair.soundprojector.common.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.thorinair.soundprojector.common.item.SpItem;

import java.util.ArrayList;
import java.util.List;

public final class SpItems {
    private static List<SpItem> items = new ArrayList<>();

    public static Item

    directional_loudspeaker;

    public static void init() {
        directional_loudspeaker = add(new SpItem("directional_loudspeaker", SpCreativeTabs.soundprojector));
    }

    private SpItems() {}

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry registry = event.getRegistry();
        for (SpItem item : items) {
            registry.registerAll(item);
        }
    }

    public static void registerRenders(ModelRegistryEvent event) {
        for (SpItem item : items) {
            registerRender(item);
        }
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    /**
     * Add the given item for automatic registry
     */
    public static SpItem add(SpItem item) {
        items.add(item);
        return item;
    }
}
