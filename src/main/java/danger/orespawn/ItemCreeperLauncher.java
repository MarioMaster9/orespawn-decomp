package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;





public class ItemCreeperLauncher extends Item
{
	public ItemCreeperLauncher(int i)
	{
		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.setMaxDamage(1);
	}
	
	/**
	 * Called when the player Left Clicks (attacks) an entity.
	 * Processed before damage is done, if return value is true further processing is canceled
	 * and the entity is not attacked.
	 *
	 * @param stack The Item being used
	 * @param player The player that is attacking
	 * @param entity The entity being attacked
	 * @return True to cancel the rest of the interaction.
	 */
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		int var3;
		float f1, f2, f3;
		if (entity != null && entity instanceof EntityCreeper)
		{
			for (var3 = 0; var3 < 6; var3++)
			{
				f1 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
				f2 = 0.25F + player.worldObj.rand.nextFloat() * 6.0F;
				f3 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
				player.worldObj.spawnParticle("smoke", (double)((float)entity.posX + f1), (double)((float)entity.posY + f2), (double)((float)entity.posZ + f3), 0.0D, (double)(f2 / 4.0F), 0.0D);
				f1 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
				f2 = 0.25F + player.worldObj.rand.nextFloat() * 6.0F;
				f3 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
				player.worldObj.spawnParticle("explode", (double)((float)entity.posX + f1), (double)((float)entity.posY + f2), (double)((float)entity.posZ + f3), 0.0D, (double)(f2 / 4.0F), 0.0D);
				f1 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
				f2 = 0.25F + player.worldObj.rand.nextFloat() * 6.0F;
				f3 = player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat();
				player.worldObj.spawnParticle("reddust", (double)((float)entity.posX + f1), (double)((float)entity.posY + f2), (double)((float)entity.posZ + f3), 0.0D, (double)(f2 / 4.0F), 0.0D);
			}
			player.worldObj.playSoundAtEntity(player, "fireworks.launch", 2.0F, 1.2F);
			
			EntityLiving e = (EntityLiving)entity;
			e.addVelocity(0.0D, 4.5D, 0.0D);
		} else {
			return false;
		}
		
		if (!player.capabilities.isCreativeMode)
		{
			--stack.stackSize;
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("OreSpawn:" + this.getUnlocalizedName().substring(5));
	}
}
