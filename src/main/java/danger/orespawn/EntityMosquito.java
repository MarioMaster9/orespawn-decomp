package danger.orespawn;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;




public class EntityMosquito extends EntityAmbientCreature
{
	private ChunkCoordinates currentFlightTarget = null;

	public EntityMosquito(World par1World)
	{
		super(par1World);
		
		this.setSize(0.2F, 0.2F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 5;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.1F);
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
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
     */
	protected float getSoundPitch()
	{
		return 1.5F;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "orespawn:mosquito";
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
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return false;
	}

	protected void collideWithEntity(Entity par1Entity) {}

	protected void collideWithNearbyEntities() {}

	public int mygetMaxHealth()
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
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();
		this.motionY *= (double)0.6F;
		
		
	}
	
	protected void updateAITasks()
	{
		Block bid;
		int keep_trying = 50;
		if (this.isDead) return;
		super.updateAITasks();
		
		
		if (this.currentFlightTarget == null) {
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}
		if (this.rand.nextInt(20) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 3.0F)
		{
			EntityPlayer target = null;
			
			if (OreSpawnMain.OreSpawnRand.nextInt(4) == 0)
			{
				target = (EntityPlayer)this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(10.0D, 6.0D, 10.0D), this);
				if (target != null) {
					
					this.currentFlightTarget.set((int)target.posX, (int)target.posY + 2, (int)target.posZ);
				} else {
					bid = Blocks.stone;
					while (bid != Blocks.air && keep_trying != 0) {
						this.currentFlightTarget.set((int)this.posX + this.rand.nextInt(6) - this.rand.nextInt(6), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(6) - this.rand.nextInt(6));
						bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
						keep_trying--;
					}
				}
			} else {
				bid = Blocks.stone;
				while (bid != Blocks.air && keep_trying != 0) {
					this.currentFlightTarget.set((int)this.posX + this.rand.nextInt(6) - this.rand.nextInt(6), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(6) - this.rand.nextInt(6));
					bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
					keep_trying--;
				}
			}
		}
		
		double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
		double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
		double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
		this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * (double)0.1F;
		this.motionY += (Math.signum(var3) * (double)0.7F - this.motionY) * (double)0.1F;
		this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * (double)0.1F;
		float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = 0.3F;
		this.rotationYaw += var8;
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
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
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return true;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		return true;
	}

	/**
	 * Initialize this creature.
	 */
	public void initCreature() {}
}
