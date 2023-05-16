package mods.thecomputerizer.projectdata.common.commands;

import mods.thecomputerizer.projectdata.common.network.NetworkHandler;
import mods.thecomputerizer.projectdata.common.network.packets.PacketBoom;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nonnull;

public class Boom extends CommandBase {

    @Override
    @Nonnull
    public String getName() {
        return "boom";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "Boom command initiated";
    }

    @SuppressWarnings("NoTranslation")
    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        if(args.length>=3) {
            try {
                if(sender instanceof EntityPlayerMP) {
                    Vec3d pos = new Vec3d(parseCoordinate(((EntityPlayerMP) sender).posX,args[0],true).getAmount(),
                            parseCoordinate(((EntityPlayerMP) sender).posX,args[1],true).getAmount(),
                            parseCoordinate(((EntityPlayerMP) sender).posX,args[2],true).getAmount());
                    int strength = args.length>=4 ? parseInt(args[3]) : 50;
                    int time = args.length>=5 ? parseInt(args[4]) : 20;
                    float scale = args.length>=6 ? Float.parseFloat(args[5]) : 0.5f;
                    NetworkHandler.sendToPlayer(new PacketBoom.Message(pos.x,pos.y,pos.z,strength,time,scale), (EntityPlayerMP) sender);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new CommandException("Error in boom command",e);
            }
        } else notifyCommandListener(sender, this, "Usage: '/boom x y z [Optional]strength [Optional]time [Optional]scale'");
    }
}