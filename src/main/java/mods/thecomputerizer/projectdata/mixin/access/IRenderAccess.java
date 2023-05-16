package mods.thecomputerizer.projectdata.mixin.access;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public interface IRenderAccess<T extends Entity> {
    ResourceLocation accessGetTexture(T entity);
}
