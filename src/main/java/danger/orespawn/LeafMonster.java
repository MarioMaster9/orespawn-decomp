package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;























public class LeafMonster extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.25F;


	public LeafMonster(World par1World)
	{
		super(par1World);
		this.setSize(1.0F, 2.5F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 5;
		this.fireResistance = 0;
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, (double)1.35F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.TargetSorter = new GenericTargetSorter(this);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.LeafMonster_stats.attack);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
	}

	public final int getAttacking()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1)
	{
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.LeafMonster_stats.health;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.LeafMonster_stats.defense;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}


	protected void fall(float par1)
	{
		float i = (float)MathHelper.ceiling_float_int(par1 - 3.0F);
		
		if (i > 0.0F)
		{
			if (i > 2.0F)
			{
				this.playSound("damage.fallbig", 1.0F, 1.0F);
				i = 2.0F;
			}
			else
			{
				this.playSound("damage.fallsmall", 1.0F, 1.0F);
			}

			this.attackEntityFrom(DamageSource.fall, i);
		}
		
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		int unused1, unused2;
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		if (this.getAttacking() == 0)
		{
			int px = (int)this.posX;
			int py = (int)this.posY;
			int pz = (int)this.posZ;
			this.posX = (double)px;
			this.posY = (double)py;
			this.posZ = (double)pz;
			if (this.posX > 0.0D) this.posX += 0.5D;
			if (this.posZ > 0.0D) this.posZ += 0.5D;
			if (this.posX < 0.0D) this.posX -= 0.5D;
			if (this.posZ < 0.0D) this.posZ -= 0.5D;
			this.rotationPitch = 0.0F;
			px = (int)this.rotationYawHead;
			px /= 90;
			this.rotationYaw = this.rotationYawHead = (float)(px * 90);
		}
	}



    /**
     * Returns the sound this mob makes while it's alive.
     */
	protected String getLivingSound()
	{
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:leaves_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:leaves_death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.65F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}






	protected Item getDropItem()
	{
		int i = this.worldObj.rand.nextInt(3);
		if (i == 0) return Item.getItemFromBlock(Blocks.log);
		if (i == 1) return Item.getItemFromBlock(Blocks.leaves);
		return Items.rotten_flesh;
	}


	protected void updateAITasks()
	{
		super.updateAITasks();
		if (this.isDead) return;
		if (this.worldObj.rand.nextInt(100) == 1) this.setRevengeTarget(null);
		if (this.worldObj.rand.nextInt(4) == 1) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null) {
				this.faceEntity(e, 10.0F, 10.0F);
				this.setAttacking(1);
				this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
				if (this.getDistanceSqToEntity(e) < 5.0D)
				{
					if (this.rand.nextInt(8) == 0 || this.rand.nextInt(10) == 1)
					{
						this.attackEntityAsMob(e);
					}
				}
			} else {
				this.setAttacking(0);
			}
		}
	}





	private boolean isSuitableTarget(EntityLivingBase par1EntityLiving, boolean par2)
	{
		if (par1EntityLiving == null)
		{
			return false;
		}
		if (par1EntityLiving == this)
		{
			return false;
		}
		if (!par1EntityLiving.isEntityAlive())
		{
			return false;
		}
		if (!this.getEntitySenses().canSee(par1EntityLiving))
		{
			
			return false;
		}
		if (par1EntityLiving instanceof EntityAnt)
		{
			return true;
		}
		if (par1EntityLiving instanceof EntityButterfly)
		{
			return true;
		}
		if (par1EntityLiving instanceof EntityLunaMoth)
		{
			return true;
		}
		if (par1EntityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (!p.capabilities.isCreativeMode) {
				return true;
			}
		}

		return false;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand((double)4.0F, 6.0D, 4.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		Entity var3 = null;
		EntityLivingBase var4 = null;

		while (var2.hasNext())
		{
			var3 = (Entity)var2.next();
			var4 = (EntityLivingBase)var3;
			
			if (this.isSuitableTarget(var4, false))
			{
				return var4;
			}
		}
		return null;
	}


	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		Block bid;
		int i, j, k;
		
		for (k = -3; k < 3; k++)
		{
			for (j = -3; j < 3; j++)
			{
				for (i = 0; i < 5; i++)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null) {
							if (s.equals("Leaf Monster")) return true;
						}
					}
				}
			}
		}
		if (!this.isValidLightLevel()) return false;
		if (this.worldObj.isDaytime()) return false;
		if (this.worldObj.provider.dimensionId == OreSpawnMain.DimensionID4) {
			if (this.posY > 20.0D) return false;
		}
		else if (this.posY < 50.0D) return false;

		if (this.findBuddies() > 4) return false;
		return true;
	}

	private int findBuddies()
	{
		List var5 = this.worldObj.getEntitiesWithinAABB(LeafMonster.class, this.boundingBox.expand(20.0D, 10.0D, 20.0D));
		return var5.size();
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		return true;
	}
}
