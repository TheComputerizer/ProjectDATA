package mods.thecomputerizer.projectdata.common.registry;

import mods.thecomputerizer.projectdata.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@GameRegistry.ObjectHolder(Constants.MODID)
@Mod.EventBusSubscriber(modid = Constants.MODID)
public final class RegistryHandler {

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        ItemRegistry.register(event);
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        BlockRegistry.register(event);
        TileRegistry.register();
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityEntry> event) {
        EntityRegistry.register();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        ItemRegistry.registerModels();
        BlockRegistry.registerModels();
    }
}
