package danger.orespawn;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
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























public class CaterKiller extends EntityMob {
	private GenericTargetSorter TargetSorter = null;
	private float moveSpeed = 0.35F;
	int foundmob = 0;
	int ticker = 0;

	public CaterKiller(World par1World)
	{
		super(par1World);
		if (OreSpawnMain.PlayNicely == 0) {
			this.setSize(2.9F, 4.6F);
		} else {
			this.setSize(1.45F, 2.3F);
		}
		this.getNavigator().setAvoidsWater(true);
		this.experienceValue = 200;
		this.fireResistance = 100;
		this.TargetSorter = new GenericTargetSorter(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(2, new MyEntityAIWanderALot(this, 16, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)this.mygetMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.moveSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((double)OreSpawnMain.CaterKiller_stats.attack);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(20, (byte)0);
		this.dataWatcher.addObject(21, OreSpawnMain.PlayNicely);
	}

	public int getPlayNicely()
	{
		return this.dataWatcher.getWatchableObjectInt(21);
	}

	protected boolean canDespawn() {
		if (this.isNoDespawnRequired()) return false;
		return true;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		Boolean ret;
		Entity e = null;
		ret = super.attackEntityFrom(par1DamageSource, par2);
		e = par1DamageSource.getEntity();
		if (e != null && e instanceof EntityLiving) {

			this.setAttackTarget((EntityLiving)e);
		}
		return ret;
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
		return OreSpawnMain.CaterKiller_stats.health;
	}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue()
	{
		return OreSpawnMain.CaterKiller_stats.defense;
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


	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		if (this.rand.nextInt(3) == 0) {
			return "orespawn:caterkiller_living";
		}
		return null;
	}


	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return "orespawn:caterkiller_hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		return "orespawn:caterkiller_death";
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 1.5F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	protected float getSoundPitch() {
		return 1.0F;
	}






	protected Item getDropItem()
	{
		return Items.beef;
	}

	private ItemStack dropItemRand(Item index, int par1)
	{
		EntityItem var3 = null;
		ItemStack is = new ItemStack(index, par1, 0);

		var3 = new EntityItem(this.worldObj, this.posX + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), this.posY + 1.0D, this.posZ + (double)OreSpawnMain.OreSpawnRand.nextInt(5) - (double)OreSpawnMain.OreSpawnRand.nextInt(5), is);

		if (var3 != null) this.worldObj.spawnEntityInWorld(var3);
		return is;
	}

	protected void dropFewItems(boolean par1, int par2) {
		int var4, var3;
		int i;
		ItemStack is = null;

		this.dropItemRand(OreSpawnMain.CaterKillerJaw, 1);
		this.dropItemRand(Items.item_frame, 1);

		for (var4 = 0; var4 < 10; ++var4) {
			this.dropItemRand(Items.leather, 1);
		}
		for (var4 = 0; var4 < 6; ++var4) {
			this.dropItemRand(Items.beef, 1);
		}

		i = 1 + this.worldObj.rand.nextInt(5);
		for (var4 = 0; var4 < i; ++var4) {
			var3 = this.worldObj.rand.nextInt(20);
			switch (var3) {
				case 0:
					is = this.dropItemRand(OreSpawnMain.MyUltimateSword, 1);
					break;
				case 1:
					is = this.dropItemRand(OreSpawnMain.MyRuby, 1);
					break;
				case 2:
					is = this.dropItemRand(Item.getItemFromBlock(Blocks.diamond_block), 1);
					break;
				case 3:
					is = this.dropItemRand(OreSpawnMain.MyRubySword, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.baneOfArthropods, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.knockback, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.looting, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireAspect, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.sharpness, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 4:
					is = this.dropItemRand(OreSpawnMain.MyRubyShovel, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 5:
					is = this.dropItemRand(OreSpawnMain.MyRubyPickaxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fortune, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 6:
					is = this.dropItemRand(OreSpawnMain.MyRubyAxe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 7:
					is = this.dropItemRand(OreSpawnMain.MyRubyHoe, 1);
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.efficiency, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 8:
					is = this.dropItemRand(OreSpawnMain.RubyHelmet, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.respiration, 1 + this.worldObj.rand.nextInt(2));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.aquaAffinity, 1 + this.worldObj.rand.nextInt(5));
					break;
				case 9:
					is = this.dropItemRand(OreSpawnMain.RubyBody, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 10:
					is = this.dropItemRand(OreSpawnMain.RubyLegs, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.protection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.blastProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.fireProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.projectileProtection, 1 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 11:
					is = this.dropItemRand(OreSpawnMain.RubyBoots, 1);
					if (this.worldObj.rand.nextInt(6) == 1) is.addEnchantment(Enchantment.featherFalling, 5 + this.worldObj.rand.nextInt(5));
					if (this.worldObj.rand.nextInt(2) == 1) is.addEnchantment(Enchantment.unbreaking, 2 + this.worldObj.rand.nextInt(4));
					break;
				case 12:
					is = this.dropItemRand(OreSpawnMain.MyUltimateBow, 1);
					break;
				default:
					break;
			}
		}


		for (var4 = 0; var4 < 25; ++var4) {
			spawnCreature(this.worldObj, "Butterfly", this.posX, this.posY + 1.0D, this.posZ);
		}

	}


	public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
		Entity var8 = null;

		if (par0World == null) return null;


		var8 = EntityList.createEntityByName(par1, par0World);

		if (var8 != null)
		{

			var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);


			par0World.spawnEntityInWorld(var8);

			((EntityLiving)var8).playLivingSound();
		}

		return var8;
	}






	public void initCreature() {}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		return false;
	}



	
	public boolean attackEntityAsMob(Entity par1Entity) {
		if (super.attackEntityAsMob(par1Entity))
		{
			if (par1Entity != null && par1Entity instanceof EntityLivingBase)
			{
				double ks = 1.2D;
				double inair = 0.1D;
				float f3 = (float)Math.atan2(par1Entity.posZ - this.posZ, par1Entity.posX - this.posX);
				if (par1Entity.isDead || par1Entity instanceof EntityPlayer) inair *= 2.0D;
				par1Entity.addVelocity(Math.cos((double)f3) * ks, inair, Math.sin((double)f3) * ks);
			}
			return true;
		}


		return false;
	}

	
	private int closest = 99999;
	private int tx = 0, ty = 0, tz = 0;
	
	private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
		int found = 0;
		int i, j, d;
		Block bid;
		
		//Fixed x, scan two sides of 3d rectangle
		for (i=-dy;i<=dy;i++){
			for(j=-dz;j<=dz;j++){
				bid = this.worldObj.getBlock(x+dx, y+i, z+j);
				if(bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.log || bid == OreSpawnMain.MyDT || bid == Blocks.log2 || bid == Blocks.leaves2 || bid == OreSpawnMain.MyAppleLeaves || bid == OreSpawnMain.MyExperienceLeaves || bid == OreSpawnMain.MyScaryLeaves || bid == OreSpawnMain.MyPeachLeaves || bid == OreSpawnMain.MyCherryLeaves)
				{
					
					d = (dx*dx) + (j*j) + (i*i);
					if(d<closest){
						closest = d;
						tx = x+dx; ty = y+i; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x-dx, y+i, z+j);
				if(bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.log || bid == OreSpawnMain.MyDT || bid == Blocks.log2 || bid == Blocks.leaves2 || bid == OreSpawnMain.MyAppleLeaves || bid == OreSpawnMain.MyExperienceLeaves || bid == OreSpawnMain.MyScaryLeaves || bid == OreSpawnMain.MyPeachLeaves || bid == OreSpawnMain.MyCherryLeaves)
				{
					
					d = (dx*dx) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x-dx; ty = y+i; tz = z+j;
						found++;
					}
				}
			}
		}

		for (i=-dx;i<=dx;i++){
			for(j=-dz;j<=dz;j++){
				bid = this.worldObj.getBlock(x+i, y+dy, z+j);
				if(bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.log || bid == OreSpawnMain.MyDT || bid == Blocks.log2 || bid == Blocks.leaves2 || bid == OreSpawnMain.MyAppleLeaves || bid == OreSpawnMain.MyExperienceLeaves || bid == OreSpawnMain.MyScaryLeaves || bid == OreSpawnMain.MyPeachLeaves || bid == OreSpawnMain.MyCherryLeaves)
				{
					
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+dy; tz = z+j;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y-dy, z+j);
				if(bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.log || bid == OreSpawnMain.MyDT || bid == Blocks.log2 || bid == Blocks.leaves2 || bid == OreSpawnMain.MyAppleLeaves || bid == OreSpawnMain.MyExperienceLeaves || bid == OreSpawnMain.MyScaryLeaves || bid == OreSpawnMain.MyPeachLeaves || bid == OreSpawnMain.MyCherryLeaves)
				{
					
					d = (dy*dy) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y-dy; tz = z+j;
						found++;
					}
				}
			}
		}

		for (i=-dx;i<=dx;i++){
			for(j=-dy;j<=dy;j++){
				bid = this.worldObj.getBlock(x+i, y+j, z+dz);
				if(bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.log || bid == OreSpawnMain.MyDT || bid == Blocks.log2 || bid == Blocks.leaves2 || bid == OreSpawnMain.MyAppleLeaves || bid == OreSpawnMain.MyExperienceLeaves || bid == OreSpawnMain.MyScaryLeaves || bid == OreSpawnMain.MyPeachLeaves || bid == OreSpawnMain.MyCherryLeaves)
				{
					
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z+dz;
						found++;
					}
				}
				bid = this.worldObj.getBlock(x+i, y+j, z-dz);
				if(bid == Blocks.leaves || bid == Blocks.vine || bid == Blocks.log || bid == OreSpawnMain.MyDT || bid == Blocks.log2 || bid == Blocks.leaves2 || bid == OreSpawnMain.MyAppleLeaves || bid == OreSpawnMain.MyExperienceLeaves || bid == OreSpawnMain.MyScaryLeaves || bid == OreSpawnMain.MyPeachLeaves || bid == OreSpawnMain.MyCherryLeaves)
				{
					
					d = (dz*dz) + (j*j) + (i*i);
					if(d < closest){
						closest = d;
						tx = x+i; ty = y+j; tz = z-dz;
						found++;
					}
				}
			}
		}

		if (found != 0) return true;
		return false;
	}

	
	
	
	protected void updateAITasks()
	{
		if (this.isDead) return;
		super.updateAITasks();
		this.dataWatcher.updateObject(21, OreSpawnMain.PlayNicely);
		int i; int j; int k; double dx; double dz;
		if (this.getHealth() + 1.0F < this.getMaxHealth()) {
			++this.ticker;
			if (this.ticker > 2400) {
				spawnCreature(this.worldObj, "Brutalfly", this.posX, this.posY + 4.0D, this.posZ);
				this.playSound("random.explode", 1.0F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
				for (i = 0; i < 10; ++i) {
					spawnCreature(this.worldObj, "Butterfly", this.posX, this.posY + 1.0D + (double)this.worldObj.rand.nextInt(4), this.posZ);
				}
				this.setDead();
				return;
			}
		}

		if (this.isInWeb) {
			for (i = -2; i <= 2; ++i) {
				for (j = -1; j < 5; ++j) {
					for (k = -2; k <= 2; ++k) {
						if (this.worldObj.getBlock((int)this.posX + i, (int)this.posY + j, (int)this.posZ + k) == Blocks.web) {
							this.worldObj.setBlock((int)this.posX + i, (int)this.posY + j, (int)this.posZ + k, Blocks.air);
							this.worldObj.setBlockMetadataWithNotify((int)this.posX + i, (int)this.posY + j, (int)this.posZ + k, 0, 3);
						}
					}
				}
			}
			this.isInWeb = false;
		}
		if (this.worldObj.rand.nextInt(4) == 0)
		{
			EntityLivingBase e = this.getAttackTarget();
			if (e != null && !e.isEntityAlive()) {
				this.setAttackTarget(null);
				e = null;
			}
			if (this.worldObj.rand.nextInt(200) == 0) {
				this.setAttackTarget(null);
			}
			if (e == null) {
				e = this.findSomethingToAttack();
			}
			if (e != null) {
				this.foundmob = 1;
				this.faceEntity(e, 10.0F, 10.0F);
				if (this.getDistanceSqToEntity(e) < (double)((5.0F + e.width / 2.0F) * (5.0F + e.width / 2.0F))) {
					this.setAttacking(1);
					
					if (this.worldObj.rand.nextInt(3) == 0 || this.worldObj.rand.nextInt(4) == 1)
					{
						this.attackEntityAsMob(e);
					}
				} else {
					this.setAttacking(0);
					this.getNavigator().tryMoveToEntityLiving(e, 1.25D);
					if (this.worldObj.rand.nextInt(4) == 0) {
						dx = e.posX;
						dz = e.posZ;
						dx += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0D;
						dz += (double)(this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 2.0D;
						for (i = 2; i > -2; --i) {
							if (this.worldObj.getBlock((int)dx, (int)e.posY + i + 1, (int)dz) == Blocks.air) {
								if (this.worldObj.getBlock((int)dx, (int)e.posY + i, (int)dz) != Blocks.air) {
									this.worldObj.setBlock((int)dx, (int)e.posY + i + 1, (int)dz, Blocks.web);
									break;
								}
							}
						}
					}
				}
				
			} else {
				this.setAttacking(0);
				this.foundmob = 0;
			}
		}
		if (this.worldObj.rand.nextInt(8) == 0 && this.getHealth() < (float)this.mygetMaxHealth() || this.worldObj.rand.nextInt(30) == 0)
		{
			
			
			if (OreSpawnMain.PlayNicely == 0) {
				this.closest = 99999;
				this.tx = this.ty = this.tz = 0;
				for (i = 1; i < 13; ++i) {
					j = i;
					if (j > 9) j = 9;
					if (this.scan_it((int)this.posX, (int)this.posY + 1, (int)this.posZ, i, j, i) == true) break;
					if (i >= 9) ++i;
				}
				
				if (this.closest < 99999)
				{
					if (this.foundmob == 0) this.getNavigator().tryMoveToXYZ((double)this.tx, (double)this.ty, (double)this.tz, 1.0D);
					if (this.closest < 81)
					{
						if (this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) this.worldObj.setBlock(this.tx, this.ty, this.tz, Blocks.air, 0, 2);
						this.heal(2.0F);
						if (this.worldObj.rand.nextInt(20) == 1) this.playSound("random.burp", 1.0F, this.worldObj.rand.nextFloat() * 0.2F + 0.9F);
					}
				}
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
		
		if (!this.MyCanSee(par1EntityLiving))
		{
			
			return false;
		}
		
		if (par1EntityLiving instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer)par1EntityLiving;
			if (p.capabilities.isCreativeMode == true) {
				return false;
			}
			return true;
		}
		if (par1EntityLiving instanceof CaterKiller)
		{
			return false;
		}
		
		if (par1EntityLiving instanceof EntityMob)
		{
			return true;
		}
		
		if (OreSpawnMain.OreSpawnUtils.isAttackableNonMob(par1EntityLiving)) {
			return true;
		}
		
		return false;
	}

	
	private EntityLivingBase findSomethingToAttack()
	{
		if (OreSpawnMain.PlayNicely != 0) return null;
		List var5 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.boundingBox.expand(20.0D, 8.0D, 20.0D));
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

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere() {
		Block bid;
		int i;
		int j;
		int k;

		for (k = -3; k < 3; ++k)
		{
			for (j = -3; j < 3; ++j)
			{
				for (i = 0; i < 5; ++i)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid == Blocks.mob_spawner) {
						TileEntityMobSpawner tileentitymobspawner = null;
						tileentitymobspawner = (TileEntityMobSpawner)this.worldObj.getTileEntity((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
						String s = tileentitymobspawner.func_145881_a().getEntityNameToSpawn();
						if (s != null) {
							if (s.equals("CaterKiller")) return true;
						}
					}
				}
			}
		}

		if (this.posY < 50.0D) return false;
		if (this.worldObj.rand.nextInt(10) != 0) return false;
		if (!this.worldObj.isDaytime()) return false;

		
		
		for (k = -1; k < 2; ++k)
		{
			for (j = -1; j < 2; ++j)
			{
				for (i = 1; i < 5; ++i)
				{
					bid = this.worldObj.getBlock((int)this.posX + j, (int)this.posY + i, (int)this.posZ + k);
					if (bid != Blocks.air && bid != Blocks.leaves && bid != Blocks.leaves2 && bid != Blocks.log && bid != Blocks.log2) return false;
				}
			}
		}

		
		CaterKiller target = null;
		target = (CaterKiller)this.worldObj.findNearestEntityWithinAABB(CaterKiller.class, this.boundingBox.expand(48.0D, 16.0D, 48.0D), this);
		if (target != null)
		{
			return false;
		}
		return true;
	}

	
	
	
	
	public boolean MyCanSee(EntityLivingBase e) {
		float startx;
		float starty;
		float startz;
		double xzoff = 2.5D;
		double cx, cz;
		float dx;
		float dy;
		float dz;
		int i;
		Block bid;
		int nblks = 10;
		
		cx = posX-(xzoff*Math.sin(Math.toRadians(rotationYaw)));
		cz = posZ+(xzoff*Math.cos(Math.toRadians(rotationYaw)));
		startx = (float)(cx);
		starty = (float)(posY+3);
		startz = (float)(cz);
		dx = (float)((e.posX-startx)/10.0D);
		dy = (float)((e.posY+(e.height/2.0F)-starty)/10.0D);
		dz = (float)((e.posZ-startz)/10.0D);
		
		
		
		
		if(Math.abs(dx) > 1.0){
			dy = dy/Math.abs(dx);
			dz = dz/Math.abs(dx);
			nblks *= Math.abs(dx);
			if(dx > 1)dx = 1;
			if(dx < -1)dx = -1;
		}
		if(Math.abs(dy) > 1.0){
			dx = dx/Math.abs(dy);
			dz = dz/Math.abs(dy);
			nblks *= Math.abs(dy);
			if(dy > 1)dy = 1;
			if(dy < -1)dy = -1;
		}
		if(Math.abs(dz) > 1.0){
			dy = dy/Math.abs(dz);
			dx = dx/Math.abs(dz);
			nblks *= Math.abs(dz);
			if(dz > 1)dz = 1;
			if(dz < -1)dz = -1;
		}

		
		
		for(i=0;i<nblks;i++){
			startx += dx;
			starty += dy;
			startz += dz;
			bid = this.worldObj.getBlock((int)startx, (int)starty, (int)startz);
			if (bid == Blocks.air) continue;
			if (bid == Blocks.web) continue;
			if (bid == Blocks.tallgrass) continue;
			if (bid == Blocks.leaves) continue;
			return false;
		}

		
		return true;
	}
}