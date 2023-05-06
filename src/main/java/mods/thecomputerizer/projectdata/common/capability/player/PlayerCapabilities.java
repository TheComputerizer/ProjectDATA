package mods.thecomputerizer.projectdata.common.capability.player;

import mods.thecomputerizer.projectdata.Constants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class PlayerCapabilities implements IPlayerCapabilities {

    private Vec3d motion = new Vec3d(0,0,0);

    @Override
    public void storeMotion(Vec3d motionVec) {
        this.motion = motionVec;
        //if(player instanceof EntityPlayerMP)
            //NetworkHandler.sendToPlayer(new PacketSyncMotion.Message(motionVec),(EntityPlayerMP)player);
    }

    @Override
    public Vec3d getStoredMotion() {
        return this.motion;
    }

    @Override
    public void resetMotion() {
        this.motion = new Vec3d(0,0,0);
    }

    @Override
    public NBTTagCompound writeToNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("motion_x",this.motion.x);
        tag.setDouble("motion_y",this.motion.y);
        tag.setDouble("motion_z",this.motion.z);
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        this.motion = tag.hasKey("motion_x") ?
                new Vec3d(tag.getDouble("motion_x"),tag.getDouble("motion_y"),tag.getDouble("motion_z")) :
                new Vec3d(0,0,0);
        Constants.MAIN_LOG.warn("READING PLAYER STUFF {}",this.motion);
    }
}
