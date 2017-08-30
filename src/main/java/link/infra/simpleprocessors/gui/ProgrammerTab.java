package link.infra.simpleprocessors.gui;

import net.minecraft.util.ResourceLocation;

public class ProgrammerTab {
	
	private int xSize;
	private int ySize;
	private ResourceLocation background;
	
	public ProgrammerTab(ResourceLocation background) {
		this.background = background;
		this.xSize = 176;
		this.ySize = 194;
	}
	
	public ProgrammerTab(ResourceLocation background, int xSize, int ySize) {
		this.background = background;
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	public ResourceLocation getBackgroundResource() {
		return background;
	}
	
	public int getXSize() {
		return xSize;
	}
	
	public int getYSize() {
		return ySize;
	}

}
