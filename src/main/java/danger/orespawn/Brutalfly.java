package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class Brutalfly extends EntityMob {
	private ChunkCoordinates currentFlightTarget = null;
	private int lastX = 0;
	private int lastZ = 0;
	private int lastY = 0;
	private int stuck_count = 0;
	private int wing_sound = 0;
	private int health_ticker = 100;
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.35F;

	public Brutalfly(World par1World) {
		super(par1World);
		this.setSize(5.0F, 2.0F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 100;
		this.isImmuneToFire = true;
		this.fireResistance = 500;
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Brutalfly_stats.attack);
	}

	protected boolean canDespawn() {
		return !this.isNoDespawnRequired();
	}

	protected void entityInit() {
		super.entityInit();
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Brutalfly_stats.defense;
	}

	public int getBrutalflyHealth() {
		return (int)this.getHealth();
	}

	protected float getSoundVolume() {
		return 1.5F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return "random.explode";
	}

	public boolean canBePushed() {
		return true;
	}

	protected void collideWithEntity(Entity par1Entity) {
	}

	protected void collideWithNearbyEntities() {
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.Brutalfly_stats.health;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onUpdate() {
		super.onUpdate();
		this.motionY *= 0.6;
		++this.wing_sound;
		if (this.wing_sound > 30) {
			if (!this.worldObj.isRemote) {
				this.worldObj.playSoundAtEntity(this, "orespawn:MothraWings", 1.0F, 1.0F);
			}

			this.wing_sound = 0;
		}

		--this.health_ticker;
		if (this.health_ticker <= 0) {
			if (this.getHealth() < (float)this.mygetMaxHealth()) {
				this.heal(1.0F);
			}

			this.health_ticker = 100;
		}

	}

	public boolean canSeeTarget(double pX, double pY, double pZ) {
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	protected void updateAITasks() {
		int xdir = 1;
		int zdir = 1;
		int keep_trying = 30;
		int shoot = 3;
		if (!this.isDead) {
			super.updateAITasks();
			if (this.lastX == (int)this.posX && this.lastY == (int)this.posY && this.lastZ == (int)this.posZ) {
				++this.stuck_count;
			} else {
				this.stuck_count = 0;
				this.lastX = (int)this.posX;
				this.lastY = (int)this.posY;
				this.lastZ = (int)this.posZ;
			}

			if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
				shoot = 2;
			}

			if (this.currentFlightTarget == null) {
				this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
			}

			if (this.stuck_count > 30 || this.worldObj.rand.nextInt(200) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 9.0F) {
				int down = 0;
				int dist = 20;

				for (int i = -5; i <= 5; i += 5) {
					for (int j = -5; j <= 5; j += 5) {
						for (int k = 1; k < 20; k++) {
							Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY - k, (int)this.posZ + i);
							if (bid != Blocks.air) {
								if (k < dist) {
									dist = k;
								}
								break;
							}
						}
					}
				}

				if (dist > 10) {
					down = dist - 10 + 1;
				}

				for (Block bid = Blocks.stone; bid != Blocks.air && keep_trying != 0; --keep_trying) {
					xdir = 1;
					zdir = 1;
					if (this.worldObj.rand.nextInt(2) == 0) {
						xdir = -1;
					}

					if (this.worldObj.rand.nextInt(2) == 0) {
						zdir = -1;
					}

					int newz = this.rand.nextInt(20) + 8;
					newz *= zdir;
					int newx = this.rand.nextInt(20) + 8;
					newx *= xdir;
					this.currentFlightTarget.set((int)this.posX + newx, (int)this.posY + this.worldObj.rand.nextInt(7) - 1 - down, (int)this.posZ + newz);
					bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
					if (bid == Blocks.air && !this.canSeeTarget((double)this.currentFlightTarget.posX, (double)this.currentFlightTarget.posY, (double)this.currentFlightTarget.posZ)) {
						bid = Blocks.stone;
					}
				}

				this.stuck_count = 0;
			}

			if (this.worldObj.rand.nextInt(6) == 0) {
				EntityPlayer target = null;
				target = (EntityPlayer)this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand((double)30.0F, 20.0D, (double)30.0F), this);
				if (target != null) {
					if (!target.capabilities.isCreativeMode) {
						if (this.getEntitySenses().canSee(target)) {
							this.currentFlightTarget.set((int)target.posX, (int)target.posY + 4, (int)target.posZ);
							if (this.rand.nextInt(shoot) == 0) {
								this.attackWithSomething(target);
							}
						}
					} else {
						target = null;
					}
				}

				if (target == null && this.worldObj.rand.nextInt(3) == 0) {
					EntityLivingBase e = null;
					e = this.findSomethingToAttack();
					if (e != null) {
						this.currentFlightTarget.set((int)e.posX, (int)e.posY + 5, (int)e.posZ);
						if (this.getDistanceSqToEntity(e) > 25.0D) {
							if (this.worldObj.rand.nextInt(shoot) == 0) {
								this.attackWithSomething(e);
							}
						} else {
							this.attackEntityAsMob(e);
						}
					}
				}
			}

			double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
			double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
			double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
			this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.30001;
			this.motionY += (Math.signum(var3) * 0.7 - this.motionY) * 0.20001;
			this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.30001;
			float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
			this.moveForward = 1.0F;
			this.rotationYaw += var8 / 8.0F;
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	protected void fall(float par1) {
	}

	protected void updateFallState(double par1, boolean par3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		Entity e = par1DamageSource.getEntity();
		if (e != null && e instanceof Brutalfly) {
			return false;
		} else {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			if (e != null && this.currentFlightTarget != null) {
				this.currentFlightTarget.set((int)e.posX, (int)e.posY + 2, (int)e.posZ);
			}

			return ret;
		}
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
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
						if (s != null && s.equals("Brutalfly")) {
							return true;
						}
					}
				}
			}
		}

		if (this.posY < (double)70.0F) {
			return false;
		} else if (!this.isValidLightLevel()) {
			return false;
		} else if (this.worldObj.isDaytime()) {
			return false;
		} else {
			for (int var10 = -4; var10 < 4; ++var10) {
				for (int j = -3; j < 3; j++) {
					for (int i = 1; i < 10; i++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + var10);
						if (bid != Blocks.air) {
							return false;
						}
					}
				}
			}

			Brutalfly target = null;
			target = (Brutalfly)this.worldObj.findNearestEntityWithinAABB(Brutalfly.class, this.boundingBox.expand((double)64.0F, 32.0D, (double)64.0F), this);
			if (target != null) {
				return false;
			} else {
				return true;
			}
		}
	}

	public void initCreature() {
	}

	private void dropItemRand(Item index, int par1) {
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(8) - (double)OreSpawnMain.OreSpawnRand.nextInt(8), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(8) - (double)OreSpawnMain.OreSpawnRand.nextInt(8), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2) {
		for (int i = 0; i < 20; i++) {
			float var1 = (this.rand.nextFloat() - 0.5F) * 8.0F;
			float var2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
			float var3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
			this.worldObj.spawnParticle("largeexplode", this.posX + (double)var1, this.posY + 2.0D + (double)var2, this.posZ + (double)var3, 0.0D, 0.0D, 0.0D);
		}

		for (int var4 = 0; var4 < 53; ++var4) {
			this.dropItemRand(Items.gold_nugget, 1);
		}

		for (int var8 = 0; var8 < 20; ++var8) {
			spawnCreature(this.worldObj, "Butterfly", this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D);
		}

	}

	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
		Entity var8 = null;
		if (par0World == null) {
			return null;
		} else {
			var8 = EntityList.createEntityByName(par1, par0World);
			if (var8 != null) {
				var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
				par0World.spawnEntityInWorld(var8);
				((EntityLiving)var8).playLivingSound();
			}

			return var8;
		}
	}

	private void attackWithSomething(EntityLivingBase par1) {
		double xzoff = 2.25D;
		double yoff = 0.0D;
		double cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		double cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		if (this.worldObj.difficultySetting == EnumDifficulty.EASY) {
			EntitySmallFireball sf = new EntitySmallFireball(this.worldObj, this, par1.posX - cx, par1.posY + 0.55 - (this.posY + yoff), par1.posZ - cz);
			sf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
			sf.setPosition(cx, this.posY + yoff, cz);
			this.worldObj.playSoundAtEntity(this, "random.bow", 0.75F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			this.worldObj.spawnEntityInWorld(sf);
		} else if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL) {
			if (this.worldObj.rand.nextInt(2) == 0) {
				EntitySmallFireball sf = new EntitySmallFireball(this.worldObj, this, par1.posX - cx, par1.posY + 0.55 - (this.posY + yoff), par1.posZ - cz);
				sf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
				sf.setPosition(cx, this.posY + yoff, cz);
				this.worldObj.playSoundAtEntity(this, "random.bow", 0.75F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
				this.worldObj.spawnEntityInWorld(sf);
			} else {
				BetterFireball bf = new BetterFireball(this.worldObj, this, par1.posX - cx, par1.posY + 0.55 - (this.posY + yoff), par1.posZ - cz);
				bf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
				bf.setPosition(cx, this.posY + yoff, cz);
				bf.setNotMe();
				this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
				this.worldObj.spawnEntityInWorld(bf);
			}
		} else {
			BetterFireball bf = new BetterFireball(this.worldObj, this, par1.posX - cx, par1.posY + 0.55 - (this.posY + yoff), par1.posZ - cz);
			bf.setLocationAndAngles(cx, this.posY + yoff, cz, this.rotationYaw, 0.0F);
			bf.setPosition(cx, this.posY + yoff, cz);
			bf.setNotMe();
			this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			this.worldObj.spawnEntityInWorld(bf);
		}

		if (this.getHealth() < (float)this.mygetMaxHealth()) {
			this.heal(1.0F);
		}

	}

	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
		if (par1EntityLiving == null) {
			return false;
		} else if (par1EntityLiving == this) {
			return false;
		} else if (!par1EntityLiving.isEntityAlive()) {
			return false;
		} else if (par1EntityLiving instanceof Brutalfly) {
			return false;
		} else if (par1EntityLiving instanceof Mothra) {
			return false;
		} else if (par1EntityLiving instanceof Vortex) {
			return false;
		} else {
			if (OreSpawnMain.OreSpawnUtils.isIgnoreable(par1EntityLiving)) {
				return false;
			} else if (!this.getEntitySenses().canSee(par1EntityLiving)) {
				return false;
			} else if (par1EntityLiving instanceof EntityMob) {
				return true;
			} else if (par1EntityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)par1EntityLiving;
				return !p.capabilities.isCreativeMode;
			} else {
				return false;
			}
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(25.0D, 20.0D, 25.0D));
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
