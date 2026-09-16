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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Crab extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private int hurt_timer = 0;
	private float moveSpeed = 0.55F;
	private int closest = 99999;
	private int tx = 0;
	private int ty = 0;
	private int tz = 0;

	public Crab(World par1World) {
		super(par1World);
		this.moveSpeed = 0.55F;
		this.setSize(1.25F, 2.5F);
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 150;
		this.fireResistance = 30;
		this.isImmuneToFire = false;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)(this.moveSpeed * this.getCrabScale()));
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)((float)OreSpawnMain.Crab_stats.attack * this.getCrabScale()));
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
		this.dataWatcher.addObject(21, 0);
		float t = 0.25F;
		if (this.worldObj != null) {
			if (this.worldObj.rand.nextInt(4) == 1) {
				t = 0.5F;
			}

			if (this.worldObj.rand.nextInt(8) == 2) {
				t = 1.0F;
			}
		} else {
			if (OreSpawnMain.OreSpawnRand.nextInt(4) == 1) {
				t = 0.5F;
			}

			if (OreSpawnMain.OreSpawnRand.nextInt(8) == 2) {
				t = 1.0F;
			}
		}

		this.setCrabScale(t);
		this.experienceValue = (int)(400.0F * t);
		this.fireResistance = (int)(10.0F * t);
		this.setSize(3.75F * this.getCrabScale(), 3.5F * this.getCrabScale());
	}

	public float getCrabScale() {
		int i = this.dataWatcher.getWatchableObjectInt(21);
		float f = (float)i;
		return f / 100.0F;
	}

	public void setCrabScale(float par1) {
		float f = par1 * 100.0F;
		int i = (int)f;
		this.dataWatcher.updateObject(21, i);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setCrabScale(par1NBTTagCompound.getFloat("Fscale"));
		this.setSize(3.75F * this.getCrabScale(), 3.5F * this.getCrabScale());
		this.experienceValue = (int)(400.0F * this.getCrabScale());
		this.fireResistance = (int)(10.0F * this.getCrabScale());
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setFloat("Fscale", this.getCrabScale());
	}

	protected boolean canDespawn() {
		return !this.isNoDespawnRequired();
	}

	public void onUpdate() {
		if (this.isInWater()) {
			this.moveSpeed = 0.95F;
		} else {
			this.moveSpeed = 0.55F;
		}

		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)(this.moveSpeed * this.getCrabScale()));
		super.onUpdate();
		this.setSize(2.5F * this.getCrabScale(), 3.5F * this.getCrabScale());
	}

	public int mygetMaxHealth() {
		return (int)((float)OreSpawnMain.PitchBlack_stats.health * this.getCrabScale());
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Crab_stats.defense + (int)(2.0F * this.getCrabScale());
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public int getCrabHealth() {
		return (int)this.getHealth();
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "orespawn:leaves_hit";
	}

	protected String getDeathSound() {
		return null;
	}

	protected float getSoundVolume() {
		return 0.75F;
	}

	protected float getSoundPitch() {
		return 2.0F - 0.3F * (1.0F / this.getCrabScale());
	}

	protected Item getDropItem() {
		return Items.fish;
	}

	private ItemStack dropItemRand(Item index, int par1) {
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), is);
		if (var3 != null) {
			this.worldObj.spawnEntityInWorld(var3);
		}

		return is;
	}

	protected void dropFewItems(boolean par1, int par2) {
		ItemStack is = null;
		int var5 = 4 + this.worldObj.rand.nextInt(8);
		var5 = (int)((float)var5 * this.getCrabScale());
		if (var5 < 1) {
			var5 = 1;
		}

		for (int var4 = 0; var4 < var5; ++var4) {
			this.dropItemRand(OreSpawnMain.MyRawCrabMeat, 1);
		}

	}

	public void initCreature() {
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)OreSpawnMain.Crab_stats.attack * this.getCrabScale());
		if (var4 && par1Entity != null && par1Entity instanceof EntityLivingBase) {
			double ks = 1.15 * (double)this.getCrabScale();
			double inair = 0.48 * (double)this.getCrabScale();
			float f3 = (float)Math.atan2(par1Entity.posZ - this.posZ, par1Entity.posX - this.posX);
			if (par1Entity.isDead || par1Entity instanceof EntityPlayer) {
				inair *= 2.0D;
			}

			par1Entity.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
		}

		return var4;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		if (par1DamageSource.getDamageType().equals("cactus")) {
			return false;
		} else {
			Entity e = par1DamageSource.getEntity();
			if (this.hurt_timer <= 0) {
				ret = super.attackEntityFrom(par1DamageSource, par2);
				this.hurt_timer = 8;
			}

			if (e != null && e instanceof EntityLiving) {
				if (e instanceof Crab) {
					return false;
				}

				this.setAttackTarget((EntityLiving)e);
				this.setTarget(e);
				this.getNavigator().tryMoveToEntityLiving((EntityLiving)e, 1.2);
			}

			return ret;
		}
	}

	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
		int found = 0;

		for (int i = -dy; i <= dy; i++) {
			for (int j = -dz; j <= dz; j++) {
				Block bid = this.worldObj.getBlock(x + dx, y + i, z + j);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dx * dx + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + dx;
						this.ty = y + i;
						this.tz = z + j;
						++found;
					}
				}

				bid = this.worldObj.getBlock(x - dx, y + i, z + j);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dx * dx + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x - dx;
						this.ty = y + i;
						this.tz = z + j;
						++found;
					}
				}
			}
		}

		for (int var12 = -dx; var12 <= dx; ++var12) {
			for (int j = -dz; j <= dz; j++) {
				Block bid = this.worldObj.getBlock(x + var12, y + dy, z + j);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dy * dy + j * j + var12 * var12;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + var12;
						this.ty = y + dy;
						this.tz = z + j;
						++found;
					}
				}

				bid = this.worldObj.getBlock(x + var12, y - dy, z + j);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dy * dy + j * j + var12 * var12;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + var12;
						this.ty = y - dy;
						this.tz = z + j;
						++found;
					}
				}
			}
		}

		for (int var13 = -dx; var13 <= dx; ++var13) {
			for (int j = -dy; j <= dy; j++) {
				Block bid = this.worldObj.getBlock(x + var13, y + j, z + dz);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dz * dz + j * j + var13 * var13;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + var13;
						this.ty = y + j;
						this.tz = z + dz;
						++found;
					}
				}

				bid = this.worldObj.getBlock(x + var13, y + j, z - dz);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dz * dz + j * j + var13 * var13;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + var13;
						this.ty = y + j;
						this.tz = z - dz;
						++found;
					}
				}
			}
		}

		if (found != 0) {
			return true;
		} else {
			return false;
		}
	}

	protected void updateAITasks() {
		if (!this.isDead) {
			super.updateAITasks();
			if (this.hurt_timer > 0) {
				--this.hurt_timer;
			}

			if (!this.isInWater() && this.worldObj.rand.nextInt(25) == 0) {
				this.closest = 99999;
				this.tx = this.ty = this.tz = 0;

				for (int i = 1; i < 12; i++) {
					int j = i;
					if (i > 10) {
						j = 10;
					}

					if (this.scan_it((int)this.posX, (int)this.posY - 1, (int)this.posZ, i, j, i)) {
						break;
					}

					if (i >= 5) {
						++i;
					}
				}

				if (this.closest < 99999) {
					this.getNavigator().tryMoveToXYZ((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.33);
				} else {
					if (this.worldObj.rand.nextInt(100) == 1) {
						this.heal(-1.0F * this.getCrabScale());
					}

					if (this.getHealth() <= 0.0F) {
						this.setDead();
						return;
					}
				}
			}

			if (this.worldObj.rand.nextInt(5) == 1) {
				EntityLivingBase e = null;
				if (this.worldObj.rand.nextInt(100) == 1) {
					this.setAttackTarget((EntityLivingBase)null);
				}

				e = this.getAttackTarget();
				if (e != null && !e.isEntityAlive()) {
					this.setAttackTarget((EntityLivingBase)null);
					e = null;
				}

				if (e == null) {
					e = this.findSomethingToAttack();
				}

				if (e != null) {
					this.faceEntity(e, 10.0F, 10.0F);
					if (this.getDistanceSqToEntity(e) < (double)((6.0F + e.width / 2.0F) * (6.0F + e.width / 2.0F) * this.getCrabScale())) {
						this.setAttacking(1);
						if (this.worldObj.rand.nextInt(4) == 0 || this.worldObj.rand.nextInt(5) == 1) {
							this.attackEntityAsMob(e);
							if (!this.worldObj.isRemote) {
								if (this.worldObj.rand.nextInt(3) == 1) {
									this.worldObj.playSoundAtEntity(e, "orespawn:scorpion_attack", 0.75F, 1.5F);
								} else {
									this.worldObj.playSoundAtEntity(e, "orespawn:scorpion_living", 0.75F, 1.5F);
								}
							}
						}
					} else {
						this.getNavigator().tryMoveToEntityLiving(e, 1.0D);
					}
				} else {
					this.setAttacking(0);
				}
			}

			if (this.worldObj.rand.nextInt(120) == 1 && this.isInWater() && this.getHealth() < (float)this.mygetMaxHealth()) {
				this.playSound("splash", 1.5F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
				this.heal(4.0F * this.getCrabScale());
			}

		}
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
		} else if (par1EntityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			return !p.capabilities.isCreativeMode;
		} else if (par1EntityLiving instanceof Crab) {
			return false;
		} else if (par1EntityLiving instanceof EntityMob) {
			return true;
		} else if (par1EntityLiving instanceof Lizard) {
			return true;
		} else if (par1EntityLiving instanceof RubberDucky) {
			return true;
		} else if (par1EntityLiving instanceof EntityVillager) {
			return true;
		} else if (par1EntityLiving instanceof Girlfriend) {
			return true;
		} else if (par1EntityLiving instanceof Boyfriend) {
			return true;
		} else {
			return OreSpawnMain.OreSpawnUtils.isAttackableNonMob(par1EntityLiving);
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0D, 6.0D, 16.0D));
			Collections.sort(var5, this.TargetSorter);
			Iterator var2 = var5.iterator();
			Entity var3 = null;
			EntityLivingBase var4 = null;
			EntityLivingBase e = this.getAttackTarget();
			if (e != null && e.isEntityAlive()) {
				return e;
			} else {
				this.setAttackTarget((EntityLivingBase)null);

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

	public final int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1) {
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	private int findBuddies() {
		List var5 = this.worldObj.getEntitiesWithinAABB(Crab.class, this.boundingBox.expand((double)24.0F, 8.0D, (double)24.0F));
		return var5.size();
	}

	public boolean getCanSpawnHere() {
		for (int k = -3; k < 3; k++) {
			for (int j = -3; j < 3; j++) {
				for (int i = 0; i < 5; i++) {
					Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null && s.equals("Crab")) {
							this.setCrabScale(0.35F);
							return true;
						}
					}
				}
			}
		}

		if (this.posY < 50.0D) {
			return false;
		} else if (!this.worldObj.isDaytime()) {
			return false;
		} else {
			if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID5) {
				if (this.worldObj.rand.nextInt(40) != 1) {
					return false;
				}

				if (this.findBuddies() > 3) {
					return false;
				}
			}

			return true;
		}
	}

	public boolean canBreatheUnderwater() {
		return true;
	}
}
