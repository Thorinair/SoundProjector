package net.thorinair.soundprojector.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thorinair.soundprojector.common.tileentity.TileEntitySoundProjector;

public class ContainerSoundProjector extends Container {

    private final TileEntitySoundProjector tileSoundProjector;

    public ContainerSoundProjector(TileEntitySoundProjector tileSoundProjector) {
        this.tileSoundProjector = tileSoundProjector;
    }

    @Override
    public void addListener(IContainerListener craft) {
        super.addListener(craft);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        super.updateProgressBar(id, value);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileSoundProjector.isUsableByPlayer(player);
    }

    @Override
    public void putStackInSlot(int slotID, ItemStack itemStack) {
        // Fix for a weird crash with some mods when flying and opening the UI.
        if (inventorySlots.size() > slotID)
            this.getSlot(slotID).putStack(itemStack);
    }
}