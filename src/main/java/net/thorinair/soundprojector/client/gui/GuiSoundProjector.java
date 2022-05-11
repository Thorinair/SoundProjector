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
    private static final String GUI_STRING_SOUND_RANGE = "soundprojector.container.sound_projector.label_sound_range";
    private static final String GUI_STRING_SOUND_DISTANCE = "soundprojector.container.sound_projector.label_sound_distance";
    private static final String GUI_STRING_SOUND_LOOP = "soundprojector.container.sound_projector.label_sound_loop";

    private TileEntitySoundProjector tileSoundProjector;

    private GuiTextField textSoundName;
    private GuiTextField textSoundRange;
    private GuiTextField textSoundDistance;

    private static final ResourceLocation SOUND_PROJECTOR_RESOURCE = new ResourceLocation(SoundProjector.MODID,"textures/gui/sound_projector.png");

    public GuiSoundProjector(TileEntitySoundProjector tileSoundProjector) {
        super(new ContainerSoundProjector(tileSoundProjector));

        this.tileSoundProjector = tileSoundProjector;
    }

    @Override
    public void initGui() {
        super.initGui();

        textSoundName = new GuiTextField(0, this.fontRenderer, width / 2 - GUI_SIZE_X / 2 + 70, height / 2 - GUI_SIZE_Y / 2 + 22, 98, 12);
        textSoundName.setMaxStringLength(24);
        textSoundName.setTextColor(0xFFFFFF);
        textSoundName.setText(tileSoundProjector.getSoundName());

        textSoundRange = new GuiTextField(1, this.fontRenderer, width / 2 - GUI_SIZE_X / 2 + 70, height / 2 - GUI_SIZE_Y / 2 + 38, 27, 12);
        textSoundRange.setMaxStringLength(3);
        textSoundRange.setTextColor(0xFFFFFF);
        textSoundRange.setText(Integer.toString(tileSoundProjector.getSoundRange()));

        textSoundDistance = new GuiTextField(2, this.fontRenderer, width / 2 - GUI_SIZE_X / 2 + 70, height / 2 - GUI_SIZE_Y / 2 + 54, 27, 12);
        textSoundDistance.setMaxStringLength(3);
        textSoundDistance.setTextColor(0xFFFFFF);
        textSoundDistance.setText(Integer.toString(tileSoundProjector.getSoundDistance()));

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

        String labelSoundRange = I18n.format(GUI_STRING_SOUND_RANGE);
        fontRenderer.drawString(labelSoundRange, GUI_SIZE_X / 2 - 80, 78, 0x404040);

        String labelSoundDistance = I18n.format(GUI_STRING_SOUND_DISTANCE);
        fontRenderer.drawString(labelSoundDistance, GUI_SIZE_X / 2 - 80, 94, 0x404040);

        String labelSoundLoop = I18n.format(GUI_STRING_SOUND_LOOP);
        fontRenderer.drawString(labelSoundLoop, GUI_SIZE_X / 2 - 80, 110, 0x404040);

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
            processTextSoundRange();
            processTextSoundDistance();
            super.keyTyped(c, key);
        }

        if (textSoundName.isFocused()) {
            textSoundName.textboxKeyTyped(c, key);
            tileSoundProjector.setSoundName(textSoundName.getText());
            updateSoundProjector();
        }
        else if (textSoundRange.isFocused()) {
            if (!Character.isLetter(c))
                textSoundRange.textboxKeyTyped(c, key);
        }
        else if (textSoundDistance.isFocused()) {
            if (!Character.isLetter(c))
                textSoundDistance.textboxKeyTyped(c, key);
        }
        else {
            super.keyTyped(c, key);
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        textSoundName.updateCursorCounter();
        textSoundRange.updateCursorCounter();
        textSoundDistance.updateCursorCounter();
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
        super.drawScreen(par1, par2, par3);
        textSoundName.drawTextBox();
        textSoundRange.drawTextBox();
        textSoundDistance.drawTextBox();
    }

    @Override
    protected void mouseClicked(int x, int y, int btn) throws IOException {
        super.mouseClicked(x, y, btn);

        textSoundName.mouseClicked(x, y, btn);
        textSoundRange.mouseClicked(x, y, btn);
        textSoundDistance.mouseClicked(x, y, btn);

        if (x > (width - GUI_SIZE_X) / 2 + 69 &&
                x < (width - GUI_SIZE_X) / 2 + 82 &&
                y > (height - GUI_SIZE_Y) / 2 + 69 &&
                y < (height - GUI_SIZE_Y) / 2 + 82) {
            tileSoundProjector.setSoundLoop(!tileSoundProjector.getSoundLoop());
            updateSoundProjector();
        }

        if (!textSoundRange.isFocused())
            processTextSoundRange();
        if (!textSoundRange.isFocused())
            processTextSoundDistance();
    }

    private void processTextSoundRange() {
        int soundRange = 0;

        if (!textSoundRange.getText().equals(""))
            soundRange = Integer.parseInt(textSoundRange.getText());

        if (soundRange > SOUND_RANGE_MAX)
            soundRange = SOUND_RANGE_MAX;
        else if (soundRange < SOUND_RANGE_MIN)
            soundRange = SOUND_RANGE_MIN;

        textSoundRange.setText(Integer.toString(soundRange));
        tileSoundProjector.setSoundRange(soundRange);
        updateSoundProjector();
    }

    private void processTextSoundDistance() {
        int soundDistance = 0;

        if (!textSoundDistance.getText().equals(""))
            soundDistance = Integer.parseInt(textSoundDistance.getText());

        if (soundDistance > SOUND_DISTANCE_MAX)
            soundDistance = SOUND_DISTANCE_MAX;
        else if (soundDistance < SOUND_DISTANCE_MIN)
            soundDistance = SOUND_DISTANCE_MIN;

        textSoundDistance.setText(Integer.toString(soundDistance));
        tileSoundProjector.setSoundDistance(soundDistance);
        updateSoundProjector();
    }

    private void updateSoundProjector() {
        tileSoundProjector.sendUpdates();
        BlockPos pos = tileSoundProjector.getPos();
        SpNetworkHelper.updateSoundProjector(
                pos.getX(), pos.getY(), pos.getZ(),
                tileSoundProjector.getSoundName(), tileSoundProjector.getSoundRange(), tileSoundProjector.getSoundDistance(), tileSoundProjector.getSoundLoop()
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
