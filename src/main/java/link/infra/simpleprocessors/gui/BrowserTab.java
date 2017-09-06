package link.infra.simpleprocessors.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
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
		fileList.put("hello", 3);
		fileList.put("hi", 10);
		slotList = new BrowserScrollingList(programmerGui.mc, fileList, programmerGui.getGuiLeft() + 5, programmerGui.getGuiTop() + 33, programmerGui.width, programmerGui.height);
		SimpleProcessors.logger.info("Gui init");
		SimpleProcessors.logger.info(te.getFileList().size());
	}
	
	@Override
	public void drawTab(ProgrammerGui gui, FontRenderer fontRenderer, ProgrammerContainer container, float partialTicks, int mouseX, int mouseY) {
		slotList.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		//slotList.actionPerformed(button);
		if (button.id == 1) {
			String name = "unnamed";
			int i = 1;
			while (fileList.containsKey(name)) {
				name = "unnamed_" + i;
				i++;
			}
			fileList.put(name, 0);
			slotList.refresh(fileList);
		}
	}
	
	@Override
	public void handleMouseInput(ProgrammerGui programmerGui, int x, int y) throws IOException {
		slotList.handleMouseInput(x, y);
	}
	
	@Override
	public void initButtons(List<GuiButton> buttonList, ProgrammerGui gui) {
		int guiLeft = gui.getGuiLeft();
		int guiTop = gui.getGuiTop();
		buttonList.add(new GuiButton(0, guiLeft + 115, guiTop + 33, 56, 20, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.browser.open.name")));
		buttonList.add(new GuiButton(1, guiLeft + 115, guiTop + 55, 56, 20, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.browser.new.name")));
	}
	
}
