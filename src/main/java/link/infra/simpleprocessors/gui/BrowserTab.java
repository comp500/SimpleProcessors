package link.infra.simpleprocessors.gui;

import java.util.HashMap;
import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class BrowserTab extends ProgrammerTab {

	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/browsertab.png");
	private HashMap<String, Integer> fileList;
	private BrowserScrollingList slotList;
	
	public BrowserTab() {
		super(background, "browser");
	}
	
	@Override
	public void initGui(ProgrammerGui programmerGui, ProgrammerTileEntity te, ProgrammerContainer container, FontRenderer fontRenderer) {
		fileList = te.getFileList();
		slotList = new BrowserScrollingList(programmerGui.mc, fileList);
		SimpleProcessors.logger.info("Gui init");
		SimpleProcessors.logger.info(te.getFileList().size());
	}
	
	@Override
	public void drawTab(ProgrammerGui gui, FontRenderer fontRenderer, ProgrammerContainer container, float partialTicks, int mouseX, int mouseY) {
		/*int i = 0;
		for (String s : fileList.keySet()) {
			fontRenderer.drawString(s + " " + fileList.get(s) + " B", gui.getGuiLeft() + 7, gui.getGuiTop() + 36 + (i*9), Color.darkGray.getRGB());
			i++;
		}*/
		slotList.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initButtons(List<GuiButton> buttonList, ProgrammerGui gui) {
		slotList.registerScrollButtons(buttonList, 0, 1);
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		slotList.actionPerformed(button);
	}
	
}
