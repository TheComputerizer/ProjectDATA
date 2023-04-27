package mods.thecomputerizer.projectdata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Constants {
    public static final String MODID = "projectdata";
    public static final String NAME = "Project D.A.T.A.";
    public static final String VERSION = "0.0.1";
    public static final String DEPENDENCIES = "required-after:theimpossiblelibrary;";
    public static final Logger MAIN_LOG = LogManager.getLogger(MODID);
    public static final File CONFIG_DIR = new File("config/ProjectData");
}