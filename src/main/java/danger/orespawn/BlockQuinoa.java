package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;








public class BlockQuinoa extends BlockReed
{
	private int myMaxHeight = 0;

	
	protected BlockQuinoa(int par1)
	{
		float var3 = 0.375F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 1.0F, 0.5F + var3);
		this.setTickRandomly(true);
	}

	
	
	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		Block bid = par1World.getBlock(par2, par3 - 1, par4);
		if (bid == Blocks.air) return false;
		if (bid == OreSpawnMain.MyQuinoaPlant1 || bid == OreSpawnMain.MyQuinoaPlant2 || bid == OreSpawnMain.MyQuinoaPlant3 || bid == OreSpawnMain.MyQuinoaPlant4 || bid == Blocks.grass || bid == Blocks.dirt || bid == Blocks.farmland || bid == OreSpawnMain.CrystalGrass)
		{
			
			
			
			return true;
		}
		return false;
	}

	
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		Block bid;
		int var6;
		int unused1;
		int Height = 1;
		int dontGrow = 0;
		
		if (par1World.isRemote) return;
		
		if (this != OreSpawnMain.MyQuinoaPlant1 && this != OreSpawnMain.MyQuinoaPlant3) return;
		
		int var7 = par1World.getBlockMetadata(par2, par3, par4);
		this.myMaxHeight = var7 >> 8;
		var7 &= 255;
		
		if (this.myMaxHeight == 0) this.myMaxHeight = 2 + OreSpawnMain.OreSpawnRand.nextInt(3);
		bid = par1World.getBlock(par2, par3 + 1, par4);
		
		if (bid == Blocks.air)
		{
			for (var6 = 1; var6 < 10; var6++)
			{
				bid = par1World.getBlock(par2, par3 - var6, par4);
				if (bid != OreSpawnMain.MyQuinoaPlant1 && bid != OreSpawnMain.MyQuinoaPlant2 && bid != OreSpawnMain.MyQuinoaPlant3 && bid != OreSpawnMain.MyQuinoaPlant4)
				{
					break;
				}
				Height++;
				if(bid == OreSpawnMain.MyQuinoaPlant3 || bid == OreSpawnMain.MyQuinoaPlant4)
				{
					dontGrow = 1;
				}
			}

			if (dontGrow != 0)
			{
				
				this.myMaxHeight = Height;
			}

			
			
			
			if (var7 >= 5 - this.myMaxHeight / 3)
			{
				if (Height < this.myMaxHeight)
				{
					
					par1World.setBlock(par2, par3 + 1, par4, OreSpawnMain.MyQuinoaPlant1, this.myMaxHeight << 8, 2);
					par1World.setBlock(par2, par3, par4, OreSpawnMain.MyQuinoaPlant2, this.myMaxHeight << 8, 2);
				} else {
					
					bid = par1World.getBlock(par2, par3, par4);
					if (bid == OreSpawnMain.MyQuinoaPlant1)
					{
						par1World.setBlock(par2, par3, par4, OreSpawnMain.MyQuinoaPlant3, this.myMaxHeight << 8, 2);
					}
					else if (bid == OreSpawnMain.MyQuinoaPlant3)
					{
						par1World.setBlock(par2, par3, par4, OreSpawnMain.MyQuinoaPlant4, this.myMaxHeight << 8, 2);
					}

					bid = par1World.getBlock(par2, par3, par4);
					par1World.setBlock(par2, par3, par4, bid, this.myMaxHeight << 8, 2);
				}
			}
			else
			{
				bid = par1World.getBlock(par2, par3, par4);
				par1World.setBlock(par2, par3, par4, bid, this.myMaxHeight << 8 | var7 + 1, 2);
			}
		}
	}

	
	
	
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return OreSpawnMain.MyQuinoa;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		if (this == OreSpawnMain.MyQuinoaPlant4)
		{
			return 3 + par1Random.nextInt(3);
		}
		return 0;
	}

	
	
	
	public Item itemPicked(World par1World, int par2, int par3, int par4)
	{
		return OreSpawnMain.MyQuinoa;
	}

	
	
	
	protected Item getSeedItem()
	{
		return OreSpawnMain.MyQuinoa;
	}

	
	
	
	protected Item getCropItem()
	{
		return OreSpawnMain.MyQuinoa;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
