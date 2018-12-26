package com.cubic_control.c_companions.gui;

import com.cubic_control.c_companions.entities.EntityCompanion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerInventory extends Container{
	protected final EntityCompanion companion;

	public ContainerInventory(IInventory playerInv, IInventory compInv, EntityCompanion comp)
	{
		this.companion = comp;
		
		for(int inventoryHeight = 0; inventoryHeight < 4; ++inventoryHeight)
		{
			for(int inventoryWidth = 0; inventoryWidth < 9; ++inventoryWidth)
			{
				addSlotToContainer(new Slot(compInv, inventoryWidth + inventoryHeight * 9, 33 + inventoryWidth * 18, 18 + inventoryHeight * 18));
			}
		}
		bindPlayerInventory((InventoryPlayer)playerInv);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
	{
		final Slot slot = (Slot) inventorySlots.get(slotId);
		ItemStack transferStack = null;

		if(slot != null && slot.getHasStack())
		{
			final ItemStack slotStack = slot.getStack();
			transferStack = slotStack.copy();

			if(slotId < 4 * 9)
			{
				if(!mergeItemStack(slotStack, 4 * 9, inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if(!mergeItemStack(slotStack, 0, 4 * 9, false))
			{
				return null;
			}

			if(slotStack.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return transferStack;
	}
	
	private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
	{
		for(int inventoryHeight = 0; inventoryHeight < 3; inventoryHeight++)
		{
			for(int inventoryWidth = 0; inventoryWidth < 9; inventoryWidth++)
			{
				addSlotToContainer(new Slot(inventoryPlayer, inventoryWidth + inventoryHeight * 9 + 9, 33 + inventoryWidth * 18, 103 + inventoryHeight * 18));
			}
		}

		for(int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, 33 + i * 18, 161));
		}
	}

}
