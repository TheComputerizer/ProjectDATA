package mods.thecomputerizer.projectdata.common.network.packets;

import io.netty.buffer.ByteBuf;
import mods.thecomputerizer.projectdata.client.world.DataBurst;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBoom implements IMessageHandler<PacketBoom.Message, IMessage> {
    @Override
    public IMessage onMessage(PacketBoom.Message message, MessageContext ctx) {
        new DataBurst(Minecraft.getMinecraft().world,message.x,message.y,message.z,message.strength,message.time,message.scale);
        return null;
    }

    public static class Message implements IMessage {

        private double x;
        private double y;
        private double z;
        private int strength;
        private int time;
        private float scale;

        public Message() {}

        public Message(Vec3d pos, int strength, int time, float scale) {
            this(pos.x,pos.y,pos.z,strength,time,scale);
        }

        public Message(double x, double y, double z, int strength, int time, float scale) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.strength = strength;
            this.time = time;
            this.scale = scale;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            this.x = buf.readDouble();
            this.y = buf.readDouble();
            this.z = buf.readDouble();
            this.strength = buf.readInt();
            this.time = buf.readInt();
            this.scale = buf.readFloat();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeDouble(this.x);
            buf.writeDouble(this.y);
            buf.writeDouble(this.z);
            buf.writeInt(this.strength);
            buf.writeInt(this.time);
            buf.writeFloat(this.scale);
        }
    }
}