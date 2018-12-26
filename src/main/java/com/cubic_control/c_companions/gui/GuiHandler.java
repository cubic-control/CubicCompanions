package com.cubic_control.c_companions.gui;

import com.cubic_control.c_companions.entities.EntityCompanion;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		EntityCompanion entity;
		
		if(ID == GuiIDs.INVENTORY_ID)
		{
			entity = (EntityCompanion) world.getEntityByID(x);
			return new ContainerInventory(player.inventory, entity.getInventory(), entity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		EntityCompanion entity;
		
		switch(ID)
		{
		case GuiIDs.COMPANION_ID:
			entity = (EntityCompanion) world.getEntityByID(x);
			return new GuiCompanion(entity);
		case GuiIDs.INVENTORY_ID:
			entity = (EntityCompanion) world.getEntityByID(x);
			return new GuiInventory(entity, player.inventory, entity.getInventory());
		}
		return null;
	}

}
