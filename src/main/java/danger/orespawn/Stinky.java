package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class Stinky extends EntityTameable {
	private ChunkCoordinates currentFlightTarget;
	private GenericTargetSorter TargetSorter = null;
	public int activity = 1;
	private int owner_flying = 0;
	private float moveSpeed = 0.3F;
	private int skin_color = -1;
	private int syncit = 0;
	private int closest = 99999;
	private int tx = 0;
	private int ty = 0;
	private int tz = 0;

	public Stinky(World par1World) {
		super(par1World);
		this.setSize(0.75F, 0.75F);
		this.moveSpeed = 0.3F;
		this.fireResistance = 1000;
		this.isImmuneToFire = true;
		this.getNavigator().setAvoidsWater(true);
		this.setSitting(false);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityMob.class, 8.0F, (double)0.3F, (double)0.4F));
		this.tasks.addTask(3, new MyEntityAIFollowOwner(this, 1.15F, 12.0F, 2.0F));
		this.tasks.addTask(4, new EntityAITempt(this, 1.25D, Items.beef, false));
		this.tasks.addTask(5, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new MyEntityAIWander(this, 0.75F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.tasks.addTask(9, new EntityAIMoveIndoors(this));
		this.TargetSorter = new GenericTargetSorter(this);
		this.experienceValue = 35;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D);
	}

	protected void entityInit() {
		super.entityInit();
		this.activity = 1;
		this.dataWatcher.addObject(22, 0);
		this.dataWatcher.addObject(21, this.activity);
		this.dataWatcher.addObject(20, 1);
		this.setSitting(false);
		this.setTamed(false);
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("SpyroActivity", this.dataWatcher.getWatchableObjectInt(21));
		par1NBTTagCompound.setInteger("SpyroFire", this.dataWatcher.getWatchableObjectInt(20));
		par1NBTTagCompound.setInteger("StinkySkin", this.dataWatcher.getWatchableObjectInt(22));
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.activity = par1NBTTagCompound.getInteger("SpyroActivity");
		this.dataWatcher.updateObject(21, this.activity);
		this.dataWatcher.updateObject(20, par1NBTTagCompound.getInteger("SpyroFire"));
		this.skin_color = par1NBTTagCompound.getInteger("StinkySkin");
		this.dataWatcher.updateObject(22, this.skin_color);
	}

	public int getActivity() {
		int i = this.dataWatcher.getWatchableObjectInt(21);
		this.activity = i;
		return i;
	}

	public void setActivity(int par1) {
		this.activity = par1;
		this.dataWatcher.updateObject(21, par1);
	}

	public int getSpyroFire() {
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	public void setSpyroFire(int par1) {
		this.dataWatcher.updateObject(20, par1);
	}

	public int getSkin() {
		int i = this.dataWatcher.getWatchableObjectInt(22);
		this.skin_color = i;
		return i;
	}

	public void setSkin(int par1) {
		this.skin_color = par1;
		this.dataWatcher.updateObject(22, 0);
		this.dataWatcher.updateObject(22, par1);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public int mygetMaxHealth() {
		return 100;
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (var2 != null && var2.stackSize <= 0) {
			par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
			var2 = null;
		}

		if (var2 != null && var2.getItem() == Items.beef && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D) {
			if (!this.isTamed()) {
				if (!this.worldObj.isRemote) {
					if (this.worldObj.rand.nextInt(2) == 1) {
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
				this.setHealth((float)this.mygetMaxHealth());
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
		} else if (this.isTamed() && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D && this.func_152114_e(par1EntityPlayer)) {
			if (!this.isSitting()) {
				this.setSitting(true);
				this.setActivity(1);
			} else {
				this.setSitting(false);
			}

			return true;
		} else {
			return super.interact(par1EntityPlayer);
		}
	}

	public boolean isWheat(ItemStack par1ItemStack) {
		return par1ItemStack != null && par1ItemStack.getItem() == Items.beef;
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) {
			return false;
		} else {
			return !this.isTamed();
		}
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "orespawn:duck_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:cryo_death";
	}

	protected float getSoundVolume() {
		return 0.6F;
	}

	public int getTotalArmorValue() {
		return 6;
	}

	protected Item getDropItem() {
		return Items.beef;
	}

	protected void dropFewItems(boolean par1, int par2) {
		int var3 = 0;
		if (this.isTamed()) {
			var3 = this.worldObj.rand.nextInt(4);
			++var3;

			for (int var4 = 0; var4 < var3; ++var4) {
				this.dropItem(Items.beef, 1);
			}
		}

	}

	protected float getSoundPitch() {
		return this.isChild() ? (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.1F + 1.5F : (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.1F + 1.0F;
	}

	protected boolean canTriggerWalking() {
		return true;
	}

	protected void fall(float par1) {
	}

	protected void updateFallState(double par1, boolean par3) {
	}

	public boolean doesEntityNotTriggerPressurePlate() {
		return false;
	}

	public boolean getCanSpawnHere() {
		if (!this.worldObj.isDaytime()) {
			return false;
		} else {
			return this.findBuddies() <= 2;
		}
	}

	public EntityAgeable createChild(EntityAgeable var1) {
		return null;
	}

	public float getAttackStrength(Entity par1Entity) {
		return 10.0F;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		float var2 = this.getAttackStrength(par1Entity);
		boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
		return var4;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		if (!par1DamageSource.getDamageType().equals("cactus")) {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			this.setSitting(false);
			this.setActivity(2);
		}

		return ret;
	}

	public boolean canSeeTarget(double pX, double pY, double pZ) {
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	private void dropItemFront(Item index, int par1) {
		float f = 0.75F + Math.abs(this.worldObj.rand.nextFloat() * 0.75F);
		EntityItem var3 = new EntityItem(this.worldObj, this.posX - (double)f * Math.sin(Math.toRadians((double)this.rotationYawHead)), this.posY + 0.9, this.posZ + (double)f * Math.cos(Math.toRadians((double)this.rotationYawHead)), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	private void dropItemRear(Item index, int par1) {
		float f = 0.55F + Math.abs(this.worldObj.rand.nextFloat() * 0.55F);
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)f * Math.sin(Math.toRadians((double)this.rotationYawHead)), this.posY + 0.25D, this.posZ - (double)f * Math.cos(Math.toRadians((double)this.rotationYawHead)), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	public void onUpdate() {
		super.onUpdate();
		if (!this.worldObj.isRemote && this.worldObj.rand.nextInt(1750) == 1) {
			this.playSound("random.burp", 1.0F, 1.0F);
			this.dropItemFront(Items.coal, 1);
		}

		if (!this.worldObj.isRemote && this.worldObj.rand.nextInt(2000) == 2) {
			this.playSound("orespawn:fart", 1.0F, 1.5F);
			if (this.skin_color == 0) {
				this.dropItemRear(Items.blaze_powder, 1);
			}

			if (this.skin_color == 1) {
				this.dropItemRear(Items.rotten_flesh, 1);
			}

			if (this.skin_color == 2) {
				this.dropItemRear(Items.melon_seeds, 1);
			}

			if (this.skin_color == 3) {
				this.dropItemRear(OreSpawnMain.UraniumNugget, 1);
			}

			if (this.skin_color == 4) {
				this.dropItemRear(Items.wheat, 1);
			}

			if (this.skin_color == 5) {
				this.dropItemRear(Items.brick, 1);
			}

			if (this.skin_color == 6) {
				this.dropItemRear(Item.getItemFromBlock(Blocks.torch), 1);
			}

			if (this.skin_color == 7) {
				this.dropItemRear(Items.emerald, 1);
			}

			if (this.skin_color == 8) {
				this.dropItemRear(Items.gold_ingot, 1);
			}

			if (this.skin_color == 9) {
				this.dropItemRear(Item.getItemFromBlock(Blocks.leaves), 1);
			}

			if (this.skin_color == 10) {
				this.dropItemRear(OreSpawnMain.TitaniumNugget, 1);
			}

			if (this.skin_color == 11) {
				this.dropItemRear(OreSpawnMain.MyAppleSeed, 1);
			}

			if (this.skin_color == 12) {
				this.dropItemRear(Items.diamond, 1);
			}

			if (this.skin_color == 13) {
				this.dropItemRear(Item.getItemFromBlock(Blocks.sand), 1);
			}

			if (this.skin_color == 14) {
				this.dropItemRear(Item.getItemFromBlock(Blocks.cobblestone), 1);
			}

			if (this.skin_color == 15) {
				this.dropItemRear(Items.bone, 1);
			}

			if (this.skin_color == 16) {
				this.dropItemRear(Items.string, 1);
			}

			if (this.skin_color == 17) {
				this.dropItemRear(OreSpawnMain.MyCherrySeed, 1);
			}

			if (this.skin_color == 18) {
				this.dropItemRear(OreSpawnMain.MyPeachSeed, 1);
			}
		}

	}

	public void onLivingUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onLivingUpdate();
		if (this.isInWater()) {
			this.motionY += 0.07;
		}

		if (!this.worldObj.isRemote && this.worldObj.rand.nextInt(2000) == 1) {
			int i = this.worldObj.rand.nextInt(19);
			this.setSkin(i);
		}

		if (this.currentFlightTarget == null) {
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}

		if (this.skin_color < 0) {
			this.skin_color = this.worldObj.rand.nextInt(19);
		}

		++this.syncit;
		if (this.syncit > 20) {
			this.syncit = 0;
			if (this.worldObj.isRemote) {
				this.getActivity();
				this.getSkin();
			} else {
				int j = this.activity;
				this.setActivity(j);
				j = this.skin_color;
				this.setSkin(j);
			}
		}

		if (this.activity == 2) {
			this.motionY *= 0.6;
		}

	}

	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
		int found = 0;

		for (int i = -dy; i <= dy; i++) {
			for (int j = -dz; j <= dz; j++) {
				Block bid = this.worldObj.getBlock(x + dx, y + i, z + j);
				if (bid == Blocks.coal_ore) {
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
				if (bid == Blocks.coal_ore) {
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
				if (bid == Blocks.coal_ore) {
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
				if (bid == Blocks.coal_ore) {
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
				if (bid == Blocks.coal_ore) {
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
				if (bid == Blocks.coal_ore) {
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
			if (this.worldObj.rand.nextInt(200) == 1) {
				this.setRevengeTarget((EntityLivingBase)null);
			}

			if (this.activity != 2) {
				super.updateAITasks();
			}

			if (this.worldObj.rand.nextInt(100) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
				this.heal(1.0F);
			}

			if (!this.isSitting()) {
				if (this.activity == 0) {
					this.setActivity(1);
				}

				if (this.worldObj.rand.nextInt(100) == 1) {
					if (this.worldObj.rand.nextInt(20) == 1) {
						this.setActivity(2);
					} else {
						this.setActivity(1);
					}
				}

				this.owner_flying = 0;
				if (this.isTamed() && this.getOwner() != null) {
					EntityPlayer e = (EntityPlayer)this.getOwner();
					if (e.capabilities.isFlying) {
						this.owner_flying = 1;
						this.setActivity(2);
					}
				}

				if (this.activity == 1 && this.isTamed() && this.getOwner() != null) {
					EntityLivingBase e = this.getOwner();
					if (this.getDistanceSqToEntity(e) > 256.0D) {
						this.setActivity(2);
					}
				}

				this.do_movement();
			}

		}
	}

	private void do_movement() {
		int xdir = 1;
		int zdir = 1;
		int keep_trying = 50;
		int do_new = 0;
		double ox = 0.0D;
		double oy = 0.0D;
		double oz = 0.0D;
		int has_owner = 0;
		EntityLivingBase e = null;
		if (this.currentFlightTarget == null) {
			do_new = 1;
			this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
		}

		if (this.activity == 2 && this.worldObj.rand.nextInt(300) == 0) {
			do_new = 1;
		}

		if (this.isTamed() && this.getOwner() != null) {
			e = this.getOwner();
			has_owner = 1;
			ox = e.posX;
			oy = e.posY;
			oz = e.posZ;
			if (this.getDistanceSqToEntity(e) > (double)100.0F) {
				do_new = 1;
			}

			if (this.owner_flying != 0 && this.getDistanceSqToEntity(e) > 36.0D) {
				do_new = 1;
			}
		}

		if (this.worldObj.rand.nextInt(7) == 1 && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) {
			e = this.findSomethingToAttack();
			if (e != null) {
				if (this.isTamed() && this.getHealth() / (float)this.mygetMaxHealth() < 0.25F) {
					this.setActivity(2);
					do_new = 0;
					this.currentFlightTarget.set((int)(this.posX + (this.posX - e.posX)), (int)(this.posY + 1.0D), (int)(this.posZ + (this.posZ - e.posZ)));
				} else {
					this.setActivity(2);
					this.currentFlightTarget.set((int)e.posX, (int)(e.posY + 1.0D), (int)e.posZ);
					do_new = 0;
					if (this.getDistanceSqToEntity(e) < (double)((3.0F + e.width / 2.0F) * (3.0F + e.width / 2.0F))) {
						this.attackEntityAsMob(e);
					}
				}
			}
		}

		if (this.activity == 1) {
			if (this.worldObj.rand.nextInt(50) == 0 && OreSpawnMain.PlayNicely == 0) {
				this.closest = 99999;
				this.tx = this.ty = this.tz = 0;

				for (int i = 1; i < 9; i++) {
					int j = i;
					if (i > 2) {
						j = 2;
					}

					if (this.scan_it((int)this.posX, (int)this.posY + 1, (int)this.posZ, i, j, i)) {
						break;
					}

					if (i >= 4) {
						++i;
					}
				}

				if (this.closest < 99999) {
					this.getNavigator().tryMoveToXYZ((double)this.tx, (double)this.ty, (double)this.tz, 1.25D);
					if (this.closest < 12) {
						this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.air, 0, 2);
						this.heal(1.0F);
						this.playSound("random.burp", 0.5F, this.worldObj.rand.nextFloat() * 0.2F + 1.5F);
					}
				}
			}

		} else {
			if (this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 2.1F) {
				do_new = 1;
			}

			if (do_new != 0) {
				for (Block bid = Blocks.stone; bid != Blocks.air && keep_trying != 0; --keep_trying) {
					int gox = (int)this.posX;
					int goy = (int)this.posY;
					int goz = (int)this.posZ;
					if (has_owner == 1) {
						gox = (int)ox;
						goy = (int)oy;
						goz = (int)oz;
						if (this.owner_flying == 0) {
							zdir = this.worldObj.rand.nextInt(4) + 6;
							xdir = this.worldObj.rand.nextInt(4) + 6;
						} else {
							zdir = this.worldObj.rand.nextInt(8);
							xdir = this.worldObj.rand.nextInt(8);
						}
					} else {
						zdir = this.worldObj.rand.nextInt(5) + 6;
						xdir = this.worldObj.rand.nextInt(5) + 6;
					}

					if (this.worldObj.rand.nextInt(2) == 0) {
						zdir = -zdir;
					}

					if (this.worldObj.rand.nextInt(2) == 0) {
						xdir = -xdir;
					}

					this.currentFlightTarget.set(gox + xdir, goy + this.worldObj.rand.nextInt(6 + this.owner_flying * 2) - 2, goz + zdir);
					bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
					if (bid == Blocks.air && !this.canSeeTarget((double)this.currentFlightTarget.posX, (double)this.currentFlightTarget.posY, (double)this.currentFlightTarget.posZ)) {
						bid = Blocks.stone;
					}
				}
			}

			double speed_factor = 1.0D;
			double var1 = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
			double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
			double var5 = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
			if (this.owner_flying != 0) {
				speed_factor = 1.75D;
				if (this.isTamed() && this.getOwner() != null) {
					e = this.getOwner();
					if (this.getDistanceSqToEntity(e) > (double)49.0F) {
						speed_factor = (double)3.5F;
					}
				}
			}

			this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.15 * speed_factor;
			this.motionY += (Math.signum(var3) * 0.7 - this.motionY) * 0.21 * speed_factor;
			this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.15 * speed_factor;
			float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
			this.moveForward = (float)(0.75D * speed_factor);
			this.rotationYaw += var8 / 3.0F;
		}
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
		} else if (par1EntityLiving instanceof Mothra) {
			return true;
		} else {
			return par1EntityLiving instanceof EntityMob;
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
				if (this.isSuitableTarget(var4, false) && this.canSeeTarget(var4.posX, var4.posY, var4.posZ)) {
					return var4;
				}
			}

			return null;
		}
	}

	private int findBuddies() {
		List var5 = this.worldObj.getEntitiesWithinAABB(Stinky.class, this.boundingBox.expand(20.0D, 10.0D, 20.0D));
		return var5.size();
	}
}
