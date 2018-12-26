package com.cubic_control.c_companions.gui;

public enum EnumInteraction {
	CUSTOMIZE(1),
	PATROL(2),
	IN_HAIR(3),
	IN_EYES(4),
	IN_SKIN(5),
	IN_SHIRT(6),
	IN_GLOVES(7),
	IN_PANTS(8),
	IN_SHOES(9),
	DE_HAIR(10),
	DE_EYES(11),
	DE_SKIN(12),
	DE_SHIRT(13),
	DE_GLOVES(14),
	DE_PANTS(15),
	DE_SHOES(16),
	SET_PATROL_POINT(17),
	SET_PATROLING(18),
	SET_MINING(19),
	INVENTORY(20),
	INTERACT(21);

	private int id;
	
	EnumInteraction(int id)
	{
		this.id = id;
	}
	
	public static EnumInteraction fromId(int id)
	{
		for(EnumInteraction interaction : EnumInteraction.values())
		{
			if(interaction.id == id)
			{
				return interaction;
			}
		}
		return null;
	}
	
	public int getId()
	{
		return id;
	}
}
