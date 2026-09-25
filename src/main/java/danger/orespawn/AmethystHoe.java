package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;








public class AmethystHoe extends ItemHoe
{
	public AmethystHoe(int par1, Item.ToolMaterial par2)
	{
		super(par2);
		this.maxStackSize = 1;
		this.setMaxDamage(2000);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	/**
	 * Returns the damage against a given entity.
	 */
	public int getDamageVsEntity(Entity par1Entity)
	{
		return 5;
	}

	public String getMaterialName()
	{
		return "Amethyst";
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
