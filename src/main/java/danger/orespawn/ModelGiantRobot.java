package danger.orespawn;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;



public class ModelGiantRobot extends ModelBase
{
	private float wingspeed = 1.0F;
	private float hipy = 0.0F;
	ModelRenderer Hip;
	ModelRenderer Thigh;
	ModelRenderer Shin;
	ModelRenderer Foot1;
	ModelRenderer Foot2;
	ModelRenderer Foot3;
	ModelRenderer Thigh2;
	ModelRenderer Thigh3;
	ModelRenderer Back1;
	ModelRenderer Back2;
	ModelRenderer Back3;
	ModelRenderer Shoulders;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer Arm1;
	ModelRenderer Arm2;
	ModelRenderer Arm3;
	ModelRenderer Knuckles;

	
	public ModelGiantRobot(float f1)
	{
		this.wingspeed = f1;
		this.textureWidth = 256;
		this.textureHeight = 512;
		
		this.Hip = new ModelRenderer(this, 0, 0);
		this.Hip.addBox(-4.0F, -4.0F, -15.0F, 8, 8, 30);
		this.Hip.setRotationPoint(0.0F, -60.0F, 0.0F);
		this.Hip.setTextureSize(64, 32);
		this.Hip.mirror = true;
		this.setRotation(this.Hip, 0.0F, 0.0F, 0.0F);
		this.Thigh = new ModelRenderer(this, 0, 115);
		this.Thigh.addBox(-3.0F, -3.0F, -3.0F, 6, 43, 6);
		this.Thigh.setRotationPoint(0.0F, -58.0F, 0.0F);
		this.Thigh.setTextureSize(64, 32);
		this.Thigh.mirror = true;
		this.setRotation(this.Thigh, 0.0F, 0.0F, 0.0F);
		this.Shin = new ModelRenderer(this, 0, 167);
		this.Shin.addBox(-3.0F, -3.0F, -3.0F, 6, 43, 6);
		this.Shin.setRotationPoint(0.0F, -18.0F, 0.0F);
		this.Shin.setTextureSize(64, 32);
		this.Shin.mirror = true;
		this.setRotation(this.Shin, 0.0F, 0.0F, 0.0F);
		this.Foot1 = new ModelRenderer(this, 0, 282);
		this.Foot1.addBox(-7.0F, 38.0F, -11.0F, 14, 4, 17);
		this.Foot1.setRotationPoint(0.0F, -18.0F, 0.0F);
		this.Foot1.setTextureSize(64, 32);
		this.Foot1.mirror = true;
		this.setRotation(this.Foot1, 0.0F, 0.0F, 0.0F);
		this.Foot2 = new ModelRenderer(this, 0, 246);
		this.Foot2.addBox(-6.0F, 19.0F, -8.0F, 12, 19, 13);
		this.Foot2.setRotationPoint(0.0F, -18.0F, 0.0F);
		this.Foot2.setTextureSize(64, 32);
		this.Foot2.mirror = true;
		this.setRotation(this.Foot2, 0.0F, 0.0F, 0.0F);
		this.Foot3 = new ModelRenderer(this, 0, 219);
		this.Foot3.addBox(-5.0F, 5.0F, -5.0F, 10, 14, 9);
		this.Foot3.setRotationPoint(0.0F, -18.0F, 0.0F);
		this.Foot3.setTextureSize(64, 32);
		this.Foot3.mirror = true;
		this.setRotation(this.Foot3, 0.0F, 0.0F, 0.0F);
		this.Thigh2 = new ModelRenderer(this, 0, 43);
		this.Thigh2.addBox(-7.0F, -8.0F, -7.0F, 14, 24, 14);
		this.Thigh2.setRotationPoint(0.0F, -58.0F, 0.0F);
		this.Thigh2.setTextureSize(64, 32);
		this.Thigh2.mirror = true;
		this.setRotation(this.Thigh2, 0.0F, 0.0F, 0.0F);
		this.Thigh3 = new ModelRenderer(this, 0, 84);
		this.Thigh3.addBox(-5.0F, 16.0F, -5.0F, 10, 17, 10);
		this.Thigh3.setRotationPoint(0.0F, -58.0F, 0.0F);
		this.Thigh3.setTextureSize(64, 32);
		this.Thigh3.mirror = true;
		this.setRotation(this.Thigh3, 0.0F, 0.0F, 0.0F);
		this.Back1 = new ModelRenderer(this, 125, 138);
		this.Back1.addBox(-4.0F, -20.0F, -4.0F, 8, 24, 8);
		this.Back1.setRotationPoint(0.0F, -60.0F, 0.0F);
		this.Back1.setTextureSize(64, 32);
		this.Back1.mirror = true;
		this.setRotation(this.Back1, 0.0F, 0.0F, 0.0F);
		this.Back2 = new ModelRenderer(this, 125, 95);
		this.Back2.addBox(-13.0F, -42.0F, -10.0F, 26, 24, 16);
		this.Back2.setRotationPoint(0.0F, -60.0F, 0.0F);
		this.Back2.setTextureSize(64, 32);
		this.Back2.mirror = true;
		this.setRotation(this.Back2, 0.0F, 0.0F, 0.0F);
		this.Back3 = new ModelRenderer(this, 125, 43);
		this.Back3.addBox(-17.0F, -68.0F, -13.0F, 34, 26, 20);
		this.Back3.setRotationPoint(0.0F, -60.0F, 0.0F);
		this.Back3.setTextureSize(64, 32);
		this.Back3.mirror = true;
		this.setRotation(this.Back3, 0.0F, 0.0F, 0.0F);
		this.Shoulders = new ModelRenderer(this, 60, 200);
		this.Shoulders.addBox(-22.0F, -64.0F, -4.0F, 44, 8, 8);
		this.Shoulders.setRotationPoint(0.0F, -60.0F, 0.0F);
		this.Shoulders.setTextureSize(64, 32);
		this.Shoulders.mirror = true;
		this.setRotation(this.Shoulders, 0.0F, 0.0F, 0.0F);
		this.Neck = new ModelRenderer(this, 125, 29);
		this.Neck.addBox(-4.0F, -70.0F, -4.0F, 8, 2, 8);
		this.Neck.setRotationPoint(0.0F, -60.0F, 0.0F);
		this.Neck.setTextureSize(64, 32);
		this.Neck.mirror = true;
		this.setRotation(this.Neck, 0.0F, 0.0F, 0.0F);
		this.Head = new ModelRenderer(this, 127, 0);
		this.Head.addBox(-7.0F, -82.0F, -7.0F, 14, 12, 14);
		this.Head.setRotationPoint(0.0F, -60.0F, 0.0F);
		this.Head.setTextureSize(64, 32);
		this.Head.mirror = true;
		this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
		this.Arm1 = new ModelRenderer(this, 77, 250);
		this.Arm1.addBox(-6.0F, -6.0F, -6.0F, 12, 21, 12);
		this.Arm1.setRotationPoint(28.0F, -120.0F, 0.0F);
		this.Arm1.setTextureSize(64, 32);
		this.Arm1.mirror = true;
		this.setRotation(this.Arm1, 0.0F, 0.0F, 0.0F);
		this.Arm2 = new ModelRenderer(this, 73, 300);
		this.Arm2.addBox(-4.0F, 15.0F, -4.0F, 8, 24, 8);
		this.Arm2.setRotationPoint(28.0F, -120.0F, 0.0F);
		this.Arm2.setTextureSize(64, 32);
		this.Arm2.mirror = true;
		this.setRotation(this.Arm2, 0.0F, 0.0F, 0.0F);
		this.Arm3 = new ModelRenderer(this, 61, 350);
		this.Arm3.addBox(-3.0F, -3.0F, -3.0F, 6, 33, 6);
		this.Arm3.setRotationPoint(28.0F, -81.0F, 0.0F);
		this.Arm3.setTextureSize(64, 32);
		this.Arm3.mirror = true;
		this.setRotation(this.Arm3, 0.0F, 0.0F, 0.0F);
		this.Knuckles = new ModelRenderer(this, 56, 400);
		this.Knuckles.addBox(-7.0F, 30.0F, -5.0F, 14, 12, 10);
		this.Knuckles.setRotationPoint(28.0F, -81.0F, 0.0F);
		this.Knuckles.setTextureSize(256, 512);
		this.Knuckles.mirror = true;
		this.setRotation(this.Knuckles, 0.0F, 0.0F, 0.0F);
		
		this.hipy = this.Hip.rotationPointY;
		
	}

	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		GiantRobot e = (GiantRobot)((Entity)entity); // is this match ok?
		RenderGiantRobotInfo r = null;
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float newangle;

		
		
		
		
