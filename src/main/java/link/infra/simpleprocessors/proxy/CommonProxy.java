package link.infra.simpleprocessors.proxy;

import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		//PacketHandler.registerMessages("ecmacraft");
	}

	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(SimpleProcessors.instance, new GuiProxy());
	}

	public void postInit(FMLPostInitializationEvent e) {
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		//event.getRegistry().register(new TestBlock());
		//GameRegistry.registerTileEntity(TestBlockTile.class, SimpleProcessors.MODID + "_testblocktile");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		//event.getRegistry().register(new EcmaItem("solderingiron", true, 1));
		//event.getRegistry().register(new Screwdriver());
		//event.getRegistry().register(new ItemBlock(ModBlocks.testblock).setRegistryName(ModBlocks.testblock.getRegistryName()));
	}
}
