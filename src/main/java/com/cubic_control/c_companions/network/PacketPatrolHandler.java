package com.cubic_control.c_companions.network;

import com.cubic_control.c_companions.entities.EntityCompanion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketPatrolHandler implements IMessageHandler<PacketPatrolMessage, IMessage>{

	@Override
	public IMessage onMessage(PacketPatrolMessage message, MessageContext ctx)
	{
		World world = DimensionManager.getWorld(message.dimID);
		EntityCompanion entity = (EntityCompanion) world.getEntityByID(message.entityID);
		
		entity.setPatroling(message.isPatroling);
		
		return null;
	}

}
