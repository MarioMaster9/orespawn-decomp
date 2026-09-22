package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;




























public class ThePrinceTeen extends EntityTameable
{
	private int boatPosRotationIncrements;
	private double boatX;
	private double boatY;
	private double boatZ;
	private double boatYaw;
	private double boatPitch;
	private double boatYawHead;
	private int updateit = 1;
	private int playing = 0;
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private int hurt_timer = 0;
	private int wing_sound = 0;
	private ChunkCoordinates currentFlightTarget = null;
	private boolean target_in_sight = false;
	private int owner_flying = 0;
	private int flyaway = 0;
	private float moveSpeed = 0.32F;
	private float deltasmooth = 0.0F;
	private int which_attack = 0;
	private int fireballticker = 0;
	
	private int head1ext = 0;
	private int head2ext = 0;
	private int head3ext = 0;
	private int head1dir = 1;
	private int head2dir = 1;
	private int head3dir = 1;
	
	private int kill_count = 0;
	private int day_count = 0;
	private int is_day = 0;

	
	
	
	
	public ThePrinceTeen(World par1World)
	{
		super(par1World);
		
		this.setSize(3.25F, 4.25F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 300;
		this.fireResistance = 1000;
		this.isImmuneToFire = true;
		this.setSitting(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIFollowOwner(this, 1.1F, 12.0F, 2.0F));
		this.tasks.addTask(2, new EntityAITempt(this, 1.25D, Items.beef, false));
		this.tasks.addTask(3, new MyEntityAIWander(this, 0.75F));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityLiving.class, 9.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.tasks.addTask(6, new EntityAIMoveIndoors(this));
		if (OreSpawnMain.PlayNicely == 0) this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.mobSelector));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.riddenByEntity = null;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
	}

	
	public ThePrinceTeen(World par1World, double par2, double par4, double par6)
	{
		this(par1World);
		this.setPosition(par2, par4 + (double)this.yOffset, par6);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(50.0D);
	}

	/**
	 * Used in model rendering to determine if the entity riding this entity should be in the 'sitting' position.
	 * @return false to prevent an entity that is mounted to this entity from displaying the 'sitting' animation.
	 */
	public boolean shouldRiderSit()
	{
		return true;
	}

	public int getTrackingRange()
	{
		return 64;
	}

	public int getUpdateFrequency() {
		return 10;
	}

	public boolean sendsVelocityUpdates() {
		return true;
	}

	
	public int getHead1Ext() {
		return this.dataWatcher.getWatchableObjectInt(22);
	}

	public int getHead2Ext() {
		return this.dataWatcher.getWatchableObjectInt(23);
	}

	public int getHead3Ext() {
		return this.dataWatcher.getWatchableObjectInt(25);
	}

	public void setHead1Ext(int par1) {
		if (this.worldObj != null && this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(22, par1);
	}

	public void setHead2Ext(int par1) {
		if (this.worldObj != null && this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(23, par1);
	}

	public void setHead3Ext(int par1) {
		if (this.worldObj != null && this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(25, par1);
	}

	protected void fall(float par1) {}

	protected void updateFallState(double par1, boolean par3) {}
	
	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return true;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, 0);
		this.dataWatcher.addObject(21, 0);
		this.dataWatcher.addObject(24, 1);
		this.dataWatcher.addObject(22, 0);
		this.dataWatcher.addObject(23, 0);
		this.dataWatcher.addObject(25, 0);
		this.setActivity(0);
		this.setAttacking(0);
		this.setTamed(false);
		this.setThePrinceTeenFire(1);
		this.noClip = false;
		
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

	
	
	public int mygetMaxHealth()
	{
		return 1500;
	}

	
	
	public int getThePrinceTeenHealth()
	{
		return (int)this.getHealth();
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
		return 18;
	}

	
	protected void jump()
	{
		super.jump();
		this.motionY += 0.25;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	
	
	
	
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	public String getLivingSound()
	{
		if (this.isSitting())
		{
			return null;
		}
		if (this.getActivity() == 1 && this.riddenByEntity == null) {
			return "orespawn:roar";
		}
		
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:alo_hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:alo_death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.6F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	public float getSoundPitch() {
		return 0.75F;
	}

	
	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return false;
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding this one.
	 */
	public double getMountedYOffset()
	{
		return 2.75D;
	}

	
	
	
	protected Item getDropItem()
	{
		return OreSpawnMain.ThePrinceEgg;
	}

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), is);
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		this.dropItemRand(OreSpawnMain.ThePrinceEgg, 1);
	}

	
	
	
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		double ks = 1.75D;
		double inair = 0.1;
		float iskraken = 1.0F;
		if (par1Entity != null && par1Entity instanceof EntityLivingBase)
		{
			if (par1Entity instanceof Kraken) iskraken = 2.0F;
			par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), iskraken * 45.0F);
			
			float f3 = (float)Math.atan2(par1Entity.posZ - this.posZ, par1Entity.posX - this.posX);
			if (par1Entity.isDead || par1Entity instanceof EntityPlayer) inair *= 2.0D;
			par1Entity.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
			if (par1Entity instanceof EntityLiving) {
				EntityLiving e = (EntityLiving)par1Entity;
				if (e.getHealth() <= 0.0F) {
					this.kill_count++;
				}
			}
		}
		return true;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		Entity e = null;
		
		if (this.hurt_timer > 0) return false;
		
		if (par1DamageSource.getDamageType().equals("cactus")) {
			return ret;
		}
		if (par1DamageSource.getDamageType().equals("inFire")) {
			return ret;
		}
		if (par1DamageSource.getDamageType().equals("onFire")) {
			return ret;
		}
		if (par1DamageSource.getDamageType().equals("lava")) {
			return ret;
		}
		if (par1DamageSource.getDamageType().equals("inWall")) {
			return ret;
		}
		
		this.setSitting(false);
		this.setActivity(1);
		
		e = par1DamageSource.getEntity();
		
		
		if (e != null && e instanceof BetterFireball)
		{
			e.setDead();
			return ret;
		}
		
		if (e != null && e instanceof EntitySmallFireball)
		{
			e.setDead();
			return ret;
		}
		if (e != null && e instanceof ThePrinceTeen) {
			return false;
		}
		if (e != null && e instanceof Spyro) {
			return false;
		}
		
		ret = super.attackEntityFrom(par1DamageSource, par2);
		this.hurt_timer = 20;
		
		if (e != null && e instanceof EntityLivingBase)
		{
			if (this.isTamed()) {
				if (e instanceof EntityPlayer) {
					return false;
				}
			}

			this.setAttackTarget((EntityLivingBase)e);
			this.setTarget(e);
			this.getNavigator().tryMoveToEntityLiving((EntityLivingBase)e, 1.2);
			ret = true;
		}

		return ret;
	}

	
	public void updateAITasks()
	{
		EntityLivingBase e = null;
		super.updateAITasks();
		
		
		if (!this.isSitting() && this.getActivity() == 0 && this.riddenByEntity == null && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.rand.nextInt(10) == 1)
		{
			e = this.findSomethingToAttack();
			if (e != null) {
				
				
				this.setActivity(1);
			} else {
				this.setAttacking(0);
			}
		}
		
		
		if (this.kill_count > 25 && this.day_count > 10) {
			Entity ent = null;
			ThePrinceAdult d = null;
			ent = spawnCreature(this.worldObj, "The Young Adult Prince", this.posX, this.posY, this.posZ);
			if (ent != null) {
				d = (ThePrinceAdult)ent;
				if (isTamed()) {
					d.setTamed(true);
					d.func_152115_b(this.func_152113_b());
				}
				this.setDead();
			}
		}
		
		if (this.is_day == 0) {
			this.is_day = 1;
			if (!this.worldObj.isDaytime()) this.is_day = -1;
		} else {
			if (this.is_day == -1) {
				if (this.worldObj.isDaytime())
				{
					this.day_count++;
				}
			}
			this.is_day = 1;
			if (!this.worldObj.isDaytime()) this.is_day = -1;
		}
	}

	public void always_do()
	{
		EntityPlayer p = null;
		
		
		if (this.worldObj.rand.nextInt(250) == 1) {
			if (this.getHealth() < (float)this.mygetMaxHealth())
			{
				this.heal(2.0F);
			}
		}

		if (this.worldObj.rand.nextInt(250) == 0) {
			this.setAttackTarget(null);
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		if (this.isSitting()) return;
		
		this.owner_flying = 0;
		if (this.isTamed() && this.getOwner() != null && this.riddenByEntity == null && !this.isSitting()) {
			p = (EntityPlayer)this.getOwner();
			
			if (p.capabilities.isFlying) {
				this.owner_flying = 1;
				
				this.setActivity(1);
			}
		}
		
		
		
		if (this.worldObj.rand.nextInt(50) == 1 && !this.isSitting())
		{
			if (!this.target_in_sight && this.riddenByEntity == null)
			{
				if (this.worldObj.rand.nextInt(15) == 1) {
					
					this.setActivity(1);
					
				} else {
					this.setActivity(0);
				}
			}
		}
		
	}

	public void fly_with_rider()
	{
		EntityLivingBase e = null;
		
		
		if (this.isDead) return;
		
		if (this.isSitting()) return;
		if (this.worldObj.isRemote) return;
		
		if (this.worldObj.rand.nextInt(5) == 1 && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
		{
			e = this.findSomethingToAttack();
			if (e != null)
			{
				this.setAttacking(1);
				if (this.getDistanceSqToEntity(e) < (double)((8.0F + e.width / 2.0F) * (8.0F + e.width / 2.0F)))
				{
					this.attackEntityAsMob(e);
				} else if (this.getDistanceSqToEntity(e) > 100.0D && this.getDistanceSqToEntity(e) < 625.0D && !this.isInWater()) {
					if (this.getThePrinceTeenFire() != 0) {
						this.shoot_something(e.posX, e.posY, e.posZ);
					}
				}
			} else {
				this.setAttacking(0);
			}
		}
	}

	
	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick()
	{
		if (this.riddenByEntity != null) return;
		super.updateAITick();
	}

	
	
	
	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
	{
		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) return false;
		
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
		if (MyUtils.isRoyalty(par1EntityLiving))
		{
			return false;
		}
		
		if (par1EntityLiving instanceof EntityMob)
		{
			return true;
		}
		if (par1EntityLiving instanceof Mothra)
		{
			
			return true;
		}
		if (par1EntityLiving instanceof Kraken)
		{
			
			return true;
		}
		if (par1EntityLiving instanceof Leon)
		{
			Leon l = (Leon)par1EntityLiving;
			if (l.isTamed()) return false;
			
			return true;
		}
		if (par1EntityLiving instanceof WaterDragon)
		{
			WaterDragon l = (WaterDragon)par1EntityLiving;
			if (l.isTamed()) return false;
			
			return true;
		}
		if (par1EntityLiving instanceof GammaMetroid)
		{
			GammaMetroid l = (GammaMetroid)par1EntityLiving;
			if (l.isTamed()) return false;
			
			return true;
		}
		
		
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(25.0D, 20.0D, 25.0D));
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

	
	
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return false;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		return false;
	}

	
	
	
	public boolean canSeeTarget(double pX, double pY, double pZ)
	{
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	
	
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	{
		super.setPositionAndRotation2(par1, par3, par5, par7, par8, par9);
		this.boatPosRotationIncrements = par9;
		
		
		this.boatX = par1;
		this.boatY = par3;
		this.boatZ = par5;
		this.boatYaw = (double)par7;
		this.boatPitch = (double)par8;
		this.boatYawHead = (double)par7;
	}

	
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5)
	{
		super.setVelocity(par1, par3, par5);
	}

	
	public void onUpdate()
	{
		EntityLivingBase e = null;
		
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		
		if (this.getActivity() != 0) {
			this.noClip = true;
		} else {
			this.noClip = false;
		}

		if (!this.worldObj.isRemote) {
			if (this.worldObj.rand.nextInt(10) == 1) {
				int i = this.worldObj.rand.nextInt(3);
				if (i == 0) this.head1dir = 2;
				if (i == 1) this.head1dir = -2;
				if (i == 2) this.head1dir = 0;
			}
			if (this.worldObj.rand.nextInt(10) == 1) {
				int i = this.worldObj.rand.nextInt(3);
				if (i == 0) this.head2dir = 2;
				if (i == 1) this.head2dir = -2;
				if (i == 2) this.head2dir = 0;
			}
			if (this.worldObj.rand.nextInt(10) == 1) {
				int i = this.worldObj.rand.nextInt(3);
				if (i == 0) this.head3dir = 2;
				if (i == 1) this.head3dir = -2;
				if (i == 2) this.head3dir = 0;
			}
			this.head1ext += this.head1dir;
			if (this.head1ext < 0) this.head1ext = 0;
			if (this.head1ext > 60) this.head1ext = 60;
			this.head2ext += this.head2dir;
			if (this.head2ext < 0) this.head2ext = 0;
			if (this.head2ext > 60) this.head2ext = 60;
			this.head3ext += this.head3dir;
			if (this.head3ext < 0) this.head3ext = 0;
			if (this.head3ext > 60) this.head3ext = 60;

			this.setHead1Ext(this.head1ext);
			this.setHead2Ext(this.head2ext);
			this.setHead3Ext(this.head3ext);
		}

		if (this.hurt_timer > 0) this.hurt_timer--;

		if (this.getActivity() == 1) {
			this.wing_sound++;
			if (this.wing_sound > 20)
			{
				if (!this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, "orespawn:MothraWings", 0.5F, 1.0F);
				this.wing_sound = 0;
			}
		}

		if (this.isInWater()) {
			this.motionY += 0.07;
		}

		if (this.worldObj.isRemote) return;
		
		if (this.getActivity() == 0 && this.isTamed() && this.getOwner() != null && !this.isSitting()) {
			e = this.getOwner();
			
			if (this.getDistanceSqToEntity(e) > 400.0D)
			{
				this.setActivity(1);
			}
		}
		
	}
	
	private void fly_without_rider()
	{
		int xdir = 1;
		int zdir = 1;
		int unused1, unused2;
		int keep_trying = 10;
		int i, j, k, dist;
		Block bid;
		int do_new = 0;
		double ox = 0.0D, oy = 0.0D, oz = 0.0D;
		int gox, goy, goz;
		int has_owner = 0;
		EntityLivingBase e = null;
		double speed_factor = 0.5D;
		double var1 = 0.0D;
		double var3 = 0.0D;
		double var5 = 0.0D;
		double dx;
		double dz;
		double unused3;
		double gh = 1.25D;
		double obstruction_factor = 0.0D;
		double velocity = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		
		int toofar = 0;
		
		
		if (this.currentFlightTarget == null) {
			do_new = 1;
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}
		if (this.riddenByEntity != null) return;
		
		
		if (this.isTamed() && this.getOwner() != null) {
			e = this.getOwner();
			has_owner = 1;
			ox = e.posX;
			oy = e.posY;
			oz = e.posZ;
			if (this.getDistanceSqToEntity(e) > 400.0D) {
				toofar = 1;
				this.target_in_sight = false;
				this.setAttacking(0);
				this.setSitting(false);
				this.flyaway = 0;
				do_new = 1;
			}
		}
		
		if (this.isSitting()) return;
		
		if (this.posY < (double)this.currentFlightTarget.posY + 2.0D) {
			this.motionY *= 0.7;
		} else if (this.posY > (double)this.currentFlightTarget.posY - 2.0D) {
			this.motionY *= 0.5D;
		} else {
			this.motionY *= 0.61;
		}
		
		
		if (this.worldObj.rand.nextInt(300) == 1) do_new = 1;
		if (this.flyaway > 0) this.flyaway--;
		
		
		if (toofar == 0 && this.flyaway == 0 && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.rand.nextInt(7) == 1)
		{
			
			e = this.getAttackTarget();
			if (e != null && !e.isEntityAlive()) {
				this.setAttackTarget(null);
				e = null;
			}

			if (e == null) {
				e = this.findSomethingToAttack();
			}
			if (e != null)
			{
				if (this.isTamed() && this.getHealth() / (float)this.mygetMaxHealth() < 0.25F) {
					this.setActivity(1);
					this.setAttacking(0);
					this.target_in_sight = false;
					do_new = 0;
					this.currentFlightTarget.set((int)(this.posX + (this.posX - e.posX)), (int)(this.posY + 1.0D), (int)(this.posZ + (this.posZ - e.posZ)));
				} else
				{
					this.setActivity(1);
					this.setAttacking(1);
					this.target_in_sight = true;
					this.currentFlightTarget.set((int)e.posX, (int)(e.posY + 1.0D), (int)e.posZ);
					
					do_new = 0;
					if (this.getDistanceSqToEntity(e) < (double)((8.0F + e.width / 2.0F) * (8.0F + e.width / 2.0F))) {
						this.attackEntityAsMob(e);
						this.flyaway = 5 + this.worldObj.rand.nextInt(15);
						do_new = 1;
					} else if (this.getDistanceSqToEntity(e) < 400.0D && !this.isInWater())
					{
						if (this.getThePrinceTeenFire() != 0 && this.worldObj.rand.nextInt(2) == 1) {
							this.shoot_something(e.posX, e.posY, e.posZ);
						}
					}
					
				}
			} else
			{
				this.target_in_sight = false;
				this.flyaway = 0;
				this.setAttacking(0);
			}
		}
		
		
		if (this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 2.1F) {
			do_new = 1;
		}
		
		
		if (do_new != 0 && !this.target_in_sight || do_new != 0 && this.flyaway != 0)
		{
			bid = Blocks.stone;
			while (bid != Blocks.air && keep_trying != 0) {
				gox = (int)this.posX;
				goy = (int)this.posY;
				goz = (int)this.posZ;
				if (has_owner == 1) {
					gox = (int)ox;
					goy = (int)oy;
					goz = (int)oz;
					if (this.owner_flying == 0) {
						zdir = this.worldObj.rand.nextInt(14) + 5;
						xdir = this.worldObj.rand.nextInt(14) + 5;
					} else {
						zdir = this.worldObj.rand.nextInt(6);
						xdir = this.worldObj.rand.nextInt(6);
					}
				} else {
					zdir = this.worldObj.rand.nextInt(10) + 16;
					xdir = this.worldObj.rand.nextInt(10) + 16;
				}
				if (this.worldObj.rand.nextInt(2) == 1) zdir = -zdir;
				if (this.worldObj.rand.nextInt(2) == 1) xdir = -xdir;
				this.currentFlightTarget.set(gox + xdir, goy + this.worldObj.rand.nextInt(9 + this.owner_flying * 2) - 4, goz + zdir);
				bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
				if (bid == Blocks.air) {
					if (!this.canSeeTarget((double)this.currentFlightTarget.posX, (double)this.currentFlightTarget.posY, (double)this.currentFlightTarget.posZ)) {
						bid = Blocks.stone;
					}
				}
				keep_trying--;
			}
		}
		
		
		
		
		
		obstruction_factor = 0.0D;
		dist = 2;
		dist += (int)(velocity * 4.0D);
		
		for (k = 1; k < dist; k++) {
			for (i = 1; i < dist * 2; i++) {
				dx = (double)i * Math.cos(Math.toRadians((double)(this.rotationYaw + 90.0F)));
				dz = (double)i * Math.sin(Math.toRadians((double)(this.rotationYaw + 90.0F)));
				bid = this.worldObj.getBlock((int)(this.posX + dx), (int)this.posY - k, (int)(this.posZ + dz));
				if (bid != Blocks.air) {
					obstruction_factor += 0.05;
				}
			}
		}
		this.motionY += obstruction_factor * 0.05;
		this.posY += obstruction_factor * 0.05;
		
		
		speed_factor = 0.5D;
		var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
		var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
		var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
		if (this.owner_flying != 0) {
			speed_factor = 1.75D;
			if (this.isTamed() && this.getOwner() != null) {
				e = this.getOwner();
				if (this.getDistanceSqToEntity(e) > 64.0D) {
					speed_factor = 3.5D;
				}
			}
		}
		this.motionX += (Math.signum(var1) - this.motionX) * 0.15 * speed_factor;
		this.motionY += (Math.signum(var3) - this.motionY) * 0.21 * speed_factor;
		this.motionZ += (Math.signum(var5) - this.motionZ) * 0.15 * speed_factor;
		float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = (float)(0.75D * speed_factor);
		
		this.rotationYaw += var8 / 4.0F;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	
	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		List list = null;
		Entity listEntity = null;
		double velocity;
		double d4;
		double d5;
		double d6 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
		double d7 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7;
		double d8; // unused
		double d9; // unused
		double d10;
		double d11;
		double newvelocity;
		double obstruction_factor = 0.0D;
		double unused1;
		double relative_g = 0.0D;
		double dx, dz;
		double max_speed = 0.95;
		double gh = 1.0D;
		double rr;
		double rhm;
		double rhdir;
		double rt = 0.0D;
		double rdv;
		double pi = 3.1415926545;
		double deltav = 0.0D;
		float im;
		int i, k, l, unused2;
		Block bid;
		int dist = 2;
		double cx, cz, cy;
		double unused3;
		
		
		if (this.getActivity() == 0)
		{
			super.onLivingUpdate();
			
		}
		else if (this.isDead) {
			super.onLivingUpdate();
			return;
		}
		

		if (this.isDead) return;
		
		
		
		
		
		
		
		
		
		
		
		
		if (this.worldObj.isRemote)
		{
			
			
			
			if (this.boatPosRotationIncrements > 0 && this.getActivity() != 0)
			{
				d4 = this.posX + (this.boatX - this.posX) / (double)this.boatPosRotationIncrements;
				d5 = this.posY + (this.boatY - this.posY) / (double)this.boatPosRotationIncrements;
				d11 = this.posZ + (this.boatZ - this.posZ) / (double)this.boatPosRotationIncrements;
				this.setPosition(d4, d5, d11);
				
				this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
				d10 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)this.rotationYaw);
				if (this.riddenByEntity != null) d10 = MathHelper.wrapAngleTo180_double((double)this.riddenByEntity.rotationYaw - (double)this.rotationYaw);
				this.rotationYaw = (float)((double)this.rotationYaw + d10 / (double)this.boatPosRotationIncrements);
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.rotationYawHead = this.rotationYaw;
				
				this.boatPosRotationIncrements--;
			}
		} else
		{
			if (this.getActivity() != 0)
			{
				
				
				
				
				if (this.fireballticker > 0) this.fireballticker--;

				if (this.riddenByEntity != null) {
					EntityPlayer pp = (EntityPlayer)this.riddenByEntity;
					
					
					
					if (this.motionX < -2.0D) this.motionX = -2.0D;
					if (this.motionX > 2.0D) this.motionX = 2.0D;
					if (this.motionZ < -2.0D) this.motionZ = -2.0D;
					if (this.motionZ > 2.0D) this.motionZ = 2.0D;
					velocity = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
					
					
					gh = 1.25D;
					bid = this.worldObj.getBlock((int)this.posX, (int)((float)this.posY - (float)gh), (int)this.posZ);
					if (bid != Blocks.air) {
						this.motionY += 0.03;
						this.posY += 0.1;
					} else
					{
						this.motionY -= 0.018;
					}

					
					
					
					
					obstruction_factor = 0.0D;
					dist = 3;
					dist += (int)(velocity * 7.0D);

					for (k = 1; k < dist; k++) {
						for (i = 1; i < dist * 2; i++) {
							dx = (double)i * Math.cos(Math.toRadians((double)(this.rotationYaw + 90.0F)));
							dz = (double)i * Math.sin(Math.toRadians((double)(this.rotationYaw + 90.0F)));
							bid = this.worldObj.getBlock((int)(this.posX + dx), (int)this.posY - k, (int)(this.posZ + dz));
							if (bid != Blocks.air) {
								obstruction_factor += 0.05;
							}
						}
					}

					
					
					this.motionY += obstruction_factor * 0.07;
					this.posY += obstruction_factor * 0.07;
					if (this.motionY > 2.0D) this.motionY = 2.0D;

					
					
					
					
					
					
					
					
					
					d4 = (double)this.riddenByEntity.rotationYaw;
					d4 %= 360.0D;
					while (d4 < 0.0D) d4 += 360.0D;
					d5 = (double)this.rotationYaw;
					d5 %= 360.0D;
					while (d5 < 0.0D) d5 += 360.0D;
					relative_g = (d4 - d5) % 180.0D;
					while (relative_g < 0.0D) relative_g += 180.0D;
					if (relative_g > 90.0D) relative_g -= 180.0D;

					
					
					
					
					
					if (velocity > 0.01)
					{
						d4 = 1.85 - velocity;
						d4 = Math.abs(d4);
						if (d4 < 0.01) d4 = 0.01;
						if (d4 > 0.9) d4 = 0.9;
						this.rotationYaw = this.riddenByEntity.rotationYaw + (float)(relative_g * d4);
					} else
					{
						this.rotationYaw = this.riddenByEntity.rotationYaw;
					}
					relative_g = Math.abs(relative_g) * velocity;
					if (relative_g > 50.0D) relative_g = 0.0D;

					
					
					
					this.rotationPitch = 2.0F * (float)velocity;
					this.setRotation(this.rotationYaw, this.rotationPitch);
					
					
					
					
					
					newvelocity = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
					
					
					
					
					
					
					
					rr = Math.atan2(this.riddenByEntity.motionZ, this.riddenByEntity.motionX);
					rhm = Math.atan2(this.motionZ, this.motionX);
					rhdir = Math.toRadians((double)((this.riddenByEntity.rotationYaw + 90.0F) % 360.0F));
					rt = 0.0D;
					pi = 3.1415926545;
					deltav = 0.0D;
					im = pp.moveForward;
					
					
					if (OreSpawnMain.flyup_keystate != 0) {
						this.motionY += 0.035;
						this.motionY += velocity * 0.046;
					}

					
					rdv = Math.abs(rhm - rhdir) % (pi * 2.0D);
					if (rdv > pi) rdv -= pi * 2.0D;
					rdv = Math.abs(rdv);
					if (Math.abs(newvelocity) < 0.01) rdv = 0.0D;
					
					
					
					
					
					
					
					if (rdv > 1.5D) newvelocity = -newvelocity;

					if (Math.abs(im) > 0.001F) {
						if (im > 0.0F) {
							deltav = 0.025;
							if (max_speed > 1.0D) deltav += 0.05;
							if (this.deltasmooth < 0.0F) this.deltasmooth = 0.0F;
							this.deltasmooth += deltav / 10.0D;
							if ((double)this.deltasmooth > deltav) this.deltasmooth = (float)deltav;
							
						} else {
							max_speed = 0.35;
							
							deltav = -0.02;
							if (this.deltasmooth > 0.0F) this.deltasmooth = 0.0F;
							this.deltasmooth += deltav / 10.0D;
							if ((double)this.deltasmooth < deltav) this.deltasmooth = (float)deltav;
						}

						newvelocity += (double)this.deltasmooth;
						if (newvelocity >= 0.0D) {
							if (newvelocity > max_speed) newvelocity = max_speed;
							this.motionX = Math.cos(Math.toRadians((double)(this.rotationYaw + 90.0F))) * newvelocity;
							this.motionZ = Math.sin(Math.toRadians((double)(this.rotationYaw + 90.0F))) * newvelocity;
						} else {
							if (newvelocity < -max_speed) newvelocity = -max_speed;
							newvelocity = -newvelocity;
							this.motionX = Math.cos(Math.toRadians((double)(this.rotationYaw + 270.0F))) * newvelocity;
							this.motionZ = Math.sin(Math.toRadians((double)(this.rotationYaw + 270.0F))) * newvelocity;
						}
						
					}
					else if (newvelocity >= 0.0D) {
						this.motionX = Math.cos(Math.toRadians((double)(this.rotationYaw + 90.0F))) * newvelocity;
						this.motionZ = Math.sin(Math.toRadians((double)(this.rotationYaw + 90.0F))) * newvelocity;
					} else {
						this.motionX = Math.cos(Math.toRadians((double)(this.rotationYaw + 270.0F))) * (newvelocity * -1.0D);
						this.motionZ = Math.sin(Math.toRadians((double)(this.rotationYaw + 270.0F))) * (newvelocity * -1.0D);
					}

					
					
					if (this.fireballticker == 0 && (pp.moveStrafing < -0.001F || pp.moveStrafing > 0.001F))
					{
						double yoff = 1.5D;
						double xzoff = 7.5D;
						
						this.which_attack++;
						if (this.which_attack > 2) this.which_attack = 0;
						
						
						if (this.which_attack == 0) {
							yoff += (double)((float)this.getHead1Ext() * 0.04F);
							cx = this.posX - xzoff * Math.sin(Math.toRadians((double)(this.rotationYaw - 10.0F)));
							cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)(this.rotationYaw - 10.0F)));
							BetterFireball bf = new BetterFireball(this.worldObj, this, 0.0D, 0.0D, 0.0D);
							bf.setNotMe();
							bf.setPosition(cx, this.posY + yoff, cz);
							cx = Math.cos(Math.toRadians((double)(pp.rotationYawHead + 90.0F)));
							cz = Math.sin(Math.toRadians((double)(pp.rotationYawHead + 90.0F)));
							cy = -Math.sin(Math.toRadians((double)pp.rotationPitch));
							
							double d3 = (double)MathHelper.sqrt_double(cx * cx + cy * cy + cz * cz);
							bf.accelerationX = cx / d3 * 0.1;
							bf.accelerationY = cy / d3 * 0.1;
							bf.accelerationZ = cz / d3 * 0.1;
							bf.motionX = this.motionX;
							bf.motionY = this.motionY;
							bf.motionZ = this.motionZ;
							bf.posX -= this.motionX * 3.0D;
							bf.posY -= this.motionY * 3.0D;
							bf.posZ -= this.motionZ * 3.0D;
							this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
							this.worldObj.spawnEntityInWorld(bf);
						}

						if (this.which_attack == 1) {
							yoff += (double)((float)this.getHead3Ext() * 0.04F);
							cx = this.posX - xzoff * Math.sin(Math.toRadians((double)(this.rotationYaw + 10.0F)));
							cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)(this.rotationYaw + 10.0F)));
							IceBall var2 = new IceBall(this.worldObj, cx, this.posY + yoff, cz);
							var2.setLocationAndAngles(cx, this.posY + yoff, cz, pp.rotationYaw + 90.0F, pp.rotationPitch);
							var2.setIceMaker(1);
							double var3 = Math.cos(Math.toRadians((double)(pp.rotationYaw + 90.0F)));
							double var5 = -Math.sin(Math.toRadians((double)pp.rotationPitch));
							double var77 = Math.sin(Math.toRadians((double)(pp.rotationYaw + 90.0F)));
							float var9 = MathHelper.sqrt_double(var3 * var3 + var77 * var77) * 0.2F;
							var2.setThrowableHeading(var3, var5 + (double)var9, var77, 1.4F, 5.0F);
							var2.posX -= this.motionX * 3.0D;
							var2.posY -= this.motionY * 3.0D;
							var2.posZ -= this.motionZ * 3.0D;
							var2.motionX *= 2.0D;
							var2.motionY *= 2.0D;
							var2.motionZ *= 2.0D;
							this.worldObj.playSoundAtEntity(this, "fireworks.launch", 0.75F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
							this.worldObj.spawnEntityInWorld(var2);
						}

						if (this.which_attack == 2) {
							yoff += (double)((float)this.getHead2Ext() * 0.04F);
							cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
							cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
							ThunderBolt lb = new ThunderBolt(this.worldObj, pp);
							lb.setLocationAndAngles(cx, this.posY + yoff, cz, pp.rotationYaw + 90.0F, pp.rotationPitch);
							lb.motionX *= 3.0D;
							lb.motionY *= 3.0D;
							lb.motionZ *= 3.0D;
							this.worldObj.playSoundAtEntity(this, "random.bow", 0.75F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
							this.worldObj.spawnEntityInWorld(lb);
						}
						this.fireballticker = 10;
					}

					
					
					this.moveEntity(this.motionX, this.motionY, this.motionZ);
					
					
					this.motionX *= 0.985;
					this.motionY *= 0.94;
					this.motionZ *= 0.985;
					
					
					
					
					if (!this.worldObj.isRemote)
					{
						list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(3.25D, 4.0D, 3.25D));
						
						if (list != null && !list.isEmpty())
						{
							for (l = 0; l < list.size(); l++)
							{
								listEntity = (Entity)list.get(l);
								
								if (listEntity != this.riddenByEntity && !listEntity.isDead && listEntity.canBePushed())
								{
									listEntity.applyEntityCollision(this);
								}
							}
						}
					}

					
					
					this.fly_with_rider();
					
					if (this.riddenByEntity != null && this.riddenByEntity.isDead)
					{
						this.riddenByEntity = null;
					}
				} else
				{
					this.fly_without_rider();
				}
			}

			
			
			this.always_do();
		}
	}

	
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			
			
			float f = 0.65F;
			this.riddenByEntity.setPosition(this.posX - (double)f * Math.sin(Math.toRadians((double)this.rotationYaw)), this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + (double)f * Math.cos(Math.toRadians((double)this.rotationYaw)));
		}

		
	}
	
	
	/**
	 * Play the taming effect, will either be hearts or smoke depending on status
	 */
	protected void playTameEffect(boolean par1)
	{
		String s = "heart";
		
		if (!par1)
		{
			s = "smoke";
		}

		for (int i = 0; i < 20; i++)
		{
			double d0 = this.rand.nextGaussian() * 0.08;
			double d1 = this.rand.nextGaussian() * 0.08;
			double d2 = this.rand.nextGaussian() * 0.08;
			this.worldObj.spawnParticle(s, this.posX + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 2.5F), this.posY + 0.5D + (double)this.rand.nextFloat() * 1.5D, this.posZ + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 2.5F), d0, d1, d2);
		}

		
	}
	
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		
		if (var2 != null) {
			if (var2.stackSize <= 0) {
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				var2 = null;
			}
		}

		if (var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.diamond_block) && par1EntityPlayer.getDistanceSqToEntity((Entity)this) < 25.0D) {
			if (!this.worldObj.isRemote)
			{
				this.setTamed(true);
				this.func_152115_b(par1EntityPlayer.getUniqueID().toString());
				this.playTameEffect(true);
				this.worldObj.setEntityState((Entity)this, (byte)7);
				this.heal(mygetMaxHealth() - getHealth());
				this.kill_count = 1000;
				this.day_count = 1000;
			}
			
			
			if (!par1EntityPlayer.capabilities.isCreativeMode) {
				var2.stackSize--;
				if (var2.stackSize <= 0) {
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}
			return true;
		}
		
		if (this.isTamed())
		{
			if (!this.func_152114_e(par1EntityPlayer)) {
				return false;
			}

			if (var2 == null && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D) {
				if (!this.worldObj.isRemote)
				{
					par1EntityPlayer.mountEntity(this);
					
					this.setActivity(1);
					this.setSitting(false);
				}
				return true;
			}

			if (var2 != null && var2.getItem() == Items.beef && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D) {
				if (this.worldObj.isRemote) {
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)7);
				}

				if ((float)this.mygetMaxHealth() > this.getHealth()) {
					this.heal((float)this.mygetMaxHealth() - this.getHealth());
				}
				if (!par1EntityPlayer.capabilities.isCreativeMode) {
					var2.stackSize--;
					if (var2.stackSize <= 0) {
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
					}
				}
				return true;
			}

			if (var2 != null && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D && var2.getItem() instanceof ItemFood)
			{
				
				if (!this.worldObj.isRemote)
				{
					ItemFood var3 = (ItemFood)var2.getItem();
					
					if ((float)this.mygetMaxHealth() > this.getHealth())
					{
						this.heal((float)(var3.func_150905_g(var2) * 10));
					}

					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)7);
				}

				if (!par1EntityPlayer.capabilities.isCreativeMode)
				{
					var2.stackSize--;
					if (var2.stackSize <= 0)
					{
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
					}
				}
				return true;
			}

			
			if (var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.ice) && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D)
			{
				if (!this.worldObj.isRemote)
				{
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)6);
					this.setThePrinceTeenFire(0);
					String healthMessage = new String();
					healthMessage = healthMessage.format("Fireballs extinguished.");
					par1EntityPlayer.addChatComponentMessage(new ChatComponentText(healthMessage));
				}
				if (!par1EntityPlayer.capabilities.isCreativeMode)
				{
					var2.stackSize--;
					if (var2.stackSize <= 0)
					{
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
					}
				}
				return true;
			}

			if (var2 != null && var2.getItem() == Items.flint_and_steel && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D)
			{
				if (!this.worldObj.isRemote)
				{
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)6);
					this.setThePrinceTeenFire(1);
					String healthMessage = new String();
					healthMessage = healthMessage.format("Fireballs lit!");
					par1EntityPlayer.addChatComponentMessage(new ChatComponentText(healthMessage));
				}
				if (!par1EntityPlayer.capabilities.isCreativeMode)
				{
					var2.stackSize--;
					if (var2.stackSize <= 0)
					{
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
					}
				}
				return true;
			}

			
			if (var2 != null && var2.getItem() == Items.diamond && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D && !this.worldObj.isRemote)
			{
				
				
				Entity ent = null;
				ThePrince d = null;
				ent = spawnCreature(this.worldObj, "The Prince", this.posX, this.posY, this.posZ);
				if (ent != null) {
					d = (ThePrince)ent;
					if (this.isTamed()) {
						d.setTamed(true);
						d.func_152115_b(par1EntityPlayer.getUniqueID().toString());
						d.set_ok_to_grow();
					}
					this.setDead();
				}
				if (!par1EntityPlayer.capabilities.isCreativeMode)
				{
					var2.stackSize--;
					if (var2.stackSize <= 0)
					{
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
					}
				}
				return true;
			}

			if (this.isTamed() && var2 != null && var2.getItem() == Items.name_tag && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D && this.func_152114_e(par1EntityPlayer))
			{
				this.setCustomNameTag(var2.getDisplayName());
				if (!par1EntityPlayer.capabilities.isCreativeMode)
				{
					var2.stackSize--;
					if (var2.stackSize <= 0)
					{
						par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
					}
				}
				return true;
			}

			if (var2 != null && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D)
			{
				if (!this.isSitting()) {
					this.setSitting(true);
					this.setActivity(0);
				} else {
					this.setSitting(false);
					this.setActivity(0);
				}
				return true;
			}
		}

		return false;
	}

	
	
	
	public boolean isWheat(ItemStack par1ItemStack)
	{
		return par1ItemStack != null && par1ItemStack.getItem() == Items.beef;
	}

	
	public int getAttacking()
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	public void setAttacking(int par1)
	{
		if (this.worldObj != null && this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(20, par1);
	}

	public int getActivity()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	public void setActivity(int par1)
	{
		if (this.worldObj != null && this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(21, par1);
	}

	
	public int getThePrinceTeenFire()
	{
		return this.dataWatcher.getWatchableObjectInt(24);
	}

	
	public void setThePrinceTeenFire(int par1)
	{
		if (this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(24, par1);
	}

	
	
	
	
	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6)
	{
		Entity var8 = null;
		
		
		var8 = EntityList.createEntityByName(par1, par0World);
		
		if (var8 != null)
		{
			
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			
			
			par0World.spawnEntityInWorld(var8);
			
			((EntityLiving)var8).playLivingSound();
		}

		return var8;
	}

	
	
	
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		return null;
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn()
	{
		if (this.isNoDespawnRequired()) return false;
		if (this.riddenByEntity != null) {
			return false;
		}
		if (this.isTamed()) {
			return false;
		}
		return true;
	}

	
	
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setInteger("ThePrinceTeenAttacking", this.getAttacking());
		par1NBTTagCompound.setInteger("ThePrinceTeenActivity", this.getActivity());
		par1NBTTagCompound.setInteger("ThePrinceTeenFire", this.getThePrinceTeenFire());
		par1NBTTagCompound.setInteger("SpyroKill", this.kill_count);
		par1NBTTagCompound.setInteger("SpyroDay", this.day_count);
		
	}
	
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		
		this.setAttacking(par1NBTTagCompound.getInteger("ThePrinceTeenAttacking"));
		this.setActivity(par1NBTTagCompound.getInteger("ThePrinceTeenActivity"));
		this.setThePrinceTeenFire(par1NBTTagCompound.getInteger("ThePrinceTeenFire"));
		this.kill_count = par1NBTTagCompound.getInteger("SpyroKill");
		this.day_count = par1NBTTagCompound.getInteger("SpyroDay");
		
	}
	
	private void shoot_something(double x, double y, double z)
	{
		double rr = 0.0D;
		double rhdir = 0.0D;
		double rdd = 0.0D;
		double pi = 3.1415926545;
		
		int which = this.worldObj.rand.nextInt(3);
		
		
		
		if (which == 0)
		{
			
			rr = Math.atan2(z - this.posZ, x - this.posX);
			rhdir = Math.toRadians((double)((this.rotationYaw + 90.0F) % 360.0F));
			
			rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
			if (rdd > pi) rdd -= pi * 2.0D;
			rdd = Math.abs(rdd);
			
			if (rdd < 0.5D) {
				this.firecanon(x, y, z);
			}
		} else if (which == 1)
		{
			
			
			rr = Math.atan2(z - this.posZ, x - this.posX);
			rhdir = Math.toRadians((double)((this.rotationYaw + 90.0F) % 360.0F));
			
			rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
			if (rdd > pi) rdd -= pi * 2.0D;
			rdd = Math.abs(rdd);
			
			if (rdd < 0.5D) {
				this.firecanonl(x, y, z);
			}
			
		}
		else
		{
			rr = Math.atan2(z - this.posZ, x - this.posX);
			rhdir = Math.toRadians((double)((this.rotationYaw + 90.0F) % 360.0F));
			
			rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
			if (rdd > pi) rdd -= pi * 2.0D;
			rdd = Math.abs(rdd);
			
			if (rdd < 0.5D) {
				this.firecanoni(x, y, z);
			}
		}
		
	}
	
	private void firecanon(double x, double y, double z)
	{
		double yoff = 3.5D;
		double xzoff = 6.0D;
		double cx, cz;
		float r1, r2, r3;
		BetterFireball bf = null;
		
		cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		r1 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		r2 = 3.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		r3 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		bf = new BetterFireball(this.worldObj, this, x - cx + (double)r1, y + 0.25D - (this.posY + yoff) + (double)r2, z - cz + (double)r3);
		bf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
		bf.setPosition(cx, this.posY + yoff, cz);
		bf.setBig();
		this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.worldObj.spawnEntityInWorld(bf);
	}

	private void firecanonl(double x, double y, double z)
	{
		double yoff = 3.5D;
		double xzoff = 6.0D;
		double cx, cz;
		float r1, r2, r3;
		int unused1;
		double var3 = 0.0D;
		double var5 = 0.0D;
		double var7 = 0.0D;
		float var9 = 0.0F;
		cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		
		this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		
		r1 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		r2 = 3.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		r3 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		ThunderBolt lb = new ThunderBolt(this.worldObj, cx, this.posY + yoff, cz);
		lb.setLocationAndAngles(cx, this.posY + yoff, cz, 0.0F, 0.0F);
		var3 = x - lb.posX;
		var5 = y + 0.25D - lb.posY;
		var7 = z - lb.posZ;
		var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
		lb.setThrowableHeading(var3, var5 + (double)var9, var7, 1.4F, 4.0F);
		lb.motionX *= 3.0D;
		lb.motionY *= 3.0D;
		lb.motionZ *= 3.0D;
		this.worldObj.spawnEntityInWorld(lb);
	}

	private void firecanoni(double x, double y, double z)
	{
		double yoff = 3.5D;
		double xzoff = 6.0D;
		double cx, cz;
		float r1, r2, r3;
		int unused1;
		double var3 = 0.0D;
		double var5 = 0.0D;
		double var7 = 0.0D;
		float var9 = 0.0F;
		cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		
		this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		
		r1 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		r2 = 3.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		r3 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
		IceBall lb = new IceBall(this.worldObj, cx, this.posY + yoff, cz);
		lb.setIceMaker(1);
		lb.setLocationAndAngles(cx, this.posY + yoff, cz, 0.0F, 0.0F);
		var3 = x - lb.posX;
		var5 = y + 0.25D - lb.posY;
		var7 = z - lb.posZ;
		var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
		lb.setThrowableHeading(var3, var5 + (double)var9, var7, 1.4F, 4.0F);
		lb.motionX *= 3.0D;
		lb.motionY *= 3.0D;
		lb.motionZ *= 3.0D;
		this.worldObj.spawnEntityInWorld(lb);
	}
}
