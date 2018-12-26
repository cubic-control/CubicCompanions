package com.cubic_control.c_companions.renders;

import java.util.ArrayList;

import com.cubic_control.c_companions.main.RefStrings;

import net.minecraft.util.ResourceLocation;

public class CompanionResources {
	public static final ResourceLocation none = new ResourceLocation(RefStrings.modid + ":textures/mobs/none.png");
	
	/*NEW METHOD*/
	
	public static void massInit()
	{
		femHairInit();
		malHairInit();
		glovesInit();
		shoesInit();
		shirtsInit();
		pantsInit();
		femEyesInit();
		malEyesInit();
		skinsInit();
		
		// Add Support For Custom Textures HERE!
	}
	
	public static ArrayList<ResourceLocation> femHair = new ArrayList<ResourceLocation>();
	
	public static void femHairInit()
	{
		femHair.clear();
		femHair.add(none);
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/black.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/blue.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/brown.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/cyan.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/gray.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/green.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/light_blue.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/light_gray.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/lime.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/magenta.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/orange.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/pink.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/purple.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/red.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/white.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/yellow.png"));
		// v2
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/black_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/blue_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/brown_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/cyan_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/gray_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/green_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/light_blue_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/light_gray_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/lime_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/magenta_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/orange_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/pink_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/purple_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/red_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/white_v2.png"));
		femHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/f/yellow_v2.png"));
	}
	
	public static ArrayList<ResourceLocation> malHair = new ArrayList<ResourceLocation>();
	
	public static void malHairInit()
	{
		malHair.clear();
		malHair.add(none);
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/black.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/blue.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/brown.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/cyan.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/gray.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/green.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/light_blue.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/light_gray.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/lime.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/magenta.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/orange.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/pink.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/purple.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/red.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/white.png"));
		malHair.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/hair/m/yellow.png"));
	}
	
	public static ArrayList<ResourceLocation> gloves = new ArrayList<ResourceLocation>();
	
	public static void glovesInit()
	{
		gloves.clear();
		gloves.add(none);
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/black.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/blue.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/brown.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/cyan.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/gray.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/green.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/light_blue.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/light_gray.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/lime.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/magenta.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/orange.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/pink.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/purple.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/red.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/white.png"));
		gloves.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/gloves/yellow.png"));
	}
	
	public static ArrayList<ResourceLocation> shoes = new ArrayList<ResourceLocation>();
	
	public static void shoesInit()
	{
		shoes.clear();
		shoes.add(none);
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/black.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/blue.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/brown.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/cyan.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/gray.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/green.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/light_blue.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/light_gray.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/lime.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/magenta.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/orange.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/pink.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/purple.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/red.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/white.png"));
		shoes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shoes/yellow.png"));
	}
	
	public static ArrayList<ResourceLocation> shirts = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> femShirts = new ArrayList<ResourceLocation>();
	
	public static void shirtsInit()
	{
		shirts.clear();
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/black.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/blue.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/brown.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/cyan.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/gray.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/green.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/light_blue.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/light_gray.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/lime.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/magenta.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/orange.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/pink.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/purple.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/red.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/white.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/yellow.png"));
		// Sweaters
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/black_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/blue_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/brown_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/cyan_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/gray_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/green_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/light_blue_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/light_gray_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/lime_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/magenta_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/orange_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/pink_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/purple_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/red_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/white_sweater.png"));
		shirts.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/shirts/yellow_sweater.png"));
		
		femShirts.addAll(shirts);
		// Add Female Exclusive Shirts Here
	}
	
	public static ArrayList<ResourceLocation> pants = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> femPants = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> malPants = new ArrayList<ResourceLocation>();
	
	public static void pantsInit()
	{
		pants.clear();
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/black.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/blue.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/brown.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/cyan.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/gray.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/green.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/light_blue.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/light_gray.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/lime.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/magenta.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/orange.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/pink.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/purple.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/red.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/white.png"));
		pants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/yellow.png"));
		
		femPants.addAll(pants);
		// Add Female Exclusive Pants Here
		// Shorts
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/black_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/blue_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/brown_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/cyan_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/gray_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/green_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/light_blue_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/light_gray_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/lime_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/magenta_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/orange_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/pink_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/purple_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/red_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/white_shorts.png"));
		femPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/f/yellow_shorts.png"));
		
		malPants.addAll(pants);
		// Add Male Exclusive Pants Here
		// Shorts
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/black_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/blue_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/brown_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/cyan_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/gray_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/green_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/light_blue_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/light_gray_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/lime_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/magenta_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/orange_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/pink_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/purple_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/red_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/white_shorts.png"));
		malPants.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/pants/m/yellow_shorts.png"));
	}
	
	public static ArrayList<ResourceLocation> femEyes = new ArrayList<ResourceLocation>();
	
	public static void femEyesInit()
	{
		femEyes.clear();
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/black.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/blue.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/brown.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/cyan.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/gray.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/green.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/light_blue.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/light_gray.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/lime.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/magenta.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/orange.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/pink.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/purple.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/red.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/white.png"));
		femEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/f/yellow.png"));
	}
	
	public static ArrayList<ResourceLocation> malEyes = new ArrayList<ResourceLocation>();
	
	public static void malEyesInit()
	{
		malEyes.clear();
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/black.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/blue.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/brown.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/cyan.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/gray.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/green.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/light_blue.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/light_gray.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/lime.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/magenta.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/orange.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/pink.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/purple.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/red.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/white.png"));
		malEyes.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/eyes/m/yellow.png"));
	}
	
	public static ArrayList<ResourceLocation> skins = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> femSkins = new ArrayList<ResourceLocation>();
	public static ArrayList<ResourceLocation> malSkins = new ArrayList<ResourceLocation>();
	
	public static void skinsInit()
	{
		skins.clear();
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_black.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_black_pure.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_white.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_white_pure.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_gray.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_gray_pure.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_light.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_light_pure.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_medium.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_medium_pure.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_dark.png"));
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_dark_pure.png"));
		
		skins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/n/skin_base_iron.png"));
		
		femSkins.addAll(skins);
		// Female Only
		femSkins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/f/skin_base_fem_1.png"));
		femSkins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/f/skin_base_fem_2.png"));
		
		malSkins.addAll(skins);
		// Male Only
		malSkins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/m/skin_base_male_1.png"));
		malSkins.add(new ResourceLocation(RefStrings.modid + ":textures/mobs/skins/m/skin_base_male_2.png"));
		
	}
}
