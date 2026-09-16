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
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Nastysaurus extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.35F;
	private RenderInfo renderdata = new RenderInfo();
	private EntityLivingBase rt = null;

	public Nastysaurus(World par1World) {
		super(par1World);
		this.setSize(2.2F, 4.6F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 40;
		this.fireResistance = 100;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Nastysaurus_stats.attack);
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
		return !this.isNoDespawnRequired();
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.Nastysaurus_stats.health;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Nastysaurus_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected String getLivingSound() {
		return this.rand.nextInt(4) == 0 ? "orespawn:alo_living" : null;
	}

	protected String getHurtSound() {
		return "orespawn:alo_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:alo_death";
	}

	protected float getSoundVolume() {
		return 1.5F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected Item getDropItem() {
		return Items.beef;
	}

	private void dropItemRand(Item index, int par1) {
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 3.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2) {
		for (int var4 = 0; var4 < 10; ++var4) {
			this.dropItemRand(Items.iron_ingot, 1);
		}

		for (int var41 = 0; var41 < 10; ++var41) {
			this.dropItemRand(Items.rotten_flesh, 1);
		}

		for (int var5 = 0; var5 < 10; ++var5) {
			this.dropItemRand(Items.leather, 1);
		}

		for (int var6 = 0; var6 < 10; ++var6) {
			this.dropItemRand(Items.string, 1);
		}

	}

	public void initCreature() {
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		if (!super.attackEntityAsMob(par1Entity)) {
			return false;
		} else {
			if (par1Entity != null && par1Entity instanceof EntityLivingBase) {
				double ks = 1.2;
				double inair = 0.1;
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
		if (!par1DamageSource.getDamageType().equals("cactus")) {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			Entity e = par1DamageSource.getEntity();
			if (e != null && e instanceof EntityLivingBase) {
				this.rt = (EntityLivingBase)e;
			}
		}

		return ret;
	}

	protected void updateAITasks() {
		if (!this.isDead) {
			super.updateAITasks();
			if (this.worldObj.rand.nextInt(5) == 0) {
				EntityLivingBase e = null;
				e = this.rt;
				if (OreSpawnMain.PlayNicely != 0) {
					e = null;
				}

				if (e != null) {
					if (e.isDead || this.worldObj.rand.nextInt(250) == 1) {
						e = null;
						this.rt = null;
					}

					if (e != null && !this.getEntitySenses().canSee(e)) {
						e = null;
					}
				}

				if (e == null) {
					e = this.findSomethingToAttack();
				}

				if (e != null) {
					this.faceEntity(e, 10.0F, 10.0F);
					if (this.getDistanceSqToEntity(e) < (double)((4.5F + e.width / 2.0F) * (4.5F + e.width / 2.0F))) {
						this.setAttacking(1);
						if (this.worldObj.rand.nextInt(4) == 0 || this.worldObj.rand.nextInt(5) == 1) {
							this.attackEntityAsMob(e);
						}
					} else {
						this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
					}
				} else {
					this.setAttacking(0);
				}
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
		} else {
			if (OreSpawnMain.OreSpawnUtils.isIgnoreable(par1EntityLiving)) {
				return false;
			} else if (par1EntityLiving instanceof Nastysaurus) {
				return false;
			} else if (par1EntityLiving instanceof Cryolophosaurus) {
				return false;
			} else if (par1EntityLiving instanceof VelocityRaptor) {
				return false;
			} else if (!this.getEntitySenses().canSee(par1EntityLiving)) {
				return false;
			} else if (par1EntityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)par1EntityLiving;
				return !p.capabilities.isCreativeMode;
			} else {
				return true;
			}
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(32.0D, 8.0D, 32.0D));
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

	public int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public void setAttacking(int par1) {
		this.dataWatcher.updateObject(20, (byte)par1);
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
						if (s != null && s.equals("Nastysaurus")) {
							return true;
						}
					}
				}
			}
		}

		if (!this.isValidLightLevel()) {
			return false;
		} else if (this.posY < 50.0D) {
			return false;
		} else if (this.worldObj.isDaytime()) {
			return false;
		} else {
			for (int var10 = -1; var10 < 1; ++var10) {
				for (int j = -1; j < 1; j++) {
					for (int i = 1; i < 6; i++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + var10);
						if (bid != Blocks.air) {
							return false;
						}
					}
				}
			}

			Nastysaurus target = null;
			target = (Nastysaurus)this.worldObj.findNearestEntityWithinAABB(Nastysaurus.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D), this);
			if (target != null) {
				return false;
			} else {
				return true;
			}
		}
	}
}
