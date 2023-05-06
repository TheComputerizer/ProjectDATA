package mods.thecomputerizer.projectdata.mixin.mixins;

import mods.thecomputerizer.projectdata.client.render.DynamicColorShader;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.util.List;

@Mixin(ShaderGroup.class)
public class MixinShaderGroup {

    @Shadow @Final private IResourceManager resourceManager;

    @Shadow @Final private List<Shader> listShaders;

    @Inject(at = @At("HEAD"), method = "addShader", cancellable = true)
    private void projectdata_addShader(String programName, Framebuffer framebufferIn, Framebuffer framebufferOut, CallbackInfoReturnable<Shader> cir) throws IOException {
        if(programName.matches("projectdata:dynamic_color_overlay")) {
            DynamicColorShader shader = new DynamicColorShader(this.resourceManager,programName,framebufferIn,framebufferOut);
            this.listShaders.add(this.listShaders.size(),shader);
            cir.setReturnValue(shader);
        }
    }
}
