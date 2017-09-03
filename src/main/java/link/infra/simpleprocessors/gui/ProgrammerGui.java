package link.infra.simpleprocessors.gui;

import java.util.ArrayList;

import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;

public class ProgrammerGui extends GuiContainer {

	private int currentTab;
	private ArrayList<ProgrammerTab> tabs;
	protected ProgrammerContainer container;

	public ProgrammerGui(ProgrammerTileEntity tileEntity, ProgrammerContainer container) {
		super(container);
		
		this.container = container;

		tabs = new ArrayList<ProgrammerTab>();
		tabs.add(new InsertTab());
		tabs.add(new BrowserTab());
		tabs.add(new ModulesTab());
	}
	
	@Override
	public void initGui() {
		switchTab(0);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
		
		int i = mouseX - this.guiLeft;
        int j = mouseY - this.guiTop;
        ProgrammerTab tab = tabs.get(currentTab);
		int newTab = tab.checkTabClicked(i, j);
        if (newTab != -1 && newTab < tabs.size()) {
        	this.drawHoveringText(I18n.format(tabs.get(newTab).getUnlocalizedName()), mouseX, mouseY);
        }
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		ProgrammerTab tab = tabs.get(currentTab);
		mc.getTextureManager().bindTexture(tab.getBackgroundResource());
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, tab.getXSize(), tab.getYSize());
		tab.drawTab(this, fontRenderer, container);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		if (state == 0) {
			int i = mouseX - this.guiLeft;
            int j = mouseY - this.guiTop;
            int newTab = tabs.get(currentTab).checkTabClicked(i, j);
            if (newTab != -1 && newTab < tabs.size()) {
            	switchTab(newTab);
            	return;
            }
		}
		super.mouseReleased(mouseX, mouseY, state);
	}
	
	private void switchTab(int newTab) {
		currentTab = newTab;
		
		ProgrammerTab tab = tabs.get(newTab);
		xSize = tab.getXSize();
		ySize = tab.getYSize();
		
		// reinit screen
		super.initGui();
		
		this.buttonList.clear();
		tab.initButtons(buttonList, this);
		container.setUsable(tab.hasSlot());
	}

}
