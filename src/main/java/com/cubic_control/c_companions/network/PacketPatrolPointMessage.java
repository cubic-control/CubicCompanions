package com.cubic_control.c_companions.network;

import com.cubic_control.c_companions.entities.EntityCompanion;
import com.cubic_control.cubic_core.Utils.BlockPos;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class PacketPatrolPointMessage implements IMessage{
	protected int x,y,z;
	protected int entityID;
	protected int dimID;
	
	public PacketPatrolPointMessage() {}
	
	public PacketPatrolPointMessage(BlockPos pos, EntityCompanion entity)
	{
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		
		entityID = entity.getEntityId();
		
		dimID = entity.worldObj.provider.dimensionId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		
		entityID = buf.readInt();
		
		dimID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		
		buf.writeInt(entityID);
		
		buf.writeInt(dimID);
	}

}
