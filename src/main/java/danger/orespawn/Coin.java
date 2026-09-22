package danger.orespawn;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;




































public class Coin extends EntityAnimal
{
	private float moveSpeed = 0.0F;

	public Coin(World par1World)
	{
		super(par1World);
		this.setSize(1.5F, 1.5F);
		
		this.experienceValue = 10;
		this.fireResistance = 100;
		
		
		this.tasks.addTask(0, new EntityAILookIdle(this));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	
	protected void entityInit()
	{
		super.entityInit();
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
		super.onUpdate();
	}

	public int mygetMaxHealth()
	{
		return 1;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return 0;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
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
	protected float getSoundVolume() {
		return 1.0F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}

	
	
	
	
	
	protected Item getDropItem()
	{
		return null;
	}

	private void dropItemRand(Item index, int par1)
	{
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), new ItemStack(index, par1, 0));
		
		this.worldObj.spawnEntityInWorld(var3);
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * par2 - Level of Looting used to kill this mob.
	 */
	protected void dropFewItems(boolean par1, int par2) {
		int i = this.worldObj.rand.nextInt(10);
		
		Item j = OreSpawnMain.MyEmeraldSword;
		if (i == 0) j = Items.diamond;
		if (i == 1) j = OreSpawnMain.UraniumNugget;
		if (i == 2) j = OreSpawnMain.TitaniumNugget;
		if (i == 3) j = Items.emerald;
		if (i == 4) j = OreSpawnMain.MyEmeraldAxe;
		if (i == 5) j = OreSpawnMain.MyEmeraldShovel;
		if (i == 6) j = OreSpawnMain.MyEmeraldPickaxe;
		if (i == 7) j = OreSpawnMain.MyEmeraldHoe;
		if (i == 8) j = OreSpawnMain.CoinEgg;
		
		this.dropItemRand(j, 1);
	}

	/**
	 * Initialize this creature.
	 */
	public void initCreature()
	{
		
	}
	
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return false;
	}

	
	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if ((!this.worldObj.isDaytime() ? true : false) == true) return false;
		if (this.posY < 50.0D) return false;
		Coin target = null;
		target = (Coin)this.worldObj.findNearestEntityWithinAABB(Coin.class, this.boundingBox.expand(20.0D, 8.0D, 20.0D), this);
		if (target != null)
		{
			return false;
		}
		return true;
	}

	
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		return null;
	}
}
