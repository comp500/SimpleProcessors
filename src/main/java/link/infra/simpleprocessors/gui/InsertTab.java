package link.infra.simpleprocessors.gui;

import java.awt.Color;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import net.minecraft.client.gui.FontRenderer;
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
        	gui.drawCenteredString(fontRenderer, currentProcessor.getDisplayName(), gui.getGuiLeft() + 71, gui.getGuiTop() + 36, Color.darkGray.getRGB());
        }
	}
	
}
