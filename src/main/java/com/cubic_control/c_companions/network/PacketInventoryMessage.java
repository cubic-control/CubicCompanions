package com.cubic_control.c_companions.network;

import com.cubic_control.c_companions.entities.EntityCompanion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class PacketInventoryMessage implements IMessage{
	protected boolean doOpenInventory;
	
	protected int entityID;
	protected int dimID;
	
	public PacketInventoryMessage() {}
	
	public PacketInventoryMessage(boolean bool, EntityCompanion entity)
	{
		doOpenInventory = bool;
		
		entityID = entity.getEntityId();
		
		dimID = entity.worldObj.provider.dimensionId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		doOpenInventory = buf.readBoolean();
		
		entityID = buf.readInt();
		
		dimID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(doOpenInventory);
		
		buf.writeInt(entityID);
		
		buf.writeInt(dimID);
	}

}
