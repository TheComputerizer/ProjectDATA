package mods.thecomputerizer.projectdata.common;

import mods.thecomputerizer.projectdata.Constants;
import mods.thecomputerizer.projectdata.ProjectData;
import mods.thecomputerizer.projectdata.client.capability.player.ClientCapabilitiesProvider;
import mods.thecomputerizer.projectdata.common.capability.player.PlayerCapabilitiesProvider;
import mods.thecomputerizer.projectdata.mixin.access.IEntityItemAccess;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class CommonEvents {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityPlayer) {
            event.addCapability(ProjectData.getResource("player_capabilities"), new PlayerCapabilitiesProvider());
            if (event.getObject() instanceof EntityPlayerSP)
                event.addCapability(ProjectData.getResource("client_capabilities"), new ClientCapabilitiesProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerDropItem(ItemTossEvent event) {
        if(!event.getPlayer().world.isRemote) {
            EntityItem entity = event.getEntityItem();
            if(entity.getItem().getItem()==Items.ENDER_EYE)
                ((IEntityItemAccess)entity).setThrownByPlayer(event.getPlayer().getUniqueID());
        }
    }
}
