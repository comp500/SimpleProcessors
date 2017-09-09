package link.infra.simpleprocessors.blocks.processorcase;

import link.infra.simpleprocessors.items.processor.Processor;
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
				if ((processorItem.isValidMeta(stack))) { // don't allow Invalid Item! (check before flashing as well)
					// TODO ensure has NBT
					ItemStack remainder = super.insertItem(slot, stack, simulate);
					if (remainder == ItemStack.EMPTY || remainder.isEmpty()) {
						unlockedSlots = processorItem.getAddonCount(stack);
						// TODO remove addon cards when slots count is decreased
						container.refreshSlots();
					}
					return remainder;
				}
			}
		} else {
			if (slot <= unlockedSlots) {
				// TODO check is Addon Card and valid
				return super.insertItem(slot, stack, simulate);
			}
		}
		return stack;
    }
}
