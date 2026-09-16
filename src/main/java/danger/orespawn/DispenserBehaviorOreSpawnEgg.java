package danger.orespawn;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;








final class DispenserBehaviorOreSpawnEgg extends BehaviorDefaultDispenseItem
{
	public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
		EnumFacing enumfacing = BlockDispenser.func_149937_b(par1IBlockSource.getBlockMetadata());
		double d0 = par1IBlockSource.getX() + (double)enumfacing.getFrontOffsetX() * 2.0D;
		double d1 = (double)((float)par1IBlockSource.getYInt() + 0.2F);
		double d2 = par1IBlockSource.getZ() + (double)enumfacing.getFrontOffsetZ() * 2.0D;
		Item it = par2ItemStack.getItem();
		if (it instanceof ItemSpawnEgg) {
			ItemSpawnEgg ise = (ItemSpawnEgg)it;
			Entity entity = ItemSpawnEgg.spawn_something(ise.my_id, par1IBlockSource.getWorld(), (double)((int)d0), (double)((int)d1), (double)((int)d2));
			if (entity instanceof EntityLivingBase && par2ItemStack.hasDisplayName())
			{
				((EntityLiving)entity).setCustomNameTag(par2ItemStack.getDisplayName());
			}
		}

		
		par2ItemStack.splitStack(1);
		return par2ItemStack;
	}
}
