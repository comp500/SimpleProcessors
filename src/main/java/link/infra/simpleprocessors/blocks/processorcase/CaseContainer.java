package link.infra.simpleprocessors.blocks.processorcase;

import javax.annotation.Nullable;

import link.infra.simpleprocessors.blocks.programmer.ProgrammerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class CaseContainer extends Container {

	// TODO rewrite to use CaseTileEntity
	private ProgrammerTileEntity te;
	private IInventory playerInv;
	
	public CaseStackHandler caseStackHandler = new CaseStackHandler(this);

	public CaseContainer(IInventory playerInventory, ProgrammerTileEntity te) {
		this.te = te;
		this.playerInv = playerInventory;
		
		addOwnSlots();
		addPlayerSlots(playerInventory);
	}


	private void addPlayerSlots(IInventory playerInventory) {
		// Slots for the main inventory
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				int x = 8 + col * 18;
				int y = row * 18 + 112;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y));
			}
		}

		// Slots for the hotbar
		for (int row = 0; row < 9; ++row) {
			int x = 8 + row * 18;
			int y = 170;
			this.addSlotToContainer(new Slot(playerInventory, row, x, y));
		}
	}

	private void addOwnSlots() {
		addSlotToContainer(new SlotItemHandler(caseStackHandler, 0, 146, 41));
		for (int i = 0; i < caseStackHandler.unlockedSlots; i++) {
			addSlotToContainer(new SlotItemHandler(caseStackHandler, 0, 146, 41));
		}
	}

	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		
		// TODO fix for multiple slots
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
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
	
	public void refreshSlots() {
		inventorySlots.clear();
		inventoryItemStacks.clear();
		addOwnSlots();
		addPlayerSlots(playerInv);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		// TODO drop all items, rather than just slot 0
		if (!playerIn.isEntityAlive() || playerIn instanceof EntityPlayerMP && ((EntityPlayerMP)playerIn).hasDisconnected()) {
			playerIn.dropItem(caseStackHandler.extractItem(0, 64, false), false);
		} else {
			playerIn.inventory.placeItemBackInInventory(playerIn.getEntityWorld(), caseStackHandler.extractItem(0, 64, false));
		}
	}
	
}
