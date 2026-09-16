package danger.orespawn;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class Termite extends EntityAnt {
	int attack_delay = 20;
	private int closest = 99999;
	private int tx = 0;
	private int ty = 0;
	private int tz = 0;

	public Termite(World par1World) {
		super(par1World);
		this.setSize(0.2F, 0.2F);
		this.moveSpeed = (double)0.2F;
		this.experienceValue = 1;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAIPanic(this, (double)1.4F));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 8, 1.0D));
		if (OreSpawnMain.PlayNicely == 0) {
			this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 6, true));
		}

	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)2.0F);
	}

	public int mygetMaxHealth() {
		return 5;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		if (OreSpawnMain.OreSpawnRand.nextInt(15) != 0) {
			return false;
		} else if (this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			return false;
		} else {
			boolean var4 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
			return var4;
		}
	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		if (par1EntityPlayer == null) {
			return false;
		} else if (!(par1EntityPlayer instanceof EntityPlayerMP)) {
			return false;
		} else {
			ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
			if (var2 != null && var2.stackSize <= 0) {
				par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
				var2 = null;
			}

			if (var2 != null) {
				par1EntityPlayer.addChatComponentMessage(new ChatComponentText("Empty your hand!"));
				return false;
			} else {
				if (par1EntityPlayer.dimension != OreSpawnMain.DimensionID5) {
					for (int i = 0; i < par1EntityPlayer.inventory.mainInventory.length; i++) {
						if (par1EntityPlayer.inventory.mainInventory[i] != null) {
							par1EntityPlayer.addChatComponentMessage(new ChatComponentText("Empty your inventory!"));
							return false;
						}
					}

					for (int var4 = 0; var4 < par1EntityPlayer.inventory.armorInventory.length; ++var4) {
						if (par1EntityPlayer.inventory.armorInventory[var4] != null) {
							par1EntityPlayer.addChatComponentMessage(new ChatComponentText("Take off your armor!"));
							return false;
						}
					}

					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, OreSpawnMain.DimensionID5, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(OreSpawnMain.DimensionID5), OreSpawnMain.DimensionID5, this.worldObj));
				} else {
					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, 0, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(0), 0, this.worldObj));
				}

				return true;
			}
		}
	}

	public void onUpdate() {
		super.onUpdate();
		if (!this.isDead) {
			if (this.attack_delay > 0) {
				--this.attack_delay;
			}

			if (this.attack_delay <= 0) {
				this.attack_delay = 20;
				if (this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL) {
					EntityLivingBase e = this.worldObj.getClosestVulnerablePlayerToEntity(this, 1.5D);
					if (e != null) {
						this.attackEntityAsMob(e);
					}

				}
			}
		}
	}

	public boolean isWood(Block bid) {
		if (bid != Blocks.fence && bid != Blocks.fence_gate && bid != Blocks.planks && bid != Blocks.wooden_slab) {
			if (bid != Blocks.double_wooden_slab && bid != Blocks.bed && bid != Blocks.crafting_table) {
				if (bid != Blocks.standing_sign && bid != Blocks.bookshelf && bid != Blocks.wooden_door && bid != Blocks.wooden_pressure_plate) {
					if (bid != Blocks.birch_stairs && bid != Blocks.oak_stairs && bid != Blocks.jungle_stairs && bid != Blocks.spruce_stairs) {
						return bid == OreSpawnMain.CrystalPlanksBlock;
					} else {
						return true;
					}
				} else {
					return true;
				}
			} else {
				return true;
			}
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

	public void updateAITick() {
		if (!this.isDead) {
			if (this.worldObj.rand.nextInt(200) == 1) {
				this.setRevengeTarget((EntityLivingBase)null);
			}

			if (this.worldObj.rand.nextInt(200) == 1 && OreSpawnMain.PlayNicely == 0) {
				this.closest = 99999;
				this.tx = this.ty = this.tz = 0;

				for (int i = 1; i < 8; i++) {
					int j = i;
					if (i > 4) {
						j = 4;
					}

					if (this.scan_it((int)this.posX, (int)this.posY + 1, (int)this.posZ, i, j, i)) {
						break;
					}

					if (i >= 5) {
						++i;
					}
				}

				boolean var3 = false;
				if (this.closest < 99999) {
					this.getNavigator().tryMoveToXYZ((double)this.tx, (double)this.ty, (double)this.tz, 1.0D);
					if (this.closest < 6) {
						if (this.worldObj.rand.nextInt(3) != 0) {
							if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
								this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.dirt, 0, 2);
							}

							if (this.findBuddies() < 10) {
								spawnCreature(this.worldObj, "Termite", this.posX + 0.1D, this.posY + 0.1D, this.posZ + 0.1D);
							}
						} else {
							if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
								this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.air, 0, 2);
							}

							if (this.findBuddies() < 10) {
								spawnCreature(this.worldObj, "Termite", (double)((float)this.tx + 0.1F), (double)((float)this.ty + 0.1F), (double)((float)this.tz + 0.1F));
							}
						}

						this.heal(1.0F);
					}
				}
			}

			super.updateAITick();
		}
	}

	private int findBuddies() {
		List var5 = this.worldObj.getEntitiesWithinAABB(Termite.class, this.boundingBox.expand((double)3.0F, 3.0D, 3.0D));
		return var5.size();
	}

	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
		Entity var8 = null;
		var8 = EntityList.createEntityByName(par1, par0World);
		if (var8 != null) {
			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
			par0World.spawnEntityInWorld(var8);
		}

		return var8;
	}
}
