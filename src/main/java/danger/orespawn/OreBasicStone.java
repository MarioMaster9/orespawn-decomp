package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class OreBasicStone extends Block {
	public OreBasicStone(int par1, float f1, float f2) {
		super(Material.rock);
		this.setHardness(f1);
		this.setResistance(f2);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setTickRandomly(false);
	}

	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
		if (!par1World.isRemote && this == OreSpawnMain.CrystalRat) {
			int num = 1 + par1World.rand.nextInt(10);

			for (int i = 0; i < num; i++) {
				spawnCreature(par1World, 0, "Rat", (double)par2 + 0.5D + (double)(par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2, (double)par3 + 0.01, (double)par4 + 0.5D + (double)(par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2);
			}
		}

		if (!par1World.isRemote && this == OreSpawnMain.CrystalFairy) {
			int num = 1 + par1World.rand.nextInt(6);

			for (int i = 0; i < num; i++) {
				spawnCreature(par1World, 0, "Fairy", (double)par2 + 0.5D + (double)(par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2, (double)par3 + 0.01, (double)par4 + 0.5D + (double)(par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2);
			}
		}

		if (!par1World.isRemote && this == OreSpawnMain.RedAntTroll) {
			int num = 15 + par1World.rand.nextInt(6);

			for (int i = 0; i < num; i++) {
				spawnCreature(par1World, 0, "Red Ant", (double)par2 + 0.5D + (double)(par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2, (double)par3 + 0.01, (double)par4 + 0.5D + (double)(par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2);
			}
		}

		if (!par1World.isRemote && this == OreSpawnMain.TermiteTroll) {
			int num = 15 + par1World.rand.nextInt(6);

			for (int i = 0; i < num; i++) {
				spawnCreature(par1World, 0, "Termite", (double)par2 + 0.5D + (double)(par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2, (double)par3 + 0.01, (double)par4 + 0.5D + (double)(par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2);
			}
		}

		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
		return true;
	}

	public boolean isOpaqueCube() {
		return OreSpawnMain.current_dimension == OreSpawnMain.DimensionID5;
	}

	public boolean renderAsNormalBlock() {
		return OreSpawnMain.current_dimension == OreSpawnMain.DimensionID5;
	}

	public static Entity spawnCreature(World par0World, int par1, String name, double par2, double par4, double par6) {
		Entity var8 = null;
		if (name == null) {
			var8 = EntityList.createEntityByID(par1, par0World);
		} else {
			var8 = EntityList.createEntityByName(name, par0World);
		}

		if (var8 != null) {
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			par0World.spawnEntityInWorld(var8);
			((EntityLiving)var8).playLivingSound();
		}

		return var8;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
