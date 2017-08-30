package link.infra.simpleprocessors;

import link.infra.simpleprocessors.blocks.programmer.Programmer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	@GameRegistry.ObjectHolder("simpleprocessors:programmer")
	public static Programmer programmer;
	
	@SideOnly(Side.CLIENT)
    public static void initModels() {
		programmer.initModel();
	}

}
