package danger.orespawn;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;



public class ModelRobot5 extends ModelBase
{
	private float wingspeed = 1.0F;
	ModelRenderer lwheel1;
	ModelRenderer lwheel2;
	ModelRenderer rwheel1;
	ModelRenderer rwheel2;
	ModelRenderer axle;
	ModelRenderer drivebox;
	ModelRenderer stand;
	ModelRenderer swivel;
	ModelRenderer barrel1;
	ModelRenderer barrel2;
	ModelRenderer ammobox;

	
	public ModelRobot5(float f1)
	{
		this.wingspeed = f1;
		
		this.textureWidth = 128;
		this.textureHeight = 128;
		
		this.lwheel1 = new ModelRenderer(this, 0, 23);
		this.lwheel1.addBox(0.0F, -4.0F, -4.0F, 2, 8, 8);
		this.lwheel1.setRotationPoint(6.0F, 19.0F, 0.0F);
		this.lwheel1.setTextureSize(128, 128);
		this.lwheel1.mirror = true;
		this.setRotation(this.lwheel1, 0.0F, 0.0F, 0.0F);
		this.lwheel2 = new ModelRenderer(this, 0, 43);
		this.lwheel2.addBox(0.0F, -4.0F, -4.0F, 2, 8, 8);
		this.lwheel2.setRotationPoint(6.0F, 19.0F, 0.0F);
		this.lwheel2.setTextureSize(128, 128);
		this.lwheel2.mirror = true;
		this.setRotation(this.lwheel2, ((float)Math.PI / 4F), 0.0F, 0.0F);
		this.rwheel1 = new ModelRenderer(this, 0, 23);
		this.rwheel1.addBox(0.0F, -4.0F, -4.0F, 2, 8, 8);
		this.rwheel1.setRotationPoint(-8.0F, 19.0F, 0.0F);
		this.rwheel1.setTextureSize(128, 128);
		this.rwheel1.mirror = true;
		this.setRotation(this.rwheel1, 0.0F, 0.0F, 0.0F);
		this.rwheel2 = new ModelRenderer(this, 0, 43);
		this.rwheel2.addBox(0.0F, -4.0F, -4.0F, 2, 8, 8);
		this.rwheel2.setRotationPoint(-8.0F, 19.0F, 0.0F);
		this.rwheel2.setTextureSize(128, 128);
		this.rwheel2.mirror = true;
		this.setRotation(this.rwheel2, ((float)Math.PI / 4F), 0.0F, 0.0F);
		this.axle = new ModelRenderer(this, 42, 0);
		this.axle.addBox(-6.0F, -0.5F, -0.5F, 12, 1, 1);
		this.axle.setRotationPoint(0.0F, 19.0F, 0.0F);
		this.axle.setTextureSize(128, 128);
		this.axle.mirror = true;
		this.setRotation(this.axle, 0.0F, 0.0F, 0.0F);
		this.drivebox = new ModelRenderer(this, 47, 4);
		this.drivebox.addBox(-2.0F, -1.5F, -1.5F, 4, 3, 3);
		this.drivebox.setRotationPoint(0.0F, 19.0F, 0.0F);
		this.drivebox.setTextureSize(128, 128);
		this.drivebox.mirror = true;
		this.setRotation(this.drivebox, 0.0F, 0.0F, 0.0F);
		this.stand = new ModelRenderer(this, 35, 0);
		this.stand.addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1);
		this.stand.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.stand.setTextureSize(128, 128);
		this.stand.mirror = true;
		this.setRotation(this.stand, 0.0F, 0.0F, 0.0F);
		this.swivel = new ModelRenderer(this, 22, 0);
		this.swivel.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
		this.swivel.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.swivel.setTextureSize(128, 128);
		this.swivel.mirror = true;
		this.setRotation(this.swivel, 0.0F, 0.0F, 0.0F);
		this.barrel1 = new ModelRenderer(this, 24, 25);
		this.barrel1.addBox(-1.0F, -2.0F, -10.0F, 2, 2, 13);
		this.barrel1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.barrel1.setTextureSize(128, 128);
		this.barrel1.mirror = true;
		this.setRotation(this.barrel1, 0.0F, 0.0F, 0.0F);
		this.barrel2 = new ModelRenderer(this, 27, 43);
		this.barrel2.addBox(-0.5F, -1.5F, -19.0F, 1, 1, 9);
		this.barrel2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.barrel2.setTextureSize(128, 128);
		this.barrel2.mirror = true;
		this.setRotation(this.barrel2, 0.0F, 0.0F, 0.0F);
		this.ammobox = new ModelRenderer(this, 0, 0);
		this.ammobox.addBox(-2.0F, -2.0F, 3.0F, 4, 3, 5);
		this.ammobox.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ammobox.setTextureSize(128, 128);
		this.ammobox.mirror = true;
		this.setRotation(this.ammobox, 0.0F, 0.0F, 0.0F);
	}

	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		Robot5 e = (Robot5)entity;
		
		float newangle = 0.0F;
		
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		
		if ((double)f1 > 0.1) {
			newangle = f2 * 0.15F % ((float)Math.PI * 2F);
			newangle = Math.abs(newangle);
		} else {
			newangle = 0.0F;
		}

		this.lwheel1.rotateAngleX = newangle;
		this.lwheel2.rotateAngleX = (float)((double)newangle + (Math.PI / 4D));
		this.rwheel1.rotateAngleX = newangle;
		this.rwheel2.rotateAngleX = (float)((double)newangle + (Math.PI / 4D));
		
		this.barrel1.rotateAngleY = this.barrel2.rotateAngleY = this.ammobox.rotateAngleY = (float)Math.toRadians((double)f3 / 2.0D);
		
		
		
		
		this.lwheel1.render(f5);
		this.lwheel2.render(f5);
		this.rwheel1.render(f5);
		this.rwheel2.render(f5);
		this.axle.render(f5);
		this.drivebox.render(f5);
		this.stand.render(f5);
		this.swivel.render(f5);
		this.barrel1.render(f5);
		this.barrel2.render(f5);
		this.ammobox.render(f5);
		
		
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
