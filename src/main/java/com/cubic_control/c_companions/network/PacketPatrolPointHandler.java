package com.cubic_control.c_companions.network;

import com.cubic_control.c_companions.entities.EntityCompanion;
import com.cubic_control.cubic_core.Utils.BlockPos;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketPatrolPointHandler implements IMessageHandler<PacketPatrolPointMessage, IMessage>{

	@Override
	public IMessage onMessage(PacketPatrolPointMessage message, MessageContext ctx)
	{
		World world = DimensionManager.getWorld(message.dimID);
		EntityCompanion entity = (EntityCompanion) world.getEntityByID(message.entityID);
		
		entity.setPatrolPoint(new BlockPos(message.x, message.y, message.z));
		
		NBTTagCompound tag = new NBTTagCompound();
		
		int[] array = new int[3];
		
		array[0] = message.x;
		array[1] = message.y;
		array[2] = message.z;
		
		tag.setIntArray("PatrolPoint", array);
		
		entity.readPatrolPointFromNBT(tag);
		
		return null;
	}

}
