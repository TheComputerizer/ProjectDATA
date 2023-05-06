package mods.thecomputerizer.projectdata.mixin.mixins;

import mods.thecomputerizer.projectdata.client.render.ClientEffects;
import mods.thecomputerizer.projectdata.client.render.RenderOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Shadow public abstract void loadShader(ResourceLocation resourceLocationIn);

    @Inject(at = @At("TAIL"), method = "onResourceManagerReload")
    private void projectdata_onResourceManagerReload(IResourceManager resourceManager, CallbackInfo ci) {
        if(Objects.nonNull(Minecraft.getMinecraft().player))
            this.loadShader(ClientEffects.GRAYSCALE_SHADER);
    }

    @Inject(at = @At("TAIL"), method = "renderWorldPass")
    private void projectdata_renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
        RenderOverlay.drawLineFromPlayer();
    }
}
