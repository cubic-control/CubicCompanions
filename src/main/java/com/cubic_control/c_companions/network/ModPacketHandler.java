package com.cubic_control.c_companions.network;

import com.cubic_control.c_companions.main.RefStrings;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class ModPacketHandler{
	public static SimpleNetworkWrapper snw;
	
	public static void initPackets() {
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(RefStrings.modid);
		registerMessage(PacketCustomizeHandler.class, PacketCustomizeMessage.class, Side.SERVER);
		registerMessage(PacketPatrolPointHandler.class, PacketPatrolPointMessage.class, Side.SERVER);
		registerMessage(PacketPatrolHandler.class, PacketPatrolMessage.class, Side.SERVER);
		registerMessage(PacketInventoryHandler.class, PacketInventoryMessage.class, Side.SERVER);
	}
	
	private static int nextPacketId = 0;
	
	private static void registerMessage(Class packet, Class message) {
		snw.registerMessage(packet, message, nextPacketId, Side.CLIENT);
		snw.registerMessage(packet, message, nextPacketId, Side.SERVER);
		nextPacketId++;
	}
	
	private static void registerMessage(Class packet, Class message, Side side) {
		snw.registerMessage(packet, message, nextPacketId, side);
		nextPacketId++;
	}
}
