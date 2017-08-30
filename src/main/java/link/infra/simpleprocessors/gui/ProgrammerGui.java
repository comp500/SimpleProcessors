package link.infra.simpleprocessors.gui;

import java.util.ArrayList;
import java.util.List;

import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;

public class ProgrammerGui extends GuiContainer {

	private int currentTab = 0;
	private List<ProgrammerTab> tabs;

	public ProgrammerGui(ProgrammerTileEntity tileEntity, ProgrammerContainer container) {
		super(container);

		tabs = new ArrayList<ProgrammerTab>();
		tabs.add(new InsertTab());
		tabs.add(new ModulesTab());
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		ProgrammerTab tab = tabs.get(currentTab);
		mc.getTextureManager().bindTexture(tab.getBackgroundResource());
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, tab.getXSize(), tab.getYSize());
	}

}
