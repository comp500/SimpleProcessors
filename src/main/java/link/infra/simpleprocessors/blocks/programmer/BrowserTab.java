package link.infra.simpleprocessors.blocks.programmer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class BrowserTab extends ProgrammerTab {

	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/browsertab.png");
	private HashMap<String, Integer> fileList;
	private BrowserScrollingList slotList;
	private GuiButton openButton;
	private ProgrammerTileEntity te;
	
	public BrowserTab() {
		super(background, "browser");
	}
	
	@Override
	public void initGui(ProgrammerGui programmerGui, ProgrammerTileEntity te, ProgrammerContainer container, FontRenderer fontRenderer) {
		this.te = te;
		fileList = te.getFileList();
		slotList = new BrowserScrollingList(programmerGui.mc, fileList, programmerGui.getGuiLeft() + 5, programmerGui.getGuiTop() + 33, programmerGui.width, programmerGui.height);
		SimpleProcessors.logger.info("Gui init");
		SimpleProcessors.logger.info(te.getFileList().size());
		
		if (fileList.size() == 0) {
			openButton.enabled = false;
		}
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
			refresh();
		} else if (button.id == 0) {
			te.openLocal();
		} else if (button.id == 2) {
			te.readLocal();
			fileList = te.getFileList();
			refresh();
		}
	}
	
	public void refresh() {
		slotList.refresh(fileList);
		if (fileList.size() == 0) {
			openButton.enabled = false;
		} else {
			openButton.enabled = true;
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
		openButton = new GuiButton(0, guiLeft + 115, guiTop + 33, 56, 20, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.browser.open.name"));
		buttonList.add(openButton);
		buttonList.add(new GuiButton(1, guiLeft + 115, guiTop + 55, 56, 20, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.browser.new.name")));
		buttonList.add(new GuiButton(2, guiLeft + 115, guiTop + 77, 56, 20, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.browser.new.name")));
	}
	
}
