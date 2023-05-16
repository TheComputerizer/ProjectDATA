package mods.thecomputerizer.projectdata.common.registry;

import mods.thecomputerizer.projectdata.Constants;
import mods.thecomputerizer.projectdata.ProjectData;
import mods.thecomputerizer.projectdata.client.render.UnstableEntityRenderer;
import mods.thecomputerizer.projectdata.common.registry.entities.UnstableEntity;
import mods.thecomputerizer.theimpossiblelibrary.util.TextUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class EntityRegistry {
    private static int globalUniqueEntityId = 0;
    public static void register() {
        registerEntity(UnstableEntity.class,16,0x0,0xFFFF);
    }

    private static void registerEntity(Class <? extends Entity> entityClass, int trackingRange,
                                       int spawnEggFirstColor, int spawnEggSecondColor) {
        List<String> classWords = Arrays.asList(entityClass.getSimpleName().split("(?=\\p{Upper})"));
        String name = TextUtil.listToString(classWords,"_").toLowerCase();
        net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(ProjectData.getResource(name),
                entityClass, name, globalUniqueEntityId++, Constants.MODID, trackingRange, 1,
                true, spawnEggFirstColor, spawnEggSecondColor);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(UnstableEntity.class,
                manager -> new UnstableEntityRenderer<>(manager, EntityLiving.class));
    }
}
