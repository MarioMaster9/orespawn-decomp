package danger.orespawn;

import java.util.Iterator;
import java.util.List;
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
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class StinkBug extends EntityAnimal {
	private float moveSpeed = 0.15F;

	public StinkBug(World par1World) {
		super(par1World);
		this.setSize(0.55F, 0.55F);
		this.fireResistance = 10;
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 2;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(4, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(5, new EntityAIAvoidEntity(this, EntityPlayer.class, 4.0F, 1.0D, (double)1.4F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new MyEntityAIWanderALot(this, 10, 1.0D));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.tasks.addTask(10, new EntityAIMoveIndoors(this));
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
	}

	protected void updateAITick() {
		if (!this.isDead) {
			if (this.worldObj.rand.nextInt(200) == 1) {
				this.setRevengeTarget((EntityLivingBase)null);
			}

			super.updateAITick();
		}
	}

	public boolean isAIEnabled() {
		return true;
	}

	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		boolean ret = false;
		if (this.isDead) {
			return false;
		} else {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			if (this.getHealth() <= 0.0F || this.isDead) {
				AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(this.posX - 8.0D, this.posY - 5.0D, this.posZ - 8.0D, this.posX + 8.0D, this.posY + 10.0D, this.posZ + 8.0D);
				List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bb);
				Iterator var2 = var5.iterator();
				EntityLivingBase var3 = null;

				while (var2.hasNext()) {
					var3 = (EntityLivingBase)var2.next();
					if (var3 != null) {
						var3.addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 0));
					}
				}
			}

			return ret;
		}
	}

	public boolean canBreatheUnderwater() {
		return false;
	}

	public int mygetMaxHealth() {
		return 5;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return "orespawn:fart";
	}

	protected float getSoundVolume() {
		return 1.0F;
	}

	protected Item getDropItem() {
		return OreSpawnMain.MyDeadStinkBug;
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
						if (s != null && s.equals("Stink Bug")) {
							return true;
						}
					}
				}
			}
		}

		if (this.posY < 50.0D) {
			return false;
		} else {
			return true;
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

	public StinkBug spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
		return new StinkBug(this.worldObj);
	}

	public boolean isWheat(ItemStack par1ItemStack) {
		return par1ItemStack != null && par1ItemStack.getItem() == Items.fish;
	}

	public boolean isBreedingItem(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() == OreSpawnMain.MyCrystalApple;
	}
}
