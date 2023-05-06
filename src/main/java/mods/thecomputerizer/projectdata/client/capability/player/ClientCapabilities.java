package mods.thecomputerizer.projectdata.client.capability.player;

import mods.thecomputerizer.projectdata.Constants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientCapabilities implements IClientCapabilities {

    private float colorCorrection;
    private float fovOffset;
    private float screenShake;
    private double motion = 0d;

    @Override
    public void setColorCorrection(float amount) {
        this.colorCorrection = amount;
    }

    @Override
    public float getColorCorrection() {
        return this.colorCorrection;
    }

    @Override
    public void setFOVFactor(float amount) {
        this.fovOffset = amount;
    }

    @Override
    public float getFOVFactor() {
        return this.fovOffset;
    }

    @Override
    public void setScreenShake(float amount) {
        this.screenShake = amount;
    }

    @Override
    public float getScreenShake() {
        return this.screenShake;
    }

    @Override
    public void storeMotion(double amount) {
        if(this.motion==0d) this.motion = amount;
    }

    @Override
    public double getStoredMotion() {
        return this.motion;
    }

    @Override
    public void resetMotion() {
        this.motion = 0d;
    }

    @Override
    public NBTTagCompound writeToNBT() {
       NBTTagCompound tag = new NBTTagCompound();
       tag.setFloat("color_correction",this.colorCorrection);
       tag.setFloat("fov_offset",this.fovOffset);
       tag.setFloat("screen_shake",this.screenShake);
       return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        this.colorCorrection = tag.hasKey("color_correction") ? tag.getFloat("color_correction") : 0f;
        this.fovOffset = tag.hasKey("fov_offset") ? tag.getFloat("fov_offset") : 0f;
        this.screenShake = tag.hasKey("screen_shake") ? tag.getFloat("screen_shake") : 0f;
        Constants.MAIN_LOG.warn("READING CLIENT STUFF {} {} {}",this.colorCorrection,this.fovOffset,this.screenShake);
    }
}
