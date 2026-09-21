package danger.orespawn;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;





























public class TheKing extends EntityMob
{
	private ChunkCoordinates currentFlightTarget = null;
	private GenericTargetSorter TargetSorter = null;
	private EntityLivingBase rt = null;
	private double attdam = (double)250.0F;
	private int hurt_timer = 0;
	private int homex = 0;
	private int homez = 0;
	private int stream_count = 0;
	private int stream_count_l = 0;
	private int stream_count_i = 0;
	private int ticker = 0;
	private int player_hit_count = 0;
	private int backoff_timer = 0;
	private int guard_mode = 0;
	private volatile int head_found = 0;
	private int wing_sound = 0;
	private int large_unknown_detected = 0;
	private int isEnd = 0;
	private int endCounter = 0;

	public TheKing(World par1World) {
		super(par1World);
		if (OreSpawnMain.PlayNicely == 0) {
			this.setSize(22.0F, 24.0F);
		} else {
			this.setSize(5.5F, 6.0F);
		}
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 25000;
		this.isImmuneToFire = true;
		this.fireResistance = 5000;
		this.noClip = true;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderDistanceWeight = 12.0D;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.62F);
		
		this.attdam = (double)OreSpawnMain.TheKing_stats.attack;
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.attdam);
	}

	
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(21, Integer.valueOf(OreSpawnMain.PlayNicely));
		this.dataWatcher.addObject(22, Integer.valueOf(this.isEnd));
	}

	public int getPlayNicely() {
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	/**
	 * Checks if the entity is in range to render by using the past in distance and comparing it to its average edge
	 * length * 64 * renderDistanceWeight Args: distance
	 */
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}

	
	
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderVec3D(Vec3 par1Vec3)
	{
		return true;
	}

	protected boolean canDespawn() {
		return false;
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
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 1.35F;
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
		return "orespawn:king_living";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return "orespawn:king_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound() {
		return "orespawn:trex_death";
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed() {
		return false;
	}

	protected void collideWithEntity(Entity par1Entity) {}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.TheKing_stats.health;
	}

	
	
	
	protected Item getDropItem()
	{
		return Item.getItemFromBlock(Blocks.yellow_flower);
	}

	private void dropItemRand(Item index, int par1)
	{
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(20) - (double)OreSpawnMain.OreSpawnRand.nextInt(20), this.posY + 12.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(20) - (double)OreSpawnMain.OreSpawnRand.nextInt(20), new ItemStack(index, par1, 0));
		
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		int i, j, k, unused1;
		Item it = null;
		Block bl = null;
		
		
		this.spawnCreature(this.worldObj, "The Prince", this.posX, this.posY + 10.0D, this.posZ);
		
		this.dropItemRand(OreSpawnMain.RoyalBody, 1);
		this.dropItemRand(OreSpawnMain.RoyalHelmet, 1);
		this.dropItemRand(OreSpawnMain.RoyalLegs, 1);
		this.dropItemRand(OreSpawnMain.RoyalBoots, 1);
		this.dropItemRand(OreSpawnMain.MyRoyal, 1);
		
		Iterator ilist = Item.itemRegistry.iterator();
		int icount = 0;
		while (ilist.hasNext()) {
			it = (Item)ilist.next();
			if (it != null) ++icount;
		}
		

		for (j = 0; j < 150; ) {
			k = 1 + this.worldObj.rand.nextInt(icount);
			ilist = Item.itemRegistry.iterator();
			while (k > 0 && ilist.hasNext()) {
				it = (Item)ilist.next();
				k--;
			}
			if (it != null) {
				++j;
				this.dropItemRand(it, 1);
			}
		}

		Iterator blist = Block.blockRegistry.iterator();
		int bcount = 0;
		while (blist.hasNext()) {
			bl = (Block)blist.next();
			if (bl != null) ++bcount;
		}
		

		for (j = 0; j < 150; ) {
			k = 1 + this.worldObj.rand.nextInt(bcount);
			blist = Block.blockRegistry.iterator();
			while (k > 0 && blist.hasNext()) {
				bl = (Block)blist.next();
				k--;
			}
			if (bl != null) {
				++j;
				this.dropItemRand(Item.getItemFromBlock(bl), 1);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
		
		this.wing_sound++;
		if (this.wing_sound > 30)
		{
			if (!this.worldObj.isRemote) this.worldObj.playSoundAtEntity(this, "orespawn:MothraWings", 1.75F, 0.75F);
			this.wing_sound = 0;
		}

		this.noClip = true;
		this.motionY *= 0.6;
		if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() * 2 / 3)) this.attdam = (double)(OreSpawnMain.TheKing_stats.attack * 2);
		if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 2)) this.attdam = (double)(OreSpawnMain.TheKing_stats.attack * 4);
		if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 4)) this.attdam = (double)(OreSpawnMain.TheKing_stats.attack * 8);
		if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 8)) this.attdam = (double)(OreSpawnMain.TheKing_stats.attack * 16);

		if (this.worldObj.isRemote) {
			float f = 7.0F;
			this.isEnd = this.dataWatcher.getWatchableObjectInt(22);
			
			if (this.isEnd != 0 && this.worldObj.rand.nextInt(3) == 1)
			{
				for (int i = 0; i < 10; i++) {
					this.worldObj.spawnParticle("fireworksSpark", this.posX - (double)f * Math.sin(Math.toRadians((double)this.rotationYaw)), this.posY + 14.0D, this.posZ + (double)f * Math.cos(Math.toRadians((double)this.rotationYaw)), (this.worldObj.rand.nextGaussian() - this.worldObj.rand.nextGaussian()) / 4.0D + this.motionX * 6.0D, (this.worldObj.rand.nextGaussian() - this.worldObj.rand.nextGaussian()) / 4.0D, (this.worldObj.rand.nextGaussian() - this.worldObj.rand.nextGaussian()) / 4.0D + this.motionZ * 6.0D);
				}
			}
		}
		
		
		
		
		
		
		
	}

	
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		if (par1Entity != null && par1Entity instanceof EntityLivingBase)
		{
			float s = par1Entity.height * par1Entity.width;
			if (s > 30.0F) {
				if (!MyUtils.isRoyalty(par1Entity) && 
				!(par1Entity instanceof Godzilla) && 
				!(par1Entity instanceof GodzillaHead) && 
				!(par1Entity instanceof PitchBlack) && 
				!(par1Entity instanceof Kraken)) {
					EntityLivingBase e = (EntityLivingBase)par1Entity;
					e.setHealth(e.getHealth() / 2.0F);
					e.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.attdam * 10.0F);
					this.large_unknown_detected = 1;
				}
			}
		}

		if (par1Entity != null && par1Entity instanceof EntityDragon) {
			EntityDragon dr = (EntityDragon)par1Entity;
			DamageSource var21 = null;
			var21 = DamageSource.setExplosionSource(null);
			var21.setExplosion();
			if (this.worldObj.rand.nextInt(6) == 1) {
				dr.attackEntityFromPart(dr.dragonPartHead, var21, (float)this.attdam);
			} else {
				dr.attackEntityFromPart(dr.dragonPartBody, var21, (float)this.attdam);
			}
		}

		boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.attdam);
		if (var4) {
			double ks = 3.3;
			double inair = 0.25D;
			float f3 = (float)Math.atan2(par1Entity.posZ - this.posZ, par1Entity.posX - this.posX);
			inair += (double)(this.worldObj.rand.nextFloat() * 0.25F);
			if (par1Entity.isDead || par1Entity instanceof EntityPlayer) inair *= 1.5D;
			par1Entity.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
		}

		return var4;
	}

	
	
	
	public boolean canSeeTarget(double pX, double pY, double pZ)
	{
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 8.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	
	private boolean tooFarFromHome()
	{
		float d1 = (float)(this.posX - (double)this.homex);
		float d2 = (float)(this.posZ - (double)this.homez);
		
		d1 = (float)Math.sqrt((double)(d1 * d1 + d2 * d2));
		if (d1 > 120.0F) return true;
		return false;
	}

	private void msgToPlayers(String s)
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand((double)80.0F, (double)64.0F, (double)80.0F));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityPlayer var4 = null;

		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (EntityPlayer)var3;
			var4.addChatComponentMessage(new ChatComponentText(s));
		}
	}

	private EntityPlayer findNearestPlayer()
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand((double)80.0F, (double)64.0F, (double)80.0F));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityPlayer var4 = null;

		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			if (var3 instanceof EntityPlayer) var4 = (EntityPlayer)var3;
			if (var4 != null) break;
		}
		
		return var4;
	}

	protected void updateAITasks()
	{
		int xdir = 1;
		int zdir = 1;
		int unused1, unused2, i, j, k, dist;
		Block bid;
		
		int attrand = 5;
		int updown = 0;
		int which = 0;
		EntityLivingBase e = null;
		EntityLivingBase f = null;
		float d1, d2;
		double rr = 0.0D;
		double rhdir = 0.0D;
		double rdd = 0.0D;
		double pi = 3.1415926545;
		double var1 = 0.0D;
		double var3 = 0.0D;
		double var5 = 0.0D;
		float var7 = 0.0F;
		float var8 = 0.0F;
		EntityLiving newent;
		EntityPlayer p = null;
		
		if (this.isDead) return;
		super.updateAITasks();
		this.dataWatcher.updateObject(22, this.isEnd);
		this.dataWatcher.updateObject(21, OreSpawnMain.PlayNicely);
		
		if (this.isEnd == 1) {
			++this.endCounter;
			this.noClip = true;
			this.motionX = 0.0D;
			this.motionY = 0.0D;
			this.motionZ = 0.0D;
			this.hurt_timer = 10;
			
			if (this.isDead) return;
			
			p = this.findNearestPlayer();
			if (p != null) {
				this.faceEntity(p, 10.0F, 10.0F);
				p.motionX = 0.0D;
				p.motionY = 0.0D;
				p.motionZ = 0.0D;
				double dd0 = this.posX - p.posX;
				double dd1 = this.posZ - p.posZ;
				float f2 = (float)(Math.atan2(dd1, dd0) * 180.0D / Math.PI) - 90.0F;
				p.rotationYaw = f2;
				p.setHealth(1.0F);
			}

			if (this.endCounter == 10) {
				this.msgToPlayers("The King: Enough of this charade. I am done. You have shown me what I wanted to know.");
				return;
			}
			if (this.endCounter == 80) {
				this.msgToPlayers("The King: That's right my little pet. It has all been a game. You never killed me. You can't.");
				return;
			}
			if (this.endCounter == 160) {
				this.msgToPlayers("The King: I am the one. The only. The many. I exist within both space and time. Everywhere and always.");
				return;
			}
			if (this.endCounter == 240) {
				this.msgToPlayers("The King: I used you to learn your ways, and I have reached my conclusion on your species.");
				return;
			}
			if (this.endCounter == 300) {
				this.msgToPlayers("The King: You have 10 seconds to run...");
				return;
			}
			if (this.endCounter == 320) {
				this.msgToPlayers("9.");
				return;
			}
			if (this.endCounter == 340) {
				this.msgToPlayers("8.");
				return;
			}
			if (this.endCounter == 360) {
				this.msgToPlayers("7.");
				return;
			}
			if (this.endCounter == 380) {
				this.msgToPlayers("6.");
				return;
			}
			if (this.endCounter == 400) {
				this.msgToPlayers("5.");
				return;
			}
			if (this.endCounter == 420) {
				this.msgToPlayers("4.");
				return;
			}
			if (this.endCounter == 440) {
				this.msgToPlayers("3.");
				return;
			}
			if (this.endCounter == 460) {
				this.msgToPlayers("2.");
				return;
			}
			if (this.endCounter == 480) {
				this.msgToPlayers("1.");
				return;
			}
			if (this.endCounter == 500) {
				this.msgToPlayers("The King: Prepare to die!");
				this.isEnd = 2;
				return;
			}
			
			return;
		}
		
		if (this.isEnd == 2) {
			this.hurt_timer = 10;
			this.player_hit_count = 0;
			this.stream_count = 10;
			this.stream_count_l = 10;
			this.stream_count_i = 10;
			attrand = 3;
			this.guard_mode = 0;
			this.large_unknown_detected = 1;
			if (this.backoff_timer > 0) --this.backoff_timer;
		}
		
		if (this.hurt_timer > 0) --this.hurt_timer;
		if ((this.homex == 0 && this.homez == 0) || this.guard_mode == 0) {
			this.homex = (int)this.posX;
			this.homez = (int)this.posZ;
		}
		
		
		++this.ticker;
		if (this.ticker > 30000) this.ticker = 0;
		if (this.ticker % 80 == 0) this.stream_count = 10;
		if (this.ticker % 90 == 0) this.stream_count_l = 5;
		if (this.ticker % 70 == 0) this.stream_count_i = 8;
		if (this.backoff_timer > 0) --this.backoff_timer;
		
		if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 2)) attrand = 3;
		this.noClip = true;
		
		
		
		if (this.currentFlightTarget == null) {
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}
		
		
		if (this.tooFarFromHome() || this.worldObj.rand.nextInt(200) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 9.1F)
		{
			
			
			zdir = this.worldObj.rand.nextInt(120);
			xdir = this.worldObj.rand.nextInt(120);
			if (this.worldObj.rand.nextInt(2) == 0) zdir = -zdir;
			if (this.worldObj.rand.nextInt(2) == 0) xdir = -xdir;
			
			
			dist = 0;
			for (i = -5; i <= 5; i += 5) {
				for (j = -5; j <= 5; j += 5) {
					bid = this.worldObj.getBlock(this.homex + j, (int)this.posY, this.homez + i);
					if (bid != Blocks.air)
						for (k = 1; k < 20; k++) {
							bid = this.worldObj.getBlock(this.homex + j, (int)this.posY + k, this.homez + i);
							++dist;
							if (bid == Blocks.air) break;
						}
					else
						for (k = 1; k < 20; k++) {
							bid = this.worldObj.getBlock(this.homex + j, (int)this.posY - k, this.homez + i);
							--dist;
							if (bid != Blocks.air) break;
						}
				}
			}
			
			dist = dist / 9 + 2;
			if ((int)(this.posY + (double)dist) > 230) dist = 230 - (int)this.posY;
			this.currentFlightTarget.set(this.homex + xdir, (int)(this.posY + (double)dist), this.homez + zdir);
		
		}
		else if (this.worldObj.rand.nextInt(attrand) == 0)
		{
			
			e = this.rt;
			if (OreSpawnMain.PlayNicely != 0) e = null;
			if (e != null) {
				if (e instanceof TheKing || e instanceof KingHead) {
					this.rt = null;
					e = null;
				}
			}
			if (e != null)
			{
				d1 = (float)(e.posX - (double)this.homex);
				d2 = (float)(e.posZ - (double)this.homez);
				d1 = (float)Math.sqrt((double)(d1 * d1 + d2 * d2));
				if (e.isDead || this.worldObj.rand.nextInt(250) == 1 || d1 > 128.0F && this.guard_mode == 1) {
					e = null;
					this.rt = null;
				}
				if (e != null) {
					if (!this.MyCanSee(e)) {
						e = null;
					}
				}
			}
			
			
			f = this.findSomethingToAttack();
			if (this.head_found == 0)
			{
				newent = (EntityLiving)spawnCreature(this.worldObj, "KingHead", this.posX, this.posY + 20.0D, this.posZ);
			}

			
			if (e == null) {
				e = f;
			}

			if (e != null)
			{
				
				this.setAttacking(1);
				if (this.backoff_timer == 0) {
					dist = (int)(e.posY + (double)(e.height / 2.0F) + 1.0D);
					if (dist > 230) dist = 230;
					this.currentFlightTarget.set((int)e.posX, dist, (int)e.posZ);
					if (this.worldObj.rand.nextInt(70) == 1) this.backoff_timer = 80 + this.worldObj.rand.nextInt(80);
				}
				else if (this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 9.1F)
				{
					
					zdir = this.worldObj.rand.nextInt(20) + 30;
					xdir = this.worldObj.rand.nextInt(20) + 30;
					if (this.worldObj.rand.nextInt(2) == 0) zdir = -zdir;
					if (this.worldObj.rand.nextInt(2) == 0) xdir = -xdir;							
					
					
					dist = 0;
					for (i = -5; i <= 5; i += 5) {
						for (j = -5; j <= 5; j += 5) {
							bid = this.worldObj.getBlock((int)e.posX + j, (int)this.posY, (int)e.posZ + i);
							if (bid != Blocks.air)
								for (k = 1; k < 20; k++) {
									bid = this.worldObj.getBlock((int)e.posX + j, (int)this.posY + k, (int)e.posZ + i);
									++dist;
									if (bid == Blocks.air) break;
								}
							else
								for (k = 1; k < 20; k++) {
									bid = this.worldObj.getBlock((int)e.posX + j, (int)this.posY - k, (int)e.posZ + i);
									--dist;
									if (bid != Blocks.air) break;
								}
						}
					}
					
					dist = dist / 9 + 2;
					if ((int)(this.posY + (double)dist) > 230) dist = 230 - (int)this.posY;
					this.currentFlightTarget.set((int)e.posX + xdir, (int)(this.posY + (double)dist), (int)e.posZ + zdir);
				}

				
				
				
				if (this.getDistanceSqToEntity(e) < (double)900.0F) {
					if (this.worldObj.rand.nextInt(2) == 1) this.doJumpDamage(this.posX, this.posY, this.posZ, 15.0D, (double)(OreSpawnMain.TheKing_stats.attack / 4), 0);
					this.attackEntityAsMob(e);
				}

				double dx = this.posX + 20.0D * Math.sin(Math.toRadians((double)this.rotationYawHead));
				double dz = this.posZ - 20.0D * Math.cos(Math.toRadians((double)this.rotationYawHead));
				if (this.worldObj.rand.nextInt(3) == 1) this.doJumpDamage(dx, this.posY + 10.0D, dz, 15.0D, (double)(OreSpawnMain.TheKing_stats.attack / 2), 1);

				if (this.getHorizontalDistanceSqToEntity(e) > 900.0D) {
					which = this.worldObj.rand.nextInt(3);
					if (which == 0)
					{
						if (this.stream_count > 0) {
							this.setAttacking(1);
							
							
							rr = Math.atan2(e.posZ - this.posZ, e.posX - this.posX);
							rhdir = Math.toRadians((double)((this.rotationYawHead + 90.0F) % 360.0F));
							
							rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
							if (rdd > pi) rdd -= pi * 2.0D;
							rdd = Math.abs(rdd);
							
							if (rdd < 0.5D) {
								this.firecanon(e);
							}
						}
					}
					else if (which == 1) {
						if (this.stream_count_l > 0) {
							this.setAttacking(1);
							
							
							rr = Math.atan2(e.posZ - this.posZ, e.posX - this.posX);
							rhdir = Math.toRadians((double)((this.rotationYawHead + 90.0F) % 360.0F));
							
							rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
							if (rdd > pi) rdd -= pi * 2.0D;
							rdd = Math.abs(rdd);
							
							if (rdd < 0.5D) {
								this.firecanonl(e);
							}
						}
						
					}
					else if (this.stream_count_i > 0) {
						this.setAttacking(1);
						
						
						rr = Math.atan2(e.posZ - this.posZ, e.posX - this.posX);
						rhdir = Math.toRadians((double)((this.rotationYawHead + 90.0F) % 360.0F));
						
						rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
						if (rdd > pi) rdd -= pi * 2.0D;
						rdd = Math.abs(rdd);
						
						if (rdd < 0.5D) {
							this.firecanoni(e);
						}
					}
				}
				
				
			} else {
				this.setAttacking(0);
				this.stream_count = 10;
				this.stream_count_l = 5;
				this.stream_count_i = 8;
			}
		}
		
		if (this.getAttacking() != 0 && this.isEnd == 2) {
			double xzoff = 10.0D;
			double yoff = 14.0D;
			Entity ppwr = this.spawnCreature(this.worldObj, "PurplePower", this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw)), this.posY + yoff, this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw)));
			
			
			
			if (ppwr != null) {
				PurplePower pwr = (PurplePower)ppwr;
				pwr.motionX = this.motionX * 3.0D;
				pwr.motionZ = this.motionZ * 3.0D;
				pwr.setPurpleType(10);
			}
		}
		
		var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
		var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
		var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
		
		
		
		
		
		this.motionX += (Math.signum(var1) * 0.7 - this.motionX) * 0.35;
		this.motionY += (Math.signum(var3) * 0.69999 - this.motionY) * 0.3;
		this.motionZ += (Math.signum(var5) * 0.7 - this.motionZ) * 0.35;
		
		var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = 1.0F;
		this.rotationYaw += var8 / 8.0F;
		
		
		if (this.worldObj.rand.nextInt(30) == 1) {
			if (this.getHealth() < (float)this.mygetMaxHealth())
			{
				this.heal(5.0F);
				if (this.large_unknown_detected != 0) this.heal(200.0F);
			}
		}
		if (this.player_hit_count < 10 && this.getHealth() < 2000.0F) this.heal(2000.0F - this.getHealth());
		
		
		
		
		
	}
	
	
	private double getHorizontalDistanceSqToEntity(Entity e)
	{
		double d1 = e.posZ - this.posZ;
		double d2 = e.posX - this.posX;
		return d1 * d1 + d2 * d2;
	}

	private void firecanon(EntityLivingBase e)
	{
		double yoff = 14.0D;
		double xzoff = 32.0D;
		double cx, cz;
		float r1, r2, r3;
		BetterFireball bf = null;
		int i;
		cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		if (this.stream_count > 0)
		{
			
			
			bf = new BetterFireball(this.worldObj, this, e.posX - cx, e.posY + (double)(e.height / 2.0F) - (this.posY + yoff), e.posZ - cz);
			bf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
			bf.setPosition(cx, this.posY + yoff, cz);
			bf.setReallyBig();
			this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			this.worldObj.spawnEntityInWorld(bf);
			for (i = 0; i < 6; i++) {
				r1 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				r2 = 3.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				r3 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				bf = new BetterFireball(this.worldObj, this, e.posX - cx + (double)r1, e.posY + (double)(e.height / 2.0F) - (this.posY + yoff) + (double)r2, e.posZ - cz + (double)r3);
				bf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
				bf.setPosition(cx, this.posY + yoff, cz);
				bf.setBig();
				if (this.worldObj.rand.nextInt(2) == 1) bf.setSmall();
				this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
				this.worldObj.spawnEntityInWorld(bf);
			}
			--this.stream_count;
		}
		
	}
	
	private void firecanonl(EntityLivingBase e)
	{
		double yoff = 14.0D;
		double xzoff = 32.0D;
		double cx, cz;
		float r1, r2, r3;
		int i;
		double var3 = 0.0D;
		double var5 = 0.0D;
		double var7 = 0.0D;
		float var9 = 0.0F;
		cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		if (this.stream_count_l > 0) {
			this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			for (i = 0; i < 3; i++) {
				r1 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				r2 = 3.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				r3 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				ThunderBolt lb = new ThunderBolt(this.worldObj, cx, this.posY + yoff, cz);
				lb.setLocationAndAngles(cx, this.posY + yoff, cz, 0.0F, 0.0F);
				var3 = e.posX - lb.posX;
				var5 = e.posY + 0.25D - lb.posY;
				var7 = e.posZ - lb.posZ;
				var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
				lb.setThrowableHeading(var3, var5 + (double)var9, var7, 1.4F, 4.0F);
				lb.motionX *= 3.0D;
				lb.motionY *= 3.0D;
				lb.motionZ *= 3.0D;
				this.worldObj.spawnEntityInWorld(lb);
			}
			--this.stream_count_l;
		}
	}

	private void firecanoni(EntityLivingBase e)
	{
		double yoff = 14.0D;
		double xzoff = 32.0D;
		double cx, cz;
		float r1, r2, r3;
		int i;
		double var3 = 0.0D;
		double var5 = 0.0D;
		double var7 = 0.0D;
		float var9 = 0.0F;
		cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		if (this.stream_count_i > 0) {
			this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			for (i = 0; i < 5; i++) {
				r1 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				r2 = 3.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				r3 = 5.0F * (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat());
				IceBall lb = new IceBall(this.worldObj, cx, this.posY + yoff, cz);
				lb.setIceMaker(1);
				lb.setLocationAndAngles(cx, this.posY + yoff, cz, 0.0F, 0.0F);
				var3 = e.posX - lb.posX;
				var5 = e.posY + 0.25D - lb.posY;
				var7 = e.posZ - lb.posZ;
				var9 = MathHelper.sqrt_double(var3 * var3 + var7 * var7) * 0.2F;
				lb.setThrowableHeading(var3, var5 + (double)var9, var7, 1.4F, 4.0F);
				lb.motionX *= 3.0D;
				lb.motionY *= 3.0D;
				lb.motionZ *= 3.0D;
				this.worldObj.spawnEntityInWorld(lb);
			}
			--this.stream_count_i;
		}
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
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		float dm = par2;
		int dist;
		float s;
		if (this.hurt_timer > 0) return false;
		if (dm > 750.0F) dm = 750.0F;

		if (par1DamageSource.getDamageType().equals("inWall")) {
			return false;
		}
		
		Entity e = par1DamageSource.getEntity();
		if (e != null && e instanceof EntityLivingBase)
		{
			EntityLivingBase enl = (EntityLivingBase)e;
			s = enl.height * enl.width;
			if (s > 30.0F) {
				if (!MyUtils.isRoyalty(enl) &&
					!(enl instanceof Godzilla) &&
					!(enl instanceof GodzillaHead) &&
					!(enl instanceof PitchBlack) &&
					!(enl instanceof Kraken)) {
					dm /= 10.0F;
					this.hurt_timer = 50;
					this.large_unknown_detected = 1;
				}
			}
			if (e instanceof EntityMob) {
				if (s < 3.0F) {
					e.setDead();
					return false;
				}
			}
		}
		
		if (!par1DamageSource.getDamageType().equals("cactus")) {
			this.hurt_timer = 20;
			ret = super.attackEntityFrom(par1DamageSource, dm);
			
			if (e != null && e instanceof EntityPlayer)
			{
				
				
				
				++this.player_hit_count;
			}

			if (e != null && e instanceof EntityLivingBase && this.currentFlightTarget != null)
			{
				if (!MyUtils.isRoyalty(e)) {
					this.rt = (EntityLivingBase)e;
					dist = (int)e.posY;
					if (dist > 230) dist = 230;
					this.currentFlightTarget.set((int)e.posX, dist, (int)e.posZ);
				}
			}
		}
		return ret;
	}

	
	
	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		return true;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		if (this.large_unknown_detected != 0) return 25;
		if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() * 2 / 3)) return OreSpawnMain.TheKing_stats.defense + 1;
		if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 2)) return OreSpawnMain.TheKing_stats.defense + 2;
		if (this.player_hit_count < 10 && this.getHealth() < (float)(this.mygetMaxHealth() / 4)) return OreSpawnMain.TheKing_stats.defense + 3;
		return OreSpawnMain.TheKing_stats.defense;
	}

	
	public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {}

	
	
	
	
	public void initCreature() {}
	
	
	

	public boolean MyCanSee(EntityLivingBase e)
	{
		float startx;
		float starty;
		float startz;
		double xzoff = 22.0D;
		double cx, cz;
		float dx;
		float dy;
		float dz;
		int i;
		int nblks = 20;
		Block bid;
		
		
		
		
		cx = posX-(xzoff*Math.sin(Math.toRadians(rotationYaw)));
		cz = posZ+(xzoff*Math.cos(Math.toRadians(rotationYaw)));
		startx = (float)(cx);
		starty = (float)(posY+(height*7/8));
		startz = (float)(cz);
		dx = (float)((e.posX-startx)/20.0D);
		dy = (float)((e.posY+(e.height/2.0F)-starty)/20.0D);
		dz = (float)((e.posZ-startz)/20.0D);
		
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
			if (bid == Blocks.flowing_water || bid == Blocks.water || bid == Blocks.leaves || bid == Blocks.vine) continue;
			if (bid != Blocks.air) return false;
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
		
		if (par1EntityLiving instanceof KingHead)
		{
			this.head_found = 1;
			return false;
		}
		if (MyUtils.isRoyalty(par1EntityLiving))
		{
			return false;
		}
		
		
		
		float d1 = (float)(par1EntityLiving.posX - (double)this.homex);
		float d2 = (float)(par1EntityLiving.posZ - (double)this.homez);
		d1 = (float)Math.sqrt((double)(d1 * d1 + d2 * d2));
		if (d1 > 144.0F) return false;
		
		if (OreSpawnMain.OreSpawnUtils.isIgnoreable(par1EntityLiving)) return false;
		
		if (this.isEnd == 2) {
			if (par1EntityLiving instanceof EntityPlayer)
			{
				EntityPlayer p = (EntityPlayer)par1EntityLiving;
				if (p.capabilities.isCreativeMode == true) {
					return false;
				}
				return true;
			}
			if (par1EntityLiving instanceof Girlfriend)
			{
				return true;
			}
			if (par1EntityLiving instanceof Boyfriend)
			{
				return true;
			}
			if (par1EntityLiving instanceof EntityVillager)
			{
				return true;
			}
		}

		
		if (!this.MyCanSee(par1EntityLiving))
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
		
		if (par1EntityLiving instanceof EntityHorse)
		{
			return true;
		}
		if (par1EntityLiving instanceof EntityMob)
		{
			return true;
		}
		
		if (par1EntityLiving instanceof EntityDragon)
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
		if (OreSpawnMain.PlayNicely != 0) {
			this.head_found = 1;
			return null;
		}
		
		if (this.isEnd == 2) {
			List var5p = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand((double)80.0F, (double)64.0F, (double)80.0F));
			Collections.sort(var5p, this.TargetSorter);
			Iterator var2p = var5p.iterator();
			Entity var3p = null;
			EntityLivingBase var4p = null;
			EntityLivingBase retp = null;
			this.head_found = 1;
			while (var2p.hasNext())
			{
				var3p = (Entity)var2p.next();
				var4p = (EntityLivingBase)var3p;
				
				if (this.isSuitableTarget(var4p, false))
				{
					return var4p;
				}
			}
		}
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand((double)80.0F, (double)64.0F, (double)80.0F));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityLivingBase var4 = null;
		EntityLivingBase ret = null;
		
		this.head_found = 0;
		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (EntityLivingBase)var3;
			
			if (this.isSuitableTarget(var4, false))
			{
				
				if (ret == null) ret = var4;
			}
			if (ret != null && this.head_found != 0) break;
		}
		return ret;
	}

	public void setGuardMode(int i)
	{
		this.guard_mode = i;
	}

	public void setFree()
	{
		this.isEnd = 1;
	}

	
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setInteger("KingHomeX", this.homex);
		par1NBTTagCompound.setInteger("KingHomeZ", this.homez);
		par1NBTTagCompound.setInteger("GuardMode", this.guard_mode);
		par1NBTTagCompound.setInteger("PlayerHits", this.player_hit_count);
		par1NBTTagCompound.setInteger("IsEnd", this.isEnd);
		par1NBTTagCompound.setInteger("EndCounter", this.endCounter);
		
	}
	
	
	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		
		this.homex = par1NBTTagCompound.getInteger("KingHomeX");
		this.homez = par1NBTTagCompound.getInteger("KingHomeZ");
		this.guard_mode = par1NBTTagCompound.getInteger("GuardMode");
		this.player_hit_count = par1NBTTagCompound.getInteger("PlayerHits");
		this.isEnd = par1NBTTagCompound.getInteger("IsEnd");
		this.endCounter = par1NBTTagCompound.getInteger("EndCounter");
	}

	
	
	
	
	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6)
	{
		Entity var8 = null;
		var8 = EntityList.createEntityByName(par1, par0World);
		if (var8 != null)
		{
			
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			
			
			par0World.spawnEntityInWorld(var8);
		}
		return var8;
	}

	private EntityLivingBase doJumpDamage(double X, double Y, double Z, double dist, double damage, int knock)
	{
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
			
			if (var4 == null)
			{
				continue;
			}
			if (var4 == this)
			{
				continue;
			}
			if (!var4.isEntityAlive())
			{
				continue;
			}
			if (MyUtils.isRoyalty(var4)) {
				continue;
			}
			
			if (var4 instanceof Ghost) continue;
			if (var4 instanceof GhostSkelly) continue;
			
			DamageSource var21 = null;
			var21 = DamageSource.setExplosionSource(null);
			var21.setExplosion();
			var4.attackEntityFrom(var21, (float)damage / 2.0F);
			var4.attackEntityFrom(DamageSource.fall, (float)damage / 2.0F);
			this.worldObj.playSoundAtEntity(var4, "random.explode", 0.65F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.5F);
			if (knock != 0) {
				double ks = (double)2.75F;
				double inair = 0.65;
				float f3 = (float)Math.atan2(var4.posZ - this.posZ, var4.posX - this.posX);
				var4.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
			}
			
		}
		return null;
	}
}
