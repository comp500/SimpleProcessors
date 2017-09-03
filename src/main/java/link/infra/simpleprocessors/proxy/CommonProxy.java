package link.infra.simpleprocessors.proxy;

import link.infra.simpleprocessors.ModBlocks;
import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.blocks.programmer.Programmer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import link.infra.simpleprocessors.items.DuctTape;
import link.infra.simpleprocessors.items.SolderingIron;
import link.infra.simpleprocessors.items.processor.Processor;
import link.infra.simpleprocessors.network.PacketHandler;
import link.infra.simpleprocessors.util.SPBlock;
import link.infra.simpleprocessors.util.SPItem;
import link.infra.simpleprocessors.util.SPItemMeta;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent e) {
		PacketHandler.registerMessages(SimpleProcessors.MODID);
	}

	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(SimpleProcessors.instance, new GuiProxy());
	}

	public void postInit(FMLPostInitializationEvent e) {
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new Programmer());
		GameRegistry.registerTileEntity(ProgrammerTileEntity.class, SimpleProcessors.MODID + "_programmertile");
		event.getRegistry().register(new SPBlock("charredstone", Material.ROCK));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new Processor());
		event.getRegistry().register(new SolderingIron());
		event.getRegistry().register(new SPItemMeta("screwdriver", true, 1, 2));
		event.getRegistry().register(new DuctTape());
		event.getRegistry().register(new SPItem("processorsocket"));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.programmer).setRegistryName(ModBlocks.programmer.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.charredstone).setRegistryName(ModBlocks.charredstone.getRegistryName()));
	}
}
