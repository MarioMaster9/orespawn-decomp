package danger.orespawn;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;


public class ModelBee extends ModelBase
{
	private float wingspeed = 1.0F;
	ModelRenderer Sting;
	ModelRenderer Abdomnem1;
	ModelRenderer Abdomnem2;
	ModelRenderer Abdomnem3;
	ModelRenderer Abdomnem4;
	ModelRenderer Abdomnem5;
	ModelRenderer MainBody;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer WingRight;
	ModelRenderer WingLeft;
	ModelRenderer RA1;
	ModelRenderer LA1;
	ModelRenderer LA2;
	ModelRenderer RA2;
	ModelRenderer RA3;
	ModelRenderer LA3;
	ModelRenderer LeftPom;
	ModelRenderer RightPom;
	ModelRenderer LeftPincerExtra;
	ModelRenderer LeftPincerMain;
	ModelRenderer RightPincerMain;
	ModelRenderer RightPincerExtra;

	
	public ModelBee(float f1)
	{
		this.wingspeed = f1;
		
		this.textureWidth = 256;
		this.textureHeight = 256;
		
		
		this.Sting = new ModelRenderer(this, 68, 0);
		this.Sting.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2);
		this.Sting.setRotationPoint(0.0F, 16.0F, 1.0F);
		this.Sting.setTextureSize(256, 256);
		this.Sting.mirror = true;
		this.setRotation(this.Sting, (-(float)Math.PI / 4F), 0.0F, 0.0F);
		this.Abdomnem1 = new ModelRenderer(this, 64, 12);
		this.Abdomnem1.addBox(-2.0F, 0.0F, 0.0F, 4, 8, 4);
		this.Abdomnem1.setRotationPoint(0.0F, 9.0F, 2.0F);
		this.Abdomnem1.setTextureSize(256, 256);
		this.Abdomnem1.mirror = true;
		this.setRotation(this.Abdomnem1, (-(float)Math.PI / 6F), 0.0F, 0.0F);
		this.Abdomnem2 = new ModelRenderer(this, 60, 24);
		this.Abdomnem2.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 6);
		this.Abdomnem2.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.Abdomnem2.setTextureSize(256, 256);
		this.Abdomnem2.mirror = true;
		this.setRotation(this.Abdomnem2, 0.0F, 0.0F, 0.0F);
		this.Abdomnem3 = new ModelRenderer(this, 56, 36);
		this.Abdomnem3.addBox(-4.0F, 0.0F, 0.0F, 8, 7, 8);
		this.Abdomnem3.setRotationPoint(0.0F, 1.0F, -2.0F);
		this.Abdomnem3.setTextureSize(256, 256);
		this.Abdomnem3.mirror = true;
		this.setRotation(this.Abdomnem3, 0.2617994F, 0.0F, 0.0F);
		this.Abdomnem4 = new ModelRenderer(this, 53, 51);
		this.Abdomnem4.addBox(-5.0F, 0.0F, 0.0F, 10, 12, 10);
		this.Abdomnem4.setRotationPoint(0.0F, -6.0F, -8.0F);
		this.Abdomnem4.setTextureSize(256, 256);
		this.Abdomnem4.mirror = true;
		this.setRotation(this.Abdomnem4, 0.5934119F, 0.0F, 0.0F);
		this.Abdomnem5 = new ModelRenderer(this, 48, 73);
		this.Abdomnem5.addBox(-6.0F, 0.0F, 0.0F, 12, 12, 12);
		this.Abdomnem5.setRotationPoint(0.0F, -6.0F, -15.0F);
		this.Abdomnem5.setTextureSize(256, 256);
		this.Abdomnem5.mirror = true;
		this.setRotation(this.Abdomnem5, 1.099557F, 0.0F, 0.0F);
		this.MainBody = new ModelRenderer(this, 48, 97);
		this.MainBody.addBox(-6.0F, 0.0F, -6.0F, 12, 14, 12);
		this.MainBody.setRotationPoint(0.0F, -12.0F, -24.0F);
		this.MainBody.setTextureSize(256, 256);
		this.MainBody.mirror = true;
		this.setRotation(this.MainBody, 1.48353F, 0.0F, 0.0F);
		this.Neck = new ModelRenderer(this, 55, 123);
		this.Neck.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8);
		this.Neck.setRotationPoint(0.0F, -12.0F, -23.0F);
		this.Neck.setTextureSize(256, 256);
		this.Neck.mirror = true;
		this.setRotation(this.Neck, 0.0F, 0.0F, 0.0F);
		this.Head = new ModelRenderer(this, 51, 139);
		this.Head.addBox(-5.0F, -5.0F, -10.0F, 10, 10, 10);
		this.Head.setRotationPoint(0.0F, -13.0F, -28.0F);
		this.Head.setTextureSize(256, 256);
		this.Head.mirror = true;
		this.setRotation(this.Head, 0.2617994F, 0.0F, 0.0F);
		this.WingRight = new ModelRenderer(this, 0, 91);
		this.WingRight.addBox(0.0F, 0.0F, 0.0F, 0, 8, 24);
		this.WingRight.setRotationPoint(-4.0F, -14.0F, -15.0F);
		this.WingRight.setTextureSize(256, 256);
		this.WingRight.mirror = true;
		this.setRotation(this.WingRight, (-(float)Math.PI / 4F), (-(float)Math.PI / 6F), 2.617994F);
		this.WingLeft = new ModelRenderer(this, 96, 91);
		this.WingLeft.addBox(0.0F, 0.0F, 0.0F, 0, 8, 24);
		this.WingLeft.setRotationPoint(3.0F, -14.0F, -15.0F);
		this.WingLeft.setTextureSize(256, 256);
		this.WingLeft.mirror = true;
		this.setRotation(this.WingLeft, (-(float)Math.PI / 4F), ((float)Math.PI / 6F), -2.617994F);
		this.RA1 = new ModelRenderer(this, 47, 152);
		this.RA1.addBox(0.0F, -6.0F, -1.0F, 1, 6, 1);
		this.RA1.setRotationPoint(-3.0F, -17.0F, -31.0F);
		this.RA1.setTextureSize(256, 256);
		this.RA1.mirror = true;
		this.setRotation(this.RA1, 0.2617994F, ((float)Math.PI / 6F), 0.0F);
		this.LA1 = new ModelRenderer(this, 91, 152);
		this.LA1.addBox(0.0F, -6.0F, -1.0F, 1, 6, 1);
		this.LA1.setRotationPoint(2.0F, -17.0F, -32.0F);
		this.LA1.setTextureSize(256, 256);
		this.LA1.mirror = true;
		this.setRotation(this.LA1, 0.2617994F, (-(float)Math.PI / 6F), 0.0F);
		this.LA2 = new ModelRenderer(this, 91, 145);
		this.LA2.addBox(0.0F, -11.0F, 0.0F, 1, 6, 1);
		this.LA2.setRotationPoint(2.0F, -17.0F, -32.0F);
		this.LA2.setTextureSize(256, 256);
		this.LA2.mirror = true;
		this.setRotation(this.LA2, 0.4363323F, -0.6108652F, 0.0F);
		this.RA2 = new ModelRenderer(this, 47, 145);
		this.RA2.addBox(0.0F, -11.0F, 0.0F, 1, 6, 1);
		this.RA2.setRotationPoint(-3.0F, -17.0F, -31.0F);
		this.RA2.setTextureSize(256, 256);
		this.RA2.mirror = true;
		this.setRotation(this.RA2, 0.4363323F, 0.6108652F, 0.0F);
		this.RA3 = new ModelRenderer(this, 47, 138);
		this.RA3.addBox(0.0F, -16.0F, 2.0F, 1, 6, 1);
		this.RA3.setRotationPoint(-3.0F, -17.0F, -31.0F);
		this.RA3.setTextureSize(256, 256);
		this.RA3.mirror = true;
		this.setRotation(this.RA3, 0.6108652F, 0.6981317F, 0.0F);
		this.LA3 = new ModelRenderer(this, 91, 138);
		this.LA3.addBox(0.0F, -16.0F, 2.0F, 1, 6, 1);
		this.LA3.setRotationPoint(2.0F, -17.0F, -32.0F);
		this.LA3.setTextureSize(256, 256);
		this.LA3.mirror = true;
		this.setRotation(this.LA3, 0.6108652F, -0.6981317F, 0.0F);
		this.LeftPom = new ModelRenderer(this, 89, 134);
		this.LeftPom.addBox(4.0F, -16.0F, -6.0F, 2, 2, 2);
		this.LeftPom.setRotationPoint(2.0F, -17.0F, -32.0F);
		this.LeftPom.setTextureSize(256, 256);
		this.LeftPom.mirror = true;
		this.setRotation(this.LeftPom, 0.0F, 0.0F, 0.0F);
		this.RightPom = new ModelRenderer(this, 45, 134);
		this.RightPom.addBox(-5.0F, -16.0F, -7.0F, 2, 2, 2);
		this.RightPom.setRotationPoint(-3.0F, -17.0F, -31.0F);
		this.RightPom.setTextureSize(256, 256);
		this.RightPom.mirror = true;
		this.setRotation(this.RightPom, 0.0F, 0.0F, 0.0F);
		this.LeftPincerExtra = new ModelRenderer(this, 71, 166);
		this.LeftPincerExtra.addBox(-2.0F, 0.0F, -6.0F, 2, 1, 2);
		this.LeftPincerExtra.setRotationPoint(2.0F, -8.0F, -36.0F);
		this.LeftPincerExtra.setTextureSize(256, 256);
		this.LeftPincerExtra.mirror = true;
		this.setRotation(this.LeftPincerExtra, 0.1745329F, -0.1745329F, 0.0F);
		this.LeftPincerMain = new ModelRenderer(this, 71, 159);
		this.LeftPincerMain.addBox(0.0F, 0.0F, -6.0F, 2, 1, 6);
		this.LeftPincerMain.setRotationPoint(2.0F, -8.0F, -36.0F);
		this.LeftPincerMain.setTextureSize(256, 256);
		this.LeftPincerMain.mirror = true;
		this.setRotation(this.LeftPincerMain, 0.1745329F, -0.1745329F, 0.0F);
		this.RightPincerMain = new ModelRenderer(this, 55, 159);
		this.RightPincerMain.addBox(0.0F, 0.0F, -6.0F, 2, 1, 6);
		this.RightPincerMain.setRotationPoint(-4.0F, -8.0F, -36.0F);
		this.RightPincerMain.setTextureSize(256, 256);
		this.RightPincerMain.mirror = true;
		this.setRotation(this.RightPincerMain, 0.1745329F, 0.1745329F, 0.0F);
		this.RightPincerExtra = new ModelRenderer(this, 63, 166);
		this.RightPincerExtra.addBox(2.0F, 0.0F, -6.0F, 2, 1, 2);
		this.RightPincerExtra.setRotationPoint(-4.0F, -8.0F, -36.0F);
		this.RightPincerExtra.setTextureSize(256, 256);
		this.RightPincerExtra.mirror = true;
		this.setRotation(this.RightPincerExtra, 0.1745329F, 0.1745329F, 0.0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		float newangle = 0.0F;
		Bee b = (Bee)entity;
		
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		
		
		newangle = MathHelper.cos(f2 * 1.1F * this.wingspeed) * (float)Math.PI * 0.3F;
		this.WingLeft.rotateAngleZ = -1.745F - newangle;
		this.WingRight.rotateAngleZ = 1.754F + newangle;
		
		
		newangle = MathHelper.cos(f2 * 0.3F * this.wingspeed) * (float)Math.PI * 0.1F;
		this.LeftPincerMain.rotateAngleY = -0.274F + newangle;
		this.LeftPincerExtra.rotateAngleY = -0.274F + newangle;
		this.RightPincerMain.rotateAngleY = 0.274F - newangle;
		this.RightPincerExtra.rotateAngleY = 0.274F - newangle;
		
		
		newangle = MathHelper.cos(f2 * 0.21F * this.wingspeed) * (float)Math.PI * 0.06F;
		this.LA1.rotateAngleX = 0.261F + newangle;
		this.LA2.rotateAngleX = 0.436F + newangle;
		this.LA3.rotateAngleX = 0.611F + newangle;
		this.LeftPom.rotateAngleX = newangle;
		newangle = MathHelper.cos(f2 * 0.27F * this.wingspeed) * (float)Math.PI * 0.06F;
		this.RA1.rotateAngleX = 0.261F + newangle;
		this.RA2.rotateAngleX = 0.436F + newangle;
		this.RA3.rotateAngleX = 0.611F + newangle;
		this.RightPom.rotateAngleX = newangle;
		
		newangle = MathHelper.cos(f2 * 0.31F * this.wingspeed) * (float)Math.PI * 0.06F;
		this.LA1.rotateAngleZ = newangle;
		this.LA2.rotateAngleZ = newangle;
		this.LA3.rotateAngleZ = newangle;
		this.LeftPom.rotateAngleZ = newangle;
		newangle = MathHelper.cos(f2 * 0.37F * this.wingspeed) * (float)Math.PI * 0.06F;
		this.RA1.rotateAngleZ = newangle;
		this.RA2.rotateAngleZ = newangle;
		this.RA3.rotateAngleZ = newangle;
		this.RightPom.rotateAngleZ = newangle;
		
		
		
		if (b.getAttacking() == 0) {
			newangle = MathHelper.cos(f2 * 0.021F * this.wingspeed) * (float)Math.PI * 0.023F;
		} else {
			newangle = MathHelper.cos(f2 * 0.11F * this.wingspeed) * (float)Math.PI * 0.055F;
		}

		this.Abdomnem5.rotateAngleX = 1.099F + newangle;
		
		
		this.Abdomnem4.rotateAngleX = this.Abdomnem5.rotateAngleX + newangle - 0.35F;
		this.Abdomnem4.rotationPointY = (float)((double)this.Abdomnem5.rotationPointY + Math.cos((double)this.Abdomnem5.rotateAngleX) * 10.0D);
		this.Abdomnem4.rotationPointZ = (float)((double)this.Abdomnem5.rotationPointZ + Math.sin((double)this.Abdomnem5.rotateAngleX) * 10.0D);
		
		this.Abdomnem3.rotateAngleX = this.Abdomnem4.rotateAngleX + newangle - 0.35F;
		this.Abdomnem3.rotationPointY = (float)((double)this.Abdomnem4.rotationPointY + Math.cos((double)this.Abdomnem4.rotateAngleX) * 10.0D);
		this.Abdomnem3.rotationPointZ = (float)((double)this.Abdomnem4.rotationPointZ + Math.sin((double)this.Abdomnem4.rotateAngleX) * 10.0D);
		
		this.Abdomnem2.rotateAngleX = this.Abdomnem3.rotateAngleX + newangle - 0.35F;
		this.Abdomnem2.rotationPointY = (float)((double)this.Abdomnem3.rotationPointY + Math.cos((double)this.Abdomnem3.rotateAngleX) * 6.0D);
		this.Abdomnem2.rotationPointZ = (float)((double)this.Abdomnem3.rotationPointZ + Math.sin((double)this.Abdomnem3.rotateAngleX) * 6.0D);
		
		this.Abdomnem1.rotateAngleX = this.Abdomnem2.rotateAngleX + newangle - 0.35F;
		this.Abdomnem1.rotationPointY = (float)((double)this.Abdomnem2.rotationPointY + Math.cos((double)this.Abdomnem2.rotateAngleX) * 5.0D);
		this.Abdomnem1.rotationPointZ = (float)((double)this.Abdomnem2.rotationPointZ + Math.sin((double)this.Abdomnem2.rotateAngleX) * 5.0D);
		
		this.Sting.rotateAngleX = this.Abdomnem1.rotateAngleX + newangle - 0.35F;
		this.Sting.rotationPointY = (float)((double)this.Abdomnem1.rotationPointY + Math.cos((double)this.Abdomnem1.rotateAngleX) * 7.0D);
		this.Sting.rotationPointZ = 1.0F + (float)((double)this.Abdomnem1.rotationPointZ + Math.sin((double)this.Abdomnem1.rotateAngleX) * 7.0D);
		
		
		
		this.Sting.render(f5);
		this.Abdomnem1.render(f5);
		this.Abdomnem2.render(f5);
		this.Abdomnem3.render(f5);
		this.Abdomnem4.render(f5);
		this.Abdomnem5.render(f5);
		this.MainBody.render(f5);
		this.Neck.render(f5);
		this.Head.render(f5);
		this.WingRight.render(f5);
		this.WingLeft.render(f5);
		this.RA1.render(f5);
		this.LA1.render(f5);
		this.LA2.render(f5);
		this.RA2.render(f5);
		this.RA3.render(f5);
		this.LA3.render(f5);
		this.LeftPom.render(f5);
		this.RightPom.render(f5);
		this.LeftPincerExtra.render(f5);
		this.LeftPincerMain.render(f5);
		this.RightPincerMain.render(f5);
		this.RightPincerExtra.render(f5);
		
		
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}
}
