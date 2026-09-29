package danger.orespawn;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;


































public class GoldFish extends EntityAnimal
{
	private ChunkCoordinates currentFlightTarget = null;

	public GoldFish(World par1World)
	{
		super(par1World);
		this.setSize(0.75F, 0.5F);
		this.experienceValue = 5;
		this.isImmuneToFire = false;
		this.fireResistance = 5;
		
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.22F);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
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
		return 0.45F;
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
		return "splash";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "orespawn:little_splat";
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
		return 6;
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





	public boolean canSeeTarget(double pX, double pY, double pZ)
	{
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
				zdir = this.rand.nextInt(5) + 5;
				xdir = this.rand.nextInt(5) + 5;
				if (this.rand.nextInt(2) == 0) zdir = -zdir;
				if (this.rand.nextInt(2) == 0) xdir = -xdir;
				this.currentFlightTarget.set((int)this.posX + xdir, (int)this.posY + this.rand.nextInt(11) - 5 + updown, (int)this.posZ + zdir);
				bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
				if (bid == Blocks.air) {
					if (!this.canSeeTarget((double)this.currentFlightTarget.posX, (double)this.currentFlightTarget.posY, (double)this.currentFlightTarget.posZ)) {
						bid = Blocks.stone;
					}
				}
				keep_trying--;
			}
		}
		
		
		double var1 = (double)this.currentFlightTarget.posX + 0.4 - this.posX;
		double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
		double var5 = (double)this.currentFlightTarget.posZ + 0.4 - this.posZ;
		this.motionX += (Math.signum(var1) * 0.4 - this.motionX) * 0.3;
		this.motionY += (Math.signum(var3) * 0.7 - this.motionY) * 0.2;
		this.motionZ += (Math.signum(var5) * 0.4 - this.motionZ) * 0.3;
		float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = 0.75F;
		this.rotationYaw += var8 / 6.0F;
		
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
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		return true;
	}





	protected Item getDropItem()
	{
		int i = this.worldObj.rand.nextInt(3);
		if (i == 0) return Item.getItemFromBlock(Blocks.gold_block);
		if (i == 1) return OreSpawnMain.UraniumNugget;
		if (i == 2) return OreSpawnMain.TitaniumNugget;
		return null;
	}



	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}


	public boolean canBreatheUnderwater()
	{
		return true;
	}
}
