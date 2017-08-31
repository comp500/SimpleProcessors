package link.infra.simpleprocessors.gui;

import java.awt.Color;
import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
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
        if (currentProcessor != null && !currentProcessor.isEmpty()) {
        	fontRenderer.drawString(currentProcessor.getDisplayName(), gui.getGuiLeft() + 7, gui.getGuiTop() + 36, Color.darkGray.getRGB());
        }
	}
	
	@Override
	public void initButtons(List<GuiButton> buttonList) {
		buttonList.add(new GuiButton(0, 127, 91, 40, 15, I18n.format("button.simpleprocessors.programmer.tab.modules.upload.name")));
		buttonList.add(new GuiButton(1, 86, 91, 40, 15, I18n.format("button.simpleprocessors.programmer.tab.modules.format.name")));
	}
	
}
