package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;






public class ItemMagicApple extends Item
{
	public int tree_radius = 6;
	
	
	
	
	public boolean no_critters = false;
	
	Random rand = OreSpawnMain.OreSpawnRand;
	
	private final WeightedRandomChestContent[] chestContentsList = new WeightedRandomChestContent[]
	{
		new WeightedRandomChestContent(Items.ender_pearl, 0, 1, 2, 3),
		new WeightedRandomChestContent(Items.diamond, 0, 1, 5, 15),
		new WeightedRandomChestContent(Items.blaze_rod, 0, 1, 3, 10),
		new WeightedRandomChestContent(OreSpawnMain.CageEmpty, 0, 1, 10, 7),
		new WeightedRandomChestContent(OreSpawnMain.CagedGirlfriend, 0, 1, 2, 6),
		new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 10, 16),
		new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 6, 16),
		new WeightedRandomChestContent(OreSpawnMain.UraniumNugget, 0, 1, 6, 6),
		new WeightedRandomChestContent(OreSpawnMain.TitaniumNugget, 0, 1, 4, 6),
		new WeightedRandomChestContent(Items.bread, 0, 1, 8, 20),
		new WeightedRandomChestContent(Items.apple, 0, 1, 8, 20),
		new WeightedRandomChestContent(Items.cookie, 0, 1, 16, 20),
		new WeightedRandomChestContent(Items.cooked_beef, 0, 1, 8, 20),
		new WeightedRandomChestContent(Items.cooked_chicken, 0, 1, 8, 20),
		new WeightedRandomChestContent(Items.cooked_fished, 0, 1, 8, 20),
		new WeightedRandomChestContent(Items.cooked_porkchop, 0, 1, 8, 20),
		new WeightedRandomChestContent(Items.pumpkin_pie, 0, 1, 4, 20),
		new WeightedRandomChestContent(Items.carrot, 0, 1, 16, 20),
		new WeightedRandomChestContent(Items.potato, 0, 1, 16, 20),
		new WeightedRandomChestContent(OreSpawnMain.MySunFish, 0, 1, 4, 6),
		new WeightedRandomChestContent(OreSpawnMain.MyFireFish, 0, 1, 8, 6),
		new WeightedRandomChestContent(OreSpawnMain.MyPopcornBag, 0, 1, 4, 16),
		new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 20),
		new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 20),
		new WeightedRandomChestContent(Items.diamond_pickaxe, 0, 1, 1, 5),
		new WeightedRandomChestContent(Items.diamond_sword, 0, 1, 1, 5),
		new WeightedRandomChestContent(Items.bow, 0, 1, 1, 20),
		new WeightedRandomChestContent(Items.arrow, 0, 1, 64, 20),
		new WeightedRandomChestContent(OreSpawnMain.MyUltimatePickaxe, 0, 1, 1, 2),
		new WeightedRandomChestContent(OreSpawnMain.MyUltimateSword, 0, 1, 1, 1),
		new WeightedRandomChestContent(OreSpawnMain.MyUltimateFishingRod, 0, 1, 1, 5),
		new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 20),
		new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 20),
		new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 20),
		new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 20),
		new WeightedRandomChestContent(Items.diamond_chestplate, 0, 1, 1, 5),
		new WeightedRandomChestContent(Items.diamond_helmet, 0, 1, 1, 5),
		new WeightedRandomChestContent(Items.diamond_leggings, 0, 1, 1, 5),
		new WeightedRandomChestContent(Items.diamond_boots, 0, 1, 1, 5),
		new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 5)
	};

	
	
	
	
	
	public ItemMagicApple(int i)
	{
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par1ItemStack.addEnchantment(Enchantment.fortune, 2);
	}

	private Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6)
	{
		Entity var8 = null;
		
		
		var8 = EntityList.createEntityByID(par1, par0World);
		
		if (var8 != null)
		{
			
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			
			
			par0World.spawnEntityInWorld(var8);
			
			((EntityLiving)var8).playLivingSound();
		}

		return var8;
	}

	/**
	 * Called each tick while using an item.
	 * @param stack The Item being used
	 * @param player The Player using the item
	 * @param count The amount of time in tick the item has been used for continuously
	 */
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack);
		if (lvl <= 0) {
			stack.addEnchantment(Enchantment.fortune, 2);
		}
	}

	public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
		this.onUsingTick(stack, (EntityPlayer)null, 0);
	}

	
	
	
	private Boolean isBoringBlock(World world, int x, int y, int z)
	{
		Block var1 = world.getBlock(x, y, z);
		
		if (var1 == Blocks.tallgrass)
			return Boolean.valueOf(true);
		if (var1 == Blocks.cactus)
			return Boolean.valueOf(true);
		if (var1 == Blocks.red_flower)
			return Boolean.valueOf(true);
		if (var1 == Blocks.yellow_flower)
			return Boolean.valueOf(true);
		if (var1 == Blocks.leaves)
			return Boolean.valueOf(true);
		if (var1 == Blocks.snow)
			return Boolean.valueOf(true);
		if (var1 == OreSpawnMain.MyStrawberryPlant)
			return Boolean.valueOf(true);
		if (var1 == OreSpawnMain.MyAppleLeaves)
			return Boolean.valueOf(true);
		
		if (world.isAirBlock(x, y, z) == true)
			return Boolean.valueOf(true);

		if (var1 == null)
			return Boolean.valueOf(true);

		return Boolean.valueOf(false);
	}

	
	
	private Boolean isBoringBaseBlock(World world, int x, int y, int z)
	{
		if (world.isAirBlock(x, y, z) == true)
			return Boolean.valueOf(true);
		Block var1 = world.getBlock(x, y, z);
		if (var1 == Blocks.stone)
			return Boolean.valueOf(false);
		if (var1 == Blocks.bedrock)
			return Boolean.valueOf(false);
		
		return Boolean.valueOf(true);
	}

	
	
	
	private void growVines(World world, int par2, int par3, int par4, int par5, int par6, Chunk chunk)
	{
		if (world.getBlock(par2, par3, par4) != Blocks.air)
		{
			return;
		}
		this.FastSetBlock(world, par2, par3, par4, Blocks.vine, par5, 2, chunk);
		while (par6 > 0)
		{
			--par3;
			if (world.getBlock(par2, par3, par4) != Blocks.air)
			{
				return;
			}
			this.FastSetBlock(world, par2, par3, par4, Blocks.vine, par5, 2, chunk);
			--par6;
		}
	}

	
	
	
	
	
	
	private void make_branch(World world, int x, int y, int z, int this_width, int dirx, int dirz, Block ID, Block leafID, int tree_type, int t_radius, boolean bad_critters, Chunk chunk)
	{
		int current_width = this_width;
		int xaccum, zaccum;
		int realx, realz;
		int last_branch = 0;
		int branch_side = 1;
		int subdirz, subdirx;
		int i, j, n;
		int leaf_depth = 0;
		int leaf_width = 0;
		int lw;
		
		xaccum = dirx;
		zaccum = dirz;
		
		if (this.rand.nextInt(2) == 0) branch_side = -1;

		int length;
		while (current_width >= 0)
		{
			length = this_width * 3 + this.rand.nextInt(this_width + 3);

			for (i = 0; i < length; i++)
			{
				
				for (j = -current_width; j <= current_width; j++) {
					realx = x + j * dirz + xaccum;
					realz = z + j * dirx + zaccum;
					if (this.isBoringBlock(world, realx, y, realz))
					{
						
						
						
						
						
						if (tree_type >= 0) {
							this.FastSetBlock(world, realx, y, realz, ID, tree_type, 2, chunk);
						} else {
							this.FastSetBlock(world, realx, y, realz, ID, 0, 2, chunk);
						}
					}
					if (i > 0 && j == 0 && current_width >= 3)
					{
						if (tree_type >= 0 && this.rand.nextInt(75) == 0 || tree_type < 0 && this.rand.nextInt(50) == 0)
						{
							if (!bad_critters && world.isAirBlock(realx, y + 1, realz))
							{
								this.FastSetBlock(world, realx, y + 1, realz, Blocks.chest, 0, 2, chunk);
								TileEntityChest chest = (TileEntityChest)world.getTileEntity(realx, y + 1, realz);
								if (chest != null)
								{
									
									WeightedRandomChestContent.generateChestContents(this.rand, this.chestContentsList, chest, 1 + this.rand.nextInt(8));
								}
							}
						}
						else if (this.rand.nextInt(50) == 0)
						{
							
							if (!bad_critters && world.isAirBlock(realx, y + 1, realz) && world.isAirBlock(realx, y + 2, realz) && world.isAirBlock(realx, y + 3, realz))
							{
								
								Entity ent = null;
								ent = this.spawnCreature(world, 99, (double)realx + 0.5D, (double)y + 1.01, (double)realz + 0.5D);
							}
						}
					}
				}

				
				if (current_width < 3 || this_width <= 1) {
					leaf_depth = 2 + this.rand.nextInt(2);
					leaf_width = 2 + this.rand.nextInt(3);

					for (n = 0; n < leaf_depth; n++) {
						lw = current_width + leaf_width - n;
						if (current_width == 0) {
							if (length - i <= 2) { 
								if (lw >= length - i) lw = length - i - 1;
							}
						}
						if (lw < 0) lw = 0;
						for (j = -lw; j <= lw; j++) {
							realx = x + j * Math.abs(dirz) + xaccum + dirx;
							realz = z + j * Math.abs(dirx) + zaccum + dirz;
							if (this.isBoringBlock(world, realx, y + n, realz)) {
								if (tree_type >= 0)
								{
									this.FastSetBlock(world, realx, y + n, realz, leafID, tree_type, 2, chunk);
									
									
									
									
									if (n == 0 && tree_type == 3 && lw != 0 && (j == lw || j == -lw) && this.rand.nextInt(5) == 0)
									{
										if (dirx == 0) {
											if (j == lw) {
												this.growVines(world, realx + 1, y, realz, 2, this.rand.nextInt(10), chunk);
											} else {
												this.growVines(world, realx - 1, y, realz, 8, this.rand.nextInt(10), chunk);
											}
										} else if (j == lw) {
											this.growVines(world, realx, y, realz + 1, 4, this.rand.nextInt(10), chunk);
										} else {
											this.growVines(world, realx, y, realz - 1, 1, this.rand.nextInt(10), chunk);
										}
										
									}
								}
								else
								{
									Block local_leaf_type = leafID;
									if (this.rand.nextInt(20) == 1) {
										if (this.rand.nextInt(3) != 0) {
											local_leaf_type = Blocks.redstone_block;
										} else {
											int ilt = this.rand.nextInt(4);
											if (ilt == 0) local_leaf_type = OreSpawnMain.MyBlockUraniumBlock;
											if (ilt == 1) local_leaf_type = OreSpawnMain.MyBlockTitaniumBlock;
											if (ilt == 2) local_leaf_type = OreSpawnMain.MyBlockRubyBlock;
											if (ilt == 3) local_leaf_type = OreSpawnMain.MyBlockAmethystBlock;
										}
									}
									this.FastSetBlock(world, realx, y + n, realz, local_leaf_type, 0, 2, chunk);
								}
							}
						}
					}
				}

				
				if (current_width > 0 && last_branch > current_width && current_width != this_width) {
					if (this.rand.nextInt(current_width + 1) == 0) {
						subdirx = branch_side;
						subdirz = 0;
						if (dirx != 0)
						{
							subdirx = 0;
							subdirz = branch_side;
						}
						
	
						this.make_branch(world, x + xaccum + current_width * subdirx, y, z + zaccum + current_width * subdirz, current_width - 1, subdirx, subdirz, ID, leafID, tree_type, t_radius, bad_critters, chunk);
						last_branch = 0;
						
						if (branch_side < 0)
							branch_side = 1;
						else
							branch_side = -1;
					}
				}
				xaccum += dirx;
				zaccum += dirz;
				++last_branch;
			}
			--current_width;
		}
	}

	
	
	
	
	
	public void MakeBigSquareTree(World world, int x, int y, int z, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, boolean bad_critters, Chunk chunk)
	{
		int i, j, k, m, n;
		
		int this_height = t_radius + this.rand.nextInt(t_radius);
		int this_width = t_radius;
		int base_height = t_radius * 3;
		int spiral = 0;
		int current_y = 0;
		int branch = 0;
		int do_floor = 0;
		int platform_looper = 1;
		int last = -1, last_last = -1;
		int next;
		
		
		for (i = -t_radius; i <= t_radius; i++)
		{
			if (this.isBoringBaseBlock(world, x + i, y, z - t_radius)) {
				for (j = 0; j < 20; j++) {
					if (y - j > 0) {
						if (this.isBoringBaseBlock(world, x + i, y - j, z - t_radius)) {
							if (tree_type >= 0)
								this.FastSetBlock(world, x + i, y - j, z - t_radius, ID, tree_type, 2, chunk);
							else
								this.FastSetBlock(world, x + i, y - j, z - t_radius, ID, 0, 2, chunk);
						} else
							break;
					}
				}
			}
			if (this.isBoringBaseBlock(world, x + i, y, z + t_radius)) {
				for (j = 0; j < 20; j++) {
					if (y - j > 0) {
						if (this.isBoringBaseBlock(world, x + i, y - j, z + t_radius)) {
							if (tree_type >= 0)
								this.FastSetBlock(world, x + i, y - j, z + t_radius, ID, tree_type, 2, chunk);
							else
								this.FastSetBlock(world, x + i, y - j, z + t_radius, ID, 0, 2, chunk);
						} else
							break;
					}
				}
			}
			if (this.isBoringBaseBlock(world, x - t_radius, y, z + i)) {
				for (j = 0; j < 20; j++) {
					if (y - j > 0) {
						if (this.isBoringBaseBlock(world, x - t_radius, y - j, z + i)) {
							if (tree_type >= 0)
								this.FastSetBlock(world, x - t_radius, y - j, z + i, ID, tree_type, 2, chunk);
							else
								this.FastSetBlock(world, x - t_radius, y - j, z + i, ID, 0, 2, chunk);
						} else
							break;
					}
				}
			}
			if (this.isBoringBaseBlock(world, x + t_radius, y, z + i)) {
				for (j = 0; j < 20; j++) {
					if (y - j > 0) {
						if (this.isBoringBaseBlock(world, x + t_radius, y - j, z + i)) {
							if (tree_type >= 0)
								this.FastSetBlock(world, x + t_radius, y - j, z + i, ID, tree_type, 2, chunk);
							else
								this.FastSetBlock(world, x + t_radius, y - j, z + i, ID, 0, 2, chunk);
						} else
							break;
					}
				}
			}
		}

		
		
		current_y = y;
		do_floor = 0;
		spiral = -this_width;
		while (this_width >= 0) {
			if (this_width != t_radius) base_height = 0;

			for (j = 0; j < this_height + base_height; j++)
			{
				do_floor = 0;

				
				for (i = -this_width; i <= this_width; i++)
				{
					if (this.isBoringBaseBlock(world, x + i, current_y, z - this_width)) {
						if (tree_type >= 0)
							this.FastSetBlock(world, x + i, current_y, z - this_width, ID, tree_type, 2, chunk);
						else
							this.FastSetBlock(world, x + i, current_y, z - this_width, ID, 0, 2, chunk);
					}
					if (this.isBoringBaseBlock(world, x + i, current_y, z + this_width)) {
						if (tree_type >= 0)
							this.FastSetBlock(world, x + i, current_y, z + this_width, ID, tree_type, 2, chunk);
						else
							this.FastSetBlock(world, x + i, current_y, z + this_width, ID, 0, 2, chunk);
					}
					if (this.isBoringBaseBlock(world, x - this_width, current_y, z + i)) {
						if (tree_type >= 0)
							this.FastSetBlock(world, x - this_width, current_y, z + i, ID, tree_type, 2, chunk);
						else
							this.FastSetBlock(world, x - this_width, current_y, z + i, ID, 0, 2, chunk);
					}
					if (this.isBoringBaseBlock(world, x + this_width, current_y, z + i)) {
						if (tree_type >= 0)
							this.FastSetBlock(world, x + this_width, current_y, z + i, ID, tree_type, 2, chunk);
						else
							this.FastSetBlock(world, x + this_width, current_y, z + i, ID, 0, 2, chunk);
					}
				}

				if (this_width != 0 || j < this_height / 2)
				{
					platform_looper = 1;
					
					
					if ((spiral == 0 && this_width >= 2) || spiral == this_width || spiral == this_width - 1 && j == this_height + base_height - 1)
					{
						platform_looper++;
						if (spiral != 0 && this_width >= 3) platform_looper++;
						if (spiral == 0) do_floor = 1;
					}

					
					
					
					
					for (k = 0; k < platform_looper; k++)
					{
						if (this.isBoringBlock(world, x - spiral, current_y, z - this_width - 1)) {
							this.FastSetBlock(world, x - spiral, current_y, z - this_width - 1, stepID, 0, 2, chunk);
						}
						if (this.isBoringBlock(world, x + spiral, current_y, z + this_width + 1)) {
							this.FastSetBlock(world, x + spiral, current_y, z + this_width + 1, stepID, 0, 2, chunk);
						}
						if (this.isBoringBlock(world, x - this_width - 1, current_y, z + spiral)) {
							this.FastSetBlock(world, x - this_width - 1, current_y, z + spiral, stepID, 0, 2, chunk);
						}
						if (this.isBoringBlock(world, x + this_width + 1, current_y, z - spiral)) {
							this.FastSetBlock(world, x + this_width + 1, current_y, z - spiral, stepID, 0, 2, chunk);
						}
						if (this_width >= 3) {
							if (this.isBoringBlock(world, x - spiral, current_y, z - this_width - 2)) {
								this.FastSetBlock(world, x - spiral, current_y, z - this_width - 2, stepID, 0, 2, chunk);
							}
							if (this.isBoringBlock(world, x + spiral, current_y, z + this_width + 2)) {
								this.FastSetBlock(world, x + spiral, current_y, z + this_width + 2, stepID, 0, 2, chunk);
							}
							if (this.isBoringBlock(world, x - this_width - 2, current_y, z + spiral)) {
								this.FastSetBlock(world, x - this_width - 2, current_y, z + spiral, stepID, 0, 2, chunk);
							}
							if (this.isBoringBlock(world, x + this_width + 2, current_y, z - spiral)) {
								this.FastSetBlock(world, x + this_width + 2, current_y, z - spiral, stepID, 0, 2, chunk);
							}
						}
						if (platform_looper != 1) spiral++;
					}

					
					if (do_floor != 0)
					{
						for (m = -this_width; m <= this_width; m++)
						{
							for (n = -this_width; n <= this_width; n++)
							{
								if (this.isBoringBlock(world, x + m, current_y, z + n)) {
									if (tree_type >= 0)
										this.FastSetBlock(world, x + m, current_y, z + n, ID, tree_type, 2, chunk);
									else
										this.FastSetBlock(world, x + m, current_y, z + n, ID, 0, 2, chunk);
									if (m == 0 && n == 0)
									{
										
										
										if (this.rand.nextInt(2) == 0)
										{
											if (!bad_critters && world.isAirBlock(x, current_y + 1, z))
											{
												this.FastSetBlock(world, x, current_y + 1, z, Blocks.chest, 0, 2, chunk);
												TileEntityChest chest = (TileEntityChest)world.getTileEntity(x, current_y + 1, z);
												if (chest != null)
												{
													
													WeightedRandomChestContent.generateChestContents(this.rand, this.chestContentsList, chest, t_radius - this_width + this.rand.nextInt(10));
												}
											}
										}
									}
								}
							}
						}
					}
				}

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				if (this_width != t_radius) {
					next = this.rand.nextInt(4 + this_width);
					while (next == last || next == last_last) {
						next = this.rand.nextInt(4 + this_width);
					}
					if (next < 4) {
						last_last = last;
						last = next;
					}
					switch (next) {
						case 0:
							this.make_branch(world, x + this_width, current_y, z, this_width, 1, 0, ID, leafID, tree_type, t_radius, bad_critters, chunk);
							break;
						case 1:
							this.make_branch(world, x - this_width, current_y, z, this_width, -1, 0, ID, leafID, tree_type, t_radius, bad_critters, chunk);
							break;
						case 2:
							this.make_branch(world, x, current_y, z + this_width, this_width, 0, 1, ID, leafID, tree_type, t_radius, bad_critters, chunk);
							break;
						case 3:
							this.make_branch(world, x, current_y, z - this_width, this_width, 0, -1, ID, leafID, tree_type, t_radius, bad_critters, chunk);
							break;
						default:
							break;
					}
				}
				
				current_y++;
				if (do_floor == 0) spiral++;
				if (spiral > this_width) spiral = -this_width;
			}

			
			this_width--;
			
			if (Math.abs(spiral) > this_width) spiral = -this_width;
			
			this_height += this.rand.nextInt(t_radius);
		}

		
		
		if (this.isBoringBaseBlock(world, x, current_y, z)) {
			this.FastSetBlock(world, x, current_y, z, Blocks.emerald_block, 0, 2, chunk);
			this.FastSetBlock(world, x, current_y + 1, z, Blocks.emerald_block, 0, 2, chunk);
			
			if (stepID == Blocks.diamond_block)
			{
				Entity var8 = null;
				
				
				var8 = EntityList.createEntityByName("The King", world);
				
				if (var8 != null)
				{
					
					var8.setLocationAndAngles((double)x, (double)(current_y + 4), (double)z, world.rand.nextFloat() * 360.0F, 0.0F);
					
					
					world.spawnEntityInWorld(var8);
					
					((EntityLiving)var8).playLivingSound();
					((TheKing)var8).setGuardMode(1);
				}
			}
			if (stepID == OreSpawnMain.MyBlockAmethystBlock)
			{
				Entity var8 = null;
				
				
				var8 = EntityList.createEntityByName("The Queen", world);
				
				if (var8 != null)
				{
					
					var8.setLocationAndAngles((double)x, (double)(current_y + 4), (double)z, world.rand.nextFloat() * 360.0F, 0.0F);
					
					
					world.spawnEntityInWorld(var8);
					
					((EntityLiving)var8).playLivingSound();
					((TheQueen)var8).setGuardMode(1);
					((TheQueen)var8).setBadMood(1);
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	private void MakeCirclularBranch(World world, int iangle, int branchlen, int width, int startx, int starty, int startz, int twist, Block ID, Block leafID, int tree_type, Chunk chunk)
	{
		double curlen = 0.0D;
		int curangle = iangle;
		double curx = (double)startx;
		double curz = (double)startz;
		double wd;
		double wx;
		double wz;
		double tw;
		int ta;
		Block id;
		
		
		for (curlen = 0.0D; curlen < (double)branchlen; curlen += 0.5D)
		{
			
			curx += 0.5D * Math.sin(Math.toRadians(curangle));
			curz += 0.5D * Math.cos(Math.toRadians(curangle));
			
			
			
			tw = (double)width - (double)width * curlen / (double)branchlen;
			for (wd = 0.0D; wd <= tw; wd += 0.5D)
			{
				
				id = leafID;
				if (wd < tw / 2.0D) id = ID;
				if (tw < 0.9) id = leafID;

				ta = curangle + 90;
				if (ta > 360) ta -= 360;
				wx = curx + wd * Math.sin(Math.toRadians(ta));
				wz = curz + wd * Math.cos(Math.toRadians(ta));
				if (this.isBoringBlock(world, (int)wx, starty, (int)wz))
				{
					if (tree_type >= 0)
						this.FastSetBlock(world, (int)wx, starty, (int)wz, id, tree_type, 2, chunk);
					else
						this.FastSetBlock(world, (int)wx, starty, (int)wz, id, 0, 2, chunk);
				}
				
				if (id == ID)
				{
					if (this.isBoringBlock(world, (int)wx, starty + 1, (int)wz))
					{
						if (tree_type >= 0)
							this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, tree_type, 2, chunk);
						else
							this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, 0, 2, chunk);
					}
				}

				
				ta = curangle - 90;
				if (ta < 0) ta += 360;
				wx = curx + wd * Math.sin(Math.toRadians(ta));
				wz = curz + wd * Math.cos(Math.toRadians(ta));
				if (this.isBoringBlock(world, (int)wx, starty, (int)wz))
				{
					if (tree_type >= 0)
						this.FastSetBlock(world, (int)wx, starty, (int)wz, id, tree_type, 2, chunk);
					else
						this.FastSetBlock(world, (int)wx, starty, (int)wz, id, 0, 2, chunk);
				}
				if (id == ID)
				{
					if (this.isBoringBlock(world, (int)wx, starty + 1, (int)wz))
					{
						if (tree_type >= 0)
							this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, tree_type, 2, chunk);
						else
							this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, 0, 2, chunk);
					}
				}
			}

			
			curangle += twist;
			if (curangle < 0) curangle += 360;
			if (curangle >= 360) curangle -= 360;
		}
	}

    /**
	 * Make a really big freaking awesome tree.
	 * Complete with spiral staircase,
	 * and ready-to-rent rooms!
	 * Well, no. Circular trees don't have pre-made rooms or staircases...
	 */	
	public void MakeBigCircularTree(World world, int x, int y, int z, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, boolean bad_critters, Chunk chunk)
	{
		int i, j, m, n;
		double rad = t_radius;
		int curx = 0, cury = 0, curz = 0;
		double dt, dr;
		int stepindex = this.rand.nextInt(360);
		int ibranch = 0;
		int ibranchlen;
		
		//First thing, make sure we touch the ground everywhere...
		//Rip along the four sides going downward until we hit something.
		cury = y;
		for(i = 0; i < 360 ; i++)
		{
			dt = (rad * Math.sin(Math.toRadians(i))) + 0.5D;
			curx = (int) dt;
			dt = (rad * Math.cos(Math.toRadians(i))) + 0.5D;
			curz = (int) dt;
			if(isBoringBaseBlock(world, x+curx, cury, z+curz)){
				for(j=0;j<20;j++){
					if(cury-j>0){
						if(isBoringBaseBlock(world, x+curx, cury-j, z+curz)){
							if(tree_type >= 0)
								FastSetBlock(world, x+curx, cury-j, z+curz, ID, tree_type, 2, chunk);
							else
								FastSetBlock(world, x+curx, cury-j, z+curz, ID, 0, 2, chunk);
						}else
							break;
					}
				}
			}
		}

		//Build the trunk and staircase
		cury = 1;
		while(rad > 0.0D){
			for(i = 0; i < 360 ; i++)
			{
				dt = (rad * Math.sin(Math.toRadians(i))) + 0.5D;
				curx = (int)dt;
				dt = (rad * Math.cos(Math.toRadians(i))) + 0.5D;
				curz = (int)dt;
				if(isBoringBaseBlock(world, x+curx, y+cury, z+curz)){
					if(tree_type >= 0)
						FastSetBlock(world, x+curx, y+cury, z+curz, ID, tree_type, 2, chunk);
					else
						FastSetBlock(world, x+curx, y+cury, z+curz, ID, 0, 2, chunk);
				}

				
				if (i >= stepindex - 1 && i <= stepindex + 1 && rad > 1.0D)
				{
					dt = ((rad + 1.9) * Math.sin(Math.toRadians(i))) + 0.5D;
					curx = (int)dt;
					dt = ((rad + 1.9) * Math.cos(Math.toRadians(i))) + 0.5D;
					curz = (int)dt;

					for(m=-1;m<=1;m++)
						for(n=-1;n<=1;n++)
							if(isBoringBaseBlock(world, x+curx+m, y+cury, z+curz+n))
								FastSetBlock(world, x+curx+m, y+cury, z+curz+n, stepID, 0, 2, chunk);
				}
			}
			
			//Add branches!
			if(cury > (int)rad)
			{
				ibranch += 80 + this.rand.nextInt(80); //angle of where to put this branch
				if(ibranch>360)ibranch -= 360;
				ibranchlen = (int)(rad * 5.0D) + this.rand.nextInt((int)rad + 2);
				dt = (rad * Math.sin(Math.toRadians(ibranch))) + 0.5D;
				curx = (int)dt;
				dt = (rad * Math.cos(Math.toRadians(ibranch))) + 0.5D;
				curz = (int)dt;
				//System.out.printf("Try making a branch @ %d,  %d, %d with angle %d and len %d\n", curx, cury, curz, ibranch, ibranchlen);
				MakeCirclularBranch(world, ibranch, ibranchlen, (int)rad+1, x+curx, y+cury, z+curz, this.rand.nextInt(2) * (this.rand.nextInt(2) == 0 ? -1 : 1), ID, leafID, tree_type, chunk);
			}

			//Add flooring if high and wide enough
			if ((cury%6)==0 && rad > 3.0D)
			{
				dr = rad - 0.25D;
				//circle around in ever smaller radius
				while(dr > 0.0D){
					for (i = 0; i < 360 ; i++)
					{
						dt = dr * Math.sin(Math.toRadians(i)) + 0.5D;
						curx = (int)dt;
						dt = dr * Math.cos(Math.toRadians(i)) + 0.5D;
						curz = (int)dt;
						if(isBoringBaseBlock(world, x+curx, y+cury, z+curz)){
							if(tree_type >= 0)
								FastSetBlock(world, x+curx, y+cury, z+curz, ID, tree_type, 2, chunk);
							else
								FastSetBlock(world, x+curx, y+cury, z+curz, ID, 0, 2, chunk);
						}
					}
					dr -= 0.25D;
				}

				
				if (this.rand.nextInt(2) == 0)
				{
					if (!bad_critters && world.isAirBlock(x, y + cury + 1, z))
					{
						this.FastSetBlock(world, x, y + cury + 1, z, Blocks.chest, 0, 2, chunk);
						TileEntityChest chest = (TileEntityChest)world.getTileEntity(x, y + cury + 1, z);
						if (chest != null)
						{
							
							WeightedRandomChestContent.generateChestContents(this.rand, this.chestContentsList, chest, t_radius - (int)rad + this.rand.nextInt(10));
						}
					}
				}
			}

			stepindex += 15 + (int)(((double)t_radius - rad) * 3.0D);
			if (stepindex > 360) stepindex -= 360;
			cury++;
			rad -= 0.01D * (double)this.rand.nextInt(15); //randomish height
			if (rad <= 0.0D)
			{
				
				
				if (this.isBoringBaseBlock(world, x, y + cury, z)) {
					this.FastSetBlock(world, x, y + cury, z, Blocks.diamond_block, 0, 2, chunk);
				}
			}
		}
	}

    /**
	 * Make a really big freaking awesome tree.
	 * Complete with spiral staircase,
	 * and ready-to-rent rooms!
	 * Well, no. Circular trees don't have pre-made rooms or staircases...
	 */	
	public void MakeBigRoundTree(World world, int inx, int y, int inz, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, Chunk chunk)
	{
		int i, j, m, n;
		double rad = t_radius;
		int cury = 0;
		double dt, dr;
		int ibranch = 0;
		int ibranchlen;
		float fcurx, fcurz;
		float fx, fz;
		fx = inx;
		fx += 0.5F;
		fz = inz;
		fz += 0.5F;
		
		
		//First thing, make sure we touch the ground everywhere...
		//Rip along the four sides going downward until we hit something.
		cury = y;
		for(i = 0; i < 360 ; i++)
		{
			dt = (rad * Math.sin(Math.toRadians(i)));
			fcurx = (float) dt;
			dt = (rad * Math.cos(Math.toRadians(i)));
			fcurz = (float) dt;
			if(isBoringBaseBlock(world, (int)(fx+fcurx), cury, (int)(fz+fcurz))){
				for(j=0;j<20;j++){
					if(cury-j>0){
						if(isBoringBaseBlock(world, (int)(fx+fcurx), cury-j, (int)(fz+fcurz))){
							if(tree_type >= 0)
								FastSetBlock(world, (int)(fx+fcurx), cury-j, (int)(fz+fcurz), ID, tree_type, 2, chunk);
							else
								FastSetBlock(world, (int)(fx+fcurx), cury-j, (int)(fz+fcurz), ID, 0, 2, chunk);
						}else
							break;
					}
				}
			}
		}

		//Build the trunk and staircase
		cury = 1;
		while(rad > 0.0D){
			for(i = 0; i < 360 ; i++)
			{
				dt = (rad * Math.sin(Math.toRadians(i)));
				fcurx = (float)dt;
				dt = (rad * Math.cos(Math.toRadians(i)));
				fcurz = (float)dt;
				if(isBoringBaseBlock(world, (int)(fx+fcurx), y+cury, (int)(fz+fcurz))){
					if(tree_type >= 0)
						FastSetBlock(world, (int)(fx+fcurx), y+cury, (int)(fz+fcurz), ID, tree_type, 2, chunk);
					else
						FastSetBlock(world, (int)(fx+fcurx), y+cury, (int)(fz+fcurz), ID, 0, 2, chunk);
				}
			}

			//Add branches!
			if(cury > (int)rad)
			{
				ibranch += 80 + world.rand.nextInt(80); //angle of where to put this branch
				if(ibranch>360)ibranch -= 360;
				ibranchlen = (int)(rad * 5.0D) + world.rand.nextInt((int)rad + 2);
				dt = (rad * Math.sin(Math.toRadians(ibranch)));
				fcurx = (float)dt;
				dt = (rad * Math.cos(Math.toRadians(ibranch)));
				fcurz = (float)dt;
				//System.out.printf("Try making a branch @ %d,  %d, %d with angle %d and len %d\n", curx, cury, curz, ibranch, ibranchlen);
				MakeRoundBranch(world, ibranch, ibranchlen, (int)rad+1, fx+fcurx, y+cury, fz+fcurz, ID, leafID, tree_type, chunk);
			}

			//Add flooring if high and wide enough
			if((cury%6)==0 && rad > 3.0D)
			{
				dr = rad - 0.25D;
				//circle around in ever smaller radius
				while(dr > 0.0D){
					for(i = 0; i < 360 ; i++)
					{
						dt = dr * Math.sin(Math.toRadians(i));
						fcurx = (float)dt;
						dt = dr * Math.cos(Math.toRadians(i));
						fcurz = (float)dt;
						if(isBoringBaseBlock(world, (int)(fx+fcurx), y+cury, (int)(fz+fcurz))){
							if(tree_type >= 0)
								FastSetBlock(world, (int)(fx+fcurx), y+cury, (int)(fz+fcurz), ID, tree_type, 2, chunk);
							else
								FastSetBlock(world, (int)(fx+fcurx), y+cury, (int)(fz+fcurz), ID, 0, 2, chunk);
						}
					}
					dr -= 0.25D;
				}
				
			}
			
			cury++;
			rad -= 0.01D * (double)world.rand.nextInt(15); //randomish height
			if (rad <= 0.0D)
			{
				
				
				if (this.isBoringBaseBlock(world, (int)fx, y + cury, (int)fz)) {
					this.FastSetBlock(world, (int)fx, y + cury, (int)fz, Blocks.diamond_block, 0, 2, chunk);
				}
			}
		}
	}

	private void MakeRoundBranch(World world, int iangle, int branchlen, int width, float startx, int starty, float startz, Block ID, Block leafID, int tree_type, Chunk chunk)
	{
	double deltadir = 3.1415926d/50.0d; //rads!
	double deltamag = 0.35f; //block!
	double h;
	int ix, iz;
	Block id;
	int ixlast = 0, izlast = 0;
	int xoff = 0;
	int zoff = 0;
	int radius = branchlen/2;
	float centerx, centerz;
	
		centerx =  (float)(startx + (radius * Math.sin(Math.toRadians(iangle))));
		centerz =  (float)(startz + (radius * Math.cos(Math.toRadians(iangle))));
	
	//System.out.printf("x = %f,  z = %f\n", (double)this.posX, (double)this.posZ);
	
		ixlast = izlast = 0;
		for(double curdir = -3.1415926d; curdir < 3.1415926d; curdir += deltadir){
			//System.out.printf("angle = %f,  radius = %f\n", (double)(curdir+dir), (double)radius);
			for(h = 0.75d; h<radius; h += deltamag){
				ix = (int)(centerx+(Math.cos(curdir)*h));
				iz = (int)(centerz+(Math.sin(curdir)*h));
				if(ix == ixlast && iz == izlast)continue;
				ixlast = ix;
				izlast = iz;
				//FastSetBlock(ix, starty, iz, Block.mycelium.blockID);
				id = ID;
				if((radius-h)<2)id = leafID;
				if(isBoringBlock(world, ix, starty, iz)){
					FastSetBlock(world, ix, starty, iz, id, tree_type, 2, chunk);
				}
			}
		}
	}

	
	public void FastSetBlock(World world, int ix, int iy, int iz, Block id, int im, int iflg, Chunk chunk)
	{
		OreSpawnMain.setBlockSuperFast(world, ix, iy, iz, id, im, 2, chunk);
	}

	
	
	
	
	
	
	
	
	
	
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int clickedX, int clickedY, int clickedZ, int par7, float par8, float par9, float par10)
	{
		int rand_treetype;
		Block leaf_type;
		Block var1 = world.getBlock(clickedX, clickedY, clickedZ);
		if (var1 != Blocks.grass && var1 != Blocks.farmland && var1 != Blocks.dirt) {
			return false;
		}
		
		
		int tree_type = this.rand.nextInt(4);
		
		leaf_type = Blocks.leaves;
		
		
		this.no_critters = true;
		if (this.rand.nextInt(2) == 1) this.no_critters = false;

		
		
		if (!world.isRemote) {
			world.setBlock(clickedX, clickedY, clickedZ, Blocks.gold_block, 0, 2);
		}
		for (int var3 = 0; var3 < 6; var3++)
		{
			par2EntityPlayer.worldObj.spawnParticle("largesmoke", (double)((float)clickedX + 0.5F), (double)((float)(clickedY + 1) + 0.25F), (double)((float)clickedZ + 0.5F), 0.0D, 0.0D, 0.0D);
			par2EntityPlayer.worldObj.spawnParticle("largeexplode", (double)((float)clickedX + 0.5F), (double)((float)(clickedY + 1) + 0.25F), (double)((float)clickedZ + 0.5F), 0.0D, 0.0D, 0.0D);
			par2EntityPlayer.worldObj.spawnParticle("reddust", (double)((float)clickedX + 0.5F), (double)((float)(clickedY + 1) + 0.25F), (double)((float)clickedZ + 0.5F), 0.0D, 0.0D, 0.0D);
		}

		par2EntityPlayer.worldObj.playSoundAtEntity(par2EntityPlayer, "random.explode", 2.8F, 1.5F);
		
		if (!world.isRemote)
		{
			rand_treetype = this.rand.nextInt(100);
			
			if (rand_treetype >= 20) {
				if (rand_treetype >= 40) {
					if (tree_type != 3 && this.rand.nextInt(10) == 1) leaf_type = OreSpawnMain.MyAppleLeaves;
					this.MakeBigSquareTree(world, clickedX, clickedY, clickedZ, Blocks.log, leaf_type, Blocks.mossy_cobblestone, tree_type, this.tree_radius, this.no_critters, null);
				} else {
					this.MakeBigRoundTree(world, clickedX, clickedY, clickedZ, Blocks.log, leaf_type, Blocks.mossy_cobblestone, tree_type, this.tree_radius, null);
				}
			} else if (rand_treetype == 1) {
				if (OreSpawnMain.GinormousEmeraldTreeEnable != 0) {
					if (this.rand.nextInt(2) == 0) {
						this.MakeBigSquareTree(world, clickedX, clickedY, clickedZ, Blocks.gold_block, Blocks.emerald_block, Blocks.diamond_block, -1, this.tree_radius, true, null);
					} else {
						this.MakeBigSquareTree(world, clickedX, clickedY, clickedZ, Blocks.obsidian, OreSpawnMain.MyBlockRubyBlock, OreSpawnMain.MyBlockAmethystBlock, -1, this.tree_radius, true, null);
					}
				} else {
					this.MakeBigSquareTree(world, clickedX, clickedY, clickedZ, Blocks.log, leaf_type, Blocks.iron_ore, tree_type, this.tree_radius, this.no_critters, null);
				}
			} else {
				this.MakeBigCircularTree(world, clickedX, clickedY, clickedZ, Blocks.log, leaf_type, Blocks.mossy_cobblestone, tree_type, this.tree_radius, this.no_critters, null);
			}
		}

		if (!par2EntityPlayer.capabilities.isCreativeMode)
		{
			--par1ItemStack.stackSize;
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
