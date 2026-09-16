package danger.orespawn;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;




public class ModelWormMedium extends ModelBase
{
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer tail;
	ModelRenderer tooth1;
	ModelRenderer tooth2;
	ModelRenderer tooth3;
	ModelRenderer tooth4;
	ModelRenderer head2;

	public ModelWormMedium()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		
		this.head = new ModelRenderer(this, 24, 0);
		this.head.addBox(-1.5F, -12.0F, -1.5F, 3, 12, 3);
		this.head.setRotationPoint(0.0F, 1.0F, 0.0F);
		this.head.setTextureSize(64, 32);
		this.head.mirror = true;
		this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 37, 0);
		this.body.addBox(-1.5F, -12.0F, -1.5F, 3, 12, 3);
		this.body.setRotationPoint(0.0F, 13.0F, 0.0F);
		this.body.setTextureSize(64, 32);
		this.body.mirror = true;
		this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
		this.tail = new ModelRenderer(this, 50, 0);
		this.tail.addBox(-1.5F, -12.0F, -1.5F, 3, 12, 3);
		this.tail.setRotationPoint(0.0F, 25.0F, 0.0F);
		this.tail.setTextureSize(64, 32);
		this.tail.mirror = true;
		this.setRotation(this.tail, 0.0F, 0.0F, 0.0F);
		this.tooth1 = new ModelRenderer(this, 15, 0);
		this.tooth1.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1);
		this.tooth1.setRotationPoint(1.0F, -11.0F, 0.0F);
		this.tooth1.setTextureSize(64, 32);
		this.tooth1.mirror = true;
		this.setRotation(this.tooth1, 0.0F, 0.0F, 0.0F);
		this.tooth2 = new ModelRenderer(this, 5, 0);
		this.tooth2.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1);
		this.tooth2.setRotationPoint(-1.0F, -11.0F, 0.0F);
		this.tooth2.setTextureSize(64, 32);
		this.tooth2.mirror = true;
		this.setRotation(this.tooth2, 0.0F, 0.0F, 0.0F);
		this.tooth3 = new ModelRenderer(this, 0, 0);
		this.tooth3.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1);
		this.tooth3.setRotationPoint(0.0F, -11.0F, 1.0F);
		this.tooth3.setTextureSize(64, 32);
		this.tooth3.mirror = true;
		this.setRotation(this.tooth3, 0.0F, 0.0F, 0.0F);
		this.tooth4 = new ModelRenderer(this, 10, 0);
		this.tooth4.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1);
		this.tooth4.setRotationPoint(0.0F, -11.0F, -1.0F);
		this.tooth4.setTextureSize(64, 32);
		this.tooth4.mirror = true;
		this.setRotation(this.tooth4, 0.0F, 0.0F, 0.0F);
		this.head2 = new ModelRenderer(this, 0, 6);
		this.head2.addBox(-2.0F, -8.0F, -2.0F, 4, 8, 4);
		this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head2.setTextureSize(64, 32);
		this.head2.mirror = true;
		this.setRotation(this.head2, 0.0F, 0.0F, 0.0F);
	}

	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		float newangle = MathHelper.cos(f2 * 0.45F) * (float)Math.PI * 0.1F;
		this.tail.rotateAngleX = newangle;
		float d1 = (float)(Math.sin((double)newangle) * 12.0D);
		float d2 = (float)(Math.cos((double)newangle) * 12.0D);
		this.body.rotationPointZ = this.tail.rotationPointZ - d1;
		newangle = MathHelper.cos(f2 * 0.25F) * (float)Math.PI * 0.08F;
		this.tail.rotateAngleZ = newangle;
		float d3 = (float)(Math.cos((double)newangle) * (double)d2);
		float d4 = (float)(Math.sin((double)newangle) * (double)d2);
		this.body.rotationPointX = this.tail.rotationPointX + d4;
		this.body.rotationPointY = (float)((double)this.tail.rotationPointY - 12.0D + (12.0D - (double)d3));
		
		newangle = MathHelper.cos(f2 * 0.35F) * (float)Math.PI * 0.1F;
		this.body.rotateAngleX = newangle;
		d1 = (float)(Math.sin((double)newangle) * 12.0D);
		d2 = (float)(Math.cos((double)newangle) * 12.0D);
		this.head2.rotationPointZ = this.head.rotationPointZ = this.body.rotationPointZ - d1;
		newangle = MathHelper.cos(f2 * 0.15F) * (float)Math.PI * 0.07F;
		this.body.rotateAngleZ = newangle;
		d3 = (float)(Math.cos((double)newangle) * (double)d2);
		d4 = (float)(Math.sin((double)newangle) * (double)d2);
		this.head2.rotationPointX = this.head.rotationPointX = this.body.rotationPointX + d4;
		this.head2.rotationPointY = this.head.rotationPointY = (float)((double)this.body.rotationPointY - 12.0D + (12.0D - (double)d3));
		
		this.head2.rotateAngleX = this.head.rotateAngleX = 0.62F + MathHelper.cos(f2 * 0.55F) * (float)Math.PI * 0.15F;
		this.head2.rotateAngleZ = this.head.rotateAngleZ = MathHelper.cos(f2 * 0.25F) * (float)Math.PI * 0.05F;
		
		newangle = this.head.rotateAngleX;
		this.tooth1.rotateAngleX = this.tooth2.rotateAngleX = this.tooth3.rotateAngleX = this.tooth4.rotateAngleX = newangle;
		d1 = (float)(Math.sin((double)newangle) * 12.0D);
		d2 = (float)(Math.cos((double)newangle) * 12.0D);
		this.tooth1.rotationPointZ = this.tooth2.rotationPointZ = this.tooth3.rotationPointZ = this.tooth4.rotationPointZ = this.head.rotationPointZ - d1;
		newangle = this.head.rotateAngleZ;
		this.tooth1.rotateAngleZ = this.tooth2.rotateAngleZ = this.tooth3.rotateAngleZ = this.tooth4.rotateAngleZ = newangle;
		d3 = (float)(Math.cos((double)newangle) * (double)d2);
		d4 = (float)(Math.sin((double)newangle) * (double)d2);
		this.tooth1.rotationPointX = this.tooth2.rotationPointX = this.tooth3.rotationPointX = this.tooth4.rotationPointX = this.head.rotationPointX + d4;
		this.tooth1.rotationPointY = this.tooth2.rotationPointY = this.tooth3.rotationPointY = this.tooth4.rotationPointY = (float)((double)this.head.rotationPointY - 12.0D + (12.0D - (double)d3));
		
		++this.tooth1.rotationPointZ;
		--this.tooth2.rotationPointZ;
		this.tooth1.rotateAngleX = this.tooth1.rotateAngleX - 0.4F - MathHelper.cos(f2 * 0.55F) * (float)Math.PI * 0.15F;
		this.tooth2.rotateAngleX = this.tooth2.rotateAngleX + 0.4F + MathHelper.cos(f2 * 0.55F) * (float)Math.PI * 0.15F;
		
		++this.tooth3.rotationPointX;
		--this.tooth4.rotationPointX;
		this.tooth3.rotateAngleZ = this.tooth3.rotateAngleZ + 0.4F + MathHelper.cos(f2 * 0.55F) * (float)Math.PI * 0.15F;
		this.tooth4.rotateAngleZ = this.tooth4.rotateAngleZ - 0.4F - MathHelper.cos(f2 * 0.55F) * (float)Math.PI * 0.15F;
		
		
		this.head.render(f5);
		this.body.render(f5);
		this.tail.render(f5);
		this.tooth1.render(f5);
		this.tooth2.render(f5);
		this.tooth3.render(f5);
		this.tooth4.render(f5);
		this.head2.render(f5);
		
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
