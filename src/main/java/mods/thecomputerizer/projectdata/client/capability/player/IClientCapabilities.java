package mods.thecomputerizer.projectdata.client.capability.player;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IClientCapabilities {

    void setColorCorrection(float amount);
    float getColorCorrection();

    void setFOVFactor(float amount);
    float getFOVFactor();

    void setScreenShake(float amount);
    float getScreenShake();

    void storeMotion(double amount);
    double getStoredMotion();
    void resetMotion();

    NBTTagCompound writeToNBT();
    void readFromNBT(NBTTagCompound tag);
}
