package link.infra.simpleprocessors.gui;

import java.awt.Color;
import java.util.HashMap;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class BrowserTab extends ProgrammerTab {

	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/browsertab.png");
	private HashMap<String, Integer> fileList;
	private BrowserSlotList slotList;
	
	public BrowserTab() {
		super(background, "browser");
	}
	
	@Override
	public void initGui(ProgrammerGui programmerGui, ProgrammerTileEntity te, ProgrammerContainer container, FontRenderer fontRenderer) {
		fileList = te.getFileList();
		slotList = new BrowserSlotList(programmerGui.mc, fileList, fontRenderer);
		SimpleProcessors.logger.info("Gui init");
		SimpleProcessors.logger.info(te.getFileList().size());
	}
	
	@Override
	public void drawTab(ProgrammerGui gui, FontRenderer fontRenderer, ProgrammerContainer container, float partialTicks, int mouseX, int mouseY) {
		int i = 0;
		for (String s : fileList.keySet()) {
			fontRenderer.drawString(s + " " + fileList.get(s) + " B", gui.getGuiLeft() + 7, gui.getGuiTop() + 36 + (i*9), Color.darkGray.getRGB());
			i++;
		}
		slotList.drawScreen(mouseX, mouseY, partialTicks);
	}
	
}
