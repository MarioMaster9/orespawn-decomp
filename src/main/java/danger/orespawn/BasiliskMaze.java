package danger.orespawn;

import java.awt.Point;
import java.util.Vector;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;





public class BasiliskMaze {
	public static final int WTOP = 1;
	public static final int WRGT = 2;
	public static final int WBOT = 4;
	public static final int WLFT = 8;
	

	public void buildBasiliskMaze(World world, int x, int y, int z) {
		int depth = 20 + world.rand.nextInt(10);
		this.clearArea(world, x + 3, y - depth - 4, z - 20);
		this.makeMaze(world, x + 3, y - depth - 3, z - 20, 10, 10, 3, 0);
		this.openMaze(world, x + 3, y - depth - 3, z - 20, 10, 10, 3);
		this.buildCastle(world, x + 3, y - depth - 4, z - 20);
		this.makeEntrance(world, x, y, z, depth);
	}

	
	private void makeMaze(World world, int xx, int yy, int zz, int xw, int zw, int csz, int b)
	{
		int x;
		int y;
		int dir;
		int val;
		int[][] cells;
		Point current_cell;
		Vector inlist;
		Vector outlist;
		Vector frontlist;
		
		int gridw = xw;
		int gridh = zw;
		int cellsize = csz;
		if (cellsize < 3) cellsize = 3;

		cells = new int[gridw][gridh];
		
		
		
		int full = 15;
		for (x = 0; x < gridw; x++) {
			for (y = 0; y < gridh; y++) {
				cells[x][y] = full;
			}
		}
		int left = (WLFT<<4);
		int right = (WRGT<<4);
		for (y = 0; y < gridh; y++)
		{
			cells[0][y] |= left;
			cells[gridw - 1][y] |= right;
		}
		int top = (WTOP<<4);
		int bottom = (WBOT<<4);
		for (x = 0; x < gridw; x++)
		{
			cells[x][0] |= top;
			cells[x][gridh - 1] |= bottom;
		}


		

		outlist = new Vector(gridw * gridh);
		inlist = new Vector(10, 10);
		frontlist = new Vector(10, 10);
		for (x = 0; x < gridw; x++)
			for (y = 0; y < gridh; y++)
				outlist.addElement(new Point(x, y));
		current_cell = (Point)this.rndElement(outlist);
		inlist.addElement(current_cell);
		this.moveNbrs(current_cell, cells, outlist, frontlist);

		while(!frontlist.isEmpty())
		{
			current_cell = (Point)this.rndElement(frontlist);
			inlist.addElement(current_cell);
			this.moveNbrs(current_cell, cells, outlist, frontlist);
			dir = this.findInNbr(current_cell, cells, inlist);
			this.removeWall(current_cell, dir, cells);
		}

		
		
			current_cell = null;

		
		
		for (x = 0; x < gridw; x++) {
			for (y = 0; y < gridh; y++) {
				
				val = cells[x][y];
				if ((val & WTOP) != 0) {
					this.drawSide(world, x * cellsize, y * cellsize, (x + 1) * cellsize, y * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
				}

				if ((val & WRGT) != 0) {
					this.drawSide(world, (x + 1) * cellsize - 1, y * cellsize, (x + 1) * cellsize - 1, (y + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
				}

				if ((val & WBOT) != 0) {
					this.drawSide(world, x * cellsize, (y + 1) * cellsize - 1, (x + 1) * cellsize, (y + 1) * cellsize - 1, xx, yy, zz, cellsize, gridh, gridw, b);
				}

				if ((val & WLFT) != 0) {
					this.drawSide(world, x * cellsize, y * cellsize, x * cellsize, (y + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
				}
			}
		}
	}

	private void drawSide(World world, int fromx, int fromz, int tox, int toz, int x, int y, int z, int cellsize, int gridh, int gridw, int bb) {
		int i, j;
		Block blk = Blocks.obsidian;
		if (bb != 0) blk = Blocks.bedrock;
		if (fromx > tox) {
			i = fromx;
			fromx = tox;
			tox = i;
		}
		if (fromz > toz) {
			i = fromz;
			fromz = toz;
			toz = i;
		}
		if (fromx == tox) {
			i = fromx;
			for (j = fromz; j <= toz; j++) {
				if (j < cellsize * gridh) {
					OreSpawnMain.setBlockFast(world, i + x, y, j + z, blk, 0, 2);
					OreSpawnMain.setBlockFast(world, i + x, y + 1, j + z, blk, 0, 2);
					OreSpawnMain.setBlockFast(world, i + x, y + 2, j + z, blk, 0, 2);
				}
			}
		} else {
			j = fromz;
			for (i = fromx; i <= tox; i++) {
				if (i < cellsize * gridw) {
					OreSpawnMain.setBlockFast(world, i + x, y, j + z, blk, 0, 2);
					OreSpawnMain.setBlockFast(world, i + x, y + 1, j + z, blk, 0, 2);
					OreSpawnMain.setBlockFast(world, i + x, y + 2, j + z, blk, 0, 2);
				}
			}
		}
		
	}

	
	
	
	
	
	
	
	private int findInNbr(Point p, int[][] cells, Vector inlist)
	{
		int d = this.rnd(4) - 1;
		int k = 0;
		while (k < 4)
		{
			switch (d)
			{
				case 0:
					if ((cells[p.x][p.y] & (WTOP<<4)) != 0) break;
					if (inlist.indexOf(new Point(p.x, p.y - 1)) >= 0)
						return WTOP;
					break;
				case 1:
					if ((cells[p.x][p.y] & (WRGT<<4)) != 0) break;
					if (inlist.indexOf(new Point(p.x + 1, p.y)) >= 0)
						return WRGT;
					break;
				case 2:
					if ((cells[p.x][p.y] & (WBOT<<4)) != 0) break;
					if (inlist.indexOf(new Point(p.x, p.y + 1)) >= 0)
						return WBOT;
					break;
				case 3:
					if ((cells[p.x][p.y] & (WLFT<<4)) != 0) break;
					if (inlist.indexOf(new Point(p.x - 1, p.y)) >= 0)
						return WLFT;
					break;
				default: break; }
			d = (d + 1) % 4;
			k++;
		}
		return 0;
	}

	
	
	
	
	
	
	private void moveNbrs(Point p, int[][] cells, Vector outlist, Vector frontlist)
	{
		if ((cells[p.x][p.y] & (WTOP<<4)) == 0)
		{
			Point s = new Point(p.x, p.y - 1);
			this.movePoint(s, outlist, frontlist);
		}
		if ((cells[p.x][p.y] & (WRGT<<4)) == 0)
		{
			Point s = new Point(p.x + 1, p.y);
			this.movePoint(s, outlist, frontlist);
		}
		if ((cells[p.x][p.y] & (WBOT<<4)) == 0)
		{
			Point s = new Point(p.x, p.y + 1);
			this.movePoint(s, outlist, frontlist);
		}
		if ((cells[p.x][p.y] & (WLFT<<4)) == 0)
		{
			Point s = new Point(p.x - 1, p.y);
			this.movePoint(s, outlist, frontlist);
		}
	}

	
	
	
	private void movePoint(Point p, Vector v, Vector w)
	{
		int i = v.indexOf(p);
		if (i >= 0)
		{
			v.removeElementAt(i);
			w.addElement(p);
		}
	}

	
	
	private void removeWall(Point p, int d, int[][] cells)
	{
		cells[p.x][p.y] ^= d;
		
		
		
		switch (d)
		{
			case WTOP: cells[p.x][p.y - 1] ^= WBOT;
				break;
			case WRGT: cells[p.x + 1][p.y] ^= WLFT;
				break;
			case WBOT: cells[p.x][p.y + 1] ^= WTOP;
				break;
			case WLFT: cells[p.x - 1][p.y] ^= WRGT;
				break;
		}
	}

	
	private int rnd(int n)
	{
		return (int)(Math.random() * (double)n + 1.0D);
	}

	private Object rndElement(Vector v)
	{
		int i = this.rnd(v.size()) - 1;
		Object s = v.elementAt(i);
		v.removeElementAt(i);
		return s;
	}

	private Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6)
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


	
	
	private void clearArea(World world, int x, int y, int z)
	{
		int i, j, k;
		for (i = 0; i < 60; i++) {
			int hi = 5;
			if (i >= 30) hi = 7;
			for (j = 0; j < hi; j++) {
				for (k = 0; k < 30; k++) {
					OreSpawnMain.setBlockFast(world, x + i, y + j, z + k, Blocks.air, 0, 2);
				}
			}
		}

		for (i = 0; i < 5; i++)
			for (j = 0; j < 6; j++)
				for (k = 0; k < 30; k++)
					OreSpawnMain.setBlockFast(world, x - i, y + j, z + k, Blocks.air, 0, 2);
	}
	
	private void openMaze(World world, int xx, int yy, int zz, int xw, int zw, int csz)
	{
		int i, j, k;
		Block bid;
		for (i = 0; i < zw * csz; i++) {
			bid = world.getBlock(xx + 1, yy, zz + i);
			if (bid == Blocks.air) {
				OreSpawnMain.setBlockFast(world, xx, yy, zz + i, Blocks.air, 0, 2);
				OreSpawnMain.setBlockFast(world, xx, yy + 1, zz + i, Blocks.air, 0, 2);
				OreSpawnMain.setBlockFast(world, xx, yy + 2, zz + i, Blocks.air, 0, 2);
				break;
			}
		}
		
		for (i = zw * csz - 1; i >= 0; i--) {
			bid = world.getBlock(xx + xw * csz - 2, yy, zz + i);
			if (bid == Blocks.air) {
				OreSpawnMain.setBlockFast(world, xx + xw * csz - 1, yy, zz + i, Blocks.air, 0, 2);
				OreSpawnMain.setBlockFast(world, xx + xw * csz - 1, yy + 1, zz + i, Blocks.air, 0, 2);
				OreSpawnMain.setBlockFast(world, xx + xw * csz - 1, yy + 2, zz + i, Blocks.air, 0, 2);
				break;
			}
		}
		
	}
	
	private final WeightedRandomChestContent[] chestContentsList = new WeightedRandomChestContent[]{
		new WeightedRandomChestContent(Items.ender_pearl, 0, 3, 6, 15),
		new WeightedRandomChestContent(Items.diamond, 0, 15, 25, 20),
		new WeightedRandomChestContent(Items.blaze_rod, 0, 4, 12, 15),
		new WeightedRandomChestContent(OreSpawnMain.CageEmpty, 0, 3, 10, 20),
		new WeightedRandomChestContent(OreSpawnMain.CagedGirlfriend, 0, 2, 4, 15),
		new WeightedRandomChestContent(Items.iron_ingot, 0, 2, 20, 20),
		new WeightedRandomChestContent(Items.gold_ingot, 0, 4, 16, 20),
		new WeightedRandomChestContent(OreSpawnMain.MyIngotUranium, 0, 2, 8, 20),
		new WeightedRandomChestContent(OreSpawnMain.MyIngotTitanium, 0, 2, 6, 20),
		new WeightedRandomChestContent(OreSpawnMain.MySunFish, 0, 2, 8, 20),
		new WeightedRandomChestContent(OreSpawnMain.MyFireFish, 0, 3, 8, 20),
		new WeightedRandomChestContent(OreSpawnMain.MyLavaEel, 0, 5, 24, 20),
		new WeightedRandomChestContent(OreSpawnMain.MyCornDog, 0, 6, 12, 20),
		new WeightedRandomChestContent(Items.diamond_pickaxe, 0, 1, 1, 15),
		new WeightedRandomChestContent(Items.diamond_sword, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.MyUltimatePickaxe, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.MyUltimateSword, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.MyUltimateFishingRod, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.MyUltimateBow, 0, 1, 1, 15),
		new WeightedRandomChestContent(Items.diamond_chestplate, 0, 1, 1, 15),
		new WeightedRandomChestContent(Items.diamond_helmet, 0, 1, 1, 15),
		new WeightedRandomChestContent(Items.diamond_leggings, 0, 1, 1, 15),
		new WeightedRandomChestContent(Items.diamond_boots, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.UltimateBody, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.UltimateLegs, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.UltimateHelmet, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.UltimateBoots, 0, 1, 1, 15),
		new WeightedRandomChestContent(OreSpawnMain.MyRuby, 0, 1, 1, 5),
		new WeightedRandomChestContent(OreSpawnMain.MyThunderStaff, 0, 1, 1, 5),
		new WeightedRandomChestContent(OreSpawnMain.MagicApple, 0, 1, 1, 15),
		new WeightedRandomChestContent(Items.golden_apple, 0, 2, 4, 15)
	};

	
	
	private void buildCastle(World world, int x, int y, int z)
	{
		int i, j, k;
		for (i = 0; i < 60; i++) {
			for (k = 0; k < 30; k++) {
				OreSpawnMain.setBlockFast(world, x + i, y, z + k, Blocks.obsidian, 0, 2);
			}
		}
		for (i = 0; i < 80; i++) {
			OreSpawnMain.setBlockFast(world, x + world.rand.nextInt(28) + 1, y, z + world.rand.nextInt(28) + 1, Blocks.lava, 0, 2);
		}
		
		
		for (i = 0; i < 20; i++) {
			OreSpawnMain.setBlockFast(world, x + 30 + world.rand.nextInt(28) + 1, y, z + world.rand.nextInt(28) + 1, OreSpawnMain.MyRTPBlock, 0, 2);
		}

		
		for (i = 0; i < 30; i++) {
			for (k = 0; k < 30; k++) {
				OreSpawnMain.setBlockFast(world, x + i, y + 4, z + k, Blocks.bedrock, 0, 2);
			}
		}
		for (i = 0; i < 30; i++) {
			for (k = 0; k < 30; k++) {
				OreSpawnMain.setBlockFast(world, x + i + 30, y + 6, z + k, Blocks.bedrock, 0, 2);
			}
		}
		for (i = 0; i < 30; i++)
			for (k = 0; k < 5; k++) {
				OreSpawnMain.setBlockFast(world, x + 59, y + k + 1, z + i, Blocks.obsidian, 0, 2);
				OreSpawnMain.setBlockFast(world, x + 60, y + k + 1, z + i, Blocks.bedrock, 0, 2);
				OreSpawnMain.setBlockFast(world, x + 61, y + k + 1, z + i, Blocks.bedrock, 0, 2);
			}
		for (i = 0; i < 30; i++)
			for (k = 0; k < 5; k++) {
				OreSpawnMain.setBlockFast(world, x + 30 + i, y + k + 1, z, Blocks.obsidian, 0, 2);
				OreSpawnMain.setBlockFast(world, x + 30 + i, y + k + 1, z - 1, Blocks.bedrock, 0, 2);
				OreSpawnMain.setBlockFast(world, x + 30 + i, y + k + 1, z - 2, Blocks.bedrock, 0, 2);
			}
		for (i = 0; i < 30; i++)
			for (k = 0; k < 5; k++) {
				OreSpawnMain.setBlockFast(world, x + 30 + i, y + k + 1, z + 29, Blocks.obsidian, 0, 2);
				OreSpawnMain.setBlockFast(world, x + 30 + i, y + k + 1, z + 30, Blocks.bedrock, 0, 2);
				OreSpawnMain.setBlockFast(world, x + 30 + i, y + k + 1, z + 31, Blocks.bedrock, 0, 2);
			}

		for (i = 0; i < 30; i++) {
			OreSpawnMain.setBlockFast(world, x + 30, y + 5, z + i, Blocks.obsidian, 0, 2);
		}

		for (i = 0; i < 30; i++)
			for (k = 0; k < 4; k++)
				OreSpawnMain.setBlockFast(world, x - 4 + k, y, z + i, Blocks.sandstone, 0, 2);

		for (i = 0; i < 30; i++)
			for (k = 0; k < 4; k++)
				OreSpawnMain.setBlockFast(world, x - 4 + k, y + 5, z + i, Blocks.obsidian, 0, 2);

		for (i = 0; i < 30; i++)
			for (k = 1; k < 5; k++)
				OreSpawnMain.setBlockFast(world, x - 5, y + k, z + i, Blocks.iron_ore, 0, 2);
		for (i = 0; i < 5; i++)
			for (k = 1; k < 5; k++)
				OreSpawnMain.setBlockFast(world, x - 4 + i, y + k, z - 1, Blocks.iron_ore, 0, 2);
		for (i = 0; i < 5; i++)
			for (k = 1; k < 5; k++)
				OreSpawnMain.setBlockFast(world, x - 4 + i, y + k, z + 30, Blocks.iron_ore, 0, 2);

		
		
		for (k = 0; k < 4; k++)
			OreSpawnMain.setBlockFast(world, x - 4, y + 1 + k, z, Blocks.sandstone, 0, 2);
		for (k = 0; k < 4; k++)
			OreSpawnMain.setBlockFast(world, x - 4, y + 1 + k, z + 15, Blocks.sandstone, 0, 2);
		for (k = 0; k < 4; k++)
			OreSpawnMain.setBlockFast(world, x - 4, y + 1 + k, z + 29, Blocks.sandstone, 0, 2);

		OreSpawnMain.setBlockFast(world, x - 3, y + 3, z, OreSpawnMain.ExtremeTorch, 0, 2);
		OreSpawnMain.setBlockFast(world, x - 3, y + 3, z + 15, OreSpawnMain.ExtremeTorch, 0, 2);
		OreSpawnMain.setBlockFast(world, x - 3, y + 3, z + 29, OreSpawnMain.ExtremeTorch, 0, 2);
		
		OreSpawnMain.setBlockFast(world, x + 30, y + 4, z + 2, Blocks.redstone_torch, 0, 2);
		OreSpawnMain.setBlockFast(world, x + 30, y + 4, z + 15, Blocks.redstone_torch, 0, 2);
		OreSpawnMain.setBlockFast(world, x + 30, y + 4, z + 27, Blocks.redstone_torch, 0, 2);
		
		
		TileEntityChest chest = null;
		i = 2 + world.rand.nextInt(3);
		for (k = 0; k < i; k++) {
			OreSpawnMain.setBlockFast(world, x + 58, y + 4, z + 2 + k * 2, Blocks.torch, 0, 2);
			OreSpawnMain.setBlockFast(world, x + 58, y + 1, z + 2 + k * 2, Blocks.chest, 0, 2);
			chest = (TileEntityChest)world.getTileEntity(x + 58, y + 1, z + 2 + k * 2);
			if (chest != null)
			{
				
				WeightedRandomChestContent.generateChestContents(world.rand, this.chestContentsList, chest, 5 + world.rand.nextInt(6));
			}
		}

		
		
		Entity ent = null;
		ent = this.spawnCreature(world, "Basilisk", (double)x + (double)45.0F, (double)y + 1.01, (double)z + 15.0D);
		if (ent != null) {
			Basilisk b = (Basilisk)ent;
			b.func_110163_bv();
		}
		ent = this.spawnCreature(world, "Basilisk", (double)x + (double)46.0F, (double)y + 1.01, (double)z + 15.0D);
		if (ent != null) {
			Basilisk b = (Basilisk)ent;
			b.func_110163_bv();
		}
		ent = this.spawnCreature(world, "Basilisk", (double)x + (double)47.0F, (double)y + 1.01, (double)z + 15.0D);
		if (ent != null) {
			Basilisk b = (Basilisk)ent;
			b.func_110163_bv();
		}
	}

	
	public void makeEntrance(World world, int x, int y, int z, int depth)
	{
		int i, j, k, l, m;
		int width = 8;

		for (j = width; j >= 0; j--) {
			for (i = 0; i < j * 2 + 4; i++) {
				OreSpawnMain.setBlockFast(world, x + i - j, y + width - j, z - j, Blocks.sandstone, 0, 2);
				OreSpawnMain.setBlockFast(world, x + i - j, y + width - j, z + j + 3, Blocks.sandstone, 0, 2);
				OreSpawnMain.setBlockFast(world, x - j, y + width - j, z + i - j, Blocks.sandstone, 0, 2);
				OreSpawnMain.setBlockFast(world, x + j + 3, y + width - j, z + i - j, Blocks.sandstone, 0, 2);
			}
		}
		

		k = 0;
		for (j = width; j > -depth; j--) {
			for (i = 0; i < 4; i++) {
				OreSpawnMain.setBlockFast(world, x + i, y + j, z, Blocks.bedrock, 0, 2);
				OreSpawnMain.setBlockFast(world, x + i, y + j, z + 3, Blocks.bedrock, 0, 2);
				OreSpawnMain.setBlockFast(world, x, y + j, z + i, Blocks.bedrock, 0, 2);
				OreSpawnMain.setBlockFast(world, x + 3, y + j, z + i, Blocks.bedrock, 0, 2);
			}
			

			for (l = 0; l < 2; ++l) {
				for (m = 0; m < 2; ++m) {
					OreSpawnMain.setBlockFast(world, x + 1 + l, y + j, z + 1 + m, Blocks.air, 0, 2);
				}
			}
			switch (k) {
				case 0:
					OreSpawnMain.setBlockFast(world, x + 1, y + j, z + 1, Blocks.obsidian, 0, 2);
					break;
				case 1:
					OreSpawnMain.setBlockFast(world, x + 2, y + j, z + 1, Blocks.obsidian, 0, 2);
					break;
				case 2:
					OreSpawnMain.setBlockFast(world, x + 2, y + j, z + 2, Blocks.obsidian, 0, 2);
					break;
				default:
					OreSpawnMain.setBlockFast(world, x + 1, y + j, z + 2, Blocks.obsidian, 0, 2);
					break;
			}
			k++;
			if (k > 3) k = 0;
		}
	}
}
