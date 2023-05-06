package mods.thecomputerizer.projectdata.client.particle;

import mods.thecomputerizer.projectdata.Constants;
import mods.thecomputerizer.projectdata.common.registry.ParticleRegistry;
import mods.thecomputerizer.theimpossiblelibrary.util.client.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.vecmath.Point4f;

@SideOnly(Side.CLIENT)
public class ParticleAscii extends Particle {

    private char curChar;
    private Point4f charUV;
    public ParticleAscii(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.particleTexture = ParticleRegistry.getParticleAtlas();
        this.motionX = velocityX + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.motionY = velocityY + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.motionZ = velocityZ + (float)(Math.random() * 2.0 - 1.0) * 0.05f;
        this.particleScale = (this.rand.nextFloat() * this.rand.nextFloat() * 6.0f + 1.0f);
        this.particleMaxAge = (int)(100f / (this.rand.nextFloat() * 0.8 + 0.2));
        this.particleAlpha = 1.0f;
        this.particleTextureIndexY = 4;
        this.curChar = '0';
        this.charUV = FontUtil.getCharUV(this.curChar,Minecraft.getMinecraft().fontRenderer);
        setRBGColorF(0.5f,1f,0.5f);
        Constants.MAIN_LOG.error("INITIALIZED PARTICLE");
    }

    @Override
    public void onUpdate() {
        this.prevPosX = posX;
        this.prevPosY = posY;
        this.prevPosZ = posZ;
        if (this.particleAge++ >= this.particleMaxAge) setExpired();
        move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.8999999761581421;
        this.motionY *= 0.8999999761581421;
        this.motionZ *= 0.8999999761581421;
        if (this.onGround) {
            this.motionX *= 0.699999988079071;
            this.motionZ *= 0.699999988079071;
        }
        this.curChar = FontUtil.ASCII_CHARS.charAt(this.rand.nextInt(FontUtil.ASCII_CHARS.length()));
        this.charUV = FontUtil.getCharUV(this.curChar,Minecraft.getMinecraft().fontRenderer);
    }

    @Override
    public void renderParticle(@Nonnull BufferBuilder buffer, @Nonnull Entity entityIn, float partialTicks,
                               float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        double x = this.prevPosX+(this.posX-this.prevPosX)*partialTicks-interpPosX;
        double y = this.prevPosY+(this.posY-this.prevPosY)*partialTicks-interpPosY;
        double z = this.prevPosZ+(this.posZ-this.prevPosZ)*partialTicks-interpPosZ;
        double scaledUDDirX = rotationXY*this.particleScale;
        double scaledUDDirY = rotationZ*this.particleScale;
        double scaledUDDirZ = rotationXZ*this.particleScale;
        double scaledLRDirX = rotationX*this.particleScale;
        double scaledLRDirZ = rotationYZ*this.particleScale;
        int combinedBrightness = getBrightnessForRender(partialTicks);
        int skyLight = combinedBrightness >> 16 & 65535;
        int blockLight = combinedBrightness & 65535;
        Constants.MAIN_LOG.error("PARTICLE IS AT {} {} {}",x,y,z);
        Constants.MAIN_LOG.error("PARTICLE IS ROTATED {} {} {}",x - scaledLRDirX - scaledUDDirX,y - scaledUDDirY,z - scaledLRDirZ - scaledUDDirZ);
        buffer.pos(x - scaledLRDirX - scaledUDDirX, y - scaledUDDirY, z - scaledLRDirZ - scaledUDDirZ)
                .tex(this.charUV.y, this.charUV.w)
                .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                .lightmap(skyLight, blockLight).endVertex();
        buffer.pos(x - scaledLRDirX + scaledUDDirX,y + scaledUDDirY,z - scaledLRDirZ + scaledUDDirZ)
                .tex(this.charUV.y, this.charUV.z)
                .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                .lightmap(skyLight, blockLight).endVertex();
        buffer.pos(x + scaledLRDirX + scaledUDDirX,y + scaledUDDirY,z + scaledLRDirZ + scaledUDDirZ)
                .tex(this.charUV.x, this.charUV.z)
                .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                .lightmap(skyLight, blockLight).endVertex();
        buffer.pos(x + scaledLRDirX - scaledUDDirX,y - scaledUDDirY,z + scaledLRDirZ - scaledUDDirZ)
                .tex(this.charUV.x, this.charUV.w)
                .color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
                .lightmap(skyLight, blockLight).endVertex();
    }

    public static class Factory implements IParticleFactory {
        @Nullable
        @Override
        public Particle createParticle(int id, @Nonnull World world, double posX, double posY, double posZ, double velocityX, double velocityY, double velocityZ, @Nonnull int... args) {
            return new ParticleAscii(Minecraft.getMinecraft().world, posX, posY, posZ, velocityX, velocityY, velocityZ);
        }
    }
}
