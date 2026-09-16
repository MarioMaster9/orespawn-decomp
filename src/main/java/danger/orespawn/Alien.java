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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;


























public class Alien extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private int hurt_timer = 0;
	private double moveSpeed = 0.65;

	public Alien(World par1World)
	{
		super(par1World);
		this.setSize(1.1F, 3.25F);
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setBreakDoors(true);
		this.experienceValue = 100;
		this.fireResistance = 30;
		this.isImmuneToFire = false;
		this.jumpMovementFactor = 0.6F;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 10, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	
	
	
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Alien_stats.attack);
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

	public int mygetMaxHealth()
	{
		return OreSpawnMain.Alien_stats.health;
	}

	
	protected void jump()
	{
		super.jump();
		this.motionY += 0.25D;
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
		return OreSpawnMain.Alien_stats.defense;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	
	
	
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		
		
		if (this.worldObj.isRemote) {
			float f = 1.7F + Math.abs(this.worldObj.rand.nextFloat() * 0.75F);
			
			
			
			
			if (this.worldObj.rand.nextInt(20) == 1) {
				this.worldObj.spawnParticle("dripLava", this.posX - (double)f * Math.sin(Math.toRadians((double)this.rotationYawHead)), this.posY + 1.6, this.posZ + (double)f * Math.cos(Math.toRadians((double)this.rotationYawHead)), 0.0D, 0.0D, 0.0D);
			}
		}
		
	}

	
	
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		super.onUpdate();
	}

	
	
	
	public int getAlienHealth()
	{
		return (int)this.getHealth();
	}

	
	
	
	
	
	protected String getLivingSound()
	{
		if (this.worldObj.rand.nextInt(4) == 0) {
			return "orespawn:alien_living";
		}
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:alien_hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:alien_death";
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
		return Items.spider_eye;
	}

	private void dropItemRand(Item index, int par1)
	{
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), new ItemStack(index, par1, 0));
		
		this.worldObj.spawnEntityInWorld(var3);
	}



	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var4;
		int var5 = 5 + this.worldObj.rand.nextInt(6);
		for (var4 = 0; var4 < var5; ++var4){
			this.dropItemRand(Items.spider_eye, 1);
		}

		var5 = 5 + this.worldObj.rand.nextInt(6);
		for (var4 = 0; var4 < var5; ++var4) {
			this.dropItemRand(Items.flint, 1);
		}

		this.dropItemRand(Items.map, 1);
		this.dropItemRand(Items.clock, 1);
		this.dropItemRand(Items.compass, 1);
	
	
	
	
	
	
	
	
	}
	
	
	
	
	public void initCreature()
	{
	}

	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity != null && par1Entity instanceof EntityLivingBase)
			{
				int var2 = 6;
				
				if (this.worldObj.difficultySetting == EnumDifficulty.EASY)
				{
					var2 = 8;
					if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
					{
						var2 = 10;
					}
					else if (this.worldObj.difficultySetting == EnumDifficulty.HARD)
					{
						var2 = 12;
					}
				}
				if (par1Entity instanceof EntityLivingBase && this.worldObj.rand.nextInt(5) == 1) {
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 5, 0));
				}

				double ks = 1.1;
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

	
	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		Entity e;
		if (par1DamageSource.getDamageType().equals("cactus")) {
			return false;
		} else
		{
			if (this.hurt_timer <= 0)
				ret = super.attackEntityFrom(par1DamageSource, par2);
			
			e = par1DamageSource.getEntity();
			if (e != null && e instanceof EntityLiving)
			{
				this.setAttackTarget((EntityLiving)e);
				this.setTarget(e);
				this.getNavigator().tryMoveToEntityLiving((EntityLiving)e, 1.2);
				ret = true;
			}
			return ret;
		}
	}
	private int closest = 99999;
	private int tx = 0, ty = 0, tz = 0;
	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz)
	{
		int found = 0;

		int i, j, d;
		Block bid;
		
		for (i = -dy; i <= dy; i++) {
			for (j = -dz; j <= dz; j++) {
				bid = this.worldObj.getBlock(x + dx, y + i, z + j);
				if (bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch) {
					d = dx * dx + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + dx; this.ty = y + i; this.tz = z + j;
						++found;
					}
				}
				bid = this.worldObj.getBlock(x - dx, y + i, z + j);
				if (bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch) {
					d = dx * dx + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x - dx; this.ty = y + i; this.tz = z + j;
						++found;
					}
				}
			}
		}

		for (i = -dx; i <= dx; i++) {
			for (j = -dz; j <= dz; j++) {
				bid = this.worldObj.getBlock(x + i, y + dy, z + j);
				if (bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch) {
					d = dy * dy + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + i; this.ty = y + dy; this.tz = z + j;
						++found;
					}
				}
				bid = this.worldObj.getBlock(x + i, y - dy, z + j);
				if (bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch) {
					d = dy * dy + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + i; this.ty = y - dy; this.tz = z + j;
						++found;
					}
				}
			}
		}

		for (i = -dx; i <= dx; i++) {
			for (j = -dy; j <= dy; j++) {
				bid = this.worldObj.getBlock(x + i, y + j, z + dz);
				if (bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch) {
					d = dz * dz + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + i; this.ty = y + j; this.tz = z + dz;
						++found;
					}
				}
				bid = this.worldObj.getBlock(x + i, y + j, z - dz);
				if (bid == Blocks.torch || bid == OreSpawnMain.ExtremeTorch) {
					d = dz * dz + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + i; this.ty = y + j; this.tz = z - dz;
						++found;
					}
				}
			}
		}

		if (found != 0) return true;
		return false;
	}

	protected void updateAITasks()
	{
		
		if (this.isDead) return;
		super.updateAITasks();
		if (this.hurt_timer > 0) --this.hurt_timer;
		if (this.worldObj.rand.nextInt(8) == 0) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null) {
				this.faceEntity(e, 10.0F, 10.0F);
				if (this.getDistanceSqToEntity(e) < 16.0D) {
					this.setAttacking(1);
					
					if (this.worldObj.rand.nextInt(4) == 0 || this.worldObj.rand.nextInt(5) == 1)
					{
						this.attackEntityAsMob(e);
					}
				}
				this.getNavigator().tryMoveToEntityLiving(e, 1.2);
			} else
			{
				this.setAttacking(0);
			}
		}
		else if (this.rand.nextInt(30) == 0 && OreSpawnMain.PlayNicely == 0)
		{
			this.closest = 99999;
			this.tx = this.ty = this.tz = 0;
			int i;
			for (i = 2; i < 15; i++) {
				if (this.scan_it((int)this.posX, (int)this.posY, (int)this.posZ, i, i, i) == true) break;
				if (i >= 10) ++i;

			}
			if (this.closest < 99999) {
				this.getNavigator().tryMoveToXYZ((double)this.tx, (double)this.ty, (double)this.tz, 1.0D);
				if (this.closest < 27) {
					if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.air, 0, 2);
				}
			}
		}

		if (this.worldObj.rand.nextInt(40) == 1) {
			if (this.getHealth() < (float)this.mygetMaxHealth())
			{
				this.heal(1.0F);
			}
		}
	}

	
	
	
	
	
	
	
	private boolean isSuitableTarget(EntityLivingBase var4, boolean par2)
	{
		if (var4 == null)
		{
			return false;
		}
		if (var4 == this)
		{
			return false;
		}
		if (!var4.isEntityAlive())
		{
			return false;
		}
		
		
		
		
		
		if (var4 instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)var4;
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
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(12.0D, 4.0D, 12.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		EntityLivingBase e;
		Entity var3 = null;
		EntityLivingBase var4 = null;
		
		e = this.getAttackTarget();
		if (e != null && e.isEntityAlive()) {
			return e;
		}
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

	public final int getAttacking()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1)
	{
		this.dataWatcher.updateObject(20, Byte.valueOf((byte)par1));
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
							if (s.equals("Alien")) return true;
						}
					}
				}
			}
		}

		if (!this.isValidLightLevel()) return false;
		if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID4) return true;
		if (this.posY > 50.0D) return false;
		
		
		for (k = -1; k < 2; k++)
		{
			for (j = -1; j < 2; j++)
			{
				for (i = 1; i < 4; i++)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid != Blocks.air) return false;

				}
			}
		}

		
		
		
		
		
		
		return true;
	}
}
