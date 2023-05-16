package mods.thecomputerizer.projectdata.common.registry;

import mods.thecomputerizer.projectdata.Constants;
import mods.thecomputerizer.projectdata.common.registry.items.DataModule;
import mods.thecomputerizer.projectdata.common.registry.items.DataRecorder;
import mods.thecomputerizer.projectdata.common.registry.items.DataTransformer;
import mods.thecomputerizer.projectdata.common.registry.items.ObservingEye;
import mods.thecomputerizer.theimpossiblelibrary.util.TextUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ItemRegistry {

    public static final ObservingEye OBSERVING_EYE = (ObservingEye)makeItem(ObservingEye::new,
            item -> item.setCreativeTab(Tabs.PROJECT_DATA_TAB),1);
    public static final DataModule DATA_MODULE = (DataModule)makeItem(DataModule::new,
            item -> item.setCreativeTab(Tabs.PROJECT_DATA_TAB),3);
    public static final DataRecorder DATA_RECORDER = (DataRecorder)makeItem(DataRecorder::new,
            item -> item.setCreativeTab(Tabs.PROJECT_DATA_TAB),1);
    public static final DataTransformer DATA_TRANSFORMER = (DataTransformer)makeItem(DataTransformer::new,
            item -> item.setCreativeTab(Tabs.PROJECT_DATA_TAB),1);

    private static Item makeItem(final Supplier<Item> constructor, final Consumer<Item> config, int stackSize) {
        final Item item = constructor.get();
        config.accept(item);
        List<String> classWords = Arrays.asList(item.getClass().getSimpleName().split("(?=\\p{Upper})"));
        String name = TextUtil.listToString(classWords,"_").toLowerCase();
        item.setRegistryName(Constants.MODID, name);
        item.setTranslationKey(Constants.MODID+"."+name);
        item.setMaxStackSize(stackSize);
        return item;
    }

    public static void register(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(OBSERVING_EYE,DATA_MODULE,DATA_RECORDER,DATA_TRANSFORMER);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(OBSERVING_EYE, 0,
                new ModelResourceLocation(Objects.requireNonNull(OBSERVING_EYE.getRegistryName()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(DATA_MODULE, 0,
                new ModelResourceLocation(Objects.requireNonNull(DATA_MODULE.getRegistryName()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(DATA_RECORDER, 0,
                new ModelResourceLocation(Objects.requireNonNull(DATA_RECORDER.getRegistryName()), "inventory"));
        ModelLoader.setCustomModelResourceLocation(DATA_TRANSFORMER, 0,
                new ModelResourceLocation(Objects.requireNonNull(DATA_TRANSFORMER.getRegistryName()), "inventory"));
    }
}
