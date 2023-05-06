package mods.thecomputerizer.projectdata.common.capability.player;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("ConstantConditions")
public class PlayerCapabilitiesProvider implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(IPlayerCapabilities.class)
    public static final Capability<IPlayerCapabilities> PLAYER_CAPABILITIES = null;
    private final IPlayerCapabilities impl = PLAYER_CAPABILITIES.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability==PLAYER_CAPABILITIES;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == PLAYER_CAPABILITIES ? PLAYER_CAPABILITIES.cast(this.impl) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound)PLAYER_CAPABILITIES.getStorage().writeNBT(PLAYER_CAPABILITIES,this.impl,null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        PLAYER_CAPABILITIES.getStorage().readNBT(PLAYER_CAPABILITIES, this.impl, null, nbt);
    }
}
