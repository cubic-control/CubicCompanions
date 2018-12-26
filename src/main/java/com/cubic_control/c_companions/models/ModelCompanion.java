package com.cubic_control.c_companions.models;

import com.cubic_control.c_companions.entities.EntityCompanion;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class ModelCompanion extends ModelBiped{
	private ModelRenderer compBreasts;

    public ModelCompanion()
    {
        this(0.0F);
    }

    public ModelCompanion(float par1)
    {
        this(par1, 0.0F, 64, 64);
    }
    
    public ModelCompanion(float par1, float par2, int texW, int texH)
    {
    	this.textureWidth = texW;
    	this.textureHeight = texH;
    	
    	// Create NON-MIRRORED Biped Model
    	this.bipedCloak = new ModelRenderer(this, 0, 0);
        this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, par1);
        this.bipedEars = new ModelRenderer(this, 24, 0);
        this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, par1);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1 + 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + par2, 0.0F);
    	
    	
    	this.compBreasts = new ModelRenderer(this, 19, 21);
    	this.compBreasts.addBox(-4.0F, 2.0F, -2.5F, 8, 4, 1, par1);
    	this.compBreasts.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
    }
    
    @Override
    public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6) {
    	super.render(entity, f1, f2, f3, f4, f5, f6);
    	
    	if(!((EntityCompanion)entity).isMale() && !((EntityCompanion)entity).isChild())
    	{
    		this.compBreasts.render(f6);
    	}
    }
    
    @Override
    public void setRotationAngles(float f1, float f2, float f3, float f4, float f5, float f6, Entity entity) {
    	super.setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
    	
    	this.compBreasts.rotateAngleX = this.bipedBody.rotateAngleX;
    	this.compBreasts.rotateAngleY = this.bipedBody.rotateAngleY;
    	this.compBreasts.rotateAngleZ = this.bipedBody.rotateAngleZ;
    }
    
}