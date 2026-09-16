package danger.orespawn;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityRedAnt extends EntityAnt {
	int attack_delay = 20;

	public EntityRedAnt(World par1World) {
		super(par1World);
		this.setSize(0.2F, 0.2F);
		this.moveSpeed = (double)0.2F;
		this.experienceValue = 1;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAIPanic(this, (double)1.4F));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 10, 1.0D));
		if (OreSpawnMain.PlayNicely == 0) {
			this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 4, true));
		}

	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
	}

	public int mygetMaxHealth() {
		return 2;
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
				return false;
			} else {
				if (par1EntityPlayer.dimension != OreSpawnMain.DimensionID2) {
					MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)par1EntityPlayer, OreSpawnMain.DimensionID2, new OreSpawnTeleporter(MinecraftServer.getServer().worldServerForDimension(OreSpawnMain.DimensionID2), OreSpawnMain.DimensionID2, this.worldObj));
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
					if (OreSpawnMain.PlayNicely == 0) {
						EntityLivingBase e = this.worldObj.getClosestVulnerablePlayerToEntity(this, 1.5D);
						if (e != null) {
							this.attackEntityAsMob(e);
						}

					}
				}
			}
		}
	}
}