		r = e.getRenderGiantRobotInfo();
		float movescale = f1 * 0.65F;
		if (movescale > 1.0F) movescale = 1.0F;

		r.hipxdisplayangle = (float)(Math.cos((double)(-f2 * this.wingspeed)) * Math.PI * 0.1F * (double)movescale);
		r.hipydisplayangle = (float)(Math.sin((double)(-f2 * this.wingspeed)) * Math.PI * 0.1F * (double)movescale);
		r.thighdisplayangle[0] = (float)(Math.cos((double)(-f2 * this.wingspeed) + (Math.PI / 2D)) * Math.PI * (double)0.15F * (double)movescale) - (float)(0.19634954084936207 * (double)movescale);
		r.thighdisplayangle[1] = (float)(Math.cos((double)(-f2 * this.wingspeed) + Math.PI + (Math.PI / 2D)) * Math.PI * (double)0.15F * (double)movescale) - (float)(0.19634954084936207 * (double)movescale);
		r.shindisplayangle[0] = (float)((double)((float)(Math.cos((double)(-f2 * this.wingspeed) + Math.PI) * Math.PI * (double)0.2F * (double)movescale)) + 0.6283185400806344 * (double)movescale);
		r.shindisplayangle[1] = (float)((double)((float)(Math.cos((double)(-f2 * this.wingspeed)) * Math.PI * (double)0.2F * (double)movescale)) + 0.6283185400806344 * (double)movescale);
		
