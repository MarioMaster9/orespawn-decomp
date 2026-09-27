package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;



















public class CreepingHorror extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.25F;

	
	public CreepingHorror(World par1World)
	{
		super(par1World);
		this.setSize(0.75F, 0.5F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 5;
		this.fireResistance = 10;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, (double)1.35F));
		this.tasks.addTask(2, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(3, new MyEntityAIWanderALot(this, 10, 1.0D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.CreepingHorror_stats.attack);
	}

	protected void entityInit()
	{
		super.entityInit();
	}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.CreepingHorror_stats.health;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.CreepingHorror_stats.defense;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	
	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (this.isNoDespawnRequired()) return;
		
		
		long t = this.worldObj.getWorldTime();
		t %= 24000L;
		if (t > 11000L) return;
		if (this.worldObj.rand.nextInt(500) == 1) this.setDead();
	}

	
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "orespawn:creepinghorror_living";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:creepinghorror_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:creepinghorror_dead";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.65F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}

	
	
	
	
	
	protected Item getDropItem()
	{
		int i = this.worldObj.rand.nextInt(3);
		if (i == 0) return Items.rotten_flesh;
		if (i == 1) return Items.bone;
		return Items.string;
	}

	
	protected void updateAITasks()
	{
		if (this.isDead) return;
		super.updateAITasks();
		if (this.worldObj.rand.nextInt(200) == 1) this.setRevengeTarget(null);
		if (this.worldObj.rand.nextInt(5) == 1) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null) {
				this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
				if (this.getDistanceSqToEntity(e) < 5.0D)
				{
					if (this.rand.nextInt(12) == 0 || this.rand.nextInt(14) == 1)
					{
						this.attackEntityAsMob(e);
					}
				}
			}
		}
		
	}
	
	
	
	
	
	
	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
	{
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
		if (par1EntityLiving instanceof CreepingHorror)
		{
			return false;
		}
		if (par1EntityLiving instanceof RockBase)
		{
			return false;
		}
		if (par1EntityLiving instanceof EnderReaper)
		{
			return false;
		}
		if (par1EntityLiving instanceof LeafMonster)
		{
			return false;
		}
		if (par1EntityLiving instanceof Dragon)
		{
			return false;
		}
		if (par1EntityLiving instanceof TerribleTerror)
		{
			return false;
		}
		if (par1EntityLiving instanceof LurkingTerror)
		{
			return false;
		}
		if (par1EntityLiving instanceof PitchBlack)
		{
			return false;
		}
		if (par1EntityLiving instanceof Firefly)
		{
			return false;
		}
		if (par1EntityLiving instanceof Island)
		{
			return false;
		}
		if (par1EntityLiving instanceof IslandToo)
		{
			return false;
		}
		
		if (par1EntityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (p.capabilities.isCreativeMode == true) {
				return false;
			}
		}

		return true;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0D, 4.0D, 16.0D));
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

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (!this.isValidLightLevel()) return false;
		if (this.worldObj.isDaytime()) return false;
		if (this.worldObj.provider.dimensionId != OreSpawnMain.DimensionID6) {
			if (this.posY > 15.0D) return false;
		}
		return true;
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		if (!this.worldObj.isDaytime()) return false;
		return true;
	}
}
