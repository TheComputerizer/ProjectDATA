package mods.thecomputerizer.projectdata.mixin.mixins;

import mods.thecomputerizer.projectdata.mixin.access.IMixinEntityFields;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import javax.annotation.Nonnull;

@Mixin(EntityPlayer.class)
public abstract class MixinPlayer extends Entity implements IMixinEntityFields {

    private double slowFactor = 1d;

    public MixinPlayer(World worldIn) {
        super(worldIn);
    }

    private EntityPlayer cast() {
        return (EntityPlayer)(Object)this;
    }

    @Override
    public double getSlowFactor() {
        return this.slowFactor;
    }

    @Override
    public void setSlowFactor(double factor) {
        this.slowFactor = factor;
    }

    /*
    @Inject(at = @At("TAIL"), method = "onLivingUpdate")
    private void projectdata_onLivingUpdate(CallbackInfo ci) {
        if(cast().noClip || cast().posY>=0 || cast().onGround) setSlowFactor(1d);
        else setSlowFactor(Math.max(0d,(63d+cast().posY)/63d));
    }
     */

    @Override
    public void move(@Nonnull MoverType type, double x, double y, double z) {
        if(type!=MoverType.PISTON) {
            x = x*getSlowFactor();
            y = y*getSlowFactor();
            z = z*getSlowFactor();
        }
        super.move(type, x, y, z);
    }
}
