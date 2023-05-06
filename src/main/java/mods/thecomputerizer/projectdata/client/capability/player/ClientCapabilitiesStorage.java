package mods.thecomputerizer.projectdata.client.capability.player;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ClientCapabilitiesStorage implements Capability.IStorage<IClientCapabilities> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IClientCapabilities> capability, IClientCapabilities instance, EnumFacing side) {
        return instance.writeToNBT();
    }

    @Override
    public void readNBT(Capability<IClientCapabilities> capability, IClientCapabilities instance, EnumFacing side, NBTBase nbt) {
        instance.readFromNBT((NBTTagCompound)nbt);
    }
}
