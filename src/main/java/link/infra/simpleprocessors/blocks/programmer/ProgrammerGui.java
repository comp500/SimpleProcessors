package link.infra.simpleprocessors.blocks.programmer;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;

public class ProgrammerGui extends GuiContainer {
	
	public static final int GUI_ID = 1;

	private int currentTab = 0;
	private ArrayList<ProgrammerTab> tabs;
	protected ProgrammerContainer container;
	private ProgrammerTileEntity te;

	public ProgrammerGui(ProgrammerTileEntity tileEntity, ProgrammerContainer container) {
		super(container);
		
		this.container = container;
		this.te = tileEntity;

		tabs = new ArrayList<ProgrammerTab>();
		tabs.add(new InsertTab());
		tabs.add(new BrowserTab());
		tabs.add(new ModulesTab());
	}
	
	@Override
	public void initGui() {
		switchTab(currentTab);
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
		tab.drawTab(this, fontRenderer, container, partialTicks, mouseX, mouseY);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		tabs.get(currentTab).actionPerformed(button);
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
		tab.initGui(this, te, container, fontRenderer);
	}
	
	@Override
	public void handleMouseInput() throws IOException {
		int i = Mouse.getEventX() * this.width / this.mc.displayWidth;
        int j = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
		tabs.get(currentTab).handleMouseInput(this, i, j);
		super.handleMouseInput();
	}

}
