package danger.orespawn;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Camarasaurus extends EntityTameable {
	private float moveSpeed = 0.2F;
	private int closest = 99999;
	private int tx = 0;
	private int ty = 0;
	private int tz = 0;

	public Camarasaurus(World par1World) {
		super(par1World);
		this.setSize(0.5F, 1.2F);
		this.moveSpeed = 0.2F;
		this.fireResistance = 100;
		this.getNavigator().setAvoidsWater(true);
		this.setSitting(false);
		this.experienceValue = 5;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new MyEntityAIFollowOwner(this, 2.0F, 10.0F, 2.0F));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityMob.class, 8.0F, 1.0D, (double)1.4F));
		this.tasks.addTask(4, new EntityAITempt(this, (double)1.2F, Items.apple, false));
		this.tasks.addTask(5, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new MyEntityAIWander(this, 1.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.tasks.addTask(9, new EntityAIMoveIndoors(this));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
	}

	protected void entityInit() {
		super.entityInit();
		this.setSitting(false);
	}

	public boolean getCanSpawnHere() {
		if (this.posY < 50.0D) {
			return false;
		} else {
			return this.worldObj.isDaytime();
		}
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	protected void fall(float par1) {
		float i = (float)MathHelper.ceiling_float_int(par1 - 3.0F);
		if (i > 0.0F) {
			if (i > 3.0F) {
				this.playSound("damage.fallbig", 1.0F, 1.0F);
			} else {
				this.playSound("damage.fallsmall", 1.0F, 1.0F);
			}

			if (i > 2.0F) {
				i = 2.0F;
			}

			this.attackEntityFrom(DamageSource.fall, i);
		}

	}

	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
		int found = 0;

		for (int i = -dy; i <= dy; i++) {
			for (int j = -dz; j <= dz; j++) {
				Block bid = this.worldObj.getBlock(x + dx, y + i, z + j);
				if (bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.tallgrass || bid == Blocks.cactus || bid == Blocks.double_plant) {
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
				if (bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.tallgrass || bid == Blocks.cactus || bid == Blocks.double_plant) {
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
				if (bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.tallgrass || bid == Blocks.cactus || bid == Blocks.double_plant) {
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
				if (bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.tallgrass || bid == Blocks.cactus || bid == Blocks.double_plant) {
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
				if (bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.tallgrass || bid == Blocks.cactus || bid == Blocks.double_plant) {
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
				if (bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.tallgrass || bid == Blocks.cactus || bid == Blocks.double_plant) {
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

	protected void updateAITick() {
		if (!this.isDead) {
			if (this.worldObj.rand.nextInt(200) == 1) {
				this.setRevengeTarget((EntityLivingBase)null);
			}

			super.updateAITick();
			if (!this.isSitting() && (this.worldObj.rand.nextInt(20) == 0 && this.getCamarasaurusHealth() < this.mygetMaxHealth() || this.worldObj.rand.nextInt(250) == 0) && OreSpawnMain.PlayNicely == 0) {
				this.closest = 99999;
				this.tx = this.ty = this.tz = 0;

				for (int i = 1; i < 11; i++) {
					int j = i;
					if (i > 2) {
						j = 2;
					}

					if (this.scan_it((int)this.posX, (int)this.posY + 1, (int)this.posZ, i, j, i)) {
						break;
					}

					if (i >= 6) {
						++i;
					}
				}

				if (this.closest < 99999) {
					this.getNavigator().tryMoveToXYZ((double)this.tx, (double)this.ty, (double)this.tz, 1.0D);
					if (this.closest < 12) {
						if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
							this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.air, 0, 2);
						}

						this.heal(1.0F);
						this.playSound("random.burp", 1.0F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
					}
				}
			}

		}
	}

	public boolean isAIEnabled() {
		return true;
	}

	public boolean canBreatheUnderwater() {
		return false;
	}

	public int mygetMaxHealth() {
		return 20;
	}

	public int getCamarasaurusHealth() {
		return (int)this.getHealth();
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
		if (var2 != null && var2.stackSize <= 0) {
			par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
			var2 = null;
		}

		if (super.interact(par1EntityPlayer)) {
			return true;
		} else if (var2 != null && var2.getItem() == Items.apple && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D) {
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
		} else if (this.isTamed() && var2 != null && var2.getItem() == Items.name_tag && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D && this.func_152114_e(par1EntityPlayer)) {
			this.setCustomNameTag(var2.getDisplayName());
			if (!par1EntityPlayer.capabilities.isCreativeMode) {
				--var2.stackSize;
				if (var2.stackSize <= 0) {
					par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				}
			}

			return true;
		} else if (this.isTamed() && this.func_152114_e(par1EntityPlayer) && par1EntityPlayer.getDistanceSqToEntity(this) < 16.0D) {
			if (!this.isSitting()) {
				this.setSitting(true);
			} else {
				this.setSitting(false);
			}

			return true;
		} else {
			return false;
		}
	}

	protected String getLivingSound() {
		return this.isSitting() ? null : null;
	}

	protected String getHurtSound() {
		return "orespawn:cryo_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:cryo_death";
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected Item getDropItem() {
		return Item.getItemFromBlock(Blocks.red_flower);
	}

	protected void dropFewItems(boolean par1, int par2) {
		int var3 = 0;
		if (this.isTamed()) {
			var3 = this.rand.nextInt(5);
			var3 += 2;

			for (int var4 = 0; var4 < var3; ++var4) {
				this.dropItem(Item.getItemFromBlock(Blocks.red_flower), 1);
			}
		}

	}

	protected float getSoundPitch() {
		return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.5F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.0F;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		ret = super.attackEntityFrom(par1DamageSource, par2);
		return ret;
	}

	protected boolean canDespawn() {
		if (this.isChild()) {
			this.func_110163_bv();
			return false;
		} else if (this.isNoDespawnRequired()) {
			return false;
		} else {
			return !this.isTamed();
		}
	}

	public EntityAgeable createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}

	public Camarasaurus spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new Camarasaurus(this.worldObj);
	}

	public boolean isWheat(ItemStack par1ItemStack) {
		return par1ItemStack != null && par1ItemStack.getItem() == Items.apple;
	}

	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() == OreSpawnMain.MyCrystalApple;
	}
}
