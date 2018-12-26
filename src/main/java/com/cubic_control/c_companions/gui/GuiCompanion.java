package com.cubic_control.c_companions.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.cubic_control.c_companions.entities.EntityCompanion;
import com.cubic_control.c_companions.main.ModRegistry;
import com.cubic_control.c_companions.main.RefStrings;
import com.cubic_control.c_companions.network.PacketCustomizeMessage;
import com.cubic_control.c_companions.network.PacketInventoryMessage;
import com.cubic_control.c_companions.network.PacketPatrolPointMessage;
import com.cubic_control.c_companions.renders.CompanionResources;
import com.cubic_control.c_companions.network.PacketPatrolMessage;
import com.cubic_control.cubic_core.Utils.BlockPos;
import com.cubic_control.cubic_core.Utils.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiCompanion extends GuiScreen{
	private final EntityCompanion companion;
	
	private int currentPage;
	private int timeSinceLastClick;
	
	private final int ImageHeight = 250, ImageWidth = 250, ImageScale = 250;
	private static final ResourceLocation GUITextures = new ResourceLocation(RefStrings.modid + ":textures/gui/back.png");
	
	public GuiCompanion(EntityCompanion entity)
	{
		this.companion = entity;
	}
	
	@Override
	public void initGui() {
		currentPage = 0;
		drawMainMenu();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void updateScreen() 
	{
		if(timeSinceLastClick < 100)
		{
			timeSinceLastClick++;
		}
	}
	
	@Override
	public void drawScreen(int widthIn, int heightIn, float par2) {
		if(currentPage == EnumInteraction.CUSTOMIZE.getId())
		{
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.enableColorMaterial();
			this.mc.getTextureManager().bindTexture(GUITextures);
			
			int k = (this.width - this.ImageWidth) / 2;
	        int l = (this.height - this.ImageHeight) / 2;
	        
	        this.drawTexturedModalRect(k, l, 0, 0, ImageWidth, ImageHeight);
	        
	        drawEntity(k + 125, l + 175, 40, (float)(k + 51) - ImageWidth, (float)(l + 75 - 50) - ImageHeight, companion);
		}
		
		super.drawScreen(widthIn, heightIn, par2);
	}
	
	public void drawEntity(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase comp)
    {
		NBTTagCompound tag = new NBTTagCompound();
		comp.writeToNBT(tag);
		
		EntityCompanion entity = new EntityCompanion(comp.worldObj);
		entity.readEntityFromNBT(tag);
		entity.tasks.taskEntries.clear();
		entity.targetTasks.taskEntries.clear();
		entity.shouldRenderInPass(1);
		entity.setSneaking(false);
		entity.rotationPitch = 0.0F;
		entity.rotationYaw = 0.0F;
		entity.rotationYawHead = 0.0F;
		entity.prevRotationPitch = 0.0F;
		entity.prevRotationYaw = 0.0F;
		entity.prevRotationYawHead = 0.0F;
		
		entity.setPositionAndRotation(0, 0, 0, 0, 0);
		entity.setPositionAndUpdate(0, 0, 0);
		
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
        GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset;
        float f3 = entity.rotationYaw;
        float f4 = entity.rotationPitch;
        float f5 = entity.prevRotationYawHead;
        float f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        entity.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
        entity.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
        entity.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
        entity.rotationYawHead = entity.rotationYaw;
        entity.prevRotationYawHead = entity.rotationYaw;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        entity.renderYawOffset = f2;
        entity.rotationYaw = f3;
        entity.rotationPitch = f4;
        entity.prevRotationYawHead = f5;
        entity.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        
        entity.setDead();
    }
	
	@Override
	protected void keyTyped(char c, int keyCode) {
		if(keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode())
		{
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if(timeSinceLastClick <= 2)
		{
			return;
		}
		timeSinceLastClick = 0;
		EnumInteraction interaction = EnumInteraction.fromId(button.id);
		
		if(interaction != null)
		{
			switch(interaction)
			{
			case CUSTOMIZE:
				drawCustomizeMenu();
				break;
			case DE_EYES:
				aCiD(companion, 0, false);
				break;
			case DE_GLOVES:
				aCiD(companion, 1, false);
				break;
			case DE_HAIR:
				aCiD(companion, 2, false);
				break;
			case DE_PANTS:
				aCiD(companion, 3, false);
				break;
			case DE_SHIRT:
				aCiD(companion, 4, false);
				break;
			case DE_SHOES:
				aCiD(companion, 5, false);
				break;
			case DE_SKIN:
				aCiD(companion, 6, false);
				break;
			case IN_EYES:
				aCiD(companion, 0, true);
				break;
			case IN_GLOVES:
				aCiD(companion, 1, true);
				break;
			case IN_HAIR:
				aCiD(companion, 2, true);
				break;
			case IN_PANTS:
				aCiD(companion, 3, true);
				break;
			case IN_SHIRT:
				aCiD(companion, 4, true);
				break;
			case IN_SHOES:
				aCiD(companion, 5, true);
				break;
			case IN_SKIN:
				aCiD(companion, 6, true);
				break;
			case PATROL:
				drawPatrolMenu();
				break;
			case SET_PATROLING:
				if(companion.hasPatrolPoint())
				{
					ModRegistry.getPacketHandler().snw.sendToServer(new PacketPatrolMessage(!companion.isPatroling(), companion));
					companion.setPatroling(!companion.isPatroling());
				}
				break;
			case SET_PATROL_POINT:
				BlockPos pos = new BlockPos((int)companion.posX, (int)companion.posY, (int)companion.posZ);
				ModRegistry.getPacketHandler().snw.sendToServer(new PacketPatrolPointMessage(pos, companion));
				companion.setPatrolPoint(pos);
				break;
			case SET_MINING:
				//if(companion.hasTool(ItemPickaxe))
				//{
					//ModRegistry.getPacketHandler().snw.sendToServer(new PacketMiningMessage(!companion.isMining(), companion));
					//companion.setMining(!companion.isMining());
				//}
				break;
			case INTERACT:
				break;
			case INVENTORY:
				ModRegistry.getPacketHandler().snw.sendToServer(new PacketInventoryMessage(true, companion));
				companion.setDoOpenInventory(true);
				break;
			}
			if(interaction.getId() == EnumInteraction.CUSTOMIZE.getId())
			{
				NBTTagCompound tag = new NBTTagCompound();
				companion.writeEntityToNBT(tag);
				
				ModRegistry.getPacketHandler().snw.sendToServer(new PacketCustomizeMessage(tag, companion.worldObj.provider.dimensionId, companion));
			}
		}
	}
	
	private void drawMainMenu()
	{
		this.buttonList.clear();
		
		int offWidth = width / 2;
		int offHeight = height / 2;
		
		buttonList.add(new GuiButton(EnumInteraction.CUSTOMIZE.getId(), offWidth - 35, offHeight - 50, 75, 20, "Customize"));
		buttonList.add(new GuiButton(EnumInteraction.PATROL.getId(), offWidth + 45, offHeight - 10, 75, 20, "Patrol"));
		buttonList.add(new GuiButton(EnumInteraction.INVENTORY.getId(), offWidth - 35, offHeight + 30, 75, 20, "Inventory"));
	}
	
	private void drawCustomizeMenu()
	{
		currentPage = EnumInteraction.CUSTOMIZE.getId();
		
		buttonList.clear();
		int offLeft = (width - ImageWidth) / 2;
		int offRight = (width + ImageWidth) / 2;
		
		int buttonSizeX = 20;
		int buttonSizeY = 20;
		
		int yPos = 30;
		int yChange = 30;
		
		// Increase
		buttonList.add(new GuiButton(EnumInteraction.IN_HAIR.getId(), offLeft + 200, yPos, buttonSizeX, buttonSizeY, ">")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.IN_EYES.getId(), offLeft + 200, yPos, buttonSizeX, buttonSizeY, ">")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.IN_SKIN.getId(), offLeft + 200, yPos, buttonSizeX, buttonSizeY, ">")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.IN_SHIRT.getId(), offLeft + 200, yPos, buttonSizeX, buttonSizeY, ">")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.IN_GLOVES.getId(), offLeft + 200, yPos, buttonSizeX, buttonSizeY, ">")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.IN_PANTS.getId(), offLeft + 200, yPos, buttonSizeX, buttonSizeY, ">")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.IN_SHOES.getId(), offLeft + 200, yPos, buttonSizeX, buttonSizeY, ">")); yPos += yChange;
		
		yPos = 30;
		
		// Decrease
		buttonList.add(new GuiButton(EnumInteraction.DE_HAIR.getId(), offRight - 200, yPos, buttonSizeX, buttonSizeY, "<")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.DE_EYES.getId(), offRight - 200, yPos, buttonSizeX, buttonSizeY, "<")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.DE_SKIN.getId(), offRight - 200, yPos, buttonSizeX, buttonSizeY, "<")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.DE_SHIRT.getId(), offRight - 200, yPos, buttonSizeX, buttonSizeY, "<")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.DE_GLOVES.getId(), offRight - 200, yPos, buttonSizeX, buttonSizeY, "<")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.DE_PANTS.getId(), offRight - 200, yPos, buttonSizeX, buttonSizeY, "<")); yPos += yChange;
		buttonList.add(new GuiButton(EnumInteraction.DE_SHOES.getId(), offRight - 200, yPos, buttonSizeX, buttonSizeY, "<")); yPos += yChange;
	}
	
	private void drawPatrolMenu()
	{
		currentPage = EnumInteraction.PATROL.getId();
		
		buttonList.clear();
		
		int offWidth = width / 2;
		
		String buttonText = "Set Patrol Point";
		
		if(companion.hasPatrolPoint())
		{
			buttonText = "Change Patrol Point";
		}
		
		buttonList.add(new GuiButton(EnumInteraction.SET_PATROL_POINT.getId(), offWidth, 50, 80, 20, buttonText));
		
		buttonText = "Start Patroling";
		
		if(companion.isPatroling())
		{
			buttonText = "End Patroling";
		}
		buttonList.add(new GuiButton(EnumInteraction.SET_PATROLING.getId(), offWidth, 100, 75, 20, buttonText));
	}
	
	private void aCiD(EntityCompanion ent, int type, boolean increase)
	{
		int max = 0;
		
		if(increase)
		{
			switch(type)
			{
			case 0: // Eyes
				if(ent.isMale())
				{
					max = CompanionResources.malEyes.size()-1;
					
					if((ent.getIdEyes()+1) > max)
					{
						ent.setIdEyes(0);
					}
					else
					{
						ent.setIdEyes(ent.getIdEyes()+1);
					}
				}
				else
				{
					max = CompanionResources.femEyes.size()-1;
					
					if((ent.getIdEyes()+1) > max)
					{
						ent.setIdEyes(0);
					}
					else
					{
						ent.setIdEyes(ent.getIdEyes()+1);
					}
				}
				break;
			case 1: // Gloves
				max = CompanionResources.gloves.size()-1;
				
				if((ent.getIdGloves()+1) > max)
				{
					ent.setIdGloves(0);
				}
				else
				{
					ent.setIdGloves(ent.getIdGloves()+1);
				}
				break;
			case 2: // Hair
				if(ent.isMale())
				{
					max = CompanionResources.malHair.size()-1;
					
					if((ent.getIdHair()+1) > max)
					{
						ent.setIdHair(0);
					}
					else
					{
						ent.setIdHair(ent.getIdHair()+1);
					}
				}
				else
				{
					max = CompanionResources.femHair.size()-1;
					
					if((ent.getIdHair()+1) > max)
					{
						ent.setIdHair(0);
					}
					else
					{
						ent.setIdHair(ent.getIdHair()+1);
					}
				}
				break;
			case 3: // Pants
				if(ent.isMale())
				{
					max = CompanionResources.pants.size()-1;
					
					if((ent.getIdPants()+1) > max)
					{
						ent.setIdPants(0);
					}
					else
					{
						ent.setIdPants(ent.getIdPants()+1);
					}
				}
				else
				{
					max = CompanionResources.femPants.size()-1;
					
					if((ent.getIdPants()+1) > max)
					{
						ent.setIdPants(0);
					}
					else
					{
						ent.setIdPants(ent.getIdPants()+1);
					}
				}
				break;
			case 4: // Shirt
				if(ent.isMale())
				{
					max = CompanionResources.shirts.size()-1;
					
					if((ent.getIdShirt()+1) > max)
					{
						ent.setIdShirt(0);
					}
					else
					{
						ent.setIdShirt(ent.getIdShirt()+1);
					}
				}
				else
				{
					max = CompanionResources.femShirts.size()-1;
					
					if((ent.getIdShirt()+1) > max)
					{
						ent.setIdShirt(0);
					}
					else
					{
						ent.setIdShirt(ent.getIdShirt()+1);
					}
				}
				break;
			case 5: // Shoes
				max = CompanionResources.shoes.size()-1;
				
				if((ent.getIdShoes()+1) > max)
				{
					ent.setIdShoes(0);
				}
				else
				{
					ent.setIdShoes(ent.getIdShoes()+1);
				}
				break;
			case 6: // Skin
				if(ent.isMale())
				{
					max = CompanionResources.malSkins.size()-1;
					
					if((ent.getIdSkin()+1) > max)
					{
						ent.setIdSkin(0);
					}
					else
					{
						ent.setIdSkin(ent.getIdPants()+1);
					}
				}
				else
				{
					max = CompanionResources.femSkins.size()-1;
					
					if((ent.getIdSkin()+1) > max)
					{
						ent.setIdSkin(0);
					}
					else
					{
						ent.setIdSkin(ent.getIdSkin()+1);
					}
				}
				break;
			}
		}
		else
		{
			switch(type)
			{
			case 0: // Eyes
				if(ent.isMale())
				{
					max = CompanionResources.malEyes.size()-1;
					
					if((ent.getIdEyes()-1) < 0)
					{
						ent.setIdEyes(max);
					}
					else
					{
						ent.setIdEyes(ent.getIdEyes()-1);
					}
				}
				else
				{
					max = CompanionResources.femEyes.size()-1;
					
					if((ent.getIdEyes()-1) < 0)
					{
						ent.setIdEyes(max);
					}
					else
					{
						ent.setIdEyes(ent.getIdEyes()-1);
					}
				}
				break;
			case 1: // Gloves
				max = CompanionResources.gloves.size()-1;
				
				if((ent.getIdGloves()-1) < 0)
				{
					ent.setIdGloves(max);
				}
				else
				{
					ent.setIdGloves(ent.getIdGloves()-1);
				}
				break;
			case 2: // Hair
				if(ent.isMale())
				{
					max = CompanionResources.malHair.size()-1;
					
					if((ent.getIdHair()-1) < 0)
					{
						ent.setIdHair(max);
					}
					else
					{
						ent.setIdHair(ent.getIdHair()-1);
					}
				}
				else
				{
					max = CompanionResources.femHair.size()-1;
					
					if((ent.getIdHair()-1) < 0)
					{
						ent.setIdHair(max);
					}
					else
					{
						ent.setIdHair(ent.getIdHair()-1);
					}
				}
				break;
			case 3: // Pants
				if(ent.isMale())
				{
					max = CompanionResources.pants.size()-1;
					
					if((ent.getIdPants()-1) < 0)
					{
						ent.setIdPants(max);
					}
					else
					{
						ent.setIdPants(ent.getIdPants()-1);
					}
				}
				else
				{
					max = CompanionResources.femPants.size()-1;
					
					if((ent.getIdPants()-1) < 0)
					{
						ent.setIdPants(max);
					}
					else
					{
						ent.setIdPants(ent.getIdPants()-1);
					}
				}
				break;
			case 4: // Shirt
				if(ent.isMale())
				{
					max = CompanionResources.shirts.size()-1;
					
					if((ent.getIdShirt()-1) < 0)
					{
						ent.setIdShirt(max);
					}
					else
					{
						ent.setIdShirt(ent.getIdShirt()-1);
					}
				}
				else
				{
					max = CompanionResources.femShirts.size()-1;
					
					if((ent.getIdShirt()-1) < 0)
					{
						ent.setIdShirt(max);
					}
					else
					{
						ent.setIdShirt(ent.getIdShirt()-1);
					}
				}
				break;
			case 5: // Shoes
				max = CompanionResources.shoes.size()-1;
				
				if((ent.getIdShoes()-1) < 0)
				{
					ent.setIdShoes(max);
				}
				else
				{
					ent.setIdShoes(ent.getIdShoes()-1);
				}
				break;
			case 6: // Skin
				if(ent.isMale())
				{
					max = CompanionResources.malSkins.size()-1;
					
					if((ent.getIdSkin()-1) < 0)
					{
						ent.setIdSkin(max);
					}
					else
					{
						ent.setIdSkin(ent.getIdPants()-1);
					}
				}
				else
				{
					max = CompanionResources.femSkins.size()-1;
					
					if((ent.getIdSkin()-1) < 0)
					{
						ent.setIdSkin(max);
					}
					else
					{
						ent.setIdSkin(ent.getIdSkin()-1);
					}
				}
				break;
			}
		}
	}

}
