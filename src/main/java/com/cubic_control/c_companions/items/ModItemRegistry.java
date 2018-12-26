package com.cubic_control.c_companions.items;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItemRegistry {
	private static Item spawnEgg;
	
	public static void InitItemRegistry(Logger log)
	{
		log.debug("Starting Companion Item Registry!");
		spawnEgg = new ItemSpawnEgg("Companion", 0x00A4DB, 0xDB3700, "Companion");
		GameRegistry.addRecipe(new ItemStack(spawnEgg), "XYX", "YaY", "XYX", 'X', Items.gold_nugget, 'Y', Items.dye, 'a', Items.egg);
		log.debug("Companion Item Registry Finished!");
	}

}
