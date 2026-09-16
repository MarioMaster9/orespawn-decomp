package danger.orespawn;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;








public class RenderPurplePower extends RenderLiving
{
	protected ModelPurplePower model;
	private float scale = 1.0F;
	private static final ResourceLocation texture = new ResourceLocation("orespawn", "PurplePowertexture.png");
	private static final ResourceLocation texture2 = new ResourceLocation("orespawn", "PurplePowertexture2.png");
	private static final ResourceLocation texture3 = new ResourceLocation("orespawn", "PurplePowertexture3.png");
	private static final ResourceLocation texture4 = new ResourceLocation("orespawn", "PurplePowertexture4.png");
	private static final ResourceLocation texture10 = new ResourceLocation("orespawn", "PurplePowertexture10.png");

	public RenderPurplePower(ModelPurplePower par1ModelBase, float par2, float par3) {
		super(par1ModelBase, par2 * par3);
		this.model = (ModelPurplePower)this.mainModel;
		this.scale = par3;
	}

	
	public void renderPurplePower(PurplePower par1EntityPurplePower, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(par1EntityPurplePower, par2, par4, par6, par8, par9);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderPurplePower((PurplePower)par1Entity, par2, par4, par6, par8, par9);
	}




	protected void preRenderScale(PurplePower par1Entity, float par2)
	{
		float localscale = this.scale;
		if (par1Entity.getPurpleType() != 0) localscale = 0.55F;
		GL11.glScalef(localscale, localscale, localscale);
	}


	
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		PurplePower p = (PurplePower)entity;
		int i = p.getPurpleType();
		if (i == 1) return texture2;
		if (i == 2) return texture3;
		if (i == 3) return texture4;
		if (i == 10) return texture10;
		return texture;
	}
}
