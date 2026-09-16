package danger.orespawn;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Whale extends EntityAnimal {
	private float moveSpeed = 0.35F;
	private int spray = 0;
	private int spray_timer = 0;
	private int closest = 99999;
	private int tx = 0;
	private int ty = 0;
	private int tz = 0;

	public Whale(World par1World) {
		super(par1World);
		this.setSize(1.5F, 2.5F);
		this.moveSpeed = 0.35F;
		this.fireResistance = 100;
		this.experienceValue = 40;
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new EntityAITempt(this, (double)1.2F, Items.fish, false));
		this.tasks.addTask(4, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
		this.tasks.addTask(6, new MyEntityAIWander(this, 1.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
	}

	protected void entityInit() {
		super.entityInit();
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (this.spray == 0) {
			if (this.spray_timer > 0) {
				--this.spray_timer;
			}

			if (this.spray_timer == 0) {
				this.spray_timer = 250 + this.worldObj.rand.nextInt(250);
				this.spray = 25 + this.worldObj.rand.nextInt(25);
			}
		}

		if (this.worldObj.isRemote && this.spray > 0) {
			for (int i = 0; i < 20; i++) {
				double d = this.worldObj.rand.nextDouble() * 0.75D;
				d *= d;
				double dir = this.worldObj.rand.nextDouble() * 2.0D * Math.PI;
				dir -= Math.PI;
				double dx = Math.cos(dir) * d / 2.0D;
				double dz = Math.sin(dir) * d / 2.0D;
				++dir;
				if (i < 10) {
					this.worldObj.spawnParticle("bubble", this.posX + dx, this.posY + 1.0D + d, this.posZ + dz, Math.cos(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D, (double)(this.worldObj.rand.nextFloat() * 2.0F), Math.sin(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D);
				} else {
					this.worldObj.spawnParticle("splash", this.posX + dx, this.posY + 1.0D + d, this.posZ + dz, Math.cos(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D, (double)(this.worldObj.rand.nextFloat() * 2.0F), Math.sin(dir) * (double)this.worldObj.rand.nextFloat() / 4.0D);
				}
			}

			--this.spray;
		}

		if (this.worldObj.rand.nextInt(200) == 1) {
			this.heal(1.0F);
		}

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

	protected String getLivingSound() {
		return "splash";
	}

	protected String getHurtSound() {
		return "orespawn:little_splat";
	}

	protected String getDeathSound() {
		return "orespawn:big_splat";
	}

	protected float getSoundVolume() {
		return 0.9F;
	}

	protected float getSoundPitch() {
		return 0.5F;
	}

	protected Item getDropItem() {
		return Items.fish;
	}

	private void dropItemRand(Item index, int par1) {
		EntityItem var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), new ItemStack(index, par1, 0));
		this.worldObj.spawnEntityInWorld(var3);
	}

	protected void dropFewItems(boolean par1, int par2) {
		int var3 = 0;
		var3 = this.rand.nextInt(25);
		var3 += 20;

		for (int var4 = 0; var4 < var3; ++var4) {
			this.dropItemRand(Items.fish, 1);
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

	protected void updateAITick() {
		super.updateAITick();
		if (!this.isDead) {
			if (this.worldObj.rand.nextInt(200) == 1) {
				this.setRevengeTarget((EntityLivingBase)null);
			}

			if (!this.isInWater() && this.worldObj.rand.nextInt(20) == 0) {
				this.closest = 99999;
				this.tx = this.ty = this.tz = 0;

				for (int i = 1; i < 11; i++) {
					int j = i;
					if (i > 4) {
						j = 4;
					}

					if (this.scan_it((int)this.posX, (int)this.posY - 1, (int)this.posZ, i, j, i)) {
						break;
					}

					if (i >= 5) {
						++i;
					}
				}

				if (this.closest < 99999) {
					this.getNavigator().tryMoveToXYZ((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.0D);
				} else {
					if (this.worldObj.rand.nextInt(25) == 1) {
						this.heal(-4.0F);
					}

					if (this.getHealth() <= 0.0F) {
						this.setDead();
						return;
					}
				}
			}

			if (this.isInWater() && this.worldObj.rand.nextInt(50) == 0) {
				this.playSound("splash", 1.0F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
				this.heal(1.0F);
			}

		}
	}

	private int findBuddies() {
		List var5 = this.worldObj.getEntitiesWithinAABB(Whale.class, this.boundingBox.expand(32.0D, 8.0D, 32.0D));
		return var5.size();
	}

	public boolean getCanSpawnHere() {
		if (this.posY < 50.0D) {
			return false;
		} else if (!this.worldObj.isDaytime()) {
			return false;
		} else if (this.worldObj.rand.nextInt(50) != 1) {
			return false;
		} else {
			return this.findBuddies() <= 0;
		}
	}

	protected boolean canDespawn() {
		if (this.isChild()) {
			this.func_110163_bv();
			return false;
		} else {
			return !this.isNoDespawnRequired();
		}
	}

	public EntityAgeable createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}

	public Whale spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new Whale(this.worldObj);
	}

	public boolean isWheat(ItemStack par1ItemStack) {
		return par1ItemStack != null && par1ItemStack.getItem() == Items.fish;
	}

	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() == OreSpawnMain.MyCrystalApple;
	}
}
