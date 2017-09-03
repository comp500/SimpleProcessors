package link.infra.simpleprocessors.gui;

import java.awt.Color;
import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.items.processor.Processor;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class InsertTab extends ProgrammerTab {
	
	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/inserttab.png");
	
	public InsertTab() {
		super(background, "insert");
	}
	
	@Override
	public boolean hasSlot() {
		return true;
	}
	
	@Override
	public void drawTab(ProgrammerGui gui, FontRenderer fontRenderer, ProgrammerContainer container) {
		ItemStack currentProcessor = container.inputStackHandler.getStackInSlot(0);
        if (currentProcessor != null && !currentProcessor.isEmpty() && currentProcessor.getItem() instanceof Processor) {
        	Processor proc = (Processor) currentProcessor.getItem();
        	fontRenderer.drawString(currentProcessor.getDisplayName(), gui.getGuiLeft() + 7, gui.getGuiTop() + 36, Color.darkGray.getRGB());
        	fontRenderer.drawString(I18n.format("tooltip." + SimpleProcessors.MODID + ".item.processor.storage.name") + ": " + proc.getStorage(currentProcessor) + " KB", gui.getGuiLeft() + 7, gui.getGuiTop() + 45, Color.darkGray.getRGB());
        	fontRenderer.drawString(I18n.format("tooltip." + SimpleProcessors.MODID + ".item.processor.addon.name") + ": " + proc.getAddonCount(currentProcessor), gui.getGuiLeft() + 7, gui.getGuiTop() + 54, Color.darkGray.getRGB());
        } else {
        	fontRenderer.drawString(I18n.format("label." + SimpleProcessors.MODID + ".programmer.tab.insert.insertproc.name"), gui.getGuiLeft() + 7, gui.getGuiTop() + 36, Color.darkGray.getRGB());
        }
	}
	
	@Override
	public void initButtons(List<GuiButton> buttonList, ProgrammerGui gui) {
		int guiLeft = gui.getGuiLeft();
		int guiTop = gui.getGuiTop();
		buttonList.add(new GuiButton(0, guiLeft + 127, guiTop + 91, 40, 15, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.insert.upload.name")));
		buttonList.add(new GuiButton(1, guiLeft + 86, guiTop + 91, 40, 15, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.insert.format.name")));
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			SimpleProcessors.logger.debug("Button 0 pressed");
		} else if (button.id == 1) {
			SimpleProcessors.logger.debug("Button 1 pressed");
		}
	}
	
}
