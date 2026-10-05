package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
























public class Molenoid extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.35F;

	public Molenoid(World par1World)
	{
		super(par1World);
		this.setSize(3.9F, 2.6F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 40;
		this.fireResistance = 100;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Molenoid_stats.attack);
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
		return OreSpawnMain.Molenoid_stats.health;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.Molenoid_stats.defense;
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
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		if (this.rand.nextInt(3) == 0) {
			return "orespawn:molenoid_living";
		}
		return null;
	}


	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:molenoid_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:molenoid_death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 1.1F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}






	protected Item getDropItem()
	{
		return Items.beef;
	}

	private void dropItemRand(Item index, int par1)
	{
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), new ItemStack(index, par1, 0));
		
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		int var4;
		
		this.dropItemRand(OreSpawnMain.MolenoidNose, 1);
		this.dropItemRand(Items.item_frame, 1);

		
		for (var4 = 0; var4 < 10; var4++) {
			this.dropItemRand(Items.gold_nugget, 1);
		}
		for (var4 = 0; var4 < 6; var4++) {
			this.dropItemRand(Items.beef, 1);
		}
		
	}

	/**
	 * Initialize this creature.
	 */
	public void initCreature() {
		
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return false;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (par1DamageSource.getDamageType().equals("inWall")) {
			return false;
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}




	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity != null && par1Entity instanceof EntityLivingBase)
			{
				double ks = 0.8;
				double inair = 0.1;
				float f3 = (float)Math.atan2(par1Entity.posZ - this.posZ, par1Entity.posX - this.posX);
				if (par1Entity.isDead || par1Entity instanceof EntityPlayer) inair *= 2.0D;
				par1Entity.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
			}
			return true;
		}
		else
		{
			return false;
		}
	}


	protected void updateAITasks() {
		double dx, dz;
		int j, k;
		EntityLivingBase e = null;
		if (this.isDead) return;
		super.updateAITasks();
		if (this.worldObj.rand.nextInt(4) == 0) {
			e = this.findSomethingToAttack();
			if (e != null) {
				this.faceEntity(e, 10.0F, 10.0F);
				if (this.getDistanceSqToEntity(e) < (double)((6.0F + e.width / 2.0F) * (6.0F + e.width / 2.0F))) {
					this.setAttacking(1);
					
					if (this.getDistanceSqToEntity(e) < 16.0D && (this.worldObj.rand.nextInt(4) == 0 || this.worldObj.rand.nextInt(5) == 1)) {
						
						this.attackEntityAsMob(e);
						
						
					} else if (OreSpawnMain.PlayNicely == 0) {
						j = 1 + this.worldObj.rand.nextInt(4);
						for (k = 0; k < j; k++) {
							dx = e.posX;
							dz = e.posZ;
							dx += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0D;
							dz += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0D;
							for (int i = 4; i > -3; i--) {
								if (this.worldObj.getBlock((int)dx, (int)e.posY + i + 1, (int)dz) == Blocks.air) {
									if (this.worldObj.getBlock((int)dx, (int)e.posY + i, (int)dz) != Blocks.air) {
										this.worldObj.setBlock((int)dx, (int)e.posY + i + 1, (int)dz, OreSpawnMain.MyMoleDirtBlock);
										break;
									}
								}
							}
						}
						
					}
				} else {
					this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
				}
			} else {
				this.setAttacking(0);
			}
		}
		if (this.worldObj.isRemote) return;
		if (this.worldObj.rand.nextInt(2) == 0) {
			double spd = 0.0D;
			
			spd = this.motionX * this.motionX + this.motionZ * this.motionZ;
			spd = Math.sqrt(spd);
			if (spd > (double)this.moveSpeed) spd = (double)this.moveSpeed;
			int odds = (int)((double)100.0F * spd / (double)this.moveSpeed);
			
			if (odds > 0) {
				if (this.worldObj.rand.nextInt(100) < odds && OreSpawnMain.PlayNicely == 0)
				{
					
					dx = this.posX + 6.0D * Math.sin(Math.toRadians((double)this.rotationYawHead));
					dz = this.posZ - 6.0D * Math.cos(Math.toRadians((double)this.rotationYawHead));
					dx += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 3.0D;
					dz += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 3.0D;
					for (int i = 4; i > -4; i--) {
						if (this.worldObj.getBlock((int)dx, (int)this.posY + i + 1, (int)dz) == Blocks.air) {
							if (this.worldObj.getBlock((int)dx, (int)this.posY + i, (int)dz) != Blocks.air) {
								this.worldObj.setBlock((int)dx, (int)this.posY + i + 1, (int)dz, OreSpawnMain.MyMoleDirtBlock);
								break;
							}
						}
					}
				}
			}
		}
		
		
		
		dx = this.posX - 3.0D * Math.sin(Math.toRadians((double)this.rotationYawHead));
		dz = this.posZ + 3.0D * Math.cos(Math.toRadians((double)this.rotationYawHead));
		dx += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 3.0D;
		dz += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 3.0D;
		int dir = 1;
		if (e != null) {
			if ((int)e.posY > (int)this.posY) dir = 2;
			if ((int)e.posY < (int)this.posY) dir = 0;
		}
		if (OreSpawnMain.PlayNicely == 0) {
			for (int i = dir; i < dir + 3; i++) {
				Block bid = this.worldObj.getBlock((int)dx, (int)this.posY + i, (int)dz);
				if (bid == Blocks.dirt || bid == Blocks.grass || bid == Blocks.gravel || bid == Blocks.sand || bid == Blocks.leaves) {
					if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock((int)dx, (int)this.posY + i, (int)dz, Blocks.air);
				}
				if (bid == OreSpawnMain.MyMoleDirtBlock) {
					this.worldObj.setBlock((int)dx, (int)this.posY + i, (int)dz, Blocks.air);
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
		
		
		
		
		
		
		
		if (!this.MyCanSee(par1EntityLiving)) {
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
		if (par1EntityLiving instanceof Molenoid)
		{
			return false;
		}
		
		if (par1EntityLiving instanceof EntityMob)
		{
			return true;
		}
		if (OreSpawnMain.OreSpawnUtils.isAttackableNonMob(par1EntityLiving)) {
			return true;
		}
		
		
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(12.0D, 6.0D, 12.0D));
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
							if (s.equals("Molenoid")) return true;
						}
					}
				}
			}
		}
		if (!this.isValidLightLevel()) return false;
		if (this.posY < 50.0D) return false;
		if (this.worldObj.isDaytime() == true) return false;
		
		
		
		for (k = -1; k < 1; k++)
		{
			for (j = -1; j < 1; j++)
			{
				for (i = 1; i < 4; i++)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid != Blocks.air) return false;
				}
			}
		}

		
		Molenoid target = null;
		target = (Molenoid)this.worldObj.findNearestEntityWithinAABB(Molenoid.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D), this);
		if (target != null)
		{
			return false;
		}
		return true;
	}





	public boolean MyCanSee(EntityLivingBase e) {
		float startx;
		float starty;
		float startz;
		double xzoff = 2.0D;
		double cx, cz;
		float dx;
		float dy;
		float dz;
		int i;
		Block bid;
		int nblks = 10;
		
		cx = posX-(xzoff*Math.sin(Math.toRadians(rotationYaw)));
		cz = posZ+(xzoff*Math.cos(Math.toRadians(rotationYaw)));
		startx = (float)(cx);
		starty = (float)(posY+1);
		startz = (float)(cz);
		dx = (float)((e.posX-startx)/10.0D);
		dy = (float)((e.posY+(e.height/2.0F)-starty)/10.0D);
		dz = (float)((e.posZ-startz)/10.0D);
		
		if(Math.abs(dx) > 1.0){
			dy = dy/Math.abs(dx);
			dz = dz/Math.abs(dx);
			nblks *= Math.abs(dx);
			if(dx > 1)dx = 1;
			if(dx < -1)dx = -1;
		}
		if(Math.abs(dy) > 1.0){
			dx = dx/Math.abs(dy);
			dz = dz/Math.abs(dy);
			nblks *= Math.abs(dy);
			if(dy > 1)dy = 1;
			if(dy < -1)dy = -1;
		}
		if(Math.abs(dz) > 1.0){
			dy = dy/Math.abs(dz);
			dx = dx/Math.abs(dz);
			nblks *= Math.abs(dz);
			if(dz > 1)dz = 1;
			if(dz < -1)dz = -1;
		}

		for(i=0;i<nblks;i++){
			startx += dx;
			starty += dy;
			startz += dz;
			bid = this.worldObj.getBlock((int)startx, (int)starty, (int)startz);
			if (bid == Blocks.air) continue;
			if (bid == OreSpawnMain.MyMoleDirtBlock) continue;
			if (bid == Blocks.dirt) continue;
			if (bid == Blocks.grass) continue;
			if (bid == Blocks.tallgrass) continue;
			if (bid == Blocks.sand) continue;
			if (bid == Blocks.gravel) continue;
			return false;
		}

		
		return true;
	}
}
