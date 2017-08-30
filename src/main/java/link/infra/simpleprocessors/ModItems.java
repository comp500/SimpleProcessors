package link.infra.simpleprocessors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	public static final CreativeTabs tab = new CreativeTabs(SimpleProcessors.MODID) {
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(Items.STICK); // TODO change to computer
	    }
	};
	
	//@GameRegistry.ObjectHolder("lightcraft:solderingiron")
    //public static EcmaItem solderingiron;
	
	//@GameRegistry.ObjectHolder("lightcraft:screwdriver")
    //public static Screwdriver screwdriver;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		//solderingiron.initModel();
		//screwdriver.initModel();
	}

}
