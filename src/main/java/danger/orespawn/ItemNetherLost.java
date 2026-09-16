package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;











public class ItemNetherLost extends Item
{
	public ItemNetherLost(int par1)
	{
		this.maxStackSize = 1;
		this.setMaxDamage(3000);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par1ItemStack.addEnchantment(Enchantment.sharpness, 2);
		
	}
	
	/**
	 * Called each tick while using an item.
	 * @param stack The Item being used
	 * @param player The Player using the item
	 * @param count The amount of time in tick the item has been used for continuously
	 */
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack);
		if (lvl <= 0) {
			stack.addEnchantment(Enchantment.sharpness, 2);
		}
	}

	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
		Block i;
		ItemStack is;
		Item it;
		
		EntityLivingBase e = null;
		EntityPlayer p = null;
		
		this.onUsingTick(stack, (EntityPlayer)null, 0);
		if (par2World == null) return;
		
		if (par3Entity != null) {
			if (par3Entity instanceof EntityLivingBase) {
				e = (EntityLivingBase)par3Entity;
				
				if (e instanceof EntityPlayer) {
					p = (EntityPlayer)e;
					is = p.getCurrentEquippedItem();
					if (is != null) {
						it = is.getItem();
						if (it != null)
						{
							if (it instanceof ItemNetherLost) {
								if (par2World.provider.dimensionId == -1) {
									i = par2World.getBlock((int)p.posX, (int)p.posY - 1, (int)p.posZ);
									if (i == Blocks.netherrack) {
										par2World.setBlock((int)p.posX, (int)p.posY - 1, (int)p.posZ, Blocks.quartz_block);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 3000;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
