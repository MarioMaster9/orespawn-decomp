package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;































public class Triffid extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private int hurt_timer = 0;
	private float moveSpeed = 0.13F;

	public Triffid(World par1World)
	{
		super(par1World);
		
		this.setSize(2.0F, 4.0F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 50;
		this.fireResistance = 75;
		this.isImmuneToFire = false;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
		this.tasks.addTask(0, new EntityAISwimming(this));
		
		
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}


	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
		this.dataWatcher.addObject(21, (byte)0);
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

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Triffid_stats.attack);
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		super.onUpdate();
		
		
		if (this.worldObj.rand.nextInt(100) == 1)
		{
			Block bid;
			int k;
			int ix = (int)this.posX;
			int iz = (int)this.posZ;
			
			for (k = -5; k <= 5; k++)
			{
				bid = this.worldObj.getBlock((int)this.posX, (int)this.posY - 1, (int)this.posZ + k);
				if (bid != Blocks.air) {
					if (k < 0) iz--;
					if (k > 0) iz++;
				}
			}
			for (k = -5; k <= 5; k++)
			{
				bid = this.worldObj.getBlock((int)this.posX + k, (int)this.posY - 1, (int)this.posZ);
				if (bid != Blocks.air) {
					if (k < 0) ix--;
					if (k > 0) ix++;
				}
			}
			this.getNavigator().tryMoveToXYZ((double)ix, this.posY, (double)iz, 1.0D);
		}

		if (this.hurt_timer <= 0) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null)
			{
				this.rotationYaw = (float)Math.toDegrees(Math.atan2(e.posZ - this.posZ, e.posX - this.posX)) - 90.0F;
				while (this.rotationYaw < 0.0F) this.rotationYaw += 360.0F;
			}
		}
		
	}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.Triffid_stats.health;
	}



	public RenderInfo getRenderInfo()
	{
		return this.renderdata;
	}

	public void setRenderInfo(RenderInfo r)
	{
		this.renderdata.rf1 = r.rf1;
		this.renderdata.rf2 = r.rf2;
		this.renderdata.rf3 = r.rf3;
		this.renderdata.rf4 = r.rf4;
		this.renderdata.ri1 = r.ri1;
		this.renderdata.ri2 = r.ri2;
		this.renderdata.ri3 = r.ri3;
		this.renderdata.ri4 = r.ri4;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.Triffid_stats.defense;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	protected boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (!this.worldObj.isRemote && this.hurt_timer > 0) {
			this.motionX = this.motionZ = 0.0D;
		}
	}



	public int getTriffidHealth()
	{
		return (int)this.getHealth();
	}



	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "orespawn:triffid_living";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:triffid_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:triffid_dead";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.75F;
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
		if (i == 0) return Items.gold_nugget;
		return null;
	}

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(3) - (double)OreSpawnMain.OreSpawnRand.nextInt(3), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(3) - (double)OreSpawnMain.OreSpawnRand.nextInt(3), is);
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	protected void dropFewItems(boolean par1, int par2)
	{
		int unused1, var4, i;
		
		i = 4 + this.worldObj.rand.nextInt(6);
		for (var4 = 0; var4 < i; var4++) {
			this.dropItemRand(OreSpawnMain.GreenGoo, 1);
		}
		this.dropItemRand(Items.item_frame, 1);
		
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean ret = super.attackEntityAsMob(par1Entity);
		return ret;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		
		if (this.hurt_timer > 0 || this.getOpenClosed() == 0) {
			this.hurt_timer = 300;
			this.setAttacking(0);
			return false;
		} else {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			this.hurt_timer = 300;
			this.setOpenClosed(0);
			this.setAttacking(0);
			return ret;
		}
	}

	protected void updateAITasks()
	{
		if (this.isDead) return;
		
		super.updateAITasks();
		
		if (this.hurt_timer > 0) {
			this.hurt_timer--;
			this.setFire(0);
			this.setOpenClosed(0);
		}
		
		if (this.worldObj.rand.nextInt(250) == 1) {
			if (this.getHealth() < (float)this.mygetMaxHealth())
			{
				this.heal(1.0F);
			}
		}
		
		if (this.worldObj.rand.nextInt(80) == 2) {
			if (this.hurt_timer <= 0) {
				if (this.worldObj.rand.nextInt(8) == 1) {
					this.setOpenClosed(1);
				} else {
					this.setOpenClosed(0);
				}
			}
		}
		
		if (this.worldObj.rand.nextInt(10) == 1 && this.hurt_timer <= 0) {
			EntityLivingBase e = this.findSomethingToAttack();
			if (e != null) {
				this.setOpenClosed(1);
				if (this.getDistanceSqToEntity(e) < 25.0D) {
					this.rotationYaw = (float)Math.toDegrees(Math.atan2(e.posZ - this.posZ, e.posX - this.posX)) - 90.0F;
					while (this.rotationYaw < 0.0F) this.rotationYaw += 360.0F;
					this.setAttacking(1);
					this.attackEntityAsMob(e);
				} else {
					this.setAttacking(0);
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
		
		if (OreSpawnMain.OreSpawnUtils.isIgnoreable(par1EntityLiving)) return false;
		
		if (!this.getEntitySenses().canSee(par1EntityLiving))
		{
			
			return false;
		}
		if (par1EntityLiving instanceof EntityCreeper)
		{
			return false;
		}
		if (par1EntityLiving instanceof EnderReaper)
		{
			return false;
		}
		if (par1EntityLiving instanceof Triffid)
		{
			return false;
		}
		if (par1EntityLiving instanceof TerribleTerror)
		{
			return false;
		}
		if (par1EntityLiving instanceof LurkingTerror)
		{
			return false;
		}
		if (par1EntityLiving instanceof PitchBlack)
		{
			return false;
		}
		if (par1EntityLiving instanceof Dragon)
		{
			return false;
		}
		
		if (par1EntityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (p.capabilities.isCreativeMode == true) {
				return false;
			}
		}

		return true;
	}

	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(10.0D, 8.0D, 10.0D));
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

	public final int getAttacking()
	{
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	public final void setAttacking(int par1)
	{
		this.dataWatcher.updateObject(20, (byte)par1);
	}

	public final int getOpenClosed()
	{
		return this.dataWatcher.getWatchableObjectByte(21);
	}

	public final void setOpenClosed(int par1)
	{
		this.dataWatcher.updateObject(21, (byte)par1);
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		return true;
	}
}
