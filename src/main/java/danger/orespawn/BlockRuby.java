package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;




public class BlockRuby extends Block
{
	public BlockRuby(int par1)
	{
		super(Material.rock);
		this.setHardness(4.0F);
		this.setResistance(4.0F);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setLightLevel(0.4F);
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
		return true;
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (this == OreSpawnMain.MyBlockMobzillaScaleBlock && par5Entity instanceof EntityLivingBase) {
			((EntityLivingBase)par5Entity).addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0));
		}
		
	}
	
	/**
	 * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
	 */
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (this == OreSpawnMain.MyBlockMobzillaScaleBlock && par5Entity instanceof EntityLivingBase) {
			((EntityLivingBase)par5Entity).addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
