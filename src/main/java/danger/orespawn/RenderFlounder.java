package danger.orespawn;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class RenderFlounder extends RenderLiving
{
	protected ModelFlounder model;
	private float scale = 1.0F;
	private static final ResourceLocation texture = new ResourceLocation("orespawn", "Floundertexture.png");

	public RenderFlounder(ModelFlounder par1ModelBase, float par2, float par3) {
		super(par1ModelBase, par2 * par3);
		this.model = (ModelFlounder)this.mainModel;
		this.scale = par3;
	}

	
	public void renderFlounder(Flounder par1EntityFlounder, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(par1EntityFlounder, par2, par4, par6, par8, par9);
	}

	public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderFlounder((Flounder)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderFlounder((Flounder)par1Entity, par2, par4, par6, par8, par9);
	}




	protected void preRenderScale(Flounder par1Entity, float par2)
	{
		if (par1Entity != null) {
			if (par1Entity.isChild()) {
				GL11.glScalef(this.scale / 2.0F, this.scale / 2.0F, this.scale / 2.0F);
				return;
			}
		}
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	
	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
	 * entityLiving, partialTickTime
	 */
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
	{
		this.preRenderScale((Flounder)par1EntityLiving, par2);
	}


	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}
}
