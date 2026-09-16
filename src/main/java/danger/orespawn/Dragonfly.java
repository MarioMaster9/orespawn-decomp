package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class Dragonfly extends EntityAnimal {
	private ChunkCoordinates currentFlightTarget = null;
	private GenericTargetSorter TargetSorter = null;

	public Dragonfly(World par1World) {
		super(par1World);
		this.setSize(1.5F, 0.5F);
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 5;
		this.isImmuneToFire = false;
		this.fireResistance = 5;
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.33F);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)2.0F);
	}

	protected void entityInit() {
		super.entityInit();
	}

	protected boolean canDespawn() {
		return !this.isNoDespawnRequired();
	}

	protected float getSoundVolume() {
		return 0.25F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected String getLivingSound() {
		return "orespawn:dragonfly_living";
	}

	protected String getHurtSound() {
		return "orespawn:dragonfly_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:dragonfly_death";
	}

	public boolean canBePushed() {
		return true;
	}

	protected void collideWithEntity(Entity par1Entity) {
	}

	public int mygetMaxHealth() {
		return 10;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onUpdate() {
		super.onUpdate();
		this.motionY *= 0.6;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
		return var4;
	}

	public boolean canSeeTarget(double pX, double pY, double pZ) {
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.25D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	protected void updateAITasks() {
		int xdir = 1;
		int zdir = 1;
		int keep_trying = 50;
		if (!this.isDead) {
			super.updateAITasks();
			if (this.currentFlightTarget == null) {
				this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
			}

			if (this.rand.nextInt(300) != 0 && !(this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 2.1F)) {
				if (this.rand.nextInt(12) == 0 && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) {
					EntityLivingBase e = null;
					e = this.findSomethingToAttack();
					if (e != null) {
						this.currentFlightTarget.set((int)e.posX, (int)(e.posY + 1.0D), (int)e.posZ);
						if (this.getDistanceSqToEntity(e) < 6.0D) {
							this.attackEntityAsMob(e);
						}
					}
				}
			} else {
				for (Block bid = Blocks.stone; bid != Blocks.air && keep_trying != 0; --keep_trying) {
					zdir = this.rand.nextInt(5) + 5;
					xdir = this.rand.nextInt(5) + 5;
					if (this.rand.nextInt(2) == 0) {
						zdir = -zdir;
					}

					if (this.rand.nextInt(2) == 0) {
						xdir = -xdir;
					}

					this.currentFlightTarget.set((int)this.posX + xdir, (int)this.posY + this.rand.nextInt(5) - 2, (int)this.posZ + zdir);
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
		if (e != null && this.currentFlightTarget != null) {
			this.currentFlightTarget.set((int)e.posX, (int)e.posY, (int)e.posZ);
		}

		return ret;
	}

	public boolean getCanSpawnHere() {
		if (this.posY < 50.0D) {
			return false;
		} else {
			return this.worldObj.isDaytime();
		}
	}

	public void initCreature() {
	}

	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			return false;
		} else if (par1EntityLiving == null) {
			return false;
		} else if (par1EntityLiving == this) {
			return false;
		} else if (!par1EntityLiving.isEntityAlive()) {
			return false;
		} else if (!this.getEntitySenses().canSee(par1EntityLiving)) {
			return false;
		} else if (par1EntityLiving instanceof EntityAnt) {
			return true;
		} else if (par1EntityLiving instanceof EntityButterfly) {
			return true;
		} else if (par1EntityLiving instanceof Cockateil) {
			return true;
		} else if (par1EntityLiving instanceof EntityMosquito) {
			return true;
		} else if (par1EntityLiving instanceof Firefly) {
			return true;
		} else {
			return par1EntityLiving instanceof EntityHorse && OreSpawnMain.DragonflyHorseFriendly == 0;
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(10.0D, 6.0D, 10.0D));
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

	protected Item getDropItem() {
		int i = this.worldObj.rand.nextInt(6);
		if (i == 0) {
			return Items.gold_nugget;
		} else if (i == 1) {
			return OreSpawnMain.UraniumNugget;
		} else {
			return i == 2 ? OreSpawnMain.TitaniumNugget : null;
		}
	}

	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}
}
