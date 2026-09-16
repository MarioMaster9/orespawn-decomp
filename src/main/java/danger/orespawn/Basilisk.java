package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class Basilisk extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private int hurt_timer = 0;
	private float moveSpeed = 0.4F;

	public Basilisk(World par1World) {
		super(par1World);
		this.setSize(1.6F, 3.5F);
		this.experienceValue = 150;
		this.fireResistance = 2000;
		this.isImmuneToFire = true;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 20, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Basilisk_stats.attack);
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
		return OreSpawnMain.Basilisk_stats.health;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Basilisk_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	protected void jump() {
		this.motionY += 0.25D;
		super.jump();
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!this.isDead) {
			if (this.rand.nextInt(200) == 0) {
				this.heal(1.0F);
			}

		}
	}

	public int getBasiliskHealth() {
		return (int)this.getHealth();
	}

	protected String getLivingSound() {
		return this.rand.nextInt(2) == 0 ? "orespawn:basilisk_living" : null;
	}

	protected String getHurtSound() {
		return "orespawn:alo_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:emperorscorpion_death";
	}

	protected float getSoundVolume() {
		return 1.0F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected Item getDropItem() {
		return Items.beef;
	}

	private ItemStack dropItemRand(Item index, int par1) {
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), is);
		if (var3 != null) {
			this.worldObj.spawnEntityInWorld(var3);
		}

		return is;
	}

	protected void dropFewItems(boolean par1, int par2) {
		ItemStack is = null;
		this.dropItemRand(OreSpawnMain.MyBasiliskScale, 1);
		this.dropItemRand(Items.item_frame, 1);
		int i = 12 + this.worldObj.rand.nextInt(6);

		for (int var4 = 0; var4 < i; ++var4) {
			this.dropItemRand(Items.emerald, 1);
		}

		i = 8 + this.worldObj.rand.nextInt(5);

		for (int var7 = 0; var7 < i; ++var7) {
			this.dropItemRand(Items.chicken, 1);
		}

		i = 3 + this.worldObj.rand.nextInt(5);

		for (int var8 = 0; var8 < i; ++var8) {
			int var3 = this.worldObj.rand.nextInt(15);
			switch (var3) {
				case 1:
					is = this.dropItemRand(Items.emerald, 1);
					break;
				case 2:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.emerald_block), 1);
					break;
				case 3:
					is = this.dropItemRand(OreSpawnMain.MyEmeraldSword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 4:
					is = this.dropItemRand(OreSpawnMain.MyEmeraldShovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 5:
					is = this.dropItemRand(OreSpawnMain.MyEmeraldPickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 6:
					is = this.dropItemRand(OreSpawnMain.MyEmeraldAxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 7:
					is = this.dropItemRand(OreSpawnMain.MyEmeraldHoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 8:
					is = this.dropItemRand(OreSpawnMain.EmeraldHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(2));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 9:
					is = this.dropItemRand(OreSpawnMain.EmeraldBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 10:
					is = this.dropItemRand(OreSpawnMain.EmeraldLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 11:
					is = this.dropItemRand(OreSpawnMain.EmeraldBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
			}
		}

	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		if (!super.attackEntityAsMob(par1Entity)) {
			return false;
		} else {
			if (par1Entity != null && par1Entity instanceof EntityLivingBase) {
				int var2 = 8;
				if (this.worldObj.difficultySetting == EnumDifficulty.EASY) {
					var2 = 10;
				}

				if (this.worldObj.difficultySetting == EnumDifficulty.NORMAL) {
					var2 = 12;
				} else if (this.worldObj.difficultySetting == EnumDifficulty.HARD) {
					var2 = 14;
				}

				if (this.worldObj.rand.nextInt(3) == 0) {
					((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
				}

				double ks = 1.5D;
				double inair = 0.15;
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
		if (this.hurt_timer > 0) {
			return false;
		} else {
			this.hurt_timer = 30;
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}

	protected void updateAITasks() {
		if (!this.isDead) {
			super.updateAITasks();
			if (this.hurt_timer > 0) {
				--this.hurt_timer;
			}

			if (this.worldObj.rand.nextInt(5) == 0) {
				EntityLivingBase e = this.findSomethingToAttack();
				if (e != null) {
					this.faceEntity(e, 10.0F, 10.0F);
					if (this.getDistanceSqToEntity(e) < (double)((6.0F + e.width / 2.0F) * (6.0F + e.width / 2.0F))) {
						this.setAttacking(1);
						if (this.worldObj.rand.nextInt(3) == 0 || this.worldObj.rand.nextInt(4) == 1) {
							this.attackEntityAsMob(e);
						}
					} else {
						this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
					}

					if (e instanceof EntityLivingBase) {
						e.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 5));
					}
				} else {
					this.setAttacking(0);
				}
			}

			if (this.worldObj.rand.nextInt(75) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
				this.heal(1.0F);
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
			} else if (par1EntityLiving instanceof Basilisk) {
				return false;
			} else if (par1EntityLiving instanceof LeafMonster) {
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
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand((double)24.0F, 7.0D, (double)24.0F));
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
						if (s != null && s.equals("Basilisk")) {
							return true;
						}
					}
				}
			}
		}

		if (!this.isValidLightLevel()) {
			return false;
		} else if (this.worldObj.isDaytime()) {
			return false;
		} else {
			for (int var10 = -1; var10 < 2; ++var10) {
				for (int j = -1; j < 2; j++) {
					for (int i = 1; i < 5; i++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + var10);
						if (bid != Blocks.air) {
							return false;
						}
					}
				}
			}

			Basilisk target = null;
			target = (Basilisk)this.worldObj.findNearestEntityWithinAABB(Basilisk.class, this.boundingBox.expand(20.0D, 6.0D, 20.0D), this);
			if (target != null) {
				return false;
			} else {
				return true;
			}
		}
	}
}
