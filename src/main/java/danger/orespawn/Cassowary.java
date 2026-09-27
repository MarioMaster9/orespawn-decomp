package danger.orespawn;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;






















public class Cassowary extends EntityAnimal
{
	private float moveSpeed = 0.25F;

	public Cassowary(World par1World)
	{
		super(par1World);
		
		this.setSize(0.5F, 1.2F);
		this.moveSpeed = 0.25F;
		this.fireResistance = 100;
		this.experienceValue = 5;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityMob.class, 8.0F, 1.0D, (double)1.4F));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 1.0D, (double)1.4F));
		this.tasks.addTask(4, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityLiving.class, 12.0F));
		this.tasks.addTask(6, new MyEntityAIWander(this, 1.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)8.0F);
	}

	protected void entityInit()
	{
		super.entityInit();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	
	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	
	public boolean canBreatheUnderwater()
	{
		return false;
	}

	public int mygetMaxHealth()
	{
		return 10;
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
		return "orespawn:duck_hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:duck_hurt";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	
	
	
	protected Item getDropItem()
	{
		return Items.chicken;
	}

	
	
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = 0;
		var3 = this.rand.nextInt(3);
		var3 += 2;
		for (int var4 = 0; var4 < var3; var4++)
		{
			this.dropItem(Items.chicken, 1);
		}
		
	}
	
	
	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{
		if (this.worldObj.rand.nextInt(200) == 1) this.setRevengeTarget(null);
		super.updateAITick();
	}

	public boolean getCanSpawnHere()
	{
		if (!this.worldObj.isDaytime()) return false;
		return true;
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn()
	{
		if (this.isChild()) {
			this.func_110163_bv();
			return false;
		}
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}

	
	
	public Cassowary spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new Cassowary(this.worldObj);
	}

	
	
	
	public boolean isWheat(ItemStack par1ItemStack)
	{
		return par1ItemStack != null && par1ItemStack.getItem() == Items.apple;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return par1ItemStack.getItem() == OreSpawnMain.MyCrystalApple;
	}
}
