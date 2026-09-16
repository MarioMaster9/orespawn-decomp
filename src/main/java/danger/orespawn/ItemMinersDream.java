package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;















public class ItemMinersDream extends Item
{
	public ItemMinersDream(int i)
	{
		this.maxStackSize = 16;
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	
	
	
	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer Player, World world, int cposx, int cposy, int cposz, int par7, float par8, float par9, float par10)
	{
		int pposx, pposy, pposz;
		int x, y, z;
		int deltax = 0, deltaz = 0;
		int i, j, k;
		Block bid;
		int dirx = 0, dirz = 0;
		int height = 5, width = 5, length = 64;
		int torches = 5;
		int solid_count = 0;
		
		
		
		
		
		
		
		
		
		if (cposx < 0) dirx = -1;
		if (cposz < 0) dirz = -1;
		pposx = (int)(Player.posX + 0.99 * (double)dirx);
		pposy = (int)Player.posY;
		pposz = (int)(Player.posZ + 0.99 * (double)dirz);
		
		
		
		
		if (cposx - pposx == 0 || cposz - pposz == 0)
		{
			
			x = cposx; y = pposy; z = cposz;
			if (x - pposx < 0) deltax = -1;
			if (x - pposx > 0) deltax = 1;
			if (z - pposz < 0) deltaz = -1;
			if (z - pposz > 0) deltaz = 1;
			if (deltax == 0 && deltaz == 0) return false;
			if (deltax != 0 && deltaz != 0) return false;
			
			Player.worldObj.playSoundAtEntity(Player, "random.explode", 1.0F, 1.5F);
			
			if (world.isRemote)
			{
				
				return true;
			}
			
			
			for (i = 0; i < height; i++)
			{
				
				for (k = 0; k < length; k++)
				{
					solid_count = 0;
					for (j = -width; j <= width; j++)
					{
						bid = world.getBlock(x + k * deltax + j * deltaz, y + i, z + k * deltaz + j * deltax);
						if (bid == Blocks.stone || bid == Blocks.dirt || bid == Blocks.gravel || bid == Blocks.flowing_water || bid == Blocks.water || bid == Blocks.flowing_lava || bid == Blocks.lava || bid == Blocks.netherrack || bid == Blocks.end_stone || bid == OreSpawnMain.CrystalStone)
						{
							
							
							
							
							world.setBlock(x + k * deltax + j * deltaz, y + i, z + k * deltaz + j * deltax, Blocks.air, 0, 2);
						}

						if (i == height - 1)
						{
							bid = world.getBlock(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax);
							if (bid != Blocks.air) ++solid_count;
							if (bid == Blocks.air || bid == Blocks.gravel || bid == Blocks.sand || bid == Blocks.flowing_water || bid == Blocks.water || bid == Blocks.flowing_lava || bid == Blocks.lava)
							{
								
								
								if (world.provider.dimensionId == OreSpawnMain.DimensionID5) {
									world.setBlock(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax, OreSpawnMain.CrystalStone, 0, 2);
								} else {
									world.setBlock(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax, Blocks.cobblestone, 0, 2);
								}
							}
						}
					}
					

					if (i == height - 1 && solid_count == 0)
					{
						
						for (j = -width; j <= width; j++)
						{
							world.setBlock(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax, Blocks.air, 0, 2);
						}
					}
				}
			}

			
			
			for (k = 0; k < length; k += torches)
			{
				bid = world.getBlock(x + k * deltax, y - 1, z + k * deltaz);
				if (bid == Blocks.stone || bid == Blocks.dirt || bid == Blocks.gravel || bid == Blocks.netherrack || bid == Blocks.end_stone || bid == Blocks.bedrock)
				{
					
					if (world.isAirBlock(x + k * deltax, y, z + k * deltaz))
					{
						world.setBlock(x + k * deltax, y, z + k * deltaz, OreSpawnMain.ExtremeTorch, 0, 2);
					}
				}
				if (bid == OreSpawnMain.CrystalStone)
				{
					if (world.isAirBlock(x + k * deltax, y, z + k * deltaz))
					{
						world.setBlock(x + k * deltax, y, z + k * deltaz, OreSpawnMain.CrystalTorch, 0, 2);
					}
				}
			}

			
			if (!Player.capabilities.isCreativeMode)
			{
				--par1ItemStack.stackSize;
			}

			return true;
		}
		return false;
	}

	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
