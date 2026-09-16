package danger.orespawn;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class RenderButterfly extends RenderLiving
{
	protected ModelButterfly model;
	private float scale = 1.0F;
	private static final ResourceLocation texture = new ResourceLocation("textures/entity/creeper/creeper_armor.png");

	public RenderButterfly(ModelButterfly par1ModelBase, float par2, float par3) {
		super(par1ModelBase, par2 * par3);
		this.model = (ModelButterfly)this.mainModel;
		this.scale = par3;
	}

	
	public void renderButterfly(EntityButterfly par1EntityButterfly, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(par1EntityButterfly, par2, par4, par6, par8, par9);
	}

	public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderButterfly((EntityButterfly)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderButterfly((EntityButterfly)par1Entity, par2, par4, par6, par8, par9);
	}




	
	protected void preRenderScale(EntityButterfly par1Entity, float par2)
	{
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	
	
	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
	 * entityLiving, partialTickTime
	 */
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2)
	{
		this.preRenderScale((EntityButterfly)par1EntityLiving, par2);
	}

	
	
	/**
	 * Queries whether should render the specified pass or not.
	 */
	public int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3)
	{
		int doit = 0;
		if (par1EntityLiving instanceof Mothra) {
			++doit;
		} else if (par1EntityLiving instanceof EntityLunaMoth) {
			EntityLunaMoth e = (EntityLunaMoth)par1EntityLiving;
			if (e.moth_type == 0) ++doit;

		}

		
		
		if (doit != 0) {
			GL11.glDepthMask(true);
			if (par2 == 1)
			{
				float var4 = (float)par1EntityLiving.ticksExisted + par3;
				
				this.bindTexture(texture);
				GL11.glMatrixMode(5890);
				GL11.glLoadIdentity();
				float var5 = var4 * 0.01F;
				float var6 = var4 * 0.01F;
				GL11.glTranslatef(var5, var6, 0.0F);
				this.setRenderPassModel(this.model);
				GL11.glMatrixMode(5888);
				GL11.glEnable(3042);
				float var7 = 0.5F;
				GL11.glColor4f(var7, var7, var7, 1.0F);
				GL11.glDisable(2896);
				GL11.glBlendFunc(1, 1);
				return 1;
			}
			if (par2 == 2)
			{
				GL11.glMatrixMode(5890);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(5888);
				GL11.glEnable(2896);
				GL11.glDisable(3042);
			}
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		return -1;
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		EntityButterfly a = (EntityButterfly)entity;
		return a.getTexture(a);
	}
}
