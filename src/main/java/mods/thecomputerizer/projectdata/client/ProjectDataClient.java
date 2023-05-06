package mods.thecomputerizer.projectdata.client;

import mods.thecomputerizer.projectdata.client.capability.player.ClientCapabilities;
import mods.thecomputerizer.projectdata.client.capability.player.ClientCapabilitiesStorage;
import mods.thecomputerizer.projectdata.client.capability.player.IClientCapabilities;
import mods.thecomputerizer.projectdata.common.registry.EntityRegistry;
import mods.thecomputerizer.projectdata.common.registry.ParticleRegistry;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ProjectDataClient {

    public static void modConstructor() {

    }

    public static void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IClientCapabilities.class,new ClientCapabilitiesStorage(),ClientCapabilities::new);
        EntityRegistry.registerRenders();
    }

    public static void init(FMLInitializationEvent event) {

    }

    public static void postInit(FMLPostInitializationEvent event) {
        ParticleRegistry.postInit();
    }

    public static void serverStarting(FMLServerStartingEvent event) {

    }

}
