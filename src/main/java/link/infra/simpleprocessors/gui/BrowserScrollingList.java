package link.infra.simpleprocessors.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

public class BrowserScrollingList extends GuiScrollingList {
	
	private ArrayList<String> nameList = new ArrayList<String>();
	
	private int currentIndex;
	private Minecraft mc;
	
	private static final int width = 108;
	private static final int height = 156;
	

	public BrowserScrollingList(Minecraft client, HashMap<String, Integer> fileList, int left, int top) {
		super(client, width, height, top, (top + height), left, 12, 1000, 1000);
		this.mc = client;

		for (String s : fileList.keySet()) {
			nameList.add(s + " (" + fileList.get(s) + "B)");
		}
	}

	@Override
	protected int getSize() {
		return nameList.size();
	}

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		if (currentIndex == index) {
			System.out.println("clicked " + index);
		}
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
		mc.fontRenderer.drawString(nameList.get(slotIdx), left + 2, slotTop + 1, Color.white.getRGB());
	}

}
