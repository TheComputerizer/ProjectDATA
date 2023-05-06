package mods.thecomputerizer.projectdata.common.registry;

import mods.thecomputerizer.projectdata.Constants;
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

    public static final Item OBSERVING_EYE = makeItem(ObservingEye::new, item -> item.setCreativeTab(Tabs.PROJECT_DATA_TAB),1);

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
        event.getRegistry().register(OBSERVING_EYE);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ModelLoader.setCustomModelResourceLocation(OBSERVING_EYE, 0,
                new ModelResourceLocation(Objects.requireNonNull(OBSERVING_EYE.getRegistryName()), "inventory"));
    }
}
