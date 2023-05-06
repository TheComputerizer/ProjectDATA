package mods.thecomputerizer.projectdata.client.capability.player;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
@SuppressWarnings("ConstantConditions")
public class ClientCapabilitiesProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(IClientCapabilities.class)
    public static final Capability<IClientCapabilities> CLIENT_CAPABILITIES = null;
    private final IClientCapabilities impl = CLIENT_CAPABILITIES.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability==CLIENT_CAPABILITIES;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CLIENT_CAPABILITIES ? CLIENT_CAPABILITIES.cast(this.impl) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound)CLIENT_CAPABILITIES.getStorage().writeNBT(CLIENT_CAPABILITIES,this.impl,null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        CLIENT_CAPABILITIES.getStorage().readNBT(CLIENT_CAPABILITIES, this.impl, null, nbt);
    }
}
