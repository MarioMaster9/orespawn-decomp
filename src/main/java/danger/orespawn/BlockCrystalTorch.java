package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;











public class BlockCrystalTorch extends BlockTorch
{
	public BlockCrystalTorch(int par1)
	{
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
	}
	
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.rand.nextInt(4) != 1) return;
		
		int var6 = par1World.getBlockMetadata(par2, par3, par4);
		double var7 = (double)((float)par2 + 0.5F);
		double var9 = (double)((float)par3 + 0.7F);
		double var11 = (double)((float)par4 + 0.5F);
		double var13 = 0.213;
		double var15 = 0.271;
		
		if (var6 == 1)
		{
			par1World.spawnParticle("fireworksSpark", var7 - var15, var9 + var13, var11, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F), (double)(par1World.rand.nextFloat() / 8.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F));
			
			par1World.spawnParticle("flame", var7 - var15, var9 + var13, var11, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F), (double)(par1World.rand.nextFloat() / 10.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F));
		
		}
		else if (var6 == 2)
		{
			par1World.spawnParticle("fireworksSpark", var7 + var15, var9 + var13, var11, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F), (double)(par1World.rand.nextFloat() / 8.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F));
			
			par1World.spawnParticle("flame", var7 + var15, var9 + var13, var11, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F), (double)(par1World.rand.nextFloat() / 10.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F));
		
		}
		else if (var6 == 3)
		{
			par1World.spawnParticle("fireworksSpark", var7, var9 + var13, var11 - var15, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F), (double)(par1World.rand.nextFloat() / 8.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F));
			
			par1World.spawnParticle("flame", var7, var9 + var13, var11 - var15, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F), (double)(par1World.rand.nextFloat() / 10.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F));
		
		}
		else if (var6 == 4)
		{
			par1World.spawnParticle("fireworksSpark", var7, var9 + var13, var11 + var15, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F), (double)(par1World.rand.nextFloat() / 8.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F));
			
			par1World.spawnParticle("flame", var7, var9 + var13, var11 + var15, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F), (double)(par1World.rand.nextFloat() / 10.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F));
		
		}
		else
		{
			par1World.spawnParticle("fireworksSpark", var7, var9, var11, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F), (double)(par1World.rand.nextFloat() / 8.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 8.0F));
			
			par1World.spawnParticle("flame", var7, var9, var11, (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F), (double)(par1World.rand.nextFloat() / 10.0F), (double)((par1World.rand.nextFloat() - par1World.rand.nextFloat()) / 60.0F));
		
		}
	}

	private boolean isCrystalBlock(World par1World, int par2, int par3, int par4)
	{
		Block l = par1World.getBlock(par2, par3, par4);
		if (l == OreSpawnMain.CrystalStone) return true;
		if (l == OreSpawnMain.CrystalGrass) return true;
		if (l == OreSpawnMain.MyCrystalTreeLog) return true;
		if (l == OreSpawnMain.CrystalPlanksBlock) return true;
		return false;
	}

	private boolean isItSolidOnSide(World par1World, int par2, int par3, int par4, ForgeDirection dir, boolean tf)
	{
		if (this.isCrystalBlock(par1World, par2, par3, par4)) return true;
		return par1World.isSideSolid(par2, par3, par4, dir, tf);
	}

	
	
	
	private boolean canPlaceTorchOn(World par1World, int par2, int par3, int par4)
	{
		Block l = par1World.getBlock(par2, par3, par4);
		if (this.isCrystalBlock(par1World, par2, par3, par4)) return true;
		
		if (World.doesBlockHaveSolidTopSurface(par1World, par2, par3, par4))
		{
			return true;
		}
		else
		{
			return l != null && l.canPlaceTorchOnTop(par1World, par2, par3, par4);
		}
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return this.isItSolidOnSide(par1World, par2 - 1, par3, par4, ForgeDirection.EAST, true) || this.isItSolidOnSide(par1World, par2 + 1, par3, par4, ForgeDirection.WEST, true) || this.isItSolidOnSide(par1World, par2, par3, par4 - 1, ForgeDirection.SOUTH, true) || this.isItSolidOnSide(par1World, par2, par3, par4 + 1, ForgeDirection.NORTH, true) || this.canPlaceTorchOn(par1World, par2, par3 - 1, par4);
	}

	
	
	
	
	/**
	 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
	 */
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		int j1 = par9;
		
		if (par5 == 1 && this.canPlaceTorchOn(par1World, par2, par3 - 1, par4))
		{
			j1 = 5;
		}

		if (par5 == 2 && (par1World.isSideSolid(par2, par3, par4 + 1, ForgeDirection.NORTH, true) || this.isCrystalBlock(par1World, par2, par3, par4 + 1)))
		{
			j1 = 4;
		}

		if (par5 == 3 && (par1World.isSideSolid(par2, par3, par4 - 1, ForgeDirection.SOUTH, true) || this.isCrystalBlock(par1World, par2, par3, par4 - 1)))
		{
			j1 = 3;
		}

		if (par5 == 4 && (par1World.isSideSolid(par2 + 1, par3, par4, ForgeDirection.WEST, true) || this.isCrystalBlock(par1World, par2 + 1, par3, par4)))
		{
			j1 = 2;
		}

		if (par5 == 5 && (par1World.isSideSolid(par2 - 1, par3, par4, ForgeDirection.EAST, true) || this.isCrystalBlock(par1World, par2 - 1, par3, par4)))
		{
			j1 = 1;
		}

		return j1;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
