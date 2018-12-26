package com.cubic_control.c_companions.entities.ai;

import com.cubic_control.c_companions.entities.EntityCompanion;
import com.cubic_control.cubic_core.Utils.BlockPos;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIPatrolPoint extends EntityAIBase{
	private EntityCompanion entity;
	private double movePosX;
    private double movePosY;
    private double movePosZ;
	private double speed;
	private BlockPos point;
	private double maxDistance;
	private double minDistance;
	private boolean switcher;

	public EntityAIPatrolPoint(EntityCompanion comp, double move, double distMx, double distMn)
	{
		this.entity = comp;
		this.speed = move;
		this.setMutexBits(1);
		this.maxDistance = distMx;
		this.minDistance = distMn;
		switcher = true;
	}
	
	@Override
	public boolean shouldExecute() {
		if(!entity.hasPatrolPoint())
		{
			return false;
		}
		this.point = entity.getPatrolPoint();
		
		if(this.entity.getRNG().nextBoolean())
		{
			return false;
		}
		else
		{
			// TODO: Create VAR in Companion to LEVEL UP distance
			Vec3 vec3;
			
			int d = ((int)maxDistance / 2);
			
			if(maxDistance < point.distSqr(movePosX, movePosY, movePosZ))
			{
				vec3 = RandomPositionGenerator.findRandomTargetBlockTowards(entity, d, d, Vec3.createVectorHelper(point.getX(), point.getY(), point.getZ()));
			}
			else if(minDistance > point.distSqr(movePosX, movePosY, movePosZ))
			{
				vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(entity, d, d, Vec3.createVectorHelper(point.getX(), point.getY(), point.getZ()));
			}
			else
			{
				vec3 = RandomPositionGenerator.findRandomTarget(entity, d, d);
			}
			
			if(vec3 == null)
			{
				return false;
			}
			
			else
			{
				this.movePosX = vec3.xCoord;
                this.movePosY = vec3.yCoord;
                this.movePosZ = vec3.zCoord;
				return true;
			}
		}
	}
	
	@Override
	public boolean continueExecuting() {
		return !this.entity.getNavigator().noPath();
	}
	
	@Override
	public void resetTask() {
		this.point = null;
	}
	
	@Override
	public void startExecuting() {
		this.entity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
	}

}
