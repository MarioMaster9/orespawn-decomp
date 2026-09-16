package danger.orespawn;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class WormLarge extends EntityMob {
	private int wormsSpawned = 0;

	public WormLarge(World par1World) {
		super(par1World);
		this.setSize(1.55F, 2.5F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 2050;
		this.noClip = true;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.2F);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.WormLarge_stats.attack);
	}

	protected void entityInit() {
		super.entityInit();
	}

	protected boolean canDespawn() {
		return false;
	}

	protected float getSoundVolume() {
		return 0.5F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "orespawn:big_splat";
	}

	protected String getDeathSound() {
		return "orespawn:alo_death";
	}

	public boolean canBePushed() {
		return true;
	}

	protected void collideWithEntity(Entity par1Entity) {
	}

	protected void collideWithNearbyEntities() {
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.WormLarge_stats.health;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.WormLarge_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void pointAtEntity(EntityLivingBase e) {
		double d1 = e.posX - this.posX;
		double d2 = e.posZ - this.posZ;
		float d = (float)Math.atan2(d2, d1);
		float f2 = (float)((double)d * 180.0D / Math.PI) - 90.0F;
		this.rotationYaw = this.rotationYawHead = f2;
	}

	public void onLivingUpdate() {
		EntityPlayer target = null;
		WormMedium worms = null;
		EntityCreature newent = null;
		super.onLivingUpdate();
		worms = (WormMedium)this.worldObj.findNearestEntityWithinAABB(WormMedium.class, this.boundingBox.expand((double)8.0F, 8.0D, 8.0D), this);
		if (worms == null) {
			target = (EntityPlayer)this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand((double)8.0F, 8.0D, 8.0D), this);
		}

		if ((worms != null || target == null) && OreSpawnMain.PlayNicely == 0) {
			this.noClip = true;
			Block bid = this.worldObj.getBlock((int)this.posX, (int)(this.posY + (double)3.5F), (int)this.posZ);
			if (bid == Blocks.tallgrass) {
				bid = Blocks.air;
			}

			if (bid != Blocks.air) {
				this.motionY += 0.1D;
				this.posY += (double)0.05F;
				if (bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.stone) {
					this.setDead();
				}
			}
		} else {
			if (target != null) {
				this.pointAtEntity(target);
			}

			Block bid = this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ);
			if (bid == Blocks.tallgrass) {
				bid = Blocks.air;
			}

			if (bid != Blocks.air) {
				this.motionY += 0.25D;
				this.posY += 0.1D;
			} else {
				this.noClip = false;
			}
		}

		if (this.noClip) {
			this.motionY -= 0.01;
			this.motionX = 0.0D;
			this.motionZ = 0.0D;
			this.moveForward = 0.0F;
		}

		if (!this.worldObj.isRemote) {
			if (this.wormsSpawned == 0) {
				this.wormsSpawned = 1;

				for (int i = 0; i < 20; i++) {
					newent = (EntityCreature)spawnCreature(this.worldObj, "Small Worm", this.posX + (double)this.worldObj.rand.nextInt(6) - (double)this.worldObj.rand.nextInt(6), this.posY, this.posZ + (double)this.worldObj.rand.nextInt(6) - (double)this.worldObj.rand.nextInt(6));
					newent = (EntityCreature)spawnCreature(this.worldObj, "Medium Worm", this.posX + (double)this.worldObj.rand.nextInt(5) - (double)this.worldObj.rand.nextInt(5), this.posY, this.posZ + (double)this.worldObj.rand.nextInt(5) - (double)this.worldObj.rand.nextInt(5));
				}

			}
		}
	}

	public void onUpdate() {
		if (this.isNoDespawnRequired()) {
			this.noClip = false;
		}

		super.onUpdate();
		this.motionY *= 0.85;
	}

	protected void updateAITasks() {
		int bid = 0;
		EntityPlayer target = null;
		WormMedium worms = null;
		if (!this.isDead) {
			if (!this.noClip) {
				super.updateAITasks();
			}

			if (OreSpawnMain.PlayNicely == 0) {
				worms = (WormMedium)this.worldObj.findNearestEntityWithinAABB(WormMedium.class, this.boundingBox.expand((double)8.0F, 8.0D, 8.0D), this);
				if (worms == null) {
					target = (EntityPlayer)this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand((double)8.0F, 6.0D, 8.0D), this);
					if (target != null && target.capabilities.isCreativeMode) {
						target = null;
					}

					if (target != null) {
						this.pointAtEntity(target);
						this.getNavigator().tryMoveToXYZ(target.posX, target.posY, target.posZ, 1.0D);
						if (this.worldObj.rand.nextInt(10) == 1 && (double)this.getDistanceToEntity(target) < 3.0D) {
							super.attackEntityAsMob(target);
							if (this.worldObj.rand.nextInt(4) == 1) {
								ItemStack boots = target.getEquipmentInSlot(4);
								if (boots != null) {
									target.setCurrentItemOrArmor(4, (ItemStack)null);
									bid = boots.getMaxDamage() - boots.getItemDamage();
									if (bid > 10) {
										bid /= 10;
									} else {
										bid = 1;
									}

									boots.damageItem(bid, this);
									EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), this.posY + 3.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), boots);
									this.worldObj.spawnEntityInWorld(var3);
								} else {
									boots = target.getEquipmentInSlot(3);
									if (boots != null) {
										target.setCurrentItemOrArmor(3, (ItemStack)null);
										bid = boots.getMaxDamage() - boots.getItemDamage();
										if (bid > 10) {
											bid /= 10;
										} else {
											bid = 1;
										}

										boots.damageItem(bid, this);
										EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), this.posY + 3.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), boots);
										this.worldObj.spawnEntityInWorld(var3);
									}
								}
							}

							if (this.worldObj.rand.nextInt(4) == 1) {
								ItemStack boots = target.getEquipmentInSlot(0);
								if (boots != null) {
									target.setCurrentItemOrArmor(0, (ItemStack)null);
									bid = boots.getMaxDamage() - boots.getItemDamage();
									if (bid > 10) {
										bid /= 10;
									} else {
										bid = 1;
									}

									boots.damageItem(bid, this);
									EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), this.posY + 3.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), boots);
									this.worldObj.spawnEntityInWorld(var3);
								}
							}
						}
					}

				}
			}
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	protected void fall(float par1) {
		if (!this.noClip) {
			super.fall(par1);
		}

	}

	protected void updateFallState(double par1, boolean par3) {
		if (!this.noClip) {
			super.updateFallState(par1, par3);
		}

	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
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
						if (s != null && s.equals("Large Worm")) {
							this.wormsSpawned = 1;
							return true;
						}
					}
				}
			}
		}

		if (this.posY < 50.0D) {
			return false;
		} else {
			WormLarge target = null;
			target = (WormLarge)this.worldObj.findNearestEntityWithinAABB(WormLarge.class, this.boundingBox.expand(32.0D, 8.0D, 32.0D), this);
			if (target != null) {
				return false;
			} else {
				for (int i = -6; i <= 6; i++) {
					for (int j = -6; j <= 6; j++) {
						for (int var13 = -2; var13 >= -8; --var13) {
							Block bid = this.worldObj.getBlock((int)this.posX + i, (int)this.posY + var13, (int)this.posZ + j);
							if (bid == Blocks.air) {
								return false;
							}
						}
					}
				}

				for (int var10 = -6; var10 <= 6; ++var10) {
					for (int j = -6; j <= 6; j++) {
						for (int var14 = 2; var14 <= 8; ++var14) {
							Block bid = this.worldObj.getBlock((int)this.posX + var10, (int)this.posY + var14, (int)this.posZ + j);
							if (bid != Blocks.air) {
								return false;
							}
						}
					}
				}

				return true;
			}
		}
	}

	public void initCreature() {
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		if (par1DamageSource.getDamageType().equals("inWall")) {
			return ret;
		} else {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			return ret;
		}
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("wormsSpawned", this.wormsSpawned);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.wormsSpawned = par1NBTTagCompound.getInteger("wormsSpawned");
	}

	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
		Entity var8 = null;
		var8 = EntityList.createEntityByName(par1, par0World);
		if (var8 != null) {
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			par0World.spawnEntityInWorld(var8);
		}

		return var8;
	}

	protected Item getDropItem() {
		return Items.rotten_flesh;
	}

	private void dropItemRand(Item index, int par1) {
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 2.5D + (double)this.worldObj.rand.nextInt(4), this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2) {
		this.dropItemRand(OreSpawnMain.WormTooth, 1);
		this.dropItemRand(Items.item_frame, 1);

		for (int var4 = 0; var4 < 6; ++var4) {
			this.dropItemRand(Items.rotten_flesh, 1);
		}

		for (int var41 = 0; var41 < 6; ++var41) {
			this.dropItemRand(Items.leather, 1);
		}

		for (int var5 = 0; var5 < 8; ++var5) {
			this.dropItemRand(Item.getItemFromBlock(Blocks.dirt), 1);
		}

		for (int var6 = 0; var6 < 16; ++var6) {
			this.dropItemRand(Items.gold_nugget, 1);
		}

		for (int var7 = 0; var7 < 5; ++var7) {
			this.dropItemRand(Items.diamond, 1);
		}

		for (int var8 = 0; var8 < 4; ++var8) {
			this.dropItemRand(OreSpawnMain.UraniumNugget, 1);
		}

		for (int var9 = 0; var9 < 4; ++var9) {
			this.dropItemRand(OreSpawnMain.TitaniumNugget, 1);
		}

	}
}
