package com.cubic_control.c_companions.entities;

import java.util.ArrayList;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class CompanionTasksDefault {
	
	public CompanionTasksDefault(EntityCompanion comp)
	{
		initTasks(comp);
		initTargetTasks(comp);
	}

	public EntityAISwimming swimming;
	public EntityAIMate mate;
	public EntityAIRestrictOpenDoor restrictOpenDoor;
	public EntityAIOpenDoor openDoor;
	public EntityAIMoveTowardsRestriction moveTowardsRestriction;
	public EntityAIAttackOnCollide attackOnCollide;
	public EntityAIMoveTowardsTarget moveTowardsTarget;
	public EntityAIFollowOwner followOwner;
	public EntityAIWander wander;
	public EntityAIWatchClosest watchClosest;
	public EntityAILookIdle lookIdle;
	
	public void initTasks(EntityCompanion entity)
	{
		swimming = new EntityAISwimming(entity);
		mate = new EntityAIMate(entity, 0.9D);
		restrictOpenDoor = new EntityAIRestrictOpenDoor(entity);
		openDoor = new EntityAIOpenDoor(entity, true);
		moveTowardsRestriction = new EntityAIMoveTowardsRestriction(entity, 1.0D);
		attackOnCollide = new EntityAIAttackOnCollide(entity, 1.0D, true);
		moveTowardsTarget = new EntityAIMoveTowardsTarget(entity, 1.0D, 10.0F);
		followOwner = new EntityAIFollowOwner(entity, 1.0D, 7.0F, 3.0F);
		wander = new EntityAIWander(entity, 1.0D);
		watchClosest = new EntityAIWatchClosest(entity, EntityPlayer.class, 8.0F);
		lookIdle = new EntityAILookIdle(entity);
	}
	
	public EntityAIOwnerHurtByTarget ownerHurtByTarget;
	public EntityAIOwnerHurtTarget ownerHurtTarget;
	public EntityAIHurtByTarget hurtByTarget;
	public EntityAINearestAttackableTarget nearestAttackableTarget;
	
	public void initTargetTasks(EntityCompanion entity)
	{
		ownerHurtByTarget = new EntityAIOwnerHurtByTarget(entity);
		ownerHurtTarget = new EntityAIOwnerHurtTarget(entity);
		hurtByTarget = new EntityAIHurtByTarget(entity, true);
		nearestAttackableTarget = new EntityAINearestAttackableTarget(entity, EntityMob.class, 10, true);
	}
	
	public EntityAIBase[] getTasksList()
	{
		EntityAIBase[] list = new EntityAIBase[11];
		
		list[0] = swimming;
		list[1] = mate;
		list[2] = restrictOpenDoor;
		list[3] = openDoor;
		list[4] = moveTowardsRestriction;
		list[5] = attackOnCollide;
		list[6] = moveTowardsTarget;
		list[7] = followOwner;
		list[8] = wander;
		list[9] = watchClosest;
		list[10] = lookIdle;
		
		return list;
	}
	
	public EntityAIBase[] getTargetTasksList()
	{
		EntityAIBase[] list = new EntityAIBase[4];
		
		list[0] = ownerHurtByTarget;
		list[1] = ownerHurtTarget;
		list[2] = hurtByTarget;
		list[3] = nearestAttackableTarget;
		
		return list;
	}
}