		newangle = (float)(Math.cos((double)(-f2 * this.wingspeed * 2.0F)) * (double)movescale);
		this.Hip.rotationPointY = this.hipy + newangle * 4.0F;
		
		this.Hip.rotateAngleX = r.hipxdisplayangle;
		this.Hip.rotateAngleY = (float)((double)r.hipydisplayangle + (Math.PI / 2D));
		this.Hip.render(f5);
		
		this.Thigh.rotateAngleX = this.Thigh2.rotateAngleX = this.Thigh3.rotateAngleX = r.thighdisplayangle[0];
		this.Thigh.rotationPointY = this.Thigh2.rotationPointY = this.Thigh3.rotationPointY = this.Hip.rotationPointY - (float)Math.sin((double)this.Hip.rotateAngleX) * 13.0F;
		this.Thigh.rotationPointZ = this.Thigh2.rotationPointZ = this.Thigh3.rotationPointZ = this.Hip.rotationPointZ + (float)Math.cos((double)this.Hip.rotateAngleX) * (float)Math.cos((double)this.Hip.rotateAngleY) * 13.0F;
		this.Thigh.rotationPointX = this.Thigh2.rotationPointX = this.Thigh3.rotationPointX = this.Hip.rotationPointX + (float)Math.cos((double)this.Hip.rotateAngleX) * (float)Math.sin((double)this.Hip.rotateAngleY) * 13.0F;
		this.Thigh.render(f5);
		this.Thigh2.render(f5);
		this.Thigh3.render(f5);
		this.Shin.rotateAngleX = r.shindisplayangle[0];
		this.Shin.rotationPointY = this.Thigh.rotationPointY + (float)Math.cos((double)this.Thigh.rotateAngleX) * 40.0F;
		this.Shin.rotationPointZ = this.Thigh.rotationPointZ + (float)Math.sin((double)this.Thigh.rotateAngleX) * 40.0F;
		this.Shin.rotationPointX = this.Thigh.rotationPointX;
		this.Shin.render(f5);
		this.Foot1.rotateAngleX = this.Foot2.rotateAngleX = this.Foot3.rotateAngleX = r.shindisplayangle[0];
		this.Foot1.rotationPointY = this.Foot2.rotationPointY = this.Foot3.rotationPointY = this.Shin.rotationPointY;
		this.Foot1.rotationPointZ = this.Foot2.rotationPointZ = this.Foot3.rotationPointZ = this.Shin.rotationPointZ;
		this.Foot1.rotationPointX = this.Foot2.rotationPointX = this.Foot3.rotationPointX = this.Shin.rotationPointX;
		this.Foot1.render(f5);
		this.Foot2.render(f5);
		this.Foot3.render(f5);
		
		
		this.Thigh.rotateAngleX = this.Thigh2.rotateAngleX = this.Thigh3.rotateAngleX = r.thighdisplayangle[1];
		this.Thigh.rotationPointY = this.Thigh2.rotationPointY = this.Thigh3.rotationPointY = this.Hip.rotationPointY + (float)Math.sin((double)this.Hip.rotateAngleX) * 13.0F;
		this.Thigh.rotationPointZ = this.Thigh2.rotationPointZ = this.Thigh3.rotationPointZ = this.Hip.rotationPointZ - (float)Math.cos((double)this.Hip.rotateAngleX) * (float)Math.cos((double)this.Hip.rotateAngleY) * 13.0F;
		this.Thigh.rotationPointX = this.Thigh2.rotationPointX = this.Thigh3.rotationPointX = this.Hip.rotationPointX - (float)Math.cos((double)this.Hip.rotateAngleX) * (float)Math.sin((double)this.Hip.rotateAngleY) * 13.0F;
		this.Thigh.render(f5);
		this.Thigh2.render(f5);
		this.Thigh3.render(f5);
		this.Shin.rotateAngleX = r.shindisplayangle[1];
		this.Shin.rotationPointY = this.Thigh.rotationPointY + (float)Math.cos((double)this.Thigh.rotateAngleX) * 40.0F;
		this.Shin.rotationPointZ = this.Thigh.rotationPointZ + (float)Math.sin((double)this.Thigh.rotateAngleX) * 40.0F;
		this.Shin.rotationPointX = this.Thigh.rotationPointX;
		this.Shin.render(f5);
		this.Foot1.rotateAngleX = this.Foot2.rotateAngleX = this.Foot3.rotateAngleX = r.shindisplayangle[1];
		this.Foot1.rotationPointY = this.Foot2.rotationPointY = this.Foot3.rotationPointY = this.Shin.rotationPointY;
		this.Foot1.rotationPointZ = this.Foot2.rotationPointZ = this.Foot3.rotationPointZ = this.Shin.rotationPointZ;
		this.Foot1.rotationPointX = this.Foot2.rotationPointX = this.Foot3.rotationPointX = this.Shin.rotationPointX;
		this.Foot1.render(f5);
		this.Foot2.render(f5);
		this.Foot3.render(f5);
		
