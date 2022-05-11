package net.thorinair.soundprojector.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.thorinair.soundprojector.common.block.BlockSoundProjector;
import net.thorinair.soundprojector.common.util.SpSound;

import javax.annotation.Nullable;

import static net.thorinair.soundprojector.common.block.BlockSoundProjector.FACING;

public class TileEntitySoundProjector extends TileEntity implements ITickable {

    /**** Static Values ****/

    public static final String ID = "tileSoundProjector";

    public static final String MACHINE_NAME = "soundprojector.container.sound_projector";

    // NBT Names
    private static final String NBT_SOUND_NAME = "sound_name";
    private static final String NBT_SOUND_RADIUS = "sound_radius";
    private static final String NBT_SOUND_OFFSET = "sound_offset";
    private static final String NBT_SOUND_LOOP = "sound_loop";

    private static final String NBT_POWERED = "powered";

    public static final int SOUND_RADIUS_MIN = 0;
    public static final int SOUND_RADIUS_MAX = 128;
    public static final int SOUND_OFFSET_MIN = 0;
    public static final int SOUND_OFFSET_MAX = 128;

    /**** Variables ****/

    // Prepare variables.
    private String soundName;
    private int soundRadius;
    private int soundOffset;
    private boolean soundLoop;

    private boolean powered;

    private TileEntitySound sound;

    private boolean firstTickClient;

    /**** Common TileEntity Methods ****/

    public TileEntitySoundProjector() {

        this.soundName = "";
        this.soundRadius = 16;
        this.soundOffset = 8;
        this.soundLoop = true;

        this.powered = false;

        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            sound = new TileEntitySound();
        }
    }

    /**
     * Writes the tags to NBT.
     */
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        // Write the variables.
        tagCompound.setString(NBT_SOUND_NAME, soundName);
        tagCompound.setInteger(NBT_SOUND_RADIUS, soundRadius);
        tagCompound.setInteger(NBT_SOUND_OFFSET, soundOffset);
        tagCompound.setBoolean(NBT_SOUND_LOOP, soundLoop);

        tagCompound.setBoolean(NBT_POWERED, powered);

        return tagCompound;
    }

    /**
     * Reads the tags from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        // Read the variables.
        soundName = tagCompound.getString(NBT_SOUND_NAME);
        soundRadius = tagCompound.getInteger(NBT_SOUND_RADIUS);
        soundOffset = tagCompound.getInteger(NBT_SOUND_OFFSET);
        soundLoop = tagCompound.getBoolean(NBT_SOUND_LOOP);

        powered = tagCompound.getBoolean(NBT_POWERED);
    }

    /**
     * Sends the data packet.
     */
    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    /**
     * Receives the data packet.
     */
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        readFromNBT(packet.getNbtCompound());
        firstTickClient = true;
    }

    /**
     * Fired on every tick. Main processing is done here.
     */
    @Override
    public void update() {
        if (world.isRemote) {
            if (firstTickClient) {
                processSound();
                firstTickClient = false;
            }
            else {
                if (powered && !sound.isPlaying() && soundLoop)
                    playSoundInDirection(true);
            }
        }
    }

    @Override
    public void invalidate() {
        if (world.isRemote)
            sound.stopSound();

        super.invalidate();
    }

    private void processSound() {
        if (world.isRemote) {
            if (powered)
                playSoundInDirection(false);
            else
                sound.stopSound();
        }
    }

    private void playSoundInDirection(boolean skipCheck) {
        EnumFacing facing = world.getBlockState(pos).getValue(FACING);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        switch (facing) {
            case DOWN:  sound.playSound(x + 0.5D, y + 0.5D - soundOffset, z + 0.5D, soundName, getNormalizedRange(), skipCheck); break;
            case UP:    sound.playSound(x + 0.5D, y + 0.5D + soundOffset, z + 0.5D, soundName, getNormalizedRange(), skipCheck); break;
            case NORTH: sound.playSound(x + 0.5D, y + 0.5D, z + 0.5D - soundOffset, soundName, getNormalizedRange(), skipCheck); break;
            case SOUTH: sound.playSound(x + 0.5D, y + 0.5D, z + 0.5D + soundOffset, soundName, getNormalizedRange(), skipCheck); break;
            case WEST:  sound.playSound(x + 0.5D - soundOffset, y + 0.5D, z + 0.5D, soundName, getNormalizedRange(), skipCheck); break;
            case EAST:  sound.playSound(x + 0.5D + soundOffset, y + 0.5D, z + 0.5D, soundName, getNormalizedRange(), skipCheck); break;
        }
    }

    private float getNormalizedRange() {
        if (world.isRemote) {
            return Math.min(soundRadius, SOUND_RADIUS_MAX) / SpSound.DEFAULT_RANGE;
        }
        return soundRadius / SpSound.DEFAULT_RANGE;
    }

    /**** Getters and Setters ****/

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public String getSoundName() {
        return this.soundName;
    }

    public void setSoundRadius(int soundRadius) {
        this.soundRadius = soundRadius;
    }

    public int getSoundRadius() {
        return this.soundRadius;
    }

    public void setSoundOffset(int soundOffset) {
        this.soundOffset = soundOffset;
    }

    public int getSoundOffset() {
        return this.soundOffset;
    }

    public void setSoundLoop(boolean soundLoop) {
        this.soundLoop = soundLoop;
    }

    public boolean getSoundLoop() {
        return this.soundLoop;
    }

    public void setPowered(boolean powered) {
        this.powered = powered;
        processSound();
    }

    public boolean getPowered() {
        return this.powered;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return !(newState.getBlock() instanceof BlockSoundProjector);
    }

    /**
     * Check if the TIle Entity can be used by the player.
     */
    public boolean isUsableByPlayer(EntityPlayer player) {
        return world.getTileEntity(pos) == this && player.getDistanceSq((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D;
    }

    public void sendUpdates() {
        world.markBlockRangeForRenderUpdate(pos, pos);
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
        markDirty();
    }
}
