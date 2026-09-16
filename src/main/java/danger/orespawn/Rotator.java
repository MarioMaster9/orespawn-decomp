package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Rotator extends EntityMob {
	private ChunkCoordinates currentFlightTarget = null;
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private int busy_fighting = 0;
	private int was_spawnered = 0;

	public Rotator(World par1World) {
		super(par1World);
		this.setSize(1.0F, 2.0F);
		this.experienceValue = 35;
		this.isImmuneToFire = true;
		this.fireResistance = 25;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Rotator_stats.attack);
	}

	protected void entityInit() {
		super.entityInit();
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

	public RenderInfo getRenderInfo() {
		return this.renderdata;
	}

	public void setRenderInfo(RenderInfo r) {
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
		if (this.isNoDespawnRequired()) {
			return false;
		} else if (this.busy_fighting != 0) {
			return false;
		} else {
			return this.was_spawnered == 0;
		}
	}

	protected float getSoundVolume() {
		return 0.75F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected String getLivingSound() {
		return "vortexlive";
	}

	protected String getHurtSound() {
		return "orespawn:glasshit";
	}

	protected String getDeathSound() {
		return "orespawn:glassdead";
	}

	public boolean canBePushed() {
		return true;
	}

	protected void collideWithEntity(Entity par1Entity) {
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.Rotator_stats.health;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onUpdate() {
		EntityLivingBase e = null;
		super.onUpdate();
		this.motionY *= 0.6;
		if (this.worldObj.isRemote && this.worldObj.rand.nextInt(10) == 1) {
			this.worldObj.spawnParticle("fireworksSpark", this.posX, this.posY + (double)1.4F, this.posZ, (double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 4.0F), (double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 4.0F), (double)((this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) / 4.0F));
		}

		this.busy_fighting = 0;
		e = this.findSomethingToAttack();
		if (e != null) {
			double a = Math.atan2(e.posZ - this.posZ, e.posX - this.posX);
			this.worldObj.spawnParticle("fireworksSpark", this.posX, this.posY + (double)1.4F, this.posZ, Math.cos(a), (e.posY - this.posY) / 10.0D, Math.sin(a));
			this.busy_fighting = 1;
		}

		if (!this.isNoDespawnRequired()) {
			if (this.busy_fighting == 0) {
				if (this.was_spawnered == 0) {
					long t = this.worldObj.getWorldTime();
					t %= 24000L;
					if (t < 12000L && this.worldObj.rand.nextInt(400) == 1) {
						this.setDead();
					}

				}
			}
		}
	}

	public boolean canSeeTarget(double pX, double pY, double pZ) {
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	protected void updateAITasks() {
		int xdir = 1;
		int zdir = 1;
		int keep_trying = 50;
		EntityLivingBase e = null;
		if (!this.isDead) {
			super.updateAITasks();
			if (this.currentFlightTarget == null) {
				this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
			}

			if (this.rand.nextInt(300) != 0 && !(this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 2.1F)) {
				if (this.rand.nextInt(9) == 2) {
					e = this.findSomethingToAttack();
					if (e != null) {
						double a = Math.atan2(e.posZ - this.posZ, e.posX - this.posX);
						++a;
						this.currentFlightTarget.set((int)(e.posX + 2.5D * Math.cos(a)), (int)e.posY, (int)(e.posZ + 2.5D * Math.sin(a)));
						if (this.getDistanceSqToEntity(e) < 9.0D) {
							this.attackEntityAsMob(e);
						}
					}
				}
			} else {
				for (Block bid = Blocks.stone; bid != Blocks.air && keep_trying != 0; --keep_trying) {
					zdir = this.rand.nextInt(10) + 8;
					xdir = this.rand.nextInt(10) + 8;
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
			this.motionX += (Math.signum(var1) * 0.4 - this.motionX) * 0.2;
			this.motionY += (Math.signum(var3) * (double)0.7F - this.motionY) * 0.20000000149011612;
			this.motionZ += (Math.signum(var5) * 0.4 - this.motionZ) * 0.2;
			float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
			this.moveForward = 0.75F;
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
		return true;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		Entity e = par1DamageSource.getEntity();
		if (e != null && e instanceof EntityArrow) {
			return false;
		} else {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			if (e != null && this.currentFlightTarget != null) {
				this.currentFlightTarget.set((int)e.posX, (int)e.posY, (int)e.posZ);
			}

			return ret;
		}
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
						if (s != null && s.equals("Rotator")) {
							this.was_spawnered = 1;
							return true;
						}
					}
				}
			}
		}

		if (!this.isValidLightLevel()) {
			return false;
		} else {
			for (int var10 = -1; var10 <= 1; ++var10) {
				for (int j = -1; j <= 1; j++) {
					for (int i = 1; i < 3; i++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + var10);
						if (bid != Blocks.air) {
							return false;
						}
					}
				}
			}

			long t = this.worldObj.getWorldTime();
			t %= 24000L;
			if (t < 12000L) {
				return false;
			} else {
				return true;
			}
		}
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Rotator_stats.defense;
	}

	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
		if (par1EntityLiving == null) {
			return false;
		} else if (par1EntityLiving == this) {
			return false;
		} else if (!par1EntityLiving.isEntityAlive()) {
			return false;
		} else {
			if (OreSpawnMain.OreSpawnUtils.isIgnoreable(par1EntityLiving)) {
				return false;
			} else if (!this.getEntitySenses().canSee(par1EntityLiving)) {
				return false;
			} else {
				if (par1EntityLiving instanceof EntityPlayer) {
					EntityPlayer p = (EntityPlayer)par1EntityLiving;
					if (p.capabilities.isCreativeMode) {
						return false;
					}
				}

				if (par1EntityLiving instanceof Termite) {
					return false;
				} else if (par1EntityLiving instanceof Vortex) {
					return false;
				} else if (par1EntityLiving instanceof Rotator) {
					return false;
				} else if (par1EntityLiving instanceof DungeonBeast) {
					return false;
				} else if (par1EntityLiving instanceof Peacock) {
					return false;
				} else if (par1EntityLiving instanceof CrystalCow) {
					return false;
				} else if (par1EntityLiving instanceof Irukandji) {
					return false;
				} else if (par1EntityLiving instanceof Skate) {
					return false;
				} else if (par1EntityLiving instanceof Whale) {
					return false;
				} else if (par1EntityLiving instanceof Flounder) {
					return false;
				} else if (par1EntityLiving instanceof Urchin) {
					return false;
				} else if (par1EntityLiving instanceof TerribleTerror) {
					return false;
				} else if (par1EntityLiving instanceof LurkingTerror) {
					return false;
				} else if (par1EntityLiving instanceof CloudShark) {
					return false;
				} else if (par1EntityLiving instanceof Mothra) {
					return false;
				} else if (par1EntityLiving instanceof Bee) {
					return false;
				} else {
					return !(par1EntityLiving instanceof Mantis);
				}
			}
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(12.0D, 10.0D, 12.0D));
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
		int i = this.worldObj.rand.nextInt(4);
		if (i == 0) {
			return OreSpawnMain.MyCrystalPinkIngot;
		} else if (i == 1) {
			return OreSpawnMain.MyTigersEyeIngot;
		} else if (i == 2) {
			return Item.getItemFromBlock(OreSpawnMain.CrystalCoal);
		} else {
			return i == 3 ? Items.iron_ingot : null;
		}
	}
}
