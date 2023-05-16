package mods.thecomputerizer.projectdata.mixin.mixins;

import mods.thecomputerizer.projectdata.mixin.access.IRenderAccess;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(Render.class)
public abstract class MixinRenderer<T extends Entity> implements IRenderAccess<T> {
    @Shadow @Nullable protected abstract ResourceLocation getEntityTexture(T entity);

    @Override
    public ResourceLocation accessGetTexture(T entity) {
        return this.getEntityTexture(entity);
    }
}
