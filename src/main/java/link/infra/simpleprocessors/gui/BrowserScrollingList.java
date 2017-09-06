package link.infra.simpleprocessors.gui;

import java.awt.Color;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

public class BrowserScrollingList extends GuiScrollingList {
	
	private HashMap<String, Integer> fileList;
	private int currentIndex;
	private Minecraft mc;
	
	private static final int width = 100;
	private static final int height = 100;
	private static final int top = 0;
	private static final int bottom = top + height;
	private static final int left = 0;
	

	public BrowserScrollingList(Minecraft client, HashMap<String, Integer> fileList) {
		super(client, width, height, top, bottom, left, 9, 1000, 1000);
		this.fileList = fileList;
		this.mc = client;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getSize() {
		return fileList.size();
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		currentIndex = index;
	}

	@Override
	protected boolean isSelected(int index) {
		return index == currentIndex;
	}

	@Override
	protected void drawBackground() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {
		mc.fontRenderer.drawString("hi", left, slotTop, Color.darkGray.getRGB());
	}

}
