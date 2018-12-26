package com.cubic_control.c_companions.entities;

import java.util.Random;

public class CompanionStrings {
	
	private static final String[] hire = {
			"Where You Go, I'll Follow",
			"I'll Stick With You For a While",
			"I Have Joined The Party",
			"Fine, I'll Go Along With You",
			"Gold, Gold, GOLD!",
			"Okay. When Can I Quit?"};
	
	public static String getHire(Random rand)
	{
		int i = rand.nextInt(hire.length);
		return hire[i];
	}
	
	private static final String[] banter = {
			"Lean on me and I'll lean right back.",
			"If we stick together we can accomplish anything, or something at the very least.",
			"This party could use more members, in my opinion.",
			"Fine, Fine, Everything's Fine.",
			"One day I'll build a city of boxes and stones!",
			"Okay... When can we stop?"};
	
	public static String getBanter(Random rand, EntityCompanion entity)
	{
		if(entity.getCustomNameTag().equalsIgnoreCase("harley") || entity.getCustomNameTag().equalsIgnoreCase("missHarley"))
		{
			int j = rand.nextInt(SpecialDialogue.banterHarley.length);
			return SpecialDialogue.banterHarley[j];
		}
		int i = rand.nextInt(banter.length);
		return banter[i];
	}
	
	private static final String[] sit = {
			"I'll Stay",
			"I'll Stay Here For a While",
			"I'm Going To Stay Right Here",
			"Fine, I'll Stay Here",
			"Am I Getting Paid For This?",
			"Okay. When Can I Leave?"};
	
	public static String getStay(Random rand, EntityCompanion entity)
	{
		if(entity.getCustomNameTag().equalsIgnoreCase("harley") || entity.getCustomNameTag().equalsIgnoreCase("missHarley"))
		{
			int j = rand.nextInt(SpecialDialogue.stayHarley.length);
			return SpecialDialogue.stayHarley[j];
		}
		int i = rand.nextInt(sit.length);
		return sit[i];
	}
	
	private static final String[] go = {
			"I'll Follow",
			"Let's Stick Together",
			"I'm Going To Follow You Around",
			"Fine, I'll Follow You",
			"Any Gold Involved In Our Next Adventure?",
			"Okay. When Can I Stop?"};
	
	public static String getGo(Random rand, EntityCompanion entity)
	{
		if(entity.getCustomNameTag().equalsIgnoreCase("harley") || entity.getCustomNameTag().equalsIgnoreCase("missHarley"))
		{
			int j = rand.nextInt(SpecialDialogue.goHarley.length);
			return SpecialDialogue.goHarley[j];
		}
		int i = rand.nextInt(go.length);
		return go[i];
	}

}
