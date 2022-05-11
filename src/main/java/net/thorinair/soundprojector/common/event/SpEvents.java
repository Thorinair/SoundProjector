package net.thorinair.soundprojector.common.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thorinair.soundprojector.SoundProjector;
import net.thorinair.soundprojector.common.init.SpBlocks;
import net.thorinair.soundprojector.common.init.SpItems;

@Mod.EventBusSubscriber(modid = SoundProjector.MODID)
public final class SpEvents
{
    private SpEvents() {}

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        SpBlocks.registerBlocks(event);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        SpBlocks.registerItemBlocks(event);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerRenders(ModelRegistryEvent event) {
        SpBlocks.registerRenders(event);
        SpItems.registerRenders(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        SpItems.registerItems(event);
    }
}
