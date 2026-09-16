package danger.orespawn;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class WormMedium extends EntityMob {
	public int upcount = 0;
	public int downcount = 0;

	public WormMedium(World par1World) {
		super(par1World);
		this.setSize(0.5F, 2.0F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 0;
		this.noClip = true;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.WormMedium_stats.attack);
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
		return 1.5F;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "orespawn:little_splat";
	}

	protected String getDeathSound() {
		return "orespawn:big_splat";
	}

	public boolean canBePushed() {
		return true;
	}

	protected void collideWithEntity(Entity par1Entity) {
	}

	protected void collideWithNearbyEntities() {
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.WormMedium_stats.health;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		Block bid = Blocks.air;
		EntityPlayer target = null;
		WormSmall worms = null;
		super.onLivingUpdate();
		if (!this.worldObj.isRemote) {
			worms = (WormSmall)this.worldObj.findNearestEntityWithinAABB(WormSmall.class, this.boundingBox.expand((double)8.0F, 8.0D, 8.0D), this);
			if (worms == null) {
				target = (EntityPlayer)this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand((double)8.0F, 8.0D, 8.0D), this);
			}

			if ((worms != null || target == null) && OreSpawnMain.PlayNicely == 0) {
				this.upcount = this.worldObj.rand.nextInt(50);
				this.downcount = 0;
				bid = this.worldObj.getBlock((int)this.posX, (int)this.posY + 3, (int)this.posZ);
				if (bid == Blocks.tallgrass) {
					bid = Blocks.air;
				}

				if (bid != Blocks.air) {
					if (bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.stone) {
						this.setDead();
					}

					this.motionY += 0.1D;
					this.posY += (double)0.05F;
				}
			} else if (this.upcount > 0) {
				--this.upcount;
				if (this.upcount == 0) {
					this.downcount = 100 + this.worldObj.rand.nextInt(150);
				}

				if (target != null) {
					this.pointAtEntity(target);
				}

				bid = this.worldObj.getBlock((int)this.posX, (int)(this.posY + 0.25D), (int)this.posZ);
				if (bid == Blocks.tallgrass) {
					bid = Blocks.air;
				}

				if (bid != Blocks.air) {
					if (bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.stone) {
						this.setDead();
					}

					this.motionY += (double)0.2F;
					this.posY += 0.1D;
				}
			} else {
				if (this.downcount > 0) {
					--this.downcount;
				} else {
					this.upcount = 25 + this.worldObj.rand.nextInt(75);
				}

				bid = this.worldObj.getBlock((int)this.posX, (int)this.posY + 3, (int)this.posZ);
				if (bid == Blocks.tallgrass) {
					bid = Blocks.air;
				}

				if (bid != Blocks.air) {
					if (bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.stone) {
						this.setDead();
					}

					this.motionY += 0.1D;
					this.posY += (double)0.05F;
				}
			}

			this.motionY -= 0.01;
			this.motionX = 0.0D;
			this.motionZ = 0.0D;
			this.moveForward = 0.0F;
		}
	}

	public void onUpdate() {
		if (this.isNoDespawnRequired()) {
			this.noClip = false;
		}

		super.onUpdate();
		this.motionY *= 0.65;
	}

	public void pointAtEntity(EntityLivingBase e) {
		double d1 = e.posX - this.posX;
		double d2 = e.posZ - this.posZ;
		float d = (float)Math.atan2(d2, d1);
		float f2 = (float)((double)d * 180.0D / Math.PI) - 90.0F;
		this.rotationYaw = this.rotationYawHead = f2;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.WormMedium_stats.defense;
	}

	protected void updateAITasks() {
		int bid = 0;
		EntityPlayer target = null;
		WormSmall worms = null;
		if (!this.isDead) {
			super.updateAITasks();
			if (OreSpawnMain.PlayNicely == 0) {
				worms = (WormSmall)this.worldObj.findNearestEntityWithinAABB(WormSmall.class, this.boundingBox.expand((double)8.0F, 8.0D, 8.0D), this);
				if (worms == null) {
					target = (EntityPlayer)this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(2.25D, 8.0D, 2.25D), this);
					if (target != null && target.capabilities.isCreativeMode) {
						target = null;
					}

					if (target != null) {
						this.pointAtEntity(target);
						if (this.upcount > 0 && this.worldObj.rand.nextInt(15) == 1 && !target.capabilities.isCreativeMode) {
							super.attackEntityAsMob(target);
							if (this.worldObj.rand.nextInt(6) == 1) {
								ItemStack boots = target.getEquipmentInSlot(1);
								if (boots != null) {
									target.setCurrentItemOrArmor(1, (ItemStack)null);
									bid = boots.getMaxDamage() - boots.getItemDamage();
									if (bid > 15) {
										bid /= 15;
									} else {
										bid = 1;
									}

									boots.damageItem(bid, this);
									EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), this.posY + 3.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), boots);
									this.worldObj.spawnEntityInWorld(var3);
								} else {
									boots = target.getEquipmentInSlot(2);
									if (boots != null) {
										target.setCurrentItemOrArmor(2, (ItemStack)null);
										bid = boots.getMaxDamage() - boots.getItemDamage();
										if (bid > 15) {
											bid /= 15;
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

	public boolean getCanSpawnHere() {
		return !this.worldObj.isDaytime();
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

	protected Item getDropItem() {
		return Items.rotten_flesh;
	}

	private void dropItemRand(Item index, int par1) {
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(3) - (double)OreSpawnMain.OreSpawnRand.nextInt(3), this.posY + 2.5D + (double)this.worldObj.rand.nextInt(3), this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(3) - (double)OreSpawnMain.OreSpawnRand.nextInt(3), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2) {
		for (int var4 = 0; var4 < 2; ++var4) {
			this.dropItemRand(Items.rotten_flesh, 1);
		}

		for (int var41 = 0; var41 < 2; ++var41) {
			this.dropItemRand(Items.leather, 1);
		}

	}
}
