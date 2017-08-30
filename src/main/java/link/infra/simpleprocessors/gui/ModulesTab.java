package link.infra.simpleprocessors.gui;

import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.util.ResourceLocation;

public class ModulesTab implements IProgrammerTab {
	
	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/modulestab.png");

	@Override
	public ResourceLocation getBackgroundResource() {
		return background;
	}

	@Override
	public int getXSize() {
		return 176;
	}

	@Override
	public int getYSize() {
		return 194;
	}

}
