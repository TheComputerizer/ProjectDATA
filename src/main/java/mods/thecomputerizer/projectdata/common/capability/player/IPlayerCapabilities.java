package mods.thecomputerizer.projectdata.common.capability.player;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public interface IPlayerCapabilities {

    void storeMotion(Vec3d motionVec);
    Vec3d getStoredMotion();
    void resetMotion();

    NBTTagCompound writeToNBT();
    void readFromNBT(NBTTagCompound tag);
}
