package link.infra.simpleprocessors.blocks.programmer;

import java.io.IOException;
import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class ProgrammerTab {
	
	private int xSize;
	private int ySize;
	private ResourceLocation background;
	private String name;
	
	public ProgrammerTab(ResourceLocation background, String name) {
		this.background = background;
		this.xSize = 176;
		this.ySize = 194;
		this.name = name;
	}
	
	public ProgrammerTab(ResourceLocation background, String name, int xSize, int ySize) {
		this.background = background;
		this.xSize = xSize;
		this.ySize = ySize;
		this.name = name;
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
	
	public int checkTabClicked(int mouseX, int mouseY) {
		if (mouseY < 30 && mouseY > 0) {
			if (mouseX < 86 && mouseX > 0) {
				return mouseX / 29;
			}
		}
		return -1;
	}
	
	public String getUnlocalizedName() {
		return "tooltip." + SimpleProcessors.MODID + ".programmer.tab." + name + ".name";
	}
	
	public boolean hasSlot() {
		return false;
	}
	
	public void drawTab(ProgrammerGui gui, FontRenderer fontRenderer, ProgrammerContainer container, float partialTicks, int mouseX, int mouseY) {
		// stub
	}
	
	public void initButtons(List<GuiButton> buttonList, ProgrammerGui gui) {
		// stub
	}
	
	public void actionPerformed(GuiButton button) {
		// stub
	}

	public void initGui(ProgrammerGui programmerGui, ProgrammerTileEntity te, ProgrammerContainer container, FontRenderer fontRenderer) {
		// stub
	}

	public void handleMouseInput(ProgrammerGui programmerGui, int x, int y) throws IOException {
		// stub
	}

}
