package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;














public class ItemSifter extends Item
{
	public ItemSifter(int i)
	{
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setMaxDamage(600);
	}

	private void dropItemRand(Item index, int par1, World world, int x, int y, int z)
	{
		EntityItem var3 = new EntityItem(world, (double)(x + OreSpawnMain.OreSpawnRand.nextInt(2) - OreSpawnMain.OreSpawnRand.nextInt(2)) + 0.5D, (double)y + 1.1, (double)(z + OreSpawnMain.OreSpawnRand.nextInt(2) - OreSpawnMain.OreSpawnRand.nextInt(2)) + 0.5D, new ItemStack(index, par1, 0));
		
		world.spawnEntityInWorld(var3);
	}

	
	
	
	
	
	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par3World.isRemote)
		{
			return true;
		}
		Block bid = par3World.getBlock(par4, par5, par6);
		Block bid2 = par3World.getBlock(par4, par5 + 1, par6);
		if (bid2 == Blocks.flowing_water) bid = Blocks.water;
		if (bid2 == Blocks.water) bid = Blocks.water;

		if (bid == Blocks.water) {
			int i = par3World.rand.nextInt(160);
			switch (i) {
				case 0:
					this.dropItemRand(Items.fish, 1, par3World, par4, par5, par6);
					break;
				case 1:
					this.dropItemRand(OreSpawnMain.MyGreenFish, 1, par3World, par4, par5, par6);
					break;
				case 2:
					this.dropItemRand(OreSpawnMain.MyBlueFish, 1, par3World, par4, par5, par6);
					break;
				case 3:
					this.dropItemRand(OreSpawnMain.MyPinkFish, 1, par3World, par4, par5, par6);
					break;
				case 4:
					this.dropItemRand(OreSpawnMain.MyRockFish, 1, par3World, par4, par5, par6);
					break;
				case 5:
					this.dropItemRand(OreSpawnMain.MyWoodFish, 1, par3World, par4, par5, par6);
					break;
				case 6:
					this.dropItemRand(OreSpawnMain.MyGreyFish, 1, par3World, par4, par5, par6);
					break;
				case 7:
					this.dropItemRand(Items.glass_bottle, 1, par3World, par4, par5, par6);
					break;
				case 8:
					this.dropItemRand(Items.iron_ingot, 1, par3World, par4, par5, par6);
					break;
				case 9:
					this.dropItemRand(Items.gold_nugget, 1, par3World, par4, par5, par6);
					break;
				case 10:
					this.dropItemRand(OreSpawnMain.MyItemShoes, 1, par3World, par4, par5, par6);
					break;
				case 11:
					this.dropItemRand(OreSpawnMain.MyItemShoes_1, 1, par3World, par4, par5, par6);
					break;
				case 12:
					this.dropItemRand(OreSpawnMain.MyItemShoes_2, 1, par3World, par4, par5, par6);
					break;
				case 13:
					this.dropItemRand(OreSpawnMain.MyItemShoes_3, 1, par3World, par4, par5, par6);
					break;
				case 14:
					this.dropItemRand(Items.glass_bottle, 1, par3World, par4, par5, par6);
					break;
				case 15:
					this.dropItemRand(Items.bone, 1, par3World, par4, par5, par6);
					break;
				case 16:
					this.dropItemRand(Item.getItemFromBlock(Blocks.stone), 1, par3World, par4, par5, par6);
					break;
				case 17:
					this.dropItemRand(Items.bucket, 1, par3World, par4, par5, par6);
					break;
				case 18:
					this.dropItemRand(Items.water_bucket, 1, par3World, par4, par5, par6);
					break;
				case 19:
					if (par3World.rand.nextInt(3) == 1)
						this.dropItemRand(Items.emerald, 1, par3World, par4, par5, par6);
					else
						this.dropItemRand(Item.getItemFromBlock(Blocks.gravel), 1, par3World, par4, par5, par6);
					break;
				case 20:
					if (par3World.rand.nextInt(3) == 1)
						this.dropItemRand(OreSpawnMain.MyRuby, 1, par3World, par4, par5, par6);
					else
						this.dropItemRand(Item.getItemFromBlock(Blocks.gravel), 1, par3World, par4, par5, par6);
					break;
				case 21:
					if (par3World.rand.nextInt(3) == 1)
						this.dropItemRand(OreSpawnMain.MyAmethyst, 1, par3World, par4, par5, par6);
					else
						this.dropItemRand(Item.getItemFromBlock(Blocks.gravel), 1, par3World, par4, par5, par6);
					break;
				case 22:
					this.dropItemRand(OreSpawnMain.MyMothScale, 1, par3World, par4, par5, par6);
					break;
				case 23:
					this.dropItemRand(OreSpawnMain.UraniumNugget, 1, par3World, par4, par5, par6);
					break;
				case 24:
					this.dropItemRand(OreSpawnMain.TitaniumNugget, 1, par3World, par4, par5, par6);
					break;
				case 25:
					if (par3World.rand.nextInt(2) == 1)
						this.dropItemRand(Items.diamond, 1, par3World, par4, par5, par6);
					else
						this.dropItemRand(Item.getItemFromBlock(Blocks.gravel), 1, par3World, par4, par5, par6);
					break;
				case 26:
					this.dropItemRand(Items.iron_ingot, 1, par3World, par4, par5, par6);
					break;
				case 27:
					this.dropItemRand(Items.gold_nugget, 1, par3World, par4, par5, par6);
					break;
				case 28:
					this.dropItemRand(Items.redstone, 1, par3World, par4, par5, par6);
					break;
				case 29:
					this.dropItemRand(Items.coal, 1, par3World, par4, par5, par6);
					break;
				case 30:
					this.dropItemRand(OreSpawnMain.MyItemShoes, 1, par3World, par4, par5, par6);
					break;
				case 31:
					this.dropItemRand(OreSpawnMain.MyItemShoes_1, 1, par3World, par4, par5, par6);
					break;
				case 32:
					this.dropItemRand(OreSpawnMain.MyItemShoes_2, 1, par3World, par4, par5, par6);
					break;
				case 33:
					this.dropItemRand(OreSpawnMain.MyItemShoes_3, 1, par3World, par4, par5, par6);
					break;
				case 34:
					this.dropItemRand(Items.fish, 1, par3World, par4, par5, par6);
					break;
				case 35:
					this.dropItemRand(Items.glass_bottle, 1, par3World, par4, par5, par6);
					break;
				case 36:
					this.dropItemRand(Items.bone, 1, par3World, par4, par5, par6);
					break;
				case 37:
					this.dropItemRand(Item.getItemFromBlock(Blocks.stone), 1, par3World, par4, par5, par6);
					break;
				case 38:
					this.dropItemRand(Item.getItemFromBlock(Blocks.stone_button), 1, par3World, par4, par5, par6);
					break;
				case 39:
					this.dropItemRand(Items.bucket, 1, par3World, par4, par5, par6);
					break;
				case 40:
					this.dropItemRand(Items.water_bucket, 1, par3World, par4, par5, par6);
					break;
				default:
					break;
			}
		}
		
		if (bid == Blocks.sand) {
			int i = par3World.rand.nextInt(60);
			switch (i) {
				case 0:
					this.dropItemRand(Items.iron_horse_armor, 1, par3World, par4, par5, par6);
					break;
				case 1:
					this.dropItemRand(Items.shears, 1, par3World, par4, par5, par6);
					break;
				case 2:
					this.dropItemRand(Items.carrot_on_a_stick, 1, par3World, par4, par5, par6);
					break;
				case 3:
					this.dropItemRand(Items.poisonous_potato, 1, par3World, par4, par5, par6);
					break;
				case 4:
					this.dropItemRand(Items.item_frame, 1, par3World, par4, par5, par6);
					break;
				case 5:
					this.dropItemRand(Items.bone, 1, par3World, par4, par5, par6);
					break;
				case 6:
					this.dropItemRand(Items.compass, 1, par3World, par4, par5, par6);
					break;
				case 7:
					this.dropItemRand(Items.glass_bottle, 1, par3World, par4, par5, par6);
					break;
				case 8:
					this.dropItemRand(Items.saddle, 1, par3World, par4, par5, par6);
					break;
				case 9:
					this.dropItemRand(Items.iron_helmet, 1, par3World, par4, par5, par6);
					break;
				case 10:
					this.dropItemRand(Items.iron_chestplate, 1, par3World, par4, par5, par6);
					break;
				case 11:
					this.dropItemRand(Items.iron_leggings, 1, par3World, par4, par5, par6);
					break;
				case 12:
					this.dropItemRand(Items.iron_boots, 1, par3World, par4, par5, par6);
					break;
				case 13:
					this.dropItemRand(Item.getItemFromBlock(Blocks.sand), 1, par3World, par4, par5, par6);
					break;
				default:
					break;
			}
		}
		
		if (bid == Blocks.gravel) {
			int i = par3World.rand.nextInt(60);
			switch (i) {
				case 0:
					this.dropItemRand(Items.flint, 1, par3World, par4, par5, par6);
					break;
				case 1:
					this.dropItemRand(OreSpawnMain.MySalt, 1, par3World, par4, par5, par6);
					break;
				case 2:
					this.dropItemRand(Items.flint_and_steel, 1, par3World, par4, par5, par6);
					break;
				case 3:
					this.dropItemRand(Items.spider_eye, 1, par3World, par4, par5, par6);
					break;
				case 4:
					this.dropItemRand(Items.item_frame, 1, par3World, par4, par5, par6);
					break;
				case 5:
					this.dropItemRand(Items.feather, 1, par3World, par4, par5, par6);
					break;
				case 6:
					this.dropItemRand(Items.string, 1, par3World, par4, par5, par6);
					break;
				case 7:
					this.dropItemRand(Items.glass_bottle, 1, par3World, par4, par5, par6);
					break;
				case 8:
					this.dropItemRand(Items.lead, 1, par3World, par4, par5, par6);
					break;
				case 9:
					this.dropItemRand(Items.name_tag, 1, par3World, par4, par5, par6);
					break;
				case 10:
					this.dropItemRand(Item.getItemFromBlock(Blocks.sand), 1, par3World, par4, par5, par6);
					break;
				case 11:
					this.dropItemRand(Item.getItemFromBlock(Blocks.gravel), 1, par3World, par4, par5, par6);
					break;
				default:
					break;
			}
		}
		
		if (bid == Blocks.dirt) {
			int i = par3World.rand.nextInt(60);
			switch (i) {
				case 0:
					this.dropItemRand(Items.string, 1, par3World, par4, par5, par6);
					break;
				case 1:
					this.dropItemRand(OreSpawnMain.MySalt, 1, par3World, par4, par5, par6);
					break;
				case 2:
					this.dropItemRand(Items.shears, 1, par3World, par4, par5, par6);
					break;
				case 3:
					this.dropItemRand(Items.stick, 1, par3World, par4, par5, par6);
					break;
				case 4:
					this.dropItemRand(Items.bowl, 1, par3World, par4, par5, par6);
					break;
				case 5:
					this.dropItemRand(Items.flower_pot, 1, par3World, par4, par5, par6);
					break;
				case 6:
					this.dropItemRand(Items.sign, 1, par3World, par4, par5, par6);
					break;
				case 7:
					this.dropItemRand(Items.brick, 1, par3World, par4, par5, par6);
					break;
				case 8:
					this.dropItemRand(Items.paper, 1, par3World, par4, par5, par6);
					break;
				case 9:
					this.dropItemRand(Items.bone, 1, par3World, par4, par5, par6);
					break;
				case 10:
					this.dropItemRand(Items.glass_bottle, 1, par3World, par4, par5, par6);
					break;
				case 11:
					this.dropItemRand(Item.getItemFromBlock(Blocks.sand), 1, par3World, par4, par5, par6);
					break;
				case 12:
					this.dropItemRand(Item.getItemFromBlock(Blocks.gravel), 1, par3World, par4, par5, par6);
					break;
				case 13:
					this.dropItemRand(Item.getItemFromBlock(Blocks.dirt), 1, par3World, par4, par5, par6);
					break;
				default:
					break;
			}
		}
		
		if (bid == Blocks.grass) {
			int i = par3World.rand.nextInt(60);
			switch (i) {
				case 0:
					this.dropItemRand(Item.getItemFromBlock(Blocks.yellow_flower), 1, par3World, par4, par5, par6);
					break;
				case 1:
					this.dropItemRand(Item.getItemFromBlock(Blocks.red_flower), 1, par3World, par4, par5, par6);
					break;
				case 2:
					this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.MyFlowerPinkBlock), 1, par3World, par4, par5, par6);
					break;
				case 3:
					this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.MyFlowerBlueBlock), 1, par3World, par4, par5, par6);
					break;
				case 4:
					this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.MyFlowerBlackBlock), 1, par3World, par4, par5, par6);
					break;
				case 5:
					this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.MyFlowerScaryBlock), 1, par3World, par4, par5, par6);
				case 6:
					this.dropItemRand(Items.wheat, 1, par3World, par4, par5, par6);
					break;
				case 7:
					this.dropItemRand(Items.pumpkin_seeds, 1, par3World, par4, par5, par6);
					break;
				case 8:
					this.dropItemRand(Items.melon_seeds, 1, par3World, par4, par5, par6);
					break;
				case 9:
					this.dropItemRand(Items.carrot, 1, par3World, par4, par5, par6);
					break;
				case 10:
					this.dropItemRand(Items.potato, 1, par3World, par4, par5, par6);
					break;
				case 11:
					this.dropItemRand(Item.getItemFromBlock(Blocks.deadbush), 1, par3World, par4, par5, par6);
					break;
				case 12:
					this.dropItemRand(Item.getItemFromBlock(Blocks.gravel), 1, par3World, par4, par5, par6);
					break;
				case 13:
					this.dropItemRand(Item.getItemFromBlock(Blocks.dirt), 1, par3World, par4, par5, par6);
					break;
				case 14:
					this.dropItemRand(Item.getItemFromBlock(Blocks.grass), 1, par3World, par4, par5, par6);
					break;
				default:
					break;
			}
		}
		
		par1ItemStack.damageItem(1, par2EntityPlayer);
		
		return true;
	}

	
	
	
	
	public String getMaterialName()
	{
		return "Unknown";
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
