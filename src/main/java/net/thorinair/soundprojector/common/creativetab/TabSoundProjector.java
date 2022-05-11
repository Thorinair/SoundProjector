package net.thorinair.soundprojector.common.creativetab;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.thorinair.soundprojector.common.init.SpBlocks;

public class TabSoundProjector extends SpCreativeTab {
    public TabSoundProjector() {
        super("soundprojector");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(SpBlocks.sound_projector));
    }
}
