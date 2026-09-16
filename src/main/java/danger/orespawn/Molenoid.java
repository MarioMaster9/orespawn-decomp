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

public class Molenoid extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.35F;

	public Molenoid(World par1World) {
		super(par1World);
		this.setSize(3.9F, 2.6F);
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Molenoid_stats.attack);
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
		return OreSpawnMain.Molenoid_stats.health;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Molenoid_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected String getLivingSound() {
		return this.rand.nextInt(3) == 0 ? "orespawn:molenoid_living" : null;
	}

	protected String getHurtSound() {
		return "orespawn:molenoid_hit";
	}

	protected String getDeathSound() {
		return "orespawn:molenoid_death";
	}

	protected float getSoundVolume() {
		return 1.1F;
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
		this.dropItemRand(OreSpawnMain.MolenoidNose, 1);
		this.dropItemRand(Items.item_frame, 1);

		for (int var4 = 0; var4 < 10; ++var4) {
			this.dropItemRand(Items.gold_nugget, 1);
		}

		for (int var41 = 0; var41 < 6; ++var41) {
			this.dropItemRand(Items.beef, 1);
		}

	}

	public void initCreature() {
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return par1DamageSource.getDamageType().equals("inWall") ? false : super.attackEntityFrom(par1DamageSource, par2);
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		if (!super.attackEntityAsMob(par1Entity)) {
			return false;
		} else {
			if (par1Entity != null && par1Entity instanceof EntityLivingBase) {
				double ks = 0.8;
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

	protected void updateAITasks() {
		EntityLivingBase e = null;
		if (!this.isDead) {
			super.updateAITasks();
			if (this.worldObj.rand.nextInt(4) == 0) {
				e = this.findSomethingToAttack();
				if (e != null) {
					this.faceEntity(e, 10.0F, 10.0F);
					if (this.getDistanceSqToEntity(e) < (double)((6.0F + e.width / 2.0F) * (6.0F + e.width / 2.0F))) {
						this.setAttacking(1);
						if (!(this.getDistanceSqToEntity(e) < 16.0D) || this.worldObj.rand.nextInt(4) != 0 && this.worldObj.rand.nextInt(5) != 1) {
							if (OreSpawnMain.PlayNicely == 0) {
								int j = 1 + this.worldObj.rand.nextInt(4);

								for (int k = 0; k < j; k++) {
									double dx = e.posX;
									double dz = e.posZ;
									dx += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0D;
									dz += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0D;

									for (int i = 4; i > -3; --i) {
										if (this.worldObj.getBlock((int)dx, (int)e.posY + i + 1, (int)dz) == Blocks.air && this.worldObj.getBlock((int)dx, (int)e.posY + i, (int)dz) != Blocks.air) {
											this.worldObj.setBlock((int)dx, (int)e.posY + i + 1, (int)dz, OreSpawnMain.MyMoleDirtBlock);
											break;
										}
									}
								}
							}
						} else {
							this.attackEntityAsMob(e);
						}
					} else {
						this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
					}
				} else {
					this.setAttacking(0);
				}
			}

			if (!this.worldObj.isRemote) {
				if (this.worldObj.rand.nextInt(2) == 0) {
					double spd = 0.0D;
					spd = this.motionX * this.motionX + this.motionZ * this.motionZ;
					spd = Math.sqrt(spd);
					if (spd > (double)this.moveSpeed) {
						spd = (double)this.moveSpeed;
					}

					int odds = (int)((double)100.0F * spd / (double)this.moveSpeed);
					if (odds > 0 && this.worldObj.rand.nextInt(100) < odds && OreSpawnMain.PlayNicely == 0) {
						double dx = this.posX + 6.0D * Math.sin(Math.toRadians((double)this.rotationYawHead));
						double dz = this.posZ - 6.0D * Math.cos(Math.toRadians((double)this.rotationYawHead));
						dx += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 3.0D;
						dz += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 3.0D;

						for (int i = 4; i > -4; --i) {
							if (this.worldObj.getBlock((int)dx, (int)this.posY + i + 1, (int)dz) == Blocks.air && this.worldObj.getBlock((int)dx, (int)this.posY + i, (int)dz) != Blocks.air) {
								this.worldObj.setBlock((int)dx, (int)this.posY + i + 1, (int)dz, OreSpawnMain.MyMoleDirtBlock);
								break;
							}
						}
					}
				}

				double dx = this.posX - 3.0D * Math.sin(Math.toRadians((double)this.rotationYawHead));
				double dz = this.posZ + 3.0D * Math.cos(Math.toRadians((double)this.rotationYawHead));
				dx += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 3.0D;
				dz += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 3.0D;
				int dir = 1;
				if (e != null) {
					if ((int)e.posY > (int)this.posY) {
						dir = 2;
					}

					if ((int)e.posY < (int)this.posY) {
						dir = 0;
					}
				}

				if (OreSpawnMain.PlayNicely == 0) {
					for (int i = dir; i < dir + 3; i++) {
						Block bid = this.worldObj.getBlock((int)dx, (int)this.posY + i, (int)dz);
						if ((bid == Blocks.dirt || bid == Blocks.grass || bid == Blocks.gravel || bid == Blocks.sand || bid == Blocks.leaves) && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
							this.worldObj.setBlock((int)dx, (int)this.posY + i, (int)dz, Blocks.air);
						}

						if (bid == OreSpawnMain.MyMoleDirtBlock) {
							this.worldObj.setBlock((int)dx, (int)this.posY + i, (int)dz, Blocks.air);
						}
					}
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
		} else if (!this.MyCanSee(par1EntityLiving)) {
			return false;
		} else if (par1EntityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			return !p.capabilities.isCreativeMode;
		} else if (par1EntityLiving instanceof Molenoid) {
			return false;
		} else if (par1EntityLiving instanceof EntityMob) {
			return true;
		} else {
			return OreSpawnMain.OreSpawnUtils.isAttackableNonMob(par1EntityLiving);
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(12.0D, 6.0D, 12.0D));
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
						if (s != null && s.equals("Molenoid")) {
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
					for (int i = 1; i < 4; i++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + var10);
						if (bid != Blocks.air) {
							return false;
						}
					}
				}
			}

			Molenoid target = null;
			target = (Molenoid)this.worldObj.findNearestEntityWithinAABB(Molenoid.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D), this);
			if (target != null) {
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean MyCanSee(EntityLivingBase e) {
		double xzoff = 2.0D;
		int nblks = 10;
		double cx = this.posX - xzoff * Math.sin(Math.toRadians((double)this.rotationYaw));
		double cz = this.posZ + xzoff * Math.cos(Math.toRadians((double)this.rotationYaw));
		float startx = (float)cx;
		float starty = (float)(this.posY + 1.0D);
		float startz = (float)cz;
		float dx = (float)((e.posX - (double)startx) / 10.0D);
		float dy = (float)((e.posY + (double)(e.height / 2.0F) - (double)starty) / 10.0D);
		float dz = (float)((e.posZ - (double)startz) / 10.0D);
		if ((double)Math.abs(dx) > 1.0D) {
			dy /= Math.abs(dx);
			dz /= Math.abs(dx);
			nblks = (int)((float)nblks * Math.abs(dx));
			if (dx > 1.0F) {
				dx = 1.0F;
			}

			if (dx < -1.0F) {
				dx = -1.0F;
			}
		}

		if ((double)Math.abs(dy) > 1.0D) {
			dx /= Math.abs(dy);
			dz /= Math.abs(dy);
			nblks = (int)((float)nblks * Math.abs(dy));
			if (dy > 1.0F) {
				dy = 1.0F;
			}

			if (dy < -1.0F) {
				dy = -1.0F;
			}
		}

		if ((double)Math.abs(dz) > 1.0D) {
			dy /= Math.abs(dz);
			dx /= Math.abs(dz);
			nblks = (int)((float)nblks * Math.abs(dz));
			if (dz > 1.0F) {
				dz = 1.0F;
			}

			if (dz < -1.0F) {
				dz = -1.0F;
			}
		}

		for (int i = 0; i < nblks; i++) {
			startx += dx;
			starty += dy;
			startz += dz;
			Block bid = this.worldObj.getBlock((int)startx, (int)starty, (int)startz);
			if (bid != Blocks.air && bid != OreSpawnMain.MyMoleDirtBlock && bid != Blocks.dirt && bid != Blocks.grass && bid != Blocks.tallgrass && bid != Blocks.sand && bid != Blocks.gravel) {
				return false;
			}
		}

		return true;
	}
}
