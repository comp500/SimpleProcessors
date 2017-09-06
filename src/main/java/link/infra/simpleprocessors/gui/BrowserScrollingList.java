package link.infra.simpleprocessors.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

public class BrowserScrollingList extends GuiScrollingList {
	
	private ArrayList<String> nameList = new ArrayList<String>();
	private ArrayList<String> fileIndexList = new ArrayList<String>();
	
	private int currentIndex = 0;
	private Minecraft mc;
	
	private static final int width = 108;
	private static final int height = 156;
	

	public BrowserScrollingList(Minecraft client, HashMap<String, Integer> fileList, int left, int top, int screenWidth, int screenHeight) {
		super(client, width, height, top, (top + height), left, 12, screenWidth, screenHeight);
		this.mc = client;

		for (String s : fileList.keySet()) {
			nameList.add(s + " (" + fileList.get(s) + "B)");
			fileIndexList.add(s);
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
	
	public String getSelectedFile() {
		return fileIndexList.get(currentIndex);
	}
	
	public void refresh(HashMap<String, Integer> fileList) {
		nameList.clear();
		fileIndexList.clear();
		for (String s : fileList.keySet()) {
			nameList.add(s + " (" + fileList.get(s) + "B)");
			fileIndexList.add(s);
		}
	}

}
