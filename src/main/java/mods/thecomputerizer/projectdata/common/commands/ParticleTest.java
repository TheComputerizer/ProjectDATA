package mods.thecomputerizer.projectdata.common.commands;

import mods.thecomputerizer.projectdata.common.network.NetworkHandler;
import mods.thecomputerizer.projectdata.common.network.packets.PacketParticleTest;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nonnull;

@SuppressWarnings("SpellCheckingInspection")
public class ParticleTest extends CommandBase {

    @Override
    @Nonnull
    public String getName() {
        return "particletest";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "Particle Test Debug command initiated";
    }

    @SuppressWarnings("NoTranslation")
    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        if(args.length==0) {
            try {
                if(sender instanceof EntityPlayerMP)
                    NetworkHandler.sendToPlayer(new PacketParticleTest.Message(new Vec3d(1d,0d,0d),1),(EntityPlayerMP)sender);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CommandException("Error in particletest command",e);
            }
        } else notifyCommandListener(sender, this, "Usage: '/particletest'");
    }
}
