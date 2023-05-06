package mods.thecomputerizer.projectdata.common.capability.player;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerCapabilitiesStorage implements Capability.IStorage<IPlayerCapabilities> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IPlayerCapabilities> capability, IPlayerCapabilities instance, EnumFacing side) {
        return instance.writeToNBT();
    }

    @Override
    public void readNBT(Capability<IPlayerCapabilities> capability, IPlayerCapabilities instance, EnumFacing side, NBTBase nbt) {
        instance.readFromNBT((NBTTagCompound)nbt);
    }
}
