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

public class TRex extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.38F;
	private EntityLivingBase rt = null;

	public TRex(World par1World) {
		super(par1World);
		this.setSize(2.0F, 4.2F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 150;
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.TRex_stats.attack);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
	}

	protected boolean canDespawn() {
		return !this.isNoDespawnRequired();
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.TRex_stats.health;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.TRex_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected String getLivingSound() {
		return this.rand.nextInt(4) == 0 ? "orespawn:trex_living" : null;
	}

	protected String getHurtSound() {
		return "orespawn:alo_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:trex_death";
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
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2) {
		this.dropItemRand(OreSpawnMain.TRexTooth, 1);
		this.dropItemRand(Items.item_frame, 1);

		for (int var4 = 0; var4 < 7; ++var4) {
			this.dropItemRand(Items.beef, 1);
		}

		int var5 = 2 + this.worldObj.rand.nextInt(4);

		for (int i = 0; i < var5; i++) {
			this.dropItemRand(OreSpawnMain.UraniumNugget, 1);
			this.dropItemRand(OreSpawnMain.TitaniumNugget, 1);
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
			if (this.worldObj.rand.nextInt(5) == 1) {
				EntityLivingBase e = null;
				e = this.rt;
				if (OreSpawnMain.PlayNicely != 0) {
					e = null;
				}

				if (e != null) {
					if (e.isDead || this.worldObj.rand.nextInt(200) == 1) {
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
					if (this.getDistanceSqToEntity(e) < (double)((4.0F + e.width / 2.0F) * (4.0F + e.width / 2.0F))) {
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
			} else if (!this.getEntitySenses().canSee(par1EntityLiving)) {
				return false;
			} else if (par1EntityLiving instanceof TRex) {
				return false;
			} else if (par1EntityLiving instanceof Cryolophosaurus) {
				return false;
			} else if (par1EntityLiving instanceof VelocityRaptor) {
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
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0D, 6.0D, 20.0D));
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
		for (int k = -3; k < 3; k++) {
			for (int j = -3; j < 3; j++) {
				for (int i = 0; i < 5; i++) {
					Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null && s.equals("T. Rex")) {
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
			for (int var10 = -1; var10 <= 1; ++var10) {
				for (int j = -1; j <= 1; j++) {
					for (int i = 1; i < 6; i++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + var10);
						if (bid != Blocks.air) {
							return false;
						}
					}
				}
			}

			TRex target = null;
			target = (TRex)this.worldObj.findNearestEntityWithinAABB(TRex.class, this.boundingBox.expand((double)24.0F, 12.0D, (double)24.0F), this);
			if (target != null) {
				return false;
			} else {
				return true;
			}
		}
	}
}
