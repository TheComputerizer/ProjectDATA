package mods.thecomputerizer.projectdata.client.capability;

import mods.thecomputerizer.projectdata.ProjectData;
import mods.thecomputerizer.projectdata.client.capability.player.ClientCapabilitiesProvider;
import mods.thecomputerizer.projectdata.client.capability.player.IClientCapabilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientCapabilityHandler {

    public static final ResourceLocation CLIENT_CAPABILITIES = ProjectData.getResource("client_capabilities");

    @SuppressWarnings("ConstantConditions")
    public static IClientCapabilities getPlayerCapabilities(EntityPlayer player) {
        return player.getCapability(ClientCapabilitiesProvider.CLIENT_CAPABILITIES,null);
    }
}
