package danger.orespawn;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;












public class ExperienceCatcher extends Item
{
	public ExperienceCatcher(int i)
	{
		this.maxStackSize = 16;
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		par2EntityPlayer.swingItem();
		System.out.printf("x, y,z, 7,8,9,10 == %d, %d, %d - %d, %f, %f, %f\n", x, y, z, par7, par8, par9, par10);
		if (!par2EntityPlayer.worldObj.isRemote) {
			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox((double)x - 0.5D + (double)par8, (double)y, (double)z - 0.5D + (double)par10, (double)x + 0.5D + (double)par8, (double)y + 2.0D, (double)z + 0.5D + (double)par10);
			List var5 = world.getEntitiesWithinAABB(EntityXPOrb.class, bb);
			Iterator var2 = var5.iterator();
			while (var2.hasNext())
			{
				Entity var3 = (Entity)var2.next();
				if (var3 instanceof EntityXPOrb)
				{
					EntityXPOrb ex = (EntityXPOrb)var3;
					
					if (ex.getXpValue() < 3 || world.rand.nextInt(5) == 1) continue;
					
					
					var3.setDead();
					
					EntityItem var4 = null;
					ItemStack is = new ItemStack(Items.experience_bottle, 1, 0);
					var4 = new EntityItem(par2EntityPlayer.worldObj, (double)(par8 + (float)x), (double)y + 1.0D, (double)(par10 + (float)z), is);
					if (var4 != null) par2EntityPlayer.worldObj.spawnEntityInWorld(var4);

					is = new ItemStack(Items.string, 1, 0);
					var4 = new EntityItem(par2EntityPlayer.worldObj, (double)(par8 + (float)x), (double)y + 1.0D, (double)(par10 + (float)z), is);
					if (var4 != null) par2EntityPlayer.worldObj.spawnEntityInWorld(var4);

					is = new ItemStack(Items.stick, 1, 0);
					var4 = new EntityItem(par2EntityPlayer.worldObj, (double)(par8 + (float)x), (double)y + 1.0D, (double)(par10 + (float)z), is);
					if (var4 != null) par2EntityPlayer.worldObj.spawnEntityInWorld(var4);

					if (!par2EntityPlayer.capabilities.isCreativeMode)
					{
						--par1ItemStack.stackSize;
					}
					return true;
				}
			}

			
			EntityItem var4 = null;
			ItemStack is = new ItemStack(OreSpawnMain.MyExperienceCatcher, 1, 0);
			var4 = new EntityItem(par2EntityPlayer.worldObj, (double)(par8 + (float)x), (double)y + 1.0D, (double)(par10 + (float)z), is);
			if (var4 != null) par2EntityPlayer.worldObj.spawnEntityInWorld(var4);

			--par1ItemStack.stackSize;
		}
		
		
		return true;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.swingItem();
		return par1ItemStack;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
