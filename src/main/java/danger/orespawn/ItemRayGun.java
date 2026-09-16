package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;




public class ItemRayGun extends Item
{
	public ItemRayGun(int i)
	{
		this.maxStackSize = 1;
		this.setMaxDamage(50);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() <= 1) {
			return par1ItemStack;
		}
		
		par2World.playSoundAtEntity(par3EntityPlayer, "fireworks.launch", 3.5F, 0.5F);
		
		if (!par2World.isRemote)
		{
			double xzoff = 1.0D;
			double yoff = 1.55;
			LaserBall lb = new LaserBall(par2World, par3EntityPlayer);
			lb.setSpecial();
			lb.setLocationAndAngles(par3EntityPlayer.posX - xzoff * Math.sin(Math.toRadians((double)(par3EntityPlayer.rotationYawHead + 45.0F))), par3EntityPlayer.posY + yoff, par3EntityPlayer.posZ + xzoff * Math.cos(Math.toRadians((double)(par3EntityPlayer.rotationYawHead + 45.0F))), par3EntityPlayer.rotationYawHead, par3EntityPlayer.rotationPitch);
			lb.motionX *= 3.0D;
			lb.motionY *= 3.0D;
			lb.motionZ *= 3.0D;
			par2World.spawnEntityInWorld(lb);
		}

		
		par3EntityPlayer.swingItem();
		
		par3EntityPlayer.addVelocity(Math.cos(Math.toRadians((double)(par3EntityPlayer.rotationYawHead - 90.0F))) * 1.5D, 0.3, Math.sin(Math.toRadians((double)(par3EntityPlayer.rotationYawHead - 90.0F))) * 1.5D);
		
		
		
		
		
		
		
		
		par1ItemStack.damageItem(1, par3EntityPlayer);
		return par1ItemStack;
	}

	
	
	
	
	public String getMaterialName()
	{
		return "Unknown";
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
