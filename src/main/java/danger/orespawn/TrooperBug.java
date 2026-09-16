package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class TrooperBug extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private int force_sync = 50;
	private int hurt_timer = 0;
	private float moveSpeed = 0.4F;

	public TrooperBug(World par1World) {
		super(par1World);
		this.setSize(3.0F, 3.5F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 150;
		this.fireResistance = 100;
		this.isImmuneToFire = false;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveThroughVillage(this, (double)0.9F, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 14, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
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
		this.force_sync = 50;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.TrooperBug_stats.attack);
	}

	protected boolean canDespawn() {
		return !this.isNoDespawnRequired();
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (this.isAirBorne) {
			this.getNavigator().setPath((PathEntity)null, 0.0D);
		}

	}

	public int mygetMaxHealth() {
		return OreSpawnMain.TrooperBug_stats.health;
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
		return OreSpawnMain.TrooperBug_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected void jump() {
		++this.motionY;
		++this.posY;
		float f = 0.2F + Math.abs(this.worldObj.rand.nextFloat() * 0.45F);
		this.motionX -= (double)f * Math.sin(Math.toRadians((double)this.rotationYawHead));
		this.motionZ += (double)f * Math.cos(Math.toRadians((double)this.rotationYawHead));
		this.isAirBorne = true;
	}

	protected void jumpAtEntity(EntityLivingBase e) {
		++this.motionY;
		++this.posY;
		float f = 0.3F + Math.abs(this.worldObj.rand.nextFloat() * 0.25F);
		float d = (float)Math.atan2(e.posX - this.posX, e.posZ - this.posZ);
		this.motionX += (double)f * Math.sin((double)d);
		this.motionZ += (double)f * Math.cos((double)d);
		this.isAirBorne = true;
	}

	public int getTrooperBugHealth() {
		return (int)this.getHealth();
	}

	protected String getLivingSound() {
		return this.rand.nextInt(4) == 0 ? "orespawn:clatter" : null;
	}

	protected String getHurtSound() {
		return "orespawn:crunch";
	}

	protected String getDeathSound() {
		return "orespawn:emperorscorpion_death";
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

	private ItemStack dropItemRand(Item index, int par1) {
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), is);
		if (var3 != null) {
			this.worldObj.spawnEntityInWorld(var3);
		}

		return is;
	}

	protected void dropFewItems(boolean par1, int par2) {
		this.dropItemRand(OreSpawnMain.MyJumpyBugScale, 1);
		this.dropItemRand(Items.item_frame, 1);
		int i = 2 + this.worldObj.rand.nextInt(5);

		for (int var4 = 0; var4 < i; ++var4) {
			this.dropItemRand(OreSpawnMain.MyAmethyst, 1);
		}

		i = 1 + this.worldObj.rand.nextInt(5);

		for (int var7 = 0; var7 < i; ++var7) {
			int var3 = this.worldObj.rand.nextInt(14);
			switch (var3) {
				case 0:
				case 1:
				case 12:
				default:
					break;
				case 2:
					ItemStack is = this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.MyBlockAmethystBlock), 1);
					break;
				case 3:
					ItemStack var16 = this.dropItemRand(OreSpawnMain.MyAmethystSword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						var16.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var16.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var16.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var16.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						var16.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var16.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var16.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 4:
					ItemStack var15 = this.dropItemRand(OreSpawnMain.MyAmethystShovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						var15.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var15.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 5:
					ItemStack var14 = this.dropItemRand(OreSpawnMain.MyAmethystPickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						var14.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var14.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var14.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 6:
					ItemStack var13 = this.dropItemRand(OreSpawnMain.MyAmethystAxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						var13.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var13.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 7:
					ItemStack var12 = this.dropItemRand(OreSpawnMain.MyAmethystHoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						var12.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var12.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 8:
					ItemStack var11 = this.dropItemRand(OreSpawnMain.AmethystHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						var11.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var11.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var11.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var11.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						var11.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var11.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(2));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var11.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 9:
					ItemStack var10 = this.dropItemRand(OreSpawnMain.AmethystBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						var10.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var10.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var10.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var10.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						var10.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 10:
					ItemStack var9 = this.dropItemRand(OreSpawnMain.AmethystLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						var9.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var9.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var9.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						var9.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						var9.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 11:
					is = this.dropItemRand(OreSpawnMain.AmethystBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
			}
		}

	}

	public void initCreature() {
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		double ks = 1.8;
		double inair = 0.2;
		int var2 = 6;
		if (!super.attackEntityAsMob(par1Entity)) {
			return false;
		} else {
			if (par1Entity != null && par1Entity instanceof EntityLivingBase) {
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
		if (this.hurt_timer > 0) {
			return false;
		} else {
			if (!par1DamageSource.getDamageType().equals("cactus") && !par1DamageSource.getDamageType().equals("fall")) {
				ret = super.attackEntityFrom(par1DamageSource, par2);
				this.hurt_timer = 20;
				Entity e = par1DamageSource.getEntity();
				if (e != null && e instanceof EntityLiving) {
					this.setAttackTarget((EntityLiving)e);
					this.setTarget(e);
					this.getNavigator().tryMoveToEntityLiving((EntityLiving)e, 1.2);
					ret = true;
				}
			}

			return ret;
		}
	}

	protected void updateAITasks() {
		EntityLivingBase e = null;
		if (!this.isDead) {
			super.updateAITasks();
			if (this.hurt_timer > 0) {
				--this.hurt_timer;
			}

			if (this.worldObj.rand.nextInt(5) == 0) {
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
					if (this.worldObj.rand.nextInt(10) == 1 && !this.isAirBorne) {
						this.jumpAtEntity(e);
					} else if (this.getDistanceSqToEntity(e) < (double)((5.0F + e.width / 2.0F) * (5.0F + e.width / 2.0F))) {
						this.setAttacking(1);
						if (this.worldObj.rand.nextInt(6) == 0 || this.worldObj.rand.nextInt(7) == 1) {
							this.attackEntityAsMob(e);
							if (!this.worldObj.isRemote) {
								if (this.worldObj.rand.nextInt(3) == 1) {
									this.worldObj.playSoundAtEntity(e, "orespawn:scorpion_attack", 1.4F, 1.0F);
								} else {
									this.worldObj.playSoundAtEntity(e, "orespawn:clatter", 1.0F, 1.0F);
								}
							}
						}
					} else if (!this.isAirBorne) {
						this.getNavigator().tryMoveToEntityLiving(e, 1.2);
					}

					if (this.worldObj.rand.nextInt(30) == 1) {
						EntityCreature newent = (EntityCreature)spawnCreature(this.worldObj, "Spit Bug", (this.posX + e.posX) / 2.0D + (double)this.worldObj.rand.nextInt(5) - (double)this.worldObj.rand.nextInt(5), (this.posY + e.posY) / 2.0D + 1.01, (this.posZ + e.posZ) / 2.0D + (double)this.worldObj.rand.nextInt(5) - (double)this.worldObj.rand.nextInt(5));
					}
				} else {
					this.setAttacking(0);
				}
			}

			if (this.worldObj.rand.nextInt(150) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
				this.heal(1.0F);
			}

		}
	}

	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
		Entity var8 = null;
		var8 = EntityList.createEntityByName(par1, par0World);
		if (var8 != null) {
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			par0World.spawnEntityInWorld(var8);
			((EntityLiving)var8).playLivingSound();
		}

		return var8;
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
			} else if (par1EntityLiving instanceof Hydrolisc) {
				return false;
			} else if (par1EntityLiving instanceof EnderReaper) {
				return false;
			} else if (par1EntityLiving instanceof EnderKnight) {
				return false;
			} else if (par1EntityLiving instanceof EntityEnderman) {
				return false;
			} else if (par1EntityLiving instanceof EntityCreeper) {
				return false;
			} else if (par1EntityLiving instanceof TrooperBug) {
				return false;
			} else if (par1EntityLiving instanceof SpitBug) {
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
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(12.0D, 7.0D, 12.0D));
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
						if (s != null && s.equals("Jumpy Bug")) {
							return true;
						}
					}
				}
			}
		}

		if (!this.isValidLightLevel()) {
			return false;
		} else if (this.worldObj.isDaytime() && this.worldObj.rand.nextInt(20) > 1) {
			return false;
		} else {
			for (int var10 = -2; var10 < 2; ++var10) {
				for (int j = -2; j < 2; j++) {
					for (int i = 1; i < 5; i++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + var10);
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
