package mods.thecomputerizer.projectdata.common.registry.items;

import mods.thecomputerizer.projectdata.ProjectData;
import mods.thecomputerizer.theimpossiblelibrary.util.object.ItemUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DataModule extends Item {

    public DataModule() {
        addPropertyOverride(ProjectData.getResource("data_module_type"),this::getPropertyOverride);
    }

    private float getPropertyOverride(ItemStack stack, World world, EntityLivingBase entityUsing) {
        String type = ItemUtil.getOrCreateTag(stack).getString("data_module_type");
        switch (type) {
            case "motion": return 0.01f;
            case "position": return 0.02f;
            case "entity": return 0.03f;
            case "block": return 0.04f;
            case "item": return 0.05f;
            case "attack": return 0.06f;
            case "harvest": return 0.07f;
            case "interdimensional": return 0.1f;
            case "void": return 0.99f;
            default: return 1f;
        }
    }

    public String getType(ItemStack stack) {
        if(!ItemUtil.getOrCreateTag(stack).hasKey("data_module_type"))
            ItemUtil.getOrCreateTag(stack).setString("data_module_type","empty");
        return ItemUtil.getOrCreateTag(stack).getString("data_module_type");
    }

    public int getStorageCapacity(ItemStack stack) {
        if(!ItemUtil.getOrCreateTag(stack).hasKey("data_module_capacity"))
            ItemUtil.getOrCreateTag(stack).setInteger("data_module_capacity",64);
        return ItemUtil.getOrCreateTag(stack).getInteger("data_module_capacity");
    }
}
