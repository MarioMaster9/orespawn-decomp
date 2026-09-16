package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Lavafoam extends Block {
	public Lavafoam(int par1) {
		super(Material.rock);
		this.setHardness(5.0F);
		this.setResistance(5.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setTickRandomly(true);
		this.slipperiness = 1.1F;
	}

	public int tickRate() {
		return 10;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (par1World.rand.nextInt(20) == 0) {
			this.sparkle(par1World, par2, par3, par4);
		}

	}

	private void sparkle(World par1World, int par2, int par3, int par4) {
		Random var5 = par1World.rand;
		double var6 = (double)0.0625F;

		for (int var8 = 0; var8 < 6; ++var8) {
			double var9 = (double)((float)par2 + var5.nextFloat());
			double var11 = (double)((float)par3 + var5.nextFloat());
			double var13 = (double)((float)par4 + var5.nextFloat());
			if (var8 == 0 && !par1World.getBlock(par2, par3 + 1, par4).isOpaqueCube()) {
				var11 = (double)(par3 + 1) + var6;
			}

			if (var8 == 1 && !par1World.getBlock(par2, par3 - 1, par4).isOpaqueCube()) {
				var11 = (double)(par3 + 0) - var6;
			}

			if (var8 == 2 && !par1World.getBlock(par2, par3, par4 + 1).isOpaqueCube()) {
				var13 = (double)(par4 + 1) + var6;
			}

			if (var8 == 3 && !par1World.getBlock(par2, par3, par4 - 1).isOpaqueCube()) {
				var13 = (double)(par4 + 0) - var6;
			}

			if (var8 == 4 && !par1World.getBlock(par2 + 1, par3, par4).isOpaqueCube()) {
				var9 = (double)(par2 + 1) + var6;
			}

			if (var8 == 5 && !par1World.getBlock(par2 - 1, par3, par4).isOpaqueCube()) {
				var9 = (double)(par2 + 0) - var6;
			}

			if (var9 < (double)par2 || var9 > (double)(par2 + 1) || var11 < 0.0D || var11 > (double)(par3 + 1) || var13 < (double)par4 || var13 > (double)(par4 + 1)) {
				int which = par1World.rand.nextInt(10);
				if (which == 1) {
					par1World.spawnParticle("smoke", var9, var11, var13, 0.0D, 0.0D, 0.0D);
				}

				if (which == 2) {
					par1World.spawnParticle("reddust", var9, var11, var13, 0.0D, 0.0D, 0.0D);
				}
			}
		}

	}

	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		double pi = 3.14159;
		double pi2 = pi / 2.0D;
		double pi4 = pi / 4.0D;
		super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
		if (par5Entity != null) {
			if (par5Entity instanceof EntityLivingBase) {
				double d = Math.atan2(par5Entity.posX - (double)((float)par2 + 0.5F), par5Entity.posZ - (double)((float)par4 + 0.5F));
				if (d < 0.0D) {
					d += pi * 2.0D;
				}

				if (d > pi2 - pi4 && d < pi2 + pi4) {
					par5Entity.motionX = (double)0.45F;
					par5Entity.motionZ *= (double)1.35F;
				} else if (d > pi - pi4 && d < pi + pi4) {
					par5Entity.motionZ = (double)-0.45F;
					par5Entity.motionX *= (double)1.35F;
				} else if (d > pi + pi2 - pi4 && d < pi + pi2 + pi4) {
					par5Entity.motionX = (double)-0.45F;
					par5Entity.motionZ *= (double)1.35F;
				} else {
					par5Entity.motionZ = (double)0.45F;
					par5Entity.motionX *= (double)1.35F;
				}

				d = Math.sqrt(par5Entity.motionZ * par5Entity.motionZ + par5Entity.motionX * par5Entity.motionX);
				if (d > 1.0D) {
					par5Entity.attackEntityFrom(DamageSource.fall, (float)d);
				}

			}
		}
	}

	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
		int j1 = 5 + par1World.rand.nextInt(5) + par1World.rand.nextInt(5);
		if (par1World.provider.dimensionId == -1) {
			this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
		}

	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		float f = 0.0125F;
		return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)(par3 + 1)), (double)((float)(par4 + 1) - f));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
