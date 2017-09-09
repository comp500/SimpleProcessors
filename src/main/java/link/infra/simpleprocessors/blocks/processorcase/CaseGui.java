package link.infra.simpleprocessors.blocks.processorcase;

import link.infra.simpleprocessors.SimpleProcessors;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class CaseGui extends GuiContainer {
	
	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/case.png");

	public CaseGui(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		
		xSize = 176;
		ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

}