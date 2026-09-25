package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;





public class EmeraldPickaxe extends ItemPickaxe
{
	private int weaponDamage = 10;

	public EmeraldPickaxe(int par1, Item.ToolMaterial par2)
	{
		super(par2);
		this.maxStackSize = 1;
		this.setMaxDamage(1300);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	/**
	 * Called each tick while using an item.
	 * @param stack The Item being used
	 * @param player The Player using the item
	 * @param count The amount of time in tick the item has been used for continuously
	 */
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack);
		if (lvl <= 0) {
			stack.addEnchantment(Enchantment.silkTouch, 1);
		}
	}

	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
		this.onUsingTick(stack, (EntityPlayer)null, 0);
	}

	/**
	 * Returns the damage against a given entity.
	 */
	public int getDamageVsEntity(Entity par1Entity)
	{
		return this.weaponDamage;
	}

	public int getDamageVsEntity()
	{
		return this.weaponDamage;
	}

	public String getMaterialName()
	{
		return "Emerald";
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
