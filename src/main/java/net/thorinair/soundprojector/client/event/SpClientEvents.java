package net.thorinair.soundprojector.client.event;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.thorinair.soundprojector.SoundProjector;
import net.thorinair.soundprojector.common.init.SpBlocks;
import net.thorinair.soundprojector.common.init.SpItems;

import java.util.HashMap;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = SoundProjector.MODID, value = Side.CLIENT)
public final class SpClientEvents
{
    private SpClientEvents() {}

    //@SubscribeEvent
    //public static void registerModels(ModelRegistryEvent event)
    //{
    //    //SpBlocks.registerModels();
    //    //SpItems.registerModels();
    //}

    //private static HashMap<ResourceLocation, Function<IBakedModel, IBakedModel>> overrides = new HashMap<>();

    //@SubscribeEvent
    //public static void onModelBake(ModelBakeEvent event)
    //{
    //    IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();
    //    for (ModelResourceLocation resource : registry.getKeys())
    //    {
    //        ResourceLocation key = new ResourceLocation(resource.getResourceDomain(), resource.getResourcePath());
    //        // For every supplied model path we wrap its existing model via the corresponding function
    //        if(overrides.containsKey(key)) registry.putObject(resource, overrides.get(key).apply(registry.getObject(resource)));
    //    }
    //}
//
    //public static void addModelOverride(ResourceLocation resource, Function<IBakedModel, IBakedModel> override)
    //{
    //    overrides.put(resource, override);
    //}
}
