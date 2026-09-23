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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;































public class Robot1 extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private float moveSpeed = 0.2F;

	public Robot1(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.5F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 5;
		this.fireResistance = 5;
		this.isImmuneToFire = true;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 10, 1.0D));
		this.tasks.addTask(2, new EntityAIMoveThroughVillage(this, (double)0.9F, false));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
		if (this.renderdata == null) {
			this.renderdata = new RenderInfo();
		}
		this.renderdata.rf1 = 0.0F;
		this.renderdata.rf2 = 0.0F;
		this.renderdata.rf3 = 0.0F;
		this.renderdata.rf4 = 0.0F;
		this.renderdata.ri1 = 0;
		this.renderdata.ri2 = 0;
		this.renderdata.ri3 = 0;
		this.renderdata.ri4 = 0;
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
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	public int mygetMaxHealth()
	{
		return 5;
	}

	
	
	public RenderInfo getRenderInfo()
	{
		return this.renderdata;
	}

	public void setRenderInfo(RenderInfo r)
	{
		this.renderdata.rf1 = r.rf1;
		this.renderdata.rf2 = r.rf2;
		this.renderdata.rf3 = r.rf3;
		this.renderdata.rf4 = r.rf4;
		this.renderdata.ri1 = r.ri1;
		this.renderdata.ri2 = r.ri2;
		this.renderdata.ri3 = r.ri3;
		this.renderdata.ri4 = r.ri4;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return 2;
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
		int i;
		super.onLivingUpdate();
		if (this.worldObj.rand.nextInt(8) == 0) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null) {
				if (this.getDistanceSqToEntity(e) < 5.0D && !this.worldObj.isRemote) {
					if (this.worldObj.rand.nextInt(18) == 1) {
						this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.5F, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
						this.setDead();
					}
				}
				for (i = 0; i < 2; i++) {
					this.worldObj.spawnParticle("smoke", this.posX, this.posY + 1.0D, this.posZ, 0.0D, 0.0D, 0.0D);
					this.worldObj.spawnParticle("lava", this.posX, this.posY + 1.0D, this.posZ, 0.0D, 0.0D, 0.0D);
				}
				this.getNavigator().tryMoveToEntityLiving(e, 1.2);
			}
		}
	}

	
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "orespawn:kyuubi_living";
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
		return "orespawn:robot1_death";
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
		return Items.gunpowder;
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return false;
	}

	
	
	
	public boolean attackEntityAsMob(Entity par1Entity) {
		return super.attackEntityAsMob(par1Entity);
	}

	protected void updateAITasks()
	{
		if (this.isDead) return;
		super.updateAITasks();
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		if (!par1DamageSource.getDamageType().equals("cactus")) {
			ret = super.attackEntityFrom(par1DamageSource, par2);
		}
		return ret;
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
		
		if (OreSpawnMain.OreSpawnUtils.isIgnoreable(par1EntityLiving)) return false;
		
		if (!this.getEntitySenses().canSee(par1EntityLiving))
		{
			
			return false;
		}
		
		if (par1EntityLiving instanceof EntityMob)
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
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(8.0D, 3.0D, 8.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		
		while (var2.hasNext())
		{
			Entity var3 = (Entity)var2.next();
			EntityLivingBase var4 = (EntityLivingBase)var3;
			
			if (this.isSuitableTarget(var4, false))
			{
				return var4;
			}
		}
		return null;
	}

	
	public final int getAttacking()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1)
	{
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		if (!this.isValidLightLevel()) return false;
		if (this.worldObj.isDaytime() == true) return false;
		
		return true;
	}
}
