package link.infra.simpleprocessors.blocks.processorcase;

import java.util.List;

import com.google.common.collect.Lists;

import link.infra.simpleprocessors.SimpleProcessors;
import link.infra.simpleprocessors.items.Processor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class CaseGui extends GuiContainer {
	
	public static final int GUI_ID = 2;
	private static final ResourceLocation background = new ResourceLocation(SimpleProcessors.MODID, "textures/gui/case.png");
	private CaseContainer caseSlots;

	public CaseGui(Container inventorySlotsIn) {
		super(inventorySlotsIn);
		
		this.caseSlots = (CaseContainer) inventorySlotsIn;
		
		xSize = 176;
		ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		for (int i = 0; i < caseSlots.getUnlockedSlots(); i++) {
			int row = i / 4;
			int column = i % 4;
			drawTexturedModalRect(69 + (column * 18), 25 + (row * 18), 0, 166, 18, 18);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	public List<String> getItemToolTip(ItemStack stack) {
		if (stack.getItem() instanceof Processor) {
			if (!((Processor)stack.getItem()).isBootable(stack)) {
				List<String> list = stack.getTooltip(this.mc.player, this.mc.gameSettings.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL);
				List<String> newList = Lists.<String>newArrayList();
				newList.add(list.get(0)); // Add registry name + adv tooltip
				newList.add(TextFormatting.RED + I18n.format("tooltip." + SimpleProcessors.MODID + ".case.notbootable"));
				return newList;
			}
		}
		return super.getItemToolTip(stack);
    }

}
