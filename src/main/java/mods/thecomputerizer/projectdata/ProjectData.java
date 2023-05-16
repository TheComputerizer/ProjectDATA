package mods.thecomputerizer.projectdata;

import mods.thecomputerizer.projectdata.client.ProjectDataClient;
import mods.thecomputerizer.projectdata.common.capability.player.IPlayerCapabilities;
import mods.thecomputerizer.projectdata.common.capability.player.PlayerCapabilities;
import mods.thecomputerizer.projectdata.common.capability.player.PlayerCapabilitiesStorage;
import mods.thecomputerizer.projectdata.common.commands.Boom;
import mods.thecomputerizer.projectdata.common.commands.ParticleTest;
import mods.thecomputerizer.projectdata.common.commands.TimeFactorDebug;
import mods.thecomputerizer.projectdata.common.network.NetworkHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.commons.io.FileExistsException;

import java.io.IOException;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION, dependencies = Constants.DEPENDENCIES)
public class ProjectData {

    public ProjectData() throws IOException {
        if (!Constants.CONFIG_DIR.exists())
            if(!Constants.CONFIG_DIR.mkdirs())
                throw new FileExistsException("Unable to create file directory at "+Constants.CONFIG_DIR.getPath()+
                        "! Project D.A.T.A. is unable to load any further.");
        if(FMLCommonHandler.instance().getSide().isClient())
            ProjectDataClient.modConstructor();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IPlayerCapabilities.class,new PlayerCapabilitiesStorage(),PlayerCapabilities::new);
        NetworkHandler.init();
        if(FMLCommonHandler.instance().getSide().isClient())
            ProjectDataClient.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(FMLCommonHandler.instance().getSide().isClient())
            ProjectDataClient.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if(FMLCommonHandler.instance().getSide().isClient())
            ProjectDataClient.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new TimeFactorDebug());
        event.registerServerCommand(new ParticleTest());
        event.registerServerCommand(new Boom());
        if(FMLCommonHandler.instance().getSide().isClient())
            ProjectDataClient.serverStarting(event);
    }

    public static ResourceLocation getResource(String path) {
        return new ResourceLocation(Constants.MODID,path);
    }
}
