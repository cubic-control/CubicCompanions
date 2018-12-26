package com.cubic_control.c_companions.entities;

import com.cubic_control.c_companions.gui.GuiIDs;
import com.cubic_control.c_companions.main.ModRegistry;
import com.cubic_control.c_companions.renders.CompanionResources;
import com.cubic_control.cubic_core.Utils.BlockPos;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCompanion extends EntityTameable{
	private boolean doOpenInventory;
	
	private CompanionTasksDefault ctasks;
	private CompanionTasksPatrol ctasksPatrol;
	
	private CompanionInventory inventory;
	
	private int idSkin;
	private int idShirt;
	private int idPants;
	private int idShoes;
	private int idGloves;
	private int idEyes;
	private int idHair;
	private boolean isMale;
	
	private boolean isPatroling;
	private BlockPos patrolPoint;
	
	private boolean isIdle;
	
	private int tickBanter;

	public EntityCompanion(World world)
	{
		super(world);
		
		ctasks = new CompanionTasksDefault(this);
		ctasksPatrol = new CompanionTasksPatrol(this);
		
		this.setSize(0.6F, 1.9F);
		this.getNavigator().setBreakDoors(true);
		this.getNavigator().setEnterDoors(true);
		this.getNavigator().setAvoidsWater(true);
		
        this.setTamed(false);
        this.setPatroling(false);
        setDoOpenInventory(false);
        tickBanter = 0;
        
        if(!isTamed())
        {
        	setRandom();
        }
        
        inventory = new CompanionInventory(this);
	}
	
	protected void removeAllTasks()
	{
		// Default
		for(int i = 0; i < ctasks.getTasksList().length; i++)
		{
			this.tasks.removeTask(ctasks.getTasksList()[i]);
		}
		
		for(int i = 0; i < ctasks.getTargetTasksList().length; i++)
		{
			this.targetTasks.removeTask(ctasks.getTargetTasksList()[i]);
		}
		this.tasks.removeTask(this.aiSit);
		
		// Patrol
		for(EntityAIBase base : ctasksPatrol.getTasksList())
		{
			this.tasks.removeTask(base);
		}
		
		for(EntityAIBase base : ctasksPatrol.getTargetTasksList())
		{
			this.targetTasks.removeTask(base);
		}
	}
	
	protected void addPatrolTasks()
	{
		removeAllTasks();
		
		for(int i = 0; i < ctasksPatrol.getTasksList().length ;i++)
		{
			this.tasks.addTask(i, ctasksPatrol.getTasksList()[i]);
		}
		
		for(int i = 0; i < ctasksPatrol.getTargetTasksList().length ;i++)
		{
			this.targetTasks.addTask(i, ctasksPatrol.getTargetTasksList()[i]);
		}
	}
	
	protected void addIdleTasks()
	{
		removeAllTasks();
	}
	
	protected void addDefaultTasks()
	{
		removeAllTasks();
		
		this.tasks.addTask(0, this.aiSit);
		
		for(int i = 0; i < ctasks.getTasksList().length ;i++)
		{
			this.tasks.addTask((i+1), ctasks.getTasksList()[i]);
		}
		
		for(int i = 0; i < ctasks.getTargetTasksList().length ;i++)
		{
			this.targetTasks.addTask(i, ctasks.getTargetTasksList()[i]);
		}
	}
	
	private void setRandom()
	{
		setMale(this.rand.nextBoolean());
        
        if(isMale())
        {
        	setIdSkin(this.rand.nextInt(CompanionResources.malSkins.size()));
        	setIdHair(this.rand.nextInt(CompanionResources.malHair.size()));
        	setIdShirt(this.rand.nextInt(CompanionResources.shirts.size()));
        	setIdPants(this.rand.nextInt(CompanionResources.pants.size()));
        	setIdEyes(this.rand.nextInt(CompanionResources.malEyes.size()));
        }
        else
        {
        	setIdSkin(this.rand.nextInt(CompanionResources.femSkins.size()));
        	setIdHair(this.rand.nextInt(CompanionResources.femHair.size()));
        	setIdShirt(this.rand.nextInt(CompanionResources.femShirts.size()));
        	setIdPants(this.rand.nextInt(CompanionResources.femPants.size()));
        	setIdEyes(this.rand.nextInt(CompanionResources.femEyes.size()));
        }
        setIdShoes(this.rand.nextInt(CompanionResources.shoes.size()));
        setIdGloves(this.rand.nextInt(CompanionResources.gloves.size()));
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        }
    }
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(18, idSkin);
		this.dataWatcher.addObject(19, idShirt);
		this.dataWatcher.addObject(20, idPants);
		this.dataWatcher.addObject(21, idShoes);
		this.dataWatcher.addObject(22, idGloves);
		this.dataWatcher.addObject(23, idEyes);
		this.dataWatcher.addObject(24, idHair);
		this.dataWatcher.addObject(25, new Integer(1));
		
		this.dataWatcher.addObject(26, new Integer(0));
		
		this.dataWatcher.addObject(27, new Integer(0));
	}
	
	@Override
	public boolean isAIEnabled()
    {
        return true;
    }

	@Override
	protected Item getDropItem()
    {
        if(!isTamed())
        {
        	return Item.getItemById(-1);
        }
        this.createMessage("I'm Dead");
        return Items.emerald;
    }
	
	@Override
	public EntityAgeable createChild(EntityAgeable entity)
	{
		EntityCompanion child = new EntityCompanion(worldObj);
		String s = this.func_152113_b();
		
		if(s != null && s.trim().length() > 0)
        {
			child.func_152115_b(s);
			child.setTamed(true);
        }
		// Set Skin To Parents
		if(entity instanceof EntityCompanion)
		{
			if(rand.nextBoolean())
			{
				child.setIdSkin(((EntityCompanion)entity).getIdSkin());
			}
			else
			{
				child.setIdSkin(this.getIdSkin());
			}
		}
		else
		{
			child.setIdSkin(this.getIdSkin());
		}
		return child;
	}
	
	@Override
	public boolean canMateWith(EntityAnimal entity) {
		if(entity != this && entity instanceof EntityCompanion)
		{
			if(((EntityCompanion)entity).isMale() != this.isMale())
			{
				if(this.isInLove() && entity.isInLove())
				{
					if(!this.isSitting() && !((EntityCompanion)entity).isSitting())
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == Items.cookie;
	}
	
	@Override
    public void onLivingUpdate()
	{
        this.updateArmSwingProgress();
        this.setSneaking(this.isSitting());
        super.onLivingUpdate();
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float f1)
	{
        if(this.isEntityInvulnerable())
        {
            return false;
        }
        else if(super.attackEntityFrom(source, f1))
        {
            Entity entity = source.getEntity();
            this.aiSit.setSitting(false);

            if(this.riddenByEntity != entity && this.ridingEntity != entity)
            {
                if(entity != this)
                {
                    this.entityToAttack = entity;
                }
                return true;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
	
	@Override
	public void setTamed(boolean tame)
    {
        super.setTamed(tame);

        if (tame)
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        }
    }
	
	@Override
	protected boolean canDespawn()
    {
        return !this.isTamed() && this.ticksExisted > 2400;
    }
	
	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if(par1Entity instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)par1Entity);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)par1Entity);
        }
        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);
        
        if(flag)
        {
            if(i > 0)
            {
                par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }
            int j = EnchantmentHelper.getFireAspectModifier(this);

            if(j > 0)
            {
                par1Entity.setFire(j * 4);
            }

            if(par1Entity instanceof EntityLivingBase)
            {
                EnchantmentHelper.func_151384_a((EntityLivingBase)par1Entity, this);
            }
            EnchantmentHelper.func_151385_b(this, par1Entity);
        }
        return flag;
    }
	
	@Override
    protected void attackEntity(Entity par1Entity, float par2) {
        if(this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(par1Entity);
        }
    }
	
	@Override
    protected boolean func_146066_aG()
	{
        return true;
    }
	
	public int getIdSkin() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	public void setIdSkin(int idSkin) {
		this.getDataWatcher().updateObject(18, idSkin);
		this.idSkin = idSkin;
	}

	public int getIdShirt() {
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	public void setIdShirt(int idShirt) {
		this.getDataWatcher().updateObject(19, idShirt);
		this.idShirt = idShirt;
	}

	public int getIdPants() {
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	public void setIdPants(int idPants) {
		this.getDataWatcher().updateObject(20, idPants);
		this.idPants = idPants;
	}

	public int getIdShoes() {
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	public void setIdShoes(int idShoes) {
		this.getDataWatcher().updateObject(21, idShoes);
		this.idShoes = idShoes;
	}

	public int getIdGloves() {
		return this.dataWatcher.getWatchableObjectInt(22);
	}

	public void setIdGloves(int idGloves) {
		this.getDataWatcher().updateObject(22, idGloves);
		this.idGloves = idGloves;
	}

	public int getIdEyes() {
		return this.dataWatcher.getWatchableObjectInt(23);
	}

	public void setIdEyes(int idEyes) {
		this.getDataWatcher().updateObject(23, idEyes);
		this.idEyes = idEyes;
	}

	public int getIdHair() {
		return this.dataWatcher.getWatchableObjectInt(24);
	}

	public void setIdHair(int idHair) {
		this.getDataWatcher().updateObject(24, idHair);
		this.idHair = idHair;
	}

	public boolean isMale() {
		if(this.dataWatcher.getWatchableObjectInt(25) == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void setMale(boolean isMale) {
		if(isMale)
		{
			this.getDataWatcher().updateObject(25, 1);
		}
		else
		{
			this.getDataWatcher().updateObject(25, 0);
		}
		this.isMale = isMale;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		readIDFromNBT(nbt);
		readPatrolPointFromNBT(nbt);
		this.isPatroling = nbt.getBoolean("isPatroling");
		
		inventory.readFromNBT(nbt.getTagList("Inventory", 10));
	}
	
	public void readIDFromNBT(NBTTagCompound nbt)
	{
		this.setIdSkin(nbt.getInteger("IDSkin"));
		this.setIdShirt(nbt.getInteger("IDShirt"));
		this.setIdPants(nbt.getInteger("IDPants"));
		this.setIdShoes(nbt.getInteger("IDShoes"));
		this.setIdGloves(nbt.getInteger("IDGloves"));
		this.setIdEyes(nbt.getInteger("IDEyes"));
		this.setIdHair(nbt.getInteger("IDHair"));
		this.setMale(nbt.getBoolean("IsMale"));
	}
	
	public void readPatrolPointFromNBT(NBTTagCompound nbt)
	{
		try {
			int[] pointInts = nbt.getIntArray("PatrolPoint");
			int tx = pointInts[0];
			int ty = pointInts[1];
			int tz = pointInts[2];
			BlockPos point = new BlockPos(tx, ty, tz);
			
			this.patrolPoint = point;
		}
		catch(Exception e)
		{
			// Do Nothing
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("IDSkin", this.getIdSkin());
		nbt.setInteger("IDShirt", this.getIdShirt());
		nbt.setInteger("IDPants", this.getIdPants());
		nbt.setInteger("IDShoes", this.getIdShoes());
		nbt.setInteger("IDGloves", this.getIdGloves());
		nbt.setInteger("IDEyes", this.getIdEyes());
		nbt.setInteger("IDHair", this.getIdHair());
		nbt.setBoolean("IsMale", this.isMale());
		
		nbt.setBoolean("isPatroling", this.isPatroling());

		if(this.hasPatrolPoint())
		{
			int[] tmp = new int[3];
			tmp[0] = patrolPoint.getX();
			tmp[1] = patrolPoint.getY();
			tmp[2] = patrolPoint.getZ();
			
			nbt.setIntArray("PatrolPoint", tmp);
		}
		nbt.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
	}
	
	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		if(!isTamed())
		{
			setRandom();
		}
		return super.onSpawnWithEgg(data);
	}
	
	@Override
	public boolean interact(EntityPlayer entity)
	{
		ItemStack stack = entity.inventory.getCurrentItem();
		
		if(this.isTamed())
		{
			if(stack != null)
			{
				if(stack.getItem() instanceof ItemFood)
				{
					if(this.getHealth() < this.getMaxHealth())
					{
						if(!entity.capabilities.isCreativeMode)
						{
							--stack.stackSize;
						}
						this.heal((float)((ItemFood)stack.getItem()).func_150905_g(stack));
						
						if(stack.stackSize <= 0)
						{
							entity.inventory.setInventorySlotContents(entity.inventory.currentItem, (ItemStack)null);
						}
						return true;
					}
				}
			}
			if(this.func_152114_e(entity) && !this.worldObj.isRemote && !entity.isSneaking())
			{
				if(!isPatroling)
				{
					this.aiSit.setSitting(!this.isSitting());
                	this.isJumping = false;
                	this.setPathToEntity((PathEntity)null);
                	this.setTarget((Entity)null);
                	this.setAttackTarget((EntityLivingBase)null);
                	
                	if(!this.isSitting())
                	{
                		this.createMessage(CompanionStrings.getStay(this.rand, this));
                	}
                	else
                	{
                		this.createMessage(CompanionStrings.getGo(this.rand, this));
                	}
				}
			}
			
			if(entity.isSneaking() && func_152114_e(entity))
			{
				entity.openGui(ModRegistry.getInstance(), GuiIDs.COMPANION_ID, this.worldObj, this.getEntityId(), 0, 0);
				return true;
			}
		}
		else if(stack != null && stack.getItem() == Items.gold_ingot)
		{
			if(!entity.capabilities.isCreativeMode)
			{
				--stack.stackSize;
			}
			
			if(stack.stackSize <= 0)
			{
				entity.inventory.setInventorySlotContents(entity.inventory.currentItem, (ItemStack)null);
			}
			
			if(!this.worldObj.isRemote)
			{
				if(this.rand.nextInt(3) == 0)
				{
					this.setTamed(true);
					this.setCustomNameTag("Companion");
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.func_152115_b(entity.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.createMessage(CompanionStrings.getHire(this.rand));
				}
				else
				{
					this.playTameEffect(false);
				}
			}
			return true;
		}
		
		return super.interact(entity);
	}
	
	protected void createMessage(String text)
	{
		if(text.equals(""))
		{
			return;
		}
		String fullMsg = "[" + this.getCustomNameTag() + "]:" + text;
		
		ChatComponentText msg = new ChatComponentText(fullMsg);
		
		if(this.getOwner() instanceof EntityPlayer)
		{
			((EntityPlayer)this.getOwner()).addChatMessage(msg);
		}
	}
	
	public boolean isPatroling()
	{
		if(this.dataWatcher.getWatchableObjectInt(26) == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void setPatroling(boolean bool)
	{
		if(bool)
		{
			this.getDataWatcher().updateObject(26, 1);
			addPatrolTasks();
		}
		else
		{
			this.getDataWatcher().updateObject(26, 0);
			addDefaultTasks();
		}
		isPatroling = bool;
	}
	
	public BlockPos getPatrolPoint()
	{
		return patrolPoint;
	}
	
	public void setPatrolPoint(BlockPos pos)
	{
		this.patrolPoint = pos;
	}
	
	public boolean hasPatrolPoint()
	{
		if(this.patrolPoint == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	@Override
	public boolean canPickUpLoot() {
		return true;
	}
	
	public void setDoOpenInventory(boolean b)
	{
		if(b)
		{
			this.getDataWatcher().updateObject(27, 1);
		}
		else
		{
			this.getDataWatcher().updateObject(27, 0);
		}
		doOpenInventory = b;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if(!worldObj.isRemote)
		{
			if(doOpenInventory)
			{
				if(this.getOwner() instanceof EntityPlayer)
				{
					((EntityPlayer)this.getOwner()).openGui(ModRegistry.getInstance(), GuiIDs.INVENTORY_ID, worldObj, this.getEntityId(), 0, 0);
				}
				setDoOpenInventory(false);
			}
		}
		
		if(!this.isSitting())
		{
			tickBanter++;
			
			int banter = this.rand.nextInt(450000);
			
	        if(tickBanter >= banter && !this.isDead)
	        {
	        	this.createMessage(CompanionStrings.getBanter(rand, this));
	        	tickBanter = -banter;
	        }
		}
	}
	
	@Override
	public void onDeath(DamageSource source) {
		super.onDeath(source);
		
		this.inventory.dropAllItems();
	}
	
	public EntityItem func_146097_a(ItemStack stack, boolean p_146097_2_, boolean p_146097_3_)
    {
        if (stack == null)
        {
            return null;
        }
        else if (stack.stackSize == 0)
        {
            return null;
        }
        else
        {
            EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY - 0.30000001192092896D + (double)this.getEyeHeight(), this.posZ, stack);
            entityitem.delayBeforeCanPickup = 40;

            if(p_146097_3_)
            {
                entityitem.func_145799_b(this.getCommandSenderName());
            }
            float f = 0.1F;
            float f1;

            if(p_146097_2_)
            {
                f1 = this.rand.nextFloat() * 0.5F;
                float f2 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                entityitem.motionX = (double)(-MathHelper.sin(f2) * f1);
                entityitem.motionZ = (double)(MathHelper.cos(f2) * f1);
                entityitem.motionY = 0.20000000298023224D;
            }
            else
            {
                f = 0.3F;
                entityitem.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
                entityitem.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
                entityitem.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI) * f + 0.1F);
                f = 0.02F;
                f1 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                f *= this.rand.nextFloat();
                entityitem.motionX += Math.cos((double)f1) * (double)f;
                entityitem.motionY += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                entityitem.motionZ += Math.sin((double)f1) * (double)f;
            }
            this.joinEntityItemWithWorld(entityitem);
            return entityitem;
        }
    }
	
	public void joinEntityItemWithWorld(EntityItem item)
    {
        if(captureDrops)
        {
            capturedDrops.add(item);
            return;
        }
        this.worldObj.spawnEntityInWorld(item);
    }
	
	@Override
	public ItemStack getEquipmentInSlot(int slot) {
		if(slot == 0)
		{
			if(this.inventory.getBestItemOfType(ItemSword.class) != null)
			{// TODO: Add Check For Mining And Other Activities
				return this.inventory.getBestItemOfType(ItemSword.class);
			}
			else
			{
				return this.inventory.getCurrentItem();
			}
		}
		else
		{
			switch((slot-1))
			{
			case 0:
				ItemStack helmet = this.inventory.getBestArmor(3);
				
				if(helmet != null)
				{
					return helmet;
				}
				break;
			case 1:
				ItemStack chestplate = this.inventory.getBestArmor(2);
				
				if(chestplate != null)
				{
					return chestplate;
				}
				break;
			case 2:
				ItemStack leggings = this.inventory.getBestArmor(1);
				
				if(leggings != null)
				{
					return leggings;
				}
				break;
			case 3:
				ItemStack boots = this.inventory.getBestArmor(0);
				
				if(boots != null)
				{
					return boots;
				}
				break;
			}
		}
		return null;
	}
	
	@Override
	public int getTotalArmorValue() {
		return this.inventory.getTotalArmorValue();
	}
	
	@Override
	public ItemStack getHeldItem() {
		return this.inventory.getCurrentItem();
	}
	
	@Override
	protected void damageArmor(float damage) {
		this.inventory.damageArmor(damage);
	}
	
	public void openInventory(EntityPlayer player)
	{
		// TODO:
		//ModRegistry.getPacketHandler().snw.sendTo(message, player);
	}
	
	public CompanionInventory getInventory()
	{
		return inventory;
	}
	
	@Override
	public void swingItem() {
		// TODO:
		super.swingItem();
	}

}
