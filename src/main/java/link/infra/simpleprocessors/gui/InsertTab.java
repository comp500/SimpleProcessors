package link.infra.simpleprocessors.gui;

import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.util.ResourceLocation;

public class InsertTab extends ProgrammerTab {
	
	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/inserttab.png");
	
	public InsertTab() {
		super(background, "insert");
	}
	
}
