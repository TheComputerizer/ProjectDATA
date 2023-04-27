package mods.thecomputerizer.projectdata;

import mods.thecomputerizer.projectdata.client.ClientInit;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.io.FileExistsException;

import java.io.IOException;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION, dependencies = Constants.DEPENDENCIES)
public class ProjectData {

    public ProjectData() throws IOException {
        if (!Constants.CONFIG_DIR.exists())
            if(!Constants.CONFIG_DIR.mkdirs())
                throw new FileExistsException("Unable to create file directory at "+Constants.CONFIG_DIR.getPath()+
                        "! Project D.A.T.A. is unable to load any further.");
        if(FMLCommonHandler.instance().getSide()==Side.CLIENT)
            ClientInit.modConstructor();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(FMLCommonHandler.instance().getSide()==Side.CLIENT)
            ClientInit.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(FMLCommonHandler.instance().getSide()==Side.CLIENT)
            ClientInit.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if(FMLCommonHandler.instance().getSide()==Side.CLIENT)
            ClientInit.postInit(event);
    }
}
