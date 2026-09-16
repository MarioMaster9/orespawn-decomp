package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class Lizard extends EntityCannonFodder {
	private GenericTargetSorter TargetSorter = null;
	public boolean should_despawn = true;
	private EntityLivingBase buddy = null;
	private int follow_time = 0;
	private float moveSpeed = 0.3F;
	private int closest = 99999;
	private int tx = 0;
	private int ty = 0;
	private int tz = 0;

	public Lizard(World par1World) {
		super(par1World);
		this.moveSpeed = 0.3F;
		this.setSize(1.5F, 1.25F);
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 15;
		this.fireResistance = 3;
		this.isImmuneToFire = false;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIFollowOwner(this, 2.0F, 10.0F, 2.0F));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.dye, false));
		this.tasks.addTask(4, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(23, (byte)0);
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	public int mygetMaxHealth() {
		return 30;
	}

	public int getTotalArmorValue() {
		return 5;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		Entity e = par1DamageSource.getEntity();
		if (!par1DamageSource.getDamageType().equals("cactus")) {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			if (e != null && e instanceof EntityLivingBase) {
				this.setAttackTarget((EntityLivingBase)e);
			}
		}

		this.follow_time = 0;
		return ret;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "orespawn:alo_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:alo_death";
	}

	protected float getSoundVolume() {
		return 1.0F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected Item getDropItem() {
		return null;
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (var2 != null && var2.stackSize <= 0) {
			par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
			var2 = null;
		}

		if (super.interact(par1EntityPlayer)) {
			return true;
		} else if (var2 != null && var2.getItem() == Items.dye && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D) {
			if (!this.worldObj.isRemote) {
				this.buddy = par1EntityPlayer;
				this.follow_time = 3000 + this.worldObj.rand.nextInt(2000);
			}

			this.playTameEffect(true);
			if (!par1EntityPlayer.capabilities.isCreativeMode) {
				--var2.stackSize;
				if (var2.stackSize <= 0) {
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}

			return true;
		} else {
			if (!this.worldObj.isRemote) {
				this.buddy = null;
				this.follow_time = 0;
			}

			this.playTameEffect(false);
			return true;
		}
	}

	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
		int found = 0;

		for (int i = -dy; i <= dy; i++) {
			for (int j = -dz; j <= dz; j++) {
				Block bid = this.worldObj.getBlock(x + dx, y + i, z + j);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dx * dx + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + dx;
						this.ty = y + i;
						this.tz = z + j;
						++found;
					}
				}

				bid = this.worldObj.getBlock(x - dx, y + i, z + j);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dx * dx + j * j + i * i;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x - dx;
						this.ty = y + i;
						this.tz = z + j;
						++found;
					}
				}
			}
		}

		for (int var12 = -dx; var12 <= dx; ++var12) {
			for (int j = -dz; j <= dz; j++) {
				Block bid = this.worldObj.getBlock(x + var12, y + dy, z + j);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dy * dy + j * j + var12 * var12;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + var12;
						this.ty = y + dy;
						this.tz = z + j;
						++found;
					}
				}

				bid = this.worldObj.getBlock(x + var12, y - dy, z + j);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dy * dy + j * j + var12 * var12;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + var12;
						this.ty = y - dy;
						this.tz = z + j;
						++found;
					}
				}
			}
		}

		for (int var13 = -dx; var13 <= dx; ++var13) {
			for (int j = -dy; j <= dy; j++) {
				Block bid = this.worldObj.getBlock(x + var13, y + j, z + dz);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dz * dz + j * j + var13 * var13;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + var13;
						this.ty = y + j;
						this.tz = z + dz;
						++found;
					}
				}

				bid = this.worldObj.getBlock(x + var13, y + j, z - dz);
				if (bid == Blocks.water || bid == Blocks.flowing_water) {
					int d = dz * dz + j * j + var13 * var13;
					if (d < this.closest) {
						this.closest = d;
						this.tx = x + var13;
						this.ty = y + j;
						this.tz = z - dz;
						++found;
					}
				}
			}
		}

		if (found != 0) {
			return true;
		} else {
			return false;
		}
	}

	protected void updateAITasks() {
		if (!this.isDead) {
			super.updateAITasks();
			if (this.follow_time > 0) {
				--this.follow_time;
				this.should_despawn = false;
			} else {
				this.should_despawn = true;
			}

			if (!this.isInWater() && this.worldObj.rand.nextInt(100) == 0) {
				this.closest = 99999;
				this.tx = this.ty = this.tz = 0;

				for (int i = 1; i < 14; i++) {
					int j = i;
					if (i > 5) {
						j = 5;
					}

					if (this.scan_it((int)this.posX, (int)this.posY - 1, (int)this.posZ, i, j, i)) {
						break;
					}

					if (i >= 5) {
						++i;
					}
				}

				if (this.closest < 99999) {
					this.getNavigator().tryMoveToXYZ((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.33);
				}
			}

			if (this.getHealth() < (float)this.mygetMaxHealth() && this.worldObj.rand.nextInt(300) == 1) {
				this.heal(1.0F);
			}

			if (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.rand.nextInt(10) == 1) {
				EntityLivingBase e = this.findSomethingToAttack();
				if (e != null) {
					this.follow_time = 0;
					if (this.getDistanceSqToEntity(e) < 12.0D) {
						this.setAttacking(1);
						if (this.worldObj.rand.nextInt(4) == 0 || this.worldObj.rand.nextInt(5) == 1) {
							this.attackEntityAsMob(e);
						}
					} else {
						this.getNavigator().tryMoveToEntityLiving(e, 1.2);
					}
				} else {
					if (this.buddy != null && !this.buddy.isDead && this.worldObj.rand.nextInt(15) == 1) {
						this.getNavigator().tryMoveToEntityLiving(this.buddy, 1.0D);
					}

					this.setAttacking(0);
				}
			}

			if (this.buddy != null && !this.buddy.isDead && this.follow_time > 0 && this.worldObj.rand.nextInt(20) == 1) {
				this.getNavigator().tryMoveToEntityLiving(this.buddy, 1.0D);
			}

		}
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		float i = 6.0F;
		boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
		return flag;
	}

	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2) {
		if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			return false;
		} else if (par1EntityLiving == null) {
			return false;
		} else if (par1EntityLiving == this) {
			return false;
		} else if (!par1EntityLiving.isEntityAlive()) {
			return false;
		} else if (!this.getEntitySenses().canSee(par1EntityLiving)) {
			return false;
		} else if (par1EntityLiving instanceof AttackSquid) {
			return true;
		} else if (par1EntityLiving instanceof EntitySpider) {
			return true;
		} else if (par1EntityLiving instanceof EntityCaveSpider) {
			return true;
		} else if (par1EntityLiving instanceof EntityChicken) {
			return true;
		} else {
			if (par1EntityLiving instanceof Lizard && this.worldObj.rand.nextInt(10) == 1 && this.follow_time <= 0) {
				this.buddy = par1EntityLiving;
			}

			return false;
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(12.0D, 4.0D, 12.0D));
			Collections.sort(var5, this.TargetSorter);
			Iterator var2 = var5.iterator();
			Entity var3 = null;
			EntityLivingBase var4 = null;
			if (this.worldObj.rand.nextInt(100) == 0) {
				this.setAttackTarget((EntityLivingBase)null);
			}

			EntityLivingBase e = this.getAttackTarget();
			if (e != null && e.isEntityAlive()) {
				return e;
			} else {
				this.setAttackTarget((EntityLivingBase)null);

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
	}

	public final int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(23);
	}

	public final void setAttacking(int par1) {
		this.dataWatcher.updateObject(23, (byte)par1);
	}

	public boolean getCanSpawnHere() {
		return !(this.posY < 50.0D);
	}

	protected boolean canDespawn() {
		if (this.isChild()) {
			this.func_110163_bv();
			return false;
		} else if (this.isNoDespawnRequired()) {
			return false;
		} else {
			return this.isTamed() ? false : this.should_despawn;
		}
	}

	public EntityAgeable createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}

	public Lizard spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new Lizard(this.worldObj);
	}

	public boolean isWheat(ItemStack par1ItemStack) {
		return par1ItemStack != null && par1ItemStack.getItem() == Items.apple;
	}

	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() == OreSpawnMain.MyCrystalApple;
	}
}
