package mods.thecomputerizer.projectdata.client.render;

import mods.thecomputerizer.projectdata.Constants;
import mods.thecomputerizer.projectdata.mixin.access.IRenderAccess;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class UnstableEntityRenderer<T extends EntityLiving> extends RenderLiving<T> {

    private final List<Class<? extends T>> potentialRenders;
    private final Random rand;
    private Class<? extends T> currentRenderClass;
    private RenderLivingBase<? extends T> currentRender;
    private T referenceEntity;
    private boolean textureSet;

    @SuppressWarnings("DataFlowIssue")
    public UnstableEntityRenderer(RenderManager manager, Class<T> classType) {
        super(manager, null, 1f);
        this.potentialRenders = manager.entityRenderMap.keySet().stream().filter(classType::isAssignableFrom)
                .map(renderClass -> (Class<? extends T>)renderClass).collect(Collectors.toList());
        this.rand = new Random();
        setNextRender();
        this.textureSet = false;
    }

    private void setNextRender() {
        int nextIndex = this.rand.nextInt(this.potentialRenders.size());
        this.currentRenderClass = potentialRenders.get(nextIndex);
        this.currentRender = ((RenderLivingBase<? extends T>)this.renderManager.entityRenderMap.get(this.currentRenderClass));
        this.mainModel = this.currentRender.getMainModel();
    }

    @Override
    public void doRender(@Nonnull T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        setNextRender();
        if (MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Pre<T>(entity, this, partialTicks, x, y, z))) return;
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        if(!this.textureSet) {
            try {
                this.referenceEntity = this.currentRenderClass.getConstructor(World.class).newInstance(entity.world);
            } catch (Exception e) {
                Constants.MAIN_LOG.error("FAILED TO INSTANTIATE REFERENCE ENTITY! ", e);
            }
        }
        if(Objects.nonNull(this.referenceEntity)) {
            this.mainModel.swingProgress = this.getSwingProgress(this.referenceEntity, partialTicks);
            boolean shouldSit = entity.isRiding() && (entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
            this.mainModel.isRiding = shouldSit;
            this.mainModel.isChild = entity.isChild();
            try {
                float f = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
                float f1 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
                float f2 = f1 - f;
                if (shouldSit && entity.getRidingEntity() instanceof EntityLivingBase) {
                    EntityLivingBase entitylivingbase = (EntityLivingBase) entity.getRidingEntity();
                    f = this.interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
                    f2 = f1 - f;
                    float f3 = MathHelper.wrapDegrees(f2);
                    if (f3 < -85.0F) f3 = -85.0F;
                    if (f3 >= 85.0F) f3 = 85.0F;
                    f = f1 - f3;
                    if (f3 * f3 > 2500.0F) f += f3 * 0.2F;
                    f2 = f1 - f;
                }
                float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
                this.renderLivingAt(entity, x, y, z);
                float f8 = this.handleRotationFloat(entity, partialTicks);
                this.applyRotations(entity, f8, f, partialTicks);
                float f4 = this.prepareScale(entity, partialTicks);
                float f5 = 0.0F;
                float f6 = 0.0F;
                if (!entity.isRiding()) {
                    f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
                    f6 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
                    if (entity.isChild()) f6 *= 3.0F;
                    if (f5 > 1.0F) f5 = 1.0F;
                    f2 = f1 - f; // Forge: Fix MC-1207
                }
                GlStateManager.enableAlpha();
                this.mainModel.setLivingAnimations(this.referenceEntity, f6, f5, partialTicks);
                this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, f4, this.referenceEntity);
                if (this.renderOutlines) {
                    boolean flag1 = this.setScoreTeamColor(entity);
                    GlStateManager.enableColorMaterial();
                    GlStateManager.enableOutlineMode(this.getTeamColor(entity));
                    if (!this.renderMarker) this.renderModel(this.referenceEntity, f6, f5, f8, f2, f7, f4);
                    this.renderLayers(this.referenceEntity, f6, f5, partialTicks, f8, f2, f7, f4);
                    GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                    if (flag1) this.unsetScoreTeamColor();
                } else {
                    boolean flag = this.setDoRenderBrightness(entity, partialTicks);
                    this.renderModel(this.referenceEntity, f6, f5, f8, f2, f7, f4);
                    if (flag) this.unsetBrightness();
                    GlStateManager.depthMask(true);
                    this.renderLayers(this.referenceEntity, f6, f5, partialTicks, f8, f2, f7, f4);
                }
                GlStateManager.disableRescaleNormal();
            } catch (Exception e) {
                Constants.MAIN_LOG.error("Couldn't render entity", e);
            }
        }
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
        if(Objects.nonNull(this.referenceEntity)) {
            if (!this.renderOutlines) this.renderName(this.referenceEntity, x, y, z);
            MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post<T>(entity, this, partialTicks, x, y, z));
            entity.world.removeEntity(this.referenceEntity);
        }
        this.textureSet = false;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull T entity) {
        this.textureSet = true;
        try {
            this.referenceEntity = this.currentRenderClass.getConstructor(World.class).newInstance(entity.world);
        } catch (Exception e) {
            Constants.MAIN_LOG.error("FAILED TO INSTANTIATE REFERENCE ENTITY FOR TEXTURE! ",e);
        }
        return ((IRenderAccess<EntityLiving>) this.currentRender).accessGetTexture(this.referenceEntity);
    }
}
