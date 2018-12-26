package com.cubic_control.c_companions.entities;

import org.apache.logging.log4j.Logger;

import com.cubic_control.c_companions.main.ModRegistry;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class ModEntityRegistry {
	
	public static void InitRegistry(Logger log)
	{
		log.debug("Begining Entity Registration");
		registerEntity(EntityCompanion.class, "Companion");
		log.debug("Entity Registration Complete");
	}
	
	public static void InitSpawnRegistry()
	{
		BiomeGenBase[] spawns = new BiomeGenBase[BiomeGenBase.explorationBiomesList.size()];
		
		for(int i = 0; i < BiomeGenBase.explorationBiomesList.size() ; i++)
		{
			spawns[i] = (BiomeGenBase) BiomeGenBase.explorationBiomesList.toArray()[i];
		}
		
		EntityRegistry.addSpawn(EntityCompanion.class, 200, 1, 3, EnumCreatureType.creature, spawns);
	}

	private static int nextId = 0;
	
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName){
		EntityRegistry.registerModEntity(entityClass, entityName, nextId, ModRegistry.getInstance(), 64, 1, true);
		nextId++;
	}
	
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates){
		EntityRegistry.registerModEntity(entityClass, entityName, nextId, ModRegistry.getInstance(), trackingRange, updateFrequency, sendsVelocityUpdates);
		nextId++;
	}
}
