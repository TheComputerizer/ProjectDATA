package mods.thecomputerizer.projectdata.client.render;

import mods.thecomputerizer.projectdata.ProjectData;
import mods.thecomputerizer.projectdata.common.CommonEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientEffects {
    public static final ResourceLocation GRAYSCALE_SHADER = ProjectData.getResource("shaders/post/dynamic_color_overlay.json");

    public static float getFOVAdjustment(EntityPlayer player, float fov) {
        float factor = (float) CommonEffects.getMovementFactor(player);
        return fov*((factor/2f)+0.5f);
    }

    public static float getColorCorrection(EntityPlayer player) {
        return 1f-(float)CommonEffects.getMovementFactor(player);
    }

    public static float getScreenShake(EntityPlayer player, boolean positive) {
        float factor = (float) (1d-CommonEffects.getMovementFactor(player))/2f;
        return positive ? factor : factor*-1;
    }
}
