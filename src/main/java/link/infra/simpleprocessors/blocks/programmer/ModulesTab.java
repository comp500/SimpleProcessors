package link.infra.simpleprocessors.blocks.programmer;

import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.util.ResourceLocation;

public class ModulesTab extends ProgrammerTab {
	
	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/modulestab.png");
	
	public ModulesTab() {
		super(background, "modules");
	}

}
