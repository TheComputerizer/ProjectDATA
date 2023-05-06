package mods.thecomputerizer.projectdata.common.capability;

import mods.thecomputerizer.projectdata.ProjectData;
import mods.thecomputerizer.projectdata.common.capability.player.IPlayerCapabilities;
import mods.thecomputerizer.projectdata.common.capability.player.PlayerCapabilitiesProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class CommonCapabilityHandler {

    public static final ResourceLocation PLAYER_CAPABILITIES = ProjectData.getResource("player_capabilities");

    @SuppressWarnings("ConstantConditions")
    public static IPlayerCapabilities getPlayerCapabilities(EntityPlayer player) {
        return player.getCapability(PlayerCapabilitiesProvider.PLAYER_CAPABILITIES,null);
    }
}
