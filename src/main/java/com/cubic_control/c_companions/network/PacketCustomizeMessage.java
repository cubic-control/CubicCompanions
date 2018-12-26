package com.cubic_control.c_companions.network;

import com.cubic_control.c_companions.entities.EntityCompanion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class PacketCustomizeMessage implements IMessage{
	protected int idSkin;
	protected int idShirt;
	protected int idPants;
	protected int idShoes;
	protected int idGloves;
	protected int idEyes;
	protected int idHair;
	protected boolean isMale;
	
	protected int dimID;
	protected int entityID;
	
	public PacketCustomizeMessage() {}
	
	public PacketCustomizeMessage(NBTTagCompound tag, int world, EntityCompanion entity)
	{
		idSkin = tag.getInteger("IDSkin");
		idShirt = tag.getInteger("IDShirt");
		idPants = tag.getInteger("IDPants");
		idShoes = tag.getInteger("IDShoes");
		idGloves = tag.getInteger("IDGloves");
		idEyes = tag.getInteger("IDEyes");
		idHair = tag.getInteger("IDHair");
		isMale = tag.getBoolean("IsMale");
		
		dimID = world;
		this.entityID = entity.getEntityId();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		idSkin = buf.readInt();
		idShirt = buf.readInt();
		idPants = buf.readInt();
		idShoes = buf.readInt();
		idGloves = buf.readInt();
		idEyes = buf.readInt();
		idHair = buf.readInt();
		isMale = buf.readBoolean();
		
		dimID = buf.readInt();
		entityID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(idSkin);
		buf.writeInt(idShirt);
		buf.writeInt(idPants);
		buf.writeInt(idShoes);
		buf.writeInt(idGloves);
		buf.writeInt(idEyes);
		buf.writeInt(idHair);
		buf.writeBoolean(isMale);
		
		buf.writeInt(dimID);
		buf.writeInt(entityID);
	}

}
