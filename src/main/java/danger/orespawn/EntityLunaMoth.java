package danger.orespawn;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;





public class EntityLunaMoth extends EntityButterfly
{
	private ChunkCoordinates currentFlightTarget = null;
	public int moth_type = 0;

	public EntityLunaMoth(World par1World)
	{
		super(par1World);
		
		this.moth_type = OreSpawnMain.OreSpawnRand.nextInt(4);
		
		this.setSize(0.5F, 0.5F);
		this.getNavigator().setAvoidsWater(true);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.1F);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	
	protected void entityInit()
	{
		super.entityInit();
	}

	
	protected void collideWithEntity(Entity par1Entity) {}
	
	
	

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
		this.motionY *= 0.6;
	}

	private int closest = 99999;
	private int tx = 0, ty = 0, tz = 0;

	
	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz){
		int found = 0;
		int i, j, d;
		Block bid;
		
		//Fixed x, scan two sides of 3d rectangle
		for(i=-dy;i<=dy;i++){
			for(j=-dz;j<=dz;j++){
				bid = this.worldObj.getBlock(x+dx, y+i, z+j);
				if(bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch){
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+dx; ty = y+i; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x-dx, y+i, z+j);
				if(bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch){
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x-dx; ty = y+i; tz = z+j;
						found++;
					}
				}
			}
		}
		//Fixed y, scan two sides of 3d rectangle
		for(i=-dx;i<=dx;i++){
			for(j=-dz;j<=dz;j++){
				bid = this.worldObj.getBlock(x+i, y+dy, z+j);
				if(bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch){
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+dy; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y-dy, z+j);
				if(bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch){
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y-dy; tz = z+j;
						found++;
					}
				}
			}
		}
		//Fixed z, scan two sides of 3d rectangle
		for(i=-dx;i<=dx;i++){
			for(j=-dy;j<=dy;j++){
				bid = this.worldObj.getBlock(x+i, y+j, z+dz);
				if(bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch){
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z+dz;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y+j, z-dz);
				if(bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch){
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z-dz;
						found++;
					}
				}
			}
		}

		if(found != 0)return true;
		return false;
	}

	
	protected void updateAITasks() {
		int i, unused2, unused3;
		int keep_trying = 25;
		Block bid;
		if (this.isDead) return;
		super.updateAITasks();
		
		
		if (this.currentFlightTarget == null) {
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}
		if (this.rand.nextInt(100) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
		{
			bid = Blocks.stone;
			while (bid != Blocks.air && keep_trying != 0) {
				this.currentFlightTarget.set((int)this.posX + this.rand.nextInt(10) - this.rand.nextInt(10), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(10) - this.rand.nextInt(10));
				bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
				keep_trying--;
			}
		}
		else if (!this.worldObj.isDaytime() && this.rand.nextInt(10) == 0)
		{
			this.closest = 99999;
			this.tx = this.ty = this.tz = 0;
			for (i = 2; i < 15; i++) {
				if (this.scan_it((int)this.posX, (int)this.posY, (int)this.posZ, i, i, i) == true) break;
				if (i >= 6) i++;
			}

			if (this.closest < 99999)
			{
				this.currentFlightTarget.set(this.tx, this.ty + 1, this.tz);
			}
		}
		
		double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
		double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
		double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
		this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * (double)0.1F;
		this.motionY += (Math.signum(var3) * 0.68 - this.motionY) * (double)0.1F;
		this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * (double)0.1F;
		float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = 0.75F;
		this.rotationYaw += var8;
		
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
		Block bid = this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ);
		if (bid != Blocks.air) return false;
		if (this.worldObj.isDaytime()) return false;
		if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID4) return true;
		if (this.posY < 50.0D) return false;
		return true;
	}

	/**
	 * Initialize this creature.
	 */
	public void initCreature() {}
}
