package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;










































public class Irukandji extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private EntityLivingBase buddy = null;
	private float moveSpeed = 0.15F;

	public Irukandji(World par1World)
	{
		super(par1World);
		this.setSize(0.25F, 0.25F);
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 50;
		this.fireResistance = 1;
		this.isImmuneToFire = false;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIWander(this, 1.0F));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}





	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Irukandji_stats.attack);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	public boolean canBreatheUnderwater()
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
	}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.Irukandji_stats.health;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.Irukandji_stats.defense;
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
	 * Returns the amount of damage a mob should deal.
	 */
	public int getAttackStrength(Entity par1Entity)
	{
		int var2 = 2;
		return var2;
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
		return "orespawn:little_splt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:ratdead";
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
		return 2.0F;
	}




	protected Item getDropItem()
	{
		return OreSpawnMain.MyIrukandji;
	}

	/**
	 * Initialize this creature.
	 */
	public void initCreature()
	{
		
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer != null) {
			if (par1EntityPlayer.getCurrentEquippedItem() == null) {
				par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), 200.0F);
			}
		}
		return false;
	}



	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		
		if (this.isDead) return false;
		
		Entity e = par1DamageSource.getEntity();
		if (e != null && e instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)e;
			if (p.getCurrentEquippedItem() == null) {
				p.attackEntityFrom(DamageSource.causeMobDamage(this), 200.0F);
				return false;
			}
		}

		if (e != null && e instanceof EntityLiving)
		{
			if (e instanceof Irukandji) return false;
			this.setAttackTarget((EntityLiving)e);
			this.setTarget(e);
			this.getNavigator().tryMoveToEntityLiving((EntityLiving)e, 1.2);
			ret = true;
		}

		ret = super.attackEntityFrom(par1DamageSource, par2);
		
		return ret;
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
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+dx; ty = y+i; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x-dx, y+i, z+j);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
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
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+dy; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y-dy, z+j);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
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
				if(bid == Blocks.water || bid == Blocks.flowing_water){
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z+dz;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y+j, z-dz);
				if(bid == Blocks.water || bid == Blocks.flowing_water){
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



	protected void updateAITasks()
	{
		if (this.isDead) return;
		super.updateAITasks();
		int i, j;
		
		if (!this.isInWater() && this.worldObj.rand.nextInt(10) == 0) {
			
			
			this.closest = 99999;
			this.tx = this.ty = this.tz = 0;
			for (i = 1; i < 12; i++) {
				j = i;
				if (j > 5) j = 5;
				if (this.scan_it((int)this.posX, (int)this.posY - 1, (int)this.posZ, i, j, i) == true) break;
				if (i >= 5) i++;
			}

			if (this.closest < 99999) {
				this.getNavigator().tryMoveToXYZ((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.33);
			} else {
				if (this.worldObj.rand.nextInt(25) == 1)
					this.heal(-1.0F);
				if (this.getHealth() <= 0.0F) {
					this.setDead();
					return;
				}
			}
		}
		
		
		if (this.worldObj.rand.nextInt(8) == 1) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null) {
				if (this.getDistanceSqToEntity(e) < 3.0D) {
					this.setAttacking(1);
					
					if (this.worldObj.rand.nextInt(4) == 0 || this.worldObj.rand.nextInt(5) == 1)
					{
						this.attackEntityAsMob(e);
					}
					
					
					
					
					
					
					
				} else {
					this.getNavigator().tryMoveToEntityLiving(e, 1.2);
				}
			} else {
				this.setAttacking(0);
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
		if (par1EntityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (p.capabilities.isCreativeMode == true) {
				return false;
			}
			return true;
		}
		
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(6.0D, 4.0D, 6.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		EntityLivingBase e;
		Entity var3 = null;
		EntityLivingBase var4 = null;
		
		e = this.getAttackTarget();
		if (e != null && e.isEntityAlive()) {
			return e;
		} else {
			this.setAttackTarget(null);

			
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
	}






	public final int getAttacking()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1)
	{
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	private int findBuddies()
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(Irukandji.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
		return var5.size();
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		if (this.posY < 50.0D) return false;
		if (!this.worldObj.isDaytime()) return false;
		if (this.worldObj.rand.nextInt(60) != 1) return false;
		if (this.findBuddies() > 2) return false;
		return true;
	}
}
