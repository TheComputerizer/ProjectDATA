package mods.thecomputerizer.projectdata.common.registry.items;

import mods.thecomputerizer.theimpossiblelibrary.util.object.ItemUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DataRecorder extends Item {

    public int getMaxModules(ItemStack stack) {
        if(!ItemUtil.getOrCreateTag(stack).hasKey("max_data_modules"))
            ItemUtil.getOrCreateTag(stack).setInteger("max_data_modules",3);
        return ItemUtil.getOrCreateTag(stack).getInteger("max_data_modules");
    }

    public int getCurrentModuleCount(ItemStack stack) {
        if(!ItemUtil.getOrCreateTag(stack).hasKey("data_module_count"))
            ItemUtil.getOrCreateTag(stack).setInteger("data_module_count",0);
        return ItemUtil.getOrCreateTag(stack).getInteger("data_module_count");
    }

    public int getRecordingTime(ItemStack stack) {
        if(!ItemUtil.getOrCreateTag(stack).hasKey("data_recording_modifier"))
            ItemUtil.getOrCreateTag(stack).setInteger("data_recording_modifier",100);
        return ItemUtil.getOrCreateTag(stack).getInteger("data_recording_modifier");
    }
}
