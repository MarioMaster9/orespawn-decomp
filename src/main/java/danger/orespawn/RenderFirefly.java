package danger.orespawn;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class RenderFirefly extends RenderLiving
{
	protected ModelFirefly model;
	private float scale = 1.0F;

	public RenderFirefly(ModelFirefly par1ModelBase, float par2, float par3) {
		super(par1ModelBase, par2 * par3);
		this.model = (ModelFirefly)this.mainModel;
		this.scale = par3;
	}

	
	public void renderFirefly(Firefly par1EntityFirefly, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(par1EntityFirefly, par2, par4, par6, par8, par9);
	}

	public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderFirefly((Firefly)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderFirefly((Firefly)par1Entity, par2, par4, par6, par8, par9);
	}




	
	protected void preRenderScale(Firefly par1Entity, float par2)
	{
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	
	
	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
	 * entityLiving, partialTickTime
	 */
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
	{
		this.preRenderScale((Firefly)par1EntityLiving, par2);
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		Firefly a = (Firefly)entity;
		return a.getTexture(a);
	}
}
