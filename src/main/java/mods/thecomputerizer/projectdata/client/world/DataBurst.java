package mods.thecomputerizer.projectdata.client.world;

import mods.thecomputerizer.projectdata.common.registry.ParticleRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.mutable.MutableInt;

@SideOnly(Side.CLIENT)
public class DataBurst {


    private final World world;
    private final Vec3d posVec;
    private final int strength;
    private final float particleScale;
    private final MutableInt timer;

    public DataBurst(World world, double x, double y, double z, int strength, int timeLeft, float particleScale) {
        this(world,new Vec3d(x,y,z),strength,timeLeft,particleScale);
    }

    public DataBurst(World world, Vec3d posVec, int strength, int timeLeft, float particleScale) {
        this.world = world;
        this.posVec = posVec;
        this.strength = strength;
        this.particleScale = particleScale;
        this.timer = new MutableInt(timeLeft);
        boom();
    }

    public boolean verify() {
        return this.timer.getValue()>0;
    }

    public boolean tick() {
        this.timer.decrement();
        return verify();
    }

    private void boom() {
        for(int i=0;i<this.strength;i++)
            this.world.spawnParticle(ParticleRegistry.RANDOM_ASCII, this.posVec.x, this.posVec.y, this.posVec.z,
                    0.1d,0.1d,0.1d,100,i,(int)(this.particleScale*100f));
    }
}
