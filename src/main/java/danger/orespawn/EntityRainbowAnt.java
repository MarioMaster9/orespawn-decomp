package danger.orespawn;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

















public class EntityRainbowAnt extends EntityAnt
{
	public EntityRainbowAnt(World par1World)
	{
		super(par1World);
		
		this.setSize(0.1F, 0.1F);
		this.experienceValue = 0;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAIPanic(this, (double)1.4F));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 9, 1.0D));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	
	
	
	
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer == null) {
			return false;
		} else if (!(par1EntityPlayer instanceof EntityPlayerMP)) {
			return false;
		} else {
			ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
			if (var2 != null && var2.stackSize <= 0) {
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				var2 = null;
			}

			if (var2 != null) {
				return false;
			} else {
				if (par1EntityPlayer.dimension != OreSpawnMain.DimensionID3) {
					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, OreSpawnMain.DimensionID3, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(OreSpawnMain.DimensionID3), OreSpawnMain.DimensionID3, this.worldObj));
				} else {
					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, 0, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(0), 0, this.worldObj));
				}

				return true;
			}
		}
	}
}
