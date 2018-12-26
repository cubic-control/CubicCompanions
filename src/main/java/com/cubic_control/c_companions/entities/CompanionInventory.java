package com.cubic_control.c_companions.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CompanionInventory implements IInventory{
	private EntityCompanion entity;
	
	// Based On InventoryPlayer.class
	public ItemStack[] mainInventory = new ItemStack[45];
	public ItemStack[] armorInventory = new ItemStack[4];
	public ItemStack currentStack;
	public boolean inventoryChanged;
	
	public CompanionInventory(EntityCompanion comp)
	{
		this.entity = comp;
	}
	
	public ItemStack getCurrentItem()
    {
        return this.currentStack;
    }

	public int getFirstEmptyStack()
    {
        for(int i = 0; i < this.mainInventory.length; ++i)
        {
            if(this.mainInventory[i] == null)
            {
                return i;
            }
        }
        return -1;
    }
	
	@Override
	public int getSizeInventory() {
		return this.mainInventory.length + 5;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		ItemStack[] stack = this.mainInventory;
		
        if(slot >= stack.length)
        {
            slot -= stack.length;
            //stack = this.armorInventory;
        }
        
        if(slot == -1)
        {
        	return null;
        }
        return stack[slot];
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		ItemStack[] stack = this.mainInventory;

        if(p_70298_1_ >= this.mainInventory.length)
        {
            p_70298_1_ -= this.mainInventory.length;
        }

        if(stack[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if(stack[p_70298_1_].stackSize <= p_70298_2_)
            {
                itemstack = stack[p_70298_1_];
                stack[p_70298_1_] = null;
                return itemstack;
            }
            else
            {
                itemstack = stack[p_70298_1_].splitStack(p_70298_2_);
                
                if(stack[p_70298_1_].stackSize == 0)
                {
                	stack[p_70298_1_] = null;
                }
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack[] stack = this.mainInventory;

        if(slot >= this.mainInventory.length)
        {
            slot -= this.mainInventory.length;
        }

        if(stack[slot] != null)
        {
            ItemStack itemstack = stack[slot];
            stack[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		ItemStack[] aitemstack = this.mainInventory;

        if(slot >= aitemstack.length)
        {
            slot -= aitemstack.length;
        }
        
        aitemstack[slot] = stack;
	}

	@Override
	public String getInventoryName() {
		return "container.companionInventory";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public void dropAllItems()
    {
        int i;

        for (i = 0; i < this.mainInventory.length; ++i)
        {
            if (this.mainInventory[i] != null)
            {
                this.entity.func_146097_a(this.mainInventory[i], true, false);
                this.mainInventory[i] = null;
            }
        }
    }
	
	@Override
	public void markDirty() {
		this.inventoryChanged = true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.entity.isDead ? false : entity.getDistanceSqToEntity(player) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
	
	public NBTTagList writeToNBT(NBTTagList list)
    {
        int i;
        NBTTagCompound nbttagcompound;
        
        for(i = 0; i < this.mainInventory.length; ++i)
        {
            if(this.mainInventory[i] != null)
            {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.mainInventory[i].writeToNBT(nbttagcompound);
                list.appendTag(nbttagcompound);
            }
        }
        for (i = 0; i < this.armorInventory.length; ++i)
        {
            if (this.armorInventory[i] != null)
            {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)(i + 100));
                this.armorInventory[i].writeToNBT(nbttagcompound);
                list.appendTag(nbttagcompound);
            }
        }
        if(this.currentStack != null)
        {
        	nbttagcompound = new NBTTagCompound();
            nbttagcompound.setByte("Slot", (byte)(i + 200));
            this.currentStack.writeToNBT(nbttagcompound);
            list.appendTag(nbttagcompound);
        }
        return list;
    }
	
	public void readFromNBT(NBTTagList list)
    {
        this.mainInventory = new ItemStack[36];
        this.armorInventory = new ItemStack[4];

        for(int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = list.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

            if(itemstack != null)
            {
                if(j >= 0 && j < this.mainInventory.length)
                {
                    this.mainInventory[j] = itemstack;
                }
                
                if (j >= 100 && j < this.armorInventory.length + 100)
                {
                    this.armorInventory[j - 100] = itemstack;
                }
                if(j == 200)
                {
                	this.currentStack = itemstack;
                }
            }
        }
    }
	
	public int getTotalArmorValue()
    {
        int i = 0;
        
        for (int j = 0; j < this.armorInventory.length; ++j)
        {
            if (this.armorInventory[j] != null && this.armorInventory[j].getItem() instanceof ItemArmor)
            {
                int k = ((ItemArmor)this.armorInventory[j].getItem()).damageReduceAmount;
                i += k;
            }
        }

        return i;
    }
	
	public void damageArmor(float damage)
    {
		damage /= 4.0F;

        if (damage < 1.0F)
        {
        	damage = 1.0F;
        }
        for (int i = 0; i < this.armorInventory.length; ++i)
        {
            if (this.armorInventory[i] != null && this.armorInventory[i].getItem() instanceof ItemArmor)
            {
                this.armorInventory[i].damageItem((int)damage, this.entity);

                if (this.armorInventory[i].stackSize == 0)
                {
                    this.armorInventory[i] = null;
                }
            }
        }
    }
	
	public ItemStack getBestItemOfType(Class type)
	{
		return getStackInSlot(getBestItemOfTypeSlot(type));
	}
	
	public int getBestItemOfTypeSlot(Class type)
	{
		int highestMaxDamage = 0;
		int output = -1;
		
		for(int i = 0; i < this.getSizeInventory(); ++i)
		{
			ItemStack stackInInventory = this.getStackInSlot(i);

			if(stackInInventory != null)
			{
				if(type.isInstance(stackInInventory.getItem()) && highestMaxDamage < stackInInventory.getMaxDamage())
				{
					highestMaxDamage = stackInInventory.getMaxDamage();					
					output = i;
				}
			}
		}
		return output;
	}
	
	public ItemStack getBestArmor(int type)
	{
		return getStackInSlot(getBestArmorSlot(type));
	}
	
	public int getBestArmorSlot(int type)
	{
		int highestMax = 0;
		int output = -1;
		
		for(int i = 0; i < this.getSizeInventory(); ++i)
		{
			ItemStack stackInInventory = this.getStackInSlot(i);

			if(stackInInventory != null && stackInInventory.getItem() instanceof ItemArmor)
			{
				if(((ItemArmor)stackInInventory.getItem()).armorType == type)
				{
					if(highestMax < ((ItemArmor)stackInInventory.getItem()).damageReduceAmount)
					{
						highestMax = stackInInventory.getMaxDamage();					
						output = i;
					}
				}
			}
		}
		return output;
	}

}
