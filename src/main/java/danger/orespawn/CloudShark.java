package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;





























public class CloudShark extends EntityMob
{
	private ChunkCoordinates currentFlightTarget = null;
	private GenericTargetSorter TargetSorter = null;

	public CloudShark(World par1World)
	{
		super(par1World);
		this.setSize(1.0F, 0.75F);
		this.experienceValue = 5;
		this.isImmuneToFire = false;
		this.fireResistance = 5;
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.3F);
		
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.CloudShark_stats.attack);
	}

	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);
		return flag;
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		if (this.worldObj.isDaytime()) return false;
		return true;
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.25F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound() {
		return "splash";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return "orespawn:little_splat";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "orespawn:big_splat";
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed() {
		return true;
	}

	protected void collideWithEntity(Entity par1Entity) {}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.CloudShark_stats.health;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled() {
		return true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();
		
		this.motionY *= 0.6;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.CloudShark_stats.defense;
	}

	
	
	
	public boolean canSeeTarget(double pX, double pY, double pZ) {
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	
	protected void updateAITasks()
	{
		int xdir = 1;
		int zdir = 1;
		int unused1, unused2;
		int keep_trying = 50;
		int unused3, unused4, unused5, unused6;
		Block bid;
		int updown = 0;
		
		if (this.isDead) return;
		super.updateAITasks();
		
		if (this.currentFlightTarget == null) {
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}
		if ((int)this.posY < 120) updown = 2;
		if ((int)this.posY > 140) updown = -2;
		if (this.rand.nextInt(300) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 2.1F)
		{
			bid = Blocks.stone;
			while (bid != Blocks.air && keep_trying != 0) {
				zdir = this.rand.nextInt(10) + 8;
				xdir = this.rand.nextInt(10) + 8;
				if (this.rand.nextInt(2) == 0) zdir = -zdir;
				if (this.rand.nextInt(2) == 0) xdir = -xdir;
				this.currentFlightTarget.set((int)this.posX + xdir, (int)this.posY + this.rand.nextInt(5) - 2 + updown, (int)this.posZ + zdir);
				bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
				if (bid == Blocks.air) {
					if (!this.canSeeTarget((double)this.currentFlightTarget.posX, (double)this.currentFlightTarget.posY, (double)this.currentFlightTarget.posZ)) {
						bid = Blocks.stone;
					}
				}
				keep_trying--;
			}
		}
		
		if (this.rand.nextInt(9) == 2)
		{
			
			EntityLivingBase e = null;
			e = this.findSomethingToAttack();
			if (e != null)
			{
				
				this.currentFlightTarget.set((int)e.posX, (int)e.posY, (int)e.posZ);
				if (this.getDistanceSqToEntity(e) < 9.0D) {
					this.attackEntityAsMob(e);
				}
			}
		}
		
		
		double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
		double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
		double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
		this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.30000000149011613;
		this.motionY += (Math.signum(var3) * (double)0.7F - this.motionY) * 0.20000000149011612;
		this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.30000000149011613;
		float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = 1.0F;
		this.rotationYaw += var8 / 4.0F;
		
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking() {
		return true;
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
	 * Return whether this entity should NOT trigger a pressure plate or a tripwire.
	 */
	public boolean doesEntityNotTriggerPressurePlate() {
		return false;
	}

	
	
    /**
     * Called when the entity is attacked.
     */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = super.attackEntityFrom(par1DamageSource, par2);
		Entity e = par1DamageSource.getEntity();
		if (e != null && this.currentFlightTarget != null)
		{
			this.currentFlightTarget.set((int)e.posX, (int)e.posY, (int)e.posZ);
		}
		return ret;
	}

	
	
	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		return true;
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
		if (par1EntityLiving instanceof RockBase)
		{
			return false;
		}
		if (par1EntityLiving instanceof EntityAnt)
		{
			return false;
		}
		if (par1EntityLiving instanceof EntityButterfly)
		{
			return true;
		}
		if (par1EntityLiving instanceof Cockateil)
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
		if (par1EntityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (p.capabilities.isCreativeMode != true) {
				return true;
			}
		}
		if (par1EntityLiving instanceof GoldFish)
		{
			return true;
		}
		if (par1EntityLiving instanceof CliffRacer)
		{
			return true;
		}
		
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(12.0D, 10.0D, 12.0D));
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

	
	
	
	
	protected Item getDropItem()
	{
		int i = this.worldObj.rand.nextInt(3);
		if (i == 0) return Items.paper;
		if (i == 1) return Items.string;
		if (i == 2) return Items.bone;
		return null;
	}

	
	public boolean canBreatheUnderwater()
	{
		return true;
	}
}
