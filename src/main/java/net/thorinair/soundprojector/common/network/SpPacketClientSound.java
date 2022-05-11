package net.thorinair.soundprojector.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.thorinair.soundprojector.common.tileentity.TileEntitySoundProjector;

public class SpPacketClientSound implements IMessage, IMessageHandler<SpPacketClientSound, IMessage> {
    private int x;
    private int y;
    private int z;

    private String soundName;
    private int soundRange;
    private int soundDistance;
    private boolean soundLoop;

    public SpPacketClientSound() {
    }

    public SpPacketClientSound(int x, int y, int z, String soundName, int soundRange, int soundDistance, boolean soundLoop) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.soundName = soundName;
        this.soundRange = soundRange;
        this.soundDistance = soundDistance;
        this.soundLoop = soundLoop;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();

        soundName = ByteBufUtils.readUTF8String(buf);
        soundRange = buf.readInt();
        soundDistance = buf.readInt();
        soundLoop = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);

        ByteBufUtils.writeUTF8String(buf, soundName);
        buf.writeInt(soundRange);
        buf.writeInt(soundDistance);
        buf.writeBoolean(soundLoop);
    }

    @Override
    public IMessage onMessage(SpPacketClientSound message, MessageContext ctx) {
        BlockPos pos = new BlockPos(message.x, message.y, message.z);
        TileEntity tileEntity = ctx.getServerHandler().player.world.getTileEntity(pos);
        if (tileEntity instanceof TileEntitySoundProjector) {
            TileEntitySoundProjector tileSoundProjector = (TileEntitySoundProjector) tileEntity;
            tileSoundProjector.setSoundName(message.soundName);
            tileSoundProjector.setSoundRange(message.soundRange);
            tileSoundProjector.setSoundDistance(message.soundDistance);
            tileSoundProjector.setSoundLoop(message.soundLoop);
            tileSoundProjector.sendUpdates();
            //IBlockState state = tileSoundProjector.getWorld().getBlockState(pos);
            //tileSoundProjector.getWorld().notifyBlockUpdate(pos, state, state, 0);
        }
        return null;
    }
}
