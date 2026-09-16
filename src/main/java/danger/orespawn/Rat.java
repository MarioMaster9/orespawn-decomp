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
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Rat extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.25F;
	private String myowner = null;

	public Rat(World par1World) {
		super(par1World);
		this.setSize(0.25F, 0.5F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 5;
		this.fireResistance = 10;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, (double)1.35F));
		this.tasks.addTask(2, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(3, new MyEntityAIWanderALot(this, 10, 1.0D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Rat_stats.attack);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) {
			return false;
		} else {
			return this.myowner == null;
		}
	}

	public final int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1) {
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.Rat_stats.health;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Rat_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	protected void jump() {
		super.jump();
		this.motionY += 0.25D;
		this.posY += 0.25D;
	}

	protected String getLivingSound() {
		return "orespawn:ratlive";
	}

	protected String getHurtSound() {
		return "orespawn:rathit";
	}

	protected String getDeathSound() {
		return "orespawn:ratdead";
	}

	protected float getSoundVolume() {
		return 0.45F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected Item getDropItem() {
		return Items.rotten_flesh;
	}

	public void initCreature() {
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	protected void updateAITasks() {
		if (!this.isDead) {
			super.updateAITasks();
			if (this.worldObj.rand.nextInt(200) == 1) {
				this.setRevengeTarget((EntityLivingBase)null);
			}

			if (this.worldObj.rand.nextInt(5) == 1) {
				EntityLivingBase e = this.findSomethingToAttack();
				if (e != null) {
					this.setAttacking(1);
					this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
					if (this.getDistanceSqToEntity(e) < 4.0D && (this.rand.nextInt(8) == 0 || this.rand.nextInt(7) == 1)) {
						this.attackEntityAsMob(e);
					}
				} else {
					this.setAttacking(0);
					if (this.myowner != null) {
						EntityPlayer p = this.worldObj.getPlayerEntityByName(this.myowner);
						if (p != null) {
							if (this.getDistanceSqToEntity(p) > (double)64.0F) {
								this.getNavigator().tryMoveToEntityLiving(p, 1.75D);
							}

							if (this.getDistanceSqToEntity(p) > 256.0D) {
								this.setPosition(p.posX + (double)this.worldObj.rand.nextFloat() - (double)this.worldObj.rand.nextFloat(), p.posY, p.posZ + (double)this.worldObj.rand.nextFloat() - (double)this.worldObj.rand.nextFloat());
							}
						}
					}
				}
			}

			if (this.worldObj.rand.nextInt(250) == 1) {
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
			} else if (par1EntityLiving instanceof Irukandji) {
				return false;
			} else if (par1EntityLiving instanceof Skate) {
				return false;
			} else if (par1EntityLiving instanceof Whale) {
				return false;
			} else if (par1EntityLiving instanceof Flounder) {
				return false;
			} else if (par1EntityLiving instanceof Rat) {
				return false;
			} else if (par1EntityLiving instanceof Ghost) {
				return false;
			} else if (par1EntityLiving instanceof GhostSkelly) {
				return false;
			} else if (par1EntityLiving instanceof DungeonBeast) {
				return false;
			} else {
				if (par1EntityLiving instanceof EntityPlayer) {
					EntityPlayer p = (EntityPlayer)par1EntityLiving;
					if (p.capabilities.isCreativeMode) {
						return false;
					}

					if (this.myowner != null) {
						if (this.myowner.equals(p.getUniqueID().toString())) {
							return false;
						}

						if (OreSpawnMain.RatPlayerFriendly != 0) {
							return false;
						}
					}
				}

				if (this.myowner != null && par1EntityLiving instanceof EntityTameable) {
					EntityTameable e = (EntityTameable)par1EntityLiving;
					if (OreSpawnMain.RatPetFriendly != 0 && e.isTamed()) {
						return false;
					}

					if (e.func_152113_b() != null && this.myowner.equals(e.func_152113_b())) {
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
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(9.0D, 2.0D, 9.0D));
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

	public void setOwner(EntityLivingBase e) {
		EntityPlayer p = null;
		if (e != null && e instanceof EntityPlayer) {
			p = (EntityPlayer)e;
			String s = p.getUniqueID().toString();
			if (s != null) {
				this.myowner = s;
			}
		}

	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		if (this.myowner == null) {
			this.myowner = "null";
		}

		par1NBTTagCompound.setString("MyOwner", this.myowner);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.myowner = par1NBTTagCompound.getString("MyOwner");
		if (this.myowner != null && this.myowner.equals("null")) {
			this.myowner = null;
		}

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

	public boolean getCanSpawnHere() {
		int sc = 0;

		for (int k = -2; k < 2; k++) {
			for (int j = -2; j < 2; j++) {
				for (int i = 0; i < 5; i++) {
					Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null && s.equals("Rat")) {
							return true;
						}
					}
				}
			}
		}

		if (!this.isValidLightLevel()) {
			return false;
		} else {
			if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID5) {
				if (this.posY > 50.0D) {
					return false;
				}

				for (int var10 = -1; var10 <= 1; ++var10) {
					for (int j = -1; j <= 1; j++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + 1, (int)this.posZ + var10);
						if (bid == Blocks.air) {
							++sc;
						}
					}
				}

				if (sc < 4) {
					return false;
				}
			}

			if (this.findBuddies() > 8) {
				return false;
			} else {
				return true;
			}
		}
	}

	private int findBuddies() {
		List var5 = this.worldObj.getEntitiesWithinAABB(Rat.class, this.boundingBox.expand(20.0D, 10.0D, 20.0D));
		return var5.size();
	}
}
