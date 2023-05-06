package mods.thecomputerizer.projectdata.client;

import mods.thecomputerizer.projectdata.Constants;
import mods.thecomputerizer.projectdata.client.render.ClientEffects;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Constants.MODID, value = Side.CLIENT)
public class ClientEvents {

    private static boolean shaderLoaded = false;
    private static boolean screenShakePositive = true;

    @SubscribeEvent
    public static void fovUpdate(FOVUpdateEvent event) {
        //event.setNewfov(ClientEffects.getFOVAdjustment(event.getEntity(), event.getFov()));
    }

    @SubscribeEvent
    public static void screenShakeUpdate(TickEvent.PlayerTickEvent event) {
        if(event.phase==TickEvent.Phase.END && event.player==Minecraft.getMinecraft().player) {
            event.player.rotationPitch += ClientEffects.getScreenShake(event.player,screenShakePositive);
            screenShakePositive = !screenShakePositive;
            if(!shaderLoaded) {
                Minecraft.getMinecraft().entityRenderer.loadShader(ClientEffects.GRAYSCALE_SHADER);
                shaderLoaded = true;
            }
        }
    }

    @SubscribeEvent
    public static void clientDisconnected(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        shaderLoaded = false;
    }
}
