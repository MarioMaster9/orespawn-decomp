package danger.orespawn;

import java.util.List;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;












public class EntityAnt extends EntityAnimal
{
	public double moveSpeed = (double)0.15F;
	private static final ResourceLocation texture1 = new ResourceLocation("orespawn", "ant.png");
	private static final ResourceLocation texture2 = new ResourceLocation("orespawn", "red_ant.png");
	private static final ResourceLocation texture3 = new ResourceLocation("orespawn", "rainbow_ant.png");
	private static final ResourceLocation texture4 = new ResourceLocation("orespawn", "unstableant.png");
	private static final ResourceLocation texture5 = new ResourceLocation("orespawn", "termite.png");

	public EntityAnt(World par1World)
	{
		super(par1World);
		this.setSize(0.1F, 0.1F);
		this.experienceValue = 0;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAIPanic(this, 1.4));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 9, 1.0D));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	public ResourceLocation getTexture(EntityAnt a) {
		if (a instanceof EntityRedAnt) return texture2;
		if (a instanceof EntityRainbowAnt) return texture3;
		if (a instanceof EntityUnstableAnt) return texture4;
		if (a instanceof Termite) return texture5;
		return texture1;
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		super.onUpdate();
	}


	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer == null) return false;





		if (!(par1EntityPlayer instanceof EntityPlayerMP)) return false;


		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (var2 != null) {
			if (var2.stackSize <= 0) {
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				var2 = null;
			}
		}
		if (var2 != null) {
			return false;
		}


		if (par1EntityPlayer.dimension != OreSpawnMain.DimensionID) {
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, OreSpawnMain.DimensionID, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(OreSpawnMain.DimensionID), OreSpawnMain.DimensionID, this.worldObj));
		}
		else {
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, 0, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(0), 0, this.worldObj));
		}


		return true;
	}


	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}


	public int mygetMaxHealth()
	{
		return 1;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return null;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return null;
	}


	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.0F;
	}



	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4) {}



	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
	protected void dropFewItems(boolean par1, int par2) {}







	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return true;
	}




	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		if (this.findBuddies() > 4) return false;
		return true;
	}

	private int findBuddies()
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityAnt.class, this.boundingBox.expand(20.0D, 10.0D, 20.0D));
		return var5.size();
	}

	public void updateAITick()
	{
		if (this.worldObj.rand.nextInt(200) == 1) this.setRevengeTarget(null);
		super.updateAITick();
	}
}