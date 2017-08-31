package link.infra.simpleprocessors;

import link.infra.simpleprocessors.items.DuctTape;
import link.infra.simpleprocessors.items.SolderingIron;
import link.infra.simpleprocessors.items.processor.Processor;
import link.infra.simpleprocessors.util.SPItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	public static final CreativeTabs tab = new CreativeTabs(SimpleProcessors.MODID) {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ModItems.processor); // TODO change to computer
	    }
	};
	
	@GameRegistry.ObjectHolder("simpleprocessors:processor")
    public static Processor processor;
	
	@GameRegistry.ObjectHolder("simpleprocessors:solderingiron")
    public static SolderingIron solderingiron;
	@GameRegistry.ObjectHolder("simpleprocessors:screwdriver")
    public static SPItem screwdriver;
	@GameRegistry.ObjectHolder("simpleprocessors:ducttape")
    public static DuctTape ducttape;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		processor.initModel();
		solderingiron.initModel();
		screwdriver.initModel();
		ducttape.initModel();
	}

}
