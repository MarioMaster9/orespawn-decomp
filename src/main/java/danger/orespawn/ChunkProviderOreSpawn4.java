package danger.orespawn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;

public class ChunkProviderOreSpawn4 implements IChunkProvider
{
	private World worldObj;
	private Random random;
	private final Block[] cachedBlockIDs = new Block[256];
	private final byte[] cachedBlockMetadata = new byte[256];

	public ChunkProviderOreSpawn4(World par1World, long par2, boolean par4)
	{
		this.worldObj = par1World;
		this.random = new Random(par2);

		for (int j = 0; j < 8; j++)
		{
			if (j == 0) this.cachedBlockIDs[j] = Blocks.bedrock;
			else if (j == 7) this.cachedBlockIDs[j] = Blocks.grass;
			else this.cachedBlockIDs[j] = Blocks.dirt;
		}
	}
	
	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	public Chunk loadChunk(int par1, int par2)
	{
		return this.provideChunk(par1, par2);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
	 * specified chunk from the map seed and chunk seed
	 */
	public Chunk provideChunk(int par1, int par2)
	{
		Chunk chunk = new Chunk(this.worldObj, par1, par2);
		int l;
		
		for (int k = 0; k < this.cachedBlockIDs.length; k++)
		{
			Block block = this.cachedBlockIDs[k];
			
			if (block != null)
			{
				l = k >> 4;
				ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[l];
				
				if (extendedblockstorage == null)
				{
					extendedblockstorage = new ExtendedBlockStorage(k, !this.worldObj.provider.hasNoSky);
					chunk.getBlockStorageArray()[l] = extendedblockstorage;
				}

				for (int i1 = 0; i1 < 16; ++i1)
				{
					for (int j1 = 0; j1 < 16; ++j1)
					{
						extendedblockstorage.func_150818_a(i1, k & 15, j1, block);
						extendedblockstorage.setExtBlockMetadata(i1, k & 15, j1, this.cachedBlockMetadata[k]);
					}
				}
			}
		}

		this.addScragglyTrees(this.worldObj, par1 * 16, par2 * 16, chunk);
		
		chunk.generateSkylightMap();
		return chunk;
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int par1, int par2)
	{
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
	{
		int k = par2 * 16;
		int l = par3 * 16;
		
		this.random.setSeed(this.worldObj.getSeed());
		long i1 = this.random.nextLong() / 2L * 2L + 1L;
		long j1 = this.random.nextLong() / 2L * 2L + 1L;
		this.random.setSeed((long)par2 * i1 + (long)par3 * j1 ^ this.worldObj.getSeed());
	
	
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
	 * Return true if all chunks have been saved.
	 */
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
	{
		return true;
	}

	/**
	 * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
	 * unimplemented.
	 */
	public void saveExtraData() {}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
	 */
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	public boolean canSave()
	{
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	public String makeString()
	{
		return "DangerDimension";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the given location.
	 */
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
	{
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
		return biomegenbase.getSpawnableList(par1EnumCreatureType);
	}

	
	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		return null;
	}

	public int getLoadedChunkCount()
	{
		return 0;
	}

	
	public void recreateStructures(int par1, int par2)
	{
	}

	
	
	public void addScragglyTrees(World world, int chunkX, int chunkZ, Chunk chunk)
	{
		int howmany = 1 + this.random.nextInt(10);
		
		
		
		if (OreSpawnMain.LessLag == 1) {
			howmany /= 2;
		}
		if (OreSpawnMain.LessLag == 2) {
			howmany /= 4;
		}
		if (howmany == 0) return;
		
		for (int i = 0; i < howmany; i++) {
			int posX = 2 + chunkX + this.random.nextInt(12);
			int posZ = 2 + chunkZ + this.random.nextInt(12);
			for (int posY = 20; posY > 2; --posY)
			{
				if (OreSpawnMain.getBlockIDInChunk(chunk, posX, posY - 1, posZ) == Blocks.grass)
				{
					this.ScragglyTreeWithBranches(world, posX, posY, posZ, chunk);
					break;
				}
			}
		}
	}

	public void makeScragglyBranch(World world, int x, int y, int z, int len, int biasx, int biasz, Chunk chunk)
	{
		int unused1, unused2, k, ix, iy, iz;
		int m, n;
		Block bid;
		
		for(k=0;k<len;k++){
			ix = this.random.nextInt(2) - this.random.nextInt(2) + biasx;
			iz = this.random.nextInt(2) - this.random.nextInt(2) + biasz;
			if(ix > 1)ix = 1;
			if(ix < -1)ix = -1;
			if(iz > 1)iz = 1;
			if(iz < -1)iz = -1;
			iy = (this.random.nextInt(3))>0?1:0;
			x += ix;
			z += iz;
			y += iy;
			bid = OreSpawnMain.getBlockIDInChunk(chunk, x, y, z);
			if(bid != Blocks.air && bid != Blocks.log && bid != OreSpawnMain.MyAppleLeaves){
				return; //STOP ran into something...
			}
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x, y, z, Blocks.log, 0);

			for(m=-1;m<2;m++){
				for(n=-1;n<2;n++){
					if(this.random.nextInt(2) == 1){
						bid = OreSpawnMain.getBlockIDInChunk(chunk, x+m, y, z+n);
						if(bid == Blocks.air){
							OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x+m, y, z+n, OreSpawnMain.MyAppleLeaves, 0);
						}
					}
				}
			}
			if (this.random.nextInt(2) == 1){
				bid = OreSpawnMain.getBlockIDInChunk(chunk, x, y+1, z);
				if (bid == Blocks.air){
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x, y+1, z, OreSpawnMain.MyAppleLeaves, 0);
				}
			}
		}
	}

	/*
	 *
	 */
	public void ScragglyTreeWithBranches(World world, int x, int y, int z, Chunk chunk)
	{
		int i, j, k, ix, iy, iz;
		int m, n;
		Block bid;
		
		
		i = 1 + this.random.nextInt(3);
		j = i + this.random.nextInt(12);

		for(k=0;k<i;k++){
			bid = OreSpawnMain.getBlockIDInChunk(chunk, x, y+k, z);
			if (k >= 1 && bid != Blocks.air && bid != Blocks.log && bid != OreSpawnMain.MyAppleLeaves){
				return; //STOP ran into something...
			}
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x, y+k, z, Blocks.log, 0);
		}
		y+=(i-1);

		for(k = i;k < j;k++){
			ix = this.random.nextInt(2) - this.random.nextInt(2);
			iz = this.random.nextInt(2) - this.random.nextInt(2);
			iy = (this.random.nextInt(4))>0?1:0;
			x += ix;
			z += iz;
			y += iy;
			bid = OreSpawnMain.getBlockIDInChunk(chunk, x, y, z);
			if(bid != Blocks.air && bid != Blocks.log && bid != OreSpawnMain.MyAppleLeaves){
				break; //STOP ran into something...
			}
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x, y, z, Blocks.log, 0);
			if(this.random.nextInt(4) == 1){
				this.makeScragglyBranch(world, x, y, z, this.random.nextInt(1+j-k), this.random.nextInt(2) - this.random.nextInt(2), this.random.nextInt(2) - this.random.nextInt(2), chunk);
			}

			for(m=-1;m<2;m++){
				for(n=-1;n<2;n++){
					if(this.random.nextInt(2) == 1){
						bid = OreSpawnMain.getBlockIDInChunk(chunk, x+m, y, z+n);
						if(bid == Blocks.air){
							OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x+m, y, z+n, OreSpawnMain.MyAppleLeaves, 0);
						}
					}
				}
			}
			if(this.random.nextInt(2) == 1){
				bid = OreSpawnMain.getBlockIDInChunk(chunk, x, y+1, z);
				if(bid == Blocks.air){
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x, y+1, z, OreSpawnMain.MyAppleLeaves, 0);
				}
			}
		}
	}
}
