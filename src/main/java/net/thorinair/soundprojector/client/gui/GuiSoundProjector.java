package net.thorinair.soundprojector.client.gui;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.thorinair.soundprojector.SoundProjector;
import net.thorinair.soundprojector.common.container.ContainerSoundProjector;
import net.thorinair.soundprojector.common.network.SpNetworkHelper;
import net.thorinair.soundprojector.common.tileentity.TileEntitySoundProjector;

import java.io.IOException;

import static net.thorinair.soundprojector.common.tileentity.TileEntitySoundProjector.*;

public class GuiSoundProjector extends GuiContainer implements IContainerListener {

    // GUI Sizes
    private static final int GUI_SIZE_X = 176;
    private static final int GUI_SIZE_Y = 90;

    // GUI Strings
    private static final String GUI_STRING_SOUND_NAME = "soundprojector.container.sound_projector.label_sound_name";
    private static final String GUI_STRING_SOUND_RADIUS = "soundprojector.container.sound_projector.label_sound_radius";
    private static final String GUI_STRING_SOUND_OFFSET = "soundprojector.container.sound_projector.label_sound_offset";
    private static final String GUI_STRING_SOUND_LOOP = "soundprojector.container.sound_projector.label_sound_loop";

    private TileEntitySoundProjector tileSoundProjector;

    private GuiTextField textSoundName;
    private GuiTextField textSoundRadius;
    private GuiTextField textSoundOffset;

    private static final ResourceLocation SOUND_PROJECTOR_RESOURCE = new ResourceLocation(SoundProjector.MODID,"textures/gui/sound_projector.png");

    public GuiSoundProjector(TileEntitySoundProjector tileSoundProjector) {
        super(new ContainerSoundProjector(tileSoundProjector));

        this.tileSoundProjector = tileSoundProjector;
    }

    @Override
    public void initGui() {
        super.initGui();

        textSoundName = new GuiTextField(0, this.fontRenderer, GUI_SIZE_X / 2 - 18, 60, 98, 12);
        textSoundName.setMaxStringLength(24);
        textSoundName.setTextColor(0xFFFFFF);
        textSoundName.setVisible(true);
        textSoundName.setText(tileSoundProjector.getSoundName());

        textSoundRadius = new GuiTextField(1, this.fontRenderer, GUI_SIZE_X / 2 - 18, 76, 27, 12);
        textSoundRadius.setMaxStringLength(3);
        textSoundRadius.setTextColor(0xFFFFFF);
        textSoundRadius.setVisible(true);
        textSoundRadius.setText(Integer.toString(tileSoundProjector.getSoundRadius()));

        textSoundOffset = new GuiTextField(2, this.fontRenderer, GUI_SIZE_X / 2 - 18, 92, 27, 12);
        textSoundOffset.setMaxStringLength(3);
        textSoundOffset.setTextColor(0xFFFFFF);
        textSoundOffset.setVisible(true);
        textSoundOffset.setText(Integer.toString(tileSoundProjector.getSoundOffset()));

        textSoundName.setFocused(true);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        // Draw the name
        String name = I18n.format(TileEntitySoundProjector.MACHINE_NAME);
        fontRenderer.drawString(name, GUI_SIZE_X / 2 - fontRenderer.getStringWidth(name) / 2, 44, 0x404040);

        // Draw labels
        String labelSoundName = I18n.format(GUI_STRING_SOUND_NAME);
        fontRenderer.drawString(labelSoundName, GUI_SIZE_X / 2 - 80, 62, 0x404040);

        String labelSoundRadius = I18n.format(GUI_STRING_SOUND_RADIUS);
        fontRenderer.drawString(labelSoundRadius, GUI_SIZE_X / 2 - 80, 78, 0x404040);

        String labelSoundOffset = I18n.format(GUI_STRING_SOUND_OFFSET);
        fontRenderer.drawString(labelSoundOffset, GUI_SIZE_X / 2 - 80, 94, 0x404040);

        String labelSoundLoop = I18n.format(GUI_STRING_SOUND_LOOP);
        fontRenderer.drawString(labelSoundLoop, GUI_SIZE_X / 2 - 80, 110, 0x404040);

        textSoundName.drawTextBox();
        textSoundRadius.drawTextBox();
        textSoundOffset.drawTextBox();

        //super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        // Bind the texture of the GUI.
        // Prepare x and y values (top left corner).
        int x = (width - GUI_SIZE_X) / 2;
        int y = (height - GUI_SIZE_Y) / 2;

        // Draw the background of GUI.
        mc.getTextureManager().bindTexture(SOUND_PROJECTOR_RESOURCE);
        drawTexturedModalRect(x, y, 0, 0, GUI_SIZE_X, GUI_SIZE_Y);

        if (tileSoundProjector.getSoundLoop())
            drawTexturedModalRect(x + 73, y + 73, GUI_SIZE_X, 0, 6, 6);
    }

