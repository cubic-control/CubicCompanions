package com.cubic_control.c_companions.network;

import com.cubic_control.c_companions.entities.EntityCompanion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class PacketPatrolMessage implements IMessage{
	protected boolean isPatroling;
	
	protected int entityID;
	protected int dimID;
	
	public PacketPatrolMessage() {}
	
	public PacketPatrolMessage(boolean bool, EntityCompanion entity)
	{
		isPatroling = bool;
		
		entityID = entity.getEntityId();
		
		dimID = entity.worldObj.provider.dimensionId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		isPatroling = buf.readBoolean();
		
		entityID = buf.readInt();
		
		dimID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isPatroling);
		
		buf.writeInt(entityID);
		
		buf.writeInt(dimID);
	}

}
