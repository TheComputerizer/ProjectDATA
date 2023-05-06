package mods.thecomputerizer.projectdata.common.registry;

import mods.thecomputerizer.projectdata.ProjectData;
import mods.thecomputerizer.theimpossiblelibrary.util.TextUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Arrays;
import java.util.List;

public class TileRegistry {

    public static void register() {

    }

    private static void registerClass(Class<? extends TileEntity> tileClass) {
        List<String> classWords = Arrays.asList(tileClass.getSimpleName().split("(?=\\p{Upper})"));
        ResourceLocation resource = ProjectData.getResource("tile."+(TextUtil.listToString(classWords,"_").toLowerCase()));
        GameRegistry.registerTileEntity(tileClass,resource);
    }
}
