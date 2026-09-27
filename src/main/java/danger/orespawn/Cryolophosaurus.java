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




















public class Cryolophosaurus extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.25F;

	
	
	public Cryolophosaurus(World par1World)
	{
		super(par1World);
		this.setSize(0.75F, 0.75F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 10;
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Cryolophosaurus_stats.attack);
	}

	protected void entityInit()
	{
		super.entityInit();
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.Cryolophosaurus_stats.health;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.Cryolophosaurus_stats.defense;
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
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		if (this.rand.nextInt(6) == 0) {
			return "orespawn:cryo_living";
		}
			return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:cryo_hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:cryo_death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
     */
	protected float getSoundVolume() {
		return 0.75F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}

	
	
	
	
	
	protected Item getDropItem()
	{
		int i = this.worldObj.rand.nextInt(10);
		if (i == 0) return Items.chicken;
		if (i == 1) return OreSpawnMain.UraniumNugget;
		if (i == 2) return OreSpawnMain.TitaniumNugget;
		return null;
	}

	
	
	/**
	 * Initialize this creature.
	 */
	public void initCreature() {}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return false;
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
		if (par1EntityLiving instanceof Alosaurus)
		{
			return false;
		}
		if (par1EntityLiving instanceof TRex)
		{
			return false;
		}
		if (par1EntityLiving instanceof Cryolophosaurus)
		{
			return false;
		}
		if (par1EntityLiving instanceof Ghost)
		{
			return false;
		}
		if (par1EntityLiving instanceof GhostSkelly)
		{
			return false;
		}
		if (par1EntityLiving instanceof CaveFisher)
		{
			return false;
		}
		if (par1EntityLiving instanceof GammaMetroid)
		{
			return false;
		}
		
		
		
		
		
		if (par1EntityLiving instanceof EntityButterfly)
		{
			return false;
		}
		if (par1EntityLiving instanceof Firefly)
		{
			return false;
		}
		if (par1EntityLiving instanceof EntityMosquito)
		{
			return false;
		}
		if (par1EntityLiving instanceof RockBase)
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
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(9.0D, 2.0D, 9.0D));
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
		if (this.worldObj.isDaytime() == true && this.posY > 50.0D) return false;
		
		
		
		return true;
	}
}
