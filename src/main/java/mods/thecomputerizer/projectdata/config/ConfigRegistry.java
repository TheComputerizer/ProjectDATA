package mods.thecomputerizer.projectdata.config;

import mods.thecomputerizer.projectdata.Constants;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Config(modid = Constants.MODID,name = Constants.MODID,category = "registry")
public class ConfigRegistry {

    @Config.Name("alphaNumParticleID")
    @Config.LangKey("projectdata.config.registry.alphaNumParticleID")
    @Config.RequiresMcRestart
    public int alphaNumParticleID = 71562;
}
