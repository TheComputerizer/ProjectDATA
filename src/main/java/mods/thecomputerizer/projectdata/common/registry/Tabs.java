package mods.thecomputerizer.projectdata.common.registry;

import mods.thecomputerizer.projectdata.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class Tabs {

    public static final CreativeTabs PROJECT_DATA_TAB = new CreativeTabs(Constants.MODID) {
        @SideOnly(Side.CLIENT)
        @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.OBSERVING_EYE);
        }
    };
}
