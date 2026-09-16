package danger.orespawn;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.event.terraingen.TerrainGen;












public class ChunkProviderOreSpawn6 implements IChunkProvider
{
	private Random hellRNG;
	/** A NoiseGeneratorOctaves used in generating nether terrain */
	private Random random;
	private NoiseGeneratorOctaves netherNoiseGen1;
	private NoiseGeneratorOctaves netherNoiseGen2;
	private NoiseGeneratorOctaves netherNoiseGen3;
	/** Determines whether slowsand or gravel can be generated at a location */
	private NoiseGeneratorOctaves slowsandGravelNoiseGen;
	/** Determines whether something other than nettherack can be generated at a location */
	private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
	public NoiseGeneratorOctaves netherNoiseGen6;
	public NoiseGeneratorOctaves netherNoiseGen7;
	/** Is the world that the nether is getting generated. */
	private World worldObj;
	private double[] noiseField;
	//public MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
	private double[] slowsandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	/** Holds the noise used to determine whether slowsand can be generated at a location */
	private double[] netherrackExclusivityNoise = new double[256];
	//private MapGenBase netherCaveGenerator = new MapGenCavesHell();
	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;
	private static final String __OBFID = "CL_00000392";
	
	

	public ChunkProviderOreSpawn6(World p_i2005_1_, long p_i2005_2_)
	{
		this.worldObj = p_i2005_1_;
		this.hellRNG = new Random(p_i2005_2_);
		this.random = new Random(p_i2005_2_);
		this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
		this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
		this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
		this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
		this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
		this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
		this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);
		
