package danger.orespawn;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;


public class ModelCassowary extends ModelBase
{
	private float wingspeed = 1.0F;
	ModelRenderer tail;
	ModelRenderer body;
	ModelRenderer neck1;
	ModelRenderer neck;
	ModelRenderer head;
	ModelRenderer beak;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer crest;
	ModelRenderer foot1;
	ModelRenderer foot2;
	ModelRenderer gobbler;

	public ModelCassowary(float f1)
	{
		this.wingspeed = f1;
		
		this.textureWidth = 64;
		this.textureHeight = 32;
		
		this.tail = new ModelRenderer(this, 38, 16);
		this.tail.addBox(-3.0F, 0.0F, 0.0F, 6, 9, 7);
		this.tail.setRotationPoint(0.0F, 8.0F, 1.0F);
		this.tail.setTextureSize(64, 32);
		this.tail.mirror = true;
		this.setRotation(this.tail, 0.8922867F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 13);
		this.body.addBox(-4.0F, 0.0F, 0.0F, 8, 10, 9);
		this.body.setRotationPoint(0.0F, 5.0F, -3.0F);
		this.body.setTextureSize(64, 32);
		this.body.mirror = true;
		this.setRotation(this.body, 0.3346075F, 0.0F, 0.0F);
		this.neck1 = new ModelRenderer(this, 48, 0);
		this.neck1.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 4);
		this.neck1.setRotationPoint(0.0F, 4.0F, -1.0F);
		this.neck1.setTextureSize(64, 32);
		this.neck1.mirror = true;
		this.setRotation(this.neck1, -1.189716F, 0.0F, 0.0F);
		this.neck = new ModelRenderer(this, 38, 0);
		this.neck.addBox(-1.0F, 0.0F, 0.0F, 2, 7, 2);
		this.neck.setRotationPoint(0.0F, 8.0F, -3.0F);
		this.neck.setTextureSize(64, 32);
		this.neck.mirror = true;
		this.setRotation(this.neck, -2.806985F, 0.0F, 0.0F);
		this.head = new ModelRenderer(this, 24, 0);
		this.head.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 4);
		this.head.setRotationPoint(0.0F, 2.0F, -6.0F);
		this.head.setTextureSize(64, 32);
		this.head.mirror = true;
		this.setRotation(this.head, 0.0371786F, 0.0F, 0.0F);
		this.beak = new ModelRenderer(this, 28, 7);
		this.beak.addBox(-0.5F, 0.0F, 3.0F, 1, 1, 3);
		this.beak.setRotationPoint(0.0F, 2.0F, -6.0F);
		this.beak.setTextureSize(64, 32);
		this.beak.mirror = true;
		this.setRotation(this.beak, -3.104414F, 0.0F, 0.0F);
		this.leg1 = new ModelRenderer(this, 0, 0);
		this.leg1.addBox(-0.5F, 0.0F, -1.0F, 1, 11, 2);
		this.leg1.setRotationPoint(3.0F, 12.0F, 3.0F);
		this.leg1.setTextureSize(64, 32);
		this.leg1.mirror = true;
		this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
		this.leg2 = new ModelRenderer(this, 0, 0);
		this.leg2.addBox(-0.5F, 0.0F, -1.0F, 1, 11, 2);
		this.leg2.setRotationPoint(-3.0F, 12.0F, 3.0F);
		this.leg2.setTextureSize(64, 32);
		this.leg2.mirror = true;
		this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
		this.crest = new ModelRenderer(this, 10, 0);
		this.crest.addBox(-0.5F, -4.0F, 1.0F, 1, 4, 5);
		this.crest.setRotationPoint(0.0F, 2.0F, -6.0F);
		this.crest.setTextureSize(64, 32);
		this.crest.mirror = true;
		this.setRotation(this.crest, 1.710216F, 0.0F, 0.0F);
		this.foot1 = new ModelRenderer(this, 47, 10);
		this.foot1.addBox(-1.033333F, 11.0F, -2.0F, 2, 1, 3);
		this.foot1.setRotationPoint(-3.0F, 12.0F, 3.0F);
		this.foot1.setTextureSize(64, 32);
		this.foot1.mirror = true;
		this.setRotation(this.foot1, 0.0F, 0.0F, 0.0F);
		this.foot2 = new ModelRenderer(this, 47, 10);
		this.foot2.addBox(-1.0F, 11.0F, -2.0F, 2, 1, 3);
		this.foot2.setRotationPoint(3.0F, 12.0F, 3.0F);
		this.foot2.setTextureSize(64, 32);
		this.foot2.mirror = true;
		this.setRotation(this.foot2, 0.0F, 0.0F, 0.0F);
		this.gobbler = new ModelRenderer(this, 38, 10);
		this.gobbler.addBox(-0.5F, -1.0F, -2.5F, 1, 5, 1);
		this.gobbler.setRotationPoint(0.0F, 8.0F, -3.0F);
		this.gobbler.setTextureSize(64, 32);
		this.gobbler.mirror = true;
		this.setRotation(this.gobbler, 0.0F, 0.0F, 0.0F);
	}

	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		Cassowary e = (Cassowary)entity;
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		float newangle = 0.0F;
		float newangle2 = 0.0F;
		
		if ((double)f1 > 0.1) {
			newangle = MathHelper.cos(f2 * 1.3F * this.wingspeed) * (float)Math.PI * 0.15F * f1;
			newangle2 = MathHelper.cos(f2 * 2.6F * this.wingspeed) * (float)Math.PI * 0.1F * f1;
		} else {
			newangle = newangle2 = 0.0F;
		}

		this.leg1.rotateAngleX = this.foot2.rotateAngleX = newangle;
		this.leg2.rotateAngleX = this.foot1.rotateAngleX = -newangle;
		
		this.neck.rotateAngleX = -2.827F + newangle2;
		this.gobbler.rotateAngleX = newangle2;
		
		this.head.rotationPointZ = this.crest.rotationPointZ = this.beak.rotationPointZ = this.neck.rotationPointZ + MathHelper.sin(this.neck.rotateAngleX) * 7.0F;
		this.head.rotationPointY = this.crest.rotationPointY = this.beak.rotationPointY = this.neck.rotationPointY + MathHelper.cos(this.neck.rotateAngleX) * 7.0F;
		
		this.tail.render(f5);
		this.body.render(f5);
		this.neck1.render(f5);
		this.neck.render(f5);
		this.head.render(f5);
		this.beak.render(f5);
		this.leg1.render(f5);
		this.leg2.render(f5);
		this.crest.render(f5);
		this.foot1.render(f5);
		this.foot2.render(f5);
		this.gobbler.render(f5);
		
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
