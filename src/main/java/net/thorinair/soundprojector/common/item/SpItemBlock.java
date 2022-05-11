package net.thorinair.soundprojector.common.item;

import net.minecraft.item.ItemBlock;
import net.thorinair.soundprojector.common.block.SpBlock;

public class SpItemBlock extends ItemBlock
{
    public SpItemBlock(SpBlock block)
    {
        super(block);
        this.setRegistryName(block.getRegistryName());
    }
}
