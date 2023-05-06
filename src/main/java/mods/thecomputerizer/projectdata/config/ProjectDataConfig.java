package mods.thecomputerizer.projectdata.config;

import mods.thecomputerizer.projectdata.Constants;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constants.MODID)
@Config(modid = Constants.MODID, name = Constants.MODID, category = "")
public class ProjectDataConfig {

    public static ConfigRegistry registry = new ConfigRegistry();

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Constants.MODID)) {
            ConfigManager.sync(event.getModID(), Config.Type.INSTANCE); // Sync config values
        }
    }
}
