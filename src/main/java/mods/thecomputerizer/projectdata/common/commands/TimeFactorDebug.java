package mods.thecomputerizer.projectdata.common.commands;

import mods.thecomputerizer.projectdata.common.registry.items.ObservingEye;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nonnull;

@SuppressWarnings("SpellCheckingInspection")
public class TimeFactorDebug extends CommandBase {
    @Override
    @Nonnull
    public String getName() {
        return "timefactor";
    }

    @Override
    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "Time Factor Debug command initiated";
    }

    @SuppressWarnings("NoTranslation")
    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
        if(args.length==1) {
            try {
                ObservingEye.TIME_FACTOR = Double.parseDouble(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CommandException("Error in timefactor command",e);
            }
        } else notifyCommandListener(sender, this, "Usage: '/timefactor factor'");
    }
}
