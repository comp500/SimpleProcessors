package link.infra.simpleprocessors;

import link.infra.simpleprocessors.blocks.programmer.Programmer;
import link.infra.simpleprocessors.util.SPBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	@GameRegistry.ObjectHolder("simpleprocessors:programmer")
	public static Programmer programmer;
	
	@GameRegistry.ObjectHolder("simpleprocessors:charredstone")
	public static SPBlock charredstone;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		programmer.initModel();
		charredstone.initModel();
	}

}
