package com.cubic_control.c_companions.network;

import com.cubic_control.c_companions.entities.EntityCompanion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class PacketCustomizeHandler implements IMessageHandler<PacketCustomizeMessage, IMessage>{

	@Override
	public IMessage onMessage(PacketCustomizeMessage message, MessageContext ctx) {
		
		World world = DimensionManager.getWorld(message.dimID);
		EntityCompanion entity = (EntityCompanion) world.getEntityByID(message.entityID);
		
		NBTTagCompound tag = new NBTTagCompound();
		
		tag.setInteger("IDSkin", message.idSkin);
		tag.setInteger("IDShirt", message.idShirt);
		tag.setInteger("IDPants", message.idPants);
		tag.setInteger("IDShoes", message.idShoes);
		tag.setInteger("IDGloves", message.idGloves);
		tag.setInteger("IDEyes", message.idEyes);
		tag.setInteger("IDHair", message.idHair);
		tag.setBoolean("IsMale", message.isMale);
		
		entity.readIDFromNBT(tag);
		
		return null;
	}

}
