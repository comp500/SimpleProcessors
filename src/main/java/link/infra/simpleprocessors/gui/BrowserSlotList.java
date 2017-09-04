package link.infra.simpleprocessors.gui;

import java.awt.Color;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiSlot;

public class BrowserSlotList extends GuiSlot {
	
	private HashMap<String, Integer> fileList;
	private FontRenderer fontRenderer;

	public BrowserSlotList(Minecraft mcIn, HashMap<String, Integer> fileList, FontRenderer fontRenderer) {
		super(mcIn, 50, 50, 100, 100, 9);
		this.fileList = fileList;
		this.fontRenderer = fontRenderer;
	}

	@Override
	protected int getSize() {
		return fileList.size();
	}

	@Override
	protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isSelected(int slotIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void drawBackground() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawSlot(int slotIndex, int x, int y, int slotHeight, int mouseX, int mouseY, float partialTicks) {
		fontRenderer.drawString("index.js", x, y, Color.darkGray.getRGB());
	}

}
