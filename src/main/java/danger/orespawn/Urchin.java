package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Urchin extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private float moveSpeed = 0.3F;
	private int was_spawnered = 0;

	public Urchin(World par1World) {
		super(par1World);
		this.setSize(1.35F, 2.1F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 20;
		this.fireResistance = 1000;
		this.isImmuneToFire = true;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 14, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Urchin_stats.attack);
	}

	protected void entityInit() {
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
		if (this.isNoDespawnRequired()) {
			return false;
		} else {
			return this.was_spawnered == 0;
		}
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (!this.isNoDespawnRequired()) {
			if (this.was_spawnered == 0) {
				long t = this.worldObj.getWorldTime();
				t %= 24000L;
				if (t < 12000L && this.worldObj.rand.nextInt(400) == 1) {
					this.setDead();
				}

			}
		}
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.Urchin_stats.health;
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

	public int getTotalArmorValue() {
		return OreSpawnMain.Urchin_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.worldObj.rand.nextInt(3) == 1) {
			this.worldObj.spawnParticle("flame", this.posX, this.posY + 0.75D, this.posZ, 0.0D, (double)(this.worldObj.rand.nextFloat() / 10.0F), 0.0D);
			if (this.isInWater() && this.worldObj.rand.nextInt(5) == 1) {
				this.attackEntityAsMob(this);
				this.worldObj.spawnParticle("smoke", this.posX, this.posY + 1.75D, this.posZ, 0.0D, (double)(this.worldObj.rand.nextFloat() / 10.0F), 0.0D);
				this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + 1.75D, this.posZ, 0.0D, (double)(this.worldObj.rand.nextFloat() / 10.0F), 0.0D);
				this.worldObj.spawnParticle("smoke", this.posX, this.posY + 2.0D, this.posZ, 0.0D, (double)(this.worldObj.rand.nextFloat() / 10.0F), 0.0D);
				this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + 2.0D, this.posZ, 0.0D, (double)(this.worldObj.rand.nextFloat() / 10.0F), 0.0D);
			}
		}

	}

	protected String getLivingSound() {
		return "orespawn:kyuubi_living";
	}

	protected String getHurtSound() {
		return "orespawn:glasshit";
	}

	protected String getDeathSound() {
		return "orespawn:glassdead";
	}

	protected float getSoundVolume() {
		return 1.1F;
	}

	protected float getSoundPitch() {
		return 1.25F;
	}

	protected Item getDropItem() {
		int i = this.worldObj.rand.nextInt(3);
		if (i == 1) {
			return OreSpawnMain.MyCrystalPinkIngot;
		} else {
			return i == 2 ? OreSpawnMain.MyCrystalApple : null;
		}
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		par1Entity.setFire(5);
		return super.attackEntityAsMob(par1Entity);
	}

	protected void updateAITasks() {
		if (!this.isDead) {
			super.updateAITasks();
			if (this.worldObj.rand.nextInt(8) == 0) {
				EntityLivingBase e = this.findSomethingToAttack();
				if (e != null) {
					if (this.getDistanceSqToEntity(e) < 8.0D) {
						this.setAttacking(1);
						if (this.worldObj.rand.nextInt(7) == 0 || this.worldObj.rand.nextInt(8) == 1) {
							this.attackEntityAsMob(e);
						}
					} else {
						this.getNavigator().tryMoveToEntityLiving(e, 1.2);
					}
				} else {
					this.setAttacking(0);
				}
			}

		}
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		if (!par1DamageSource.getDamageType().equals("cactus")) {
			ret = super.attackEntityFrom(par1DamageSource, par2);
		}

		return ret;
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
			} else if (par1EntityLiving instanceof Vortex) {
				return false;
			} else if (par1EntityLiving instanceof Rotator) {
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
			} else {
				if (par1EntityLiving instanceof EntityPlayer) {
					EntityPlayer p = (EntityPlayer)par1EntityLiving;
					if (p.capabilities.isCreativeMode) {
						return false;
					}
				}

				return true;
			}
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(16.0D, 3.0D, 16.0D));
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

	public final int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1) {
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	public boolean getCanSpawnHere() {
		int sc = 0;

		for (int k = -2; k <= 2; k++) {
			for (int j = -2; j <= 2; j++) {
				for (int i = 1; i < 4; i++) {
					Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null && s.equals("Crystal Urchin")) {
							this.was_spawnered = 1;
							return true;
						}
					}
				}
			}
		}

		for (int var10 = -1; var10 <= 1; ++var10) {
			for (int j = -1; j <= 1; j++) {
				Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + 1, (int)this.posZ + var10);
				if (bid == Blocks.air) {
					++sc;
				}
			}
		}

		if (sc < 6) {
			return false;
		} else if (!this.isValidLightLevel()) {
			return false;
		} else {
			long t = this.worldObj.getWorldTime();
			t %= 24000L;
			if (t < 13000L) {
				return false;
			} else {
				return true;
			}
		}
	}
}
