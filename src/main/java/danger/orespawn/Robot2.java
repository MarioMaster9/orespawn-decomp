package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;




























public class Robot2 extends EntityMob
{
	private GenericTargetSorter TargetSorter = null;
	private RenderInfo renderdata = new RenderInfo();
	private int just_for_fun = 0;
	private float moveSpeed = 0.3F;

	public Robot2(World par1World) {
		super(par1World);
		this.setSize(3.0F, 6.2F);
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 100;
		this.fireResistance = 200;
		this.isImmuneToFire = true;
		this.TargetSorter = new GenericTargetSorter(this);
		this.renderdata = new RenderInfo();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new MyEntityAIWanderALot(this, 14, 1.0D));
		this.tasks.addTask(2, new EntityAIMoveThroughVillage(this, (double)0.9F, false));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.Robot2_stats.attack);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
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
	}

	public int mygetMaxHealth()
	{
		return OreSpawnMain.Robot2_stats.health;
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
		return OreSpawnMain.Robot2_stats.defense;
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
	}

	
	protected void jump()
	{
		this.motionY += 0.25D;
		super.jump();
	}

	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		if (this.rand.nextInt(4) == 0) {
			return "orespawn:robot_living";
		}
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:robot_hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:robot_death";
	}

	
	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 1.0F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}

	
	
	
	protected Item getDropItem()
	{
		return Item.getItemFromBlock(Blocks.iron_block);
	}

	private ItemStack dropItemRand(Item index, int par1) {
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);
		
		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(2) - (double)OreSpawnMain.OreSpawnRand.nextInt(2), is);
		
		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * par2 - Level of Looting used to kill this mob.
	 */
	protected void dropFewItems(boolean par1, int par2) {
		int var4, var5; ItemStack is = null;
		
		
		var5 = 2 + this.worldObj.rand.nextInt(8);
		for (var4 = 0; var4 < var5; var4++) {
			this.dropItemRand(Item.getItemFromBlock(Blocks.iron_block), 1);
		}

		var5 = 5 + this.worldObj.rand.nextInt(6);
		for (var4 = 0; var4 < var5; var4++) {
			this.dropItemRand(Items.iron_ingot, 1);
		}
		int i, var3;
		
		i = 5 + this.worldObj.rand.nextInt(10);
		for (var4 = 0; var4 < i; var4++) {
			var3 = this.worldObj.rand.nextInt(15);
			switch (var3) {
				case 0:
					is = this.dropItemRand(Items.redstone, 1);
					break;
				case 1:
					is = this.dropItemRand(Items.repeater, 1);
					break;
				case 2:
					is = this.dropItemRand(Items.comparator, 1);
					break;
				case 3:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.redstone_block), 1);
					break;
				case 4:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.dispenser), 1);
					break;
				case 5:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.sticky_piston), 1);
					break;
				case 6:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.piston), 1);
					break;
				case 7:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.lever), 1);
					break;
				case 8:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.redstone_block), 1);
					break;
				case 9:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.light_weighted_pressure_plate), 1);
					break;
				default:
					
					break;
			}
		}
	}
	
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return false;
	}

	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		return super.attackEntityAsMob(par1Entity);
	}

	
	protected void destroyBlock(EntityLivingBase e)
	{
		double x = e.posX + (double)this.worldObj.rand.nextFloat() - (double)this.worldObj.rand.nextFloat();
		double y = e.posY - 1.0D;
		double z = e.posZ + (double)this.worldObj.rand.nextFloat() - (double)this.worldObj.rand.nextFloat();
		
		
		Block bid = this.worldObj.getBlock((int)x, (int)y, (int)z);
		if (bid == Blocks.obsidian) return;
		if (bid == Blocks.bedrock) return;
		if (bid == Blocks.quartz_block) return;
		if (bid == Blocks.mob_spawner) return;
		if (bid == Blocks.redstone_block) return;
		if (bid == Blocks.iron_block) return;
		if (bid == Blocks.chest) return;
		
		
		if (bid != Blocks.air) {
			if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock((int)x, (int)y, (int)z, Blocks.air);
		}
	}
	
	protected void destroyNearbyBlocks()
	{
		double x, y, z;
		int i;
		Block bid;
		for (i = 0; i < 50; i++) {
			x = this.posX + (double)this.worldObj.rand.nextFloat() * (double)6.5F - (double)this.worldObj.rand.nextFloat() * (double)6.5F;
			y = this.posY + 0.1 + (double)this.worldObj.rand.nextFloat() * 8.5D;
			z = this.posZ + (double)this.worldObj.rand.nextFloat() * (double)6.5F - (double)this.worldObj.rand.nextFloat() * (double)6.5F;
			
			
			bid = this.worldObj.getBlock((int)x, (int)y, (int)z);
			if (bid == Blocks.obsidian) continue;
			if (bid == Blocks.bedrock) continue;
			if (bid == Blocks.quartz_block) continue;
			if (bid == Blocks.mob_spawner) continue;
			if (bid == Blocks.redstone_block) continue;
			if (bid == Blocks.iron_block) continue;
			if (bid == Blocks.chest) continue;
			
			
			if (bid != Blocks.air) {
				if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock((int)x, (int)y, (int)z, Blocks.air);
			}
		}
	}

	protected void updateAITasks()
	{
		if (this.isDead) return;
		super.updateAITasks();
		if (this.worldObj.rand.nextInt(6) == 1 && OreSpawnMain.PlayNicely == 0) {
			EntityLivingBase e = null;
			if (this.worldObj.rand.nextInt(50) == 1) this.setAttackTarget(null);
			e = this.getAttackTarget();
			if (e != null && !e.isEntityAlive()) {
				this.setAttackTarget(null);
				e = null;
			}
			if (e == null) {
				e = this.findSomethingToAttack();
			}
			if (e != null)
			{
				double rr = Math.atan2(e.posZ - this.posZ, e.posX - this.posX);
				double rhdir = Math.toRadians((double)((this.rotationYaw + 90.0F) % 360.0F));
				double rdd;
				double pi = 3.1415926545;
				
				rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
				if (rdd > pi) rdd -= pi * 2.0D;
				rdd = Math.abs(rdd);
				
				this.faceEntity(e, 10.0F, 10.0F);
				if (rdd < 1.25D) {
					if (this.getDistanceSqToEntity(e) < (double)((5.0F + e.width / 2.0F) * (5.0F + e.width / 2.0F))) {
						this.setAttacking(1);
						
						if (this.worldObj.rand.nextInt(5) == 0 || this.worldObj.rand.nextInt(6) == 1)
						{
							this.attackEntityAsMob(e);
							for (int i = 0; i < 6; i++) this.destroyBlock(e);
						}

						this.destroyNearbyBlocks();
					}
				} else {
					this.setAttacking(0);
				}
				this.getNavigator().tryMoveToEntityLiving(e, 1.0D);
			} else {
				this.setAttacking(0);
			}
		}
		if (this.getAttacking() == 0 && OreSpawnMain.PlayNicely == 0) {
			if (this.worldObj.rand.nextInt(450) == 1) {
				this.just_for_fun = 50;
			}
			if (this.just_for_fun > 0) --this.just_for_fun;
			if (this.just_for_fun > 0) {
				this.setAttacking(1);
				if (this.worldObj.rand.nextInt(3) == 1) {
					this.destroyNearbyBlocks();
				}
			} else {
				this.setAttacking(0);
			}
		}
		
	}
	
	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		boolean ret = false;
		if (!par1DamageSource.getDamageType().equals("cactus")) {
			ret = super.attackEntityFrom(par1DamageSource, par2);
			Entity e = par1DamageSource.getEntity();
			if (e != null && e instanceof EntityLiving)
			{
				this.setAttackTarget((EntityLiving)e);
				this.setTarget(e);
				this.getNavigator().tryMoveToEntityLiving((EntityLiving)e, 1.2);
			}
			return ret;
		}
		return false;
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
		
		if (par1EntityLiving instanceof EntityMob)
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
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(14.0D, 3.0D, 14.0D));
		Collections.sort(var5, this.TargetSorter);
		Iterator var2 = var5.iterator();
		
		while (var2.hasNext())
		{
			Entity var3 = (Entity)var2.next();
			EntityLivingBase var4 = (EntityLivingBase)var3;
			
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
							if (s.equals("Robo-Pounder")) return true;
						}
					}
				}
			}
		}

		if (this.posY < 50.0D) return false;
		if (this.worldObj.isDaytime() == true) return false;
		
		
		
		
		for (k = -1; k < 1; k++)
		{
			for (j = -1; j <= 1; j++)
			{
				for (i = 1; i < 6; i++)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid != Blocks.air && bid != Blocks.tallgrass) return false;
				}
			}
		}
		if (!this.isValidLightLevel()) return false;
		
		return true;
	}
}
