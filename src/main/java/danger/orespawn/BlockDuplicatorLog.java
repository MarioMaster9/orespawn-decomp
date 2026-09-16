package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;








public class BlockDuplicatorLog extends Block
{
	protected BlockDuplicatorLog(int par1)
	{
		super(Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		this.setTickRandomly(true);
	}

	/**
	 * How many world ticks before ticking
	 */
	public int tickRate()
	{
		return 1;
	}

	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.isRemote) return;


		if (OreSpawnMain.enableduplicatortree != 0) {
			OreSpawnMain.OreSpawnTrees.DuplicatorTree(par1World, par2, par3, par4);
		}
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(Item.getItemFromBlock(this), 1, 0));
	}

	/**
	 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
	 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
	 */
	protected ItemStack createStackedBlock(int par1)
	{
		return new ItemStack(this, 1, 0);
	}

	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	
	public boolean isWood(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	
	
	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
