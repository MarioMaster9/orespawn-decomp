package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;





























public class Vortex extends EntityMob
{
	private ChunkCoordinates currentFlightTarget = null;
	private GenericTargetSorter TargetSorter = null;
	private int winded = 0;
	private int busy_fighting = 0;
	private int was_spawnered = 0;

	public Vortex(World par1World) {
		super(par1World);
		this.setSize(2.0F, 4.0F);
		this.experienceValue = 200;
		this.isImmuneToFire = true;
		this.fireResistance = 250;
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.35F);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Vortex_stats.attack);
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		if (this.busy_fighting != 0) return false;
		if (this.was_spawnered != 0) return false;
		return true;
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

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound() {
		return "orespawn:vortexlive";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return null;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "orespawn:vortexlive";
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
		return OreSpawnMain.Vortex_stats.health;
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
		int i;
		double d;
		double dx, dz;
		double dir;
		EntityLivingBase e = null;
		super.onUpdate();
		
		this.motionY *= 0.6;
		this.busy_fighting = 0;
		e = this.findSomethingToAttack();
		if (e != null)
		{
			this.busy_fighting = 1;
			if (this.worldObj.isRemote)
			{
				
				
				for (i = 0; i < 20; i++) {
					d = this.worldObj.rand.nextDouble() * (double)3.5F;
					d *= d;
					dir = this.worldObj.rand.nextDouble() * 2.0D * Math.PI;
					dir -= Math.PI;
					dx = Math.cos(dir) * d / 2.0D;
					dz = Math.sin(dir) * d / 2.0D;
					dir += Math.PI / 2D;
					this.worldObj.spawnParticle("smoke", this.posX + dx, this.posY + 0.75D + d, this.posZ + dz, Math.cos(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D, (double)(this.worldObj.rand.nextFloat() / 2.0F), Math.sin(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D);
				}
			}
		}

		
		
		
		if (this.worldObj.rand.nextInt(200) == 1) this.heal(1.0F);

		if (this.isNoDespawnRequired()) return;
		if (this.busy_fighting != 0) return;
		if (this.was_spawnered != 0) return;
		long t = this.worldObj.getWorldTime();
		t %= 24000L;
		if (t < 12000L && this.worldObj.rand.nextInt(500) == 1) this.setDead();
	}





	public boolean canSeeTarget(double pX, double pY, double pZ)
	{
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}



	protected void updateAITasks()
	{
		int xdir = 1;
		int zdir = 1;
		int newx, newz; // unused
		int keep_trying = 50;
		int i, j, k, dist; // unused
		Block bid;
		EntityLivingBase e = null;
		
		
		if (this.isDead) return;
		super.updateAITasks();
		
		if (this.currentFlightTarget == null) {
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}
		
		if (this.winded > 0) this.winded--;
		
		if (this.rand.nextInt(300) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 2.1F)
		{
			bid = Blocks.stone;
			while (bid != Blocks.air && keep_trying != 0) {
				zdir = this.rand.nextInt(14) + 10;
				xdir = this.rand.nextInt(14) + 10;
				if (this.rand.nextInt(2) == 0) zdir = -zdir;
				if (this.rand.nextInt(2) == 0) xdir = -xdir;
				this.currentFlightTarget.set((int)this.posX + xdir, (int)this.posY + this.rand.nextInt(6) - 3, (int)this.posZ + zdir);
				bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
				if (bid == Blocks.air) {
					if (!this.canSeeTarget((double)this.currentFlightTarget.posX, (double)this.currentFlightTarget.posY, (double)this.currentFlightTarget.posZ)) {
						bid = Blocks.stone;
					}
				}
				keep_trying--;
			}
		}
		
		
		
		e = this.findSomethingToAttack();
		if (e != null)
		{
			this.currentFlightTarget.set((int)e.posX, (int)e.posY, (int)e.posZ);
			
			double d = this.getDistanceSqToEntity(e);
			if (d < 81.0D && this.winded == 0) {
				double a = Math.atan2(this.posZ - e.posZ, this.posX - e.posX);
				double pm = 1.0D;
				if (e instanceof EntityPlayer) pm = 2.0D;
				e.addVelocity(Math.cos(a) * (10.0D - Math.sqrt(d)) * (double)0.1F, (10.0D - Math.sqrt(d)) * (double)0.05F * pm, Math.sin(a) * (10.0D - Math.sqrt(d)) * (double)0.1F);
			}
			if (this.getDistanceSqToEntity(e) < (double)((4.0F + e.width / 2.0F) * (4.0F + e.width / 2.0F)) && this.rand.nextInt(8) == 2) {
				this.attackEntityAsMob(e);
			}
		}
		
		
		double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
		double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
		double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
		this.motionX += (Math.signum(var1) * 0.4 - this.motionX) * 0.2;
		this.motionY += (Math.signum(var3) * (double)0.7F - this.motionY) * 0.20000000149011612;
		this.motionZ += (Math.signum(var5) * 0.4 - this.motionZ) * 0.2;
		float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = 0.75F;
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
		return true;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		Entity e = par1DamageSource.getEntity();
		ret = super.attackEntityFrom(par1DamageSource, par2);
		if (e != null && this.currentFlightTarget != null)
		{
			this.currentFlightTarget.set((int)e.posX, (int)e.posY, (int)e.posZ);
		}
		this.winded = 20;
		return ret;
	}


	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.Vortex_stats.defense;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere() {
		Block bid;
		int i, j, k;
		
		for (k = -3; k < 3; k++)
		{
			for (j = -3; j < 3; j++)
			{
				for (i = 0; i < 5; i++)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null) {
							if (s.equals("Vortex")) {
								this.was_spawnered = 1;
								return true;
							}
						}
					}
				}
			}
		}

		
		for (k = -2; k <= 2; k++)
		{
			for (j = -2; j <= 2; j++)
			{
				for (i = 1; i < 4; i++)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid != Blocks.air) return false;
				}
			}
		}
		if (!this.isValidLightLevel()) return false;
		if (this.posY < 50.0D) return false;
		long t = this.worldObj.getWorldTime();
		t %= 24000L;
		if (t < 12000L) return false;
		if (this.worldObj.rand.nextInt(2) != 1) return false;
		Vortex target = null;
		target = (Vortex)this.worldObj.findNearestEntityWithinAABB(Vortex.class, this.boundingBox.expand(20.0D, 16.0D, 20.0D), this);
		if (target != null)
		{
			return false;
		}
		
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
		
		if (OreSpawnMain.OreSpawnUtils.isIgnoreable(par1EntityLiving)) return false;
		
		if (!this.getEntitySenses().canSee(par1EntityLiving))
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
		
		if (par1EntityLiving instanceof Vortex)
		{
			return false;
		}
		if (par1EntityLiving instanceof Rotator)
		{
			return false;
		}
		if (par1EntityLiving instanceof Mothra)
		{
			return false;
		}
		if (par1EntityLiving instanceof Brutalfly)
		{
			return false;
		}
		if (par1EntityLiving instanceof Peacock)
		{
			return false;
		}
		if (par1EntityLiving instanceof CrystalCow)
		{
			return false;
		}
		if (par1EntityLiving instanceof Irukandji)
		{
			return false;
		}
		if (par1EntityLiving instanceof Skate)
		{
			return false;
		}
		if (par1EntityLiving instanceof Whale)
		{
			return false;
		}
		if (par1EntityLiving instanceof Flounder)
		{
			return false;
		}
		if (par1EntityLiving instanceof Urchin)
		{
			return false;
		}
		
		return true;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0D, 10.0D, 16.0D));
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

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(6) - (double)OreSpawnMain.OreSpawnRand.nextInt(6), this.posY + 1.0D + (double)this.worldObj.rand.nextInt(10), this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(6) - (double)OreSpawnMain.OreSpawnRand.nextInt(6), is);
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		int var3, var4, i;
		
		this.dropItemRand(OreSpawnMain.VortexEye, 1);
		this.dropItemRand(Items.item_frame, 1);
		
		i = 5 + this.worldObj.rand.nextInt(7);
		for (var4 = 0; var4 < i; var4++) {
			var3 = this.worldObj.rand.nextInt(10);
			if (var3 == 0) this.dropItemRand(Items.stick, 1);
			if (var3 == 1) this.dropItemRand(OreSpawnMain.MyTigersEyeIngot, 1);
			if (var3 == 2) this.dropItemRand(OreSpawnMain.MyCrystalPinkIngot, 1);
			if (var3 == 3) this.dropItemRand(Items.iron_ingot, 1);
			if (var3 == 4) this.dropItemRand(OreSpawnMain.UraniumNugget, 1);
			if (var3 == 6) this.dropItemRand(OreSpawnMain.TitaniumNugget, 1);
			if (var3 == 7) this.dropItemRand(OreSpawnMain.MyIrukandji, 1);
			if (var3 == 8) this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.CrystalCoal), 1);
			
		}
	}




	protected Item getDropItem()
	{
		return OreSpawnMain.FairyEgg;
	}
}
