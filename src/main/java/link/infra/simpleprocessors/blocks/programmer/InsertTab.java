package link.infra.simpleprocessors.blocks.programmer;

import java.awt.Color;
import java.util.List;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.items.Processor;
import link.infra.simpleprocessors.network.PacketHandler;
import link.infra.simpleprocessors.network.PacketProgrammerAction;
import link.infra.simpleprocessors.network.PacketProgrammerAction.ProgrammerAction;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class InsertTab extends ProgrammerTab {
	
	private ProgrammerTileEntity te;
	
	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/inserttab.png");
	
	public InsertTab(ProgrammerTileEntity te) {
		super(background, "insert");
		this.te = te;
	}
	
	@Override
	public boolean hasSlot() {
		return true;
	}
	
	@Override
	public void drawTab(ProgrammerGui gui, FontRenderer fontRenderer, ProgrammerContainer container, float partialTicks, int mouseX, int mouseY) {
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
		buttonList.add(new GuiButton(0, guiLeft + 127, guiTop + 89, 42, 20, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.insert.upload.name")));
		buttonList.add(new GuiButton(1, guiLeft + 84, guiTop + 89, 42, 20, I18n.format("button." + SimpleProcessors.MODID + ".programmer.tab.insert.format.name")));
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			PacketHandler.INSTANCE.sendToServer(new PacketProgrammerAction(ProgrammerAction.FLASH_PROCESSOR, te.getPos()));
			SimpleProcessors.logger.info("Button 0 pressed");
		} else if (button.id == 1) {
			PacketHandler.INSTANCE.sendToServer(new PacketProgrammerAction(ProgrammerAction.WIPE_PROCESSOR, te.getPos()));
			SimpleProcessors.logger.info("Button 1 pressed");
		}
	}
	
}
