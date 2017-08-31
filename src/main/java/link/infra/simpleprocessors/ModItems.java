package link.infra.simpleprocessors;

import link.infra.simpleprocessors.items.processor.Processor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	public static final CreativeTabs tab = new CreativeTabs(SimpleProcessors.MODID) {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(Items.STICK); // TODO change to computer
	    }
	};
	
	@GameRegistry.ObjectHolder("simpleprocessors:processor")
    public static Processor processor;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		processor.initModel();
	}

}