    @Override
    protected void keyTyped(char c, int key) throws IOException {
        if (key == 1) {
            processTextSoundRadius();
            processTextSoundOffset();
            super.keyTyped(c, key);
        }

        if (textSoundName.isFocused()) {
            textSoundName.textboxKeyTyped(c, key);
            tileSoundProjector.setSoundName(textSoundName.getText());
            updateSoundProjector();
        }
        else if (textSoundRadius.isFocused()) {
            if (!Character.isLetter(c))
                textSoundRadius.textboxKeyTyped(c, key);
        }
        else if (textSoundOffset.isFocused()) {
            if (!Character.isLetter(c))
                textSoundOffset.textboxKeyTyped(c, key);
        }
        else {
            super.keyTyped(c, key);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        textSoundName.updateCursorCounter();
        textSoundRadius.updateCursorCounter();
        textSoundOffset.updateCursorCounter();
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        super.drawScreen(par1, par2, par3);
    }

    @Override
    protected void mouseClicked(int x, int y, int btn) throws IOException {
        super.mouseClicked(x, y, btn);

        textSoundName.mouseClicked(x - (width - GUI_SIZE_X) / 2 + 3, y - (height - GUI_SIZE_Y) / 2 + 38, btn);
        textSoundRadius.mouseClicked(x - (width - GUI_SIZE_X) / 2 + 3, y - (height - GUI_SIZE_Y) / 2 + 38, btn);
        textSoundOffset.mouseClicked(x - (width - GUI_SIZE_X) / 2 + 3, y - (height - GUI_SIZE_Y) / 2 + 38, btn);

        if (x > (width - GUI_SIZE_X) / 2 + 69 &&
                x < (width - GUI_SIZE_X) / 2 + 82 &&
                y > (height - GUI_SIZE_Y) / 2 + 69 &&
                y < (height - GUI_SIZE_Y) / 2 + 82) {
            tileSoundProjector.setSoundLoop(!tileSoundProjector.getSoundLoop());
            updateSoundProjector();
        }

        if (!textSoundRadius.isFocused())
            processTextSoundRadius();
        if (!textSoundRadius.isFocused())
            processTextSoundOffset();
    }

    private void processTextSoundRadius() {
        int soundRadius = 0;

        if (!textSoundRadius.getText().equals(""))
            soundRadius = Integer.parseInt(textSoundRadius.getText());

        if (soundRadius > SOUND_RADIUS_MAX)
            soundRadius = SOUND_RADIUS_MAX;
        else if (soundRadius < SOUND_RADIUS_MIN)
            soundRadius = SOUND_RADIUS_MIN;

        textSoundRadius.setText(Integer.toString(soundRadius));
        tileSoundProjector.setSoundRadius(soundRadius);
        updateSoundProjector();
    }

    private void processTextSoundOffset() {
        int soundOffset = 0;

        if (!textSoundOffset.getText().equals(""))
            soundOffset = Integer.parseInt(textSoundOffset.getText());

        if (soundOffset > SOUND_OFFSET_MAX)
            soundOffset = SOUND_OFFSET_MAX;
        else if (soundOffset < SOUND_OFFSET_MIN)
            soundOffset = SOUND_OFFSET_MIN;

        textSoundOffset.setText(Integer.toString(soundOffset));
        tileSoundProjector.setSoundOffset(soundOffset);
        updateSoundProjector();
    }

    private void updateSoundProjector() {
        tileSoundProjector.sendUpdates();
        BlockPos pos = tileSoundProjector.getPos();
        SpNetworkHelper.updateSoundProjector(
                pos.getX(), pos.getY(), pos.getZ(),
                tileSoundProjector.getSoundName(), tileSoundProjector.getSoundRadius(), tileSoundProjector.getSoundOffset(), tileSoundProjector.getSoundLoop()
        );
    }


    @Override
    public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList) {
    }

    @Override
    public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack) {
    }

    @Override
    public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue) {
    }

    @Override
    public void sendAllWindowProperties(Container containerIn, IInventory inventory) {
    }
}
