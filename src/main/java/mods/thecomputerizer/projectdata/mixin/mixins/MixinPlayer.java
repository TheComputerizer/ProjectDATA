package mods.thecomputerizer.projectdata.mixin.mixins;

import mods.thecomputerizer.projectdata.mixin.access.IEntityItemAccess;
import mods.thecomputerizer.projectdata.mixin.access.IMixinEntityFields;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import java.util.Objects;

@Mixin(EntityPlayer.class)
public abstract class MixinPlayer extends Entity implements IMixinEntityFields {

    private double slowFactor = 1d;

    public MixinPlayer(World worldIn) {
        super(worldIn);
    }

    @Override
    public double getSlowFactor() {
        return this.slowFactor;
    }

    @Override
    public void setSlowFactor(double factor) {
        this.slowFactor = factor;
    }

    @Override
    public void move(@Nonnull MoverType type, double x, double y, double z) {
        if(type!=MoverType.PISTON) {
            x = x*getSlowFactor();
            y = y*getSlowFactor();
            z = z*getSlowFactor();
        }
        super.move(type, x, y, z);
    }

    @Inject(at = @At("RETURN"),method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/item/EntityItem;")
    private void projectdata_dropItem(ItemStack droppedItem, boolean dropAround, boolean traceItem, CallbackInfoReturnable<EntityItem> cir) {
        EntityItem entity = cir.getReturnValue();
        if(Objects.nonNull(entity) && entity.getItem().getItem()==Items.ENDER_EYE)
            ((IEntityItemAccess)entity).setThrownByPlayer(this.getUniqueID());
    }
}
