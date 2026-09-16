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
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Beaver extends EntityAnimal {
	private float moveSpeed = 0.15F;
	private GenericTargetSorter TargetSorter = null;
	private int closest = 99999;
	private int tx = 0;
	private int ty = 0;
	private int tz = 0;

	public Beaver(World par1World) {
		super(par1World);
		this.setSize(0.6F, 0.8F);
		this.moveSpeed = 0.2F;
		this.fireResistance = 100;
		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 5;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityMob.class, 8.0F, 1.0D, 1.5D));
		this.tasks.addTask(4, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(5, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 1.0D, 1.5D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new MyEntityAIWanderALot(this, 10, 1.0D));
		this.tasks.addTask(8, new EntityAILookIdle(this));
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
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
	}

	public boolean isWood(Block bid) {
		if (bid != Blocks.log && bid != OreSpawnMain.MyDT && bid != OreSpawnMain.MySkyTreeLog) {
			return bid == Blocks.fence || bid == Blocks.fence_gate || bid == Blocks.standing_sign;
		} else {
			return true;
		}
	}

	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
		int found = 0;

		for (int i = -dy; i <= dy; i++) {
			for (int j = -dz; j <= dz; j++) {
				Block bid = this.worldObj.getBlock(x + dx, y + i, z + j);
				if (this.isWood(bid)) {
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
				if (this.isWood(bid)) {
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
				if (this.isWood(bid)) {
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
				if (this.isWood(bid)) {
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
				if (this.isWood(bid)) {
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
				if (this.isWood(bid)) {
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

	private ItemStack dropItemRand(Item index, int par1) {
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), this.posY + 4.0D + (double)this.worldObj.rand.nextInt(4), this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(4) - (double)OreSpawnMain.OreSpawnRand.nextInt(4), is);
		if (var3 != null) {
			this.worldObj.spawnEntityInWorld(var3);
		}

		return is;
	}

	public void breakRecursor(World world, int x, int y, int z, int xf, int yf, int zf, int recursion) {
		int var7 = 1;
		if (recursion <= 200) {
			for (int var9 = -var7; var9 <= var7; ++var9) {
				for (int var10 = -var7; var10 <= var7; ++var10) {
					for (int var11 = -var7; var11 <= var7; ++var11) {
						if ((var9 != 0 || var10 != 0 || var11 != 0) && (x + var9 != xf || y + var10 != yf || z + var11 != zf) && (recursion <= 0 || x + var9 < xf - var7 || x + var9 > xf + var7 || y + var10 < yf - var7 || y + var10 > yf + var7 || z + var11 < zf - var7 || z + var11 > zf + var7)) {
							Block var12 = world.getBlock(x + var9, y + var10, z + var11);
							if (this.isWood(var12)) {
								world.setBlock(x + var9, y + var10, z + var11, Blocks.air, 0, 2);
								this.dropItemRand(Item.getItemFromBlock(var12), 1);
								this.breakRecursor(world, x + var9, y + var10, z + var11, x, y, z, recursion + 1);
							}
						}
					}
				}
			}

		}
	}

	protected void updateAITick() {
		if (!this.isDead) {
			if (this.worldObj.rand.nextInt(200) == 1) {
				this.setRevengeTarget((EntityLivingBase)null);
			}

			if ((this.worldObj.rand.nextInt(30) == 0 && this.getBeaverHealth() < this.mygetMaxHealth() || this.worldObj.rand.nextInt(350) == 1) && OreSpawnMain.PlayNicely == 0) {
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

				int var4 = 0;
				if (this.closest < 99999) {
					this.getNavigator().tryMoveToXYZ((double)this.tx, (double)this.ty, (double)this.tz, 1.0D);
					if (this.closest < 12) {
						if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
							this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.air, 0, 2);
							this.breakRecursor(this.worldObj, this.tx, this.ty, this.tz, this.tx, this.ty, this.tz, var4);
						}

						this.heal(1.0F);
						this.playSound("orespawn:chainsaw", 1.0F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
					}
				}
			}

			if (this.worldObj.rand.nextInt(200) == 1) {
				Beaver buddy = this.findBuddy();
				if (buddy != null) {
					this.getNavigator().tryMoveToXYZ(buddy.posX, buddy.posY, buddy.posZ, 0.5D);
				}
			}

			super.updateAITick();
		}
	}

	private Beaver findBuddy() {
		List var5 = this.worldObj.getEntitiesWithinAABB(Beaver.class, this.boundingBox.expand(16.0D, 6.0D, 16.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		Beaver var4 = null;
		if (var2.hasNext()) {
			var3 = (Entity)var2.next();
			var4 = (Beaver)var3;
			return var4;
		} else {
			return null;
		}
	}

	public boolean isAIEnabled() {
		return true;
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public int mygetMaxHealth() {
		return 15;
	}

	public int getBeaverHealth() {
		return (int)this.getHealth();
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "orespawn:scorpion_hit";
	}

	protected String getDeathSound() {
		return "orespawn:cryo_death";
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected Item getDropItem() {
		return Items.porkchop;
	}

	protected float getSoundPitch() {
		return this.isChild() ? (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.5F : (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F + 1.0F;
	}

	public boolean getCanSpawnHere() {
		if (this.posY < 50.0D) {
			return false;
		} else if (this.posY > (double)100.0F) {
			return false;
		} else {
			Block bid = this.worldObj.getBlock((int)this.posX, (int)this.posY - 1, (int)this.posZ);
			return bid == Blocks.dirt || bid == Blocks.grass || bid == Blocks.tallgrass || bid == Blocks.leaves;
		}
	}

	protected boolean canDespawn() {
		return false;
	}

	public EntityAgeable createChild(EntityAgeable entityageable) {
		return this.spawnBabyAnimal(entityageable);
	}

	public Beaver spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new Beaver(this.worldObj);
	}

	public boolean isWheat(ItemStack par1ItemStack) {
		return par1ItemStack != null && par1ItemStack.getItem() == Items.apple;
	}

	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() == OreSpawnMain.MyCrystalApple;
	}
}
