package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;












public class Frog extends EntityAnimal
{
	private GenericTargetSorter TargetSorter = null;
	public double moveSpeed = (double)0.1F;
	private int singing = 0;
	private int jumpcount = 0;

	public Frog(World par1World) {
		super(par1World);
		this.setSize(0.75F, 0.75F);
		this.experienceValue = 5;
		this.TargetSorter = new GenericTargetSorter(this);
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.4));
		this.tasks.addTask(2, new MyEntityAIWander(this, 1.0F));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
	}


	public boolean canBreatheUnderwater()
	{
		return true;
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	public int getSinging()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public void setSinging(int par1)
	{
		this.dataWatcher.updateObject(20, (byte)par1);
	}


	private void jumpAround()
	{
		this.motionY += (double)(0.75F + Math.abs(this.worldObj.rand.nextFloat() * 0.55F));
		this.posY += (double)0.35F;
		float f = 0.7F + Math.abs(this.worldObj.rand.nextFloat() * 0.75F);
		float d = (float)Math.toRadians((double)this.rotationYaw);
		this.motionX -= (double)f * Math.sin((double)d);
		this.motionZ += (double)f * Math.cos((double)d);
		this.isAirBorne = true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		super.onUpdate();
		if(!worldObj.isRemote){
			if(singing != 0){
				singing--;
				if(singing <= 0){
					setSinging(0);
				}
			}
			if(jumpcount > 0)jumpcount--;
			if(jumpcount == 0){
				if(worldObj.rand.nextInt(70) == 1){
					jumpAround();
					jumpcount = 50;
				}
			}
		}
		
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer != null) {
			if (par1EntityPlayer.isSneaking()) {
				if (par1EntityPlayer.inventory.getCurrentItem() == null) {
					World world = par1EntityPlayer.worldObj;
					this.setDead();
					par1EntityPlayer.worldObj.playSoundAtEntity(par1EntityPlayer, "random.explode", 1.0F, world.rand.nextFloat() * 0.2F + 0.9F);
					if (!world.isRemote) {
						if (world.rand.nextInt(2) == 0) {
							Boyfriend ent = null;
							ent = (Boyfriend)spawnCreature(world, "Boyfriend", this.posX, this.posY + 0.01, this.posZ);
							if (ent != null) ent.setPrince(1 + world.rand.nextInt(2));
						} else {
							Girlfriend ent = null;
							ent = (Girlfriend)spawnCreature(world, "Girlfriend", this.posX, this.posY + 0.01, this.posZ);
							if (ent != null) ent.setPrincess(1 + world.rand.nextInt(2));
						}
					} else {
						for (int var3 = 0; var3 < 16; var3++)
						{
							world.spawnParticle("smoke", (double)((float)this.posX + world.rand.nextFloat() - world.rand.nextFloat()), (double)((float)this.posY + world.rand.nextFloat()), (double)((float)this.posZ + world.rand.nextFloat() - world.rand.nextFloat()), 0.0D, 0.0D, 0.0D);
							world.spawnParticle("explode", (double)((float)this.posX + world.rand.nextFloat() - world.rand.nextFloat()), (double)((float)this.posY + world.rand.nextFloat()), (double)((float)this.posZ + world.rand.nextFloat() - world.rand.nextFloat()), 0.0D, 0.0D, 0.0D);
							world.spawnParticle("reddust", (double)((float)this.posX + world.rand.nextFloat() - world.rand.nextFloat()), (double)((float)this.posY + world.rand.nextFloat()), (double)((float)this.posZ + world.rand.nextFloat() - world.rand.nextFloat()), 0.0D, 0.0D, 0.0D);
						}
					}
				}
			}
		}

		return false;
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
		return 8;
	}


	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		if(!worldObj.isRemote){
			if(worldObj.rand.nextInt(2) == 0)return null;
			singing = 35;
			setSinging(singing);
		}
		return "orespawn:frog";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:scorpion_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:big_splat";
	}


	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.7F;
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float par1) {}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
	 * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	protected void updateFallState(double par1, boolean par3) {}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		
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
		int i;
		for (i = 0; i < 4; i++) {
			this.dropItemRand(Items.slime_ball, 1);
		}
	}



	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
		if (par1Entity.isDead) this.heal(1.0F);
		return var4;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		
		ret = super.attackEntityFrom(par1DamageSource, par2);
		if (!this.worldObj.isRemote && this.jumpcount <= 0) {
			this.jumpAround();
			this.jumpcount = 25;
		}
		return ret;
	}





	public boolean canSeeTarget(double pX, double pY, double pZ)
	{
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.25D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}










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

	private int findBuddies()
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(Frog.class, this.boundingBox.expand(20.0D, 8.0D, 20.0D));
		return var5.size();
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		if (!this.worldObj.isDaytime()) return false;
		
		if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID5) {
			if (this.worldObj.rand.nextInt(20) != 1) return false;
		}
		if (this.findBuddies() > 5) return false;
		return true;
	}

	protected void updateAITasks()
	{
		int xdir = 1;
		int zdir = 1;
		int unused1, unused2;
		int keep_trying = 50;
		int unused3, unused4, unused5, unused6;
		Block unused7;
		
		if (this.isDead) return;
		super.updateAITasks();
		if (this.rand.nextInt(12) == 0 && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
		{
			
			EntityLivingBase e = null;
			e = this.findSomethingToAttack();
			if (e != null)
			{
				
				this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
				if (this.getDistanceSqToEntity(e) < 6.0D) {
					this.attackEntityAsMob(e);
				}
			}
		}
		
	}




	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
	{
		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) return false;
		
		if (par1EntityLiving == null)
		{
			return false;
		}
		if (par1EntityLiving == this)
		{
			return false;
		}
		if (!par1EntityLiving.isEntityAlive())
		{
			
			return false;
		}
		if (!this.getEntitySenses().canSee(par1EntityLiving))
		{
			
			return false;
		}
		if (par1EntityLiving instanceof EntityAnt)
		{
			return true;
		}
		if (par1EntityLiving instanceof EntityButterfly)
		{
			return true;
		}
		if (par1EntityLiving instanceof Cricket)
		{
			return true;
		}
		if (par1EntityLiving instanceof EntityMosquito)
		{
			return true;
		}
		if (par1EntityLiving instanceof Firefly)
		{
			return true;
		}
		if (par1EntityLiving instanceof WormSmall)
		{
			return true;
		}
		
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand((double)8.0F, 3.0D, 8.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityLivingBase var4 = null;

		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (EntityLivingBase)var3;
			
			if (this.isSuitableTarget(var4, false))
			{
				return var4;
			}
		}
		return null;
	}





	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6)
	{
		Entity var8 = null;
		var8 = EntityList.createEntityByName(par1, par0World);
		if (var8 != null) {
			
			
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			
			
			par0World.spawnEntityInWorld(var8);
			
			((EntityLiving)var8).playLivingSound();
		}
		return var8;
	}
}
