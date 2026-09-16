package danger.orespawn;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;







public class RenderSpiderDriver extends RenderSpider
{
	private static final ResourceLocation texture = new ResourceLocation("orespawn", "spiderdriver.png");

	
	public RenderSpiderDriver(ModelSpider modelSpider, float par2) {
	}

	
	public void renderSpiderDriver(SpiderDriver par1EntitySpiderDriver, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(par1EntitySpiderDriver, par2, par4, par6, par8, par9);
	}

	public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderSpiderDriver((SpiderDriver)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderSpiderDriver((SpiderDriver)par1Entity, par2, par4, par6, par8, par9);
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return texture;
	}

	protected ResourceLocation getSpiderTextures(EntitySpider par1EntitySpider)
	{
		return texture;
	}
}
