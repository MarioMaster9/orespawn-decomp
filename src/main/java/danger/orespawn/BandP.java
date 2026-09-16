package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class BandP extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.32F;
	private int whatset = 0;
	private int whatami = 0;
	public ItemStack[] MymainInventory = new ItemStack[100];
	int got_stuff = 0;

	public BandP(World par1World) {
		super(par1World);
		this.setSize(0.75F, 1.75F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 1000;
		this.fireResistance = 2;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAIMoveThroughVillage(this, 0.5D, false));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 16, 0.5D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(3, new EntityAILookIdle(this));
		this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(5, new EntityAIMoveIndoors(this));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.BandP_stats.attack);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) {
			return false;
		} else {
			return this.got_stuff == 0;
		}
	}

	public void onUpdate() {
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (!this.worldObj.isRemote && this.whatset == 0) {
			this.whatset = 1;
			this.whatami = this.worldObj.rand.nextInt(2);
			this.setWhat(this.whatami);
		}

	}

	public int mygetMaxHealth() {
		return OreSpawnMain.BandP_stats.health;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.BandP_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	protected String getLivingSound() {
		return "mob.villager.idle";
	}

	protected String getHurtSound() {
		return "mob.villager.hit";
	}

	protected String getDeathSound() {
		return "mob.villager.death";
	}

	protected float getSoundVolume() {
		return 1.5F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected Item getDropItem() {
		return Items.emerald;
	}

	private ItemStack dropItemRand(Item index, int par1) {
		EntityItem var3 = null;
		if (index == null) {
			return null;
		} else {
			ItemStack is = new ItemStack(index, par1, 0);
			var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), is);
			if (var3 != null) {
				this.worldObj.spawnEntityInWorld(var3);
			}

			return is;
		}
	}

	protected void dropFewItems(boolean par1, int par2) {
		int var4 = 10 + this.worldObj.rand.nextInt(5);

		for (int i = 0; i < var4; i++) {
			this.dropItemRand(Items.emerald, 1);
		}

		if (this.getWhat() == 0) {
			var4 = 2 + this.worldObj.rand.nextInt(3);

			for (int var7 = 0; var7 < var4; ++var7) {
				this.dropItemRand(OreSpawnMain.UraniumNugget, 1);
				this.dropItemRand(OreSpawnMain.TitaniumNugget, 1);
			}
		}

		for (int var8 = 0; var8 < this.MymainInventory.length; ++var8) {
			if (this.MymainInventory[var8] != null && this.MymainInventory[var8].stackSize != 0) {
				ItemStack is = this.dropItemRand(this.MymainInventory[var8].getItem(), this.MymainInventory[var8].stackSize);
				if (this.MymainInventory[var8].stackSize == 1) {
					is.setItemDamage(this.MymainInventory[var8].getItemDamage());
				}
			}
		}

	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		return super.attackEntityAsMob(par1Entity);
	}

	protected void updateAITasks() {
		if (!this.isDead) {
			super.updateAITasks();
			if (this.worldObj.rand.nextInt(12) == 1) {
				EntityLivingBase e = this.findSomethingToAttack();
				if (e != null) {
					this.faceEntity(e, 10.0F, 10.0F);
					if (this.getDistanceSqToEntity(e) < 9.0D) {
						this.attackEntityAsMob(e);
						if (e instanceof EntityPlayer) {
							EntityPlayer p = (EntityPlayer)e;
							int k = -1;
							int kp = -1;

							for (int i = 0; i < this.MymainInventory.length; i++) {
								if (this.MymainInventory[i] == null) {
									k = i;
									break;
								}
							}

							if (k >= 0) {
								for (int var6 = p.inventory.armorInventory.length - 1; var6 >= 0; --var6) {
									if (p.inventory.armorInventory[var6] != null) {
										kp = var6;
										break;
									}
								}

								if (kp >= 0) {
									this.MymainInventory[k] = p.inventory.armorInventory[kp];
									p.inventory.armorInventory[kp] = null;
									++this.got_stuff;
								}

								if (kp < 0) {
									for (int var7 = p.inventory.mainInventory.length - 1; var7 >= 0; --var7) {
										if (p.inventory.mainInventory[var7] != null) {
											kp = var7;
											break;
										}
									}

									if (kp >= 0) {
										this.MymainInventory[k] = p.inventory.mainInventory[kp];
										p.inventory.mainInventory[kp] = null;
										++this.got_stuff;
									}
								}
							}
						}
					} else {
						this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
					}
				}
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
		} else if (!this.getEntitySenses().canSee(par1EntityLiving)) {
			return false;
		} else if (par1EntityLiving instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			return !p.capabilities.isCreativeMode;
		} else if (par1EntityLiving instanceof EntityVillager) {
			return true;
		} else if (par1EntityLiving instanceof Girlfriend) {
			return true;
		} else {
			return par1EntityLiving instanceof Boyfriend;
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0D, 6.0D, 20.0D));
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

	public int getWhat() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public void setWhat(int par1) {
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
						if (s != null && s.equals("Criminal")) {
							return true;
						}
					}
				}
			}
		}

		if (!this.worldObj.isDaytime()) {
			return false;
		} else if (this.posY < 50.0D) {
			return false;
		} else if (this.posY < (double)100.0F) {
			return false;
		} else {
			BandP target = null;
			target = (BandP)this.worldObj.findNearestEntityWithinAABB(BandP.class, this.boundingBox.expand(32.0D, 12.0D, 32.0D), this);
			if (target != null) {
				return false;
			} else {
				EntityVillager target2 = null;
				target2 = (EntityVillager)this.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.boundingBox.expand(36.0D, 12.0D, 36.0D), this);
				if (target2 == null) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		if (this.got_stuff != 0) {
			par1NBTTagCompound.setTag("Inventory", this.writeToNBT(new NBTTagList()));
		}

		par1NBTTagCompound.setInteger("GotStuff", this.got_stuff);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.got_stuff = par1NBTTagCompound.getInteger("GotStuff");
		if (this.got_stuff != 0) {
			NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Inventory", 10);
			this.readFromNBT(nbttaglist);
		}

	}

	public NBTTagList writeToNBT(NBTTagList par1NBTTagList) {
		for (int i = 0; i < this.MymainInventory.length; i++) {
			if (this.MymainInventory[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte)i);
				this.MymainInventory[i].writeToNBT(nbttagcompound);
				par1NBTTagList.appendTag(nbttagcompound);
			}
		}

		return par1NBTTagList;
	}

	public void readFromNBT(NBTTagList par1NBTTagList) {
		this.MymainInventory = new ItemStack[100];

		for (int i = 0; i < par1NBTTagList.tagCount(); i++) {
			NBTTagCompound nbttagcompound = par1NBTTagList.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);
			if (itemstack != null && j >= 0 && j < this.MymainInventory.length) {
				this.MymainInventory[j] = itemstack;
			}
		}

	}
}
