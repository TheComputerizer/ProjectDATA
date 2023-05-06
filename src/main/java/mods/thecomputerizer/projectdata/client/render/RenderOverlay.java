package mods.thecomputerizer.projectdata.client.render;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class RenderOverlay {

    private static BlockPos savedPos;
    public static void drawLineFromPlayer() {
        if(Objects.nonNull(savedPos)) {
            //Tessellator.getInstance().getBuffer().begin(GL11.GL_LINES);
        }
    }
}
