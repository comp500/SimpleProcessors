package link.infra.simpleprocessors.gui;

import link.infra.simpleprocessors.blocks.programmer.ProgrammerContainer;
import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;

public class ProgrammerGui extends GuiContainer {

	public static final int WIDTH = 180;
	public static final int HEIGHT = 152;

	public ProgrammerGui(ProgrammerTileEntity tileEntity, ProgrammerContainer container) {
		super(container);

		xSize = WIDTH;
		ySize = HEIGHT;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub

	}

}
