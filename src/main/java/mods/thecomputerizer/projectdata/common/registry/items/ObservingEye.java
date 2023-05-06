package mods.thecomputerizer.projectdata.common.registry.items;

import mods.thecomputerizer.projectdata.Constants;
import mods.thecomputerizer.projectdata.ProjectData;
import mods.thecomputerizer.projectdata.client.capability.ClientCapabilityHandler;
import mods.thecomputerizer.projectdata.common.CommonEffects;
import mods.thecomputerizer.theimpossiblelibrary.util.object.ItemUtil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public class ObservingEye extends Item {

    public static double TIME_FACTOR = 20d;

    public ObservingEye() {
        addPropertyOverride(ProjectData.getResource("eye_property"),this::getPropertyOverride);
    }

    private float getPropertyOverride(ItemStack stack, World world, EntityLivingBase entityUsing) {
        return ItemUtil.getOrCreateTag(stack).getBoolean("is_corrupted_eye") ? 1f : 0f;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, @Nonnull EntityPlayer player, @Nonnull EnumHand hand) {
        player.setActiveHand(hand);
        ItemStack stack = player.getHeldItem(hand);
        double store = (Math.abs(player.motionX)+Math.abs(player.motionY)+Math.abs(player.motionZ));
        Constants.MAIN_LOG.error("STORING {} MOTION",store);
        if(world.isRemote) ClientCapabilityHandler.getPlayerCapabilities(player).storeMotion(store);
        return ActionResult.newResult(EnumActionResult.SUCCESS,stack);
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack) {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull EntityLivingBase entity, int timeLeft) {
        if(entity instanceof EntityPlayer) {
            CommonEffects.setMovementFactor((EntityPlayer) entity, 1d);
            if(world.isRemote) {
                double storedMotion = ClientCapabilityHandler.getPlayerCapabilities((EntityPlayer) entity).getStoredMotion();
                int adjusted = stack.getMaxItemUseDuration() - timeLeft - 20;
                if (adjusted > 0) {
                    double strength = ((double) adjusted) / TIME_FACTOR;
                    Vec3d dir = entity.getLookVec();
                    double x = dir.x*storedMotion*strength;
                    double y = dir.y*storedMotion*strength;
                    double z = dir.z*storedMotion*strength;
                    entity.motionX = x;
                    entity.motionY = y;
                    entity.motionZ = z;
                    Constants.MAIN_LOG.error("APPLIED MOTION IS {} {} {}, IS CLIENT {}", x, y, z, entity instanceof EntityPlayerSP);
                    ClientCapabilityHandler.getPlayerCapabilities((EntityPlayer) entity).resetMotion();
                }
            }
            ((EntityPlayer)entity).getCooldownTracker().setCooldown(this, 100);
        }
    }

    @Override
    public void onUsingTick(@Nonnull ItemStack stack, @Nonnull EntityLivingBase entity, int count) {
        if(entity instanceof EntityPlayer) {
            double usageFactor = getUsingTicks(entity, stack) >= 20 ? 0d : 1d - (((double) getUsingTicks(entity, stack)) / 20d);
            CommonEffects.setMovementFactor((EntityPlayer) entity, usageFactor);
            if (usageFactor == 0d && entity.world.isRemote) {
                double storedMotion = ClientCapabilityHandler.getPlayerCapabilities((EntityPlayer)entity).getStoredMotion();
                double strength = (((double) getUsingTicks(entity, stack) - 20d) * storedMotion) / TIME_FACTOR;
                Constants.MAIN_LOG.error("STRENGTH IS {} FROM {} IS CLIENT {}", strength, getUsingTicks(entity, stack),
                        entity instanceof EntityPlayerSP);
            }
        }
    }

    private int getUsingTicks(@Nonnull EntityLivingBase entity, @Nonnull ItemStack stack) {
        return stack.getMaxItemUseDuration()-entity.getItemInUseCount();
    }

    @Override
    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public @Nonnull String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String regName = ItemUtil.getOrCreateTag(stack).getBoolean("is_corrupted_eye") ?
                "corrupted_eye" : "observing_eye";
        return I18n.translateToLocal("item.projectdata."+regName+".name").trim();
    }
}
