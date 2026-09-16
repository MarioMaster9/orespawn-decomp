package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;













public class BlockCrystalLeaves extends BlockLeaves
{
	protected BlockCrystalLeaves(int par1)
	{
		this.setTickRandomly(true);
		
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(Item.getItemFromBlock(this), 1, 0));
	}

	
	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		if (!par1World.isRemote)
		{
			if (par1World.rand.nextInt(100) == 1)
			{
				this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(OreSpawnMain.MyCrystalApple));
			}
			if (par1World.rand.nextInt(50) == 1)
			{
				if (this == OreSpawnMain.MyCrystalLeaves) this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(OreSpawnMain.MyCrystalPlant));
				if (this == OreSpawnMain.MyCrystalLeaves2) this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(OreSpawnMain.MyCrystalPlant2));
				if (this == OreSpawnMain.MyCrystalLeaves3) this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(OreSpawnMain.MyCrystalPlant3));
			}
		}
	}
	

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	
	
	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int var7 = 2;
		int var12, var13, var14;
		Block bid;
		int totaldist = 0;
		int chance = 20;
		
		if (par1World.provider.dimensionId == OreSpawnMain.DimensionID4) {
			chance = 100;
			var7 = 1;
		}

		if (!par1World.isRemote && par1World.checkChunksExist(par2 - var7, par3 - var7, par4 - var7, par2 + var7, par3 + var7, par4 + var7))
		{
			for (var12 = -var7; var12 <= var7; var12++)
			{
				for (var13 = -var7; var13 <= 0; var13++)
				{
					for (var14 = -var7; var14 <= var7; var14++)
					{
						totaldist = Math.abs(var12) + Math.abs(var13) + Math.abs(var14);
						if (totaldist <= 3) {
							bid = par1World.getBlock(par2 + var12, par3 + var13, par4 + var14);
							
							if (bid != null && bid.canSustainLeaves(par1World, par2 + var12, par3 + var13, par4 + var14))
							{
								
								bid = par1World.getBlock(par2, par3 - 1, par4);
								if (bid == Blocks.air)
								{
									if (par1World.rand.nextInt(chance) == 3)
									{
										this.dropBlockAsItemWithChance(par1World, par2, par3 - 1, par4, 0, 0.0F, 0);
									}
								}
								return;
							}
						}
					}
				}
			}
			this.removeLeaves(par1World, par2, par3, par4);
		}
	}

	private void removeLeaves(World par1World, int par2, int par3, int par4)
	{
		this.dropBlockAsItem(par1World, par2, par3, par4, 0, 0);
		par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		if (OreSpawnMain.FastGraphicsLeaves != 0) return true;
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		if (OreSpawnMain.FastGraphicsLeaves != 0) return true;
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		if (OreSpawnMain.FastGraphicsLeaves != 0) return super.getRenderType();
		return 1;
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		Block i1 = par1IBlockAccess.getBlock(par2, par3, par4);
		return (OreSpawnMain.FastGraphicsLeaves != 0 && i1 == this) ? false : true;
	}
	
	
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		return 14540253;
	}

	
	/**
	 * Returns the color this block should be rendered. Used by leaves.
	 */
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1)
	{
		return 14540253;
	}

	
	/**
	 * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
	 * when first determining what to render.
	 */
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return 14540253;
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		return this.blockIcon;
	}

	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}

	
	public String[] func_150125_e()
	{
		return null;
	}
}