		float shoulderangle = -r.hipydisplayangle;
		float a1angle, a2angle; a1angle = a2angle = r.thighdisplayangle[1];
		float b1angle, b2angle; b1angle = b2angle = r.thighdisplayangle[0];
		if (e.getAttacking() != 0) {
			shoulderangle = (float)(-(Math.sin((double)(f2 * this.wingspeed * 2.0F)) * Math.PI * (double)0.2F));
			a1angle = (float)((double)((float)(Math.sin((double)(f2 * this.wingspeed * 2.0F)) * Math.PI / 5.0D)) - (Math.PI / 4D));
			a2angle = (float)((double)(-a1angle) + Math.PI);
			a1angle = (float)((double)a1angle + (Math.PI / 5D));
			a2angle = (float)((double)a2angle + (Math.PI / 5D));
			b1angle = (float)((double)((float)(-(Math.sin((double)(f2 * this.wingspeed * 2.0F)) * Math.PI / 5.0D))) - (Math.PI / 4D));
			b2angle = (float)((double)(-b1angle) + Math.PI);
			b1angle = (float)((double)b1angle + (Math.PI / 5D));
			b2angle = (float)((double)b2angle + (Math.PI / 5D));
		}
		
		this.Back3.rotateAngleY = shoulderangle / 2.0F;
		this.Shoulders.rotateAngleY = shoulderangle;
		