		NoiseGenerator[] noiseGens = new NoiseGenerator[]{this.netherNoiseGen1, this.netherNoiseGen2, this.netherNoiseGen3, this.slowsandGravelNoiseGen, this.netherrackExculsivityNoiseGen, this.netherNoiseGen6, this.netherNoiseGen7};
		noiseGens = TerrainGen.getModdedNoiseGenerators(p_i2005_1_, this.hellRNG, noiseGens);
		this.netherNoiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
		this.netherNoiseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
		this.netherNoiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
		this.slowsandGravelNoiseGen = (NoiseGeneratorOctaves)noiseGens[3];
		this.netherrackExculsivityNoiseGen = (NoiseGeneratorOctaves)noiseGens[4];
		this.netherNoiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
		this.netherNoiseGen7 = (NoiseGeneratorOctaves)noiseGens[6];
	}

	public void func_147419_a(int p_147419_1_, int p_147419_2_, Block[] p_147419_3_)
	{
		byte b0 = 4;
		byte b1 = 32;
		int k = b0 + 1;
		byte b2 = 17;
		int l = b0 + 1;
		int j2;
		short short1;
		double d14;
		double d15;
		double d16;
		double d9;
		double d10;
		double d11;
		double d12;
		double d13;
		double d0;
		double d1;
		double d2;
		double d3;
		double d4;
		double d5;
		double d6;
		double d7;
		double d8;
		
		Block block = Blocks.stone;
		
		this.noiseField = this.initializeNoiseField(this.noiseField, p_147419_1_ * b0, 0, p_147419_2_ * b0, k, b2, l);

		for (int i1 = 0; i1 < b0; ++i1)
		{
			for (int j1 = 0; j1 < b0; ++j1)
			{
				for (int k1 = 0; k1 < 16; ++k1)
				{
					d0 = 0.125D;
					d1 = this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
					d2 = this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
					d3 = this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
					d4 = this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
					d5 = (this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
					d6 = (this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
					d7 = (this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
					d8 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

					for (int l1 = 0; l1 < 8; ++l1)
					{
						d9 = 0.25D;
						d10 = d1;
						d11 = d2;
						d12 = (d3 - d1) * d9;
						d13 = (d4 - d2) * d9;

						for (int i2 = 0; i2 < 4; ++i2)
						{
							j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
							short1 = 128;
							d14 = 0.25D;
							d15 = d10;
							d16 = (d11 - d10) * d14;

							for (int k2 = 0; k2 < 4; ++k2)
							{
								block = Blocks.stone;
								
								
								if (d15 > 0.0D)
								{
									block = null;
								}

								p_147419_3_[j2] = block;
								j2 += short1;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	
	
	
	public void replaceBiomeBlocks(int p_147418_1_, int p_147418_2_, Block[] p_147418_3_, byte[] meta, BiomeGenBase[] biomes)
	{
		byte b0 = 64;
		double d0 = 0.03125D;
		this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, p_147418_1_ * 16, p_147418_2_ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
		this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, p_147418_1_ * 16, 109, p_147418_2_ * 16, 16, 1, 16, d0, 1.0D, d0);
		this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, p_147418_1_ * 16, p_147418_2_ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);
		boolean flag;
		boolean flag1;
		int i1;
		int j1;
		Block block;
		Block block1;
		int l1;
		
		for (int k = 0; k < 16; k++)
		{
			for (int l = 0; l < 16; ++l)
			{
				flag = this.slowsandNoise[k + l * 16] + this.hellRNG.nextDouble() * 0.2 > 0.0D;
				flag1 = this.gravelNoise[k + l * 16] + this.hellRNG.nextDouble() * 0.2 > 0.0D;
				i1 = (int)(this.netherrackExclusivityNoise[k + l * 16] / 3.0D + 3.0D + this.hellRNG.nextDouble() * 0.25D);
				j1 = -1;
				block = Blocks.grass;
				block1 = Blocks.dirt;

				for (int k1 = 127; k1 >= 0; --k1)
				{
					l1 = (l * 16 + k) * 128 + k1;
					
					if (k1 < 127 - this.hellRNG.nextInt(5) && k1 > 0 + this.hellRNG.nextInt(5))
					{
						Block block2 = p_147418_3_[l1];
						
						if (block2 != null && block2.getMaterial() != Material.air)
						{
							if (block2 == Blocks.stone)
							{
								if (j1 == -1)
								{
									if (i1 <= 0)
									{
										block = null;
										block1 = Blocks.stone;
									}
									else if (k1 >= b0 - 4 && k1 <= b0 + 1)
									{
										block = Blocks.stone;
										block1 = Blocks.stone;
										
										if (flag1)
										{
											block = Blocks.grass;
											block1 = Blocks.dirt;
										}

										if (flag)
										{
											block = Blocks.grass;
											block1 = Blocks.dirt;
										}
									}

									if (k1 < b0 && block == Blocks.air)
									{
										block = Blocks.water;
									}

									j1 = i1;
									
									if (k1 >= b0 - 1)
									{
										p_147418_3_[l1] = block;
									}
									else
									{
										p_147418_3_[l1] = block1;
									}
								}
								else if (j1 > 0)
								{
									--j1;
									p_147418_3_[l1] = block1;
								}
							}
						}
						else
						{
							j1 = -1;
						}
					}
					else
					{
						p_147418_3_[l1] = null;
					}
				}
			}
		}
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
	{
		return this.provideChunk(p_73158_1_, p_73158_2_);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
	 * specified chunk from the map seed and chunk seed
	 */
	public Chunk provideChunk(int p_73154_1_, int p_73154_2_)
	{
		this.hellRNG.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
		Block[] ablock = new Block[32768];
		byte[] meta = new byte[ablock.length];
		
		this.func_147419_a(p_73154_1_, p_73154_2_, ablock);
		this.replaceBiomeBlocks(p_73154_1_, p_73154_2_, ablock, meta, null);
		
		Chunk chunk = new Chunk(this.worldObj, ablock, meta, p_73154_1_, p_73154_2_);
		
		OreSpawnMain.Chunker.generateOresInChunk(this.worldObj, this.worldObj.rand, p_73154_1_ * 16, p_73154_2_ * 16, chunk);
		this.addScragglyTrees(this.worldObj, p_73154_1_ * 16, p_73154_2_ * 16, chunk);
		
		chunk.generateSkylightMap();
		return chunk;
	}

	
	
    /**
     * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the
     * size.
     */
	private double[] initializeNoiseField(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_)
	{
		int k1 = 0;
		int l1 = 0;
		double[] adouble1 = new double[p_73164_6_];
		int i2;
		double d11;
		double d6;
		double d7;
		double d8;
		double d9;
		double d10;
		double d4;
		double d5;
		double d3;
		double d2;
		
		
		if (p_73164_1_ == null)
		{
			p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_];
		}

		double d0 = 684.412;
		double d1 = 2053.236;
		this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0D, 0.0D, 1.0D);
		this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, (double)100.0F, 0.0D, (double)100.0F);
		this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0 / (double)80.0F, d1 / (double)60.0F, d0 / (double)80.0F);
		this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
		this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);

		
		for (i2 = 0; i2 < p_73164_6_; ++i2)
		{
			adouble1[i2] = Math.cos((double)i2 * Math.PI * 6.0D / (double)p_73164_6_) * 2.0D;
			d2 = (double)i2;
			
			if (i2 > p_73164_6_ / 2)
			{
				d2 = (double)(p_73164_6_ - 1 - i2);
			}

			if (d2 < 4.0D)
			{
				d2 = 4.0D - d2;
				adouble1[i2] -= d2 * d2 * d2 * 10.0D;
			}
		}

		for (i2 = 0; i2 < p_73164_5_; ++i2)
		{
			for (int k2 = 0; k2 < p_73164_7_; ++k2)
			{
				d3 = (this.noiseData4[l1] + 256.0D) / 512.0D;
				
				if (d3 > 1.0D)
				{
					d3 = 1.0D;
				}

				d4 = 0.0D;
				d5 = this.noiseData5[l1] / 8000.0D;
				
				if (d5 < 0.0D)
				{
					d5 = -d5;
				}

				d5 = d5 * 3.0D - 3.0D;
				
				if (d5 < 0.0D)
				{
					d5 /= 2.0D;
					
					if (d5 < -1.0D)
					{
						d5 = -1.0D;
					}

					d5 /= 1.4;
					d5 /= 2.0D;
					d3 = 0.0D;
				}
				else
				{
					if (d5 > 1.0D)
					{
						d5 = 1.0D;
					}

					d5 /= 6.0D;
				}

				d3 += 0.5D;
				d5 = d5 * (double)p_73164_6_ / 16.0D;
				++l1;

				for (int j2 = 0; j2 < p_73164_6_; ++j2)
				{
					d6 = 0.0D;
					d7 = adouble1[j2];
					d8 = this.noiseData2[k1] / 512.0D;
					d9 = this.noiseData3[k1] / 512.0D;
					d10 = (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D;
					
					if (d10 < 0.0D)
					{
						d6 = d8;
					}
					else if (d10 > 1.0D)
					{
						d6 = d9;
					}
					else
					{
						d6 = d8 + (d9 - d8) * d10;
					}

					d6 -= d7;
					
					
					if (j2 > p_73164_6_ - 4)
					{
						d11 = (double)((float)(j2 - (p_73164_6_ - 4)) / 3.0F);
						d6 = d6 * (1.0D - d11) + -10.0D * d11;
					}

					if ((double)j2 < d4)
					{
						d11 = (d4 - (double)j2) / 4.0D;
						
						if (d11 < 0.0D)
						{
							d11 = 0.0D;
						}

						if (d11 > 1.0D)
						{
							d11 = 1.0D;
						}

						d6 = d6 * (1.0D - d11) + -10.0D * d11;
					}

					p_73164_1_[k1] = d6;
					++k1;
				}
			}
		}

		return p_73164_1_;
	}
	
	/**
	 * Checks to see if a chunk exists at x, y
	 */
	public boolean chunkExists(int p_73149_1_, int p_73149_2_)
	{
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	public void populate(IChunkProvider p_73153_1_, int par2, int par3)
	{
		BlockFalling.fallInstantly = false;
		
		
		int var4 = par2 * 16;
		int var5 = par3 * 16;
		BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		
		var6.decorate(this.worldObj, this.worldObj.rand, var4, var5);
		
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.worldObj.rand);
		
		
	}
	
	/**
	 * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
	 * Return true if all chunks have been saved.
	 */
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
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
		return "ChaosDimension";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the given location.
	 */
	public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
	{
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
		return biomegenbase.getSpawnableList(p_73155_1_);
	}

	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		return null;
	}

	public int getLoadedChunkCount()
	{
		return 0;
	}

	
	
	
	public void recreateStructures(int p_82695_1_, int p_82695_2_) {}

	
	
	public void addScragglyTrees(World world, int chunkX, int chunkZ, Chunk chunk)
	{
		int howmany = 1 + world.rand.nextInt(5);
		if (world.rand.nextInt(4) != 0) return;
		
		
		
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
			for (int posY = 120; posY > 50; --posY)
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
			if(this.random.nextInt(2) == 1){
				bid = OreSpawnMain.getBlockIDInChunk(chunk, x, y+1, z);
				if(bid == Blocks.air){
					OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x, y+1, z, OreSpawnMain.MyAppleLeaves, 0);
				}
			}
		}
	}

	
	
	
	public void ScragglyTreeWithBranches(World world, int x, int y, int z, Chunk chunk)
	{
		int i, j, k, ix, iy, iz;
		int m, n;
		Block bid;
		
		
		i = 1 + this.random.nextInt(3);
		j = i + this.random.nextInt(12);

		for(k=0;k<i;k++){
			bid = OreSpawnMain.getBlockIDInChunk(chunk, x, y+k, z);
			if(k >= 1 && bid != Blocks.air && bid != Blocks.log && bid != OreSpawnMain.MyAppleLeaves){
				return; //STOP ran into something...
			}
			OreSpawnMain.setBlockIDWithMetadataInChunk(chunk, x, y + k, z, Blocks.log, 0);
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
