package link.infra.simpleprocessors.gui;

import java.util.HashMap;

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
		//fileList = te.getFileList();
		fileList = new HashMap<String, Integer>();
		for (int i = 0; i < 50; i++) {
			fileList.put("hi" + i, 10);
		}
		fileList.put("hello", 3);
		fileList.put("hi", 10);
		slotList = new BrowserScrollingList(programmerGui.mc, fileList, programmerGui.getGuiLeft() + 5, programmerGui.getGuiTop() + 33);
		SimpleProcessors.logger.info("Gui init");
		SimpleProcessors.logger.info(te.getFileList().size());
	}
	
	@Override
	public void drawTab(ProgrammerGui gui, FontRenderer fontRenderer, ProgrammerContainer container, float partialTicks, int mouseX, int mouseY) {
		slotList.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		slotList.actionPerformed(button);
	}
	
}
