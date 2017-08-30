package link.infra.simpleprocessors.gui;

import java.util.ArrayList;
import java.util.List;

import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;

public class ProgrammerGui extends GuiContainer {
	
	private int currentTab = 0;
	private List<IProgrammerTab> tabs;

	public ProgrammerGui(ProgrammerTileEntity tileEntity, ProgrammerContainer container) {
		super(container);

		tabs = new ArrayList<IProgrammerTab>();
		tabs.add(new InsertTab());
		tabs.add(new ModulesTab());
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		IProgrammerTab tab = tabs.get(currentTab);
		mc.getTextureManager().bindTexture(tab.getBackgroundResource());
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, tab.getXSize(), tab.getYSize());
	}

}
