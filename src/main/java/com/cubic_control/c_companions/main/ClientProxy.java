package com.cubic_control.c_companions.main;

import com.cubic_control.c_companions.entities.EntityCompanion;
import com.cubic_control.c_companions.models.ModelCompanion;
import com.cubic_control.c_companions.renders.RenderCompanion;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

	@Override
	public void init(FMLInitializationEvent arg0) {
		super.init(arg0);
		RenderingRegistry.registerEntityRenderingHandler(EntityCompanion.class, new RenderCompanion(new ModelCompanion(), 0.5f));
	}

	@Override
	public void postInit(FMLPostInitializationEvent arg0) {
		// TODO Auto-generated method stub
		super.postInit(arg0);
	}

	@Override
	public void preInit(FMLPreInitializationEvent arg0) {
		// TODO Auto-generated method stub
		super.preInit(arg0);
	}
	
}
