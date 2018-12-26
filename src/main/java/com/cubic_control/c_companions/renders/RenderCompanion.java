package com.cubic_control.c_companions.renders;

import org.lwjgl.opengl.GL11;

import com.cubic_control.c_companions.entities.EntityCompanion;
import com.cubic_control.c_companions.main.RefStrings;
import com.cubic_control.c_companions.models.ModelCompanion;
import com.cubic_control.cubic_core.Utils.GlStateManager;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderCompanion extends RenderBiped{
	private ResourceLocation skin;
	private ResourceLocation eyes;
	private ResourceLocation pants;
	private ResourceLocation shirt;
	private ResourceLocation shoes;
	private ResourceLocation gloves;
	private ResourceLocation hair;
	
	public ModelCompanion compModel;

	public RenderCompanion(ModelCompanion model, float p_i1257_2_) {
		super(model, p_i1257_2_);
		this.mainModel = model;
		this.modelBipedMain = (ModelCompanion)this.mainModel;
		this.compModel = model;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLiving entity) {
		EntityCompanion comp = (EntityCompanion)entity;
		if(comp.isMale())
		{
			skin = CompanionResources.malSkins.get(comp.getIdSkin());
		}
		else
		{
			skin = CompanionResources.femSkins.get(comp.getIdSkin());
		}
		return skin;
	}
	
	@Override
	protected void renderModel(EntityLivingBase entity, float f1, float f2, float f3, float f4, float f5, float f6) {
		super.renderModel(entity, f1, f2, f3, f4, f5, f6);
		// This Method Works; Adds On To Skin;
		if(entity instanceof EntityCompanion)
		{
			EntityCompanion comp = (EntityCompanion)entity;
			
			if(comp.getCustomNameTag().equalsIgnoreCase("Harley"))
			{
				eyes = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/harley/eyes_harley.png");
				pants = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/harley/pants_harley.png");
				shirt = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/harley/shirt_harley.png");
				shoes = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/harley/shoes_harley.png");
				gloves = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/harley/gloves_harley.png");
				hair = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/harley/hood_harley.png");
			}
			else if(comp.getCustomNameTag().equalsIgnoreCase("MissHarley"))
			{
				eyes = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/missHarley/eyes.png");
				pants = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/missHarley/pants.png");
				shirt = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/missHarley/shirt.png");
				shoes = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/missHarley/boots.png");
				gloves = CompanionResources.none;
				hair = new ResourceLocation(RefStrings.modid + ":textures/mobs/special/missHarley/hair.png");
			}
			else
			{
				if(comp.isMale())
				{
					pants = CompanionResources.pants.get(comp.getIdPants());
					shirt = CompanionResources.shirts.get(comp.getIdShirt());
					hair = CompanionResources.malHair.get(comp.getIdHair());
					eyes = CompanionResources.malEyes.get(comp.getIdEyes());
				}
				else
				{
					pants = CompanionResources.femPants.get(comp.getIdPants());
					shirt = CompanionResources.femShirts.get(comp.getIdShirt());
					hair = CompanionResources.femHair.get(comp.getIdHair());
					eyes = CompanionResources.femEyes.get(comp.getIdEyes());
				}
				shoes = CompanionResources.shoes.get(comp.getIdShoes());
				gloves = CompanionResources.gloves.get(comp.getIdGloves());
			}
			renderPart(comp, f1, f2, f3, f4, f5, f6, eyes);
			renderPart(comp, f1, f2, f3, f4, f5, f6, pants);
			renderPart(comp, f1, f2, f3, f4, f5, f6, shirt);
			renderPart(comp, f1, f2, f3, f4, f5, f6, shoes);
			renderPart(comp, f1, f2, f3, f4, f5, f6, gloves);
			renderPart(comp, f1, f2, f3, f4, f5, f6, hair);
		}
	}
	
	private void renderPart(EntityCompanion entity, float f1, float f2, float f3, float f4, float f5, float f6, ResourceLocation part)
	{
		ModelCompanion model;
		
		for (int i = 0; i < 4; ++i)
		{
			model = this.compModel;
			
			GlStateManager.color(1, 1, 1, 1);
			this.bindTexture(part);
			
			model.bipedHead.showModel = true;
			model.bipedHeadwear.showModel = true;
			model.bipedBody.showModel = true;
			model.bipedRightArm.showModel = true;
			model.bipedLeftArm.showModel = true;
			model.bipedRightLeg.showModel = true;
			model.bipedLeftLeg.showModel = true;
			
			model.onGround = this.mainModel.onGround;
			model.isRiding = this.mainModel.isRiding;
			model.isChild = this.mainModel.isChild;
			
			if (this.mainModel instanceof ModelCompanion)
			{
				model.heldItemLeft = ((ModelCompanion)this.mainModel).heldItemLeft;
				model.heldItemRight = ((ModelCompanion)this.mainModel).heldItemRight;
				model.isSneak = ((ModelCompanion)this.mainModel).isSneak;
				model.aimedBow = ((ModelCompanion)this.mainModel).aimedBow;
			}
			model.setLivingAnimations(entity, f1, f2, 0.0F);
			model.render(entity, f1, f2, f3, f4, f5, f6);
			
			// Start alpha render
			GlStateManager.disableLighting();
			this.bindTexture(part);
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			float time = entity.ticksExisted / 10.0F;
			float sTime = (float) Math.sin(time) * 0.5F + 0.5F;
			
			float r = 0.2F * sTime;
			float g = 1.0F * sTime;
			float b = 0.2F * sTime;
			
			model.render(entity, f1, f2, f3, f4, f5, f6);
			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.enableLighting();
		}
	}

}
