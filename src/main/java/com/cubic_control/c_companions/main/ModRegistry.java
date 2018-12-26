package com.cubic_control.c_companions.main;

import org.apache.logging.log4j.Logger;

import com.cubic_control.c_companions.entities.ModEntityRegistry;
import com.cubic_control.c_companions.gui.GuiHandler;
import com.cubic_control.c_companions.items.ModItemRegistry;
import com.cubic_control.c_companions.network.ModPacketHandler;
import com.cubic_control.c_companions.renders.CompanionResources;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = RefStrings.modid, name = RefStrings.name, version = "1.0", guiFactory = RefStrings.guiFactory)
public class ModRegistry {
	
	@SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE)
	private static CommonProxy proxy;
	
	private static Logger log;
	
	@Mod.Instance(RefStrings.modid)
	private static ModRegistry instance;
	
	private static ModPacketHandler packetHandler;
	
	@EventHandler
	protected void PreInitialization(FMLPreInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(getInstance(), new GuiHandler());
		log = event.getModLog();
		log.info("Cubic Companions Starting!");
		proxy.preInit(event);
		
		CompanionResources.massInit();
		
		ModEntityRegistry.InitRegistry(log);
		ModItemRegistry.InitItemRegistry(log);
	}
	
	@EventHandler
	protected void Initialization(FMLInitializationEvent event)
	{
		proxy.init(event);
		packetHandler.initPackets();
		ModEntityRegistry.InitSpawnRegistry();
		log.info("Cubic Companions Loaded!");
	}
	
	public static ModRegistry getInstance()
	{
		return instance;
	}
	
	public static ModPacketHandler getPacketHandler()
	{
		return packetHandler;
	}

}
