package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class Cryolophosaurus extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.25F;

	public Cryolophosaurus(World par1World) {
		super(par1World);
		this.setSize(0.75F, 0.75F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 10;
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
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Cryolophosaurus_stats.attack);
	}

	protected void entityInit() {
		super.entityInit();
	}

	protected boolean canDespawn() {
		return !this.isNoDespawnRequired();
	}

	public int mygetMaxHealth() {
		return OreSpawnMain.Cryolophosaurus_stats.health;
	}

	public int getTotalArmorValue() {
		return OreSpawnMain.Cryolophosaurus_stats.defense;
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

	protected String getLivingSound() {
		return this.rand.nextInt(6) == 0 ? "orespawn:cryo_living" : null;
	}

	protected String getHurtSound() {
		return "orespawn:cryo_hurt";
	}

	protected String getDeathSound() {
		return "orespawn:cryo_death";
	}

	protected float getSoundVolume() {
		return 0.75F;
	}

	protected float getSoundPitch() {
		return 1.0F;
	}

	protected Item getDropItem() {
		int i = this.worldObj.rand.nextInt(10);
		if (i == 0) {
			return Items.chicken;
		} else if (i == 1) {
			return OreSpawnMain.UraniumNugget;
		} else {
			return i == 2 ? OreSpawnMain.TitaniumNugget : null;
		}
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
					this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
					if (this.getDistanceSqToEntity(e) < 5.0D && (this.rand.nextInt(12) == 0 || this.rand.nextInt(14) == 1)) {
						this.attackEntityAsMob(e);
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
		} else if (par1EntityLiving instanceof Alosaurus) {
			return false;
		} else if (par1EntityLiving instanceof TRex) {
			return false;
		} else if (par1EntityLiving instanceof Cryolophosaurus) {
			return false;
		} else if (par1EntityLiving instanceof Ghost) {
			return false;
		} else if (par1EntityLiving instanceof GhostSkelly) {
			return false;
		} else if (par1EntityLiving instanceof CaveFisher) {
			return false;
		} else if (par1EntityLiving instanceof GammaMetroid) {
			return false;
		} else if (par1EntityLiving instanceof EntityButterfly) {
			return false;
		} else if (par1EntityLiving instanceof Firefly) {
			return false;
		} else if (par1EntityLiving instanceof EntityMosquito) {
			return false;
		} else if (par1EntityLiving instanceof RockBase) {
			return false;
		} else {
			if (par1EntityLiving instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)par1EntityLiving;
				if (p.capabilities.isCreativeMode) {
					return false;
				}
			}

			return true;
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

	public boolean getCanSpawnHere() {
		if (!this.isValidLightLevel()) {
			return false;
		} else {
			return !this.worldObj.isDaytime() || !(this.posY > 50.0D);
		}
	}
}
