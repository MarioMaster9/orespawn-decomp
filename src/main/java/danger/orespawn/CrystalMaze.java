package danger.orespawn;

import java.awt.Point;
import java.util.Vector;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;










public class CrystalMaze
{
	public static final int WTOP = 1;
	public static final int WRGT = 2;
	public static final int WBOT = 4;
	public static final int WLFT = 8;
	
	public void buildCrystalMaze(World world, int x, int y, int z, Chunk chunk)
	{
		int i, j, k;
		
		for (i = 0; i < 16; i++) {
			for (j = 0; j < 16; j++) {
				for (k = 0; k < 3; k++) {
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x + j, y + k, z + i, Blocks.air, 0);
				}
			}
		}

		this.makeMaze(world, x, y, z, 4, 4, 4, 1, chunk);
		
		this.openCrystalMaze(world, x, y, z, 4, 4, 4, chunk);
	}

	private void openCrystalMaze(World world, int xx, int yy, int zz, int xw, int zw, int csz, Chunk chunk)
	{
		int i, j, k;
		
		for (i = 0; i < zw * csz; i++) {
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx, yy, zz + i, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx, yy + 1, zz + i, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx, yy + 2, zz + i, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + i, yy, zz, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + i, yy + 1, zz, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + i, yy + 2, zz, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + zw * csz - 1, yy, zz + i, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + zw * csz - 1, yy + 1, zz + i, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + zw * csz - 1, yy + 2, zz + i, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + i, yy, zz + zw * csz - 1, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + i, yy + 1, zz + zw * csz - 1, Blocks.air, 0);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + i, yy + 2, zz + zw * csz - 1, Blocks.air, 0);
		}

		
		
		for (i = 0; i < zw * csz; i++) {
			for (j = 0; j < zw * csz; j++) {
				OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + j, yy - 1, zz + i, Blocks.bedrock, 0);
				OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + j, yy + 3, zz + i, Blocks.bedrock, 0);
			}
		}

		for (k = 0; k < 4; k++) {
			i = world.rand.nextInt(zw * csz);
			j = world.rand.nextInt(zw * csz);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + j, yy + 3, zz + i, OreSpawnMain.CrystalStone, 0);
		}
		i = world.rand.nextInt(zw * csz);
		j = world.rand.nextInt(zw * csz);
		OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + j, yy - 1, zz + i, OreSpawnMain.CrystalStone, 0);
		
	}

	private void makeMaze(World world, int xx, int yy, int zz, int xw, int zw, int csz, int b, Chunk chunk) {
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
		
		
		
		int full = (WTOP | WRGT | WBOT | WLFT);
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
					this.drawSide(world, x * cellsize, y * cellsize, (x + 1) * cellsize, y * cellsize, xx, yy, zz, cellsize, gridh, gridw, b, chunk);
				}

				if ((val & WRGT) != 0) {
					this.drawSide(world, (x + 1) * cellsize - 1, y * cellsize, (x + 1) * cellsize - 1, (y + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b, chunk);
				}

				if ((val & WBOT) != 0) {
					this.drawSide(world, x * cellsize, (y + 1) * cellsize - 1, (x + 1) * cellsize, (y + 1) * cellsize - 1, xx, yy, zz, cellsize, gridh, gridw, b, chunk);
				}

				if ((val & WLFT) != 0) {
					this.drawSide(world, x * cellsize, y * cellsize, x * cellsize, (y + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b, chunk);
				}
			}
		}
	}

	private void drawSide(World world, int fromx, int fromz, int tox, int toz, int x, int y, int z, int cellsize, int gridh, int gridw, int bb, Chunk chunk) {
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
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y, j + z, blk, 0);
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y + 1, j + z, blk, 0);
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y + 2, j + z, blk, 0);
				}
			}
		} else {
			j = fromz;
			for (i = fromx; i <= tox; i++) {
				if (i < cellsize * gridw) {
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y, j + z, blk, 0);
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y + 1, j + z, blk, 0);
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y + 2, j + z, blk, 0);
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

	
	
	
	
	private void clearArea(World world, int x, int y, int z, Chunk chunk)
	{
		int i, j, k;
		for (i = 0; i < 60; i++) {
			int hi = 5;
			if (i >= 30) hi = 7;
			for (j = 0; j < hi; j++) {
				for (k = 0; k < 30; k++) {
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x + i, y + j, z + k, Blocks.air, 0);
				}
			}
		}

		for (i = 0; i < 5; i++)
			for (j = 0; j < 6; j++)
				for (k = 0; k < 30; k++)
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x - i, y + j, z + k, Blocks.air, 0);
	}
}
