package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;






























public class Cephadrome extends EntityCreature
{
	private int boatPosRotationIncrements;
	private double boatX;
	private double boatY;
	private double boatZ;
	private double boatYaw;
	private double boatPitch;
	private double boatYawHead;
	private int damage_counter = 100;
	private int updateit = 1;
	private int color = 1;
	private int playing = 0;
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private int hurt_timer = 0;
	private int wasfed;
	private int shouldattack = 0;
	private int wing_sound = 0;
	private int hit_by_player = 0;
	private int badmood = 0;
	private float moveSpeed = 0.25F;

	
	
	
	public Cephadrome(World par1World)
	{
		super(par1World);
		this.setSize(2.5F, 2.25F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 200;
		this.fireResistance = 100;
		this.isImmuneToFire = false;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 9.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.riddenByEntity = null;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
	}

	public Cephadrome(World par1World, double par2, double par4, double par6)
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)70.0F);
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
		return 128;
	}

	public int getUpdateFrequency() {
		return 10;
	}

	public boolean sendsVelocityUpdates() {
		return true;
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
		this.dataWatcher.addObject(20, (byte)0);
		this.dataWatcher.addObject(21, (byte)0);
		
		this.setActivity(0);
		this.setAttacking(0);
		
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
		return 300;
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
		return 16;
	}

	protected void jump()
	{
		super.jump();
		this.motionY += 0.1;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	public String getLivingSound()
	{
		if (this.getActivity() != 1 && this.rand.nextInt(6) == 1) {
			return "orespawn:MothraWings";
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
		return 1.5F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	public float getSoundPitch() {
		return 1.0F;
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
		return 2.5D;
	}

	
	
	
	protected Item getDropItem()
	{
		return Items.beef;
	}

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), is);
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3, var4, i;
		ItemStack is;
		i = 4 + this.worldObj.rand.nextInt(6);
		for (var4 = 0; var4 < i; var4++) {
			this.dropItemRand(OreSpawnMain.UraniumNugget, 1);
		}

		i = 4 + this.worldObj.rand.nextInt(6);
		for (var4 = 0; var4 < i; var4++) {
			this.dropItemRand(OreSpawnMain.TitaniumNugget, 1);
		}

		i = 1 + this.worldObj.rand.nextInt(5);
		for (var4 = 0; var4 < i; var4++) {
			var3 = this.worldObj.rand.nextInt(20);
			switch (var3) {
				case 0:
					is = this.dropItemRand(OreSpawnMain.MyRubySword, 1);
					break;
				case 1:
					is = this.dropItemRand(Items.diamond, 1);
					break;
				case 2:
					is = this.dropItemRand(OreSpawnMain.MyThunderStaff, 1);
					break;
				case 3:
					is = this.dropItemRand(OreSpawnMain.MyRubySword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 4:
					is = this.dropItemRand(OreSpawnMain.MyRubyShovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 5:
					is = this.dropItemRand(OreSpawnMain.MyRubyPickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 6:
					is = this.dropItemRand(OreSpawnMain.MyRubyAxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 7:
					is = this.dropItemRand(OreSpawnMain.MyRubyHoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 8:
					is = this.dropItemRand(OreSpawnMain.RubyHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(2));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 9:
					is = this.dropItemRand(OreSpawnMain.RubyBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 10:
					is = this.dropItemRand(OreSpawnMain.RubyLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 11:
					is = this.dropItemRand(OreSpawnMain.RubyBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 12:
				case 13:
				case 14:
				case 15:
				case 16:
				case 17:
					is = this.dropItemRand(OreSpawnMain.MyRuby, 1);
					break;
				default:
					break;
			}
		}
	}
	

	
	public int getCephadromeHealth()
	{
		return (int)this.getHealth();
	}

	
	
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		double ks = 2.5D;
		double inair = 0.35;
		float iskraken = 1.0F;
		boolean ret = false;
		if (par1Entity != null && par1Entity instanceof EntityDragon) {
			EntityDragon dr = (EntityDragon)par1Entity;
			DamageSource var21 = null;
			var21 = DamageSource.setExplosionSource(null);
			var21.setExplosion();
			if (this.worldObj.rand.nextInt(6) == 1) {
				dr.attackEntityFromPart(dr.dragonPartHead, var21, 70.0F);
			} else {
				dr.attackEntityFromPart(dr.dragonPartBody, var21, 70.0F);
			}
			ret = true;
		}
		else if (par1Entity != null && par1Entity instanceof EntityLivingBase)
		{
			if (par1Entity instanceof Kraken) iskraken = 1.5F;
			ret = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), iskraken * 70.0F);
			
			float f3 = (float)Math.atan2(par1Entity.posZ - this.posZ, par1Entity.posX - this.posX);
			if (par1Entity.isDead || par1Entity instanceof EntityPlayer) inair *= 2.0D;
			par1Entity.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
		}

		return ret;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		if (this.hurt_timer > 0) return false;
		
		if (!par1DamageSource.getDamageType().equals("cactus")) {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			this.hurt_timer = 25;
			
			Entity e = par1DamageSource.getEntity();
			if (e != null && e instanceof EntityLivingBase)
			{
				this.setAttackTarget((EntityLivingBase)e);
				this.setTarget(e);
				this.getNavigator().tryMoveToEntityLiving((EntityLivingBase)e, 1.2);
				ret = true;
			}
			if (e != null && e instanceof EntityPlayer && this.getHealth() < this.getMaxHealth() * 9.0F / 10.0F)
			{
				this.hit_by_player = 1;
			}
		}
		return ret;
	}

	
	public double getHorizontalDistanceSqToEntity(Entity par1Entity)
	{
		double d0 = this.posX - par1Entity.posX;
		double d2 = this.posZ - par1Entity.posZ;
		return d0 * d0 + d2 * d2;
	}

	public void updateAITasks()
	{
		EntityLivingBase e = null;
		double maxdist = 10.0D;
		
		if (this.isDead) return;
		
		if (this.updateit > 0) this.updateit--;
		if (this.hurt_timer > 0) this.hurt_timer--;
		
		if (this.updateit <= 0 && !this.worldObj.isRemote) {
			this.updateit = 30;
			if (this.riddenByEntity != null) {
				this.setActivity(1);
			} else {
				this.setActivity(0);
			}
		}
		
		if (this.worldObj.rand.nextInt(100) == 1) {
			if (this.getHealth() < (float)this.mygetMaxHealth()) {
				this.heal(2.0F);
			}
		}
		
		if (this.getActivity() == 0) {
			super.updateAITasks();
		}
		if (this.worldObj.rand.nextInt(7) == 1 && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
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
				if (this.getActivity() == 0) {
					this.getNavigator().tryMoveToEntityLiving(e, 1.7);
					maxdist = 6.0D;
				}
				this.faceEntity(e, 10.0F, 10.0F);
				this.setAttacking(1);
				if (this.getDistanceSqToEntity(e) < (maxdist + (double)(e.width / 2.0F)) * (maxdist + (double)(e.width / 2.0F))) {
					this.attackEntityAsMob(e);
				}
				else if (e instanceof Kraken && this.getHorizontalDistanceSqToEntity(e) < (maxdist + (double)(e.width / 2.0F)) * (maxdist + (double)(e.width / 2.0F))) {
					this.attackEntityAsMob(e);
				}
			}
			
			else if (this.getAttacking() != 0) this.setAttacking(0);
		}
		
		
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
		if (par1EntityLiving instanceof Cephadrome)
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
		if (par1EntityLiving instanceof Leon)
		{
			EntityTameable et = (EntityTameable)par1EntityLiving;
			if (et.isTamed()) return false;
			return true;
		}
		if (par1EntityLiving instanceof GammaMetroid)
		{
			EntityTameable et = (EntityTameable)par1EntityLiving;
			if (et.isTamed()) return false;
			return true;
		}
		if (par1EntityLiving instanceof WaterDragon)
		{
			EntityTameable et = (EntityTameable)par1EntityLiving;
			if (et.isTamed()) return false;
			return true;
		}
		
		if (par1EntityLiving instanceof EntityDragon)
		{
			return true;
		}
		if (par1EntityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (p.capabilities.isCreativeMode == true) {
				return false;
			}
			if (this.hit_by_player != 0) return true;
			if (this.badmood != 0) return true;
			if (this.shouldattack > 0) {
				this.shouldattack = 0;
				return true;
			}
			return false;
		}
		
		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0D, 20.0D, 16.0D));
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
							if (s.equals("Cephadrome")) {
								this.badmood = 1;
								return true;
							}
						}
					}
				}
			}
		}

		if ((!this.worldObj.isDaytime() ? true : false) == true) return false;
		if (this.posY < 50.0D) return false;
		
		
		for (k = -2; k < 2; k++)
		{
			for (j = -2; j < 2; j++)
			{
				for (i = 1; i < 5; i++)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid != Blocks.air) return false;
				}
			}
		}

		
		Cephadrome target = null;
		target = (Cephadrome)this.worldObj.findNearestEntityWithinAABB(Cephadrome.class, this.boundingBox.expand(16.0D, 6.0D, 16.0D), this);
		if (target != null)
		{
			return false;
		}
		return true;
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
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (this.getActivity() == 1) {
			this.wing_sound++;
			if (this.wing_sound > 22)
			{
				if (!this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, "orespawn:MothraWings", 0.5F, 1.0F);
				this.wing_sound = 0;
			}
		}
		if (OreSpawnMain.PlayNicely == 0) this.wasfed = 1;
		
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
		double max_speed = 1.15;
		double gh = 1.0D;
		double rr;
		double rhm;
		double rhdir;
		double rt = 0.0D;
		double unused2, rdv;
		double pi = 3.1415926545;
		double deltav = 0.0D;
		double im;
		int i, k, l, unused3;
		Block bid;
		int dist = 2;
		int unused4, unused5;
		
		
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
				
				
				this.boatPosRotationIncrements--;
				
			}
			
		}
		else if (this.getActivity() != 0)
		{
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			if (this.riddenByEntity != null) {
				EntityPlayer pp = (EntityPlayer)this.riddenByEntity;
				
				
				
				if (this.motionX < -2.0D) this.motionX = -2.0D;
				if (this.motionX > 2.0D) this.motionX = 2.0D;
				if (this.motionZ < -2.0D) this.motionZ = -2.0D;
				if (this.motionZ > 2.0D) this.motionZ = 2.0D;
				velocity = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
				
				gh = 1.55;
				bid = this.worldObj.getBlock((int)this.posX, (int)((float)this.posY - (float)gh), (int)this.posZ);
				if (bid != Blocks.air) {
					this.motionY += 0.07;
					this.posY += 0.1;
				} else {
					this.motionY -= 0.018;
				}

				
				
				
				
				obstruction_factor = 0.0D;
				dist = 2;
				dist += (int)(velocity * 6.0D);
				for (k = 1; k < dist; k++) {
					for (i = 1; i < dist * 2; i++) {
						dx = (double)i * Math.cos(Math.toRadians((double)(this.rotationYaw + 90.0F)));
						dz = (double)i * Math.sin(Math.toRadians((double)(this.rotationYaw + 90.0F)));
						bid = this.worldObj.getBlock((int)(this.posX + dx), (int)this.posY - k, (int)(this.posZ + dz));
						if (bid != Blocks.air) {
							obstruction_factor += 0.04;
						}
					}
				}
				this.motionY += obstruction_factor * 0.09;
				this.posY += obstruction_factor * 0.09;
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

				
				
				
				
				if (velocity > 0.1) {
					d4 = 1.5D - velocity;
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

				
				
				if (this.motionY > 0.0D) {
					this.rotationPitch = 360.0F - 2.0F * (float)velocity;
				} else {
					this.rotationPitch = 2.0F * (float)velocity;
				}
				this.setRotation(this.rotationYaw, this.rotationPitch);
				
				
				
				
				
				
				newvelocity = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
				
				
				
				
				
				
				
				
				rr = Math.atan2(this.riddenByEntity.motionZ, this.riddenByEntity.motionX);
				rhm = Math.atan2(this.motionZ, this.motionX);
				rhdir = Math.toRadians((double)((this.riddenByEntity.rotationYaw + 90.0F) % 360.0F));
				rt = 0.0D;
				pi = 3.1415926545;
				deltav = 0.0D;
				im = (double)pp.moveForward;
				
				
				if (OreSpawnMain.flyup_keystate != 0) {
					this.motionY += 0.04;
					this.motionY += velocity * 0.05;
				}

				
				rdv = Math.abs(rhm - rhdir) % (pi * 2.0D);
				if (rdv > pi) rdv -= pi * 2.0D;
				rdv = Math.abs(rdv);
				if (Math.abs(newvelocity) < 0.01) rdv = 0.0D;

				
				
				
				
				
				
				if (rdv > 1.5D) newvelocity = -newvelocity;

				if (Math.abs(im) > 0.001F) {
					if (im > 0.0D) {
						deltav = 0.03;
						if (max_speed > 0.85) deltav += 0.05;
						
					} else {
						max_speed = 0.35;
						
						deltav = -0.03;
					}

					newvelocity += deltav;
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
			}

			
			
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			
			this.motionX *= 0.985;
			this.motionY *= 0.94;
			this.motionZ *= 0.985;
			
			
			
			
			if (!this.worldObj.isRemote)
			{
				list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(2.25D, 2.0D, 2.25D));
				
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
			if (this.riddenByEntity != null && this.riddenByEntity.isDead)
			{
				this.riddenByEntity = null;
			}
		}
		
		
		
		if (this.getActivity() == 1) {
			this.updateAITasks();
		}
		
	}
	
	
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			
			float f = 0.75F;
			this.riddenByEntity.setPosition(this.posX - (double)f * Math.sin(Math.toRadians((double)this.rotationYaw)), this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + (double)f * Math.cos(Math.toRadians((double)this.rotationYaw)));
		}
		
		
	}
	
	
	
	
	
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
		if (var2 != null && (var2.getItem() == Items.beef || var2.getItem() == Items.chicken || var2.getItem() == Items.porkchop) && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D)
		{
			
			if (!this.worldObj.isRemote)
			{
				this.heal((float)this.mygetMaxHealth() - this.getHealth());
			}
			this.wasfed = 1;
			this.shouldattack = 0;
			this.playTameEffect(true);
			if (!par1EntityPlayer.capabilities.isCreativeMode)
			{
				var2.stackSize--;
				if (var2.stackSize <= 0)
				{
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
					var2 = null;
				}
			}
		} else {
			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != par1EntityPlayer)
			{
				return true;
			}
			else
			{
				if (var2 == null && par1EntityPlayer.getDistanceSqToEntity(this) < 25.0D)
				{
					if (!this.worldObj.isRemote)
					{
						if (this.wasfed == 0)
						{
							
							this.getNavigator().tryMoveToEntityLiving(par1EntityPlayer, 1.2);
							this.shouldattack = 1;
							return false;
						}
						par1EntityPlayer.mountEntity(this);
						this.wasfed = 0;
					}
				}
			}
			return true;
		}
		return false;
	}

	
	public int getAttacking()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public void setAttacking(int par1)
	{
		if (this.worldObj != null && this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	public int getActivity()
	{
		return this.dataWatcher.getWatchableObjectByte(21);
	}

	public void setActivity(int par1)
	{
		if (this.worldObj != null && this.worldObj.isRemote) return;
		this.dataWatcher.updateObject(21, (byte)par1);
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
		return true;
	}

	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("CephaWasFed", this.wasfed);
		par1NBTTagCompound.setInteger("CephaAttacking", this.getAttacking());
		par1NBTTagCompound.setInteger("CephaActivity", this.getActivity());
		par1NBTTagCompound.setInteger("CephaHitByPlayer", this.hit_by_player);
		par1NBTTagCompound.setInteger("CephaBadMood", this.badmood);
		
	}
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.wasfed = par1NBTTagCompound.getInteger("CephaWasFed");
		this.hit_by_player = par1NBTTagCompound.getInteger("CephaHitByPlayer");
		this.badmood = par1NBTTagCompound.getInteger("CephaBadMood");
		this.setAttacking(par1NBTTagCompound.getInteger("CephaAttacking"));
		this.setActivity(par1NBTTagCompound.getInteger("CephaActivity"));
	}
}
