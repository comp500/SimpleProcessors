package link.infra.simpleprocessors.blocks.processorcase;

import link.infra.simpleprocessors.items.AddonCard;
import link.infra.simpleprocessors.items.Processor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class CaseStackHandler extends ItemStackHandler {
	public int unlockedSlots = 0;
	private CaseContainer container;
	
	public CaseStackHandler(CaseContainer container) {
		super(9);
		this.container = container;
	}
	
	@Override
	public int getSlotLimit(int slot) {
		return 1; // Only allow one item in each slot
	}
	
	@Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		Item stackItem = stack.getItem();
		if (slot == 0) {
			if (stackItem instanceof Processor) { // Only allow processors
				Processor processorItem = (Processor) stackItem;
				if ((processorItem.isValidMeta(stack))) { // don't allow Invalid Item!
					if (processorItem.isBootable(stack)) { // only allow bootable processors
						ItemStack remainder = super.insertItem(slot, stack, simulate);
						if (remainder == ItemStack.EMPTY || remainder.isEmpty()) {
							unlockedSlots = processorItem.getAddonCount(stack);
							// TODO remove addon cards when slots count is decreased, eject cards when processor removed
							container.refreshSlots();
						}
						return remainder;
					}
				}
			}
		} else {
			if (slot <= unlockedSlots) {
				if (stackItem instanceof AddonCard) {
					if (((AddonCard) stackItem).isValidMeta(stack)) {
						return super.insertItem(slot, stack, simulate);
					}
				}
			}
		}
		return stack;
    }
}
