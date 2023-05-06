package mods.thecomputerizer.projectdata.client.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.mutable.MutableInt;

@SideOnly(Side.CLIENT)
public class DataBurst {


    private final World WORLD;
    private final BlockPos POS;
    private final int STRENGTH;
    private final MutableInt TIMER;

    public DataBurst(World world, BlockPos pos, int strength, int timeLeft) {
        this.WORLD = world;
        this.POS = pos;
        this.STRENGTH = strength;
        this.TIMER = new MutableInt(timeLeft);
    }

    public boolean verify() {
        return this.TIMER.getValue()>0;
    }

    public boolean tick() {
        this.TIMER.decrement();
        return verify();
    }
}
