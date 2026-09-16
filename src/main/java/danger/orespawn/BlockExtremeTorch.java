package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;




public class BlockExtremeTorch extends BlockTorch
{
	public BlockExtremeTorch(int par1)
	{
		this.setCreativeTab(CreativeTabs.tabRedstone);
		
	}
	
	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int var6 = par1World.getBlockMetadata(par2, par3, par4);
		double var7 = (double)((float)par2 + 0.5F);
		double var9 = (double)((float)par3 + 0.7F);
		double var11 = (double)((float)par4 + 0.5F);
		double var13 = 0.213;
		double var15 = 0.271;
		
		if (var6 == 1)
		{
			par1World.spawnParticle("smoke", var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("reddust", var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
		}
		else if (var6 == 2)
		{
			par1World.spawnParticle("smoke", var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("reddust", var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
		}
		else if (var6 == 3)
		{
			par1World.spawnParticle("smoke", var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("reddust", var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
		}
		else if (var6 == 4)
		{
			par1World.spawnParticle("smoke", var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("reddust", var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
		}
		else
		{
			par1World.spawnParticle("smoke", var7, var9, var11, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", var7, var9, var11, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("reddust", var7, var9, var11, 0.0D, 0.0D, 0.0D);
		}

		
		this.onBlockPlacedBy(par1World, par2, par3, par4, null, null);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return super.canPlaceBlockAt(par1World, par2, par3, par4);
	}

	public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
	{
		int x = par2, y = par3, z = par4, tries, unused1, unused2, found = 0;
		int unused3;
		
		if (world.getBlock(x, y - 1, z) == OreSpawnMain.MyEyeOfEnderBlock) {
			for (tries = 0; tries < 100 && found == 0; tries++) {
				if (world.rand.nextInt(2) == 0) {
					x = par2 + 4 + world.rand.nextInt(3) - world.rand.nextInt(3);
				} else {
					x = par2 - 4 + world.rand.nextInt(3) - world.rand.nextInt(3);
				}
				if (world.rand.nextInt(2) == 0) {
					z = par4 + 4 + world.rand.nextInt(3) - world.rand.nextInt(3);
				} else {
					z = par4 - 4 + world.rand.nextInt(3) - world.rand.nextInt(3);
				}
				for (y = par3 - 2; y <= par3 + 2; y++) {
					if (world.getBlock(x, y - 1, z).getMaterial().isSolid()) {
						if (world.getBlock(x, y, z) == Blocks.air) {
							if (world.getBlock(x, y + 1, z) == Blocks.air) {
								found = 1;
								break;
							}
						}
					}
				}
			}

			if (found != 0) {
				if (!world.isRemote) {
					Entity ent = null;
					ent = spawnCreature(world, "Cephadrome", (double)x + 0.5D, (double)y + 0.01, (double)z + 0.5D);
				} else {
					for (int var3 = 0; var3 < 16; var3++)
					{
						world.spawnParticle("smoke", (double)((float)par2 + world.rand.nextFloat() - world.rand.nextFloat()), (double)((float)par3 + world.rand.nextFloat()), (double)((float)par4 + world.rand.nextFloat() - world.rand.nextFloat()), 0.0D, 0.0D, 0.0D);
						world.spawnParticle("explode", (double)((float)par2 + world.rand.nextFloat() - world.rand.nextFloat()), (double)((float)par3 + world.rand.nextFloat()), (double)((float)par4 + world.rand.nextFloat() - world.rand.nextFloat()), 0.0D, 0.0D, 0.0D);
						world.spawnParticle("reddust", (double)((float)par2 + world.rand.nextFloat() - world.rand.nextFloat()), (double)((float)par3 + world.rand.nextFloat()), (double)((float)par4 + world.rand.nextFloat() - world.rand.nextFloat()), 0.0D, 0.0D, 0.0D);
					}
				}
				

				if (par5EntityLiving != null) {
					par5EntityLiving.worldObj.playSoundAtEntity(par5EntityLiving, "random.explode", 1.0F, world.rand.nextFloat() * 0.2F + 0.9F);
				} else {
					world.playSound((double)par2, (double)par3, (double)par4, "random.explode", 1.0F, world.rand.nextFloat() * 0.2F + 0.9F, false);
				}
				world.setBlock(par2, par3, par4, Blocks.air);
			}
		}

		
		
		super.onBlockPlacedBy(world, par2, par3, par4, par5EntityLiving, par6ItemStack);
	}

	
	
	
	
	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6)
	{
		Entity var8 = null;
		
		
		var8 = EntityList.createEntityByName(par1, par0World);
		
		if (var8 != null)
		{
			
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			
			
			par0World.spawnEntityInWorld(var8);
			
			((EntityLiving)var8).playLivingSound();
		}

		return var8;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
