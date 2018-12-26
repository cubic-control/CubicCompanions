package com.cubic_control.c_companions.entities;

import com.cubic_control.c_companions.entities.ai.EntityAIPatrolPoint;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;

public class CompanionTasksPatrol {
	CompanionTasksDefault def;
	
	public CompanionTasksPatrol(EntityCompanion comp)
	{
		initTasks(comp);
		initTargetTasks(comp);
	}

	public EntityAIPatrolPoint patrolPoint;
	public EntityAIWatchClosest watchClosest;
	
	public void initTasks(EntityCompanion entity)
	{
		patrolPoint = new EntityAIPatrolPoint(entity, 0.6D, 10.0D, 0.1D);
		watchClosest = new EntityAIWatchClosest(entity, EntityLiving.class, 16.0F);
		
		// From Default
		def = new CompanionTasksDefault(entity);
	}
	
	public EntityAINearestAttackableTarget nearestAttackableTarget;
	
	public void initTargetTasks(EntityCompanion entity)
	{
		nearestAttackableTarget = new EntityAINearestAttackableTarget(entity, EntityLivingBase.class, 0, true, false, IMob.mobSelector);
	}
	
	public EntityAIBase[] getTasksList()
	{
		EntityAIBase[] list = new EntityAIBase[8];
		
		list[0] = def.swimming;
		list[1] = def.restrictOpenDoor;
		list[2] = def.openDoor;
		list[3] = def.moveTowardsRestriction;
		list[4] = def.attackOnCollide;
		list[5] = def.moveTowardsTarget;
		list[6] = patrolPoint;
		list[7] = watchClosest;
		
		
		return list;
	}
	
	public EntityAIBase[] getTargetTasksList()
	{
		EntityAIBase[] list = new EntityAIBase[1];
		
		list[0] = nearestAttackableTarget;
		
		return list;
	}
}
