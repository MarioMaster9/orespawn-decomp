package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;



















public class Godzilla extends EntityMob {

	
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.75F;
	private int hurt_timer = 0;
	private int jumped = 0;
	private int jump_timer = 0;
	private int ticker = 0;
	private RenderInfo renderdata = new RenderInfo();
	private int stream_count = 8;
	private MyEntityAIWanderALot wander = null;
	private int head_found = 0;
	private int large_unknown_detected = 0;

	public Godzilla(World par1World)
	{
		super(par1World);
		if (OreSpawnMain.PlayNicely == 0) {
			this.setSize(9.9F, 25.0F);
		} else {
			this.setSize(2.475F, 6.25F);
		}
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 10000;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.wander = new MyEntityAIWanderALot(this, 15, 1.0D);
		this.tasks.addTask(2, this.wander);
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityLiving.class, 50.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.TargetSorter = new GenericTargetSorter(this);
		this.fireResistance = 10000;
		this.isImmuneToFire = true;
		this.renderDistanceWeight = 12.0D;
	}

	
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Godzilla_stats.attack);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(21, Integer.valueOf(OreSpawnMain.PlayNicely));
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

	public int getPlayNicely()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	
	
	
	public RenderInfo getRenderInfo() {
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

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		if (OreSpawnMain.PlayNicely != 0) return true;
		return false;
	}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.Godzilla_stats.health;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		if (this.large_unknown_detected != 0) return 25;
		return OreSpawnMain.Godzilla_stats.defense;
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
		double xzoff = 0.0D;
		
		double myoff = 20.0D;
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (this.isAirBorne) {
			this.getNavigator().setPath(null, 0.0D);
		}
	}

	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	
	
	
	
	protected void fall(float par1) {}

	
	
	
	
	protected void updateFallState(double par1, boolean par3) {}

	
	
	
	
	protected String getLivingSound() {
		if (this.worldObj.rand.nextInt(5) == 0) {
			return "orespawn:godzilla_living";
		}
		return null;
	}

	
	
	
	
	protected String getHurtSound() {
		return "orespawn:alo_hurt";
	}

	
	
	
	
	protected String getDeathSound() {
		return "orespawn:godzilla_death";
	}

	
	
	
	protected float getSoundVolume() {
		return 1.65F;
	}

	
	
	
	protected float getSoundPitch() {
		return 1.1F;
	}

	
	
	
	
	
	protected Item getDropItem() {
		return null;
	}

	
	
	
	
	protected void jump() {
		for (; this.rotationYaw < 0.0F; this.rotationYaw += 360.0F);
		for (; this.rotationYawHead < 0.0F; this.rotationYawHead += 360.0F);
		for (; this.rotationYaw > 360.0F; this.rotationYaw -= 360.0F);
		for (; this.rotationYawHead > 360.0F; this.rotationYawHead -= 360.0F);

		this.motionY += 0.45F;
		this.posY += 0.5D;
		float f = 0.2F + Math.abs(this.worldObj.rand.nextFloat() * 0.45F);
		this.motionX += (double)f * Math.cos(Math.toRadians((double)(this.rotationYawHead + 90.0F)));
		this.motionZ += (double)f * Math.sin(Math.toRadians((double)(this.rotationYawHead + 90.0F)));
		this.isAirBorne = true;
		this.getNavigator().setPath(null, 0.0D);
	}

	
	
	
	protected void jumpAtEntity(EntityLivingBase e) {
		this.motionY += 1.25D;
		this.posY += 1.55F;
		double d1 = e.posX - this.posX;
		double d2 = e.posZ - this.posZ;
		float d = (float)Math.atan2(d2, d1);
		float f2 = (float)((double)d * 180.0D / Math.PI) - 90.0F;
		this.rotationYaw = f2;
		d1 = Math.sqrt(d1 * d1 + d2 * d2);
		this.motionX += d1 * 0.05 * Math.cos((double)d);
		this.motionZ += d1 * 0.05 * Math.sin((double)d);
		this.isAirBorne = true;
		this.getNavigator().setPath(null, 0.0D);
	}

	
	
	private double getHorizontalDistanceSqToEntity(Entity e) {
		double d1 = e.posZ - this.posZ;
		double d2 = e.posX - this.posX;
		return d1 * d1 + d2 * d2;
	}

	
	
	
	
	public double MygetDistanceSqToEntity(Entity par1Entity) {
		double d0 = this.posX - par1Entity.posX;
		double d1 = par1Entity.posY - this.posY;
		double d2 = this.posZ - par1Entity.posZ;
		if (d1 > 0.0D && d1 < 20.0D) d1 = 0.0D;
		if (d1 > 20.0D) d1 -= 10.0D;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}

	protected void updateAITasks()
	{
		EntityLivingBase e = null;
		
		int xzrange = 9;
		
		
		if (this.isDead){ return; }
			if (this.worldObj.isRemote){ return;}
				this.dataWatcher.updateObject(21, Integer.valueOf(OreSpawnMain.PlayNicely));
				
				super.updateAITasks();
				
				this.ticker++;
				if (this.ticker > 30000) this.ticker = 0;
				if (this.ticker % 100 == 0) this.stream_count = 8;
				if (this.hurt_timer > 0) this.hurt_timer--;
				if (this.jump_timer > 0) this.jump_timer--;
				OreSpawnMain.godzilla_has_spawned = 1;
				
				if (this.worldObj.rand.nextInt(200) == 0) {
					this.setAttackTarget(null);
				}

				if (OreSpawnMain.PlayNicely == 0)
				{
					if (this.motionY < -0.95) this.jumped = 1;
					if (this.motionY < -1.5D) this.jumped = 2;
					if (this.jumped != 0 && this.motionY > -0.1)
					{
						double df = 1.0D;
						if (this.jumped == 2) df = 1.5D;
						this.doJumpDamage(this.posX, this.posY, this.posZ, 10.0D, OreSpawnMain.Godzilla_stats.attack * df, 0);
						this.doJumpDamage(this.posX, this.posY, this.posZ, 15.0D, (OreSpawnMain.Godzilla_stats.attack / 2) * df, 0);
						this.doJumpDamage(this.posX, this.posY, this.posZ, 25.0D, (OreSpawnMain.Godzilla_stats.attack / 4) * df, 0);
						this.jumped = 0;
					}
				}

				xzrange = 12;
				if (this.getAttacking() != 0) xzrange = 16;

				
				int k = -3 + this.ticker % 30;
				if (OreSpawnMain.PlayNicely == 0) {
					for (int i = -xzrange; i <= xzrange; i++) {
						for (int j = -xzrange; j <= xzrange; j++) {
							
							Block bid = this.worldObj.getBlock((int)this.posX + i, (int)this.posY + k, (int)this.posZ + j);
							if (this.isCrushable(bid)) {
								this.worldObj.setBlock((int)this.posX + i, (int)this.posY + k, (int)this.posZ + j, Blocks.air);
								if (this.worldObj.rand.nextInt(15) == 1) this.dropItemRand(Item.getItemFromBlock(bid), 1);
							} else {
								if (bid == Blocks.grass && 
									this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock((int)this.posX + i, (int)this.posY + k, (int)this.posZ + j, Blocks.dirt);

								if (bid == Blocks.farmland && 
									this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock((int)this.posX + i, (int)this.posY + k, (int)this.posZ + j, Blocks.dirt);

							}
						}
					}
				}

				
				double dx = this.posX + 16.0D * Math.sin(Math.toRadians((double)this.rotationYawHead));
				double dz = this.posZ - 16.0D * Math.cos(Math.toRadians((double)this.rotationYawHead));
				k = -3 + this.ticker % 12;
				if (OreSpawnMain.PlayNicely == 0) {
					for (int i = -xzrange; i <= xzrange; i++) {
						for (int j = -xzrange; j <= xzrange; j++) {
							Block bid = this.worldObj.getBlock((int)dx + i, (int)this.posY + k, (int)dz + j);
							if (this.isCrushable(bid)) {
								this.worldObj.setBlock((int)dx + i, (int)this.posY + k, (int)dz + j, Blocks.air);
								if (this.worldObj.rand.nextInt(15) == 1) this.dropItemRandAt(Item.getItemFromBlock(bid), 1, dx, dz);
							} else {
								if (bid == Blocks.grass && 
									this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock((int)dx + i, (int)this.posY + k, (int)dz + j, Blocks.dirt);

								if (bid == Blocks.farmland && 
									this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock((int)dx + i, (int)this.posY + k, (int)dz + j, Blocks.dirt);

							}
						}
					}
				}
				if (OreSpawnMain.PlayNicely == 0 && 
					k == 0) this.doJumpDamage(dx, this.posY, dz, 15.0D, (double)(OreSpawnMain.Godzilla_stats.attack / 2), 1);

				
				
				if (this.worldObj.rand.nextInt(5 - this.large_unknown_detected) == 1) {
					e = this.getAttackTarget();
					if (OreSpawnMain.PlayNicely != 0) e = null;
					if (e != null) {
						if (!e.isEntityAlive()) {
							this.setAttackTarget(null);
							e = null;
						}
						else if (e instanceof Godzilla || e instanceof GodzillaHead) {
							this.setAttackTarget(null);
							e = null;
						}
					}

					if (e == null) {
						e = this.findSomethingToAttack();
						
						if (this.head_found == 0)
						{
							
							EntityLiving newent = (EntityLiving)spawnCreature(this.worldObj, "MobzillaHead", this.posX, this.posY + 20.0D, this.posZ);
						}
					}
					if (e != null) {
						this.wander.setBusy(1);
						this.faceEntity(e, 10.0F, 10.0F);
						if (this.worldObj.rand.nextInt(65) == 1 && this.MygetDistanceSqToEntity(e) > (double)300.0F) {
							this.doLightningAttack(e);
						} else if (this.worldObj.rand.nextInt(20 - this.large_unknown_detected * 5) == 1 && this.jump_timer == 0) {
							this.jumpAtEntity(e);
							this.jump_timer = 30;
						}
						else if (this.MygetDistanceSqToEntity(e) < (double)(300.0F + e.width / 2.0F * (e.width / 2.0F))) {
							this.setAttacking(1);
							this.getNavigator().tryMoveToEntityLiving(e, 1.0D);
							
							if (this.worldObj.rand.nextInt(4 - this.large_unknown_detected) == 0 || this.worldObj.rand.nextInt(3 - this.large_unknown_detected) == 1)
							{
								this.attackEntityAsMob(e);
							}
						} else {
							this.getNavigator().tryMoveToEntityLiving(e, 1.0D);
							if (this.getHorizontalDistanceSqToEntity(e) > 625.0D)
							{
								if (this.stream_count > 0) {
									this.setAttacking(1);
									
									
									double rr = Math.atan2(e.posZ - this.posZ, e.posX - this.posX);
									double rhdir = Math.toRadians((double)((this.rotationYawHead + 90.0F) % 360.0F));
									
									double pi = 3.1415926545;
									
									double rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
									if (rdd > pi) rdd -= pi * 2.0D;
									rdd = Math.abs(rdd);
									
									if (rdd < 0.5D) {
										this.firecanon(e);
									}
								} else {
									this.setAttacking(0);
								}
							} else {
								this.setAttacking(0);
							}
							
						}
					} else
					{
						this.setAttacking(0);
						this.wander.setBusy(0);
						this.stream_count = 8;
					}
				}

				if (this.worldObj.rand.nextInt(35) == 1 && 
					this.getHealth() < (float)this.mygetMaxHealth())
				{
					this.heal(5.0F);
				}
		//	}
	//	}
	}

	
	
	
	
	
	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
		Entity var8 = null;
		var8 = EntityList.createEntityByName(par1, par0World);
		if (var8 != null) {
			
			
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			
			
			par0World.spawnEntityInWorld(var8);
		}

		return var8;
	}

	
	
	
	
	
	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
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
		if (par1EntityLiving instanceof Godzilla)
		{
			return false;
		}
		if (par1EntityLiving instanceof GodzillaHead)
		{
			return false;
		}
		
		
		if (par1EntityLiving instanceof EntityCreeper)
		{
			return false;
		}
		if (par1EntityLiving instanceof EntityZombie)
		{
			return false;
		}
		if (par1EntityLiving instanceof EntitySpider)
		{
			return false;
		}
		if (par1EntityLiving instanceof EntitySkeleton)
		{
			return false;
		}
		if (par1EntityLiving instanceof Ghost)
		{
			return false;
		}
		if (par1EntityLiving instanceof GhostSkelly)
		{
			return false;
		}
		
		if (par1EntityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (p.capabilities.isCreativeMode) {
				return false;
			}
		}

		return true;
	}

	
	
	
	
	
	private boolean isVillagerTarget(EntityLivingBase par1EntityLiving, boolean par2) {
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

		if (par1EntityLiving instanceof EntityVillager)
		{
			return true;
		}
		
		return false;
	}

	
	private EntityLivingBase doJumpDamage(double X, double Y, double Z, double dist, double damage, int knock) {
		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(X - dist, Y - 10.0D, Z - dist, X + dist, Y + 10.0D, Z + dist);
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bb);
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityLivingBase var4 = null;

		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (EntityLivingBase)var3;
			
			

			if (var4 == null) {
				continue;
			}
			
			if (var4 == this) {
				continue;
			}
			
			if (!var4.isEntityAlive()) {
				continue;
			}

			if (var4 instanceof Godzilla) {
				continue;
			}
			if (var4 instanceof GodzillaHead) {
				continue;
			}
			if (var4 instanceof Ghost ||
				var4 instanceof GhostSkelly)
				continue;
			DamageSource var21 = null;
			var21 = DamageSource.setExplosionSource(null);
			var21.setExplosion();
			var4.attackEntityFrom(var21, (float)damage / 2.0F);
			var4.attackEntityFrom(DamageSource.fall, (float)damage / 2.0F);
			this.worldObj.playSoundAtEntity(var4, "random.explode", 0.85F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5F);
			if (knock != 0) {
				double ks = (double)3.5F;
				double inair = 0.75D;
				float f3 = (float)Math.atan2(var4.posZ - this.posZ, var4.posX - this.posX);
				var4.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
			}
		}

		return null;
	}

	
	
	
	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			this.head_found = 1;
			return null;
		} else {
			List var5 = null;
			Iterator var2 = null;
			Entity var3 = null;
			EntityLivingBase var4 = null;
			EntityLivingBase ret = null;
			int vf = 0;
			
			var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand((double)64.0F, (double)40.0F, (double)64.0F));
			if (var5 == null) return null;
			Collections.sort(var5, this.TargetSorter);
			var2 = var5.iterator();
			
			this.head_found = 0;
			while (var2.hasNext())
			{
				var3 = (Entity)var2.next();
				var4 = (EntityLivingBase)var3;
				
				if (var4 instanceof GodzillaHead) {
					this.head_found = 1;
				}
				if (vf == 0 && this.isVillagerTarget(var4, false))
				{
					ret = var4;
					vf = 1;
				}

				if (ret == null && vf == 0 && this.isSuitableTarget(var4, false))
				{
					ret = var4;
				}
			}

			return ret;
		}
	}

	
	
	
	public boolean getCanSpawnHere() {
		if (!this.isValidLightLevel()) return false;
		if (this.worldObj.isDaytime()) return false;
		if (this.posY < 50.0D) return false;
		
		if (OreSpawnMain.godzilla_has_spawned != 0) return false;
		if (this.worldObj.rand.nextInt(40) != 1) return false;
		
		
		for (int k = -8; k <= 8; k++)
		{
			for (int j = -8; j <= 8; j++)
			{
				for (int i = 5; i < 15; i++)
				{
					Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid != Blocks.air) return false;

				}
			}
		}

		Godzilla target = null;
		target = (Godzilla)this.worldObj.findNearestEntityWithinAABB(Godzilla.class, this.boundingBox.expand((double)64.0F, 16.0D, (double)64.0F), this);
		if (target != null)
		{
			return false;
		}
		if (!this.worldObj.isRemote) OreSpawnMain.godzilla_has_spawned = 1;
		return true;
	}

	
	public final int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	
	public final void setAttacking(int par1) {
		this.dataWatcher.updateObject(20, Byte.valueOf((byte)par1));
	}

	
	private ItemStack dropItemRand(Item index, int par1) {
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(this.worldObj, 
				this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(10) - (double)OreSpawnMain.OreSpawnRand.nextInt(10),
				this.posY + 4.0D + (double)this.worldObj.rand.nextInt(10),
				this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(10) - (double)OreSpawnMain.OreSpawnRand.nextInt(10),
				is);
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	
	private ItemStack dropItemRandAt(Item index, int par1, double dx, double dz) {
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(this.worldObj,
				dx + (double)OreSpawnMain.OreSpawnRand.nextInt(10) - (double)OreSpawnMain.OreSpawnRand.nextInt(10),
				this.posY + 4.0D + (double)this.worldObj.rand.nextInt(6),
				dz + (double)OreSpawnMain.OreSpawnRand.nextInt(10) - (double)OreSpawnMain.OreSpawnRand.nextInt(10),
				is);
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isCrushable(Block bid) {
		if (bid == null) return false;
		if (!this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) return false;
		if (bid == Blocks.grass) return false;
		if (bid == Blocks.dirt) return false;
		if (bid == Blocks.stone) return false;
		if (bid == Blocks.farmland) return false;
		if (bid == Blocks.water) return false;
		if (bid == Blocks.flowing_water) return false;
		if (bid == Blocks.lava) return false;
		if (bid == Blocks.flowing_lava) return false;
		if (bid == Blocks.bedrock) return false;
		if (bid == Blocks.obsidian) return false;
		if (bid == Blocks.sand) return false;
		if (bid == Blocks.gravel) return false;
		if (bid == Blocks.iron_block) return false;
		if (bid == Blocks.diamond_block) return false;
		if (bid == Blocks.emerald_block) return false;
		if (bid == Blocks.gold_block) return false;
		if (bid == Blocks.netherrack) return false;
		if (bid == Blocks.end_stone) return false;
		
		if (bid == OreSpawnMain.MyBlockAmethystBlock) return false;
		if (bid == OreSpawnMain.MyBlockRubyBlock) return false;
		if (bid == OreSpawnMain.MyBlockUraniumBlock) return false;
		if (bid == OreSpawnMain.MyBlockTitaniumBlock) return false;
		if (bid == OreSpawnMain.CrystalStone) return false;
		if (bid == OreSpawnMain.CrystalGrass) return false;
		
		return true;
	}

	
	private void firecanon(EntityLivingBase e) {
		double yoff = 19.0D;
		double xzoff = 22.0D;
		
		
		BetterFireball bf = null;
		
		double cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		double cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		if (this.stream_count > 0) {
			
			
			
			bf = new BetterFireball(this.worldObj, this, e.posX - cx, e.posY + (double)(e.height / 2.0F) - (this.posY + yoff), e.posZ - cz);
			bf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
			bf.setPosition(cx, this.posY + yoff, cz);
			bf.setBig();
			this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			this.worldObj.spawnEntityInWorld(bf);
			for (int i = 0; i < 5; i++) {
				float r1 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				float r2 = 3.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				float r3 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				bf = new BetterFireball(this.worldObj, this, e.posX - cx + (double)r1, e.posY + (double)(e.height / 2.0F) - (this.posY + yoff) + (double)r2, e.posZ - cz + (double)r3);
				bf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
				bf.setPosition(cx, this.posY + yoff, cz);
				if (this.worldObj.rand.nextInt(2) == 1) bf.setSmall();
				this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
				this.worldObj.spawnEntityInWorld(bf);
			}
			this.stream_count--;
		}
	}


	public boolean attackEntityAsMob(Entity par1Entity) {
		if (par1Entity != null && par1Entity instanceof EntityLivingBase) {
			float s = par1Entity.height * par1Entity.width;
			if (s > 30.0F && !MyUtils.isRoyalty(par1Entity) && !(par1Entity instanceof Godzilla) && !(par1Entity instanceof GodzillaHead) && !(par1Entity instanceof PitchBlack) && !(par1Entity instanceof Kraken)) {
				EntityLivingBase e = (EntityLivingBase)par1Entity;
				e.setHealth(e.getHealth() / 2.0F);
				e.attackEntityFrom(DamageSource.causeMobDamage(this), (float)OreSpawnMain.Godzilla_stats.attack * 10.0F);
				this.large_unknown_detected = 1;
			}
		}

		if (par1Entity != null && par1Entity instanceof EntityDragon) {
			EntityDragon dr = (EntityDragon)par1Entity;
			DamageSource var21 = null;
			var21 = DamageSource.setExplosionSource(null);
			var21.setExplosion();
			if (this.worldObj.rand.nextInt(6) == 1) {
				dr.attackEntityFromPart(dr.dragonPartHead, var21, (float)OreSpawnMain.Godzilla_stats.attack / 2.0F);
			} else {
				dr.attackEntityFromPart(dr.dragonPartBody, var21, (float)OreSpawnMain.Godzilla_stats.attack / 2.0F);
			}
		}

		if (!super.attackEntityAsMob(par1Entity)) {
			return false;
		} else {
			if (par1Entity != null && par1Entity instanceof EntityLivingBase) {
				double ks = 3.2;
				double inair = 0.3;
				float f3 = (float)Math.atan2(par1Entity.posZ - this.posZ, par1Entity.posX - this.posX);
				if (par1Entity.isDead || par1Entity instanceof EntityPlayer) {
					inair *= 2.0D;
				}

				par1Entity.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
			}

			return true;
		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		float dm = par2;
		float s = 0.0F;
		if (this.hurt_timer > 0) {
			return false;
		} else {
			if (par2 > 750.0F) {
				dm = 750.0F;
			}

			Entity e = par1DamageSource.getEntity();
			if (e != null && e instanceof EntityLivingBase) {
				EntityLivingBase enl = (EntityLivingBase)e;
				s = enl.height * enl.width;
				if (s > 30.0F && !MyUtils.isRoyalty(enl) && !(enl instanceof Godzilla) && !(enl instanceof GodzillaHead) && !(enl instanceof PitchBlack) && !(enl instanceof Kraken)) {
					dm /= 10.0F;
					this.hurt_timer = 50;
					this.large_unknown_detected = 1;
				}
			}

			if (!par1DamageSource.getDamageType().equals("cactus")) {
				ret = super.attackEntityFrom(par1DamageSource, dm);
				this.hurt_timer = 20;
				e = par1DamageSource.getEntity();
				if (e != null && e instanceof EntityLivingBase && !(e instanceof GodzillaHead) && !(e instanceof Godzilla)) {
					this.setAttackTarget((EntityLivingBase)e);
					this.setTarget(e);
					this.getNavigator().tryMoveToEntityLiving((EntityLivingBase)e, 1.2);
				}
			}

			return ret;
		}
	}

	public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {
	}

	private void doLightningAttack(EntityLivingBase e) {
		if (e != null) {
			float var2 = 100.0F;
			e.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
			e.setFire(5);

			for (int var3 = 0; var3 < 20; ++var3) {
				this.worldObj.spawnParticle("smoke", e.posX + (double)this.rand.nextFloat() - (double)this.rand.nextFloat(), e.posY + (double)this.rand.nextFloat() - (double)this.rand.nextFloat(), e.posZ + (double)this.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
				this.worldObj.spawnParticle("largesmoke", e.posX + (double)this.rand.nextFloat() - (double)this.rand.nextFloat(), e.posY + (double)this.rand.nextFloat() - (double)this.rand.nextFloat(), e.posZ + (double)this.rand.nextFloat() - (double)this.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
				this.worldObj.spawnParticle("fireworksSpark", e.posX, e.posY, e.posZ, this.worldObj.rand.nextGaussian(), this.worldObj.rand.nextGaussian(), this.worldObj.rand.nextGaussian());
			}

			this.worldObj.playSoundAtEntity(e, "random.explode", 0.5F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5F);
			if (!this.worldObj.isRemote) {
				this.worldObj.createExplosion(this, e.posX, e.posY, e.posZ, 3.0F, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
			}

			this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, e.posX, e.posY + 1.0D, e.posZ));
			this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY + 15.0D, this.posZ));
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void dropFewItems(boolean par1, int par2) {
		ItemStack is = null;
		
		
		this.dropItemRand(Items.item_frame, 1);
		
		int var5 = 50 + this.worldObj.rand.nextInt(30);
		for (int var4 = 0; var4 < var5; ++var4) {
			this.dropItemRand(OreSpawnMain.MyGodzillaScale, 1);
		}
		var5 = 100 + this.worldObj.rand.nextInt(160);
		for (int var4 = 0; var4 < var5; ++var4) {
			this.dropItemRand(Items.beef, 1);
		}
		var5 = 50 + this.worldObj.rand.nextInt(60);
		for (int var4 = 0; var4 < var5; ++var4) {
			this.dropItemRand(Items.bone, 1);
		}

		
		int i = 25 + this.worldObj.rand.nextInt(15);
		for (int var4 = 0; var4 < i; ++var4) {
			int var3 = this.worldObj.rand.nextInt(80);
			switch (var3) {
				case 0:
					is = this.dropItemRand(OreSpawnMain.MyUltimateSword, 1);
					break;
				case 1:
					is = this.dropItemRand(Items.diamond, 1);
					break;
				case 2:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.diamond_block), 1);
					break;
				case 3:
					is = this.dropItemRand(Items.diamond_sword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 4:
					is = this.dropItemRand(Items.diamond_shovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 5:
					is = this.dropItemRand(Items.diamond_pickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 6:
					is = this.dropItemRand(Items.diamond_axe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 7:
					is = this.dropItemRand(Items.diamond_hoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 8:
					is = this.dropItemRand(Items.diamond_helmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(2));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 9:
					is = this.dropItemRand(Items.diamond_chestplate, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 10:
					is = this.dropItemRand(Items.diamond_leggings, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 11:
					is = this.dropItemRand(Items.diamond_boots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 12:
					is = this.dropItemRand(OreSpawnMain.MyUltimateBow, 1);
					break;
				case 13:
					is = this.dropItemRand(OreSpawnMain.MyUltimateAxe, 1);
					break;
				case 14:
					is = this.dropItemRand(Items.iron_ingot, 1);
					break;
				case 15:
					is = this.dropItemRand(OreSpawnMain.MyUltimatePickaxe, 1);
					break;
				case 16:
					is = this.dropItemRand(Items.iron_sword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 17:
					is = this.dropItemRand(Items.iron_shovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 18:
					is = this.dropItemRand(Items.iron_pickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 19:
					is = this.dropItemRand(Items.iron_axe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 20:
					is = this.dropItemRand(Items.iron_hoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 21:
					is = this.dropItemRand(Items.iron_helmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 22:
					is = this.dropItemRand(Items.iron_chestplate, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 23:
					is = this.dropItemRand(Items.iron_leggings, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 24:
					is = this.dropItemRand(Items.iron_boots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 25:
					is = this.dropItemRand(OreSpawnMain.MyUltimateShovel, 1);
					break;
				case 26:
					this.dropItemRand(Item.getItemFromBlock(Blocks.iron_block), 1);
					break;
				case 27:
					is = this.dropItemRand(Items.gold_nugget, 1);
					break;
				case 28:
					is = this.dropItemRand(Items.gold_ingot, 1);
					break;
				case 29:
					is = this.dropItemRand(Items.golden_carrot, 1);
					break;
				case 30:
					is = this.dropItemRand(Items.golden_sword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 31:
					is = this.dropItemRand(Items.golden_shovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 32:
					is = this.dropItemRand(Items.golden_pickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 33:
					is = this.dropItemRand(Items.golden_axe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 34:
					is = this.dropItemRand(Items.golden_hoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 35:
					is = this.dropItemRand(Items.golden_helmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 36:
					is = this.dropItemRand(Items.golden_chestplate, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 37:
					is = this.dropItemRand(Items.golden_leggings, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 38:
					is = this.dropItemRand(Items.golden_boots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 39:
					this.dropItemRand(Items.golden_apple, 1);
					break;
				case 40:
					this.dropItemRand(Item.getItemFromBlock(Blocks.gold_block), 1);
					break;
					
				case 41:
					EntityItem var33 = null;
					is = new ItemStack(Items.golden_apple, 1, 1);
					var33 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(3) - (double)OreSpawnMain.OreSpawnRand.nextInt(3), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(3) - (double)OreSpawnMain.OreSpawnRand.nextInt(3), is);
					
					if (var33 != null) this.worldObj.spawnEntityInWorld(var33);
					
					break;
				case 42:
					is = this.dropItemRand(OreSpawnMain.MyExperienceSword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 43:
					is = this.dropItemRand(OreSpawnMain.ExperienceHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 44:
					is = this.dropItemRand(OreSpawnMain.ExperienceBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 45:
					is = this.dropItemRand(OreSpawnMain.ExperienceLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 46:
					is = this.dropItemRand(OreSpawnMain.ExperienceBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 47:
					is = this.dropItemRand(OreSpawnMain.MyAmethystSword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 48:
					is = this.dropItemRand(OreSpawnMain.MyAmethystShovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 49:
					is = this.dropItemRand(OreSpawnMain.MyAmethystPickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 50:
					is = this.dropItemRand(OreSpawnMain.MyAmethystAxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 51:
					is = this.dropItemRand(OreSpawnMain.MyAmethystHoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 52:
					is = this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.MyBlockAmethystBlock), 1);
					break;
				case 53:
					is = this.dropItemRand(OreSpawnMain.AmethystHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 54:
					is = this.dropItemRand(OreSpawnMain.AmethystBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 55:
					is = this.dropItemRand(OreSpawnMain.AmethystLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 56:
					is = this.dropItemRand(OreSpawnMain.AmethystBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 57:
					is = this.dropItemRand(OreSpawnMain.RubyHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 58:
					is = this.dropItemRand(OreSpawnMain.RubyBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 59:
					is = this.dropItemRand(OreSpawnMain.RubyLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 60:
					is = this.dropItemRand(OreSpawnMain.RubyBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 61:
					is = this.dropItemRand(OreSpawnMain.MyRubySword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 62:
					is = this.dropItemRand(OreSpawnMain.MyRubyShovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 63:
					is = this.dropItemRand(OreSpawnMain.MyRubyPickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 64:
					is = this.dropItemRand(OreSpawnMain.MyRubyAxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 65:
					is = this.dropItemRand(OreSpawnMain.MyRubyHoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 66:
					is = this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.MyBlockRubyBlock), 1);
					break;
				case 67:
					is = this.dropItemRand(OreSpawnMain.UltimateHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 68:
					is = this.dropItemRand(OreSpawnMain.UltimateBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 69:
					is = this.dropItemRand(OreSpawnMain.UltimateLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 70:
					is = this.dropItemRand(OreSpawnMain.UltimateBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 71:
					is = this.dropItemRand(OreSpawnMain.MyUltimateShovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 73:
					is = this.dropItemRand(OreSpawnMain.MyUltimatePickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 74:
					is = this.dropItemRand(OreSpawnMain.MyUltimateAxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 75:
					is = this.dropItemRand(OreSpawnMain.MyUltimateHoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
			}
		}
	}
}
