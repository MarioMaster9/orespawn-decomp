package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;










public class ItemAppleSeed extends Item
{
	public ItemAppleSeed(int i)
	{
		this.maxStackSize = 16;
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if (!world.isRemote)
		{
			
			Block bid = world.getBlock(x, y, z);
			if (bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.farmland) {
				return false;
			}

			if (this == OreSpawnMain.MyAppleSeed) {
				this.makeTree(world, x, y, z, OreSpawnMain.MyAppleLeaves, null);
			} else if (this == OreSpawnMain.MyCherrySeed) {
				this.makeTree(world, x, y, z, OreSpawnMain.MyCherryLeaves, null);
			} else {
				this.makeTree(world, x, y, z, OreSpawnMain.MyPeachLeaves, null);
			}
		}

		
		
		
		
		if (!par2EntityPlayer.capabilities.isCreativeMode)
		{
			--par1ItemStack.stackSize;
		}

		return true;
	}

	public void makeTree(World world, int x, int y, int z, Block blkid, Chunk chunk)
	{
		int i, j, k, width;
		int h1, h2, h3, h4, h5, w1, w2;
		Block bid;
		//Only grow on grass or dirt
		bid = world.getBlock(x, y, z);
		if(bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.farmland){
			return;
		}
		
		h1 = 12;
		h2 = 6;
		h3 = 9;
		h4 = 6;
		h5 = 14;
		w1 = 5;
		w2 = 3;
		if(blkid == OreSpawnMain.MyPeachLeaves){
			h1 = 10;
			h2 = 5;
			h3 = 7;
			h4 = 5;
			h5 = 12;
			w1 = 4;
			w2 = 2;
		}
		if(blkid == OreSpawnMain.MyCherryLeaves){
			h1 = 8;
			h2 = 3;
			h3 = 5;
			h4 = 3;
			h5 = 10;
			w1 = 3;
			w2 = 1;
		}
		
		//Grow up
		for(j=1;j<h1;j++){
			world.setBlock(x, y+j, z, Blocks.log, 0, 2);
		}
		
		//Now for some branches
		for(j=1;j<w1;j++) OreSpawnMain.setBlockSuperFast(world, x+j, y+h2, z, Blocks.log, 0, 2, chunk);
		for(j=1;j<w1;j++) OreSpawnMain.setBlockSuperFast(world, x-j, y+h2, z, Blocks.log, 0, 2, chunk);
		for(j=1;j<w1;j++) OreSpawnMain.setBlockSuperFast(world, x, y+h2, z+j, Blocks.log, 0, 2, chunk);
		for(j=1;j<w1;j++) OreSpawnMain.setBlockSuperFast(world, x, y+h2, z-j, Blocks.log, 0, 2, chunk);
		
		//And a few little branches
		for(j=1;j<w2;j++) OreSpawnMain.setBlockSuperFast(world, x+j, y+h3, z, Blocks.log, 0, 2, chunk);
		for(j=1;j<w2;j++) OreSpawnMain.setBlockSuperFast(world, x-j, y+h3, z, Blocks.log, 0, 2, chunk);
		for(j=1;j<w2;j++) OreSpawnMain.setBlockSuperFast(world, x, y+h3, z+j, Blocks.log, 0, 2, chunk);
		for(j=1;j<w2;j++) OreSpawnMain.setBlockSuperFast(world, x, y+h3, z-j, Blocks.log, 0, 2, chunk);
		
		//Now fill with leaves, and then let it decay however it wants
		for(i=h4; i<h5; i++){
			width = 6;
			if(i > 8)width = 5;
			if(i > 10)width = 4;
			if(blkid != OreSpawnMain.MyAppleLeaves)width--;
			for(j=-width; j<=width; j++){
				for(k=-width; k<=width; k++){
					bid = world.getBlock(x+k, y+i, z+j);
					if(bid == Blocks.air)OreSpawnMain.setBlockSuperFast(world, x+k, y+i, z+j, blkid, 0, 2, chunk);
				}
			}
		}
		//It actually ends up looking fairly nice...
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
