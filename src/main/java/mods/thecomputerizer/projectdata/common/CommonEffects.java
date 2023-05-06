package mods.thecomputerizer.projectdata.common;

import mods.thecomputerizer.projectdata.mixin.access.IMixinEntityFields;
import net.minecraft.entity.player.EntityPlayer;

public class CommonEffects {
    public static double getMovementFactor(EntityPlayer player) {
        return ((IMixinEntityFields)player).getSlowFactor();
    }

    public static void setMovementFactor(EntityPlayer player, double factor) {
        ((IMixinEntityFields)player).setSlowFactor(factor);
    }
}
