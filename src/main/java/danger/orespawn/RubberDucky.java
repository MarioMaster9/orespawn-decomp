package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class RubberDucky extends EntityTameable {
	private GenericTargetSorter TargetSorter = null;
	public boolean should_despawn = true;
	private EntityLivingBase buddy = null;
	private float moveSpeed = 0.22F;
	private int killcount = 0;
	private int died = 0;
	private RenderInfo renderdata = new RenderInfo();
	private int closest = 99999;
	private int tx = 0;
	private int ty = 0;
	private int tz = 0;

	public RubberDucky(World par1World) {
		super(par1World);
		this.moveSpeed = 0.22F;
		this.setSize(0.33F, 0.5F);
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 15;
		this.fireResistance = 3;
		this.isImmuneToFire = false;
		this.renderdata = new RenderInfo();
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(1, new MyEntityAIFollowOwner(this, 2.0F, 10.0F, 2.0F));
		this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.fish, false));
		this.tasks.addTask(4, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityLiving.class, 6.0F));
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
		this.dataWatcher.addObject(22, (byte)0);
		this.setSitting(false);
		if (this.getGrowingAge() < 0) {
			this.setGrowingAge(-this.getGrowingAge());
		}

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

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (this.isInWater()) {
			this.motionY += 0.1D;
			if (this.motionY < (double)-0.05F) {
				this.motionY = (double)-0.05F;
			}
		}

	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		Entity w = null;
		w = par1DamageSource.getEntity();
		ret = super.attackEntityFrom(par1DamageSource, par2);
		this.setSitting(false);
		if (!this.worldObj.isRemote && w != null && w instanceof EntityPlayer && (this.isDead || this.getHealth() <= 0.0F) && this.died == 0) {
			this.died = 1;
			++this.killcount;
			this.setKillCount(this.killcount);
			if (this.killcount < 10) {
				for (int m = 0; m < 20; ++m) {
					int i = this.worldObj.rand.nextInt(3);
					if (this.worldObj.rand.nextInt(2) == 1) {
						i = -i;
					}

					int k = this.worldObj.rand.nextInt(3);
					if (this.worldObj.rand.nextInt(2) == 1) {
						k = -k;
					}

					for (int j = 3; j > -3; --j) {
						if (this.worldObj.getBlock((int)this.posX + i, (int)this.posY + j + 1, (int)this.posZ + k) == Blocks.air && this.worldObj.getBlock((int)this.posX + i, (int)this.posY + j, (int)this.posZ + k) != Blocks.air) {
							Entity e = spawnCreature(this.worldObj, "Rubber Ducky", (double)((int)this.posX + i + 1), (double)((int)this.posY + j + 1), (double)((int)this.posZ + k));
							if (e != null) {
								RubberDucky d = (RubberDucky)e;
								d.setKillCount(this.killcount);
							}

							return ret;
						}
					}
				}
			}
		}

		return ret;
	}

	public int mygetMaxHealth() {
		return 5;
	}

	public int getTotalArmorValue() {
		return 1;
	}

	protected void fall(float par1) {
	}

	protected void updateFallState(double par1, boolean par3) {
	}

	protected boolean isAIEnabled() {
		return true;
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

	protected String getLivingSound() {
		return this.worldObj.rand.nextInt(10) == 1 ? "orespawn:duck_hurt" : null;
	}

	protected String getHurtSound() {
		return "orespawn:duck_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:duck_hurt";
	}

	protected float getSoundVolume() {
		return 0.8F;
	}

	protected float getSoundPitch() {
		return 1.2F;
	}

	protected Item getDropItem() {
		if (this.worldObj.rand.nextInt(2) == 1) {
			return Items.feather;
		} else {
			return this.worldObj.rand.nextInt(2) == 1 ? OreSpawnMain.RubberDuckyEgg : null;
		}
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (var2 != null && var2.stackSize <= 0) {
			par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
			var2 = null;
		}

		if (super.interact(par1EntityPlayer)) {
			return true;
		} else if (var2 != null && var2.getItem() == Items.fish && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D) {
			if (!this.isTamed()) {
				if (!this.worldObj.isRemote) {
					if (this.rand.nextInt(2) == 0) {
						this.setTamed(true);
						this.func_152115_b(par1EntityPlayer.getUniqueID().toString());
						this.playTameEffect(true);
						this.worldObj.setEntityState(this, (byte)7);
						this.heal((float)this.mygetMaxHealth() - this.getHealth());
					} else {
						this.playTameEffect(false);
						this.worldObj.setEntityState(this, (byte)6);
					}
				}
			} else if (this.func_152114_e(par1EntityPlayer)) {
				if (this.worldObj.isRemote) {
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)7);
				}

				if ((float)this.mygetMaxHealth() > this.getHealth()) {
					this.heal((float)this.mygetMaxHealth() - this.getHealth());
				}
			}

			if (!par1EntityPlayer.capabilities.isCreativeMode) {
				--var2.stackSize;
				if (var2.stackSize <= 0) {
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}

			return true;
		} else if (this.isTamed() && var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.deadbush) && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D && this.func_152114_e(par1EntityPlayer)) {
			if (!this.worldObj.isRemote) {
				this.setTamed(false);
				this.func_152115_b("");
				this.playTameEffect(false);
				this.worldObj.setEntityState(this, (byte)6);
			}

			if (!par1EntityPlayer.capabilities.isCreativeMode) {
				--var2.stackSize;
				if (var2.stackSize <= 0) {
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}

			return true;
		} else if (this.isTamed() && this.func_152114_e(par1EntityPlayer) && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D) {
			if (!this.isSitting() && this.getKillCount() < 5) {
				this.setSitting(true);
			} else {
				this.setSitting(false);
			}

			return true;
		} else {
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
			if (!this.isInWater() && this.worldObj.rand.nextInt(50) == 0) {
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

			if (this.killcount > 0 && this.worldObj.rand.nextInt(200) == 1) {
				--this.killcount;
				this.setKillCount(this.killcount);
			}

			if (this.getHealth() < (float)this.mygetMaxHealth() && this.worldObj.rand.nextInt(300) == 1) {
				this.heal(1.0F);
			}

			if (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && this.worldObj.rand.nextInt(5) == 1) {
				EntityLivingBase e = this.findSomethingToAttack();
				if (e != null) {
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

			if (this.buddy != null && !this.buddy.isDead && this.worldObj.rand.nextInt(20) == 1) {
				this.getNavigator().tryMoveToEntityLiving(this.buddy, 1.0D);
			}

		}
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		float i = 1.0F;
		if (this.getKillCount() >= 5) {
			i = 2.0F;
		}

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
		} else if (par1EntityLiving instanceof EntitySquid) {
			return true;
		} else {
			if (par1EntityLiving instanceof RubberDucky && this.worldObj.rand.nextInt(10) == 1) {
				this.buddy = par1EntityLiving;
			}

			if (this.getKillCount() >= 5 && par1EntityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)par1EntityLiving;
				return !p.capabilities.isCreativeMode;
			} else {
				return false;
			}
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand((double)8.0F, 4.0D, 8.0D));
			Collections.sort(var5, this.TargetSorter);
			Iterator var2 = var5.iterator();
			Entity var3 = null;
			EntityLivingBase var4 = null;
			EntityLivingBase e = this.getAttackTarget();
			if (e != null && e.isEntityAlive()) {
				return e;
			} else {
				this.setAttackTarget((EntityLivingBase)null);
				this.buddy = null;

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

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("Killcount", this.killcount);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.killcount = par1NBTTagCompound.getInteger("Killcount");
		this.setKillCount(this.killcount);
	}

	public final int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(23);
	}

	public final void setAttacking(int par1) {
		this.dataWatcher.updateObject(23, (byte)par1);
	}

	public final int getKillCount() {
		return this.dataWatcher.getWatchableObjectByte(22);
	}

	public final void setKillCount(int par1) {
		this.dataWatcher.updateObject(22, (byte)par1);
		this.killcount = par1;
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
						if (s != null && s.equals("Rubber Ducky")) {
							return true;
						}
					}
				}
			}
		}

		if (this.posY < 50.0D) {
			return false;
		} else if (!this.worldObj.isDaytime()) {
			return false;
		} else {
			return true;
		}
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

	public RubberDucky spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new RubberDucky(this.worldObj);
	}

	public boolean isWheat(ItemStack par1ItemStack) {
		return par1ItemStack != null && par1ItemStack.getItem() == Items.fish;
	}

	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() == OreSpawnMain.MyCrystalApple;
	}
}
