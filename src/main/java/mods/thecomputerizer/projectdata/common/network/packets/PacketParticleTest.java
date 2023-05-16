package mods.thecomputerizer.projectdata.common.network.packets;

import io.netty.buffer.ByteBuf;
import mods.thecomputerizer.projectdata.common.registry.ParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketParticleTest implements IMessageHandler<PacketParticleTest.Message, IMessage> {


    @Override
    public IMessage onMessage(Message message, MessageContext ctx) {
        BlockPos pos = Minecraft.getMinecraft().player.getPosition();
        for(int i=0;i<message.particles;i++)
            Minecraft.getMinecraft().player.world.spawnParticle(ParticleRegistry.RANDOM_ASCII, pos.getX(), pos.getY(),
                pos.getZ(), message.motion.x, message.motion.x, message.motion.x);
        return null;
    }

    public static class Message implements IMessage {

        private Vec3d motion;
        private int particles;

        public Message() {}

        public Message(Vec3d motion, int particles) {
            this.motion = motion;
            this.particles = particles;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            this.motion = new Vec3d(buf.readDouble(),buf.readDouble(),buf.readDouble());
            this.particles = buf.readInt();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeDouble(this.motion.x);
            buf.writeDouble(this.motion.y);
            buf.writeDouble(this.motion.z);
            buf.writeInt(this.particles);
        }
    }
}