		this.Arm1.rotationPointY = this.Arm2.rotationPointY = this.Hip.rotationPointY - 60.0F;
		this.Arm1.rotationPointX = this.Arm2.rotationPointX = this.Hip.rotationPointX + 26.0F;
		this.Arm1.rotationPointZ = this.Arm2.rotationPointZ = this.Shoulders.rotationPointZ - (float)Math.sin((double)this.Shoulders.rotateAngleY) * 26.0F;
		this.Arm1.rotateAngleX = this.Arm2.rotateAngleX = a1angle;
		this.Arm1.render(f5);
		this.Arm2.render(f5);
		this.Arm3.rotateAngleX = this.Knuckles.rotateAngleX = (float)((double)a2angle - 0.19634954084936207);
		this.Arm3.rotationPointY = this.Knuckles.rotationPointY = this.Arm1.rotationPointY + (float)Math.cos((double)this.Arm1.rotateAngleX) * 41.0F;
		this.Arm3.rotationPointZ = this.Knuckles.rotationPointZ = this.Arm1.rotationPointZ + (float)Math.sin((double)this.Arm1.rotateAngleX) * 41.0F;
		this.Arm3.rotationPointX = this.Knuckles.rotationPointX = this.Arm1.rotationPointX;
		this.Arm3.render(f5);
		this.Knuckles.render(f5);
		
		this.Arm1.rotationPointY = this.Arm2.rotationPointY = this.Hip.rotationPointY - 60.0F;
		this.Arm1.rotationPointX = this.Arm2.rotationPointX = this.Hip.rotationPointX - 26.0F;
		this.Arm1.rotationPointZ = this.Arm2.rotationPointZ = this.Shoulders.rotationPointZ + (float)Math.sin((double)this.Shoulders.rotateAngleY) * 26.0F;
		this.Arm1.rotateAngleX = this.Arm2.rotateAngleX = b1angle;
		this.Arm1.render(f5);
		this.Arm2.render(f5);
		this.Arm3.rotateAngleX = this.Knuckles.rotateAngleX = (float)((double)b2angle - 0.19634954084936207);
		this.Arm3.rotationPointY = this.Knuckles.rotationPointY = this.Arm1.rotationPointY + (float)Math.cos((double)this.Arm1.rotateAngleX) * 41.0F;
		this.Arm3.rotationPointZ = this.Knuckles.rotationPointZ = this.Arm1.rotationPointZ + (float)Math.sin((double)this.Arm1.rotateAngleX) * 41.0F;
		this.Arm3.rotationPointX = this.Knuckles.rotationPointX = this.Arm1.rotationPointX;
		this.Arm3.render(f5);
		this.Knuckles.render(f5);
		
		this.Back1.rotationPointY = this.Back2.rotationPointY = this.Back3.rotationPointY = this.Hip.rotationPointY;
		this.Shoulders.rotationPointY = this.Neck.rotationPointY = this.Head.rotationPointY = this.Hip.rotationPointY;
		
		this.Head.rotateAngleY = (float)Math.toRadians((double)f3);
		this.Head.rotateAngleX = (float)Math.toRadians((double)f4) / 3.0F;
		
		this.Back1.render(f5);
		this.Back2.render(f5);
		this.Back3.render(f5);
		this.Shoulders.render(f5);
		this.Neck.render(f5);
		this.Head.render(f5);
		
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
