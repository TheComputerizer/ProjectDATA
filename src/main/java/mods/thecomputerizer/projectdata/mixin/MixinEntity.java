package mods.thecomputerizer.projectdata.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@SuppressWarnings("ConstantValue")
@Mixin(Entity.class)
public class MixinEntity {

    @Shadow
    public double posY;
    @Shadow
    public boolean onGround;
    @Shadow
    public boolean noClip;

    @ModifyVariable(at = @At("HEAD"), method = "move", ordinal = 0, argsOnly = true)
    private double projectdata_slowVoidX(double x) {
        if(!((Object)this instanceof EntityPlayer) || this.noClip || this.posY>=0 || this.onGround) return x;
        double factor = Math.max(0d,(63d+this.posY)/63d);
        return x*factor;
    }

    @ModifyVariable(at = @At("HEAD"), method = "move", ordinal = 1, argsOnly = true)
    private double projectdata_slowVoidY(double y) {
        if(!((Object)this instanceof EntityPlayer) || this.noClip || this.posY>=0 || this.onGround) return y;
        double factor = Math.max(0d,(63d+this.posY)/63d);
        return y*factor;
    }

    @ModifyVariable(at = @At("HEAD"), method = "move", ordinal = 2, argsOnly = true)
    private double projectdata_slowVoidZ(double z) {
        if(!((Object)this instanceof EntityPlayer) || this.noClip || this.posY>=0 || this.onGround) return z;
        double factor = Math.max(0d,(63d+this.posY)/63d);
        return z*factor;
    }
}
