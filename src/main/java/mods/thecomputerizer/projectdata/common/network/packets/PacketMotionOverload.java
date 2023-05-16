package mods.thecomputerizer.projectdata.common.network.packets;

import io.netty.buffer.ByteBuf;
import mods.thecomputerizer.projectdata.common.network.NetworkHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Objects;

public class PacketMotionOverload  implements IMessageHandler<PacketMotionOverload.Message, IMessage> {
    @Override
    public IMessage onMessage(PacketMotionOverload.Message message, MessageContext ctx) {
        WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(message.dimension);
        EntityLivingBase entity = (EntityLivingBase)world.getEntityByID(message.entityID);
        if(Objects.nonNull(entity)) {
            ItemStack stack = entity.getActiveItemStack();
            entity.stopActiveHand();
            stack.shrink(1);
            for(EntityPlayer player : world.playerEntities)
                NetworkHandler.sendToPlayer(new PacketBoom.Message(entity.getPositionVector(),50,10,0.1f),(EntityPlayerMP)player);
            world.playSound(null,entity.getPosition(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS,1f,1f);
        }
        return null;
    }

    public static class Message implements IMessage {

        private int entityID;
        private int dimension;

        public Message() {
        }

        public Message(int entityID, int dimension) {
            this.entityID = entityID;
            this.dimension = dimension;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            this.entityID = buf.readInt();
            this.dimension = buf.readInt();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeInt(this.entityID);
            buf.writeInt(this.dimension);
        }
    }
}
