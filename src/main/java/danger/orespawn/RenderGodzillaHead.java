package danger.orespawn;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;




public class RenderGodzillaHead extends RenderLiving
{
	public RenderGodzillaHead(ModelGodzilla par1ModelBase, float par2, float par3)
	{
		super(par1ModelBase, par2 * par3);
	}

	
	public void renderGodzillaHead(GodzillaHead par1EntityGodzillaHead, double par2, double par4, double par6, float par8, float par9)
	{
	}

	public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		
	}




	protected void preRenderScale(GodzillaHead par1Entity, float par2)
	{
		
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
	 * entityLiving, partialTickTime
	 */
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
	{
		
	}


	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
