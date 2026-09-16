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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntitySquid;
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
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

public class Kraken extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private ChunkCoordinates currentFlightTarget = null;
	private EntityLivingBase caught = null;
	private int newtarget = 0;
	private int release = 0;
	private int weather_set = 10;
	private int long_enough = 3600;
	private int call_reinforcements = 0;
	private boolean hit_by_player = false;
	private int straight_down = 1;
	private int hurt_timer = 0;

	public Kraken(World par1World) {
		super(par1World);
		if (OreSpawnMain.PlayNicely == 0) {
			this.setSize(4.0F, 15.0F);
		} else {
			this.setSize(1.3333334F, 5.0F);
		}

		this.getNavigator().setAvoidsWater(false);
		this.experienceValue = 500;
		this.fireResistance = 120;
		this.isImmuneToFire = true;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
		this.tasks.addTask(1, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)0.37F);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Kraken_stats.attack);
	}

	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
		this.dataWatcher.addObject(21, OreSpawnMain.PlayNicely);
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

	public int getPlayNicely() {
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.Kraken_stats.health;
	}

	public int getKrakenHealth() {
		return (int)this.getHealth();
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
		return OreSpawnMain.Kraken_stats.defense;
	}

	protected boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
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

	public void onUpdate() {
		super.onUpdate();
		if (!this.isDead) {
			if (this.currentFlightTarget == null) {
				this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)(this.posY - 10.0D), (int)this.posZ);
			} else if (this.posY < (double)this.currentFlightTarget.posY) {
				this.motionY *= 0.72;
			} else {
				this.motionY *= 0.5D;
			}

			if (this.weather_set > 0 && OreSpawnMain.PlayNicely == 0) {
				--this.weather_set;
				if (this.weather_set == 0 && !this.worldObj.isRemote) {
					WorldInfo worldinfo = this.worldObj.getWorldInfo();
					if (!this.worldObj.isRaining()) {
						worldinfo.setRainTime(300);
						worldinfo.setThunderTime(300);
						worldinfo.setRaining(true);
						worldinfo.setThundering(true);
					} else {
						worldinfo.setRainTime(300);
						worldinfo.setThunderTime(300);
					}

					this.weather_set = 100;
				}
			}

		}
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("LongEnough", this.long_enough);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.long_enough = par1NBTTagCompound.getInteger("LongEnough");
	}

	protected String getLivingSound() {
		return this.rand.nextInt(5) == 0 ? "orespawn:kraken_living" : null;
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return "orespawn:alo_death";
	}

	protected float getSoundVolume() {
		return 2.0F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected Item getDropItem() {
		return Items.quartz;
	}

	private ItemStack dropItemRand(Item index, int par1) {
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(8) - (double)OreSpawnMain.OreSpawnRand.nextInt(8), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(8) - (double)OreSpawnMain.OreSpawnRand.nextInt(8), is);
		if (var3 != null) {
			this.worldObj.spawnEntityInWorld(var3);
		}

		return is;
	}

	protected void dropFewItems(boolean par1, int par2) {
		ItemStack is = null;
		this.dropItemRand(OreSpawnMain.MyKrakenTooth, 1);
		this.dropItemRand(Items.item_frame, 1);
		int var5 = 120 + this.worldObj.rand.nextInt(160);

		for (int var4 = 0; var4 < var5; ++var4) {
			this.dropItemRand(Items.dye, 1);
		}

		int i = 5 + this.worldObj.rand.nextInt(10);

		for (int var9 = 0; var9 < i; ++var9) {
			int var3 = this.worldObj.rand.nextInt(53);
			switch (var3) {
				case 0:
					is = this.dropItemRand(OreSpawnMain.MyUltimateSword, 1);
					break;
				case 1:
					is = this.dropItemRand(Items.diamond, 1);
					break;
				case 2:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.diamond_block), 1);
					break;
				case 3:
					is = this.dropItemRand(Items.diamond_sword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 4:
					is = this.dropItemRand(Items.diamond_shovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 5:
					is = this.dropItemRand(Items.diamond_pickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 6:
					is = this.dropItemRand(Items.diamond_axe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 7:
					is = this.dropItemRand(Items.diamond_hoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 8:
					is = this.dropItemRand(Items.diamond_helmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(2));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 9:
					is = this.dropItemRand(Items.diamond_chestplate, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 10:
					is = this.dropItemRand(Items.diamond_leggings, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 11:
					is = this.dropItemRand(Items.diamond_boots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 12:
					is = this.dropItemRand(OreSpawnMain.MyUltimateBow, 1);
					break;
				case 13:
					is = this.dropItemRand(OreSpawnMain.MyUltimateAxe, 1);
					break;
				case 14:
					is = this.dropItemRand(Items.iron_ingot, 1);
					break;
				case 15:
					is = this.dropItemRand(OreSpawnMain.MyUltimatePickaxe, 1);
					break;
				case 16:
					is = this.dropItemRand(Items.iron_sword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 17:
					is = this.dropItemRand(Items.iron_shovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 18:
					is = this.dropItemRand(Items.iron_pickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 19:
					is = this.dropItemRand(Items.iron_axe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 20:
					is = this.dropItemRand(Items.iron_hoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 21:
					is = this.dropItemRand(Items.iron_helmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 22:
					is = this.dropItemRand(Items.iron_chestplate, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 23:
					is = this.dropItemRand(Items.iron_leggings, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 24:
					is = this.dropItemRand(Items.iron_boots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 25:
					is = this.dropItemRand(OreSpawnMain.MyUltimateShovel, 1);
					break;
				case 26:
					this.dropItemRand(Item.getItemFromBlock(Blocks.iron_block), 1);
					break;
				case 27:
					is = this.dropItemRand(Items.gold_nugget, 1);
					break;
				case 28:
					is = this.dropItemRand(Items.gold_ingot, 1);
					break;
				case 29:
					is = this.dropItemRand(Items.golden_carrot, 1);
					break;
				case 30:
					is = this.dropItemRand(Items.golden_sword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 31:
					is = this.dropItemRand(Items.golden_shovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 32:
					is = this.dropItemRand(Items.golden_pickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 33:
					is = this.dropItemRand(Items.golden_axe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 34:
					is = this.dropItemRand(Items.golden_hoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 35:
					is = this.dropItemRand(Items.golden_helmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 36:
					is = this.dropItemRand(Items.golden_chestplate, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 37:
					is = this.dropItemRand(Items.golden_leggings, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 38:
					is = this.dropItemRand(Items.golden_boots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 39:
					this.dropItemRand(Items.golden_apple, 1);
					break;
				case 40:
					this.dropItemRand(Item.getItemFromBlock(Blocks.gold_block), 1);
					break;
				case 41:
					EntityItem var33 = null;
					is = new ItemStack(Items.golden_apple, 1, 1);
					var33 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(3) - (double)OreSpawnMain.OreSpawnRand.nextInt(3), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(3) - (double)OreSpawnMain.OreSpawnRand.nextInt(3), is);
					if (var33 != null) {
						this.worldObj.spawnEntityInWorld(var33);
					}
					break;
				case 42:
					is = this.dropItemRand(OreSpawnMain.MyExperienceSword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 43:
					is = this.dropItemRand(OreSpawnMain.ExperienceHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 44:
					is = this.dropItemRand(OreSpawnMain.ExperienceBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 45:
					is = this.dropItemRand(OreSpawnMain.ExperienceLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 46:
					is = this.dropItemRand(OreSpawnMain.ExperienceBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}
					break;
				case 47:
					is = this.dropItemRand(OreSpawnMain.MyAmethystSword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 48:
					is = this.dropItemRand(OreSpawnMain.MyAmethystShovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 49:
					is = this.dropItemRand(OreSpawnMain.MyAmethystPickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 50:
					is = this.dropItemRand(OreSpawnMain.MyAmethystAxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 51:
					is = this.dropItemRand(OreSpawnMain.MyAmethystHoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) {
						is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					}

					if (this.worldObj.rand.nextInt(6) == 1) {
						is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					}
					break;
				case 52:
					is = this.dropItemRand(Item.getItemFromBlock(OreSpawnMain.MyBlockAmethystBlock), 1);
			}
		}

	}

	public boolean interact(EntityPlayer par1EntityPlayer) {
		return false;
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) {
			return false;
		} else if (this.long_enough <= 0) {
			return true;
		} else if (this.posY > (double)150.0F && this.getHealth() < (float)(this.mygetMaxHealth() / 2)) {
			return true;
		} else if (this.posY > 180.0D && this.long_enough <= 0) {
			this.setDead();
			return true;
		} else {
			return false;
		}
	}

	public boolean canSeeTarget(double pX, double pY, double pZ) {
		return this.worldObj.rayTraceBlocks(Vec3.createVectorHelper(this.posX, this.posY + 0.75D, this.posZ), Vec3.createVectorHelper(pX, pY, pZ), false) == null;
	}

	protected void updateAITasks() {
		int xdir = 1;
		int zdir = 1;
		int keep_trying = 50;
		if (!this.isDead) {
			super.updateAITasks();
			if (this.hurt_timer > 0) {
				--this.hurt_timer;
			}

			if (this.long_enough > 0) {
				--this.long_enough;
			}

			this.dataWatcher.updateObject(21, OreSpawnMain.PlayNicely);
			if (this.worldObj.rand.nextInt(400) == 1 && OreSpawnMain.PlayNicely == 0) {
				this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY - 16.0D, this.posZ));
			}

			if (this.currentFlightTarget == null) {
				this.currentFlightTarget = new ChunkCoordinates((int)this.posX, (int)this.posY, (int)this.posZ);
			}

			if (this.newtarget == 0 && this.rand.nextInt(250) != 1 && !(this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 9.1F)) {
				if (this.caught == null && this.worldObj.rand.nextInt(8) == 1 && OreSpawnMain.PlayNicely == 0) {
					EntityPlayer target = null;
					target = (EntityPlayer)this.worldObj.findNearestEntityWithinAABB(EntityPlayer.class, this.boundingBox.expand(25.0D, (double)40.0F, 25.0D), this);
					if (target != null) {
						if (!target.capabilities.isCreativeMode) {
							if (this.getEntitySenses().canSee(target)) {
								this.currentFlightTarget.set((int)target.posX, (int)target.posY + 15, (int)target.posZ);
								this.attackWithSomething(target);
							}
						} else {
							target = null;
						}
					}

					if (target == null && this.worldObj.rand.nextInt(2) == 0) {
						EntityLivingBase e = null;
						e = this.findSomethingToAttack();
						if (e != null) {
							this.currentFlightTarget.set((int)e.posX, (int)e.posY + 15, (int)e.posZ);
							this.attackWithSomething(e);
						}
					}
				}
			} else {
				this.newtarget = 0;

				int ground_dist;
				for (ground_dist = 0; ground_dist < 31; ++ground_dist) {
					Block bid = this.worldObj.getBlock((int)this.posX, (int)this.posY - ground_dist, (int)this.posZ);
					if (bid != Blocks.air) {
						this.straight_down = 0;
						break;
					}
				}

				ground_dist = 20 - ground_dist;

				for (Block bid = Blocks.stone; bid != Blocks.air && keep_trying != 0; --keep_trying) {
					zdir = this.worldObj.rand.nextInt(6) + 12;
					xdir = this.worldObj.rand.nextInt(6) + 12;
					if (this.worldObj.rand.nextInt(2) == 0) {
						zdir = -zdir;
					}

					if (this.worldObj.rand.nextInt(2) == 0) {
						xdir = -xdir;
					}

					if (this.straight_down != 0) {
						xdir = 0;
						zdir = 0;
					}

					this.currentFlightTarget.set((int)this.posX + xdir, (int)this.posY + ground_dist + this.rand.nextInt(9) - 6, (int)this.posZ + zdir);
					bid = this.worldObj.getBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ);
					if (bid == Blocks.air && !this.canSeeTarget((double)this.currentFlightTarget.posX, (double)this.currentFlightTarget.posY, (double)this.currentFlightTarget.posZ)) {
						bid = Blocks.stone;
					}
				}

				if (this.long_enough <= 0 || this.posY < 200.0D && this.getHealth() < (float)(this.mygetMaxHealth() / 4)) {
					this.currentFlightTarget.set(this.currentFlightTarget.posX, this.currentFlightTarget.posY + 30, this.currentFlightTarget.posZ);
					if (this.hit_by_player && this.call_reinforcements == 0 && this.getHealth() < (float)(this.mygetMaxHealth() / 8) && this.posY > (double)130.0F) {
						this.call_reinforcements = 1;

						for (int i = 0; i < 10; i++) {
							EntityCreature newent = (EntityCreature)spawnCreature(this.worldObj, "The Kraken", this.posX + (double)this.worldObj.rand.nextInt(10) - (double)this.worldObj.rand.nextInt(10), (double)170.0F, this.posZ + (double)this.worldObj.rand.nextInt(10) - (double)this.worldObj.rand.nextInt(10));
						}
					}
				}
			}

			if (this.caught != null) {
				if (!this.caught.isDead) {
					this.currentFlightTarget.set((int)this.posX, 200, (int)this.posZ);
					if (this.posY > (double)190.0F) {
						this.release = 1;
					}

					this.caught.motionX = this.motionX;
					this.caught.motionZ = this.motionZ;
					this.caught.motionY = this.motionY;
					this.caught.posX = this.posX;
					if (this.posY - this.caught.posY > 16.0D) {
						this.caught.motionY += 0.25D;
					}

					this.caught.posY = this.posY - 15.0D;
					this.caught.posZ = this.posZ;
					this.caught.rotationYaw = this.rotationYaw;
					if (this.worldObj.rand.nextInt(50) == 1) {
						this.attackEntityAsMob(this.caught);
					}

					if (this.release != 0 || this.worldObj.rand.nextInt(250) == 1) {
						this.caught = null;
						this.newtarget = 1;
						this.release = 0;
						this.setAttacking(0);
					}
				} else {
					this.caught = null;
					this.newtarget = 1;
					this.release = 0;
					this.setAttacking(0);
				}
			}

			double var1 = (double)this.currentFlightTarget.posX + 0.3 - this.posX;
			double var3 = (double)this.currentFlightTarget.posY + 0.1 - this.posY;
			double var5 = (double)this.currentFlightTarget.posZ + 0.3 - this.posZ;
			this.motionX += (Math.signum(var1) * 0.45 - this.motionX) * 0.15;
			this.motionY += (Math.signum(var3) * 0.70999 - this.motionY) * 0.202;
			this.motionZ += (Math.signum(var5) * 0.45 - this.motionZ) * 0.15;
			float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
			this.moveForward = 0.4F;
			if (Math.abs(this.motionX) + Math.abs(this.motionZ) < 0.15) {
				var8 = 0.0F;
			}

			this.rotationYaw += var8 / 5.0F;
			double obstruction_factor = 0.0D;
			double dx = 0.0D;
			double dz = 0.0D;
			int dist = 10;

			for (int k = -20; k < 18; k += 2) {
				for (int i = 1; i < dist; i += 2) {
					dx = (double)i * Math.cos(Math.toRadians((double)(this.rotationYaw + 90.0F)));
					dz = (double)i * Math.sin(Math.toRadians((double)(this.rotationYaw + 90.0F)));
					Block bid = this.worldObj.getBlock((int)(this.posX + dx), (int)this.posY + k, (int)(this.posZ + dz));
					if (bid != Blocks.air) {
						obstruction_factor += 0.1;
					}
				}
			}

			this.motionY += obstruction_factor * 0.08;
			this.posY += obstruction_factor * 0.08;
			if (this.posY > 256.0D && !this.isNoDespawnRequired()) {
				this.setDead();
			}

		}
	}

	private void attackWithSomething(EntityLivingBase par1) {
		if (this.caught == null) {
			double dist = (this.posX - par1.posX) * (this.posX - par1.posX);
			dist += (this.posZ - par1.posZ) * (this.posZ - par1.posZ);
			dist += (this.posY - par1.posY - 15.0D) * (this.posY - par1.posY - 15.0D);
			if (dist < (double)30.0F) {
				this.caught = par1;
				this.release = 0;
				this.setAttacking(1);
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
			} else if (par1EntityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)par1EntityLiving;
				if (p.capabilities.isCreativeMode) {
					return false;
				} else {
					return !p.capabilities.isFlying;
				}
			} else if (!par1EntityLiving.onGround && !par1EntityLiving.isInWater()) {
				return false;
			} else if (par1EntityLiving instanceof EntitySquid) {
				return false;
			} else if (par1EntityLiving instanceof AttackSquid) {
				return false;
			} else if (par1EntityLiving instanceof Kraken) {
				return false;
			} else if (par1EntityLiving instanceof Spyro) {
				return false;
			} else if (par1EntityLiving instanceof Dragon) {
				Dragon c = (Dragon)par1EntityLiving;
				return c.riddenByEntity == null;
			} else if (par1EntityLiving instanceof Cephadrome) {
				Cephadrome c = (Cephadrome)par1EntityLiving;
				return c.riddenByEntity == null;
			} else if (par1EntityLiving instanceof Leon) {
				Leon c = (Leon)par1EntityLiving;
				return c.riddenByEntity == null;
			} else if (par1EntityLiving instanceof ThePrinceTeen) {
				ThePrinceTeen c = (ThePrinceTeen)par1EntityLiving;
				return c.riddenByEntity == null;
			} else if (par1EntityLiving instanceof ThePrinceAdult) {
				ThePrinceAdult c = (ThePrinceAdult)par1EntityLiving;
				return c.riddenByEntity == null;
			} else if (par1EntityLiving instanceof EntityChicken) {
				return false;
			} else if (par1EntityLiving instanceof Chipmunk) {
				return false;
			} else if (par1EntityLiving instanceof StinkBug) {
				return false;
			} else {
				return !(par1EntityLiving instanceof Mothra);
			}
		}
	}

	private EntityLivingBase findSomethingToAttack() {
		if (OreSpawnMain.PlayNicely != 0) {
			return null;
		} else {
			List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0D, (double)40.0F, 20.0D));
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

	public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		Entity e = par1DamageSource.getEntity();
		boolean ret = false;
		if (this.currentFlightTarget != null && e != null && e instanceof EntityPlayer && this.getHealth() > (float)(this.mygetMaxHealth() / 4)) {
			this.hit_by_player = true;
			this.currentFlightTarget.set((int)e.posX, (int)e.posY + 15, (int)e.posZ);
		}

		if (this.hurt_timer > 0) {
			return false;
		} else {
			this.hurt_timer = 30;
			ret = super.attackEntityFrom(par1DamageSource, par2);
			if (this.worldObj.rand.nextInt(2) == 1) {
				this.release = 1;
			}

			return ret;
		}
	}

	public final int getAttacking() {
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1) {
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	protected void fall(float par1) {
	}

	protected void updateFallState(double par1, boolean par3) {
	}

	public boolean getCanSpawnHere() {
		if (this.posY < 50.0D) {
			return false;
		} else {
			for (int k = -1; k < 2; k++) {
				for (int j = -1; j < 1; j++) {
					for (int i = 1; i < 6; i++) {
						Block bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						if (bid != Blocks.air && bid != Blocks.tallgrass) {
							return false;
						}
					}
				}
			}

			return true;
		}
	}
}
