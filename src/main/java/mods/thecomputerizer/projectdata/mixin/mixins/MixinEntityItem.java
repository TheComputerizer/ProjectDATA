package mods.thecomputerizer.projectdata.mixin.mixins;

import mods.thecomputerizer.projectdata.common.registry.ItemRegistry;
import mods.thecomputerizer.projectdata.mixin.access.IEntityItemAccess;
import mods.thecomputerizer.theimpossiblelibrary.util.object.ItemUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.UUID;

@Mixin(EntityItem.class)
public abstract class MixinEntityItem extends Entity implements IEntityItemAccess {

    private UUID playerUUID;

    public MixinEntityItem(World worldIn) {
        super(worldIn);
    }

    @Override
    public void setThrownByPlayer(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    @Override
    protected void outOfWorld() {
        if(!this.world.isRemote && Objects.nonNull(this.playerUUID)) {
            EntityPlayer player = this.world.getPlayerEntityByUUID(this.playerUUID);
            if(Objects.nonNull(player)) {
                ItemStack corruptedStack = ItemRegistry.OBSERVING_EYE.getDefaultInstance();
                ItemUtil.getOrCreateTag(corruptedStack).setBoolean("is_corrupted_eye",true);
                EntityItem item = new EntityItem(this.world, player.posX, player.posY + 2, player.posZ, corruptedStack);
                this.world.spawnEntity(item);
                this.world.playSound(null,player.posX,player.posY+2,player.posZ,
                        SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.PLAYERS,1f,1f);
            }
        }
        this.setDead();
    }

    @Inject(at = @At("TAIL"),method = "readEntityFromNBT")
    private void projectdata_readEntityFromNBT(NBTTagCompound compound, CallbackInfo ci) {
        String potentialUUID = compound.getString("playerThrownByUUID");
        try {
            this.playerUUID = UUID.fromString(potentialUUID);
        } catch (IllegalArgumentException ignored) {
            this.playerUUID = null;
        }
    }

    @Inject(at = @At("TAIL"),method = "writeEntityToNBT")
    private void projectdata_writeEntityToNBT(NBTTagCompound compound, CallbackInfo ci) {
        compound.setString("playerThrownByUUID", Objects.nonNull(this.playerUUID) ? this.playerUUID.toString() : "");
    }
}
