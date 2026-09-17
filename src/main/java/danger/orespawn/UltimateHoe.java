package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;







public class UltimateHoe extends ItemHoe
{
	public UltimateHoe(int par1, Item.ToolMaterial par2)
	{
		super(par2);
		this.maxStackSize = 1;
		this.setMaxDamage(3000);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par1ItemStack.addEnchantment(Enchantment.efficiency, 2);
	}

	/**
	 * Called each tick while using an item.
	 * @param stack The Item being used
	 * @param player The Player using the item
	 * @param count The amount of time in tick the item has been used for continuously
	 */
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack);
		if (lvl <= 0) {
			stack.addEnchantment(Enchantment.efficiency, 2);
		}
	}

	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
		this.onUsingTick(stack, (EntityPlayer)null, 0);
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
		{
			return false;
		}
		else
		{
			Block i1 = par3World.getBlock(par4, par5, par6);
			boolean air = par3World.isAirBlock(par4, par5 + 1, par6);
			
			if (par7 != 0 && air && (i1 == Blocks.grass || i1 == Blocks.dirt))
			{
				Block block = Blocks.farmland;
				par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				
				if (par3World.isRemote)
				{
					return true;
				}
				else
				{
					int i, j, k;
					for (i = -1; i <= 1; i++) {
						for (k = -1; k <= 1; k++) {
							for (j = -1; j <= 1; j++) {
								i1 = par3World.getBlock(par4 + i, par5 + j, par6 + k);
								air = par3World.isAirBlock(par4 + i, par5 + j + 1, par6 + k);
								if (air && (i1 == Blocks.grass || i1 == Blocks.dirt)) {
									par3World.setBlock(par4 + i, par5 + j, par6 + k, block, 7, 2);
								}
							}
						}
					}
	
					par1ItemStack.damageItem(1, par2EntityPlayer);
					return true;
				}
			}
			else
			{
				return false;
			}
		}
	}

	
	public String getMaterialName()
	{
		return "Uranium/Titanium";
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
