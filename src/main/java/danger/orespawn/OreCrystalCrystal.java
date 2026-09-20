package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;






public class OreCrystalCrystal extends Block
{
	public OreCrystalCrystal(int par1, float lv, float f1, float f2)
	{
		super(Material.rock);
		this.setHardness(f1);
		this.setResistance(f2);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setLightLevel(lv);
		this.setTickRandomly(true);
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.rand.nextInt(20) == 0)
			this.sparkle(par1World, par2, par3, par4);
	}

	private void sparkle(World par1World, int par2, int par3, int par4)
	{
		int which = 0;
		float dx = 0.5F;
		float dz = 0.5F;
		float dy = 0.5F;
		if (this == OreSpawnMain.TigersEye) {
			par1World.spawnParticle("flame", (double)((float)par2 + dx), (double)par3 + (double)dy, (double)((float)par4 + dz), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 4.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 4.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 4.0F));
		} else {
			
			par1World.spawnParticle("fireworksSpark", (double)((float)par2 + dx), (double)par3 + (double)dy, (double)((float)par4 + dz), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 4.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 4.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 4.0F));
		}
		
	}
	
	
	public int getRenderType()
	{
		return 1;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
	 */
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
	{
		if (this == OreSpawnMain.CrystalCrystal && !par1World.isRemote && par1World.rand.nextInt(10) == 1)
		{
			par1World.newExplosion((Entity)null, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), 1.0F, true, par1World.getGameRules().getGameRuleBooleanValue("mobGriefing"));
		}
		
		
		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
		int j1 = 5 + par1World.rand.nextInt(5) + par1World.rand.nextInt(10);
		if (par3 < 40)
			this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		if (this != OreSpawnMain.TigersEye) return 1;
		return par1Random.nextInt(2);
	}

	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
