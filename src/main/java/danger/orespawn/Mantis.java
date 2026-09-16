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
import net.minecraft.entity.passive.EntitySquid;
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

public class Mantis extends EntityMob {
	private ChunkCoordinates currentFlightTarget = null;
	private GenericTargetSorter TargetSorter = null;
	private int stuck_count = 0;
	private int lastX = 0;
	private int lastZ = 0;
	private Entity rt = null;

	public Mantis(World par1World) {
		super(par1World);
		this.setSize(2.5F, 3.25F);
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 100;
		this.isImmuneToFire = false;
		this.fireResistance = 5;
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.32F);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Mantis_stats.attack);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
	}

	protected boolean canDespawn() {
		return !this.isNoDespawnRequired();
	}

	public final int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1) {
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	protected float getSoundVolume() {
		return 0.35F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected String getLivingSound() {
		return "orespawn:Beebuzz";
	}

	protected String getHurtSound() {
		return "orespawn:dragonfly_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:alo_death";
	}

	public boolean canBePushed() {
		return true;
	}

	protected void collideWithEntity(Entity par1Entity) {
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.Mantis_stats.health;
	}

	protected Item getDropItem() {
		return Item.getItemFromBlock(Blocks.yellow_flower);
	}

	private void dropItemRand(Item index, int par1) {
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2) {
		this.dropItemRand(OreSpawnMain.MyMantisClaw, 1);
		this.dropItemRand(OreSpawnMain.MyMantisClaw, 1);
		this.dropItemRand(Items.item_frame, 1);
		int var4 = 2 + this.worldObj.rand.nextInt(10);

		for (int i = 0; i < var4; i++) {
			this.dropItemRand(Items.gold_nugget, 1);
		}

		var4 = 1 + this.worldObj.rand.nextInt(3);

		for (int var8 = 0; var8 < var4; ++var8) {
			this.dropItemRand(OreSpawnMain.UraniumNugget, 1);
		}

		var4 = 1 + this.worldObj.rand.nextInt(3);

		for (int var9 = 0; var9 < var4; ++var9) {
			this.dropItemRand(OreSpawnMain.TitaniumNugget, 1);
		}

		var4 = 2 + this.worldObj.rand.nextInt(3);

		for (int var10 = 0; var10 < var4; ++var10) {
			this.dropItemRand(Items.diamond, 1);
		}

	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onUpdate() {
		super.onUpdate();
		this.motionY *= 0.6;
		if (this.isInWater() && this.worldObj.rand.nextInt(20) == 1) {
			this.attackEntityAsMob(this);
		}

	}

	public boolean canSeeTarget(double pX, double pY, double pZ) {
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	protected void updateAITasks() {
		int xdir = 1;
		int zdir = 1;
		int keep_trying = 50;
		if (!this.isDead) {
			super.updateAITasks();
			if (this.lastX == (int)this.posX && this.lastZ == (int)this.posZ) {
				++this.stuck_count;
			} else {
				this.stuck_count = 0;
				this.lastX = (int)this.posX;
				this.lastZ = (int)this.posZ;
			}

			if (this.currentFlightTarget == null) {
				this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
			}

			if (this.stuck_count <= 50 && this.rand.nextInt(300) != 0 && !(this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 2.1F)) {
				if (this.rand.nextInt(8) == 0) {
					EntityLivingBase e = null;
					e = (EntityLivingBase)this.rt;
					if (e != null && e.isDead) {
						e = null;
					}

					if (e == null) {
						e = this.findSomethingToAttack();
					}

					if (e != null) {
						this.setAttacking(1);
						this.currentFlightTarget.set((int)e.posX, (int)e.posY + 1, (int)e.posZ);
						if (this.getDistanceSqToEntity(e) < (double)((5.0F + e.width / 2.0F) * (5.0F + e.width / 2.0F))) {
							this.attackEntityAsMob(e);
						}
					} else {
						this.setAttacking(0);
					}
				}
			} else {
				Block bid = Blocks.stone;

				for (this.stuck_count = 0; bid != Blocks.air && keep_trying != 0; --keep_trying) {
					zdir = this.rand.nextInt(9) + 4;
					xdir = this.rand.nextInt(9) + 4;
					if (this.rand.nextInt(2) == 0) {
						zdir = -zdir;
					}

					if (this.rand.nextInt(2) == 0) {
						xdir = -xdir;
					}

					this.currentFlightTarget.set((int)this.posX + xdir, (int)this.posY + this.rand.nextInt(6) - 3, (int)this.posZ + zdir);
					bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
					if (bid == Blocks.air && !this.canSeeTarget((double)this.currentFlightTarget.posX, (double)this.currentFlightTarget.posY, (double)this.currentFlightTarget.posZ)) {
						bid = Blocks.stone;
					}
				}
			}

			double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
			double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
			double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
			this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.30000000149011613;
			this.motionY += (Math.signum(var3) * (double)0.7F - this.motionY) * 0.20000000149011612;
			this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.30000000149011613;
			float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
			this.moveForward = 1.0F;
			this.rotationYaw += var8 / 4.0F;
			if (this.worldObj.rand.nextInt(100) == 1) {
				this.heal(1.0F);
			}

		}
	}

	protected boolean canTriggerWalking() {
		return true;
	}

	protected void fall(float par1) {
	}

	protected void updateFallState(double par1, boolean par3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return false;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = super.attackEntityFrom(par1DamageSource, par2);
		Entity e = par1DamageSource.getEntity();
		if (e != null && e instanceof EntityLivingBase && this.currentFlightTarget != null) {
			this.rt = e;
			this.currentFlightTarget.set((int)e.posX, (int)e.posY, (int)e.posZ);
		}

		return ret;
	}

	public boolean getCanSpawnHere() {
		for (int k = -2; k <= 2; k++) {
			for (int j = -2; j <= 2; j++) {
				for (int i = 1; i < 4; i++) {
					Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null && s.equals("Mantis")) {
							return true;
						}
					}
				}
			}
		}

		for (int var10 = -2; var10 < 2; ++var10) {
			for (int j = -2; j < 2; j++) {
				for (int i = 1; i < 6; i++) {
					Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + var10);
					if (bid != Blocks.air) {
						return false;
					}
				}
			}
		}

		if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID6 && this.worldObj.rand.nextInt(6) != 0) {
			return false;
		} else if (this.posY < 50.0D) {
			return false;
		} else if (!this.worldObj.isDaytime()) {
			return false;
		} else {
			Mantis target = null;
			target = (Mantis)this.worldObj.findNearestEntityWithinAABB(Mantis.class, this.boundingBox.expand(32.0D, 16.0D, 32.0D), this);
			if (target != null) {
				return false;
			} else {
				return true;
			}
		}
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Mantis_stats.defense;
	}

	public void initCreature() {
	}

	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
		if (par1EntityLiving == null) {
			return false;
		} else if (par1EntityLiving == this) {
			return false;
		} else if (!par1EntityLiving.isEntityAlive()) {
			return false;
		} else if (!this.getEntitySenses().canSee(par1EntityLiving)) {
			return false;
		} else if (par1EntityLiving.isInWater()) {
			return false;
		} else if (par1EntityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			return !p.capabilities.isCreativeMode;
		} else if (par1EntityLiving instanceof Mantis) {
			return false;
		} else if (par1EntityLiving instanceof Irukandji) {
			return false;
		} else if (par1EntityLiving instanceof Skate) {
			return false;
		} else if (par1EntityLiving instanceof Flounder) {
			return false;
		} else if (par1EntityLiving instanceof Whale) {
			return false;
		} else if (par1EntityLiving instanceof EntitySquid) {
			return false;
		} else if (par1EntityLiving instanceof WaterDragon) {
			return false;
		} else if (par1EntityLiving instanceof AttackSquid) {
			return false;
		} else if (par1EntityLiving instanceof TerribleTerror) {
			return false;
		} else if (par1EntityLiving instanceof LurkingTerror) {
			return false;
		} else if (par1EntityLiving instanceof CloudShark) {
			return false;
		} else if (par1EntityLiving instanceof Rotator) {
			return false;
		} else if (par1EntityLiving instanceof Bee) {
			return false;
		} else if (par1EntityLiving instanceof Mothra) {
			return false;
		} else if (par1EntityLiving instanceof EntityMob) {
			return true;
		} else if (par1EntityLiving instanceof EntityButterfly) {
			return true;
		} else if (par1EntityLiving instanceof Cockateil) {
			return true;
		} else if (par1EntityLiving instanceof Fairy) {
			return true;
		} else {
			if (par1EntityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)par1EntityLiving;
				if (!p.capabilities.isCreativeMode) {
					return true;
				}
			}

			return OreSpawnMain.OreSpawnUtils.isAttackableNonMob(par1EntityLiving);
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
			Collections.sort(var5, this.TargetSorter);
			Iterator var2 = var5.iterator();
			Entity var3 = null;
			EntityLivingBase var4 = null;

			while (var2.hasNext()) {
				var3 = (Entity)var2.next();
				var4 = (EntityLivingBase)var3;
				if (this.isSuitableTarget(var4, false)) {
					return var4;
				}
			}

			return null;
		}
	}
}
