package link.infra.simpleprocessors.blocks.programmer;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ProgrammerContainer extends Container {

	private ProgrammerTileEntity te;
	private Slot processorSlot;
	private boolean usable = true;
	
	private ItemStackHandler inputStackHandler = new ItemStackHandler(1);

	public ProgrammerContainer(IInventory playerInventory, ProgrammerTileEntity te) {
		this.te = te;
		
		addOwnSlots();
		addPlayerSlots(playerInventory);
	}


	private void addPlayerSlots(IInventory playerInventory) {
		// Slots for the main inventory
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				int x = 9 + col * 18;
				int y = row * 18 + 70;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
			}
		}

		// Slots for the hotbar
		for (int row = 0; row < 9; ++row) {
			int x = 9 + row * 18;
			int y = 58 + 70;
			this.addSlotToContainer(new Slot(playerInventory, row, x, y));
		}
	}

	private void addOwnSlots() {
		processorSlot = new SlotItemHandler(inputStackHandler, 0, 146, 41);
		addSlotToContainer(processorSlot);
	}

	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (usable && slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 1) {
				if (!this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.canInteractWith(playerIn);
	}
	
	public void setUsable(boolean usable) {
		this.usable = usable;
		if (usable) {
			processorSlot.xPos = 146;
		} else {
			processorSlot.xPos = -999; // make slot invisible
		}
	}

}
