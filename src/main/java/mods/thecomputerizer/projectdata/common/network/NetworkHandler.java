package mods.thecomputerizer.projectdata.common.network;

import mods.thecomputerizer.projectdata.Constants;
import mods.thecomputerizer.projectdata.common.network.packets.PacketBoom;
import mods.thecomputerizer.projectdata.common.network.packets.PacketMotionOverload;
import mods.thecomputerizer.projectdata.common.network.packets.PacketParticleTest;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkHandler {
    private static SimpleNetworkWrapper NETWORK;

    public static void init() {
        NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MODID);
        registerPackets();
    }

    private static void registerPackets() {
        int id = 0;
        NETWORK.registerMessage(PacketMotionOverload.class, PacketMotionOverload.Message.class, id++, Side.SERVER);
        NETWORK.registerMessage(PacketParticleTest.class, PacketParticleTest.Message.class, id++, Side.CLIENT);
        NETWORK.registerMessage(PacketBoom.class, PacketBoom.Message.class, id, Side.CLIENT);
    }

    @SideOnly(Side.CLIENT)
    public static void sendToServer(IMessage packet) {
        NETWORK.sendToServer(packet);
    }

    public static void sendToPlayer(IMessage packet, EntityPlayerMP player) {
        NETWORK.sendTo(packet, player);
    }
}
