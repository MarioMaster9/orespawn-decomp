package danger.orespawn;

import java.awt.Point;
import java.util.Vector;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class CrystalMaze {
	public static final int WTOP = 1;
	public static final int WRGT = 2;
	public static final int WBOT = 4;
	public static final int WLFT = 8;

	public void buildCrystalMaze(World world, int x, int y, int z, Chunk chunk) {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				for (int k = 0; k < 3; k++) {
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x + j, y + k, z + i, Blocks.air, 0);
				}
			}
		}

		this.makeMaze(world, x, y, z, 4, 4, 4, 1, chunk);
		this.openCrystalMaze(world, x, y, z, 4, 4, 4, chunk);
	}

	private void openCrystalMaze(World world, int xx, int yy, int zz, int xw, int zw, int csz, Chunk chunk) {
		for (int i = 0; i < zw * csz; i++) {
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

		for (int var12 = 0; var12 < zw * csz; ++var12) {
			for (int j = 0; j < zw * csz; j++) {
				OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + j, yy - 1, zz + var12, Blocks.bedrock, 0);
				OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + j, yy + 3, zz + var12, Blocks.bedrock, 0);
			}
		}

		for (int k = 0; k < 4; k++) {
			int var13 = world.rand.nextInt(zw * csz);
			int j = world.rand.nextInt(zw * csz);
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + j, yy + 3, zz + var13, OreSpawnMain.CrystalStone, 0);
		}

		int var14 = world.rand.nextInt(zw * csz);
		int j = world.rand.nextInt(zw * csz);
		OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, xx + j, yy - 1, zz + var14, OreSpawnMain.CrystalStone, 0);
	}

	private void makeMaze(World world, int xx, int yy, int zz, int xw, int zw, int csz, int b, Chunk chunk) {
		int gridw = xw;
		int gridh = zw;
		int cellsize = csz;
		if (csz < 3) {
			cellsize = 3;
		}

		int[][] cells = new int[xw][zw];
		int full = 15;

		for (int x = 0; x < gridw; ++x) {
			for (int y = 0; y < gridh; ++y) {
				cells[x][y] = full;
			}
		}

		int left = (WLFT<<4);
		int right = (WRGT<<4);

		for (int y = 0; y < gridh; ++y) {
			cells[0][y] |= left;
			cells[gridw - 1][y] |= right;
		}

		int top = (WTOP<<4);
		int bottom = (WBOT<<4);

		for (int var27 = 0; var27 < gridw; ++var27) {
			cells[var27][0] |= top;
			cells[var27][gridh - 1] |= bottom;
		}

		Vector outlist = new Vector(gridw * gridh);
		Vector inlist = new Vector(10, 10);
		Vector frontlist = new Vector(10, 10);

		for (int var28 = 0; var28 < gridw; ++var28) {
			for (int var31 = 0; var31 < gridh; ++var31) {
				outlist.addElement(new Point(var28, var31));
			}
		}

		Point current_cell = (Point)this.rndElement(outlist);
		inlist.addElement(current_cell);
		this.moveNbrs(current_cell, cells, outlist, frontlist);

		while(!frontlist.isEmpty()) {
			current_cell = (Point)this.rndElement(frontlist);
			inlist.addElement(current_cell);
			this.moveNbrs(current_cell, cells, outlist, frontlist);
			int dir = this.findInNbr(current_cell, cells, inlist);
			this.removeWall(current_cell, dir, cells);
		}

		Point var34 = null;

		for (int var29 = 0; var29 < gridw; ++var29) {
			for (int var32 = 0; var32 < gridh; ++var32) {
				int val = cells[var29][var32];
				if ((val & WTOP) != 0) {
					this.drawSide(world, var29 * cellsize, var32 * cellsize, (var29 + 1) * cellsize, var32 * cellsize, xx, yy, zz, cellsize, gridh, gridw, b, chunk);
				}

				if ((val & WRGT) != 0) {
					this.drawSide(world, (var29 + 1) * cellsize - 1, var32 * cellsize, (var29 + 1) * cellsize - 1, (var32 + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b, chunk);
				}

				if ((val & WBOT) != 0) {
					this.drawSide(world, var29 * cellsize, (var32 + 1) * cellsize - 1, (var29 + 1) * cellsize, (var32 + 1) * cellsize - 1, xx, yy, zz, cellsize, gridh, gridw, b, chunk);
				}

				if ((val & WLFT) != 0) {
					this.drawSide(world, var29 * cellsize, var32 * cellsize, var29 * cellsize, (var32 + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b, chunk);
				}
			}
		}

	}

	private void drawSide(World world, int fromx, int fromz, int tox, int toz, int x, int y, int z, int cellsize, int gridh, int gridw, int bb, Chunk chunk) {
		Block blk = Blocks.obsidian;
		if (bb != 0) {
			blk = Blocks.bedrock;
		}

		if (fromx > tox) {
			int i = fromx;
			fromx = tox;
			tox = i;
		}

		if (fromz > toz) {
			int i = fromz;
			fromz = toz;
			toz = i;
		}

		if (fromx == tox) {
			int i = fromx;

			for (int j = fromz; j <= toz; j++) {
				if (j < cellsize * gridh) {
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y, j + z, blk, 0);
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y + 1, j + z, blk, 0);
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y + 2, j + z, blk, 0);
				}
			}
		} else {
			int j = fromz;

			for (int i = fromx; i <= tox; i++) {
				if (i < cellsize * gridw) {
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y, j + z, blk, 0);
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y + 1, j + z, blk, 0);
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, i + x, y + 2, j + z, blk, 0);
				}
			}
		}

	}

	private int findInNbr(Point p, int[][] cells, Vector inlist) {
		int d = this.rnd(4) - 1;

		for (int k = 0; k < 4; k++) {
			switch (d) {
				case 0:
					if ((cells[p.x][p.y] & (WTOP<<4)) == 0 && inlist.indexOf(new Point(p.x, p.y - 1)) >= 0) {
						return WTOP;
					}
					break;
				case 1:
					if ((cells[p.x][p.y] & (WRGT<<4)) == 0 && inlist.indexOf(new Point(p.x + 1, p.y)) >= 0) {
						return WRGT;
					}
					break;
				case 2:
					if ((cells[p.x][p.y] & (WBOT<<4)) == 0 && inlist.indexOf(new Point(p.x, p.y + 1)) >= 0) {
						return WBOT;
					}
					break;
				case 3:
					if ((cells[p.x][p.y] & (WLFT<<4)) == 0 && inlist.indexOf(new Point(p.x - 1, p.y)) >= 0) {
						return WLFT;
					}
			}

			d = (d + 1) % 4;
		}

		return 0;
	}

	private void moveNbrs(Point p, int[][] cells, Vector outlist, Vector frontlist) {
		if ((cells[p.x][p.y] & (WTOP<<4)) == 0) {
			Point s = new Point(p.x, p.y - 1);
			this.movePoint(s, outlist, frontlist);
		}

		if ((cells[p.x][p.y] & (WRGT<<4)) == 0) {
			Point s = new Point(p.x + 1, p.y);
			this.movePoint(s, outlist, frontlist);
		}

		if ((cells[p.x][p.y] & (WBOT<<4)) == 0) {
			Point s = new Point(p.x, p.y + 1);
			this.movePoint(s, outlist, frontlist);
		}

		if ((cells[p.x][p.y] & (WLFT<<4)) == 0) {
			Point s = new Point(p.x - 1, p.y);
			this.movePoint(s, outlist, frontlist);
		}

	}

	private void movePoint(Point p, Vector v, Vector w) {
		int i = v.indexOf(p);
		if (i >= 0) {
			v.removeElementAt(i);
			w.addElement(p);
		}

	}

	private void removeWall(Point p, int d, int[][] cells) {
		cells[p.x][p.y] ^= d;
		switch (d) {
			case WTOP:
				cells[p.x][p.y - 1] ^= WBOT;
				break;
			case WRGT:
				cells[p.x + 1][p.y] ^= WLFT;
				break;
			case WBOT:
				cells[p.x][p.y + 1] ^= WTOP;
				break;
			case WLFT:
				cells[p.x - 1][p.y] ^= WRGT;
				break;
		}
	}

	private int rnd(int n) {
		return (int)(Math.random() * (double)n + 1.0D);
	}

	private Object rndElement(Vector v) {
		int i = this.rnd(v.size()) - 1;
		Object s = v.elementAt(i);
		v.removeElementAt(i);
		return s;
	}

	private void clearArea(World world, int x, int y, int z, Chunk chunk) {
		for (int i = 0; i < 60; i++) {
			int hi = 5;
			if (i >= 30) {
				hi = 7;
			}

			for (int j = 0; j < hi; j++) {
				for (int k = 0; k < 30; k++) {
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x + i, y + j, z + k, Blocks.air, 0);
				}
			}
		}

		for (int var10 = 0; var10 < 5; ++var10) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 30; k++) {
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x - var10, y + j, z + k, Blocks.air, 0);
				}
			}
		}

	}
}
